package com.campusconnect.neo4j.types.neo4j;

import com.campusconnect.neo4j.types.common.Constants;
import org.springframework.data.neo4j.annotation.RelationshipEntity;

/**
 * Created by sn1 on 3/25/15.
 */
@RelationshipEntity(type = Constants.GOODREADS_REC_RELATION)
public class GoodreadsFriendBookRecRelationship extends BookRelationship {

    private String friendGoodreadsId;
    private String friendImageUrl;
    private String friendName;
    private String friendId;


    public GoodreadsFriendBookRecRelationship() {
    }

    public GoodreadsFriendBookRecRelationship(User user, Book book, String status, String friendId, String friendGoodreadsId, String friendImageUrl, String friendName) {

        super(user, book, status);
        this.friendGoodreadsId = friendGoodreadsId;
        this.friendImageUrl = friendImageUrl;
        this.friendName = friendName;
        this.friendId = friendId;
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

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }
}
