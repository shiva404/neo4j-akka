package com.campusconnect.neo4j.da;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.campusconnect.neo4j.da.iface.NotificationDao;
import com.campusconnect.neo4j.repositories.AuditEventRepository;
import com.campusconnect.neo4j.repositories.NotificationRepository;
import com.campusconnect.neo4j.types.AuditEvent;
import com.campusconnect.neo4j.types.Notification;
import com.campusconnect.neo4j.types.NotificationEntity;
import com.campusconnect.neo4j.types.NotificationType;

public class NotificationDaoImpl implements NotificationDao {

	
	@Autowired
	NotificationRepository notificationRepository;
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public NotificationEntity savenotification(
			NotificationEntity notificationEntity) {
		return notificationRepository.save(notificationEntity);
	}

	@Override
	public List<Notification> getNotifications(String userId,String filter) {
		
		List<Notification> notifications = new ArrayList<Notification>(); 
		Set<String> notificationsSet = new HashSet<String>();
		if(filter.equalsIgnoreCase("all"))
		{
		List<NotificationEntity> notificationEntity = notificationRepository.getAllNotificationForUser(userId);    	
    	for(NotificationEntity eachNotificationEntity:notificationEntity)
    	{
    		notificationsSet.addAll(eachNotificationEntity.getNotifications());
    	}
    	 for(String eachNotification:notificationsSet)
    	 {
    		 try
    		 {
    			 System.out.println("each notification" + eachNotification);
    		 Notification notificationDeserialised = objectMapper.readValue(eachNotification, Notification.class);
    		 notifications.add(notificationDeserialised);		 
    		 }
    		 catch(Exception e)
    		 {
    			 e.printStackTrace();
    		 }
    	 }
    	 }
		else
		{
			NotificationEntity notificationEntity = notificationRepository.getFreshNotificationForUser(userId, NotificationType.FRESH.toString());
			notificationsSet.addAll(notificationEntity.getNotifications());
			
			for(String eachNotification:notificationsSet)
	    	 {
	    		 try
	    		 {
	    			 System.out.println("each notification" + eachNotification);
	    		 Notification notificationDeserialised = objectMapper.readValue(eachNotification, Notification.class);
	    		 notifications.add(notificationDeserialised);		 
	    		 }
	    		 catch(Exception e)
	    		 {
	    			 e.printStackTrace();
	    		 }
	    	 }
		}
		
		return notifications;
	}

	@Override
	public NotificationEntity addNotification(String userId,
			Notification notification) {
		try
		{
		String notificationEventSerialized = objectMapper.writeValueAsString(notification);
    	String notificationString = notificationEventSerialized;
    	NotificationEntity notificationEntity = getFreshNotification(userId);
    	Set<String> notifications = notificationEntity.getNotifications();
    	notifications.add(notificationString);   	
    	return savenotification(notificationEntity);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public NotificationEntity getFreshNotification(String userId) {
		NotificationEntity notificationEntity = notificationRepository.getFreshNotificationForUser(userId, NotificationType.FRESH.toString());
		return notificationEntity;
	}

	@Override
	public void moveNotification(String userId) {
		
		NotificationEntity freshNotificationEntity = notificationRepository.getFreshNotificationForUser(userId, NotificationType.FRESH.toString());
		NotificationEntity pastNotificationEntity = notificationRepository.getFreshNotificationForUser(userId, NotificationType.PAST.toString());
		
		pastNotificationEntity.getNotifications().addAll(freshNotificationEntity.getNotifications());
		
		savenotification(pastNotificationEntity);
	
		freshNotificationEntity.getNotifications().clear();
		savenotification(freshNotificationEntity);
		
	}
	
}
