package com.campusconnect.neo4j.filters;

import com.campusconnect.neo4j.logging.PerfLogHolder;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import org.apache.log4j.MDC;

import javax.ws.rs.core.MultivaluedMap;

import static com.campusconnect.neo4j.logging.LoggingConstants.*;

public class Neo4jServiceRequestFilter implements ContainerRequestFilter {

    public static final String CONTENT_LENGTH_LOWERCASE = "content-length";
    public static final String CONTENT_LENGTH_CAMELCASE = "Content-Length";
    public static final String CONTENT_LENGTH_UPPERCASE = "CONTENT-LENGTH";
    public static final String HEALTH_FULL_URL = "health/full";

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        String path = request.getPath();
        if (!path.contains(HEALTH_FULL_URL)) {
            PerfLogHolder perfLogHolder = PerfLogHolder.getPerfLogHolder();


            perfLogHolder.start(path);

            removeRetainedHeaders();

            MDC.put(REQUEST_PATH, path);
            MDC.put(REQUEST_HTTP_METHOD, request.getMethod());

            StringBuilder stringBuilder = new StringBuilder("{ ");

            MultivaluedMap<String, String> queryParameters = request.getQueryParameters();

            for (String key : queryParameters.keySet())
                stringBuilder.append(key).append("=").append(queryParameters.getFirst(key));

            stringBuilder.append(" }");

            MDC.put(REQUEST_QUERY_PARAM, stringBuilder.toString());

            MultivaluedMap<String, String> headers = request.getRequestHeaders();
            for (String key : headers.keySet()) {
                MDC.put(key, headers.getFirst(key));
            }
            String contentLength = getContentLengthFromHeaders(request.getRequestHeaders());
            MDC.put(REQUEST_CONTENT_LENGTH, contentLength != null ? contentLength : EMPTY_STRING);
            MDC.put(REQUEST_CONTENT_TYPE, request.getMediaType() != null ? request.getMediaType().toString() : EMPTY_STRING);
        }
        return request;
    }

    private String getContentLengthFromHeaders(MultivaluedMap<String, String> requestHeaders) {
        String contentLength;
        contentLength = requestHeaders.getFirst(CONTENT_LENGTH_LOWERCASE);
        if (contentLength == null)
            contentLength = requestHeaders.getFirst(CONTENT_LENGTH_CAMELCASE);
        if (contentLength == null)
            contentLength = requestHeaders.getFirst(CONTENT_LENGTH_UPPERCASE);

        return contentLength;
    }

    private void removeRetainedHeaders() {
        MDC.remove(TID_HEADER);
    }
}
