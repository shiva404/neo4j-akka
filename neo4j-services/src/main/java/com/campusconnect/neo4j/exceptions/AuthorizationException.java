package com.campusconnect.neo4j.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 7/8/15
 * Time: 6:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuthorizationException extends Neo4jException {

    public AuthorizationException(String errorCode, String message) {
        super(errorCode, message);
    }
}
