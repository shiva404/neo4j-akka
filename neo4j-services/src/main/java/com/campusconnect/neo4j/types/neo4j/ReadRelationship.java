package com.campusconnect.neo4j.types.neo4j;

import com.campusconnect.neo4j.types.common.Constants;
import org.springframework.data.neo4j.annotation.RelationshipEntity;

/**
 * Created by sn1 on 4/22/15.
 */
@RelationshipEntity(type = Constants.READ_RELATION)
public class ReadRelationship extends BookRelationship {
    String goodreadsStatus;

    public ReadRelationship(String goodreadsStatus) {
        this.goodreadsStatus = goodreadsStatus;
    }

    public ReadRelationship(User user, Book book, String status, String goodreadsStatus) {
        super(user, book, status);
        this.goodreadsStatus = goodreadsStatus;
    }

    public ReadRelationship(User user, Book book, String status, long createdDate, long lastModifiedDate, String goodreadsStatus) {
        super(user, book, status, createdDate, lastModifiedDate);
        this.goodreadsStatus = goodreadsStatus;
    }

    public ReadRelationship() {
    }

    @Override
    public String toString() {
        return "ReadRelationship{" +
                "goodreadsStatus='" + goodreadsStatus + '\'' +
                '}';
    }
}
