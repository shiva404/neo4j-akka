package com.campusconnect.neo4j.types;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.neo4j.annotation.NodeEntity;


@NodeEntity
public class AuditEvent {

	private Set<String> events;

	public Set<String> getEvents() {
		
		if(null== events)
		{
			events = new TreeSet<String>();
		}
		return events;
	}

}
