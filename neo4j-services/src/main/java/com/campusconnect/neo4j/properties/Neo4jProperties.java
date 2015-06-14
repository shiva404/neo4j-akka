package com.campusconnect.neo4j.properties;

import java.util.Properties;

/**
 * Created by sn1 on 6/11/14.
 */
public class Neo4jProperties {

    private Neo4jServicePropertyPlaceholderConfigurer documentPropertyPlaceholderConfigurer;

    public Neo4jProperties(Neo4jServicePropertyPlaceholderConfigurer propertyPlaceholderConfigurer) {
        documentPropertyPlaceholderConfigurer = propertyPlaceholderConfigurer;
    }

    public String getProperty(String key) {
        Properties properties = documentPropertyPlaceholderConfigurer.getProperties();
        return properties.getProperty(key);
    }


}
