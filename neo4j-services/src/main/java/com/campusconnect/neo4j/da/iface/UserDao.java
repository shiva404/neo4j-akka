package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.*;

import java.util.List;

/**
 * Created by sn1 on 3/6/15.
 */
public interface UserDao {
    User createUser(User user, String accessToken);

    User getUser(String userId);

    User getUserByFbId(String fbId);

    void createFollowingRelation(User user1, User user2);

    User updateUser(String userId, User user);

    List<User> getFollowers(String userId);

    List<User> getFollowing(String userId);

    List<OwnedBook> getOwnedBooks(String userId);

    List<OwnedBook> getAvailableBooks(String userId);

    List<OwnedBook> getLentBooks(String userId);

    List<BorrowedBook> getBorrowedBooks(String userId);

    void addAddressToUser(Address address, User user);

    List<WishListBook> getWishListBooks(String userId);

    void synchWishListRec(String userId);

    List<UserRecommendation> getUserRecommendations(String userId);

}
