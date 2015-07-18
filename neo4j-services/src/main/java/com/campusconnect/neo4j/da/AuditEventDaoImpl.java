package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.da.iface.AuditEventDao;
import com.campusconnect.neo4j.da.utils.EventHelper;
import com.campusconnect.neo4j.repositories.AuditEventRepository;
import com.campusconnect.neo4j.types.common.AuditEventType;
import com.campusconnect.neo4j.types.common.IdType;
import com.campusconnect.neo4j.types.neo4j.AuditEvent;
import com.campusconnect.neo4j.types.web.Event;
import com.campusconnect.neo4j.types.web.Subject;
import com.campusconnect.neo4j.util.Constants;
import com.campusconnect.neo4j.util.EventDisplayStrings;
import com.campusconnect.neo4j.util.comparator.TimeStampComparator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;

import static com.campusconnect.neo4j.types.common.AuditEventType.*;
import static com.campusconnect.neo4j.util.EventDisplayStrings.*;

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
//        List<AuditEvent> followersAuditEvents = auditEventRepository.getAuditEventsForFollowers(userId);
        List<AuditEvent> friendsAuditEvents = auditEventRepository.getAuditEventsForFriends(userId);
        Map<Long, AuditEvent> mergedEvents = new HashMap<>();

//        for (AuditEvent auditEvent : followersAuditEvents) {
//            mergedEvents.put(auditEvent.getNodeId(), auditEvent);
//        }

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
                    if(event.getTarget().getIdType().equals(IdType.BOOK_ID.toString())){
                        //TODO: attach a book
                    }
                    event.setSubject(new Subject(IdType.USER_ID.toString(), auditEvent.getUserName(), "/users/" + auditEvent.getUserId(), auditEvent.getImageUrl()));
                    events.add(event);
                }
            }
        }

        Collections.sort(events, new TimeStampComparator());
        return events;
    }

    private void setEventStrings(Event event) {
        switch (event.getAuditEventType()){
            case ADDED_ADDRESS:
                break;

            case BOOK_ADDED_CURRENTLY_READING:
                break;

            case FRIEND:
                event.setEventActionString(FRIEND_EVENT_STRING);
                break;
            case FOLLOWING:
                break;

            case BOOK_ADDED_OWNS:
                event.setEventActionString(OWNS_EVENT_STRING);
                break;
            case BOOK_ADDED_READ:
                event.setEventActionString(READ_EVENT_STRING);
                break;
            case BOOK_ADDED_WISHLIST:
                event.setEventActionString(WISHLIST_EVENT_STRING);
                break;

            case DELETED_ADDRESS:
                break;

            case LENT:
                event.setEventActionString(LENT_EVENT_STRING);
                break;

            case REMINDER_SENT:
                break;

            case RETURN_INITIATED:
                break;

            case UPDATED_ADDRESS:
                break;

            case USER_CREATED:
                break;

            case USER_UPDATED:
                break;

            default:
        }
    }

    @Override
    public void deleteAuditEventsOfUser(String userId) {
        AuditEvent auditEvent = auditEventRepository.getAuditEventForUser(userId);
        auditEventRepository.delete(auditEvent);
    }
}
