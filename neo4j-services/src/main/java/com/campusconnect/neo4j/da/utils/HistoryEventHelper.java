package com.campusconnect.neo4j.da.utils;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.campusconnect.neo4j.types.common.Target;
import com.campusconnect.neo4j.types.web.Event;
import com.campusconnect.neo4j.types.web.HistoryEvent;

public class HistoryEventHelper {
	
	
private static Logger logger = LoggerFactory.getLogger(EventHelper.class);
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static HistoryEvent createPublicEvent(String eventType,Target target) {
		return createEvent(eventType, target, true);
	}
	
	public static Event createPrivateEvent(String eventType,Target target) {
		return createEvent(eventType, target, false);
	}
	
	private static HistoryEvent createEvent(String eventType,Target target, boolean isPublic) {
		HistoryEvent historyEvent = new HistoryEvent(eventType,target,System.currentTimeMillis(),isPublic);
		return historyEvent;
	}
	
	public static String serializeEvent(HistoryEvent historyEvent) {
		try {
			return mapper.writeValueAsString(historyEvent);
		} catch (IOException e) {
			logger.error("Error while serializing eventObject:" + historyEvent);
		}
		return null;
	}
	
	public static HistoryEvent deserializeEventString(String eventString) {
		try {
			return mapper.readValue(eventString, HistoryEvent.class);
		} catch (IOException e) {
			logger.error("Error while deSerializing eventObject:" + eventString);
		}
		return null;
	}

}
