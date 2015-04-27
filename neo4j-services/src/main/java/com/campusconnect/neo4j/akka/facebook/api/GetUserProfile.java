package com.campusconnect.neo4j.akka.facebook.api;

import com.campusconnect.neo4j.akka.facebook.client.FacebookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by sn1 on 4/25/15.
 */
public class GetUserProfile {
    private FacebookClient facebookClient;

    private FacebookConnectionFactory facebookConnectionFactory;

    public GetUserProfile(FacebookClient facebookClient, FacebookConnectionFactory facebookConnectionFactory) {
        this.facebookClient = facebookClient;
        this.facebookConnectionFactory = facebookConnectionFactory;
    }

    public static Map<String, String> getDefaultHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        //add headers
        headers.put("accept", "application/json");
        return headers;
    }
    
    public void getUserProfile(String userId, String accessToken) {
        Connection<Facebook> facebook = facebookConnectionFactory.createConnection(new AccessGrant(accessToken));
        UserProfile userProfile = facebook.fetchUserProfile();
        System.out.println(userProfile.getEmail());
    }
}
