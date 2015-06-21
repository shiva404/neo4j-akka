package com.campusconnect.neo4j.akka.facebook.task;

import com.campusconnect.neo4j.types.neo4j.User;

/**
 * Created by sn1 on 4/2/15.
 */
public class UserProfileImageTask {
    private User user;
    private String accessToken;

    public UserProfileImageTask(User user, String accessToken) {

        this.user = user;
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
