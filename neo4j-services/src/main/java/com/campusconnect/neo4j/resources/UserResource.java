package com.campusconnect.neo4j.resources;

import com.campusconnect.neo4j.da.FBDao;
import com.campusconnect.neo4j.da.GoodreadsDao;
import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.exceptions.InvalidInputDataException;
import com.campusconnect.neo4j.types.*;
import com.campusconnect.neo4j.util.Validator;

import static com.campusconnect.neo4j.util.ErrorCodes.*;

import org.apache.commons.beanutils.BeanUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sn1 on 1/22/15.
 */
@Path("users")
@Consumes("application/json")
@Produces("application/json")
public class UserResource {
    private UserDao userDao;
    private BookDao bookDao;
    private FBDao fbDao;
    private GoodreadsDao goodreadsDao;

    public UserResource() {
    
    }

    public UserResource(UserDao userDao, BookDao bookDao, FBDao fbDao, GoodreadsDao goodreadsDao) {
        this.userDao = userDao;
        this.bookDao = bookDao;
        this.fbDao = fbDao;
        this.goodreadsDao = goodreadsDao;
    }

    @POST
    public Response createUser(@QueryParam("accessToken") final String accessToken, final User user) throws URISyntaxException {
    	StringBuffer validateUserDataMessage = Validator.validateUserObject(user);
    	
    	if(null!=validateUserDataMessage)
    	{
    		throw new InvalidInputDataException(INVALId_ARGMENTS,validateUserDataMessage.toString());
    	}
    	addPropertiesForCreate(user);
        User createdUser = userDao.createUser(user, accessToken);
        return Response.created(new URI("/user/" + createdUser.getId())).entity(createdUser).build();
    }
    
    
    @PUT
    @Path("{userId}/fields")
    public Response updateUserFields(@PathParam("userId") final String userId, Fields fields) throws Exception {
        //todo: validate passed fields are valid or not
        User user = userDao.getUser(userId);
        setUpdatedFields(user, fields);
        user.setLastModifiedDate(System.currentTimeMillis());
        User updatedUser = userDao.updateUser(userId, user);
        checkWhetherSynchIsNeeded(updatedUser, fields);
        return Response.ok().entity(updatedUser).build();
    }

    private void checkWhetherSynchIsNeeded(User user, Fields fields) {
        for (Field field : fields.getFields()) {
            if(field.getName().contains("goodreadsAccessTokenSecret")) {
                goodreadsDao.getAndSaveBooksFromGoodreads(user.getId(), user.getGoodreadsId(), user.getGoodreadsAccessToken(), user.getGoodreadsAccessTokenSecret());
            }
            else if(field.getName().contains("fbId")) {
                //todo kick off fb stuff
            }
        }
    }

    private void setUpdatedFields(User user, Fields fields) throws Exception {
        for (Field field : fields.getFields()){
            BeanUtils.setProperty(user, field.getName(), field.getValue());
        }
    }

    @GET
    @Path("{userId}")
    public Response getUser(@PathParam("userId") final String userId) {
        User user = userDao.getUser(userId);
        return Response.ok().entity(user).build();
    }
    
