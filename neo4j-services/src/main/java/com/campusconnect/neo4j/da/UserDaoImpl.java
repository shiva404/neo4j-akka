package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.akka.goodreads.GoodreadsAsynchHandler;
import com.campusconnect.neo4j.da.iface.AuditEventDao;
import com.campusconnect.neo4j.da.iface.EmailDao;
import com.campusconnect.neo4j.da.iface.NotificationDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.repositories.BookRepository;
import com.campusconnect.neo4j.repositories.UserRelationRepository;
import com.campusconnect.neo4j.repositories.UserRepository;
import com.campusconnect.neo4j.types.common.*;
import com.campusconnect.neo4j.types.neo4j.*;
import com.campusconnect.neo4j.types.web.Event;
import com.campusconnect.neo4j.types.web.Notification;
import com.googlecode.ehcache.annotations.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import java.util.*;

/**
 * Created by sn1 on 1/19/15.
 */
public class UserDaoImpl implements UserDao {

    String SEARCH_STRING = "(?i)%1$s.*";

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRelationRepository userRelationRepository;

    @Autowired
    GoodreadsAsynchHandler goodreadsAsynchHandler;
    @Autowired
    AuditEventDao auditEventDao;

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

    public static Target createTargetToUser(User user) {
        String targetEventUserId = user.getId();
        String targetEventUserName = user.getName();
        String targetEventUrl = "users/" + targetEventUserId;
        return new Target(IdType.USER_ID.toString(), targetEventUserName,
                targetEventUrl);
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
            Long currentTime = System.currentTimeMillis();
            Event userCreatedEvent = new Event(
                    AuditEventType.CREATED.toString(), null, currentTime, false);
            String serializedEvent = objectMapper
                    .writeValueAsString(userCreatedEvent);
            AuditEvent auditEvent = new AuditEvent();
            Set<String> events = auditEvent.getEvents();
            auditEvent.setUserId(createdUser.getId());
            auditEvent.setUserName(createdUser.getName());
            auditEvent.setImageUrl(createdUser.getProfileImageUrl());
            NotificationEntity notificationEntityFresh = new NotificationEntity();
            NotificationEntity notificationEntityPast = new NotificationEntity();
            events.add(serializedEvent);
            auditEvent = auditEventDao.saveEvent(auditEvent);
            notificationEntityFresh = notificationDao
                    .savenotification(notificationEntityFresh);
            notificationEntityPast = notificationDao
                    .savenotification(notificationEntityPast);
            UserEventRelationship userEventRelationship = new UserEventRelationship(
                    auditEvent, createdUser);
            UserNotificationRelationship userFreshNotificationRelationship = new UserNotificationRelationship(
                    createdUser, notificationEntityFresh,
                    NotificationType.FRESH.toString());
            UserNotificationRelationship userPastNotificationRelationship = new UserNotificationRelationship(
                    createdUser, notificationEntityPast,
                    NotificationType.PAST.toString());
            neo4jTemplate.save(userFreshNotificationRelationship);
            neo4jTemplate.save(userPastNotificationRelationship);
            neo4jTemplate.save(userEventRelationship);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return createdUser;
    }


    @Override
    @Cacheable(cacheName = "userIdCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public User getUser(String userId) {
        return userRepository.findBySchemaPropertyValue("id", userId);
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
        List<User> friends = findFriends(userId, userId);
        friends.addAll(findPendingFriendReq(userId));
        return replaceBooksWithExistingFriends(users, friends);
    }

    @Override
    public List<User> findPendingFriendReq(String userId) {
        List<User> pendingFriends = userRelationRepository.getPendingFriendRequests(userId);
        for (User user : pendingFriends) {
            user.setUserRelation(UserRelationType.FRIEND_REQUEST_PENDING.toString());
        }
        return pendingFriends;
    }

    @Override
    public List<UserRelation> getUsersRelationShip(User user, User fellowUser) {
        return userRelationRepository.getUsersRelationship(user.getId(), fellowUser.getId());
    }

    @Override
    public List<User> searchFriends(String userId, String searchString) {
        List<User> users = userRepository.searchFriends(userId, String.format(SEARCH_STRING, searchString));
        List<User> friends = findFriends(userId, userId);
        friends.addAll(findPendingFriendReq(userId));
        return replaceBooksWithExistingFriends(users, friends);
    }

    @Override
    public List<User> getRandomUsers(int size, String userId) {
        List<User> users = userRepository.getRandomUsers(size);
        List<User> friends = findFriends(userId, userId);
        friends.addAll(findPendingFriendReq(userId));
        return replaceBooksWithExistingFriends(users, friends);
    }

