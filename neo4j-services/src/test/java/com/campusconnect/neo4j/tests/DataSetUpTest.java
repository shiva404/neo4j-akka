package com.campusconnect.neo4j.tests;

import com.campusconnect.neo4j.tests.base.DataBrewer;
import com.campusconnect.neo4j.tests.functional.GroupResourceTest;
import com.campusconnect.neo4j.tests.functional.UserResourceFuncTest;
import com.campusconnect.neo4j.tests.functional.UserResourceTest;
import com.campusconnect.neo4j.types.common.ReminderAbout;
import com.campusconnect.neo4j.types.web.BorrowRequest;
import com.campusconnect.neo4j.types.web.GroupPage;
import com.campusconnect.neo4j.types.web.Reminder;
import com.campusconnect.neo4j.types.web.ReminderPage;
import com.sun.jersey.api.client.ClientResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.campusconnect.neo4j.util.Constants.*;

/**
 * Created by sn1 on 5/7/15.
 */
public class DataSetUpTest extends TestBase {
    @Test
    public void dataSetUp() {

        // create User1
        String userId3 = UserResourceTest.createKnowUserWithGoogleId(
                "Amrutha K S", "hiamrutha.ks@gmail.com", "105609898189858031660");

//        String userId3 = UserResourceTest.createKnowUserWithGoogleId(
//                "Namitha Hugar", "namics08@gmail.comm", "118244923848234911918");

        // createUser2
        String userId2 = UserResourceFuncTest.createUser();

        // createUser3
        String userId1 = UserResourceFuncTest.createUser();

        // createUser4
        String userId4 = UserResourceFuncTest.createUser();

        // create user5
        String userId5 = UserResourceFuncTest.createUser();

        // create user6
        String userId6 = UserResourceFuncTest.createUser();

        // create user7
        String userId7 = UserResourceFuncTest.createUser();

        // create user8
        String userId8 = UserResourceFuncTest.createUser();

        // create user9
        String userId9 = UserResourceFuncTest.createUser();

        // create user10
        String userId10 = UserResourceFuncTest.createUser();

        // create book1
        String book1 = UserResourceFuncTest.createBook();

        // create book2
        String book2 = UserResourceFuncTest.createBook();

        // create book3
        String book3 = UserResourceFuncTest.createBook();

        // create book4
        String book4 = UserResourceFuncTest.createBook();

        // create book5
        String book5 = UserResourceFuncTest.createBook();

        // create book6
        String book6 = UserResourceFuncTest.createBook();

        // user1 friend with user2
        ClientResponse clientResponse = resource.path("users").path(userId1)
                .path("friend").path(userId2).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId1).path("friend")
                .path(userId2).queryParam("status", "agreed")
                .type("application/json").put(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        // user1 friend with user3
        clientResponse = resource.path("users").path(userId1).path("friend")
                .path(userId3).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId1).path("friend")
                .path(userId3).queryParam("status", "agreed")
                .type("application/json").put(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        // user1 follow user8
//        clientResponse = resource.path("users").path(userId1).path("follow")
//                .path(userId8).type("application/json")
//                .post(ClientResponse.class);
//        assert clientResponse.getStatus() == 200;

        // user2 friend with user9
        clientResponse = resource.path("users").path(userId2).path("friend")
                .path(userId9).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId2).path("friend")
                .path(userId9).queryParam("status", "agreed")
                .type("application/json").put(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        // user3 friend with user4
        clientResponse = resource.path("users").path(userId3).path("friend")
                .path(userId4).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId3).path("friend")
                .path(userId4).queryParam("status", "agreed")
                .type("application/json").put(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        // user3 friend with user7
        clientResponse = resource.path("users").path(userId3).path("friend")
                .path(userId7).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId3).path("friend")
                .path(userId7).queryParam("status", "agreed")
                .type("application/json").put(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        // user3 friend with user8
        clientResponse = resource.path("users").path(userId3).path("friend")
                .path(userId8).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId3).path("friend")
                .path(userId8).queryParam("status", "agreed")
                .type("application/json").put(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

//        // user3 follow with user5
//        clientResponse = resource.path("users").path(userId3).path("follow")
//                .path(userId5).type("application/json")
//                .post(ClientResponse.class);
//        assert clientResponse.getStatus() == 200;
//
//        // user3 follow with user6
//        clientResponse = resource.path("users").path(userId3).path("follow")
//                .path(userId6).type("application/json")
//                .post(ClientResponse.class);
//        assert clientResponse.getStatus() == 200;

        // user4 friend with user8
        clientResponse = resource.path("users").path(userId4).path("friend")
                .path(userId8).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId4).path("friend")
                .path(userId8).queryParam("status", "agreed")
                .type("application/json").put(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        // user5 friend with user6
        clientResponse = resource.path("users").path(userId5).path("friend")
                .path(userId6).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId5).path("friend")
                .path(userId6).queryParam("status", "agreed")
                .type("application/json").put(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

//        // user6 follow with user3
//        clientResponse = resource.path("users").path(userId6).path("follow")
//                .path(userId3).type("application/json")
//                .post(ClientResponse.class);
//        assert clientResponse.getStatus() == 200;
//
//        // user7 follow with user2
//        clientResponse = resource.path("users").path(userId7).path("follow")
//                .path(userId2).type("application/json")
//                .post(ClientResponse.class);
//        assert clientResponse.getStatus() == 200;
//
//        // user7 follow with user9
//        clientResponse = resource.path("users").path(userId7).path("follow")
//                .path(userId9).type("application/json")
//                .post(ClientResponse.class);
//        assert clientResponse.getStatus() == 200;

        String groupID1 = GroupResourceTest.createGroup(userId3);
        String groupID2 = GroupResourceTest.createGroup(userId5);
        String groupID3 = GroupResourceTest.createGroup(userId3);

        clientResponse = resource.path("groups").path(groupID1).path("users")
                .path(userId6).queryParam("role", "READ")
                .queryParam("createdBy", userId3).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 201;

        clientResponse = resource.path("groups").path(groupID1).path("users")
                .path(userId7).queryParam("role", "READ")
                .queryParam("createdBy", userId3).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 201;

        clientResponse = resource.path("groups").path(groupID3).path("users")
                .path(userId1).queryParam("role", "READ")
                .queryParam("createdBy", userId3).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 201;

        clientResponse = resource.path("groups").path(groupID3).path("users")
                .path(userId2).queryParam("role", "READ")
                .queryParam("createdBy", userId3).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 201;

        clientResponse = resource.path("groups").path(groupID3).path("users")
                .path(userId7).queryParam("role", "ADMIN")
                .queryParam("createdBy", userId3).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 201;

        clientResponse = resource.path("users").path(userId3).path("groups")
                .type("application/json").get(ClientResponse.class);
        assert clientResponse.getStatus() == 200;
        GroupPage groupPage = clientResponse.getEntity(GroupPage.class);
        // assert groupPage.getSize() == 2;

        //Add books to user
        clientResponse = resource
                .path("books").path(book2)
                .path("users").path(userId1)
                .queryParam(LISTING_TYPE_QPARAM, WISHLIST)
                .queryParam(ID_TYPE_QPARAM, ID)
                .type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book3)
                .path("users").path(userId1)
                .queryParam(LISTING_TYPE_QPARAM, OWNS)
                .queryParam(ID_TYPE_QPARAM, ID)
                .queryParam(STATUS_QPARAM, AVAILABLE)
                .type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book1)
                .path("users").path(userId1)
                .queryParam(LISTING_TYPE_QPARAM, READ)
                .queryParam(ID_TYPE_QPARAM, ID)
                .type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book2)
                .path("users").path(userId2)
                .queryParam(LISTING_TYPE_QPARAM, OWNS)
                .queryParam(STATUS_QPARAM, AVAILABLE)
                .queryParam(ID_TYPE_QPARAM, ID)
                .type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book3)
                .path("users").path(userId2)
                .queryParam(LISTING_TYPE_QPARAM, OWNS)
                .queryParam(STATUS_QPARAM, AVAILABLE)
                .queryParam(ID_TYPE_QPARAM, ID)
                .type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book3)
                .path("users").path(userId3)
                .queryParam(LISTING_TYPE_QPARAM, WISHLIST)
                .queryParam(ID_TYPE_QPARAM, ID)
                .type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book1)
                .path("users").path(userId3)
                .queryParam(LISTING_TYPE_QPARAM, OWNS)
                .queryParam(STATUS_QPARAM, AVAILABLE)
                .queryParam(ID_TYPE_QPARAM, ID).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book4)
                .path("users").path(userId3)
                .queryParam(LISTING_TYPE_QPARAM, OWNS)
                .queryParam(STATUS_QPARAM, AVAILABLE)
                .queryParam(ID_TYPE_QPARAM, ID).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book3)
                .path("users").path(userId4)
                .queryParam(LISTING_TYPE_QPARAM, READ)
                .queryParam(ID_TYPE_QPARAM, ID)
                .type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book5)
                .path("users").path(userId6)
                .queryParam(LISTING_TYPE_QPARAM, OWNS)
                .queryParam(STATUS_QPARAM, AVAILABLE)
                .queryParam(ID_TYPE_QPARAM, ID)
                .type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book4)
                .path("users").path(userId6)
                .queryParam(LISTING_TYPE_QPARAM, WISHLIST)
                .queryParam(ID_TYPE_QPARAM, ID).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book1)
                .path("users").path(userId7)
                .queryParam(LISTING_TYPE_QPARAM, WISHLIST)
                .queryParam(ID_TYPE_QPARAM, ID).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book4)
                .path("users").path(userId7)
                .queryParam(LISTING_TYPE_QPARAM, WISHLIST)
                .queryParam(ID_TYPE_QPARAM, ID).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book5)
                .path("users").path(userId7)
                .queryParam(LISTING_TYPE_QPARAM, OWNS)
                .queryParam(STATUS_QPARAM, AVAILABLE)
                .queryParam(ID_TYPE_QPARAM, ID).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        //adding borrow borrow req
        clientResponse = resource
                .path("books")
                .path(book5)
                .path("borrow")
                .entity(new BorrowRequest(userId7, userId3, 5, System
                        .currentTimeMillis(), "Can i borrow this book"))
                .type("application/json").post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        //accept borrow
        clientResponse = resource
                .path("books").path(book5)
                .path("borrow")
                .queryParam(OWNER_USER_ID_QPARAM, userId7)
                .queryParam(BORROWER_ID_QPARAM, userId3)
                .queryParam(STATUS_QPARAM, BORROW_SUCCESS)
                .queryParam(MESSAGE_QPARAM, "Sample comment")
                .type("application/json").put(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books").path(book3)
                .path("users").path(userId8)
                .queryParam(LISTING_TYPE_QPARAM, OWNS)
                .queryParam(STATUS_QPARAM, AVAILABLE)
                .queryParam(ID_TYPE_QPARAM, ID).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("books").path(book2)
                .path("users").path(userId9)
                .queryParam(LISTING_TYPE_QPARAM, OWNS)
                .queryParam(STATUS_QPARAM, AVAILABLE)
                .queryParam(ID_TYPE_QPARAM, ID).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("books").path(book5)
                .path("users").path(userId10)
                .queryParam(LISTING_TYPE_QPARAM, OWNS)
                .queryParam(STATUS_QPARAM, AVAILABLE)
                .queryParam(ID_TYPE_QPARAM, ID).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("books").path(book6)
                .path("users").path(userId10)
                .queryParam(LISTING_TYPE_QPARAM, WISHLIST)
                .queryParam(ID_TYPE_QPARAM, ID).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        Reminder reminder = DataBrewer.getFakeReminder("Subject");
        ClientResponse clientResponseForReminderCreation = resource.path("users").path(userId3).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", userId1).type("application/json").entity(reminder).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;
        Reminder reminderCreated = clientResponseForReminderCreation.getEntity(Reminder.class);


//        ClientResponse clientResponseToGetReminderCreated = resource.path("users").path(userId3).path("reminders").path(reminderCreated.getNodeId().toString()).type("application/json").get(ClientResponse.class);
//        Reminder reminderReturned = clientResponseToGetReminderCreated.getEntity(Reminder.class);
//        assert reminderReturned.getReminderMessage().equalsIgnoreCase(reminderCreated.getReminderMessage()) && reminderReturned.getReminderTime().equals(reminderCreated.getReminderTime());
//
//
//        reminderReturned.setReminderMessage("Collect Book Time Update");
//        Long updatedTime = System.currentTimeMillis();
//        reminderReturned.setReminderTime(updatedTime);
//        ClientResponse clientResponseToUpdateReminder = resource.path("users").path(userId3).path("reminders").path(reminderReturned.getNodeId().toString()).queryParam("createdBy", userId4).type("application/json").entity(reminderReturned).post(ClientResponse.class);
//        assert clientResponseToUpdateReminder.getStatus() == 200;
//
//        ClientResponse clientResponseToGetReminderUpdated = resource.path("users").path(userId3).path("reminders").path(reminderCreated.getNodeId().toString()).type("application/json").get(ClientResponse.class);
//        Reminder reminderUpdated = clientResponseToGetReminderUpdated.getEntity(Reminder.class);
//        Assert.assertEquals(reminderUpdated.getReminderMessage(), reminderReturned.getReminderMessage());
//        Assert.assertEquals(reminderUpdated.getReminderTime(), reminderReturned.getReminderTime());
//
//
//        ClientResponse clientResponseToDeleteReminder = resource.path("users").path(userId3).path("reminders").path(reminderCreated.getNodeId().toString()).type("application/json").delete(ClientResponse.class);
//        assert clientResponseToDeleteReminder.getStatus() == 200;

        //Multiple Reminders of a User Fetch test

        clientResponseForReminderCreation = resource.path("users").path(userId3).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", userId7).type("application/json").entity(DataBrewer.getFakeReminder("Reminder One")).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;

        clientResponseForReminderCreation = resource.path("users").path(userId3).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", userId2).type("application/json").entity(DataBrewer.getFakeReminder("Reminder two")).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;

        clientResponseForReminderCreation = resource.path("users").path(userId3).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", userId4).type("application/json").entity(DataBrewer.getFakeReminder("Reminder three")).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;

        clientResponseForReminderCreation = resource.path("users").path(userId3).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", userId5).type("application/json").entity(DataBrewer.getFakeReminder("Reminder four")).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;

        clientResponseForReminderCreation = resource.path("users").path(userId3).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", userId6).type("application/json").entity(DataBrewer.getFakeReminder("Reminder five")).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;

        ClientResponse clientResponseForMultipleReminderFetch = resource.path("users").path(userId3).path("reminders").type("application/json").get(ClientResponse.class);
        assert clientResponseForMultipleReminderFetch.getStatus() == 200;
        ReminderPage reminderPage = clientResponseForMultipleReminderFetch.getEntity(ReminderPage.class);
    }
}
