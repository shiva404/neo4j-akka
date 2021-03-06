package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.common.Target;

public class Event {

    private String auditEventType;
    private boolean isPublic;

    private Subject subject;

    private String eventActionString;
    private Target target;
    private Long timeStamp;

    public Event() {
    }

    public Event(String auditEventType, Target target, Long timeStamp, boolean isPublic) {
        super();
        this.auditEventType = auditEventType;
        this.target = target;
        this.timeStamp = timeStamp;
        this.isPublic = isPublic;
    }

    public String getAuditEventType() {
        return auditEventType;
    }

    public String getEventActionString() {
        return eventActionString;
    }

    //Use only when returning
    @Deprecated
    public Subject getSubject() {
        return subject;
    }

    public Target getTarget() {
        return target;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setAuditEventType(String auditEventType) {
        this.auditEventType = auditEventType;
    }

    public void setEventActionString(String eventActionString) {
        this.eventActionString = eventActionString;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    //use only when returning event
    @Deprecated
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
