package com.campusconnect.neo4j.akka.goodreads.task;

/**
 * Created by sn1 on 5/5/15.
 */
public class ReplaceGRIdWithUserIdForRecTask {
    private String userId;
    private Integer goodreadsId;
    private String imageUrl;

    public String getUserId() {
        return userId;
    }

    public Integer getGoodreadsId() {
        return goodreadsId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ReplaceGRIdWithUserIdForRecTask(String userId, Integer goodreadsId, String imageUrl) {
        this.userId = userId;
        this.goodreadsId = goodreadsId;
        this.imageUrl = imageUrl;
    }
}
