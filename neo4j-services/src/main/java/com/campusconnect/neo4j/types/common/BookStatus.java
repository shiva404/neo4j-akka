package com.campusconnect.neo4j.types.common;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/6/15
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public enum BookStatus {
    AVAILABLE,
    BORROW_INIT,
    BORROW_LOCK,
    BORROWED,
    LENT,
}
