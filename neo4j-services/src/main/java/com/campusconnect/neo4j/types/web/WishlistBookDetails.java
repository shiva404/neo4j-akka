package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.common.BookDetails;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WishlistBookDetails extends BookDetails {
    private List<User> users;
    private List<GroupWithMembers> groupsWithMembers;
    private List<GoodreadsUserRecommendation> goodreadsUserRecommendations;

    public List<GroupWithMembers> getGroupsWithMembers() {
        if(groupsWithMembers == null) {
            groupsWithMembers = new LinkedList<>();
        }
        return groupsWithMembers;
    }

    public List<User> getUsers() {
        if(users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public List<GoodreadsUserRecommendation> getGoodreadsUserRecommendations() {
        if(goodreadsUserRecommendations == null){
            goodreadsUserRecommendations = new ArrayList<>();
        }
        return goodreadsUserRecommendations;
    }


}
