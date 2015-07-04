package com.campusconnect.neo4j.types.neo4j;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.Set;
import java.util.TreeSet;


@NodeEntity
public class AuditEvent {

    private Set<String> events;
    private String imageUrl;
    @GraphId
    private Long nodeId;
    private String userId;
    private String userName;

    public AuditEvent(String userName, String userId) {
        super();
        this.userId = userId;
        this.userName = userName;
    }

    public AuditEvent() {
    }

    public Set<String> getEvents() {

        if (null == events) {
            events = new TreeSet<String>();
        }
        return events;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
