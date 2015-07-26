package com.campusconnect.neo4j.resources;

import com.campusconnect.neo4j.da.FBDao;
import com.campusconnect.neo4j.da.GoodreadsDao;
import com.campusconnect.neo4j.da.GroupDao;
import com.campusconnect.neo4j.da.iface.*;
import com.campusconnect.neo4j.exceptions.InvalidDataException;
import com.campusconnect.neo4j.mappers.Neo4jToWebMapper;
import com.campusconnect.neo4j.types.common.GoodreadsAuthStatus;
import com.campusconnect.neo4j.types.neo4j.Address;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.Group;
import com.campusconnect.neo4j.types.neo4j.Reminder;
import com.campusconnect.neo4j.types.neo4j.*;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.web.*;
import com.campusconnect.neo4j.util.Constants;
import com.campusconnect.neo4j.util.StringUtils;
import com.campusconnect.neo4j.util.Validator;
import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.campusconnect.neo4j.mappers.Neo4jToWebMapper.*;
import static com.campusconnect.neo4j.mappers.WebToNeo4jMapper.mapAddressWebToNeo4j;
import static com.campusconnect.neo4j.mappers.WebToNeo4jMapper.mapUserWebToNeo4j;
import static com.campusconnect.neo4j.util.Constants.*;
import static com.campusconnect.neo4j.util.ErrorCodes.INVALID_ARGUMENTS;

/**
 * Created by sn1 on 1/22/15.
 */
@Path("users")
@Consumes("application/json")
@Produces("application/json")
public class UserResource {

    private AddressDao addressDao;
    private BookDao bookDao;
    private FBDao fbDao;
    private GoodreadsDao goodreadsDao;
    private UserDao userDao;
    private ReminderDao reminderDao;
    private AuditEventDao auditEventDao;
    private NotificationDao notificationDao;
    private GroupDao groupDao;

    public UserResource() {
    }

    public UserResource(UserDao userDao, BookDao bookDao, FBDao fbDao, GoodreadsDao goodreadsDao, AddressDao addressDao, ReminderDao reminderDao, AuditEventDao auditEventDao, NotificationDao notificationDao, GroupDao groupDao) {

        this.userDao = userDao;
        this.bookDao = bookDao;
        this.fbDao = fbDao;
        this.goodreadsDao = goodreadsDao;
        this.addressDao = addressDao;
        this.reminderDao = reminderDao;
        this.auditEventDao = auditEventDao;
        this.notificationDao = notificationDao;
        this.groupDao = groupDao;

    }

    @POST
    public Response createUser(@QueryParam(ACCESS_TOKEN_QPARAM) final String accessToken,
                               final com.campusconnect.neo4j.types.web.User userPayload) throws URISyntaxException {

        StringBuffer validateUserDataMessage = Validator.validateUserObject(userPayload);

        if (null != validateUserDataMessage) {
            throw new InvalidDataException(INVALID_ARGUMENTS, validateUserDataMessage.toString());
        }

        User user = mapUserWebToNeo4j(userPayload);

        if (user.getEmail() != null) {
            User existingUser = userDao.getUserByEmail(user.getEmail());
            if (null != existingUser) {
                if (existingUser.getFbId() == null && user.getFbId() != null) {
                    existingUser.setFbId(user.getFbId());
                    existingUser = userDao.updateUser(existingUser.getId(), existingUser, true);
                }
                if (existingUser.getGoogleId() == null && user.getGoogleId() != null) {
                    existingUser.setGoogleId(user.getGoogleId());
                    existingUser = userDao.updateUser(existingUser.getId(), existingUser, true);
                }
                com.campusconnect.neo4j.types.web.User returnUser = mapUserNeo4jToWeb(existingUser);
                return Response.created(new URI("/users/" + returnUser.getId())).entity(returnUser).build();
            }
        }

        if (user.getFbId() != null) {
            User existingUser = userDao.getUserByFbId(user.getFbId());
            if (null != existingUser) {
                com.campusconnect.neo4j.types.web.User returnUser = mapUserNeo4jToWeb(existingUser);
                return Response.created(new URI("/users/" + returnUser.getId())).entity(returnUser).build();
            }
        }

        if (user.getGoogleId() != null) {
            User existingUser = userDao.getUserByGoogleId(user.getGoogleId());
            if (null != existingUser) {
                com.campusconnect.neo4j.types.web.User returnUser = mapUserNeo4jToWeb(existingUser);
                return Response.created(new URI("/users/" + returnUser.getId())).entity(returnUser).build();
            }
        }

        addPropertiesForCreate(user);
        User createdUser = userDao.createUser(user, accessToken);
        com.campusconnect.neo4j.types.web.User returnUser = mapUserNeo4jToWeb(createdUser);
        return Response.created(new URI("/users/" + returnUser.getId())).entity(returnUser).build();

    }

