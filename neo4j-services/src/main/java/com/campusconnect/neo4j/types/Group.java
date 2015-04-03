package com.campusconnect.neo4j.types;


import org.springframework.data.annotation.*;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Created by sn1 on 1/23/15.
 */
@NodeEntity
public class Group {

    @GraphId
    private Long nodeId;

    @Indexed(unique=true)
    private String id;
    private String name;

    @CreatedDate
    private long createdDate;

    @LastModifiedDate
    private long lastModifiedTime;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    private String collegeId;
    private String departmentId;

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, String createdBy, String collegeId, String departmentId, long createdDate) {
        this.name = name;
        this.createdBy = createdBy;
        this.collegeId = collegeId;
        this.departmentId = departmentId;
        this.createdDate = createdDate;
    }

    public Group() {
    }

    public Group(String name, String collegeId, String departmentId) {
        this.name = name;
        this.collegeId = collegeId;
        this.departmentId = departmentId;
    }

    public Group(String collegeId, String name) {
        this.collegeId = collegeId;
        this.name = name;
    }

    public Long getNodeId() {

        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
