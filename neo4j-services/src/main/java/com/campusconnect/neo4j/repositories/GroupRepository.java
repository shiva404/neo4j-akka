package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.Group;
import com.campusconnect.neo4j.types.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by sn1 on 1/23/15.
 */
public interface GroupRepository extends GraphRepository<Group> {
    @Query("match (users:User)-[:USER_ACCESS]->(group:Group {id: {0}}) return users")
    public List<User> getUsers(String groupId);
}
