package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.AuditEvent;
import com.campusconnect.neo4j.types.Reminder;

public interface AuditEventDao {
	
	void createEvent(AuditEvent auditEvent);
	AuditEvent getEvents(String userId);
	

}
