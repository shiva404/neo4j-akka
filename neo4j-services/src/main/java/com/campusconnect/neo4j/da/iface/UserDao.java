package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.*;

import java.util.List;

/**
 * Created by sn1 on 3/6/15.
 */
public interface UserDao {
    void addAddressToUser(Address address, User user);

    void createFollowingRelation(User user, User follower);

    void confirmFriendRelation(User user, User friend);

    User createUser(User user, String accessToken);

    List<Book> getReadBooks(String userId);

    List<OwnedBook> getAvailableBooks(String userId);

    List<BorrowedBook> getBorrowedBooks(String userId);

    List<User> getFollowers(String userId);

    List<User> getFollowing(String userId);

    List<OwnedBook> getLentBooks(String userId);

    List<OwnedBook> getOwnedBooks(String userId);

    User getUser(String userId);

    User getUserByFbId(String fbId);

    List<UserRecommendation> getUserRecommendations(String userId);

    List<WishListBook> getWishListBooks(String userId);

    void synchWishListRec(String userId);

    User updateUser(String userId, User user);

    void setReminder(ReminderRelationShip reminderRelationShip);

    User getUserByGoodreadsId(String goodreadsId);

    User getUserByGoogleId(String googleId);

    User getUserByEmail(String email);

    List<User> searchFriends(String userId, String searchString);

    List<User> findFriends(String userId, String currentUser);

    List<User> findMutualFriends(String userId, String mutualFriends);

    void createFriendRelationWithPending(User user, User user2);

    void deleteFriendRequest(String userId, String friendUserId);


    List<User> getRandomUsers(int size, String userID);

    List<User> search(String searchString, String userId);

    List<User> findPendingFriendReq(String userId);

    List<UserRelation> getUsersRelationShip(User user, User fellowUser);
}
