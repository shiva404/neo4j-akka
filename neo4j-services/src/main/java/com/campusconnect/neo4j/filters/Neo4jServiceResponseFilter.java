package com.campusconnect.neo4j.filters;

import com.campusconnect.neo4j.logging.PerfLogHolder;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.HttpHeaders;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.campusconnect.neo4j.logging.LoggingConstants.*;


public class Neo4jServiceResponseFilter implements ContainerResponseFilter {

    private static final Logger logger = LoggerFactory.getLogger(Neo4jServiceResponseFilter.class);
    public static final String SERVICE_TIME = "Service-Time";

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        String path = request.getPath();
        if (!path.contains(Neo4jServiceRequestFilter.HEALTH_FULL_URL)) {
            PerfLogHolder perfLogHolder = PerfLogHolder.getPerfLogHolder();
            perfLogHolder.stop();
            MDC.put(RESPONSE_CONTENT_LENGTH, response.getHttpHeaders().getFirst(HttpHeaders.CONTENT_LENGTH) != null ? response.getHttpHeaders().getFirst(HttpHeaders.CONTENT_LENGTH) : EMPTY_STRING);
            MDC.put(RESPONSE_STATUS, response.getResponse().getStatus());
            MDC.put(RESPONSE_CONTENT_TYPE, response.getMediaType() != null ? response.getMediaType().toString() : EMPTY_STRING);
            long totalExecutionTime = perfLogHolder.getTotalExecutionTime();
            MDC.put(EXECUTION_TIME, totalExecutionTime);
            String perfLogString = String.format("%1$s freeMem=%2$dmb totalMem=%3$dmb", perfLogHolder.getSummaryString(),
                    (Runtime.getRuntime().freeMemory() / 1000000L), (Runtime.getRuntime().maxMemory() / 1000000L));
            logger.info(perfLogString);
            response.getHttpHeaders().add(SERVICE_TIME, totalExecutionTime);
            perfLogHolder.reset();
            clearMDCAndRetainRequired();
        }
        return response;
    }

    private void clearMDCAndRetainRequired() {

        Hashtable<String, Object> headers = MDC.getContext();
        if (headers != null) {
            Set<Map.Entry<String, Object>> entrySets = headers.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entrySets.iterator();

            while (iterator.hasNext()) {
                String key = iterator.next().getKey();
                if (!key.equals(TID_HEADER)) {
                    iterator.remove();
                }
            }
        }
    }
}