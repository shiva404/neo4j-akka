package com.campusconnect.neo4j.types.neo4j;

import com.campusconnect.neo4j.types.common.Constants;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = Constants.NOTIFICATION_RELATION)
public class UserNotificationRelationship {

    @GraphId
    private Long id;

    @EndNode
    private NotificationEntity notifictionEntity;

    private String type;

    @StartNode
    private User user;

    public UserNotificationRelationship() {
        super();
    }

    public UserNotificationRelationship(User user, NotificationEntity notifictionEntity, String type) {
        super();
        this.notifictionEntity = notifictionEntity;
        this.user = user;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationEntity getNotifictionEntity() {
        return notifictionEntity;
    }

    public void setNotifictionEntity(NotificationEntity notifictionEntity) {
        this.notifictionEntity = notifictionEntity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
