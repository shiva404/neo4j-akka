package com.campusconnect.neo4j.types.neo4j;

import org.springframework.data.neo4j.annotation.RelationshipEntity;

/**
 * Created by sn1 on 4/22/15.
 */
@RelationshipEntity(type = "READ")
public class ReadRelation extends BookRelation {
    String goodreadsStatus;

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

    public ReadRelation() {
    }

    @Override
    public String toString() {
        return "ReadRelation{" +
                "goodreadsStatus='" + goodreadsStatus + '\'' +
                '}';
    }
}
