package com.campusconnect.neo4j.akka.goodreads.worker;

import akka.actor.UntypedActor;
import com.campusconnect.neo4j.akka.goodreads.GoodreadsAsynchHandler;
import com.campusconnect.neo4j.akka.goodreads.client.GoodreadsOauthClient;
import com.campusconnect.neo4j.akka.goodreads.task.GetFriendsTask;
import com.sun.jersey.api.uri.UriBuilderImpl;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.UriBuilder;

/**
 * Created by sn1 on 3/21/15.
 */
public class GetFriends extends UntypedActor {
    @Autowired
    private GoodreadsOauthClient goodreadsOauthClient;

    @Autowired
    private GoodreadsAsynchHandler goodreadsAsynchHandler;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof GetFriendsTask) {
            GetFriendsTask getFriendsTask = (GetFriendsTask) message;
            UriBuilder uriBuilder = new UriBuilderImpl();
            uriBuilder.path("https://www.goodreads.com");
            uriBuilder.path("friend/user");
            uriBuilder.path(getFriendsTask.getGoodreadsId());
            uriBuilder.queryParam("format", "xml");
            Token sAccessToken = new Token(getFriendsTask.getAccessToken(), getFriendsTask.getAccessSecret());
            OAuthRequest getBooksRequest = new OAuthRequest(Verb.GET, uriBuilder.build().toString());
            goodreadsOauthClient.getsService().signRequest(sAccessToken, getBooksRequest);
            Response response = getBooksRequest.send();
//            GetFriends getFriendsResponse = ResponseUtils.getEntity(response.getBody(), GetFriends.class);

        }
    }
}
