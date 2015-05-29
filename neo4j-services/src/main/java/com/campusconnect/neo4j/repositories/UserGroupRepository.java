package com.campusconnect.neo4j.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.campusconnect.neo4j.types.UserGroupRelationship;



public interface UserGroupRepository extends GraphRepository<UserGroupRelationship>{

	@Query("match (users:User{id:{0}})-[r:USER_ACCESS]-(member:Group{id: {1}}) return r")
    public UserGroupRelationship getUserGroupRelationShip(String userId, String groupId);
}
