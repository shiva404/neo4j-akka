package com.campusconnect.neo4j.types;

import org.springframework.data.neo4j.annotation.RelationshipEntity;

/**
 * Created by sn1 on 4/22/15.
 */
@RelationshipEntity(type = "READ")
public class ReadRelation extends BookRelation {
    public ReadRelation(String goodreadsStatus) {
        this.goodreadsStatus = goodreadsStatus;
    }

    public ReadRelation(User user, Book book, String status, String goodreadsStatus) {
        super(user, book, status);
        this.goodreadsStatus = goodreadsStatus;
    }

    public ReadRelation(User user, Book book, String status, long createdDate, long lastModifiedDate, String goodreadsStatus) {
        super(user, book, status, createdDate, lastModifiedDate);
        this.goodreadsStatus = goodreadsStatus;
    }

    String goodreadsStatus;
    
}
