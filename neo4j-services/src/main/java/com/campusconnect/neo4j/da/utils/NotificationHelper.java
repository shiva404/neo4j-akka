package com.campusconnect.neo4j.da.utils;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.campusconnect.neo4j.types.common.Target;
import com.campusconnect.neo4j.types.web.Event;
import com.campusconnect.neo4j.types.web.Notification;

public class NotificationHelper {
	
private static Logger logger = LoggerFactory.getLogger(NotificationHelper.class);
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	
	
	public static String serializeNotification(Notification notification) {
		try {
			return mapper.writeValueAsString(notification);
		} catch (IOException e) {
			logger.error("Error while serializing eventObject:" + notification);
		}
		return null;
	}
	
	public static Notification deserializeNotificationString(String notificationString) {
		try {
			return mapper.readValue(notificationString, Notification.class);
		} catch (IOException e) {
			logger.error("Error while deSerializing eventObject:" + notificationString);
		}
		return null;
	}
}