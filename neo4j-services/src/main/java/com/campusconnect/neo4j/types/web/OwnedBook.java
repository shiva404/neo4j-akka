package com.campusconnect.neo4j.types.web;

import java.io.Serializable;

/**
 * Created by sn1 on 2/25/15.
 */
public class OwnedBook extends Book implements Serializable {

    private Long createdDate;
    private String status;
    private Long lastModifiedDate;
    private String borrowerId;
    private String dueDate;
    private int contractPeriodInDays;

    public OwnedBook() {
    }

    public OwnedBook(Book book) {
        super(book.getId(), book.getGoodreadsId(), book.getAuthorName(), book.getGoodreadsAuthorId(), book.getName(),
                book.getIsbn(), book.getIsbn13(), book.getPublishedYear(), book.getDescription(), book.getPublisher(),
                book.getNumberOfPages(), book.getImageUrl());
        setBookType(book.getBookType());
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getContractPeriodInDays() {
        return contractPeriodInDays;
    }

    public void setContractPeriodInDays(int contractPeriodInDays) {
        this.contractPeriodInDays = contractPeriodInDays;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
