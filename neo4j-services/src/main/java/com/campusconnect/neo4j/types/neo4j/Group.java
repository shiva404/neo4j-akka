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

    private boolean isPublic;

    public Group(String id, String name, Long createdDate, Long lastModifiedTime, String lastModifiedBy, boolean isPublic) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.lastModifiedTime = lastModifiedTime;
        this.lastModifiedBy = lastModifiedBy;
        this.isPublic = isPublic;
    }

    @CreatedDate

    private Long createdDate;

    @LastModifiedDate
    private Long lastModifiedTime;

    @LastModifiedBy
    private String lastModifiedBy;

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

    public void setPublic(boolean isPublic) { this.isPublic = isPublic; }

    public boolean getPublic() { return  isPublic; }

    @Override
    public String toString() {
        return "Group [nodeId=" + nodeId + ", id=" + id + ", name=" + name
                + ", createdDate=" + createdDate + ", lastModifiedTime="
                + lastModifiedTime + ", lastModifiedBy=" + lastModifiedBy + ", isPublic=" + isPublic + "]";
    }

}