    @PUT
    @Path("{userId}/fields")
    public Response updateUserFields(@PathParam("userId") final String userId,
                                     Fields fields) throws Exception {
        //TODO: validate passed fields are valid or not
    	Validator.validateUserFields(fields);
    	
        User user = userDao.getUser(userId);
        setUpdatedFields(user, fields);
        user.setLastModifiedDate(System.currentTimeMillis());
        User updatedUser = userDao.updateUser(userId, user, true);
        checkWhetherSynchIsNeeded(updatedUser, fields);
        com.campusconnect.neo4j.types.web.User returnUser = mapUserNeo4jToWeb(updatedUser);
        return Response.ok().entity(returnUser).build();
    }

    @POST
    @Path("{userId}/goodreads/synch")
    public Response synchGoodreads(@PathParam("userId") final String userId) throws Exception {
        initiateGoodreadsSynch(userDao.getUser(userId));
        return Response.ok().build();
    }

    private void checkWhetherSynchIsNeeded(User user, Fields fields) {
        for (Field field : fields.getFields()) {
            if (field.getName().contains("goodreadsAccessTokenSecret")) {
                initiateGoodreadsSynch(user);
            } else if (field.getName().contains("fbId")) {
                //todo kick off fb stuff
            }
        }
    }

    private void initiateGoodreadsSynch(User user) {
        updateUserGoodReadsSynchToInprogress(user);
        goodreadsDao.getAndSaveBooksFromGoodreads(user.getId(), user.getGoodreadsId(), user.getGoodreadsAccessToken(), user.getGoodreadsAccessTokenSecret());
        goodreadsDao.replaceGoodreadsRecWithUserId(user.getId(), Integer.parseInt(user.getGoodreadsId()), user.getProfileImageUrl());
    }

    private void updateUserGoodReadsSynchToInprogress(User user) {
        user.setGoodReadsSynchStatus(IN_PROGRESS_GREADS_STATUS);
        userDao.updateUser(user.getId(), user, false);
    }

    private void setUpdatedFields(User user, Fields fields) throws Exception {
    	
        for (Field field : fields.getFields()) {
            BeanUtils.setProperty(user, field.getName(), field.getValue());
        }
        }

    

    @GET
    @Path("{userId}")
    public Response getUser(@PathParam("userId") final String userId) {
        User user = userDao.getUser(userId);
        com.campusconnect.neo4j.types.web.User returnUser = mapUserNeo4jToWeb(user);
        return Response.ok().entity(returnUser).build();
    }

