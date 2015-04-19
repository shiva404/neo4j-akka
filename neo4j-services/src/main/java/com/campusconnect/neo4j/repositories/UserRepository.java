package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.Reminder;
import com.campusconnect.neo4j.types.User;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by sn1 on 1/19/15.
 */
public interface UserRepository extends GraphRepository<User> {
    @Query(value = "match (user:User {id:{0}})-[:FOLLOWING]->(followers) return following")
    public List<User> getFollowing(String userId);

    @Query(value = "match (user:User {id:{0}})<-[:FOLLOWING]-(followers:User) return followers")
    public List<User> getFollowers(String userId);
    
   
}
