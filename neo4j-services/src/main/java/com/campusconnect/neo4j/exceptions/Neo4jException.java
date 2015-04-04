package com.campusconnect.neo4j.exceptions;

public class Neo4jException extends RuntimeException{
	
		private String errorCode;
	    private String message;
	    private String details;
	    private String moreInfo;

	    public Neo4jException(String errorCode, String message) {
	        super(message);
	        this.errorCode = errorCode;
	        this.message = message;
	    }

	    public String getErrorCode() {
	        return errorCode;
	    }

	    public void setErrorCode(String errorCode) {
	        this.errorCode = errorCode;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public String getDetails() {
	        return details;
	    }

	    public void setDetails(String details) {
	        this.details = details;
	    }

	    public String getMoreInfo() {
	        return moreInfo;
	    }

	    public void setMoreInfo(String moreInfo) {
	        this.moreInfo = moreInfo;
	    }
	}


