package com.campusconnect.neo4j.types;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by sn1 on 1/17/15.
 */

@NodeEntity
public class User implements Serializable {

    @Fetch
    private Set<Address> addresses;

    @CreatedDate
    private long createdDate;

    @Indexed
    private String email;
    private Set<String> favorites;
    @Indexed()
    private String fbId;
    private String goodreadsAccessToken;
    private String goodreadsAccessTokenSecret;
    private String goodreadsAuthStatus;
    private String goodReadsSynchStatus;
    private long lastGoodreadsSychDate;
    @Indexed()
    private String goodreadsId;
    @Indexed()
    private String googleId;
    @Indexed(unique = true)
    private String id;
    @LastModifiedDate
    private long lastModifiedDate;
    private String name;
    @GraphId
    private Long nodeId;
    private String phone;
    private String gender;
    private String profileImageUrl;
    private String workDesignation;
    private String workLocation;

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

    public String getGoodReadsSynchStatus() {
        return goodReadsSynchStatus;
    }

    public void setGoodReadsSynchStatus(String goodReadsSynchStatus) {
        this.goodReadsSynchStatus = goodReadsSynchStatus;
    }

    public long getLastGoodreadsSychDate() {
        return lastGoodreadsSychDate;
    }

    public void setLastGoodreadsSychDate(long lastGoodreadsSychDate) {
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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
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

    public long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(long lastModifiedDate) {
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
