package com.campusconnect.neo4j.util.comparator;

import java.util.Comparator;

import com.campusconnect.neo4j.types.Event;

public class TimeStampComparator implements Comparator<Event>{

	@Override
	public int compare(Event o1, Event o2) {
		return (int)(o2.getTimeStamp() - o1.getTimeStamp());
	}

}
