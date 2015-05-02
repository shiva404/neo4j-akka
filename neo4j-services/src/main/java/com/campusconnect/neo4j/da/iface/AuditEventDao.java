package com.campusconnect.neo4j.da.iface;

import java.util.List;

import com.campusconnect.neo4j.types.AuditEvent;
import com.campusconnect.neo4j.types.Event;
import com.campusconnect.neo4j.types.Reminder;

public interface AuditEventDao {
	
	AuditEvent saveEvent(AuditEvent auditEvent);
	List<Event> getEvents(String userId);
	AuditEvent addEvent(String userId,Event event);
	

}
