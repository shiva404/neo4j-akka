package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.UserRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface UserRelationRepository extends GraphRepository<UserRelation> {

    @Query("match (users:User{id:{0}})-[r:CONNECTED]-(fellowUser:User{id: {1}}) return r")
    public UserRelation getUsersRelationship(String userId, String fellowUserId);


}
