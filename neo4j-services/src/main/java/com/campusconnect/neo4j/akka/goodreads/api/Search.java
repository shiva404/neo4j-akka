package com.campusconnect.neo4j.akka.goodreads.api;

import com.campusconnect.neo4j.akka.goodreads.client.GoodReadsClient;
import static com.campusconnect.neo4j.akka.goodreads.client.GoodReadsClient.getDefaultHeaders;


import com.campusconnect.neo4j.akka.goodreads.types.SearchResponse;
import com.campusconnect.neo4j.util.StringUtils;
import com.sun.jersey.api.client.ClientResponse;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by sn1 on 3/9/15.
 */
public class Search {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Search.class);
    private GoodReadsClient goodReadsClient;

    public Search(GoodReadsClient goodReadsClient) {
        this.goodReadsClient = goodReadsClient;
    }
    
    public SearchResponse search(String searchString) throws IOException {
        ClientResponse clientResponse = goodReadsClient.path("search/index.xml").addAppKeyQueryParam().queryParam("q",searchString).header(getDefaultHeaders()).get(ClientResponse.class);
        XMLSerializer xmlSerializer = new XMLSerializer();
        String theString = IOUtils.toString(clientResponse.getEntityInputStream());
        JSON json = xmlSerializer.read(StringUtils.cleanEmptyTags(theString));
        LOGGER.debug("Json String: " + json);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SearchResponse searchResponse = objectMapper.readValue(json.toString(), SearchResponse.class);
        return searchResponse;
    }

    public static void main(String[] args) throws IOException {
        Search search = new Search(new GoodReadsClient("https://www.goodreads.com", "QLM3lL2nqXe4LujHQt12A"));
        search.search("Aravind Adiga");
    }
}