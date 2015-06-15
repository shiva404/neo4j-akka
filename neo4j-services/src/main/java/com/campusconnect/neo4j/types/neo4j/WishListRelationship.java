package com.campusconnect.neo4j.types.neo4j;

import com.campusconnect.neo4j.util.Constants;
import org.springframework.data.neo4j.annotation.RelationshipEntity;

/**
 * Created by sn1 on 3/19/15.
 */
@RelationshipEntity(type = Constants.WISHLIST_RELATION)
public class WishListRelationship extends BookRelationship {

    public WishListRelationship(User user, Book book, String status, long createdDate, long lastModifiedDate) {
        super(user, book, status, createdDate, lastModifiedDate);
    }

    public WishListRelationship() {
    }

    public WishListRelationship(User user, Book book, String status) {

        super(user, book, status);
    }
}
