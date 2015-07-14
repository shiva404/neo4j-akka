package com.campusconnect.neo4j.da.utils;

import com.campusconnect.neo4j.types.common.Target;
import com.campusconnect.neo4j.types.web.Event;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EventHelper {
    private static Logger logger = LoggerFactory.getLogger(EventHelper.class);

    private static ObjectMapper mapper = new ObjectMapper();


    public static Event createPublicEvent(String eventType, Target target) {
        return createEvent(eventType, target, true);
    }

    public static Event createPrivateEvent(String eventType, Target target) {
        return createEvent(eventType, target, false);
    }

    private static Event createEvent(String eventType, Target target, boolean isPublic) {
        Event event = new Event(eventType, target, System.currentTimeMillis(), isPublic);
        return event;
    }

    public static String serializeEvent(Event event) {
        try {
            return mapper.writeValueAsString(event);
        } catch (IOException e) {
            logger.error("Error while serializing eventObject:" + event);
        }
        return null;
    }

    public static Event deserializeEventString(String eventString) {
        try {
            return mapper.readValue(eventString, Event.class);
        } catch (IOException e) {
            logger.error("Error while deSerializing eventObject:" + eventString, e);
        }
        return null;
    }
}
