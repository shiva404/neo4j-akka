package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.akka.goodreads.GoodreadsAsynchHandler;
import com.campusconnect.neo4j.da.iface.*;
import com.campusconnect.neo4j.da.mapper.DBMapper;
import com.campusconnect.neo4j.da.utils.AuditEventHelper;
import com.campusconnect.neo4j.da.utils.EventHelper;
import com.campusconnect.neo4j.da.utils.Queries;
import com.campusconnect.neo4j.da.utils.TargetHelper;
import com.campusconnect.neo4j.exceptions.NotFoundException;
import com.campusconnect.neo4j.mappers.Neo4jToWebMapper;
import com.campusconnect.neo4j.repositories.UserRelationRepository;
import com.campusconnect.neo4j.repositories.UserRepository;
import com.campusconnect.neo4j.types.common.AuditEventType;
import com.campusconnect.neo4j.types.common.NotificationType;
import com.campusconnect.neo4j.types.common.Target;
import com.campusconnect.neo4j.types.common.UserRelationType;
import com.campusconnect.neo4j.types.neo4j.*;
import com.campusconnect.neo4j.types.web.Event;
import com.campusconnect.neo4j.types.web.FriendRecommendation;
import com.campusconnect.neo4j.types.web.Notification;
import com.campusconnect.neo4j.util.Constants;
import com.campusconnect.neo4j.util.ErrorCodes;
import com.googlecode.ehcache.annotations.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.neo4j.rest.graphdb.entity.RestRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import java.util.*;

import static com.campusconnect.neo4j.da.utils.FilterHelper.replaceUsersWithExistingFriends;

/**
 * Created by sn1 on 1/19/15.
 */
public class UserDaoImpl implements UserDao {

    public static final String GET_RELATED_USERS = "match (currentUser:User {id: {userId}})-[relation:" + Constants.CONNECTED_RELATION + "]->(user:User) return user, relation";
    String SEARCH_STRING = "(?i)%1$s.*";

    @Autowired
    ReminderDao reminderDao;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRelationRepository userRelationRepository;

    @Autowired
    GoodreadsAsynchHandler goodreadsAsynchHandler;

    @Autowired
    AuditEventDao auditEventDao;

    @Autowired
    AddressDao addressDao;

    @Autowired
    NotificationDao notificationDao;
    ObjectMapper objectMapper;
    @Autowired
    private FBDao fbDao;
    private Neo4jTemplate neo4jTemplate;
    @Autowired
    private EmailDao emailDao;