    @Override
//    @TriggersRemove(cacheName = "userFollowing", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public void createFollowingRelation(@PartialCacheKey User user, User follower) {

        long now = System.currentTimeMillis();
        UserRelation userRelation = new UserRelation(user, follower, now, UserRelationType.FOLLOWING.toString());
        neo4jTemplate.save(userRelation);

        try {
            Long currentTime = System.currentTimeMillis();
            Target targetforAuditEvent = createTargetToUser(follower);
            Event followedUSerEvent = new Event(AuditEventType.FOLLOWING.toString(), targetforAuditEvent, currentTime, true);
            auditEventDao.addEvent(user.getId(), followedUSerEvent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void confirmFriendRelation(@PartialCacheKey User user, User friend) {

        //	long now = System.currentTimeMillis();
        List<UserRelation> existingRelation = userRelationRepository.getUsersRelationship(user.getId(), friend.getId());

        UserRelation relation;
        if (existingRelation.size() == 1) {
            relation = existingRelation.get(0);
            if (relation.getType().equals(UserRelationType.FRIEND_REQUEST_PENDING.toString())) {

                relation.setType(UserRelationType.FRIEND.toString());
                neo4jTemplate.save(relation);
                try {
                    Long currentTime = System.currentTimeMillis();

                    String targetNotificationUserId = friend.getId();
                    String targetNoitficationUrl = "users/" + targetNotificationUserId;
                    String targetNotificationstring = friend.getName();
                    Target targetEventUser = createTargetToUser(friend);
                    Target targetEventFriend = createTargetToUser(user);
                    Event beFriendUserEvent1 = new Event(AuditEventType.FRIEND.toString(), targetEventUser, System.currentTimeMillis(), true);
                    Event beFriendUserEvent2 = new Event(AuditEventType.FRIEND.toString(), targetEventFriend, System.currentTimeMillis(), true);
                    Target targetNotification = new Target(IdType.USER_ID.toString(), targetNotificationstring + " accepted your friend request", targetNoitficationUrl);

                    Notification beFriendNotification = new Notification(targetNotification, currentTime);
                    auditEventDao.addEvent(user.getId(), beFriendUserEvent1);
                    auditEventDao.addEvent(friend.getId(), beFriendUserEvent2);
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
    public User updateUser(@PartialCacheKey String userId, User user) {
        return neo4jTemplate.save(user);
    }

    @Override
    @Cacheable(cacheName = "userFollowers", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
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

            String targetEvent = objectMapper.writeValueAsString(address);

            Target target = new Target(IdType.USER_ID.toString(), targetEvent, null);
            Event addAddressToUserEvent = new Event(AuditEventType.ADDED_ADDRESS.toString(), target, currentTime, false);
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
    public void setReminder(ReminderRelationShip reminderRelationShip) {

        neo4jTemplate.save(reminderRelationShip);

    }

    @Override
    public List<User> findFriends(String userId, String currentUser) {
        List<User> friends = userRelationRepository.getFriends(userId);
        if (currentUser == null) {
            return friends;
        } else if (currentUser.equals(userId)) {
            for (User user : friends) {
                user.setUserRelation(UserRelationType.FRIEND.toString());
            }
            return friends;
        } else {
            List<User> userFriends = userRelationRepository.getFriends(currentUser);
            for (User user : userFriends) {
                user.setUserRelation(UserRelationType.FRIEND.toString());
            }
            return replaceBooksWithExistingFriends(friends, userFriends);
        }
    }

    private List<User> replaceBooksWithExistingFriends(List<User> friends, List<User> userExistingFriends) {
        Map<String, User> friendsMapWithId = new HashMap<>(friends.size());
        for (User user : friends) {
            friendsMapWithId.put(user.getId(), user);
        }
        for (User existingUser : userExistingFriends) {
            for (User user : friends) {
                if (user.getId().equals(existingUser.getId())) {
                    friendsMapWithId.put(user.getId(), existingUser);
                }
            }
        }
        List<User> resultUser = new ArrayList<>();
        for (User user : friendsMapWithId.values()) {
            resultUser.add(user);
        }
        return resultUser;
    }


    @Override
    public List<User> findMutualFriends(String currentUser, String userId) {
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
            // Notification friendRequestRecievedNotification = new
            // Notification(target, timeStamp)
        }
    }

    @Override
    public void deleteFriendRequest(String userId, String friendUserId) {
        //UserRelation userRelation = userRelationRepository.getUsersRelationship(userId, friendUserId);
        User user = getUser(userId);
        User friend = getUser(friendUserId);
        neo4jTemplate.deleteRelationshipBetween(user, friend, "CONNECTED");

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
