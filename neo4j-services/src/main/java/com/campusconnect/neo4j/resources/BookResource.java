package com.campusconnect.neo4j.resources;

import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.BorrowRequest;
import com.campusconnect.neo4j.types.SearchResult;
import com.campusconnect.neo4j.types.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by I308260 on 2/19/2015.
 */

@Path("books")
@Consumes("application/json")
@Produces("application/json")
public class BookResource {
    private BookDao bookDao;
    private UserDao userDao;

    public BookResource(BookDao bookDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    @POST
    public Response createBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        Book createdBook = bookDao.createBook(book);
        return Response.created(null).entity(createdBook).build();
    }

    @GET
    @Path("{bookId}")
    public Response getBook(@PathParam("bookId") String bookId, @QueryParam("expectedType") final String expectedType) {
        Book book = bookDao.getBook(bookId);
        return Response.ok().entity(book).build();
    }
    
    @GET
    @Path("{bookId}/users/{userId}")
    public Response getBookWithRespectToUser(@PathParam("bookId") String bookId, @PathParam("userId") final String userId, @QueryParam("expectedType") final String expectedType) {
        Book book = bookDao.getBook(bookId, userId);
        return Response.ok().entity(book).build();
    }
    
    @GET
    @Path("goodreadsId/{goodreadsId}")
    public Response getBookByGoodreadsId(@PathParam("goodreadsId") final String goodreadsId ) throws IOException {
        Book book = bookDao.getBookByGoodreadsId(goodreadsId);
        return Response.ok().entity(book).build();
    }
    
    @GET
    @Path("isbn/{isbn}")
    public Response getBookByISBN(@PathParam("isbn") final String isbn) throws IOException {
        Book book = bookDao.getBookByIsbn(isbn);
        return Response.ok().entity(book).build();
    }
    
    @POST
    @Path("{bookId}/borrow")
    public Response borrowBook(@PathParam("bookId") String bookId, BorrowRequest borrowRequest){
        //create a link to the user - pendingBorrow
        Book book = bookDao.getBook(bookId);
        User borrower = userDao.getUser(borrowRequest.getBorrowerUserId());
        bookDao.addBookToBorrower(borrower, book, borrowRequest);
        //generate notification
        return Response.ok().build();
    }
    
    @PUT
    @Path("{bookId}/users/{userId}/borrow")
    public Response updateStatus(@PathParam("bookId") String bookId, @PathParam("userId") String userId, 
                                 @QueryParam("borrowerId") String borrowerId, @QueryParam("status") String status, @QueryParam("sharePh ") String phoneSharing, BorrowRequest borrowRequest) {
        //locked - for user
        //agreed - for borrower
        Book book = bookDao.getBook(bookId);
        User user = userDao.getUser(userId);
        if(status.equals("agreed")){
           if(borrowerId != null){
               User borrower = userDao.getUser(borrowerId);
               if(borrower != null)
               bookDao.updateBookStatusOnAgreement(user, book, borrower, borrowRequest.getAdditionalMessage());
               
                   //todo: throw error userNot found
           } else {
               //todo throw exception
           }
        }
            
        else if(status.equals("success"))
            if(borrowerId != null){
                User borrower = userDao.getUser(borrowerId);
                if(borrower != null)
                    bookDao.updateBookStatusOnSuccess(user, book, borrower, borrowRequest.getAdditionalMessage());

                //todo: throw error userNot found
            } else {
                //todo throw borrower not found
            }
        return Response.ok().build();
    }
    
    @GET
    @Path("search")
    public Response search(@QueryParam("q") final String queryString) {
        SearchResult searchResult = bookDao.search(queryString);
        return Response.ok().entity(searchResult).build();
    }
    
}
