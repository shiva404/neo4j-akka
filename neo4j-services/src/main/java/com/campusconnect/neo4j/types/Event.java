package com.campusconnect.neo4j.types;

import java.util.Comparator;

public class Event {

    private Subject subject;
    private String auditEventType;
    private Target target;
    private Long timeStamp;
    private boolean isPublic;

    public Event() {
    }

    public Event(String auditEventType, Target target, Long timeStamp, boolean isPublic) {
        super();
        this.auditEventType = auditEventType;
        this.target = target;
        this.timeStamp = timeStamp;
        this.isPublic = isPublic;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getAuditEventType() {
        return auditEventType;
    }

    public void setAuditEventType(String auditEventType) {
        this.auditEventType = auditEventType;
    }

    //Use only when returning
    @Deprecated
    public Subject getSubject() {
        return subject;
    }

    //use only when returning event
    @Deprecated
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
