package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.College;
import com.campusconnect.neo4j.types.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by sn1 on 1/22/15.
 */
public interface CollegeRepository extends GraphRepository<College> {

    @Query("match (users:User)-[:USER_ACCESS]->(college:College {id: {0}}) return users")
    public List<User> getUsers(String userId);
}
