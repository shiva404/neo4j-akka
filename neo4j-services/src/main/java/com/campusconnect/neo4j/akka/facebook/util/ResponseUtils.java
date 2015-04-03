package com.campusconnect.neo4j.akka.facebook.util;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by sn1 on 4/2/15.
 */
public class ResponseUtils {

    public static Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    public static <T> T getEntity(String json, Class<T> clazz) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            logger.error("Failed to get object JsonData:" + json, e);
        }
        return null;
    }
}
