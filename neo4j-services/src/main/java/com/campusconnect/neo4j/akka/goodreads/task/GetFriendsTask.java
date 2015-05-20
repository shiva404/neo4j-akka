package com.campusconnect.neo4j.akka.goodreads.task;

/**
 * Created by sn1 on 3/22/15.
 */
public class GetFriendsTask extends GoodreadsTask {
    private int page;

    public GetFriendsTask(String accessToken, String accessSecret, String userId, String goodreadsId) {
        super(accessToken, accessSecret, userId, goodreadsId);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
