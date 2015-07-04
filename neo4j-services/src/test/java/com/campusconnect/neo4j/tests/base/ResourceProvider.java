package com.campusconnect.neo4j.tests.base;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * Created by sn1 on 2/24/15.
 */
public class ResourceProvider {
    private WebResource resource;

    public ResourceProvider() {
        DefaultClientConfig cc = new DefaultClientConfig();
//       cc.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, readTimeout);
//       cc.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, connectTimeout);
        cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
//       cc.getClasses().add(JacksonJsonProvider.class);
        com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create(cc);
        client.addFilter(new LoggingFilter());
        resource = client.resource("http://localhost:8389/neo4j/v1");
    }

    public WebResource getResource() {
        return resource;
    }
}
