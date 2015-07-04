package com.campusconnect.neo4j.da.utils;

import java.util.Set;

import com.campusconnect.neo4j.da.iface.AuditEventDao;
import com.campusconnect.neo4j.types.neo4j.AuditEvent;
import com.campusconnect.neo4j.types.neo4j.User;

public class AuditEventHelper {
	
	
	
	
	public static AuditEvent createAuditEvent(User user,String serializedEvent)
	{
		AuditEvent auditEvent = new AuditEvent(user.getName(), user.getId());
		 Set<String> events = auditEvent.getEvents();
         events.add(serializedEvent);
         return auditEvent;
	}

}
