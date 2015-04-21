package com.campusconnect.neo4j.types;

public class Event {
	
	private String auditEventType;
	private Target target;
	
	public Event()
	{
	}
	
	public Event(String auditEventType, Target target) {
		super();
		this.auditEventType = auditEventType;
		this.target = target;
	}
	
	public String getAuditEventType() {
		return auditEventType;
	}
	public Target getTarget() {
		return target;
	}
	public void setAuditEventType(String auditEventType) {
		this.auditEventType = auditEventType;
	}
	public void setTarget(Target target) {
		this.target = target;
	}
	
	
	

}
