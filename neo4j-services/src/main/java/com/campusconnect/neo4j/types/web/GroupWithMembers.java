package com.campusconnect.neo4j.types.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sn1 on 7/12/15.
 */
public class GroupWithMembers {
    private List<GroupMember> groupMembers;
    private Group group;

    public List<GroupMember> getGroupMembers() {
        if(groupMembers == null)
            groupMembers = new LinkedList<>();
        return groupMembers;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public GroupWithMembers() {

    }

    public GroupWithMembers(List<GroupMember> groupMembers, Group group) {

        this.groupMembers = groupMembers;
        this.group = group;
    }
}
