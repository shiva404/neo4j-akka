package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.common.BookDetails;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/6/15
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class OwnedBookDetails extends BookDetails {

    public Long getLentDate() {
        return lentDate;
    }

    public void setLentDate(Long lentDate) {
        this.lentDate = lentDate;
    }

    private Long lentDate;
    private String borrowerId;
    private Integer contractPeriodInDays;
    private String dueDate;
    private String goodreadsStatus;
    private String userComment;
    private String status;

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Integer getContractPeriodInDays() {
        return contractPeriodInDays;
    }

    public void setContractPeriodInDays(Integer contractPeriodInDays) {
        this.contractPeriodInDays = contractPeriodInDays;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getGoodreadsStatus() {
        return goodreadsStatus;
    }

    public void setGoodreadsStatus(String goodreadsStatus) {
        this.goodreadsStatus = goodreadsStatus;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OwnedBookDetails(String borrowerId, Integer contractPeriodInDays, String dueDate, String goodreadsStatus, String userComment, String status) {

        this.borrowerId = borrowerId;
        this.contractPeriodInDays = contractPeriodInDays;
        this.dueDate = dueDate;
        this.goodreadsStatus = goodreadsStatus;
        this.userComment = userComment;
        this.status = status;
    }

    public OwnedBookDetails() {
    }
}
