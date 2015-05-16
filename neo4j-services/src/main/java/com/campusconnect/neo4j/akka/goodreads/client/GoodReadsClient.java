package com.campusconnect.neo4j.akka.goodreads.client;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by sn1 on 3/9/15.
 */
public class GoodReadsClient {

    private final String url;
    //    private final int connectTimeout;
//    private final int readTimeout;
    private final String appKey;
    private Logger LOGGER = LoggerFactory.getLogger(GoodReadsClient.class);
    private WebResource resource;

    public GoodReadsClient(String url, String appKey) {
        this.url = url;
//        this.connectTimeout = connectTimeout;
//        this.readTimeout = readTimeout;
        this.appKey = appKey;
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

    public GoodreadsWebResource path(String path) {
        if (resource == null) {
            init();
        }
        GoodreadsWebResource goodreadsWebResource = new GoodreadsWebResource();
        goodreadsWebResource.wrapped = resource.path(path);
        return goodreadsWebResource;
    }

    public <T> T getEntity(Class<T> clazz, ClientResponse cr, int expectedHttpStatus) {
        handleError(cr, expectedHttpStatus);
        return cr.getEntity(clazz);
    }

    public void handleError(ClientResponse cr, int expected) throws GoodreadsException {
        if (cr.getStatus() != expected) {
            //todo handel error
        }
    }

    public void initSSL() {
        try {
            SSLContext sc = SSLContext.getInstance("SSL");

            HostnameVerifier hv = new HostnameVerifier() {
                public boolean verify(String urlHostName, SSLSession session) {
                    return true;
                }
            };

            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            SSLSocketFactory sslSocketFactory = sc.getSocketFactory();
            HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
        } catch (Exception e) {
            // Do some handling
        }
    }

    private void initOAuthFilter(String consumerKey, String consumerSecret) {

    }

    public class GoodreadsWebResource {
        private WebResource wrapped;

        public GoodreadsWebResource path(String path) {
            wrapped = wrapped.path(path);
            return this;
        }

        public GoodreadsWebResource queryParam(String key, String value) {
            wrapped = wrapped.queryParam(key, value);
            return this;
        }

        public GoodreadsWebResource addAppKeyQueryParam() {
            wrapped = wrapped.queryParam("key", appKey);
            return this;
        }

        public GoodreadsWebResource addV2Param() {
            wrapped = wrapped.queryParam("v", "2");
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
