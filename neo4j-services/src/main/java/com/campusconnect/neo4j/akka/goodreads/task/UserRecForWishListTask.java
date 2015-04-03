package com.campusconnect.neo4j.akka.goodreads.task;

import com.campusconnect.neo4j.akka.goodreads.types.User;
import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.UserRecommendation;
import com.campusconnect.neo4j.types.WishListBook;

import java.util.List;

/**
 * Created by sn1 on 3/25/15.
 */
public class UserRecForWishListTask extends GoodreadsTask {
    private User friend;
    private List<WishListBook> wishListBooks;
    List<UserRecommendation> userRecommendations;

    public List<UserRecommendation> getUserRecommendations() {
        return userRecommendations;
    }

    public int getPage() {
        return page;
    }

    private int page;

    public User getFriend() {
        return friend;
    }

    public List<WishListBook> getWishListBooks() {
        return wishListBooks;
    }

    public UserRecForWishListTask(String accessToken, String accessSecret, String userId, String goodreadsId, User friend, List<WishListBook> wishListBooks, int page, List<UserRecommendation> userRecommendations) {

        super(accessToken, accessSecret, userId, goodreadsId);
        this.friend = friend;
        this.wishListBooks = wishListBooks;
        this.page = page;
        this.userRecommendations = userRecommendations;
    }
}
