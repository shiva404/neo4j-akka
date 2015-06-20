package com.campusconnect.neo4j.types.neo4j;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by sn1 on 3/21/15.
 */
public abstract class BookRelationship {
    @GraphId
    private Long id;

    @StartNode
    private User user;
    @EndNode
    private Book book;
    private String status;
    private Long createdDate;
    private Long lastModifiedDate;

    protected BookRelationship() {
    }

    protected BookRelationship(User user, Book book, String status) {
        this.user = user;
        this.book = book;
        this.status = status;
    }

    protected BookRelationship(User user, Book book, String status, Long createdDate, Long lastModifiedDate) {
        this.user = user;
        this.book = book;
        this.status = status;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
