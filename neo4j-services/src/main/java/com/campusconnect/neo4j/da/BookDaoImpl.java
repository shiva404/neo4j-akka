package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.akka.goodreads.GoodreadsAsynchHandler;
import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.repositories.BookRepository;
import com.campusconnect.neo4j.repositories.OwnsRelationshipRepository;
import com.campusconnect.neo4j.types.*;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.PartialCacheKey;
import com.googlecode.ehcache.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by sn1 on 2/16/15.
 */
public class BookDaoImpl implements BookDao {

    private Neo4jTemplate neo4jTemplate;
    private GoodreadsDao goodreadsDao;
    private GoodreadsAsynchHandler goodreadsAsynchHandler;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    OwnsRelationshipRepository ownsRelationshipRepository;

    public BookDaoImpl(Neo4jTemplate neo4jTemplate, GoodreadsDao goodreadsDao, GoodreadsAsynchHandler goodreadsAsynchHandler) {
        this.neo4jTemplate = neo4jTemplate;
        this.goodreadsDao = goodreadsDao;
        this.goodreadsAsynchHandler = goodreadsAsynchHandler;
    }


    @Override
    public Book createBook(Book book) {
        return neo4jTemplate.save(book);
    }

    @Override
    public Book getBook(String bookId) {
        return bookRepository.findBySchemaPropertyValue("id", bookId);
    }

    @Override
    public void addBookToUser(OwnsRelationship ownsRelationship) {
        neo4jTemplate.save(ownsRelationship);
    }

    @Override
    @Transactional
    public void updateOwnedBookStatus(User user, Book book, String status) {
        OwnsRelationship relationship = neo4jTemplate.getRelationshipBetween(user, book, OwnsRelationship.class, RelationTypes.OWNS.toString());
        if (relationship == null) //todo: throw an exception
            return;
        relationship.setStatus(status);
        relationship.setLastModifiedDate(System.currentTimeMillis());
        neo4jTemplate.save(relationship);
    }

    @Override
    public void addBookToBorrower(User borrower, Book book, BorrowRequest borrowRequest) {
        BorrowRelation borrowRelation = new BorrowRelation(borrower, book, "pending");
        borrowRelation.setBorrowDate(borrowRequest.getBorrowDate());
        borrowRelation.setContractPeriodInDays(borrowRequest.getContractPeriodInDays());
        borrowRelation.setAdditionalComments(borrowRequest.getAdditionalMessage());
        borrowRelation.setOwnerUserId(borrowRequest.getOwnerUserId());
        neo4jTemplate.save(borrowRelation);
    }

    @Override
    public void updateBookStatusOnAgreement(User user, Book book, User borrower) {
        updateOwnedBookStatus(user, book, "locked");
        updateBorrowedBookStatus(borrower, book, "agreed");
    }

    @Override
    public void updateBookStatusOnSuccess(User user, Book book, User borrower) {
        updateOwnedBookStatus(user, book, "lent");
        updateBorrowedBookStatus(borrower, book, "borrowed");
    }

    @Override
    @Transactional
    public void updateBorrowedBookStatus(User user, Book book, String status) {
        BorrowRelation relationship = neo4jTemplate.getRelationshipBetween(user, book, BorrowRelation.class, RelationTypes.BORROWED.toString());
        if (relationship == null) //todo: throw an exception
            return;
        relationship.setStatus(status);
        relationship.setLastModifiedDate(System.currentTimeMillis());
        neo4jTemplate.save(relationship);
    }

    @Override
    public SearchResult search(String queryString) {
        return goodreadsDao.search(queryString);
    }

    @Override
    @Cacheable(cacheName = "bookByGRIdCache", keyGenerator = @KeyGenerator(name="HashCodeCacheKeyGenerator", properties = @Property( name="includeMethod", value="false")))
    public Book getBookByGoodreadsId(String goodreadsId) throws IOException {
        try {
            Book book = bookRepository.findBySchemaPropertyValue("goodreadsId", goodreadsId);
            if(book == null) {
                final Book bookByGoodreadId = goodreadsDao.getBookById(goodreadsId);
                bookByGoodreadId.setId(UUID.randomUUID().toString());
                goodreadsAsynchHandler.saveBook(bookByGoodreadId);
                return bookByGoodreadId;     
            }
        } catch (Exception e) {
           //Todo : if exception is not found search in goodreads
            return goodreadsDao.getBookById(goodreadsId);
        }
        return null;
    }

    @Override
    @Cacheable(cacheName = "bookByGRIdCache", keyGenerator = @KeyGenerator(name="HashCodeCacheKeyGenerator", properties = @Property( name="includeMethod", value="false")))
    public Book getBookByGoodreadsId(@PartialCacheKey String goodreadsId, Book book) {
        Book bookByGoodreadsId = bookRepository.findBySchemaPropertyValue("goodreadsId", goodreadsId);
        if(bookByGoodreadsId == null) {
            book.setId(UUID.randomUUID().toString());
            return createBook(book);
        }
        return bookByGoodreadsId;
    }

    @Override
    public void addWishBookToUser(WishListRelationship wishListRelationship) {
        neo4jTemplate.save(wishListRelationship);
    }
    
    @Override
    public void createGoodreadsFriendBookRec(GoodreadsFriendBookRecRelation goodreadsFriendBookRecRelation) {
        neo4jTemplate.save(goodreadsFriendBookRecRelation);
    }
}
