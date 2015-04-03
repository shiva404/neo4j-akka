package com.campusconnect.neo4j.akka.facebook.api;

import com.campusconnect.neo4j.akka.facebook.client.FacebookClient;
import com.campusconnect.neo4j.akka.facebook.types.ProfileImage;
import com.sun.jersey.api.client.ClientResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sn1 on 4/2/15.
 */
public class GetUserProfileImage {

    private FacebookClient facebookClient;

    public GetUserProfileImage(FacebookClient facebookClient) {
        this.facebookClient = facebookClient;
    }

    public static Map<String, String> getDefaultHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        //add headers
        headers.put("accept", "application/json");
        return headers;
    }

    public String getImageUrlForUser(String fbId, String accessToken) {
        ClientResponse clientResponse = facebookClient.path(fbId)
                .path("picture")
                .queryParam("access_token", accessToken)
                .queryParam("fields", "url")
                .queryParam("format", "json")
                .queryParam("height", "200")
                .queryParam("width", "200")
                .queryParam("redirect", "false")
                .queryParam("suppress_http_code", "1")
                .header(getDefaultHeaders())
                .get(ClientResponse.class);
        if (clientResponse.getStatus() == 200) {
            ProfileImage profileImage = clientResponse.getEntity(ProfileImage.class);
            return profileImage.getData().getUrl();
        }
        return null;
    }

}
