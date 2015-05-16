package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.repositories.GroupRepository;
import com.campusconnect.neo4j.types.Group;
import com.campusconnect.neo4j.types.User;
import com.campusconnect.neo4j.types.UserGroupRelationship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import java.util.List;
import java.util.UUID;

/**
 * Created by sn1 on 1/23/15.
 */
public class GroupDao {
    @Autowired
    private GroupRepository groupRepository;

    private Neo4jTemplate neo4jTemplate;

    public GroupDao(Neo4jTemplate neo4jTemplate) {
        this.neo4jTemplate = neo4jTemplate;
    }

    public Group createGroup(Group group){
//        group.setId(UUID.randomUUID().toString());
        return groupRepository.save(group);
    }

    public void deleteGroup(Group group) {
		groupRepository.delete(group);
	}

    public Group getGroup(String groupId){
    	
       return groupRepository.findBySchemaPropertyValue("id", groupId);
    }
    
    public List<Group> getGroups(String userId)
    {
    	return groupRepository.getGroups(userId);
    }
    
    public List<User> getUsers(String groupId){
       return groupRepository.getUsers(groupId);
    }
    
    public void addUser(UserGroupRelationship userGroupRelationship)
    {
    	neo4jTemplate.save(userGroupRelationship);
    }

	public Group updateGroup(String groupId,Group group)
    {
    	Group groupToBeUpdated = getGroup(groupId);
    	groupToBeUpdated.setName(group.getName());
    	
    	return groupRepository.save(groupToBeUpdated);
    }
	
	

	
}
