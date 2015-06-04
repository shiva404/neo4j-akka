package com.campusconnect.neo4j.types.neo4j;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
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

    @Indexed(unique = true)
    private String id;
    private String name;

    @CreatedDate
    private long createdDate;

    @LastModifiedDate
    private long lastModifiedTime;

    @LastModifiedBy
    private String lastModifiedBy;

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, long createdDate) {

        this.name = name;
        this.createdDate = createdDate;
    }

    public Group() {
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

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public String toString() {
        return "Group [nodeId=" + nodeId + ", id=" + id + ", name=" + name
                + ", createdDate=" + createdDate + ", lastModifiedTime="
                + lastModifiedTime + ", lastModifiedBy=" + lastModifiedBy + "]";
    }

}
