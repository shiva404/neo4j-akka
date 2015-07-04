package com.campusconnect.neo4j.types.neo4j;

import com.campusconnect.neo4j.util.Constants;
import org.springframework.data.neo4j.annotation.RelationshipEntity;


@RelationshipEntity(type = Constants.CURRENTLY_READING_RELATION)
public class CurrentlyReadingRelationShip extends BookRelationship {

    private Long startDate;

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public CurrentlyReadingRelationShip() {

    }

    public CurrentlyReadingRelationShip(User user, Book book, String status) {
        super(user, book, status);

    }

    public CurrentlyReadingRelationShip(User user, Book book, String status, Long createdDate, Long lastModifiedDate) {
        super(user, book, status, createdDate, lastModifiedDate);

    }

}
