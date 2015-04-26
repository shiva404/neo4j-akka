package com.campusconnect.neo4j.types;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type = "REMINDER_ABOUT")
public class ReminderRelationShip {
	
	@CreatedBy
	private String createdBy;

	@CreatedDate
	private long createdDate;

	@GraphId
	private Long id;

	private long lastModified;

	@EndNode
	private Reminder reminder;

	@StartNode
	private User reminderFor;

	private String type;
	
	public ReminderRelationShip() {
		
	}

	public ReminderRelationShip(String createdBy, long createdDate, User reminderFor,
			long lastModified, String type, Reminder reminder) {
		super();
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.reminderFor = reminderFor;
		this.lastModified = lastModified;
		this.type = type;
		this.reminder = reminder;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public Long getId() {
		return id;
	}

	public long getLastModified() {
		return lastModified;
	}

	public Reminder getReminder() {
		return reminder;
	}

	public User getReminderFor() {
		return reminderFor;
	}

	public String getType() {
		return type;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}

	public void setReminderFor(User reminderFor) {
		this.reminderFor = reminderFor;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	


}