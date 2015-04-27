package com.campusconnect.neo4j.types;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.io.Serializable;

/**
 * Created by sn1 on 2/23/15.
 */
@NodeEntity
public class Address implements Serializable {
    private String city;

    private String country;

    @CreatedDate
    private long createdDate;

    @GraphId
    private Long id;
    private String landmark;
    @LastModifiedDate
    private long lastModifiedTime;
    private String latitude;
    private String line1;
    private String line2;
    private String longitude;
    private String state;
    private String type; //HOME, WORK, OTHER
    private String zipCode;
    public Address() {

    }

    public Address(String type, String line1, String zipCode) {
        this.type = type;
        this.line1 = line1;
        this.zipCode = zipCode;
    }
    
    public Address(String latitude, String longitude, String type, long createdDate, long lastModifiedTime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.createdDate = createdDate;
        this.lastModifiedTime = lastModifiedTime;
    }

    public Address(String type, String line1, String line2, String landmark, String city, String state, String country, String zipCode) {
        this.type = type;
        this.line1 = line1;
        this.line2 = line2;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public long getCreatedDate() {
        return createdDate;
    }


    public Long getId() {
        return id;
    }

    public String getLandmark() {
        return landmark;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLine1() {
        return line1;
    }
    
    public String getLine2() {
        return line2;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", landmark='" + landmark + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}
