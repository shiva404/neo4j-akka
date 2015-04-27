package com.campusconnect.neo4j.da;

import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.campusconnect.neo4j.da.iface.AuditEventDao;
import com.campusconnect.neo4j.repositories.AuditEventRepository;
import com.campusconnect.neo4j.types.AuditEvent;
import com.campusconnect.neo4j.types.Event;

public class AuditEventDaoImpl implements AuditEventDao {
	
	@Autowired
	AuditEventRepository auditEventRepository;
	ObjectMapper objectMapper = new ObjectMapper();
	

	@Override
	public AuditEvent saveEvent(AuditEvent auditEvent) {
		
		
		return auditEventRepository.save(auditEvent);
		
	}

	@Override
	public AuditEvent getEvents(String userId) {
		
		AuditEvent auditEvent = auditEventRepository.getAuditEventForUser(userId);
		return auditEvent;
	}

	@Override
	public AuditEvent addEvent(String userId, Event event) {
		try
		{
		String serializedEvent = objectMapper.writeValueAsString(event);
    	String eventString = serializedEvent;
    	AuditEvent auditEventOfUSer = getEvents(userId);
    	Set<String> events = auditEventOfUSer.getEvents();
    	events.add(eventString);   	
    	return saveEvent(auditEventOfUSer);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
