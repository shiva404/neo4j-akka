package com.campusconnect.neo4j.types;

import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelationshipEntity;

/**
 * Created by sn1 on 3/25/15.
 */
@RelationshipEntity(type = "GR_REC")
public class GoodreadsFriendBookRecRelation extends BookRelation {

    public GoodreadsFriendBookRecRelation() {
    }

    public GoodreadsFriendBookRecRelation(User user, Book book, String status, String friendGoodreadsId, String friendImageUrl, String friendName) {

        super(user, book, status);
        this.friendGoodreadsId = friendGoodreadsId;
        this.friendImageUrl = friendImageUrl;
        this.friendName = friendName;
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

    private String friendGoodreadsId;
    private String friendImageUrl;
    private String friendName;
    
    
}
