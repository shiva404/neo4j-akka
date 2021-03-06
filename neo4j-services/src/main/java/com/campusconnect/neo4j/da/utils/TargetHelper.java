package com.campusconnect.neo4j.da.utils;

import com.campusconnect.neo4j.types.common.IdType;
import com.campusconnect.neo4j.types.common.Target;
import com.campusconnect.neo4j.types.neo4j.*;
import com.campusconnect.neo4j.types.web.BorrowRequest;

public class TargetHelper {

    public static Target createUserTarget(User user) {
        String targetEventUserId = user.getId();
        String targetEventUserName = user.getName();
        String targetEventUrl = "/users/" + targetEventUserId;
        String imageUrl = user.getProfileImageUrl();
        return new Target(IdType.USER_ID.toString(), targetEventUserName,
                targetEventUrl, user.getId(),imageUrl);
    }

    public static Target createReminderTarget(Reminder reminder, User createdBy, User createdFor) {
        String targetReminderString = createdBy.getName();
        String targetReminderUrl = "/users/" + createdFor.getId() + "/reminders/" + reminder.getNodeId();
        return new Target(IdType.REMINDER_ID.toString(), targetReminderString, targetReminderUrl, reminder.getNodeId().toString(),null);
    }
    
    public static Target createDeleteGroupTarget(String groupName)
    {
    	
    		Target target = new Target(IdType.Group_NAME.toString(), null, groupName,null,null);
    		return target;
    }
    
    public static Target createGroupTarget(Group group)
    {
    	String targetUrl = "/groups/"+group.getId();
    	String imageUrl =  group.getImageUrl();
    	Target target = new Target(IdType.GROUP_ID.toString(),group.getName(),targetUrl,group.getId(),imageUrl);
    	return target;
    }



	public static Target createBookTarget(Book book) {
		
		String targetUrl = "/books/"+book.getId();
		String imageUrl = book.getImageUrl();
	
		return new Target(IdType.BOOK_ID.toString(), book.getName(), targetUrl, book.getId(),imageUrl);
	}
}
