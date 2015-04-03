package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.repositories.GroupRepository;
import com.campusconnect.neo4j.types.Group;
import com.campusconnect.neo4j.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import java.util.List;
import java.util.UUID;

/**
 * Created by sn1 on 1/23/15.
 */
public class GroupDao {
    private Neo4jTemplate neo4jTemplate;

    public GroupDao(Neo4jTemplate neo4jTemplate) {
        this.neo4jTemplate = neo4jTemplate;
    }

//    @Autowired
//    private GroupRepository groupRepository;

    public Group createGroup(Group group){
//        group.setId(UUID.randomUUID().toString());
//        return groupRepository.save(group);
        return null;
    }

    public Group getGroup(String groupId){
//        return groupRepository.findBySchemaPropertyValue("id", groupId);
        return null;
    }

    public List<User> getUsers(String groupId){
//        return groupRepository.getUsers(groupId);
        return null;
    }
}
