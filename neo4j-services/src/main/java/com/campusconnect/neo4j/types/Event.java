package com.campusconnect.neo4j.types;

public class Event {

    private String auditEventType;
    
    private Subject subject;

    private Target target;
	private  Long timeStamp;
	private boolean isPublic;
	public Event(){
	}
	public Event(String auditEventType, Target target, Long timeStamp,boolean isPublic) {
		super();
		this.auditEventType = auditEventType;
		this.target = target;
		this.timeStamp = timeStamp;
		this.isPublic = isPublic;
	}

	public String getAuditEventType() {
		return auditEventType;
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
	
	public void setAuditEventType(String auditEventType) {
		this.auditEventType = auditEventType;
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
