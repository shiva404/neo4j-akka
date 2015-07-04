package com.campusconnect.neo4j.types.web;

import java.util.Set;

public class GroupMember extends User {
    public GroupMember(Set<Address> addresses, Long createdDate, String email, Set<String> favorites, String fbId,
                       String goodreadsAccessToken, String goodreadsAccessTokenSecret, String goodreadsAuthStatus,
                       String goodReadsSynchStatus, Long lastGoodreadsSychDate, String goodreadsId, String googleId,
                       String id, Long lastModifiedDate, String name, String phone, String gender,
                       String profileImageUrl, String workDesignation, String workLocation,
                       String userRelation, String groupId,String groupName,  String role, Long memberSince) {
        super(addresses, createdDate, email, favorites, fbId, goodreadsAccessToken, goodreadsAccessTokenSecret,
                goodreadsAuthStatus, goodReadsSynchStatus, lastGoodreadsSychDate, goodreadsId, googleId, id,
                lastModifiedDate, name, phone, gender, profileImageUrl, workDesignation, workLocation, userRelation);
        this.groupId = groupId;
        this.groupName = groupName;
        this.role = role;

        this.memberSince = memberSince;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public GroupMember() {
    }

    private String groupId;
    private String role;
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(Long memberSince) {
        this.memberSince = memberSince;
    }

    private Long memberSince;

}
