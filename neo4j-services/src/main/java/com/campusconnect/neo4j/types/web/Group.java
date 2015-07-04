package com.campusconnect.neo4j.types.web;

public class Group {

    private String id;
    private String name;

    public Group(String id, String name, Long createdDate, Long lastModifiedTime, String lastModifiedBy, boolean isPublic) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.lastModifiedTime = lastModifiedTime;
        this.lastModifiedBy = lastModifiedBy;
        this.isPublic = isPublic;
    }

    private Long createdDate;

    private Long lastModifiedTime;

    private String lastModifiedBy;

    private boolean isPublic;

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, Long createdDate) {

        this.name = name;
        this.createdDate = createdDate;
    }

    public Group(String name, boolean isPublic) {
        this.name = name;
        this.isPublic = isPublic;
    }

    public Group(String name, Long createdDate, boolean isPublic) {

        this.name = name;
        this.createdDate = createdDate;
        this.isPublic = isPublic;
    }

    public Group() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public boolean getPublic() {
        return  isPublic;
    }

}
