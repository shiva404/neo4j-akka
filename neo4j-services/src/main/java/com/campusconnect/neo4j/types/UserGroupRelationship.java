package com.campusconnect.neo4j.types;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by sn1 on 1/22/15.
 */
@RelationshipEntity(type = "USER_ACCESS")
public class UserGroupRelationship {

	@CreatedBy
	private String createdBy;

	@CreatedDate
	private long createdDate;
	@EndNode
	private Group group;

	@GraphId
	private Long id;

	private long lastModified;

	private String role;
	
	@StartNode
	private User user;
	
	public UserGroupRelationship() {
	}

	public UserGroupRelationship(String createdBy, long createdDate,
			Group group, long lastModified, String role, User user) {
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

	public long getCreatedDate() {
		return createdDate;
	}

	public Group getGroup() {
		return group;
	}

	public long getLastModified() {
		return lastModified;
	}

	public String getRole() {
		return role;
	}

	public User getUser() {
		return user;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUser(User user) {
		this.user = user;
	}
}