    public UserDaoImpl(Neo4jTemplate neo4jTemplate) {
        this.neo4jTemplate = neo4jTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public UserDaoImpl() {
    }

    @Override
    public User createUser(User user, String accessToken) {
        user.setId(UUID.randomUUID().toString());
        if (accessToken != null && user.getFbId() != null) {
            String profileImageUrl = fbDao.getUserProfileImage(user.getFbId(),
                    accessToken);
            if (profileImageUrl != null) {
                user.setProfileImageUrl(profileImageUrl);
            }
        }

        User createdUser = neo4jTemplate.save(user);
        try {

            Event userCreatedEvent = EventHelper.createPrivateEvent(AuditEventType.USER_CREATED.toString(), TargetHelper.createUserTarget(createdUser));

            String serializedEvent = EventHelper.serializeEvent(userCreatedEvent);
            AuditEvent rawAuditEvent = AuditEventHelper.createAuditEvent(createdUser, serializedEvent);
            AuditEvent auditEvent = neo4jTemplate.save(rawAuditEvent);
            NotificationEntity notificationEntityFresh = new NotificationEntity();
            NotificationEntity notificationEntityPast = new NotificationEntity();

            notificationEntityFresh = notificationDao
                    .saveNotification(notificationEntityFresh);
            notificationEntityPast = notificationDao
                    .saveNotification(notificationEntityPast);
            UserEventRelationship userEventRelationship = new UserEventRelationship(
                    auditEvent, createdUser);
            UserNotificationRelationship userFreshNotificationRelationship = new UserNotificationRelationship(
                    createdUser, notificationEntityFresh,
                    NotificationType.FRESH.toString());
            UserNotificationRelationship userPastNotificationRelationship = new UserNotificationRelationship(
                    createdUser, notificationEntityPast,
                    NotificationType.PAST.toString());
            neo4jTemplate.save(userEventRelationship);
            neo4jTemplate.save(userFreshNotificationRelationship);
            neo4jTemplate.save(userPastNotificationRelationship);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return createdUser;
    }


    @Override
    @Cacheable(cacheName = "userIdCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public User getUser(String userId) {
        if(userId == null)
            return null;
        User user = userRepository.findBySchemaPropertyValue("id", userId);
        if (user == null) {
            throw new NotFoundException(ErrorCodes.USER_NOT_FOUND, "User with id:" + userId + " not found");
        }
        user.setUserRelation(Constants.SELF);
        return user;
    }

    @Override
    public User getUserByFbId(String fbId) {
        return userRepository.findBySchemaPropertyValue("fbId", fbId);
    }

    @Override
    public User getUserByGoodreadsId(String goodreadsId) {
        return userRepository.findBySchemaPropertyValue("goodreadsId", goodreadsId);
    }

    @Override
    public User getUserByGoogleId(String googleId) {
        return userRepository.findBySchemaPropertyValue("googleId", googleId);
    }

    public User getUserByEmail(String email) {
        return userRepository.findBySchemaPropertyValue("email", email);
    }

    @Override
    public List<User> search(String searchString, String userId) {
        List<User> users = userRepository.searchUsers(String.format(SEARCH_STRING, searchString));
        if (userId != null) {
            List<User> friends = getRelatedUsers(userId);
            User currentUser = getUser(userId);
            friends.add(currentUser);
            return replaceUsersWithExistingFriends(users, friends);
        }
        return users;
    }

    @Override
    public List<User> getPendingFriendReq(String userId) {
        List<User> pendingFriends = userRelationRepository.getPendingFriendRequests(userId);
        for (User user : pendingFriends) {
            user.setUserRelation(Constants.FRIEND_REQ_APPROVAL_PENDING);
        }
        return pendingFriends;
    }

    @Override
    public List<UserRelation> getUsersRelationShip(User user, User fellowUser) {
        return userRelationRepository.getUsersRelationship(user.getId(), fellowUser.getId());
    }

    @Override
    //todo: cache this
    public List<User> getRelatedUsers(String loggedInUser) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("userId", loggedInUser);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query(GET_RELATED_USERS, params);

        List<User> users = getUsersAlongWithRelationFromResultMap(mapResult);
        List<User> pendingApprovalFriends = getPendingFriendReq(loggedInUser);
        users = replaceUsersWithExistingFriends(users, pendingApprovalFriends);
        return users;
    }

    @Override
    public List<FriendRecommendation> getFriendsRecWithCount(String userId, String size) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query(Queries.GET_FRIEND_REC_ONLY_COUNT, params);
        return getFriendsRecWithCountFromMap(mapResult);
    }

