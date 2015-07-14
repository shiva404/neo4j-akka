package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.neo4j.Address;
import com.campusconnect.neo4j.types.neo4j.ReminderRelationShip;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.neo4j.UserRelation;
import com.campusconnect.neo4j.types.web.FriendRecommendation;

import java.util.List;

/**
 * Created by sn1 on 3/6/15.
 */
public interface UserDao {
    void addAddressToUser(Address address, User user);

    void createFollowingRelation(User user, User follower);

    void confirmFriendRelation(User user, User friend);

    User createUser(User user, String accessToken);

    List<User> getFollowers(String userId);

    List<User> getFollowing(String userId);

    User getUser(String userId);

    User getUserByFbId(String fbId);

    void synchWishListRec(String userId);

    User updateUser(String userId, User user,boolean addEvent);

    User getUserByGoodreadsId(String goodreadsId);

    User getUserByGoogleId(String googleId);

    User getUserByEmail(String email);

    List<User> searchFriends(String userId, String searchString);

    List<User> getFriends(String userId, String currentUser);

    List<User> getMutualFriends(String userId, String mutualFriends);

    void createFriendRelationWithPending(User user, User user2);

    void deleteFriendRequest(String userId, String friendUserId,String deleteType);

    List<User> getRandomUsers(int size, String userID);

    List<User> search(String searchString, String userId);

    List<User> getPendingFriendReq(String userId);

    List<UserRelation> getUsersRelationShip(User user, User fellowUser);

    List<User> getRelatedUsers(String loggedInUser);

    List<FriendRecommendation> getFriendsRecWithCount(String userId, String size);

    List<FriendRecommendation> getFriendsRecWithFriends(String userId, String size);

    void deleteUser(String userId);

	void setReminder(ReminderRelationShip reminderRelationShip,
			boolean sendNotificationAddEvent);
}
