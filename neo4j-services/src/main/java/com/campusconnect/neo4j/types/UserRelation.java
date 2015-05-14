package com.campusconnect.neo4j.types;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.annotation.*;

/**
 * Created by sn1 on 1/19/15.
 */
@RelationshipEntity(type = "CONNECTED")
public class UserRelation {
    @GraphId
    private Long id;
    @StartNode
    private User user1;
    @EndNode
    private User user2;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    @CreatedDate
    private long createdDate;

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }


    public UserRelation(User user1, User user2, long createdDate, String type) {
        this.type = type;
        this.user1 = user1;
        this.user2 = user2;
        this.createdDate = createdDate;
    }

    public UserRelation() {
    }
}
