package com.campusconnect.neo4j.types;

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
    
    public BorrowedBook(Book book, BorrowRelation borrowRelation) {
        setId(book.getId());
        setName(book.getName());
        setIsbn(book.getIsbn());
        setImageUrl(book.getImageUrl());
        setIsbn13(book.getIsbn13());
        setAuthorName(book.getAuthorName());
        setDescription(book.getDescription());
        setGoodreadsAuthorId(book.getGoodreadsAuthorId());
        setGoodreadsId(book.getGoodreadsId());
        setNodeId(book.getNodeId());
        setNumberOfPages(book.getNumberOfPages());
        setPublishedYear(book.getPublishedYear());
        setPublisher(book.getPublisher());
        this.status = borrowRelation.getStatus();
        this.dueDate = borrowRelation.getDueDate();
        this.createdDate = borrowRelation.getCreatedDate();
        this.ownerUserId = borrowRelation.getOwnerUserId();
        this.additionalComments = borrowRelation.getAdditionalComments();
        this.borrowDate = borrowRelation.getBorrowDate();
        this.contractPeriodInDays = borrowRelation.getContractPeriodInDays();
        this.lastModifiedDate = borrowRelation.getLastModifiedDate();
    }

    public BorrowedBook(String name, String isbn, String status, long dueDate, String ownerUserId, long borrowDate) {
        super(name, isbn);
        this.status = status;
        this.dueDate = dueDate;
        this.ownerUserId = ownerUserId;
        this.borrowDate = borrowDate;
    }

    public BorrowedBook(String id, String name, String isbn, String status, long dueDate, String ownerUserId, long borrowDate) {
        super(id, name, isbn);
        this.status = status;
        this.dueDate = dueDate;
        this.ownerUserId = ownerUserId;
        this.borrowDate = borrowDate;
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
