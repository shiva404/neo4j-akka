package com.campusconnect.neo4j.akka.goodreads.api;

import com.campusconnect.neo4j.akka.goodreads.client.GoodreadsOauthClient;
import com.sun.jersey.api.uri.UriBuilderImpl;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;

import javax.ws.rs.core.UriBuilder;

/**
 * Created by sn1 on 4/11/15.
 */

public class GetUser {
    private GoodreadsOauthClient goodreadsOauthClient;

    public GetUser(GoodreadsOauthClient goodreadsOauthClient) {
        this.goodreadsOauthClient = goodreadsOauthClient;
    }

    public void getUser(String goodreadsId, String accessToken, String secret) {
        UriBuilder uriBuilder = new UriBuilderImpl();
        uriBuilder.path("https://www.goodreads.com");
        uriBuilder.path("user/show");
        uriBuilder.path(goodreadsId + ".xml");
        
        Token sAccessToken = new Token(accessToken, secret);
        OAuthRequest getBooksRequest = new OAuthRequest(Verb.GET, uriBuilder.build().toString());
        goodreadsOauthClient.getsService().signRequest(sAccessToken, getBooksRequest);
        Response response = getBooksRequest.send();
        System.out.println(response.getBody());
    }
}
