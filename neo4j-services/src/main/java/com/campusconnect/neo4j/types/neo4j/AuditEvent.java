package com.campusconnect.neo4j.types.neo4j;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.Set;
import java.util.TreeSet;


@NodeEntity
public class AuditEvent {

    @GraphId
    private Long nodeId;
    private String userName;
    private String imageUrl;
    private String userId;
    private Set<String> events;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Set<String> getEvents() {

        if (null == events) {
            events = new TreeSet<String>();
        }
        return events;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

}
