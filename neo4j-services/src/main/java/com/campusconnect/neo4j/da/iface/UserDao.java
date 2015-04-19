package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.*;

import java.util.List;

/**
 * Created by sn1 on 3/6/15.
 */
public interface UserDao {
    void addAddressToUser(Address address, User user);

    void createFollowingRelation(User user1, User user2);

    User createUser(User user, String accessToken);

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

}
