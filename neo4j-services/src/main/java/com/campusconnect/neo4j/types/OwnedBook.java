package com.campusconnect.neo4j.types;


import java.io.Serializable;

/**
 * Created by sn1 on 2/25/15.
 */
public class OwnedBook extends Book implements Serializable {

    private long createdDate;
    private String status;
    private long lastModifiedDate;
    private String borrowerId;
    private String dueDate;
    private int contractPeriodInDays;

    public OwnedBook(Book book, OwnsRelationship ownsRelationship) {
        super(book.getId(), book.getName(), book.getIsbn());
        this.createdDate = ownsRelationship.getCreatedDate();
        this.status = ownsRelationship.getStatus();
        this.lastModifiedDate = ownsRelationship.getLastModifiedDate();
        this.borrowerId = ownsRelationship.getBorrowerId();
        this.dueDate = ownsRelationship.getDueDate();
    }

    public OwnedBook() {
    }

    public OwnedBook(long createdDate, String status, long lastModifiedDate) {

        this.createdDate = createdDate;
        this.status = status;
        this.lastModifiedDate = lastModifiedDate;
    }

    public OwnedBook(String name, String isbn, long createdDate, String status, long lastModifiedDate) {
        super(name, isbn);
        this.createdDate = createdDate;
        this.status = status;
        this.lastModifiedDate = lastModifiedDate;
    }

    public OwnedBook(String id, String name, String isbn, long createdDate, String status, long lastModifiedDate) {
        super(id, name, isbn);
        this.createdDate = createdDate;
        this.status = status;
        this.lastModifiedDate = lastModifiedDate;
    }

    public OwnedBook(Integer goodreadsId, String authorName, String goodreadsAuthorId, String name, String isbn, String isbn13, int publishedYear, String description, String publisher, Integer numberOfPages, String imageUrl, long createdDate, String status, long lastModifiedDate, String borrowerId, String dueDate, int contractPeriodInDays) {
        super(goodreadsId, authorName, goodreadsAuthorId, name, isbn, isbn13, publishedYear, description, publisher, numberOfPages, imageUrl);
        this.createdDate = createdDate;
        this.status = status;
        this.lastModifiedDate = lastModifiedDate;
        this.borrowerId = borrowerId;
        this.dueDate = dueDate;
        this.contractPeriodInDays = contractPeriodInDays;
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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
