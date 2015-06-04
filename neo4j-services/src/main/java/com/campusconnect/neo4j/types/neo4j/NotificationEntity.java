package com.campusconnect.neo4j.types.neo4j;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.Set;
import java.util.TreeSet;

@NodeEntity
public class NotificationEntity {

    @GraphId
    private Long nodeId;

    private Set<String> notifications;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Set<String> getNotifications() {

        if (null == notifications) {
            notifications = new TreeSet<String>();
        }
        return notifications;
    }

}
