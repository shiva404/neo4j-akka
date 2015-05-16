package com.campusconnect.neo4j.exceptions;

public class DataDuplicateException extends Neo4jException {

    public DataDuplicateException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }


}
