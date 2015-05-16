package com.campusconnect.neo4j.akka.facebook.task;

/**
 * Created by sn1 on 3/2/15.
 */
public class FriendsListTask {

    private String accessToken;

    public FriendsListTask() {
    }

    public FriendsListTask(String accessToken) {

        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
