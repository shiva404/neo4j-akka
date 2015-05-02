package com.campusconnect.neo4j.types;

import java.util.List;

public class NotificationPage {

	List<Notification> notifications;
	private int offset;

	private int size;
	public NotificationPage() {
		super();
	}

	public NotificationPage(int offset, int size,
			List<Notification> notifications) {
		this.offset = offset;
		this.size = size;
		this.notifications = notifications;
	}
	
	public List<Notification> getNotifications() {
		return notifications;
	}

	public int getOffset() {
		return offset;
	}

	public int getSize() {
		return size;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
