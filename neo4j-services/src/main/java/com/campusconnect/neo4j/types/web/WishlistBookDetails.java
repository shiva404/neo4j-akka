package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.common.BookDetails;

import java.util.ArrayList;
import java.util.List;

public class WishlistBookDetails extends BookDetails {
    private List<User> users;

    public List<GroupMember> getGroupMembers() {
        if(groupMembers == null){
            groupMembers = new ArrayList<>();
        }
        return groupMembers;
    }


    private List<GroupMember> groupMembers;
    private List<GoodreadsUserRecommendation> goodreadsUserRecommendations;

    public List<User> getUsers() {
        if(users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public List<GoodreadsUserRecommendation> getGoodreadsUserRecommendations() {
        if(goodreadsUserRecommendations == null){
            goodreadsUserRecommendations = new ArrayList<>();
        }
        return goodreadsUserRecommendations;
    }


}
