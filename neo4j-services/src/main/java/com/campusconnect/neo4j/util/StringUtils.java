package com.campusconnect.neo4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by sn1 on 3/10/15.
 */
public class StringUtils {
    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);
    public static String cleanEmptyTags(String data) {
        return data.replaceAll("<[A-Z0-9a-z_]*>\\s*</[A-Z0-9a-z_]*>|.*nil=\"true\".*|.*<!\\[CDATA\\[\\]\\]>.*|<[A-Z0-9a-z_ ]*/>|<br>|<br/>|<br />|<p>|</p>|<strong>|</strong>|<link>.*</link>|<link>|</link>|<link />", "");
    }

    public static boolean getBoolean(String value) {
        try {
           return Boolean.parseBoolean(value);
        } catch (Exception e){
            logger.warn("Error while parsing boolean, returning false");
        }
        return false;
    }

    public static Integer getIntegerValue(String value, Integer defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e){
            logger.warn("Error while parsing boolean, returning defaultValue");
        }
        return defaultValue;
    }

    public static Integer getIntegerValue(String value) throws NumberFormatException{
        try {
            return Integer.parseInt(value);
        } catch (Exception e){
            logger.warn("Error while parsing boolean, throwing NumberFormatException");
            throw new NumberFormatException(e.getMessage());
        }
    }

}
