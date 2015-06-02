package com.campusconnect.neo4j.tests;

import com.campusconnect.neo4j.tests.functional.GroupResourceTest;
import com.campusconnect.neo4j.tests.functional.UserResourceTest;
import com.campusconnect.neo4j.tests.functional.base.DataBrewer;
import com.campusconnect.neo4j.types.BorrowRequest;
import com.campusconnect.neo4j.types.GroupPage;
import com.campusconnect.neo4j.types.Reminder;
import com.campusconnect.neo4j.types.ReminderAbout;
import com.campusconnect.neo4j.types.ReminderPage;
import com.sun.jersey.api.client.ClientResponse;

import org.testng.annotations.Test;

/**
 * Created by sn1 on 5/7/15.
 */
public class DataSetUp extends TestBase {
    @Test
    public void dataSetUp() {
        // create User1

        String userId3 = UserResourceTest.createKnowUserWithGoogleId(
                "Shiva Kumar", "shiva.n404@gmail.com", "105609898189858031660");

        // createUser2
        String userId2 = UserResourceTest.createUser();

        // createUser3
        String userId1 = UserResourceTest.createUser();

        // createUser4
        String userId4 = UserResourceTest.createUser();

        // create user5
        String userId5 = UserResourceTest.createUser();

        // create user6
        String userId6 = UserResourceTest.createUser();

        // create user7
        String userId7 = UserResourceTest.createUser();

        // create user8
        String userId8 = UserResourceTest.createUser();

        // create user9
        String userId9 = UserResourceTest.createUser();

        // create user10
        String userId10 = UserResourceTest.createUser();

        // create book1
        String book1 = UserResourceTest.createBook();

        // create book2
        String book2 = UserResourceTest.createBook();

        // create book3
        String book3 = UserResourceTest.createBook();

        // create book4
        String book4 = UserResourceTest.createBook();

        // create book5
        String book5 = UserResourceTest.createBook();

        // create book6
        String book6 = UserResourceTest.createBook();

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
        clientResponse = resource.path("users").path(userId1).path("follow")
                .path(userId8).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

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

        // user3 follow with user5
        clientResponse = resource.path("users").path(userId3).path("follow")
                .path(userId5).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        // user3 follow with user6
        clientResponse = resource.path("users").path(userId3).path("follow")
                .path(userId6).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

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

        // user6 follow with user3
        clientResponse = resource.path("users").path(userId6).path("follow")
                .path(userId3).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        // user7 follow with user2
        clientResponse = resource.path("users").path(userId7).path("follow")
                .path(userId2).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        // user7 follow with user9
        clientResponse = resource.path("users").path(userId7).path("follow")
                .path(userId9).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

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

        clientResponse = resource.path("users").path(userId1).path("books")
                .path(book2).path("wish").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId1).path("books")
                .path(book3).path("own").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId1).path("books")
                .path(book1).path("read").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId2).path("books")
                .path(book2).path("own").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId2).path("books")
                .path(book3).path("own").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId3).path("books")
                .path(book3).path("wish").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId3).path("books")
                .path(book1).path("own").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId3).path("books")
                .path(book4).path("own").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId4).path("books")
                .path(book3).path("read").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId6).path("books")
                .path(book5).path("own").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId6).path("books")
                .path(book4).path("wish").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId7).path("books")
                .path(book1).path("wish").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId7).path("books")
                .path(book4).path("wish").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId7).path("books")
                .path(book5).path("own").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books")
                .path(book5)
                .path("borrow")
                .entity(new BorrowRequest(userId7, userId3, 5, System
                        .currentTimeMillis(), "Can i borrow this book"))
                .type("application/json").post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource
                .path("books")
                .path(book5)
                .path("users")
                .path(userId7)
                .path("borrow")
                .queryParam("borrowerId", userId3)
                .queryParam("status", "success")
                .entity(new BorrowRequest(userId7, userId3, 5, System
                        .currentTimeMillis(), "Can i borrow this book"))
                .type("application/json").put(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId8).path("books")
                .path(book3).path("own").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId9).path("books")
                .path(book2).path("own").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId10).path("books")
                .path(book5).path("own").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        clientResponse = resource.path("users").path(userId10).path("books")
                .path(book6).path("wish").type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;
        
        Reminder reminder = DataBrewer.getFakeReminder("Subject");
        ClientResponse clientResponseForReminderCreation = resource.path("users").path(userId3).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", userId1).type("application/json").entity(reminder).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;
        Reminder reminderCreated = clientResponseForReminderCreation.getEntity(Reminder.class);


        ClientResponse clientResponseToGetReminderCreated = resource.path("users").path(userId3).path("reminders").path(reminderCreated.getNodeId().toString()).type("application/json").get(ClientResponse.class);
        Reminder reminderReturned = clientResponseToGetReminderCreated.getEntity(Reminder.class);
        assert reminderReturned.getReminderMessage().equalsIgnoreCase(reminderCreated.getReminderMessage()) && reminderReturned.getReminderTime() == reminderCreated.getReminderTime();

        reminderReturned.setReminderMessage("Collect Book Time Update");
        Long updatedTime = System.currentTimeMillis();
        reminderReturned.setReminderTime(updatedTime);
        ClientResponse clientResponseToUpdateReminder = resource.path("users").path(userId3).path("reminders").path(reminderReturned.getNodeId().toString()).queryParam("createdBy", userId4).type("application/json").entity(reminderReturned).put(ClientResponse.class);
        assert clientResponseToUpdateReminder.getStatus() == 200;

        ClientResponse clientResponseToGetReminderUpdated = resource.path("users").path(userId3).path("reminders").path(reminderCreated.getNodeId().toString()).type("application/json").get(ClientResponse.class);
        Reminder reminderUpdated = clientResponseToGetReminderUpdated.getEntity(Reminder.class);
        assert reminderUpdated.getReminderMessage().equalsIgnoreCase(reminderReturned.getReminderMessage()) && reminderUpdated.getReminderTime()==reminderReturned.getReminderTime();


        ClientResponse clientResponseToDeleteReminder = resource.path("users").path(userId3).path("reminders").path(reminderCreated.getNodeId().toString()).type("application/json").delete(ClientResponse.class);
        assert clientResponseToDeleteReminder.getStatus() == 200;

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
