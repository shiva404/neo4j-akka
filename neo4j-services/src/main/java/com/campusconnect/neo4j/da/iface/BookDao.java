package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * Created by sn1 on 3/11/15.
 */
public interface BookDao {
    Book createBook(Book book);

    Book getBook(String bookId);

    void listBookAsOwns(OwnsRelationship ownsRelationship);

    void listBookAsRead(ReadRelation readRelation);

    @Transactional
    void updateOwnedBookStatus(User user, Book book, String status, String userComment);

    void addBookToBorrower(User borrower, Book book, BorrowRequest borrowRequest);

    void updateBookStatusOnAgreement(User user, Book book, User borrower, String userComment);

    void updateBookStatusOnSuccess(User user, Book book, User borrower, String userComment);

    @Transactional
    void updateBorrowedBookStatus(User user, Book book, String status, String userComment);

    SearchResult search(String queryString);

    // @Cacheable(cacheName = "bookByGRIdCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    Book getBookByGoodreadsId(Integer goodreadsId) throws IOException;

    public Book getBookByGoodreadsIdAndSaveIfNotExists(String goodreadsId, Book book);

    public void addWishBookToUser(WishListRelationship wishListRelationship);

    void createGoodreadsFriendBookRec(GoodreadsFriendBookRecRelation goodreadsFriendBookRecRelation);

    Book getBookByIsbn(String isbn) throws IOException;

    List<UserRecommendation> getRecommendationsForUserAndBook(String bookId, String userId);

    Book getBookRelatedUser(String bookId, String userId);

    Book getBookByGoodreadsIdWithUser(Integer goodreadsId, String userId);

    List<Book> getBooksRelatedUser(String userId);

    SearchResult searchWithRespectToUser(String userId, String searchString);
}
