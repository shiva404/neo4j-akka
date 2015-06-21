package com.campusconnect.neo4j.properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;


public class Neo4jServicePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public Properties mergeProperties() throws IOException {
        properties = super.mergeProperties();
        return properties;
    }
}
