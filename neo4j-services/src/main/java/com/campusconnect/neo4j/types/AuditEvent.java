package com.campusconnect.neo4j.types;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;


@NodeEntity
public class AuditEvent {

    @GraphId
    private Long nodeId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;
   
	private Set<String> events;
    
	public Set<String> getEvents() {
		
		if(null== events)
		{
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
