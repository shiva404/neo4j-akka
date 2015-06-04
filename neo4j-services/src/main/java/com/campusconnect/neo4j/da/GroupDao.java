package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.repositories.GroupRepository;
import com.campusconnect.neo4j.repositories.UserGroupRepository;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.Group;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.neo4j.UserGroupRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import java.util.List;

/**
 * Created by sn1 on 1/23/15.
 */
public class GroupDao {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserDao userDao;

    private Neo4jTemplate neo4jTemplate;

    public GroupDao(Neo4jTemplate neo4jTemplate) {
        this.neo4jTemplate = neo4jTemplate;
    }

    public Group createGroup(Group group) {
//        group.setId(UUID.randomUUID().toString());
        return groupRepository.save(group);
    }

    public void deleteGroup(Group group) {
        groupRepository.delete(group);
    }

    public Group getGroup(String groupId) {

        return groupRepository.findBySchemaPropertyValue("id", groupId);
    }

    public List<Group> getGroups(String userId) {
        return groupRepository.getGroups(userId);
    }

    public List<User> getUsers(String groupId) {
        return groupRepository.getUsers(groupId);
    }

    public void addUser(String groupId, String userId, String role, String createdBy) {

        Group group = getGroup(groupId);
        User user = userDao.getUser(userId);
        UserGroupRelationship existingUserGroupRelationship = userGroupRepository.getUserGroupRelationShip(userId, groupId);
        //TODO: null check for role of addUser
        if (null != existingUserGroupRelationship) {
            existingUserGroupRelationship.setRole(role);
            neo4jTemplate.save(existingUserGroupRelationship);
        } else {
            Long currentTime = System.currentTimeMillis();
            UserGroupRelationship userGroupRelationship = new UserGroupRelationship(
                    createdBy, currentTime, group, currentTime,
                    role, user);
            neo4jTemplate.save(userGroupRelationship);
        }
    }

    public Group updateGroup(String groupId, Group group) {
        Group groupToBeUpdated = getGroup(groupId);
        groupToBeUpdated.setName(group.getName());

        return groupRepository.save(groupToBeUpdated);
    }

    public List<Book> getavailableBooks(String groupId) {
        return groupRepository.getAvailableBooks(groupId);
    }

    public List<Book> getWishListBooks(String groupId) {
        return groupRepository.getWishListBooks(groupId);
    }


}
