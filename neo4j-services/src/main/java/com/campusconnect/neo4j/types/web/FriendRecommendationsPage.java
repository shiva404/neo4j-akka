package com.campusconnect.neo4j.types.web;

import java.util.List;

public class FriendRecommendationsPage {
    private List<FriendRecommendation> friendRecommendations;
    private Integer size;
    private Integer offset;


    public FriendRecommendationsPage(List<FriendRecommendation> friendRecommendations, int size, int offset) {
        this.friendRecommendations = friendRecommendations;
        this.size = size;
        this.offset = offset;
    }

    public FriendRecommendationsPage() {
    }

    public List<FriendRecommendation> getFriendRecommendations() {

        return friendRecommendations;
    }

    public void setFriendRecommendations(List<FriendRecommendation> friendRecommendations) {
        this.friendRecommendations = friendRecommendations;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
