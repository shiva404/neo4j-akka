package com.campusconnect.neo4j.types;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Reminder {
	
	@CreatedDate
    private long createdDate;
	@LastModifiedDate
    private long lastModifiedTime;
	@GraphId
    private Long nodeId;
	private String reminderMessage;
	private Long reminderTime;
	
	public Reminder()
	{}
	
	public Reminder(long createdDate, long lastModifiedTime,
			String reminderMessage, Long reminderTime) {
		super();
		this.createdDate = createdDate;
		this.lastModifiedTime = lastModifiedTime;
		this.reminderMessage = reminderMessage;
		this.reminderTime = reminderTime;
	}
	public long getCreatedDate() {
		return createdDate;
	}
	public long getLastModifiedTime() {
		return lastModifiedTime;
	}
	public Long getNodeId() {
		return nodeId;
	}
	public String getReminderMessage() {
		return reminderMessage;
	}
	public Long getReminderTime() {
		return reminderTime;
	}
	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}
    public void setLastModifiedTime(long lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

    public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

    public void setReminderMessage(String reminderMessage) {
		this.reminderMessage = reminderMessage;
	}
    public void setReminderTime(Long reminderTime) {
		this.reminderTime = reminderTime;
	}
    

}
