package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.neo4j.UserGroupRelationship;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;


public interface UserGroupRepository extends GraphRepository<UserGroupRelationship> {

    @Query("match (users:User{id:{0}})-[r:USER_ACCESS]-(member:Group{id: {1}}) return r")
    public UserGroupRelationship getUserGroupRelationShip(String userId, String groupId);
    
    @Query("match (users:User{id:{0}})-[r:USER_ACCESS{role:{2}}]-(member:Group{id: {1}}) delete r")
    public void deleteUserGroupRelationShip(String userId,String groupId,String role);
}
