package com.campusconnect.neo4j.da.mapper;

import com.campusconnect.neo4j.types.common.BookDetails;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.web.AvailableBookDetails;
import com.campusconnect.neo4j.types.web.BorrowedBookDetails;
import com.campusconnect.neo4j.types.web.LentBookDetails;
import org.neo4j.rest.graphdb.entity.RestRelationship;

import static com.campusconnect.neo4j.types.common.Constants.*;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/6/15
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class RelationToBookDetailsMapper {

    public static Book setRelationDetailsToBook(RestRelationship rawRelationship, Book book) {
        switch (rawRelationship.getType().name()) {
            case OWNS_RELATION:
                book.setBookType(OWNS_RELATION);
                book.setBookDetails(getOwnsBookDetails(rawRelationship));
                break;
            case BORROWED_RELATION:
                book.setBookType(BORROWED_RELATION);
                book.setBookDetails(getBorrowBookDetails(rawRelationship));
                break;
            case READ_RELATION:
                book.setBookType(READ_RELATION);
                break;
            case WISHLIST_RELATION:
                book.setBookType(WISHLIST_RELATION);
                break;
        }
        return book;
    }


    public static BookDetails getOwnsBookDetails(RestRelationship ownsRelationship) {
        //todo return based on the status
        return new AvailableBookDetails();
    }

    public static BorrowedBookDetails getBorrowBookDetails(RestRelationship borrowRelation) {
        return new BorrowedBookDetails();
    }

    public static LentBookDetails getLentBookDetails(RestRelationship ownsRelationship) {
        return new LentBookDetails();
    }
}
