package com.campusconnect.neo4j.da.utils;

import com.campusconnect.neo4j.types.neo4j.AuditEvent;
import com.campusconnect.neo4j.types.neo4j.User;

import java.util.Set;

public class AuditEventHelper {

    public static AuditEvent createAuditEvent(User user, String serializedEvent) {
        AuditEvent auditEvent = new AuditEvent(user.getName(), user.getId());
        auditEvent.setImageUrl(user.getProfileImageUrl());
        Set<String> events = auditEvent.getEvents();
        events.add(serializedEvent);
        return auditEvent;
    }
}
