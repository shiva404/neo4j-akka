package com.campusconnect.neo4j.types.web;

public class Group {

    private Long createdDate;
    private String id;

    private String imageUrl;

    private String isPublic;

	private String lastModifiedBy;

	private Long lastModifiedTime;

	private String name;

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

    public String getisPublic() {
        return  isPublic;
    }

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

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

    public void setisPublic(String isPublic) {
        this.isPublic = isPublic;
    }

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

}
