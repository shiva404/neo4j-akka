package com.campusconnect.neo4j.types.web;

import java.util.List;

public class EventPage {

    private List<Event> events;
    private int offset;
    private int size;

    public EventPage() {
        // TODO Auto-generated constructor stub
    }

    public EventPage(int offset, int size, List<Event> events) {
        super();
        this.offset = offset;
        this.size = size;
        this.events = events;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
