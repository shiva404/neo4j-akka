package com.campusconnect.neo4j.types;

/**
 * Created by sn1 on 3/29/15.
 */
public class UserRecommendation {
    public UserRecommendation() {
    }

    private Book book;
    private String friendGoodreadsId;
    private String friendImageUrl;
    private String friendName;
    private String userId;
    private long createDate;
    private String friendId;

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setFriendGoodreadsId(String friendGoodreadsId) {
        this.friendGoodreadsId = friendGoodreadsId;
    }

    public void setFriendImageUrl(String friendImageUrl) {
        this.friendImageUrl = friendImageUrl;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public Book getBook() {

        return book;
    }

    public String getFriendGoodreadsId() {
        return friendGoodreadsId;
    }

    public String getFriendImageUrl() {
        return friendImageUrl;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getUserId() {
        return userId;
    }

    public long getCreateDate() {
        return createDate;
    }

    public UserRecommendation(Book book, GoodreadsFriendBookRecRelation goodreadsFriendsRec) {
        this.book = book;
        this.friendGoodreadsId = goodreadsFriendsRec.getFriendGoodreadsId();
        this.friendImageUrl = goodreadsFriendsRec.getFriendImageUrl();
        this.friendName = goodreadsFriendsRec.getFriendName();
        this.userId = goodreadsFriendsRec.getFriendName();
        this.createDate = goodreadsFriendsRec.getCreatedDate();
        this.friendId = goodreadsFriendsRec.getFriendId();
    }
}
