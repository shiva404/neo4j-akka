package com.campusconnect.neo4j.types.web;

import java.util.List;

public class GroupMembersPage {
    private List<GroupMember> groupMembers;
    private Integer size;
    private Integer offset;

    public GroupMembersPage(List<GroupMember> groupMembers, Integer size, Integer offset) {
        this.groupMembers = groupMembers;
        this.size = size;
        this.offset = offset;
    }

    public GroupMembersPage() {
    }

    public List<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
