package com.campusconnect.neo4j.akka.goodreads.task;

import com.campusconnect.neo4j.akka.goodreads.types.Friends;
import com.campusconnect.neo4j.types.neo4j.User;

public class AddFriendsFromGoodReadsTask {

    private Friends friends;
    private User user;

    public AddFriendsFromGoodReadsTask(User user, Friends friend) {
        super();
        this.user = user;
        this.friends = friend;
    }

    public Friends getFriends() {
        return friends;
    }

    public void setFriends(Friends friend) {
        this.friends = friend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
