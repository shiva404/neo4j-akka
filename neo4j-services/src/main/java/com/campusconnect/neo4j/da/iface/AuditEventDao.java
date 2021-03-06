package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.neo4j.AuditEvent;
import com.campusconnect.neo4j.types.web.Event;

import java.io.IOException;
import java.util.List;

public interface AuditEventDao {

    AuditEvent saveEvent(AuditEvent auditEvent);

    List<Event> getEvents(String userId);

    AuditEvent addEvent(String userId, Event event);


    List<Event> getFeedEvents(String userId) throws IOException;

    void deleteAuditEventsOfUser(String userId);
}
