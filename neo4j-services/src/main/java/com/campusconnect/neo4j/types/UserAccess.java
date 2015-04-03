package com.campusconnect.neo4j.types;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.annotation.RelationshipEntity;

/**
 * Created by sn1 on 1/22/15.
 */
//@RelationshipEntity(type = "USER_ACCESS")
public class UserAccess {

    public UserAccess(String role, String createdBy) {
        this.role = role;
        this.createdBy = createdBy;
    }

    @CreatedDate
    private long createdlong;

    private String role;

    private String createdBy;

    public UserAccess() {
    }

    public long getCreatedlong() {
        return createdlong;
    }

    public void setCreatedlong(long createdlong) {
        this.createdlong = createdlong;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
