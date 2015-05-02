package com.campusconnect.neo4j.types;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class NotificationEntity {

@GraphId
private Long nodeId;
	
	private Set<String> notifications; 

	public Long getNodeId() {
		return nodeId;
	}

	public Set<String> getNotifications() {
		
		if(null== notifications)
		{
			notifications = new TreeSet<String>();
		}
		return notifications;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

}
