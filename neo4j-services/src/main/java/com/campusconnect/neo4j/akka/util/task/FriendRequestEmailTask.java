package com.campusconnect.neo4j.akka.util.task;

import com.campusconnect.neo4j.types.neo4j.User;

public class FriendRequestEmailTask {


    private User user;
    private User friend;

    public FriendRequestEmailTask(User user, User friend) {
        super();
        this.user = user;
        this.friend = friend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }


}
