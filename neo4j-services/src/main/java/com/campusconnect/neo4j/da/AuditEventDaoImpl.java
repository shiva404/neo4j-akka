package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.da.iface.AuditEventDao;
import com.campusconnect.neo4j.da.utils.EventHelper;
import com.campusconnect.neo4j.repositories.AuditEventRepository;
import com.campusconnect.neo4j.types.common.AuditEventType;
import com.campusconnect.neo4j.types.common.IdType;
import com.campusconnect.neo4j.types.neo4j.AuditEvent;
import com.campusconnect.neo4j.types.web.Event;
import com.campusconnect.neo4j.types.web.Subject;
import com.campusconnect.neo4j.util.EventDisplayStrings;
import com.campusconnect.neo4j.util.comparator.TimeStampComparator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;

public class AuditEventDaoImpl implements AuditEventDao {

    @Autowired
    AuditEventRepository auditEventRepository;

    public AuditEventDaoImpl() {
    }


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
                Event eventDeserialised = EventHelper.deserializeEventString(eachEvent);
                if (eventDeserialised.isPublic()) {
                    setEventStrings(eventDeserialised);
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
   
            String eventString = EventHelper.serializeEvent(event);
            AuditEvent auditEvent = auditEventRepository.getAuditEventForUser(userId);
            Set<String> events = auditEvent.getEvents();
            events.add(eventString);
            return saveEvent(auditEvent);
       
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
                Event event = EventHelper.deserializeEventString(eventString);
                if (event.isPublic()) {
                    setEventStrings(event);

                    event.setSubject(new Subject(IdType.USER_ID.toString(), auditEvent.getUserName(), "/users/" + auditEvent.getUserId(), auditEvent.getImageUrl()));
                    events.add(event);
                }
            }
        }

        Collections.sort(events, new TimeStampComparator());
        return events;
    }

    private void setEventStrings(Event event) {
        if (event.getAuditEventType().equals(AuditEventType.FRIEND.toString())) {
            event.setEventActionString(EventDisplayStrings.FRIEND_EVENT_STRING);
        } else if (event.getAuditEventType().equals(AuditEventType.FOLLOWING.toString())) {
            event.setEventActionString(EventDisplayStrings.FOLLOWING_EVENT_STRING);
        } else if (event.getAuditEventType().equals(AuditEventType.ADDED_ADDRESS.toString())) {
            event.setEventActionString(EventDisplayStrings.ADDED_ADDRESS_EVENT_STRING);
        } else if (event.getAuditEventType().equals(AuditEventType.BORROWED.toString())) {
            event.setEventActionString(EventDisplayStrings.BORROWED_EVENT_STRING);
        } else if (event.getAuditEventType().equals(AuditEventType.USER_CREATED.toString())) {
            event.setEventActionString(EventDisplayStrings.CREATED_EVENT_STRING);
        } else if (event.getAuditEventType().equals(AuditEventType.FOLLOWED.toString())) {
            event.setEventActionString(EventDisplayStrings.FOLLOWED_EVENT_STRING);
        } else if (event.getAuditEventType().equals(AuditEventType.UPDATED_ADDRESS.toString())) {
            event.setEventActionString(EventDisplayStrings.UPDATED_ADDRESS_EVENT_STRING);
        } else if (event.getAuditEventType().equals(AuditEventType.USER_UPDATED.toString())) {
            event.setEventActionString(EventDisplayStrings.USER_UPDATED_EVENT_STRING);
        } else if (event.getAuditEventType().equals(AuditEventType.WISHLIST.toString())) {
            event.setEventActionString(EventDisplayStrings.WISHLIST_EVENT_STRING);
        }
    }

    @Override
    public void deleteAuditEventsOfUser(String userId) {
        AuditEvent auditEvent = auditEventRepository.getAuditEventForUser(userId);
        auditEventRepository.delete(auditEvent);
    }
}
