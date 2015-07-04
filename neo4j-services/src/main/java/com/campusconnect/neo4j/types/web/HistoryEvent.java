package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.common.Target;

public class HistoryEvent extends Event{
	

	public HistoryEvent() {
	super();
	}

	public HistoryEvent(String auditEventType, Target target, Long timeStamp,
			boolean isPublic) {
		super(auditEventType, target, timeStamp, isPublic);
	}
}
