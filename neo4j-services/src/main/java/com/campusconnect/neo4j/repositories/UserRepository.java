package com.campusconnect.neo4j.repositories;

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

    @Query("MATCH (n:User) WHERE n.name =~ {0} RETURN n")
    public List<User> searchUsers(String searchString);

    @Query("MATCH (n:User) - [:CONNECTED {type:\"FRIEND\"}] - (me:User {id:{0}}) WHERE n.name =~ {1} RETURN n")
    List<User> searchFriends(String userId, String searchString);
}
