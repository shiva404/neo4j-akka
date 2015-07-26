package com.campusconnect.neo4j.types.common;

public class Target {

    private String displayString;

    private String id;

    private String idType;

    private String url;
    
    private String imageUrl;
    
    public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Target() {

    }
    public Target(String idType, String displayString, String url, String id,String imageUrl) {
        super();
        this.id = id;
        this.idType = idType;
        this.displayString = displayString;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getDisplayString() {
        return displayString;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
