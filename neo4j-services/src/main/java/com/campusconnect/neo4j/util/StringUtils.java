package com.campusconnect.neo4j.util;

/**
 * Created by sn1 on 3/10/15.
 */
public class StringUtils {
    public static String cleanEmptyTags(String data) {
        return data.replaceAll("<[A-Z0-9a-z_]*>\\s*</[A-Z0-9a-z_]*>|.*nil=\"true\".*|.*<!\\[CDATA\\[\\]\\]>.*|<[A-Z0-9a-z_ ]*/>|<br>|<br/>|<br />|<p>|</p>|<strong>|</strong>|<link>.*</link>|<link>|</link>|<link />", "");
    }
}
