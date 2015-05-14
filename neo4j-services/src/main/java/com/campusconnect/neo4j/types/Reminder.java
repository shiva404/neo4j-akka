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
    private Long createdDate;
	@LastModifiedDate
    private Long lastModifiedTime;
	@GraphId
    private Long nodeId;
	private String subject;
	private String reminderMessage;
	private String description;
	private Long reminderTime;
	
	public Reminder()
	{}
	
	public Reminder(Long createdDate, Long lastModifiedTime,
			String subject,String reminderMessage,String description, Long reminderTime) {
		super();
		this.createdDate = createdDate;
		this.lastModifiedTime = lastModifiedTime;
		this.reminderMessage = reminderMessage;
		this.reminderTime = reminderTime;
		this.subject = subject;
		this.reminderMessage = reminderMessage;
		this.description = description;
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
