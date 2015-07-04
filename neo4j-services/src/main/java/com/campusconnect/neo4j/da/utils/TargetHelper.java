package com.campusconnect.neo4j.da.utils;

import com.campusconnect.neo4j.types.common.IdType;
import com.campusconnect.neo4j.types.common.Target;
import com.campusconnect.neo4j.types.neo4j.Reminder;
import com.campusconnect.neo4j.types.neo4j.User;

public class TargetHelper {
	
	 public static Target createTargetToUser(User user) {
	        String targetEventUserId = user.getId();
	        String targetEventUserName = user.getName();
	        String targetEventUrl = "users/" + targetEventUserId;
	        return new Target(IdType.USER_ID.toString(), targetEventUserName,
	                targetEventUrl);
	    }
	 
	 public static Target createNotificationTarget(String targetIdType,User user)
	 {
		  String targetNotificationString = "Notification";
          String targetNotificationUrl = "/users/" + user.getId();
          Target targetNotification = new Target(IdType.USER_ID.toString(), targetNotificationString, targetNotificationUrl);
          return targetNotification;
	 }
	 
	 public static Target createReminderTarget(String targetIdType,Reminder reminder,String userId)
	 {
		  String targetReminderString = "Reminder";
          String targetReminderUrl = "/users/"+userId+"/reminders" + reminder.getNodeId();
          Target targetReminder = new Target(targetIdType, targetReminderString , targetReminderUrl);
          return targetReminder;
	 }
	 
	
}
