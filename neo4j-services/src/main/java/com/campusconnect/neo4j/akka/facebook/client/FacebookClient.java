package com.campusconnect.neo4j.akka.facebook.client;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by sn1 on 4/2/15.
 */
public class FacebookClient {
    private final String url;
    private Logger LOGGER = LoggerFactory.getLogger(FacebookClient.class);
    //    private final Integer connectTimeout;
//    private final Integer readTimeout;
//    private final String appKey;
    private WebResource resource;

    public FacebookClient(String url) {
        this.url = url;
//        this.connectTimeout = connectTimeout;
//        this.readTimeout = readTimeout;
//        this.appKey = appKey;
    }

    public static Map<String, String> getDefaultHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        //add headers
        return headers;
    }

    public void init() {
        DefaultClientConfig cc = new DefaultClientConfig();
//        cc.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, readTimeout);
//        cc.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, connectTimeout);
        cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
//        cc.getClasses().add(JacksonJsonProvider.class);
        com.sun.jersey.api.client.Client client = com.sun.jersey.api.client.Client.create(cc);
        client.addFilter(new LoggingFilter());
        resource = client.resource(url);
    }

    public FacebookWebResource path(String path) {
        if (resource == null) {
            init();
        }
        FacebookWebResource facebookWebResource = new FacebookWebResource();
        facebookWebResource.wrapped = resource.path(path);
        return facebookWebResource;
    }

    public <T> T getEntity(Class<T> clazz, ClientResponse cr, Integer expectedHttpStatus) {
        handleError(cr, expectedHttpStatus);
        return cr.getEntity(clazz);
    }

    public void handleError(ClientResponse cr, Integer expected) throws FacebookException {
        if (cr.getStatus() != expected) {
            //todo handel error
        }
    }

    public class FacebookWebResource {
        private WebResource wrapped;

        public FacebookWebResource path(String path) {
            wrapped = wrapped.path(path);
            return this;
        }

        public FacebookWebResource queryParam(String key, String value) {
            wrapped = wrapped.queryParam(key, value);
            return this;
        }

        public FacebookWebResource addAppKeyQueryParam() {
//            wrapped = wrapped.queryParam("key", appKey);
//            wrapped = wrapped.queryParam("v", "2");
            return this;
        }

        public WebResource.Builder header(Map<String, String> headers) {
            WebResource.Builder out = wrapped.getRequestBuilder();
            if (headers != null) {
                Set<String> keys = headers.keySet();
                for (String key : keys) {
                    out = out.header(key, headers.get(key));
                }
            }
            return out;
        }
    }
}
