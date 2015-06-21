package com.campusconnect.neo4j.types.web;

public class Address {
    private String city;

    private String country;

    private Long createdDate;

    private Long id;
    private String landmark;
    private Long lastModifiedTime;
    private String latitude;
    private String line1;
    private String line2;
    private String longitude;
    private String state;
    private String type; //HOME, WORK, OTHER
    private String zipCode;

    public Address() {

    }

    public Address(String city, String country, Long createdDate, Long id, String landmark, Long lastModifiedTime, String latitude, String line1, String line2, String longitude, String state, String type, String zipCode) {
        this.city = city;
        this.country = country;
        this.createdDate = createdDate;
        this.id = id;
        this.landmark = landmark;
        this.lastModifiedTime = lastModifiedTime;
        this.latitude = latitude;
        this.line1 = line1;
        this.line2 = line2;
        this.longitude = longitude;
        this.state = state;
        this.type = type;
        this.zipCode = zipCode;
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

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZipCode() {
        return zipCode;
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