    @GET
    @Path("fbId/{fbId}")
    public Response getUserByFbId(@PathParam("fbId") final String fbId) {
        User user = userDao.getUserByFbId(fbId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Neo4jErrorResponse("NOT_FOUND", "client", "User is nto found with fbId : " + fbId)).build();
        }
        com.campusconnect.neo4j.types.web.User returnUser = mapUserNeo4jToWeb(user);
        return Response.ok().entity(returnUser).build();
    }

    @PUT
    @Path("{userId}")
    public Response updateUser(@PathParam("userId") final String userId, com.campusconnect.neo4j.types.web.User userPayload) {
        User user = mapUserWebToNeo4j(userPayload);
        user.setLastModifiedDate(System.currentTimeMillis());
        User updatedUser = userDao.updateUser(userId, user, true);
        com.campusconnect.neo4j.types.web.User returnUser = mapUserNeo4jToWeb(updatedUser);
        return Response.ok().entity(returnUser).build();
    }

    @GET
    @Path("{userId}/addresses/{addressId}")
    public Response getAddress(@PathParam("userId") String userId, @PathParam("addressId") String addressId) {
        //TODO: validate user and address existing
        Address address = addressDao.getAddress(addressId);
        com.campusconnect.neo4j.types.web.Address returnAddress = mapAddressNeo4jToWeb(address);
        return Response.ok().entity(returnAddress).build();
    }

    @PUT
    @Path("{userId}/books/wishlist/rec")
    public Response synchWishListRec(@PathParam("userId") final String userId) {
        userDao.synchWishListRec(userId);
        return Response.ok().build();
    }


    @GET
    @Path("{userId}/addresses")
    public Response getAddress(@PathParam("userId") final String userId) {
        List<Address> addresses = addressDao.getAddresses(userId);
        List<com.campusconnect.neo4j.types.web.Address> returnAddress = new ArrayList<>(addresses.size());
        for (Address address : addresses) {
            returnAddress.add(mapAddressNeo4jToWeb(address));
        }
        AddressesPage addressesPage = new AddressesPage(returnAddress.size(), 0, returnAddress);
        return Response.ok().entity(addressesPage).build();
    }

    @POST
    @Path("{userId}/addresses")
    public Response addAddress(@PathParam("userId") String userId, com.campusconnect.neo4j.types.web.Address addressPayload) {
        Address address = mapAddressWebToNeo4j(addressPayload);
        User user = userDao.getUser(userId);
        //create new address
        Address createdAddress = addressDao.createAddress(address, userId);
        //Link to user
        userDao.addAddressToUser(createdAddress, user);

        com.campusconnect.neo4j.types.web.Address returnAddress = mapAddressNeo4jToWeb(createdAddress);
        return Response.ok().entity(returnAddress).build();
    }

    @PUT
    @Path("{userId}/addresses/{addressId}")
    public Response updateAddress(@PathParam("userId") String userId, @PathParam("addressId") String addressId, com.campusconnect.neo4j.types.web.Address addressPayload) {
        //TODO: validate the address before updating
        Address address = mapAddressWebToNeo4j(addressPayload);
        Address updatedAddress = addressDao.updateAddress(address, userId);
        com.campusconnect.neo4j.types.web.Address returnAddress = mapAddressNeo4jToWeb(updatedAddress);
        return Response.ok().entity(returnAddress).build();
    }

    @DELETE
    @Path("{userId}/addresses/{addressId}")
    public Response deleteAddress(@PathParam("userId") String userId, @PathParam("addressId") String addressId) {
        addressDao.deleteAddress(addressId, userId);
        return Response.ok().build();
    }


    @GET
    @Path("{userId}/books")
    public Response getBooks(@PathParam("userId") final String userId,
                             @QueryParam(FILTER_QPARAM) String filter,
                             @QueryParam(LOGGED_IN_USER_QPARAM) String loggedInUser) throws Exception {
        if (filter == null) {
            throw new Exception("filer is null");
        }
        switch (filter.toUpperCase()) {
            case OWNS_RELATION: {
                final List<OwnedBook> ownedBooks = bookDao.getOwnedBooks(userId);
                OwnedBooksPage ownedBooksPage = new OwnedBooksPage(0, ownedBooks.size(), ownedBooks);
                return Response.ok().entity(ownedBooksPage).build();
            }
            case READ_RELATION: {
                final List<Book> readBooks = bookDao.getReadBooks(userId);
                List<com.campusconnect.neo4j.types.web.Book> returnBooks = getWebBooks(readBooks);
                BooksPage booksPage = new BooksPage(0, returnBooks.size(), returnBooks);
                return Response.ok().entity(booksPage).build();
            }
            case AVAILABLE: {
                final List<OwnedBook> ownedBooks = bookDao.getAvailableBooks(userId);
                OwnedBooksPage ownedBooksPage = new OwnedBooksPage(0, ownedBooks.size(), ownedBooks);
                return Response.ok().entity(ownedBooksPage).build();
            }
            case LENT: {
                final List<OwnedBook> ownedBooks = bookDao.getLentBooks(userId);
                OwnedBooksPage ownedBooksPage = new OwnedBooksPage(0, ownedBooks.size(), ownedBooks);
                return Response.ok().entity(ownedBooksPage).build();
            }
            case BORROWED:
                final List<BorrowedBook> borrowedBooks = bookDao.getBorrowedBooks(userId);
                BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage(0, borrowedBooks.size(), borrowedBooks);
                return Response.ok().entity(borrowedBooksPage).build();
            case WISHLIST_RELATION:
                List<com.campusconnect.neo4j.types.web.Book> wishListBooks = getWebBooks(bookDao.getWishlistBooksWithDetails(userId, loggedInUser));
                BooksPage wishListBooksPage = new BooksPage(wishListBooks.size(), 0, wishListBooks);
                return Response.ok().entity(wishListBooksPage).build();
            case CURRENTLY_READING_RELATION:
                List<CurrentlyReadingBook> currentlyReadingBooks = bookDao.getCurrentlyReadingBook(userId);
                CurrentlyReadingBooksPage currentlyReadingBooksPage = new CurrentlyReadingBooksPage(0, currentlyReadingBooks.size(), currentlyReadingBooks);
                return Response.ok().entity(currentlyReadingBooksPage).build();
            case WISHLIST_WITH_REC:
                List<com.campusconnect.neo4j.types.web.Book> wishListBooksWithRec = getWebBooks(bookDao.getWishListBooksWithMin1Rec(userId));
                BooksPage wishListBooksRecPage = new BooksPage(wishListBooksWithRec.size(), 0, wishListBooksWithRec);
                return Response.ok().entity(wishListBooksRecPage).build();
            case ALL:

                List<Book> allBooks = bookDao.getAllUserBooks(userId, loggedInUser);
                AllBooks resultBooks = new AllBooks();
                for (Book book : allBooks) {
                    com.campusconnect.neo4j.types.web.Book webBook = mapBookNeo4jToWeb(book);
                    switch (book.getBookType()) {
                        case OWNS_RELATION:
                            resultBooks.getOwnedBooks().add(webBook);
                            break;
                        case BORROWED_RELATION:
                            resultBooks.getBorrowedBooks().add(webBook);
                            break;
                        case WISHLIST_RELATION:
                            resultBooks.getWishlistBooks().add(webBook);
                            break;
                        case READ_RELATION:
                            resultBooks.getReadBooks().add(webBook);
                            break;
                        case CURRENTLY_READING_RELATION:
                            resultBooks.getCurrentlyReadingBooks().add(webBook);
                    }
                }
                return Response.ok().entity(resultBooks).build();
        }
        return Response.ok().build();
    }

    private List<com.campusconnect.neo4j.types.web.Book> getWebBooks(List<Book> neo4jBooks) {
        List<com.campusconnect.neo4j.types.web.Book> returnBooks = new ArrayList<>();
        for (Book book : neo4jBooks)
            returnBooks.add(Neo4jToWebMapper.mapBookNeo4jToWeb(book));
        return returnBooks;
    }

    @GET
    @Path("{userId}/followers")
    public Response getFollowers(@PathParam("userId") final String userId) {
        final List<User> followers = userDao.getFollowers(userId);
        List<com.campusconnect.neo4j.types.web.User> returnUsers = new ArrayList<>(followers.size());
        for (User user : followers) {
            returnUsers.add(Neo4jToWebMapper.mapUserNeo4jToWeb(user));
        }
        UsersPage usersPage = new UsersPage(0, returnUsers.size(), returnUsers);
        return Response.ok().entity(usersPage).build();
    }

    @GET
    @Path("{userId}/friends/pending")
    public Response getPendingFriends(@PathParam("userId") final String userId) {
        final List<User> pendingFriends = userDao.getPendingFriendReq(userId);
        List<com.campusconnect.neo4j.types.web.User> returnUsers = new ArrayList<>(pendingFriends.size());
        for (User user : pendingFriends) {
            returnUsers.add(Neo4jToWebMapper.mapUserNeo4jToWeb(user));
        }
        UsersPage usersPage = new UsersPage(0, returnUsers.size(), returnUsers);
        return Response.ok().entity(usersPage).build();
    }

    @GET
    @Path("{userId}/following")
    public Response getFollowing(@PathParam("userId") final String userId) {
        final List<User> following = userDao.getFollowing(userId);
        List<com.campusconnect.neo4j.types.web.User> returnUsers = new ArrayList<>(following.size());
        for (User user : following) {
            returnUsers.add(Neo4jToWebMapper.mapUserNeo4jToWeb(user));
        }
        UsersPage usersPage = new UsersPage(0, returnUsers.size(), returnUsers);
        return Response.ok().entity(usersPage).build();
    }

    @POST
    @Path("{userId}/follow/{followUserId}")
    public Response follow(@PathParam("userId") final String userId, @PathParam("followUserId") final String followUserId) {
        userDao.createFollowingRelation(userDao.getUser(userId), userDao.getUser(followUserId));
        return Response.ok().build();
    }

    @POST
    @Path("{userId}/friend/{friendUserId}")
    public Response addFriend(@PathParam("userId") final String userId, @PathParam("friendUserId") final String friendUserId) {
        userDao.createFriendRelationWithPending(userDao.getUser(userId), userDao.getUser(friendUserId));
        return Response.ok().build();
    }

    @PUT
    @Path("{userId}/friend/{friendUserId}")
    public Response confirmFriend(@PathParam("userId") final String userId, @PathParam("friendUserId") final String friendUserId, @QueryParam(STATUS_QPARAM) final String status) {
        if (status.toLowerCase().equals("agreed")) {
            userDao.confirmFriendRelation(userDao.getUser(userId), userDao.getUser(friendUserId));
        } else if (status.toLowerCase().equals("cancel")) {
            userDao.deleteFriendRequest(userId, friendUserId, Constants.FRIEND_REQUEST_CANCEL_DELETE);
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("{userId}/friend/{friendUserId}")
    public Response unFriend(@PathParam("userId") final String userId, @PathParam("friendUserId") final String friendUserId) {
        userDao.deleteFriendRequest(userId, friendUserId, Constants.FRIEND_UNFRIEND_DELETE);
        return Response.ok().build();
    }

    private void addPropertiesForCreate(User user) {
        final Long createdDate = System.currentTimeMillis();
        user.setCreatedDate(createdDate);
        user.setLastModifiedDate(createdDate);
        user.setGoodreadsAuthStatus(GoodreadsAuthStatus.NONE.toString());
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


    //    public void approveCollegeAccess(String userId, String collegeId, String createdBy, String role){
//        User user = userDao.getUser(userId);
//        College college = collegeDao.getCollege(collegeId);
//        userDao.addCollege(college, user, getRequiredHeadersForAccess(createdBy, role));
//    }

    //    public void addCollegeAccess(String userId, String groupId, String role) {
//    }


    @POST
    @Path("{userId}/reminders")
    public Response createReminder(Reminder reminder, @PathParam("userId") final String userId, @QueryParam(REMINDER_ABOUT_QPARM) final String reminderAbout, @QueryParam(CREATED_BY_QPARAM) final String createdBy) {
        setReminderCreateProperties(reminder);
        Reminder createdReminder = reminderDao.createReminder(reminder);
        User reminderForUser = userDao.getUser(userId);
        Long currentTime = System.currentTimeMillis();
        ReminderRelationShip reminderRelationShipFor = new ReminderRelationShip(
                createdBy, currentTime, reminderForUser, currentTime,
                reminderAbout, reminder, Constants.RECEIVED_REMINDER_TYPE);
        ReminderRelationShip reminderRelationShipBy = new ReminderRelationShip(reminderForUser.toString(), currentTime, userDao.getUser(createdBy), currentTime,
                reminderAbout, reminder, Constants.SENT_REMINDER_TYPE);
        userDao.setReminder(reminderRelationShipFor, true);
        userDao.setReminder(reminderRelationShipBy, false);
        return Response.created(null).entity(createdReminder).build();
    }

//    @PUT
//    @Path("{userId}/reminders/{reminderId}")
//    public Response updateReminder(Reminder reminder, @PathParam("userId") final String userId, @PathParam("reminderId") final String reminderId) {
//        Reminder updatedReminder = reminderDao.updateReminder(reminderId, reminder,userId);
//        return Response.ok().entity(updatedReminder).build();
//    }

    @DELETE
    @Path("{userId}/reminders/{reminderId}")
    public Response deleteReminder(@PathParam("userId") final String userId, @PathParam("reminderId") final String reminderId) {
        reminderDao.deleteReminder(reminderId, userId);
        //TODO : Should event/Notification be added ??
        return Response.ok().build();
    }

    @GET
    @Path("{userId}/reminders/{reminderId}")
    public Response getSingleReminder(@PathParam("userId") final String userId, @PathParam("reminderId") final String reminderId) {
        Reminder reminder = reminderDao.getReminder(reminderId);
        return Response.ok().entity(reminder).build();
    }


    @GET
    @Path("{googleId}/googleId")
    public Response getUserByGoogleId(@PathParam("googleId") final String googleId) {
        User user = userDao.getUserByGoogleId(googleId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new Neo4jErrorResponse("NOT_FOUND", "client", "User is nto found with googleId : " + googleId)).build();
        }
        return Response.ok().entity(user).build();

    }


    @GET
    @Path("{userId}/reminders")
    public Response getReminders(@PathParam("userId") final String userId, @QueryParam("filter") @DefaultValue("all") final String filter) {
        final List<Reminder> reminders = reminderDao.getReminders(userId, filter);
        ReminderPage reminderPage = new ReminderPage(0, reminders.size(), reminders);
        return Response.ok().entity(reminderPage).build();
    }


    @GET
    @Path("{userId}/timeline/events")
    public Response getActivityEvents(@PathParam("userId") final String userId) {
        List<Event> events = auditEventDao.getEvents(userId);
        EventPage eventPage = new EventPage(0, events.size(), events);
        return Response.ok().entity(eventPage).build();
    }

    @GET
    @Path("{userId}/timeline/feed")
    public Response getFeedForTheUser(@PathParam("userId") final String userId) throws IOException {
        List<Event> events = auditEventDao.getFeedEvents(userId);
        EventPage eventPage = new EventPage(0, events.size(), events);
        return Response.ok().entity(eventPage).build();
    }

    @GET
    @Path("{userId}/notifications")
    public Response getNotifications(@PathParam("userId") final String userId,
                                     @QueryParam(FILTER_QPARAM) @DefaultValue(FRESH_NOTIFICATION_TYPE) final String filter) {
        //AuditEvent auditEvent = auditEventDao.getEvents(userId);
        List<Notification> notifications = notificationDao.getNotifications(userId, filter);
        NotificationPage notificationPage = new NotificationPage(0, notifications.size(), notifications);
        return Response.ok().entity(notificationPage).build();
    }

    @GET
    @Path("{userId}/groups")
    public Response getGroups(@PathParam("userId") final String userId) {
        List<com.campusconnect.neo4j.types.neo4j.Group> groups = groupDao.getGroups(userId);
        List<com.campusconnect.neo4j.types.web.Group> returnGroups = new ArrayList<>(groups.size());
        for (Group grp : groups) {
            returnGroups.add(Neo4jToWebMapper.mapGroupNeo4jToWeb(grp));
        }
        GroupPage groupPage = new GroupPage(returnGroups, 0, returnGroups.size());
        return Response.ok().entity(groupPage).build();
    }

    @DELETE
    @Path("{userId}/notifications")
    public Response moveNotifications(@PathParam("userId") final String userId) {
        notificationDao.moveNotification(userId);
        return Response.ok().build();
    }

    private void setReminderCreateProperties(Reminder reminder) {
        Long currentTime = System.currentTimeMillis();
        reminder.setCreatedDate(currentTime);
        reminder.setLastModifiedTime(currentTime);
    }

    @GET
    @Path("{userId}/search")
    public Response searchUsers(@PathParam("userId") String userId, @QueryParam(SEARCH_QPARAM) String searchString) {
        List<User> users = userDao.search(searchString, userId);
        List<com.campusconnect.neo4j.types.web.User> returnUsers = new ArrayList<>(users.size());
        for (User user : users) {
            returnUsers.add(Neo4jToWebMapper.mapUserNeo4jToWeb(user));
        }
        return Response.ok().entity(new UsersPage(0, returnUsers.size(), returnUsers)).build();
    }

    @GET
    @Path("{userId}/search/friends")
    public Response searchFriends(@PathParam("userId") String userId, @QueryParam(SEARCH_QPARAM) String searchString) {
        List<User> users = userDao.searchFriends(userId, searchString);
        List<com.campusconnect.neo4j.types.web.User> returnUsers = new ArrayList<>(users.size());
        for (User user : users) {
            returnUsers.add(Neo4jToWebMapper.mapUserNeo4jToWeb(user));
        }
        return Response.ok().entity(new UsersPage(0, returnUsers.size(), returnUsers)).build();
    }

    @GET
    @Path("random")
    public Response getRandomUsers(@QueryParam("size") @DefaultValue("10") final String size, @QueryParam("currentUserId") String currentUser) {
        List<User> userList = userDao.getRandomUsers(Integer.parseInt(size), currentUser);
        List<com.campusconnect.neo4j.types.web.User> returnUsers = new ArrayList<>(userList.size());
        for (User user : userList) {
            returnUsers.add(Neo4jToWebMapper.mapUserNeo4jToWeb(user));
        }
        return Response.ok().entity(new UsersPage(0, returnUsers.size(), returnUsers)).build();
    }

    @GET
    @Path("{userId}/friends/rec")
    public Response getFriendsRec(@PathParam("userId") String userId, @QueryParam(SIZE_QPARAM) @DefaultValue("10") final String size, @QueryParam(INCLUDE_FRIENDS_QPARAM) @DefaultValue("false") final String includeFriends) {
        //validations
        boolean isIncludeFriends = StringUtils.getBoolean(includeFriends);
        Integer sizeValue = StringUtils.getIntegerValue(size, 10);
        List<FriendRecommendation> friendRecommendations;
        if (isIncludeFriends) {
            friendRecommendations = userDao.getFriendsRecWithFriends(userId, size);
        } else {
            friendRecommendations = userDao.getFriendsRecWithCount(userId, size);
        }
        return Response.ok().entity(new FriendRecommendationsPage(friendRecommendations, friendRecommendations.size(), 0)).build();
    }

    @GET
    @Path("{userId}/friends")
    public Response getFriends(@PathParam("userId") String userId, @QueryParam(LOGGED_IN_USER_QPARAM) String currentUser) {
        List<User> friends = userDao.getFriends(userId, currentUser);
        List<com.campusconnect.neo4j.types.web.User> returnFriends = new ArrayList<>();
        for (User friend : friends) {
            returnFriends.add(Neo4jToWebMapper.mapUserNeo4jToWeb(friend));
        }
        FriendsPage friendsPage = new FriendsPage(returnFriends.size(), 0, returnFriends);

        Friends allFriends = new Friends();
        allFriends.setFriends(friendsPage);

        if (currentUser != null && !currentUser.equals(userId)) {
            List<User> mutualFriends = userDao.getMutualFriends(currentUser, userId);
            List<com.campusconnect.neo4j.types.web.User> returnMutualFriends = new ArrayList<>();
            for (User friend : mutualFriends) {
                returnMutualFriends.add(Neo4jToWebMapper.mapUserNeo4jToWeb(friend));
            }
            FriendsPage mutualFriendsPage = new FriendsPage(returnMutualFriends.size(), 0, returnMutualFriends);
            allFriends.setMutualFriends(mutualFriendsPage);
        }
        return Response.ok().entity(allFriends).build();
    }


    @DELETE
    @Path("{userId}")
    public Response deleteUser(@PathParam("userId") String userId) {
        userDao.deleteUser(userId);
        return Response.ok().build();
    }
}
