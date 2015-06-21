package com.campusconnect.neo4j.types.web;

public class Subject {

    private String displayString;
    private String idType;
    private String url;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String imageUrl;

    public Subject() {

    }

    public Subject(String idType, String displayString, String url, String imageUrl) {
        super();
        this.idType = idType;
        this.displayString = displayString;
        this.url = url;
        this.imageUrl = imageUrl;
    }


    public String getDisplayString() {
        return displayString;
    }

    public String getIdType() {
        return idType;
    }

    public String getUrl() {
        return url;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
