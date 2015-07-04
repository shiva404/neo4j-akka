package com.campusconnect.neo4j.types.web;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 7/4/15
 * Time: 2:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReturnRequest {
    private String ownerUserId;
    private String borrowerUserId;
    private String additionalMessage;

    public ReturnRequest(String ownerUserId, String borrowerUserId, String additionalMessage) {
        this.ownerUserId = ownerUserId;
        this.borrowerUserId = borrowerUserId;
        this.additionalMessage = additionalMessage;
    }

    public ReturnRequest() {
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getBorrowerUserId() {
        return borrowerUserId;
    }

    public void setBorrowerUserId(String borrowerUserId) {
        this.borrowerUserId = borrowerUserId;
    }

    public String getAdditionalMessage() {
        return additionalMessage;
    }

    public void setAdditionalMessage(String additionalMessage) {
        this.additionalMessage = additionalMessage;
    }
}
