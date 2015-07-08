package com.campusconnect.neo4j.exceptions;

public class InvalidDataException extends Neo4jException {

    public InvalidDataException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