    private List<FriendRecommendation> getFriendsRecWithFriendsFromMap(Result<Map<String, Object>> mapResult) {
        List<FriendRecommendation> friendRecommendations = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode friendOfFriendNode = (RestNode) objectMap.get("friend_of_friend");
            RestNode friendNode = (RestNode) objectMap.get("friend");

            User friendOfFriend = DBMapper.getUserFromRestNode(friendOfFriendNode);
            User friend = DBMapper.getUserFromRestNode(friendNode);
            boolean appended = false;
            for (FriendRecommendation friendRecommendation : friendRecommendations) {
                if (friendRecommendation.getUser().getId().equals(friendOfFriend.getId())) {
                    friendRecommendation.getMutualFriends().add(Neo4jToWebMapper.mapUserNeo4jToWeb(friend));
                    friendRecommendation.setSize(friendRecommendation.getSize() + 1);
                    appended = true;
                }
            }
            if (!appended) {
                FriendRecommendation friendRecommendation = new FriendRecommendation();
                friendRecommendation.setUser(Neo4jToWebMapper.mapUserNeo4jToWeb(friendOfFriend));
                friendRecommendation.getMutualFriends().add(Neo4jToWebMapper.mapUserNeo4jToWeb(friend));
                friendRecommendation.setSize(1);
                friendRecommendations.add(friendRecommendation);
            }
        }
        return friendRecommendations;
    }

    @Override
    public List<FriendRecommendation> getFriendsRecWithFriends(String userId, String size) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query(Queries.GET_FRIEND_REC_WITH_FRIENDS, params);
        return getFriendsRecWithFriendsFromMap(mapResult);
    }

    @Override
    public void deleteUser(String userId) {
        User user = getUser(userId);
        //delete notifications of the user
        notificationDao.deleteNotificationOfUser(userId);
        //delete events of the user
        auditEventDao.deleteAuditEventsOfUser(userId);
        //delete address of the user
        addressDao.deleteAddressOfUser(user);
        //Delete reminders
        reminderDao.deleteRemindersOfUser(userId);

        neo4jTemplate.delete(user);
    }

    private List<FriendRecommendation> getFriendsRecWithCountFromMap(Result<Map<String, Object>> mapResult) {
        List<FriendRecommendation> friendRecommendations = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode friendOfFriendNode = (RestNode) objectMap.get("friend_of_friend");
            Integer mutualFriendsCount = (Integer) objectMap.get("count");

            User friendOfFriend = DBMapper.getUserFromRestNode(friendOfFriendNode);
            FriendRecommendation friendRecommendation = new FriendRecommendation(Neo4jToWebMapper.mapUserNeo4jToWeb(friendOfFriend), null, mutualFriendsCount);
            friendRecommendations.add(friendRecommendation);
        }
        return friendRecommendations;
    }

    private List<User> getUsersAlongWithRelationFromResultMap(Result<Map<String, Object>> mapResult) {
        List<User> users = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode userNode = (RestNode) objectMap.get("user");
            RestRelationship userRawRelation = (RestRelationship) objectMap.get("relation");

            User user = DBMapper.getUserFromRestNode(userNode);
            //set all the relations to the user
            DBMapper.setUserRelationFieldsToUser(userRawRelation, user);
            users.add(user);
        }
        return users;
    }

    @Override
    public List<User> searchFriends(String userId, String searchString) {
        List<User> users = userRepository.searchFriends(userId, String.format(SEARCH_STRING, searchString));
        List<User> friends = getRelatedUsers(userId);
        return replaceUsersWithExistingFriends(users, friends);
    }

    @Override
    public List<User> getRandomUsers(int size, String userId) {
        List<User> users = userRepository.getRandomUsers(size);
        removeCurrentUserFromList(users, userId);
//        List<User> friends = getRelatedUsers(userId);
        return users;
    }

    private void removeCurrentUserFromList(List<User> users, String userId) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            // Do something
            if (user.getId().equals(userId)) {
                iterator.remove();
            }
        }
    }

    @Override
