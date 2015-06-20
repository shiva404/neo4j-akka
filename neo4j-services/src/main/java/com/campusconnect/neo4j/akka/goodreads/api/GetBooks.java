package com.campusconnect.neo4j.akka.goodreads.api;

import com.campusconnect.neo4j.akka.goodreads.client.GoodReadsClient;
import com.campusconnect.neo4j.akka.goodreads.types.GetBooksResponse;
import com.campusconnect.neo4j.akka.goodreads.util.ResponseUtils;
import com.campusconnect.neo4j.util.StringUtils;
import com.sun.jersey.api.client.ClientResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.campusconnect.neo4j.akka.goodreads.client.GoodReadsClient.getDefaultHeaders;

/**
 * Created by sn1 on 3/24/15.
 */
public class GetBooks {
    private static Logger logger = LoggerFactory.getLogger(GetBook.class);

    private GoodReadsClient goodReadsClient;

    public GetBooks(GoodReadsClient goodReadsClient) {
        this.goodReadsClient = goodReadsClient;
    }

    public GetBooksResponse getBooksForUser(String goodreadsId, Integer page) throws IOException {
        ClientResponse clientResponse = goodReadsClient.path("review/list").path(goodreadsId).addAppKeyQueryParam().addV2Param().queryParam("format", "xml").queryParam("page", String.valueOf(page)).header(getDefaultHeaders()).get(ClientResponse.class);
        if (clientResponse.getStatus() == 200) {
            String theString = IOUtils.toString(clientResponse.getEntityInputStream());
            return ResponseUtils.getEntity(StringUtils.cleanEmptyTags(theString), GetBooksResponse.class);
        }
        return null;
    }
}
                                                    