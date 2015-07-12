package com.campusconnect.neo4j.exceptions;

public class UnAvailableException extends Neo4jException{
	
	 public UnAvailableException(String errorCode, String message) {
	        super(errorCode, message);
	    }

}
