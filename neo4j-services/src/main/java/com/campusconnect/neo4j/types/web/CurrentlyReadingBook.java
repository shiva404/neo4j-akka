package com.campusconnect.neo4j.types.web;

import java.io.Serializable;

public class CurrentlyReadingBook extends Book implements Serializable {

    private Long createdDate;
    private String status;
    private Long lastModifiedDate;
  

    public CurrentlyReadingBook() {
    }

    public CurrentlyReadingBook(Book book) {
        super(book.getId(), book.getGoodreadsId(), book.getAuthorName(), book.getGoodreadsAuthorId(), book.getName(),
                book.getIsbn(), book.getIsbn13(), book.getPublishedYear(), book.getDescription(), book.getPublisher(),
                book.getNumberOfPages(), book.getImageUrl());
        setBookType(book.getBookType());
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
