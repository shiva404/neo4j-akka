package com.campusconnect.neo4j.da.mapper;

import com.campusconnect.neo4j.types.common.BookDetails;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.GoodreadsRecRelationship;
import com.campusconnect.neo4j.types.web.AvailableBookDetails;
import com.campusconnect.neo4j.types.web.BorrowedBookDetails;
import com.campusconnect.neo4j.types.web.LentBookDetails;
import org.neo4j.graphdb.NotFoundException;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.neo4j.rest.graphdb.entity.RestRelationship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.campusconnect.neo4j.util.Constants.*;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/15/15
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBMapper {
    private static Logger logger = LoggerFactory.getLogger(DBMapper.class);

    public static GoodreadsRecRelationship getGoodreadsRecRelationship(RestRelationship restRelationship) {
        if (restRelationship.getType().name().equals(GOODREADS_REC_RELATION)) {
            GoodreadsRecRelationship goodreadsRecRelationship = new GoodreadsRecRelationship();
            goodreadsRecRelationship.setFriendGoodreadsId((String) restRelationship.getProperty("friendGoodreadsId", null));
            goodreadsRecRelationship.setFriendImageUrl((String) restRelationship.getProperty("friendImageUrl", null));
            goodreadsRecRelationship.setFriendName((String) restRelationship.getProperty("friendName", null));
            goodreadsRecRelationship.setFriendId((String) restRelationship.getProperty("friendId", null));
            goodreadsRecRelationship.setCreatedDate((Long) restRelationship.getProperty("createdDate", null));

            return goodreadsRecRelationship;

        }
        return null;
    }

    public static Book getBookFromRestNode(RestNode restNode) {
        try {
            Book book = new Book();
            book.setAuthorName((String) restNode.getProperty("authorName", null));
            book.setDescription((String) restNode.getProperty("description", null));
            book.setGoodreadsAuthorId((String) restNode.getProperty("goodreadsAuthorId", null));
            book.setGoodreadsId((Integer) restNode.getProperty("goodreadsId", null));
            book.setId((String) restNode.getProperty("id", null));
            book.setImageUrl((String) restNode.getProperty("imageUrl", null));
            book.setIsbn((String) restNode.getProperty("isbn", null));
            book.setIsbn13((String) restNode.getProperty("isbn13", null));
            book.setName((String) restNode.getProperty("name", null));
            book.setNodeId(restNode.getId());
            book.setNumberOfPages((Integer) restNode.getProperty("numberOfPages", null));
            book.setPublishedYear((Integer) restNode.getProperty("publishedYear", null));
            book.setPublisher((String) restNode.getProperty("publisher", null));
            return book;
        } catch (NotFoundException e) {
            logger.debug("Ignoring field not found exception, message:" + e.getMessage());
        }
        return null;
    }

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

    private static BookDetails getOwnsBookDetails(RestRelationship ownsRelationship) {
        //todo return based on the status
        return new AvailableBookDetails();
        /*
        wnedBook.setCreatedDate(ownsRelationship.getCreatedDate());
            ownedBook.setStatus(ownsRelationship.getStatus());
            ownedBook.setLastModifiedDate(ownsRelationship.getLastModifiedDate());
            ownedBook.setBorrowerId(ownsRelationship.getBorrowerId());
            ownedBook.setDueDate(ownsRelationship.getDueDate());
         */
    }

    private static BorrowedBookDetails getBorrowBookDetails(RestRelationship borrowRelation) {
        return new BorrowedBookDetails();
        /*
                    borrowedBook.setStatus(borrowRelationship.getStatus());
            borrowedBook.setDueDate(borrowRelationship.getDueDate());
            borrowedBook.setCreatedDate(borrowRelationship.getCreatedDate());
            borrowedBook.setOwnerUserId(borrowRelationship.getOwnerUserId());
            borrowedBook.setAdditionalComments(borrowRelationship.getAdditionalComments());
            borrowedBook.setBorrowDate(borrowRelationship.getBorrowDate());
            borrowedBook.setContractPeriodInDays(borrowRelationship.getContractPeriodInDays());
            borrowedBook.setLastModifiedDate(borrowRelationship.getLastModifiedDate());
         */
    }

    private static LentBookDetails getLentBookDetails(RestRelationship ownsRelationship) {
        return new LentBookDetails();
    }
}
