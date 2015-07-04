package com.campusconnect.neo4j.resources;

import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.types.neo4j.*;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.web.Book;
import com.campusconnect.neo4j.types.web.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.campusconnect.neo4j.mappers.Neo4jToWebMapper.mapBookNeo4jToWeb;
import static com.campusconnect.neo4j.mappers.WebToNeo4jMapper.mapBookWebToNeo4j;
import static com.campusconnect.neo4j.util.Constants.*;

/**
 * Created by I308260 on 2/19/2015.
 */

@Path("books")
@Consumes("application/json")
@Produces("application/json")
public class BookResource {

    private static final Logger logger = LoggerFactory.getLogger(BookResource.class);

    private BookDao bookDao;
    private UserDao userDao;

    public BookResource(BookDao bookDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    public BookResource() {
    }

    @POST
    public Response createBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        com.campusconnect.neo4j.types.neo4j.Book createdBook = bookDao.createBook(mapBookWebToNeo4j(book));
        return Response.created(null).entity(mapBookNeo4jToWeb(createdBook)).build();
    }

    @GET
    @Path("{bookId}")
    public Response getBook(@PathParam("bookId") String bookId,
                            @QueryParam(LOGGED_IN_USER_QPARAM) final String loggedInUser,
                            @QueryParam(ID_TYPE_QPARAM) @DefaultValue("id") String bookIdType) {
        com.campusconnect.neo4j.types.neo4j.Book book;
        if (loggedInUser != null) {
            if (bookIdType.equals("id"))
                book = bookDao.getBooksRelatedUser(bookId, loggedInUser);
            else
                book = bookDao.getBookByGoodreadsIdWithUser(Integer.parseInt(bookId), loggedInUser);
        } else {
            if (bookIdType.equals("id"))
                book = bookDao.getBook(bookId);
            else
                book = bookDao.getBookByGoodreadsId(Integer.parseInt(bookId));
        }
        return Response.ok().entity(mapBookNeo4jToWeb(book)).build();
    }

    @GET
    @Path("isbn/{isbn}")
    public Response getBookByISBN(@PathParam("isbn") final String isbn) throws IOException {

        com.campusconnect.neo4j.types.neo4j.Book book = bookDao.getBookByIsbn(isbn);
        return Response.ok().entity(mapBookNeo4jToWeb(book)).build();
    }

    //Except borrow relation everything should be handled here
    @POST
    @Path("{bookId}/users/{userId}")
    public Response addBook(@PathParam("userId") final String userId,
                            @PathParam("bookId") final String bookId,
                            @QueryParam(LISTING_TYPE_QPARAM) final String listingType,
                            @QueryParam(STATUS_QPARAM) final String status,
                            @QueryParam(ID_TYPE_QPARAM) String bookIdType) throws Exception {
        User user = userDao.getUser(userId);
        com.campusconnect.neo4j.types.neo4j.Book book = null;
        if (bookIdType == null || bookIdType.equals(ID)) {
            book = bookDao.getBook(bookId);
        } else if (bookIdType.equals(GR_ID)) {
            book = bookDao.getBookByGoodreadsId(Integer.parseInt(bookId));
        }
        Long now = System.currentTimeMillis();
        //todo: listing type shouldn't be null:
        switch (listingType.toUpperCase()) {
            case OWNS_RELATION:
                bookDao.listBookAsOwns(new OwnsRelationship(user, book, now, status.toUpperCase(), now));
                break;
            case WISHLIST_RELATION:
                bookDao.addWishBookToUser(new WishListRelationship(user, book, status, now, now));
                break;
            case CURRENTLY_READING_RELATION:
                bookDao.listBookAsCurrentlyReading(new CurrentlyReadingRelationShip(user, book, status, now, now));
                break;
            case READ_RELATION:
                bookDao.listBookAsRead(new ReadRelationship(user, book, status, now, now, null));
                break;
            default:
                logger.warn("BookType is not matched: " + listingType);
        }
        return Response.ok().build();
    }


    @POST
    @Path("{bookId}/borrow")
    public Response borrowBook(@PathParam("bookId") String bookId,
                               @QueryParam(ID_TYPE_QPARAM) String bookIdType,
                               BorrowRequest borrowRequest) {
        com.campusconnect.neo4j.types.neo4j.Book book = null;
        if (bookIdType == null || bookIdType.equals("id")) {
            book = bookDao.getBook(bookId);
        } else if (bookIdType.equals("grId")) {
            book = bookDao.getBookByGoodreadsId(Integer.parseInt(bookId));
        }
        User borrower = userDao.getUser(borrowRequest.getBorrowerUserId());
        bookDao.addBookToBorrower(borrower, book, borrowRequest);
        return Response.ok().build();
    }

    @PUT
    @Path("{bookId}/users/{ownerUserId}/{borrowerId}/borrowedBook")
    public Response updateStatus(@PathParam("bookId") String bookId,
                                 @PathParam("ownerUserId") String ownerUserId,
                                 @PathParam("borrowerId") String borrowerId,
                                 @QueryParam(STATUS_QPARAM) String status,
                                 @QueryParam(SHARE_PH_QPARAM) String phoneSharing,
                                 @QueryParam(MESSAGE_QPARAM) String message) {

        com.campusconnect.neo4j.types.neo4j.Book book = bookDao.getBook(bookId);
        User owner = userDao.getUser(ownerUserId);
        switch (status) {
            case BORROW_AGREED:
                if (borrowerId != null) {
                    User borrower = userDao.getUser(borrowerId);
                    if (borrower != null)
                        bookDao.updateBookStatusOnAgreement(owner, book, borrower, message);
                }
                break;
            case BORROW_SUCCESS:
                if (borrowerId != null) {
                    User borrower = userDao.getUser(borrowerId);
                    if (borrower != null)
                        bookDao.updateBookStatusOnSuccess(owner, book, borrower, message);
                }
                break;
            case BORROW_REJECT:
                if (borrowerId != null) {
                    User borrower = userDao.getUser(borrowerId);
                    if (borrower != null) {
                        bookDao.deleteBorrowRequest(borrowerId, bookId, ownerUserId, message);
                    }
                }
                break;
        }
        return Response.ok().build();
    }

    @GET
    @Path("search")
    public Response search(@QueryParam("q") final String queryString, @QueryParam(LOGGED_IN_USER_QPARAM) final String userId) {
        List<com.campusconnect.neo4j.types.neo4j.Book> searchResult;
        if (userId == null) {
            searchResult = bookDao.search(queryString);
        } else {
            searchResult = bookDao.searchWithRespectToUser(userId, queryString);
        }
        List<com.campusconnect.neo4j.types.web.Book> webBooks = new ArrayList<>();
        for (com.campusconnect.neo4j.types.neo4j.Book book : searchResult) {
            webBooks.add(mapBookNeo4jToWeb(book));
        }
        SearchResult result = new SearchResult();
        result.getBooks().addAll(webBooks);
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("{bookId}/users/{userId}/history")
    public Response bookHistory(@PathParam("bookId") String bookId, @PathParam("userId") String userId) {
        List<HistoryEvent> historyEvents = bookDao.getBookHistory(bookId, userId);
        HistoryEventPage historyEventPage = new HistoryEventPage(historyEvents, 0, historyEvents.size());
        return Response.ok().entity(historyEventPage).build();
    }
}
