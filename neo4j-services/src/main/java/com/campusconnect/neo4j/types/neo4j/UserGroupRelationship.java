package com.campusconnect.neo4j.types.neo4j;

import com.campusconnect.neo4j.util.Constants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by sn1 on 1/22/15.
 */
@RelationshipEntity(type = Constants.USER_GROUP_RELATION)
public class UserGroupRelationship {

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Long createdDate;
    @EndNode
    private Group group;

    @GraphId
    private Long id;

    private Long lastModified;

    private String role;

    @StartNode
    private User user;

    public UserGroupRelationship() {
    }

    public UserGroupRelationship(String createdBy, Long createdDate,
                                 Group group, Long lastModified, String role, User user) {
        super();
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.group = group;
        this.lastModified = lastModified;
        this.role = role;
        this.user = user;
    }

    public UserGroupRelationship(String role, String createdBy) {
        this.role = role;
        this.createdBy = createdBy;
    }


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
