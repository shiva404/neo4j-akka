package com.campusconnect.neo4j.repositories;

import java.util.List;

import com.campusconnect.neo4j.types.User;
import com.campusconnect.neo4j.types.UserRelation;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRelationRepository extends GraphRepository<UserRelation> {

    @Query("match (users:User{id:{0}})-[r:CONNECTED]-(fellowUser:User{id: {1}}) return r")
    public UserRelation getUsersRelationship(String userId, String fellowUserId);

    @Query("match(seeingUserprofile:User{id:{0})-[r:CONNECTED]-(friends:User) return friends")
	public List<User> getFriends(String userId);
    
    @Query("match (currentUser:User {id:{0}}) - [r1:CONNECTED] - (mutualFriend:User) - [r2:CONNECTED] - (user:User {id:{1}) return mutualFriend")
    public List<User> getMutualFriends(String currentUser,String userID);
    
    @Query("match (user:User {id:{0}})- [r:CONNECTED {type:\"FRIEND_PENDING_REQUEST\"}]")
    public List<User> getPendingFriendRequests(String userID);


}
