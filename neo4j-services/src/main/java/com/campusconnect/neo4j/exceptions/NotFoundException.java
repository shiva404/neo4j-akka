package com.campusconnect.neo4j.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 7/8/15
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotFoundException extends Neo4jException {
    public NotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }
}
