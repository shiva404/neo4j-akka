package com.campusconnect.neo4j.types.web;

/**
 * Created by sn1 on 3/29/15.
 */
public class UserRecommendation {
    private Book book;
    private String friendGoodreadsId;
    private String friendImageUrl;
    private String friendName;
    private Long createDate;
    private String friendId;

    public UserRecommendation() {
    }

    public UserRecommendation(Book book) {
        this.book = book;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public Book getBook() {

        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getFriendGoodreadsId() {
        return friendGoodreadsId;
    }

    public void setFriendGoodreadsId(String friendGoodreadsId) {
        this.friendGoodreadsId = friendGoodreadsId;
    }

    public String getFriendImageUrl() {
        return friendImageUrl;
    }

    public void setFriendImageUrl(String friendImageUrl) {
        this.friendImageUrl = friendImageUrl;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
}
