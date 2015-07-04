package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.common.BookDetails;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/6/15
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class BorrowedBookDetails extends BookDetails {
    private Long dueDate;
    private String ownerUserId;
    private String additionalComments;
    private Long borrowDate;
    private Integer contractPeriodInDays;
    private String status;

    public Long getDueDate() {
        return dueDate;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }

    public void setAdditionalComments(String additionalComments) {
        this.additionalComments = additionalComments;
    }

    public Long getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Long borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Integer getContractPeriodInDays() {
        return contractPeriodInDays;
    }

    public void setContractPeriodInDays(Integer contractPeriodInDays) {
        this.contractPeriodInDays = contractPeriodInDays;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BorrowedBookDetails() {

    }

    public BorrowedBookDetails(Long dueDate, String ownerUserId, String additionalComments, Long borrowDate, Integer contractPeriodInDays, String status) {

        this.dueDate = dueDate;
        this.ownerUserId = ownerUserId;
        this.additionalComments = additionalComments;
        this.borrowDate = borrowDate;
        this.contractPeriodInDays = contractPeriodInDays;
        this.status = status;
    }
}