    @GET
    @Path("fbId/{fbId}")
    public Response getUserByFbId(@PathParam("fbId") final String fbId) {
        User user = userDao.getUserByFbId(fbId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new Neo4jErrorResponse("NOT_FOUND", "client", "User is nto found with fbId : " + fbId)).build();
        }
        return Response.ok().entity(user).build();
    }

    @PUT
    @Path("{userId}")
    public Response updateUser(@PathParam("userId") final String userId, User user) {
        user.setLastModifiedDate(System.currentTimeMillis());
        User updatedUser = userDao.updateUser(userId, user);
        return Response.ok().entity(updatedUser).build();
    }

    @PUT
    @Path("{userId}/addresses")
    public Response addAddress(@PathParam("userId") final String userId, final Address address){
        return null;
    }
    
    @POST
    @Path("{userId}/books/{bookId}/own")
    public Response addBook(@PathParam("userId") final String userId, 
                            @PathParam("bookId") final String bookId,
                            @QueryParam("status") @DefaultValue("none") final String status) throws Exception {
        
        User user = userDao.getUser(userId);
        Book book = bookDao.getBook(bookId);
        long now = System.currentTimeMillis();
        bookDao.addBookToUser(new OwnsRelationship(user, book, now, status, now));
        return Response.ok().build();
    }
    
    @POST
    @Path("{userId}/books/{bookId}/wish")
    public Response addBookToWishList(@PathParam("userId") final String userId,
                            @PathParam("bookId") final String bookId,
                            @QueryParam("status") @DefaultValue("none") final String status) throws Exception {
        
        User user = userDao.getUser(userId);
        Book book = bookDao.getBook(bookId);
        long now = System.currentTimeMillis();
        bookDao.addWishBookToUser(new WishListRelationship(user, book, status, now, now));
        return Response.ok().build();
    }
    
    @PUT
    @Path("{userId}/books/{bookId}/own")
    public Response changeBookStatus(@PathParam("userId") final String userId, 
                            @PathParam("bookId") final String bookId,
                            @QueryParam("status") @DefaultValue("none") final String status) throws Exception {
        
        User user = userDao.getUser(userId);
        Book book = bookDao.getBook(bookId);
        
        bookDao.updateOwnedBookStatus(user, book, status);
        return Response.ok().build();
    }
    
    @PUT
    @Path("{userId}/books/wishlist/rec")
    public Response synchWishListRec(@PathParam("userId") final String userId) {
        userDao.synchWishListRec(userId);
        return Response.ok().build();
    }
    
    @GET
    @Path("{userId}/books")
    public Response getBooks(@PathParam("userId") final String userId, @QueryParam("filter") String filter) throws Exception {
        if(filter == null){
            throw new Exception("filer is null");
        }
        switch (filter) {
            case "owned": {
                final List<OwnedBook> ownedBooks = userDao.getOwnedBooks(userId);
                OwnedBooksPage ownedBooksPage = new OwnedBooksPage(0, ownedBooks.size(), ownedBooks);
                return Response.ok().entity(ownedBooksPage).build();
            }
            case "available": {
                final List<OwnedBook> ownedBooks = userDao.getAvailableBooks(userId);
                OwnedBooksPage ownedBooksPage = new OwnedBooksPage(0, ownedBooks.size(), ownedBooks);
                return Response.ok().entity(ownedBooksPage).build();
            }
            case "lent": {
                final List<OwnedBook> ownedBooks = userDao.getLentBooks(userId);
                OwnedBooksPage ownedBooksPage = new OwnedBooksPage(0, ownedBooks.size(), ownedBooks);
                return Response.ok().entity(ownedBooksPage).build();
            }
            case "borrowed":
                final List<BorrowedBook> borrowedBooks = userDao.getBorrowedBooks(userId);
                BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage(0, borrowedBooks.size(), borrowedBooks);
                return Response.ok().entity(borrowedBooksPage).build();
        }
        return Response.ok().build();
    }
    
    @GET
    @Path("{userId}/followers")
    public Response getFollowers(@PathParam("userId") final String userId)
    {
    	final List<User> followers = userDao.getFollowers(userId);
    	UsersPage usersPage = new UsersPage(0,followers.size(),followers);
    	return Response.ok().entity(usersPage).build();
    }
    
    @GET
    @Path("{userId}/following")
    public Response getFollowing(@PathParam("userId") final String userId)
    {
    	final List<User> following = userDao.getFollowing(userId);
    	UsersPage usersPage = new UsersPage(0, following.size(), following);
    	return Response.ok().entity(usersPage).build();
    }

    @POST
    @Path("{userId}/follow/{followUserId}")
    public Response follow(@PathParam("userId") final String userId, @PathParam("followUserId") final String followUserId)
    {
    	
		return null;
    	
    }
    
    @PUT
    @Path("{userId}/favourites")
    public Response setFavourites(@PathParam("userId") final String userId,final Favourites favourites)
    {
    	 User user = userDao.getUser(userId);
    	 user.setFavorites(favourites.getFavourites());
    	 userDao.updateUser(userId, user);
         return Response.ok().build();
    }
    private void addPropertiesForCreate(User user) {
        final long createdDate = System.currentTimeMillis();
        user.setCreatedDate(createdDate);
        user.setLastModifiedDate(createdDate);
    }
    
    private Map<String, Object> getHeadersForAddingBook(String status) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("createdDate", System.currentTimeMillis());
        properties.put("status", status);
        properties.put("lastModifiedDate", System.currentTimeMillis());
        return properties;
    }
    private Map<String, Object> getHeadersForStatusUpdate(String status) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("status", status);
        properties.put("lastModifiedDate", System.currentTimeMillis());
        return properties;
    }
    
    private Map<String, Object> getRequiredHeadersForAccess(String createdBy, String role) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("createdBy", createdBy);
        properties.put("createdDate", System.currentTimeMillis());
        properties.put("role", role);
        return properties;
    }

    private Map<String, Object> getRequiredHeadersForAddressLink(String addressType) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("createdDate", System.currentTimeMillis());
        properties.put("type", addressType);
        return properties;
    }

    private Address addAddress(User user, Address address){
//        Address createdAddress = addressDao.createAddress(address);
//        addressDao.linkAddressToUser(user, address, getRequiredHeadersForAddressLink(address.getAddressType()));
//        return createdAddress;
        return null;
    }

    private List<Address> addAddressesToUser(User user, List<Address> addresses) {
        if(addresses != null){
            List<Address> createdAddresses = new ArrayList<>();
            for (Address address : addresses) {
                createdAddresses.add(addAddress(user, address));
            }
            return createdAddresses;
        }
        return null;
    }

    //    public void approveCollegeAccess(String userId, String collegeId, String createdBy, String role){
//        User user = userDao.getUser(userId);
//        College college = collegeDao.getCollege(collegeId);
//        userDao.addCollege(college, user, getRequiredHeadersForAccess(createdBy, role));
//    }

    //    public void addCollegeAccess(String userId, String groupId, String role) {
//    }
}
