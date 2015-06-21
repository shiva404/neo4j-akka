package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.akka.goodreads.GoodreadsAsynchHandler;
import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.da.iface.EmailDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.da.mapper.DBMapper;
import com.campusconnect.neo4j.mappers.Neo4jToWebMapper;
import com.campusconnect.neo4j.repositories.BookRepository;
import com.campusconnect.neo4j.repositories.UserRecRepository;
import com.campusconnect.neo4j.types.common.RelationTypes;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.*;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.web.*;
import com.googlecode.ehcache.annotations.PartialCacheKey;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.neo4j.rest.graphdb.entity.RestRelationship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

import static com.campusconnect.neo4j.da.mapper.DBMapper.getBookFromRestNode;
import static com.campusconnect.neo4j.da.mapper.DBMapper.setRelationDetailsToBook;

/**
 * Created by sn1 on 2/16/15.
 */
public class BookDaoImpl implements BookDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookDaoImpl.class);

    public BookDaoImpl() {
    }

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRecRepository userRecRepository;

    private Neo4jTemplate neo4jTemplate;
    private GoodreadsDao goodreadsDao;
    private GoodreadsAsynchHandler goodreadsAsynchHandler;
    private EmailDao emailDao;
    private UserDao userDao;

    public BookDaoImpl(Neo4jTemplate neo4jTemplate, GoodreadsDao goodreadsDao, GoodreadsAsynchHandler goodreadsAsynchHandler, EmailDao emailDao, UserDao userDao) {
        this.neo4jTemplate = neo4jTemplate;
        this.goodreadsDao = goodreadsDao;
        this.goodreadsAsynchHandler = goodreadsAsynchHandler;
        this.emailDao = emailDao;
        this.userDao = userDao;
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
    public void listBookAsOwns(OwnsRelationship ownsRelationship) {
        neo4jTemplate.save(ownsRelationship);
    }

    @Override
    public void listBookAsRead(ReadRelationship readRelation) {
        try {
            neo4jTemplate.save(readRelation);
        } catch (Exception e) {
            LOGGER.error("Error while saving read relation bookId" + readRelation.getBook().getId() + " UserId:" + readRelation.getUser().getId());
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void updateOwnedBookStatus(User user, Book book, String status, String userComment) {
        OwnsRelationship relationship = neo4jTemplate.getRelationshipBetween(user, book, OwnsRelationship.class, RelationTypes.OWNS.toString());
        if (relationship == null) //todo: throw an exception
            return;
        relationship.setStatus(status);
        relationship.setLastModifiedDate(System.currentTimeMillis());
        relationship.setUserComment(userComment);
        neo4jTemplate.save(relationship);
    }

    @Override
    public void addBookToBorrower(User borrower, Book book, BorrowRequest borrowRequest) {
        BorrowRelationship borrowRelation = new BorrowRelationship(borrower, book, "progress");
        borrowRelation.setBorrowDate(borrowRequest.getBorrowDate());
        borrowRelation.setContractPeriodInDays(borrowRequest.getContractPeriodInDays());
        borrowRelation.setAdditionalComments(borrowRequest.getAdditionalMessage());
        borrowRelation.setOwnerUserId(borrowRequest.getOwnerUserId());
        neo4jTemplate.save(borrowRelation);
        User ownerUser = userDao.getUser(borrowRelation.getOwnerUserId());
        emailDao.sendBorrowBookInitEmail(borrower, ownerUser, book);
    }

    @Override
    public void updateBookStatusOnAgreement(User user, Book book, User borrower, String userComment) {
        updateOwnedBookStatus(user, book, "locked", userComment);
        updateBorrowedBookStatus(borrower, book, "agreed", userComment);
        emailDao.sendAcceptedToLendBookEmail(user, borrower, book);
    }

    @Override
    public void updateBookStatusOnSuccess(User user, Book book, User borrower, String userComment) {
        updateOwnedBookStatus(user, book, "lent", userComment);
        updateBorrowedBookStatus(borrower, book, "borrowed", userComment);
    }

    @Override
    @Transactional
    public void updateBorrowedBookStatus(User user, Book book, String status, String userComment) {
        BorrowRelationship relationship = neo4jTemplate.getRelationshipBetween(user, book, BorrowRelationship.class, RelationTypes.BORROWED.toString());
        if (relationship == null) //todo: throw an exception
            return;
        relationship.setStatus(status);
        relationship.setLastModifiedDate(System.currentTimeMillis());
        relationship.setAdditionalComments(userComment);
        neo4jTemplate.save(relationship);
    }

    @Override
    public List<Book> search(String queryString) {
        List<Book> search = goodreadsDao.search(queryString);
        LOGGER.debug("got results back");
        return search;
    }

    @Override
    public List<Book> searchWithRespectToUser(String userId, String searchString) {
        List<Book> searchBooks = goodreadsDao.search(searchString);
        List<Book> existingBooks = getAllUserBooks(userId);

        //todo: make sure already read books comes first

        return replaceBooksWithExistingBooks(searchBooks, existingBooks);
    }

    private List<Book> replaceBooksWithExistingBooks(List<Book> books, List<Book> existingBooks) {

        List<Book> identifiedBooks = new ArrayList<>();
        for (Iterator<Book> iterator = books.iterator(); iterator.hasNext(); ) {
            Book book = iterator.next();
            for (Book existingBook : existingBooks) {
                if (existingBook.getGoodreadsId() != null && book.getGoodreadsId().equals(existingBook.getGoodreadsId())) {
                    iterator.remove();
                    identifiedBooks.add(existingBook);
                }
            }
        }
        LOGGER.debug("Identified books count:" + identifiedBooks.size());
        List<Book> resultBooks = new ArrayList<>();
        resultBooks.addAll(identifiedBooks);
        resultBooks.addAll(books);
        return resultBooks;
    }

    @Override
//    @Cacheable(cacheName = "bookByGRIdCache", keyGenerator = @KeyGenerator(name="HashCodeCacheKeyGenerator", properties = @Property( name="includeMethod", value="false")))
    public Book getBookByGoodreadsId(Integer goodreadsId) throws IOException {
        try {
            Book book = bookRepository.findBySchemaPropertyValue("goodreadsId", goodreadsId);
            if (book == null) {
                LOGGER.info("Goodreads book is not in there datsbase fetching from goodreads. Id: " + goodreadsId);
                final Book bookByGoodreadId = goodreadsDao.getBookById(goodreadsId.toString());
                bookByGoodreadId.setId(UUID.randomUUID().toString());
                createBook(bookByGoodreadId);
                return bookByGoodreadId;
            } else {
                return book;
            }
        } catch (Exception e) {
            //Todo : if exception is not found search in goodreads
            //return goodreadsDao.getBookById(goodreadsId);
        }
        return null;
    }

    @Override
    public Book getBookByGoodreadsIdAndSaveIfNotExists(@PartialCacheKey String goodreadsId, Book book) {
        Book bookByGoodreadsId = bookRepository.findBySchemaPropertyValue("goodreadsId", Integer.parseInt(goodreadsId));
        if (bookByGoodreadsId == null) {
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
    public void createGoodreadsFriendBookRec(GoodreadsRecRelationship goodreadsFriendBookRecRelation) {
        neo4jTemplate.save(goodreadsFriendBookRecRelation);
    }

    @Override
    public Book getBookByIsbn(String isbn) throws IOException {
        Book book = goodreadsDao.getBookByISBN(isbn);
        Book goodreadsBook = getBookByGoodreadsIdAndSaveIfNotExists(book.getGoodreadsAuthorId(), book);
        return goodreadsBook;
    }

    @Override
    public Book getBookRelatedUser(String bookId, String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("bookId", bookId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match(book:Book {id: {bookId}}) - [relation] - (user:User {id: {userId}}) return relation, book", params);
        //todo throw not found
        List<Book> books = getBookDetails(mapResult, userId);
        if (books.size() > 0) {
            return books.get(0);
        } else
            return null;
    }

    @Override
    public List<Book> getAllUserBooks(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match(book:Book) - [relation] - (user:User {id: {userId}}) " +
                "return relation, book", params);
        //todo throw not found
        return getBookDetails(mapResult, userId);
    }

    @Override
    public Book getBookByGoodreadsIdWithUser(Integer goodreadsId, String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("goodreadsId", goodreadsId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match(book:Book {goodreadsId: {goodreadsId}}) - [relation] - (user:User {id: {userId}}) return relation, book", params);
        //todo throw not found
        List<Book> books = getBookDetails(mapResult, userId);
        if (books.size() > 0) {
            return books.get(0);
        } else
            return null;
    }

    private List<Book> getBookDetails(Result<Map<String, Object>> mapResult, String userId) {
        List<Book> books = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode bookNode = (RestNode) objectMap.get("book");
            RestRelationship rawWishRelationship = (RestRelationship) objectMap.get("relation");
            Book book = getBookFromRestNode(bookNode);
            book = setRelationDetailsToBook(rawWishRelationship, book);
            books.add(book);
        }
        return books;
    }


    @Override
    public List<UserRecommendation> getGoodreadsUserRecommendations(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[relation:GR_REC]->(books:Book) return books, relation", params);
        return getWishUserRecFromResultMap(mapResult);
    }


    @Override
//    @Cacheable(cacheName = "userWishBooks", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public List<WishListBook> getWishListBooks(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[relation:WISH]->(books:Book) return books, relation", params);
        return getWishListBooksFromResultMap(mapResult);
    }

    @Override
    public List<Book> getReadBooks(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[:READ]->(books:Book) return books", params);
        return getBooksFromResultMap(mapResult);

    }

    @Override
//    @Cacheable(cacheName = "userOwnedBooks", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public List<OwnedBook> getOwnedBooks(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[relation:OWNS]->(books:Book) return books, relation", params);
        return getOwnedBooksFromResultMap(mapResult);
    }

    @Override
    public List<OwnedBook> getAvailableBooks(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[relation:OWNS {status: \"available\"}]->(books:Book) return books, relation", params);
        return getOwnedBooksFromResultMap(mapResult);
    }

    @Override
    public List<OwnedBook> getLentBooks(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[relation:OWNS {status: \"lent\"}]->(books:Book) return books, relation", params);
        return getOwnedBooksFromResultMap(mapResult);
    }

    private List<OwnedBook> getOwnedBooksFromResultMap(Result<Map<String, Object>> mapResult) {
        List<OwnedBook> ownedBooks = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode bookNode = (RestNode) objectMap.get("books");
            RestRelationship rawOwnsRelationship = (RestRelationship) objectMap.get("relation");

            Book book = getBookFromRestNode(bookNode);
            setRelationDetailsToBook(rawOwnsRelationship, book);

            com.campusconnect.neo4j.types.web.Book webBook = Neo4jToWebMapper.mapBookNeo4jToWeb(book);
            OwnedBook ownedBook = new OwnedBook(webBook);
            ownedBooks.add(ownedBook);
        }
        return ownedBooks;
    }

    @Override
//    @Cacheable(cacheName = "userBorrowedBooks", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public List<BorrowedBook> getBorrowedBooks(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[relation:BORROWED]->(books:Book) return books, relation", params);
        return getBorrowedBooksFromResultMap(mapResult);
    }

    private List<Book> getBooksFromResultMap(Result<Map<String, Object>> mapResult) {
        List<Book> books = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode bookNode = (RestNode) objectMap.get("books");
            Book book = getBookFromRestNode(bookNode);
            books.add(book);
        }
        return books;
    }

    private List<WishListBook> getWishListBooksFromResultMap(Result<Map<String, Object>> mapResult) {
        List<WishListBook> wishListBooks = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode bookNode = (RestNode) objectMap.get("books");
            RestRelationship rawWishRelationship = (RestRelationship) objectMap.get("relation");

            Book book = getBookFromRestNode(bookNode);
            setRelationDetailsToBook(rawWishRelationship, book);

            com.campusconnect.neo4j.types.web.Book webBook = Neo4jToWebMapper.mapBookNeo4jToWeb(book);
            WishListBook wishListBook = new WishListBook(webBook);

            wishListBooks.add(wishListBook);
        }
        return wishListBooks;
    }

    private List<UserRecommendation> getWishUserRecFromResultMap(Result<Map<String, Object>> mapResult) {
        List<UserRecommendation> userRecommendations = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode bookNode = (RestNode) objectMap.get("books");
            RestRelationship rawWishRelationship = (RestRelationship) objectMap.get("relation");

            Book book = getBookFromRestNode(bookNode);
            book.setBookType("WISH");
            GoodreadsRecRelationship goodreadsRecRelation = DBMapper.getGoodreadsRecRelationship(rawWishRelationship);

            UserRecommendation userRecommendation = new UserRecommendation(Neo4jToWebMapper.mapBookNeo4jToWeb(book));
            userRecommendation.setFriendGoodreadsId(goodreadsRecRelation.getFriendGoodreadsId());
            userRecommendation.setFriendImageUrl(goodreadsRecRelation.getFriendImageUrl());
            userRecommendation.setFriendName(goodreadsRecRelation.getFriendName());
            userRecommendation.setCreateDate(goodreadsRecRelation.getCreatedDate());
            userRecommendation.setFriendId(goodreadsRecRelation.getFriendId());
            userRecommendations.add(userRecommendation);
        }
        return userRecommendations;
    }

    private List<BorrowedBook> getBorrowedBooksFromResultMap(Result<Map<String, Object>> mapResult) {
        List<BorrowedBook> borrowedBooks = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode bookNode = (RestNode) objectMap.get("books");
            RestRelationship rawBorrowRelationship = (RestRelationship) objectMap.get("relation");
            Book book = getBookFromRestNode(bookNode);
            setRelationDetailsToBook(rawBorrowRelationship, book);

            com.campusconnect.neo4j.types.web.Book webBook = Neo4jToWebMapper.mapBookNeo4jToWeb(book);
            BorrowedBook borrowedBook = new BorrowedBook(webBook);
            borrowedBooks.add(borrowedBook);
        }
        return borrowedBooks;
    }

}
