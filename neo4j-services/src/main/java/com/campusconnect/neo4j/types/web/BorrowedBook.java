package com.campusconnect.neo4j.types.web;


import java.io.Serializable;

/**
 * Created by sn1 on 2/26/15.
 */
public class BorrowedBook extends Book implements Serializable {
    private String status;
    private long dueDate;
    private long createdDate;
    private String ownerUserId;
    private String additionalComments;
    private long borrowDate;
    private int contractPeriodInDays;
    private long lastModifiedDate;


    public BorrowedBook() {
    }

    public BorrowedBook(Book book) {
        super(book.getId(), book.getGoodreadsId(), book.getAuthorName(), book.getGoodreadsAuthorId(), book.getName(),
                book.getIsbn(), book.getIsbn13(), book.getPublishedYear(), book.getDescription(), book.getPublisher(),
                book.getNumberOfPages(), book.getImageUrl());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
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

    public long getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(long borrowDate) {
        this.borrowDate = borrowDate;
    }

    public int getContractPeriodInDays() {
        return contractPeriodInDays;
    }

    public void setContractPeriodInDays(int contractPeriodInDays) {
        this.contractPeriodInDays = contractPeriodInDays;
    }

    public long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
