package com.campusconnect.neo4j.util.comparator;

import com.campusconnect.neo4j.types.web.Event;

import java.util.Comparator;

public class TimeStampComparator implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        return (int) (o2.getTimeStamp() - o1.getTimeStamp());
    }

}
