package com.campusconnect.neo4j.exceptions;

public class DuplicateDataException extends Neo4jException {

    public DuplicateDataException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }


}
