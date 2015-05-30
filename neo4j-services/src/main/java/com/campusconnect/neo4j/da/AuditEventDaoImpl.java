package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.da.iface.AuditEventDao;
import com.campusconnect.neo4j.repositories.AuditEventRepository;
import com.campusconnect.neo4j.types.AuditEvent;
import com.campusconnect.neo4j.types.Event;
import com.campusconnect.neo4j.types.IdType;
import com.campusconnect.neo4j.types.Subject;
import com.campusconnect.neo4j.util.comparator.TimeStampComparator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;

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
                if (eventDeserialised.isPublic()) {
                    eventDeserialised.setSubject(new Subject(IdType.USER_ID.toString(), auditEvent.getUserName(), "/users/" + auditEvent.getUserId(), auditEvent.getImageUrl()));
                    events.add(eventDeserialised);
                }
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

        Map<Long, AuditEvent> mergedEvents = new HashMap<>();

        for (AuditEvent auditEvent : followersAuditEvents) {
            mergedEvents.put(auditEvent.getNodeId(), auditEvent);
        }

        for (AuditEvent auditEvent : friendsAuditEvents) {
            mergedEvents.put(auditEvent.getNodeId(), auditEvent);
        }

        List<Event> events = new ArrayList<>();
        for (Long key : mergedEvents.keySet()) {
            AuditEvent auditEvent = mergedEvents.get(key);
            for (String eventString : auditEvent.getEvents()) {
                Event event = objectMapper.readValue(eventString, Event.class);
                if (event.isPublic()) {
                    event.setSubject(new Subject(IdType.USER_ID.toString(), auditEvent.getUserName(), "/users/" + auditEvent.getUserId(), auditEvent.getImageUrl()));
                    events.add(event);
                }
            }
        }

        Collections.sort(events, new TimeStampComparator());
        return events;
    }
}
