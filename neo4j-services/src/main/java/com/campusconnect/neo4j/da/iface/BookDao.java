package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.*;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.web.*;

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

    void listBookAsRead(ReadRelationship readRelation);

    void listBookAsCurrentlyReading(CurrentlyReadingRelationShip currentlyReadingRelationship);

    @Transactional
    void updateOwnedBookStatus(User user, Book book, String status, String userComment);

    void addBookToBorrower(Book book, BorrowRequest borrowRequest);

    void updateBookStatusOnAgreement(User user, Book book, User borrower, String userComment);

    void updateBookStatusOnSuccess(User user, Book book, User borrower, String userComment);

    @Transactional
    void updateBorrowedBookStatus(User user, Book book, String status, String userComment);

    List<Book> search(String queryString);

    // @Cacheable(cacheName = "bookByGRIdCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    Book getBookByGoodreadsId(Integer goodreadsId);

    public Book getBookByGoodreadsIdAndSaveIfNotExists(Integer goodreadsId, Book book);

    public void addWishBookToUser(WishListRelationship wishListRelationship);

    void createGoodreadsFriendBookRec(GoodreadsRecRelationship goodreadsFriendBookRecRelation);

    Book getBookByIsbn(String isbn) throws IOException;

    Book getBookRelatedUser(String bookId, String userId);

    Book getBookByGoodreadsIdWithUser(Integer goodreadsId, String userId);

    List<Book> getAllUserBooks(String userId, String loggedInUser);

    List<Book> searchWithRespectToUser(String userId, String searchString);

    List<Book> getReadBooks(String userId);

    List<OwnedBook> getOwnedBooks(String userId);

    List<OwnedBook> getLentBooks(String userId);

    List<OwnedBook> getAvailableBooks(String userId);

    List<GoodreadsUserRecommendation> getGoodreadsUserRecommendations(String userId);

    //    @Cacheable(cacheName = "userWishBooks", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    List<WishListBook> getWishListBooks(String userId);

    List<CurrentlyReadingBook> getCurrentlyReadingBook(String userId);

    //    @Cacheable(cacheName = "userBorrowedBooks", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    List<BorrowedBook> getBorrowedBooks(String userId);

    // Return history of books
    List<HistoryEvent> getBookHistory(String bookId, String UserId);

    void deleteBorrowRequest(String borrowerId, String bookId, String ownerId, String message);


    List<Book> getWishlistBooksWithDetails(String userId, String loggedInUser);
    List<Book> getOwnedBooksWithDetails(String userId, String loggedInUser);

    void initiateBookReturn(String bookId, String status, ReturnRequest returnRequest);


    void updateBookReturnToAgreed(String bookId, String status, String ownerId, String borrowerId, String comment);

    void updateBookReturnToSuccess(String bookId, String status, String ownerId, String borrowerId, String comment);

    List<Book> getWishListBooksWithMin1Rec(String userId);



	void removeOwnedBook(String userId, String bookId);

	void removeWishlistBook(String userId, String bookId);

	void removeCurrentlyReadingBook(String userId, String bookId);

	void removeReadBook(String userId, String bookId);

}
