package com.campusconnect.neo4j.logging;

import org.apache.log4j.MDC;

public class LogUtils {

    public static void putLog4jMDC(String key, String value) {
        if (key != null && value != null)
            MDC.put(key, value);
    }

    public static Object getValueFromMDC(String key) {
        if (key != null)
            return MDC.get(key);
        return null;
    }

}
