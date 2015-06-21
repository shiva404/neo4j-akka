package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.da.mapper.DBMapper;
import com.campusconnect.neo4j.da.utils.FilterHelper;
import com.campusconnect.neo4j.repositories.GroupRepository;
import com.campusconnect.neo4j.repositories.UserGroupRepository;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.Group;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.neo4j.UserGroupRelationship;
import com.campusconnect.neo4j.types.web.GroupMember;
import com.campusconnect.neo4j.util.Constants;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.neo4j.rest.graphdb.entity.RestRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sn1 on 1/23/15.
 */
public class GroupDao {
    public static final String GET_GROUP_USERS = String.format("match (user:User)-[access_rel:%s]->(group:Group {id: {groupId}}) return access_rel, user", Constants.USER_GROUP_RELATION);
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

    public GroupDao() {
    }

    public Group createGroup(Group group) {
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

    public List<GroupMember> getMembers(String groupId, String loggedInUser) {
        List<GroupMember> allGroupUsers = getAllGroupUsers(groupId);
        if(allGroupUsers.size() > 0 && loggedInUser != null){
            List<User> friends = userDao.getRelatedUsers(loggedInUser);
            friends.add(userDao.getUser(loggedInUser));
            return FilterHelper.groupMembersMergeWithFriends(allGroupUsers, friends);
        }
        return allGroupUsers;
    }

    private List<GroupMember> getAllGroupUsers(String groupId) {
        Map<String, Object> params = new HashMap<>();
        params.put("groupId", groupId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query(GET_GROUP_USERS, params);
        return getGroupMembersFromResult(mapResult, groupId);
    }

    private List<GroupMember> getGroupMembersFromResult(Result<Map<String, Object>> mapResult, String groupId) {
        List<GroupMember> groupMembers = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode userNode = (RestNode) objectMap.get("user");
            RestRelationship rawRelationship = (RestRelationship) objectMap.get("access_rel");
            User user = DBMapper.getUserFromRestNode(userNode);
            GroupMember groupMember = DBMapper.getGroupMember(user, rawRelationship, groupId);
            groupMembers.add(groupMember);
        }
        return groupMembers;
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
