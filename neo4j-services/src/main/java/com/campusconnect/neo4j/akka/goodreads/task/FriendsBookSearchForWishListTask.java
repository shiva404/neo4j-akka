package com.campusconnect.neo4j.akka.goodreads.task;


import com.campusconnect.neo4j.types.web.WishListBook;

import java.util.List;

/**
 * Created by sn1 on 3/22/15.
 */
public class FriendsBookSearchForWishListTask extends GoodreadsTask {
    private Integer page;
    private List<WishListBook> wishListBooks;

    public FriendsBookSearchForWishListTask(String accessToken, String accessSecret, String userId, String goodreadsId, Integer page, List<WishListBook> wishListBooks) {
        super(accessToken, accessSecret, userId, goodreadsId);
        this.page = page;
        this.wishListBooks = wishListBooks;
    }

    protected FriendsBookSearchForWishListTask(String accessToken, String accessSecret, String userId, String goodreadsId) {
        super(accessToken, accessSecret, userId, goodreadsId);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<WishListBook> getWishListBooks() {
        return wishListBooks;
    }

    public void setWishListBooks(List<WishListBook> wishListBooks) {
        this.wishListBooks = wishListBooks;
    }
}
