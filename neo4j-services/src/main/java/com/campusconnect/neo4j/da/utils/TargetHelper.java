package com.campusconnect.neo4j.da.utils;

import com.campusconnect.neo4j.types.common.IdType;
import com.campusconnect.neo4j.types.common.Target;
import com.campusconnect.neo4j.types.neo4j.User;

public class TargetHelper {
	
	 public static Target createTargetToUser(User user) {
	        String targetEventUserId = user.getId();
	        String targetEventUserName = user.getName();
	        String targetEventUrl = "users/" + targetEventUserId;
	        return new Target(IdType.USER_ID.toString(), targetEventUserName,
	                targetEventUrl);
	    }
	 
	
}
