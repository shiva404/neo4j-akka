package com.campusconnect.neo4j.types;

import java.util.List;

public class EventPage {

	private int offset;

	private int size;

	List<Event> userEvent;
	
	public EventPage() {
		// TODO Auto-generated constructor stub
	}

	public EventPage(int offset, int size, List<Event> userEvent) {
		super();
		this.offset = offset;
		this.size = size;
		this.userEvent = userEvent;
	}

	public int getOffset() {
		return offset;
	}

	public int getSize() {
		return size;
	}

	public List<Event> getUserEvent() {
		return userEvent;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setUserEvent(List<Event> userEvent) {
		this.userEvent = userEvent;
	}
	
}
