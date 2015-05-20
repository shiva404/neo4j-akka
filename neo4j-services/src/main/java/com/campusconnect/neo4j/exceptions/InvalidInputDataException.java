package com.campusconnect.neo4j.exceptions;

public class InvalidInputDataException extends Neo4jException {

    public InvalidInputDataException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }


}
