package com.campusconnect.neo4j.da;

import java.io.IOException;
import java.util.*;

import com.campusconnect.neo4j.types.IdType;
import com.campusconnect.neo4j.types.Subject;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.campusconnect.neo4j.da.iface.AuditEventDao;
import com.campusconnect.neo4j.repositories.AuditEventRepository;
import com.campusconnect.neo4j.types.AuditEvent;
import com.campusconnect.neo4j.types.Event;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.Result;

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

        for (String eachEvent : event) {
            try {
                System.out.println("each event" + eachEvent);
                Event eventDeserialised = objectMapper.readValue(eachEvent, Event.class);
                 eventDeserialised.setSubject(new Subject(IdType.USER_ID.toString(), auditEvent.getUserName(), "/users/" + auditEvent.getUserId(), auditEvent.getImageUrl()));
                events.add(eventDeserialised);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return events;
    }

	@Override
	public AuditEvent addEvent(String userId, Event event) {
        try {
            String eventString = objectMapper.writeValueAsString(event);
            AuditEvent auditEvent = auditEventRepository.getAuditEventForUser(userId);
            Set<String> events = auditEvent.getEvents();
            events.add(eventString);
            return saveEvent(auditEvent);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<Event> getFeedEvents(String userId) throws IOException {
        List<AuditEvent> followersAuditEvents = auditEventRepository.getAuditEventsForFollowers(userId);
        List<AuditEvent> friendsAuditEvents = auditEventRepository.getAuditEventsForFriends(userId);

        List<AuditEvent> mergedEvents = new ArrayList<>(friendsAuditEvents);
        mergedEvents.addAll(followersAuditEvents);

        List<Event> events = new ArrayList<>();
        for (AuditEvent auditEvent : friendsAuditEvents) {
            for (String eventString : auditEvent.getEvents()) {
                Event event = objectMapper.readValue(eventString, Event.class);
                event.setSubject(new Subject(IdType.USER_ID.toString(), auditEvent.getUserName(), "/users/" + auditEvent.getUserId(), auditEvent.getImageUrl()));
                events.add(event);
            }
        }
        //TODO make sure following and friend relation
        return events;
    }
}
