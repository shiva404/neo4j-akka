package com.campusconnect.neo4j.types.web;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/4/15
 * Time: 9:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private Set<Address> addresses;
    private Long createdDate;
    private String email;
    private Set<String> favorites;
    private String fbId;
    private String goodreadsAccessToken;
    private String goodreadsAccessTokenSecret;
    private String goodreadsAuthStatus;
    private String goodReadsSynchStatus;
    private Long lastGoodreadsSychDate;
    private String goodreadsId;
    private String googleId;
    private String id;
    private Long lastModifiedDate;
    private String name;
    private Long nodeId;
    private String phone;
    private String gender;
    private String profileImageUrl;
    private String workDesignation;
    private String workLocation;
    private String userRelation;

    public User() {

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    public String getUserRelation() {
        return userRelation;
    }

    public void setUserRelation(String userRelation) {
        this.userRelation = userRelation;
    }

    public String getGoodReadsSynchStatus() {
        return goodReadsSynchStatus;
    }

    public void setGoodReadsSynchStatus(String goodReadsSynchStatus) {
        this.goodReadsSynchStatus = goodReadsSynchStatus;
    }

    public Long getLastGoodreadsSychDate() {
        return lastGoodreadsSychDate;
    }

    public void setLastGoodreadsSychDate(Long lastGoodreadsSychDate) {
        this.lastGoodreadsSychDate = lastGoodreadsSychDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<String> favourites) {
        this.favorites = favourites;
    }


    public User(Set<Address> addresses, Long createdDate, String email, Set<String> favorites, String fbId, String goodreadsAccessToken, String goodreadsAccessTokenSecret, String goodreadsAuthStatus, String goodReadsSynchStatus, Long lastGoodreadsSychDate, String goodreadsId, String googleId, String id, Long lastModifiedDate, String name, String phone, String gender, String profileImageUrl, String workDesignation, String workLocation, String userRelation) {
        this.addresses = addresses;
        this.createdDate = createdDate;
        this.email = email;
        this.favorites = favorites;
        this.fbId = fbId;
        this.goodreadsAccessToken = goodreadsAccessToken;
        this.goodreadsAccessTokenSecret = goodreadsAccessTokenSecret;
        this.goodreadsAuthStatus = goodreadsAuthStatus;
        this.goodReadsSynchStatus = goodReadsSynchStatus;
        this.lastGoodreadsSychDate = lastGoodreadsSychDate;
        this.goodreadsId = goodreadsId;
        this.googleId = googleId;
        this.lastModifiedDate = lastModifiedDate;
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.gender = gender;
        this.profileImageUrl = profileImageUrl;
        this.workDesignation = workDesignation;
        this.workLocation = workLocation;
        this.userRelation = userRelation;
    }

    public String getFbId() {

        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getGoodreadsAccessToken() {
        return goodreadsAccessToken;
    }

    public void setGoodreadsAccessToken(String goodreadsAccessToken) {
        this.goodreadsAccessToken = goodreadsAccessToken;
    }

    public String getGoodreadsAccessTokenSecret() {
        return goodreadsAccessTokenSecret;
    }

    public void setGoodreadsAccessTokenSecret(String goodreadsAccessTokenSecret) {
        this.goodreadsAccessTokenSecret = goodreadsAccessTokenSecret;
    }

    public String getGoodreadsAuthStatus() {
        return goodreadsAuthStatus;
    }

    public void setGoodreadsAuthStatus(String goodreadsAuthStatus) {
        this.goodreadsAuthStatus = goodreadsAuthStatus;
    }

    public String getGoodreadsId() {
        return goodreadsId;
    }

    public void setGoodreadsId(String goodreadsId) {
        this.goodreadsId = goodreadsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getWorkDesignation() {
        return workDesignation;
    }

    public void setWorkDesignation(String workDesignation) {
        this.workDesignation = workDesignation;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    @Override
    public String toString() {
        return "User{" +
                "nodeId=" + nodeId +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", addresses=" + addresses +
                ", phone='" + phone + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
