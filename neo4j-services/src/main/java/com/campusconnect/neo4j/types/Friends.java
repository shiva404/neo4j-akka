package com.campusconnect.neo4j.types;

public class Friends {

    FriendsPage friends;
    FriendsPage mutualFriends;

    public Friends() {
    }

    public Friends(FriendsPage friendsPage, FriendsPage mutualFriends) {
        this.friends = friendsPage;
        this.mutualFriends = mutualFriends;
    }

    public FriendsPage getFriends() {
        return friends;
    }

    public void setFriends(FriendsPage friends) {
        this.friends = friends;
    }

    public FriendsPage getMutualFriends() {
        return mutualFriends;
    }

    public void setMutualFriends(FriendsPage mutualFriends) {
        this.mutualFriends = mutualFriends;
    }

}