//    @TriggersRemove(cacheName = "userFollowing", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public void createFollowingRelation(@PartialCacheKey User user, User follower) {

        Long now = System.currentTimeMillis();
        UserRelation userRelation = new UserRelation(user, follower, now, UserRelationType.FOLLOWING.toString());
        neo4jTemplate.save(userRelation);

        try {
            Long currentTime = System.currentTimeMillis();
            Target targetforAuditEvent = TargetHelper.createUserTarget(follower);
            Event followedUSerEvent = new Event(AuditEventType.FOLLOWING.toString(), targetforAuditEvent, currentTime, true);
            auditEventDao.addEvent(user.getId(), followedUSerEvent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void confirmFriendRelation(@PartialCacheKey User user, User friend) {
        List<UserRelation> existingRelation = userRelationRepository.getUsersRelationship(user.getId(), friend.getId());
        UserRelation relation;
        if (existingRelation.size() == 1) {
            relation = existingRelation.get(0);
            if (relation.getType().equals(UserRelationType.FRIEND_REQUEST_PENDING.toString())) {

                relation.setType(UserRelationType.FRIEND.toString());
                neo4jTemplate.save(relation);
                try {
                    Long currentTime = System.currentTimeMillis();


                    Target targetEventUser = TargetHelper.createUserTarget(friend);
                    Event beFriendUserEvent1 = EventHelper.createPublicEvent(AuditEventType.FRIEND.toString(), targetEventUser);
                    auditEventDao.addEvent(user.getId(), beFriendUserEvent1);


                    Target targetEventFriend = TargetHelper.createUserTarget(user);
                    Event beFriendUserEvent2 = EventHelper.createPublicEvent(AuditEventType.FRIEND.toString(), targetEventFriend);
                    auditEventDao.addEvent(friend.getId(), beFriendUserEvent2);

                    Target targetNotification = TargetHelper.createUserTarget(friend);
                    Notification beFriendNotification = new Notification(targetNotification, currentTime, Constants.FRIEND_REQUEST_ACCEPTED_NOTIFICATION_TYPE);
                    notificationDao.addNotification(user.getId(), beFriendNotification);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            } else if (relation.getType().equals(UserRelationType.FRIEND.toString())) {
                throw new IllegalArgumentException(
                        "You are already friend's with " + friend.getName());
            }
        } else if (existingRelation.size() > 1) {
            int index = checkUserRelationExists(existingRelation,
                    UserRelationType.FRIEND.toString());
            if (index != -1) {
                deleteRelationExceptIndex(existingRelation, index);
                throw new IllegalArgumentException(
                        "You are already friend's with " + friend.getName());
            }
            index = checkUserRelationExists(existingRelation,
                    UserRelationType.FRIEND_REQUEST_PENDING.toString());
            if (index != -1) {
                deleteRelationExceptIndex(existingRelation, index);
                throw new IllegalArgumentException(
                        "You have already sent a friend Request");
            }
        }
    }

    @Override
    @TriggersRemove(cacheName = "userIdCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public User updateUser(@PartialCacheKey String userId, User user, boolean addEvent) {
        user.setUserRelation(null);
        if (addEvent) {
            Target target = TargetHelper.createUserTarget(user);
            Event event = EventHelper.createPrivateEvent(AuditEventType.USER_UPDATED.toString(), target);
            AuditEvent auditEvent = auditEventDao.addEvent(user.getId(), event);
        }
        return neo4jTemplate.save(user);
    }

    @Override
    //@Cacheable(cacheName = "userFollowers", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public List<User> getFollowers(String userId) {
        return userRepository.getFollowers(userId);
    }

    @Override
    //   @Cacheable(cacheName = "userFollowing", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public List<User> getFollowing(String userId) {
        return userRepository.getFollowing(userId);
    }


    @Override
    public void addAddressToUser(Address address, User user) {
        if (address.getId() == null)
            address = neo4jTemplate.save(address);
        AddressRelation addressRelation = new AddressRelation(user, address);
        neo4jTemplate.save(addressRelation);

        try {
            Long currentTime = System.currentTimeMillis();

            Target target = TargetHelper.createUserTarget(user);
            Event addAddressToUserEvent = EventHelper.createPrivateEvent(AuditEventType.ADDED_ADDRESS.toString(), target);
            auditEventDao.addEvent(user.getId(), addAddressToUserEvent);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void synchWishListRec(String userId) {
        User user = getUser(userId);
        if (user != null)
            goodreadsAsynchHandler.getFriendRecForUser(userId, user.getGoodreadsId(), user.getGoodreadsAccessToken(), user.getGoodreadsAccessTokenSecret());
    }


    @Override
    public void setReminder(ReminderRelationShip reminderRelationShip, boolean sendNotificationAddEvent) {

        neo4jTemplate.save(reminderRelationShip);
        if (sendNotificationAddEvent) {
            User createdByUser = getUser(reminderRelationShip.getCreatedBy());
            Target reminderTarget = TargetHelper.createReminderTarget(reminderRelationShip.getReminder(), createdByUser, reminderRelationShip.getReminderFor());
            Event event = EventHelper.createPrivateEvent(AuditEventType.REMINDER_SENT.toString(), reminderTarget);
            auditEventDao.addEvent(reminderRelationShip.getCreatedBy(), event);

            Target reminderNotificationTarget = TargetHelper.createReminderTarget(reminderRelationShip.getReminder(), createdByUser, reminderRelationShip.getReminderFor());

            Notification reminderNotification = new Notification(reminderNotificationTarget, System.currentTimeMillis(), Constants.REMINDER_CREATED_NOTIFICATION_TYPE);
            notificationDao.addNotification(reminderRelationShip.getReminderFor().getId(), reminderNotification);
        }
    }

    @Override
    public List<User> getFriends(String queryUserId, String currentUserId) {
        List<User> friends = userRelationRepository.getFriends(queryUserId);
        if (currentUserId == null) {
            return friends;
        } else if (currentUserId.equals(queryUserId)) {
            for (User user : friends) {
                user.setUserRelation(UserRelationType.FRIEND.toString());
            }
            return friends;
        } else {
            List<User> userFriends = userRelationRepository.getFriends(currentUserId);
            User currentUser = getUser(currentUserId);
            userFriends.add(currentUser);
            for (User user : userFriends) {
                user.setUserRelation(UserRelationType.FRIEND.toString());
            }
            return replaceUsersWithExistingFriends(friends, userFriends);
        }
    }

    @Override
    public List<User> getMutualFriends(String currentUser, String userId) {
        List<User> mutualFriends = userRelationRepository.getMutualFriends(currentUser, userId);
        for (User user : mutualFriends) {
            user.setUserRelation(UserRelationType.FRIEND.toString());
        }
        return mutualFriends;
    }

    @Override
    public void createFriendRelationWithPending(User user, User friend) {

        List<UserRelation> existingRelation = userRelationRepository.getUsersRelationship(user.getId(), friend.getId());

        UserRelation relation;
        if (existingRelation.size() == 1) {
            relation = existingRelation.get(0);
            if (relation.getType().equals(
                    UserRelationType.FRIEND_REQUEST_PENDING.toString())) {
                throw new IllegalArgumentException(
                        "You have already sent a friend Request");
            } else if (relation.getType().equals(UserRelationType.FRIEND.toString())) {
                throw new IllegalArgumentException(
                        "You are already friend's with " + friend.getName());
            }
        } else if (existingRelation.size() > 1) {
            int index = checkUserRelationExists(existingRelation,
                    UserRelationType.FRIEND.toString());
            if (index != -1) {
                deleteRelationExceptIndex(existingRelation, index);
                throw new IllegalArgumentException(
                        "You are already friend's with " + friend.getName());
            }
            index = checkUserRelationExists(existingRelation,
                    UserRelationType.FRIEND_REQUEST_PENDING.toString());
            if (index != -1) {
                deleteRelationExceptIndex(existingRelation, index);
                throw new IllegalArgumentException(
                        "You have already sent a friend Request");
            }
        } else {
            UserRelation userRelation = new UserRelation(user, friend,
                    System.currentTimeMillis(),
                    UserRelationType.FRIEND_REQUEST_PENDING.toString());
            neo4jTemplate.save(userRelation);
            emailDao.sendFriendRequestEmail(user, friend);
            //send notification to friend

            Target targetNotification = TargetHelper.createUserTarget(user);

            Notification sentFriendRequestNotification = new Notification(targetNotification, System.currentTimeMillis(), Constants.FRIEND_REQUEST_SENT_NOTIFICATION_TYPE);
            notificationDao.addNotification(friend.getId(), sentFriendRequestNotification);
        }
    }

    @Override
    public void deleteFriendRequest(String userId, String friendUserId, String deletetype) {
        List<UserRelation> userRelations = userRelationRepository.getUsersRelationship(userId, friendUserId);
        for (UserRelation userRelation : userRelations) {
            neo4jTemplate.delete(userRelation);
        }
        //TODO: internal event according to delete type
    }

    private void deleteRelationExceptIndex(List<UserRelation> existingRelation,
                                           int index) {
        int count = 0;
        for (UserRelation relation : existingRelation) {
            if (count != index) {
                neo4jTemplate.delete(relation);
            }
            count++;
        }
    }

    private int checkUserRelationExists(List<UserRelation> existingRelation,
                                        String type) {
        for (int i = 0; i < existingRelation.size(); i++) {
            if (existingRelation.get(i).getType().equals(type)) {
                return i;
            }
        }
        return -1;
    }


}
