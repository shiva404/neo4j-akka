package com.campusconnect.neo4j.types.neo4j;


import org.neo4j.cypher.internal.compiler.v2_0.functions.Str;
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

    @CreatedDate

    private Long createdDate;

    @Indexed(unique = true)
    private String id;
    private String imageUrl;

    private String isPublic;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private Long lastModifiedTime;

    private String name;
    
    @GraphId
    private Long nodeId;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, Long createdDate) {

        this.name = name;
        this.createdDate = createdDate;
    }
    public Group(String name, Long createdDate, String isPublic) {

        this.name = name;
        this.createdDate = createdDate;
        this.isPublic = isPublic;
    }

    public Group(String name, String isPublic) {
        this.name = name;
        this.isPublic = isPublic;
    }

    public Group(String id, String name, Long createdDate, Long lastModifiedTime, String lastModifiedBy, String isPublic,String imageUrl) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.lastModifiedTime = lastModifiedTime;
        this.lastModifiedBy = lastModifiedBy;
        this.isPublic = isPublic;
        this.imageUrl = imageUrl;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

	public String getId() {
        return id;
    }

	public String getImageUrl() {
		return imageUrl;
	}

	public String getisPublic() { return  isPublic; }

	public String getIsPublic() {
		return isPublic;
	}

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public Long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public String getName() {
        return name;
    }

    public Long getNodeId() {

        return nodeId;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

    public void setisPublic(String isPublic) { this.isPublic = isPublic; }

    public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public void setLastModifiedTime(Long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String toString() {
        return "Group [nodeId=" + nodeId + ", id=" + id + ", name=" + name
                + ", createdDate=" + createdDate + ", lastModifiedTime="
                + lastModifiedTime + ", lastModifiedBy=" + lastModifiedBy + ", isPublic=" + isPublic + "]";
    }

}
