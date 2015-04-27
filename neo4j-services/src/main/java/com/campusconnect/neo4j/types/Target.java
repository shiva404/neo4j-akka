package com.campusconnect.neo4j.types;

public class Target {
	
	private String displayString;
	private String idType;
	private String url;
	
	public Target()
	{
		
	}
	
	public Target(String idType, String displayString, String url) {
		super();
		this.idType = idType;
		this.displayString = displayString;
		this.url = url;
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
