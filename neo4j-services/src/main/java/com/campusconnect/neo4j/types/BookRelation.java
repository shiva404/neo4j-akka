package com.campusconnect.neo4j.types;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * Created by sn1 on 3/21/15.
 */
public abstract class BookRelation {
    @GraphId
    private Long id;

    @StartNode
    private User user;
    @EndNode
    private Book book;
    private String status;
    private long createdDate;
    private long lastModifiedDate;

    protected BookRelation() {
    }

    protected BookRelation(User user, Book book, String status) {
        this.user = user;
        this.book = book;
        this.status = status;
    }
    protected BookRelation(User user, Book book, String status, long createdDate, long lastModifiedDate) {
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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(long lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
