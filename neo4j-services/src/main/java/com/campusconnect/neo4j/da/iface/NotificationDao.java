package com.campusconnect.neo4j.da.iface;

import java.util.List;

import com.campusconnect.neo4j.types.AuditEvent;
import com.campusconnect.neo4j.types.Event;
import com.campusconnect.neo4j.types.Notification;
import com.campusconnect.neo4j.types.NotificationEntity;

public interface NotificationDao {

	NotificationEntity savenotification(NotificationEntity notificationEntity);
	NotificationEntity addNotification(String userId,Notification notification);
	NotificationEntity getFreshNotification(String userId);
	List<Notification> getNotifications(String userId, String filter);
	void moveNotification(String userId);
	
	
}
