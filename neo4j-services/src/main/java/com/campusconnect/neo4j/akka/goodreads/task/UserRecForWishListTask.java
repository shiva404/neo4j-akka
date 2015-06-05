package com.campusconnect.neo4j.akka.goodreads.task;

import com.campusconnect.neo4j.akka.goodreads.types.User;
import com.campusconnect.neo4j.types.web.UserRecommendation;
import com.campusconnect.neo4j.types.web.WishListBook;

import java.util.List;

/**
 * Created by sn1 on 3/25/15.
 */
public class UserRecForWishListTask extends GoodreadsTask {
    List<UserRecommendation> userRecommendations;
    private User friend;
    private List<WishListBook> wishListBooks;
    private int page;

    public UserRecForWishListTask(String accessToken, String accessSecret, String userId, String goodreadsId, User friend, List<WishListBook> wishListBooks, int page, List<UserRecommendation> userRecommendations) {

        super(accessToken, accessSecret, userId, goodreadsId);
        this.friend = friend;
        this.wishListBooks = wishListBooks;
        this.page = page;
        this.userRecommendations = userRecommendations;
    }

    public List<UserRecommendation> getUserRecommendations() {
        return userRecommendations;
    }

    public int getPage() {
        return page;
    }

    public User getFriend() {
        return friend;
    }

    public List<WishListBook> getWishListBooks() {
        return wishListBooks;
    }
}
