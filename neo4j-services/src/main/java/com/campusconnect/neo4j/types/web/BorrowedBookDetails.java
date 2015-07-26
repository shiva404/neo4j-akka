package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.common.BookDetails;

public class BorrowedBookDetails extends BookDetails {
    private User borrower;
    private User ownerUserId;

    private Long dueDate;
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

    public User getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(User ownerUserId) {
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

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public BorrowedBookDetails(Long dueDate, User ownerUserId, User borrower, String additionalComments,
                               Long borrowDate, Integer contractPeriodInDays, String status) {
        this.dueDate = dueDate;
        this.ownerUserId = ownerUserId;
        this.additionalComments = additionalComments;
        this.borrowDate = borrowDate;
        this.contractPeriodInDays = contractPeriodInDays;
        this.status = status;
        this.borrower = borrower;
    }
}
