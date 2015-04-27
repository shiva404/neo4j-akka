package com.campusconnect.neo4j.types;

public class Event {
	
	private String auditEventType;
	private Target target;
	private  Long timeStamp;
	
	public Event()
	{
	}

	public Event(String auditEventType, Target target, Long timeStamp) {
		super();
		this.auditEventType = auditEventType;
		this.target = target;
		this.timeStamp = timeStamp;
	}

	public String getAuditEventType() {
		return auditEventType;
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
	public void setTarget(Target target) {
		this.target = target;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	

}
