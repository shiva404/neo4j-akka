package com.campusconnect.neo4j.types.web;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/21/15
 * Time: 8:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class FriendRecommendation {
    private User user;
    private List<User> mutualFriends;
    private int size;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getMutualFriends() {
        if(mutualFriends == null) {
            mutualFriends = new ArrayList<>();
        }
        return mutualFriends;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public FriendRecommendation() {

    }

    public FriendRecommendation(User user, List<User> mutualFriends, int size) {
        this.user = user;
        this.mutualFriends = mutualFriends;
        this.size = size;
    }

}
