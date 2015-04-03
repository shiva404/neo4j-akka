package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.*;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.Property;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by sn1 on 3/11/15.
 */
public interface BookDao {
    Book createBook(Book book);

    Book getBook(String bookId);

    void addBookToUser(OwnsRelationship ownsRelationship);

    @Transactional
    void updateOwnedBookStatus(User user, Book book, String status);

    void addBookToBorrower(User borrower, Book book, BorrowRequest borrowRequest);

    void updateBookStatusOnAgreement(User user, Book book, User borrower);

    void updateBookStatusOnSuccess(User user, Book book, User borrower);

    @Transactional
    void updateBorrowedBookStatus(User user, Book book, String status);

    SearchResult search(String queryString);

    @Cacheable(cacheName = "bookByGRIdCache", keyGenerator = @KeyGenerator(name="HashCodeCacheKeyGenerator", properties = @Property( name="includeMethod", value="false")))
    Book getBookByGoodreadsId(String goodreadsId) throws IOException;

    public Book getBookByGoodreadsId(String goodreadsId, Book book);

    public void addWishBookToUser(WishListRelationship wishListRelationship);

    void createGoodreadsFriendBookRec(GoodreadsFriendBookRecRelation goodreadsFriendBookRecRelation);
}
