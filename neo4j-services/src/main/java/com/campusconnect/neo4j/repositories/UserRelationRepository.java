package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.neo4j.UserRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface UserRelationRepository extends GraphRepository<UserRelation> {

    @Query("match (users:User{id:{0}})-[r:CONNECTED]-(fellowUser:User{id:{1}}) return r")
    public List<UserRelation> getUsersRelationship(String userId, String fellowUserId);

    @Query("match (seeingUserprofile:User{id:{0}})-[r:CONNECTED {type:\"FRIEND\"}]-(friends:User) return friends")
    public List<User> getFriends(String userId);

    @Query("match (currentUser:User {id:{0}}) - [r1:CONNECTED] - (mutualFriend:User) - [r2:CONNECTED] - (user:User {id:{1}}) return mutualFriend")
    public List<User> getMutualFriends(String currentUser, String userID);

    @Query("match (user:User {id:{0}})<- [r:CONNECTED {type:\"FRIEND_REQUEST_PENDING\"}]-(fellowUser:User) return fellowUser")
    public List<User> getPendingFriendRequests(String userID);

    @Query("match (users:User{id:{0}})-[r:CONNECTED]-(fellowUser:User) return fellowUser")
    List<User> getRelatedUsers(String userId);
}
