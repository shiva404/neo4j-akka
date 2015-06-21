package com.campusconnect.neo4j.da.utils;

import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.web.GroupMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterHelper {
    public static List<User> replaceUsersWithExistingFriends(List<User> friends, List<User> userExistingFriends) {
        Map<String, User> friendsMapWithId = new HashMap<>(friends.size());
        for (User user : friends) {
            friendsMapWithId.put(user.getId(), user);
        }
        for (User existingUser : userExistingFriends) {
            for (User user : friends) {
                if (user.getId().equals(existingUser.getId())) {
                    friendsMapWithId.put(user.getId(), existingUser);
                }
            }
        }
        List<User> resultUser = new ArrayList<>();
        for (User user : friendsMapWithId.values()) {
            resultUser.add(user);
        }
        return resultUser;
    }

    public static List<GroupMember> groupMembersMergeWithMembers(List<GroupMember> allUsers, List<GroupMember> members) {
        for(GroupMember user : allUsers) {
            for(GroupMember member : members) {
                if(user.getId().equals(member.getId())) {
                    user.setGroupId(member.getGroupId());
                    user.setMemberSince(member.getMemberSince());
                    user.setRole(member.getRole());
                }
            }
        }
        return allUsers;
    }

    public static List<GroupMember> groupMembersMergeWithFriends(List<GroupMember> allGroupUsers, List<User> friends) {
        for(GroupMember groupMember : allGroupUsers){
            for(User friend : friends){
                if(groupMember.getId().equals(friend.getId())){
                    groupMember.setUserRelation(friend.getUserRelation());
                }
            }
        }
        return allGroupUsers;
    }
}
