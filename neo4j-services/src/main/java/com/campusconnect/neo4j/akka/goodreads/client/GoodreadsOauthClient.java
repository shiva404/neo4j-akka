package com.campusconnect.neo4j.akka.goodreads.client;

import com.campusconnect.neo4j.akka.goodreads.task.GetBooksTask;
import com.sun.jersey.api.uri.UriBuilderImpl;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by sn1 on 3/11/15.
 */
public class GoodreadsOauthClient {
    private static final String CALLBACK = "oauth://goodreads";

    private String sApiKey;
    private String sApiSecret;

    public String getsApiKey() {
        return sApiKey;
    }
    
    public String getsApiSecret() {
        return sApiSecret;
    }

    public OAuthService getsService() {
        return sService;
    }
    private OAuthService sService;

    public GoodreadsOauthClient(String appKey, String appSecret) {
        sApiKey = appKey;
        sApiSecret = appSecret;
    }

    public void init() {
        sService = new ServiceBuilder()
                .provider(GoodreadsApi.class)
                .apiKey(sApiKey)
                .apiSecret(sApiSecret)
                .callback(CALLBACK)
                .debug()
                .build();
    }
    
//    public  void getShelvesForUser(String goodreadsId) {
//        UriBuilder uriBuilder = new UriBuilderImpl();
//        uriBuilder.path("https://www.goodreads.com");
////        uriBuilder.path("user");
////        uriBuilder.path(goodreadsId);
//        uriBuilder.path("friend/user.xml");
//        uriBuilder.queryParam("format", "xml");
//        uriBuilder.queryParam("id", goodreadsId);
//        uriBuilder.queryParam("key", sApiKey);
//        OAuthRequest getShelvesRequest = new OAuthRequest(Verb.GET, uriBuilder.build().toString());
//        Token sAccessToken = new Token("KztlD3JKfZ1qJfeqQCEqfg", "nlcbU0zMdDUWs0QB032JfdUGOGWAJXCgy7GvEnoQ");
//        sService.signRequest(sAccessToken, getShelvesRequest);
//        Response response = getShelvesRequest.send();
//        System.out.println(response.getBody());
//    }
    
 
    public void getFriendsForUser(String userId, String accessToken, String secret) {
      
        UriBuilder uriBuilder = new UriBuilderImpl();
        uriBuilder.path("https://www.goodreads.com");
        uriBuilder.path("friend/user");
        uriBuilder.path(userId);
        uriBuilder.queryParam("format", "xml");
        Token sAccessToken = new Token(accessToken, secret);
        OAuthRequest getBooksRequest = new OAuthRequest(Verb.GET, uriBuilder.build().toString());
        getsService().signRequest(sAccessToken, getBooksRequest);
        Response response = getBooksRequest.send();
//        System.out.printf(response.getBody());
//
    }
    

    public static void main(String[] args) {
        GoodreadsOauthClient goodreadsOauthClient = new GoodreadsOauthClient("QLM3lL2nqXe4LujHQt12A", "Aegcm52QdTinBh6g5fZe81S5cVYdKk9P6IDVS38pDOw");
        goodreadsOauthClient.init();
        goodreadsOauthClient.getFriendsForUser("22748455", "KztlD3JKfZ1qJfeqQCEqfg", "nlcbU0zMdDUWs0QB032JfdUGOGWAJXCgy7GvEnoQ");
    }
}
