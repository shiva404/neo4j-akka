package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.AuditEvent;
import com.campusconnect.neo4j.types.Event;
import com.campusconnect.neo4j.types.Reminder;

public interface AuditEventDao {
	
	AuditEvent saveEvent(AuditEvent auditEvent);
	AuditEvent getEvents(String userId);
	AuditEvent addEvent(String userId,Event event);
	

}
