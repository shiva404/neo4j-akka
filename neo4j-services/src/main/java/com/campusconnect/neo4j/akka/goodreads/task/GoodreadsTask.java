package com.campusconnect.neo4j.akka.goodreads.task;

/**
 * Created by sn1 on 3/22/15.
 */
public abstract class GoodreadsTask {
    private String accessToken;
    private String accessSecret;
    private String userId;
    private String goodreadsId;

    public String getAccessToken() {
        return accessToken;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public String getUserId() {
        return userId;
    }

    public String getGoodreadsId() {
        return goodreadsId;
    }

    protected GoodreadsTask(String accessToken, String accessSecret, String userId, String goodreadsId) {

        this.accessToken = accessToken;
        this.accessSecret = accessSecret;
        this.userId = userId;
        this.goodreadsId = goodreadsId;
    }
}
