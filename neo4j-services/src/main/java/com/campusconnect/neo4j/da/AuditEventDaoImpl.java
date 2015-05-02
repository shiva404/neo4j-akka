package com.campusconnect.neo4j.da;

import java.util.LinkedList;
import java.util.List;
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
	public List<Event> getEvents(String userId) {
		
		AuditEvent auditEvent = auditEventRepository.getAuditEventForUser(userId);
		Set<String> event = auditEvent.getEvents();
    	List<Event> events = new LinkedList<Event>();
    	
    	 for(String eachEvent:event)
    	 {
    		 try
    		 {
    			 System.out.println("each event" + eachEvent);
    		 Event eventDeserialised = objectMapper.readValue(eachEvent, Event.class);
    		 events.add(eventDeserialised);		 
    		 }
    		 catch(Exception e)
    		 {
    			 e.printStackTrace();
    		 }
    	 }
		return events;
	}

	@Override
	public AuditEvent addEvent(String userId, Event event) {
		try
		{
		String serializedEvent = objectMapper.writeValueAsString(event);
    	String eventString = serializedEvent;
    	AuditEvent auditEvent = auditEventRepository.getAuditEventForUser(userId);
    	Set<String> events = auditEvent.getEvents();
    	events.add(eventString);   	
    	return saveEvent(auditEvent);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
