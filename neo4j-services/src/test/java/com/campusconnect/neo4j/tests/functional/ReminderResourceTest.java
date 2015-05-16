package com.campusconnect.neo4j.tests.functional;

import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.tests.functional.base.DataBrewer;
import com.campusconnect.neo4j.types.Reminder;
import com.campusconnect.neo4j.types.ReminderAbout;
import com.campusconnect.neo4j.types.ReminderPage;
import com.sun.jersey.api.client.ClientResponse;
import org.testng.annotations.Test;

public class ReminderResourceTest extends TestBase {

    @Test
    public void testRemindersSetByUser() {
        String userId = UserResourceTest.createUser();
        String createdBy = UserResourceTest.createUser();
        Reminder reminder = DataBrewer.getFakeReminder();
        ClientResponse clientResponseForReminderCreation = resource.path("users").path(userId).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", createdBy).type("application/json").entity(reminder).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;
        Reminder reminderCreated = clientResponseForReminderCreation.getEntity(Reminder.class);


        ClientResponse clientResponseToGetReminderCreated = resource.path("users").path(userId).path("reminders").path(reminderCreated.getNodeId().toString()).type("application/json").get(ClientResponse.class);
        Reminder reminderReturned = clientResponseToGetReminderCreated.getEntity(Reminder.class);
        assert reminderReturned.getReminderMessage().equalsIgnoreCase(reminderCreated.getReminderMessage()) && reminderReturned.getReminderTime().equals(reminderCreated.getReminderTime());

        reminderReturned.setReminderMessage("Collect Book Time Update");
        Long updatedTime = System.currentTimeMillis();
        reminderReturned.setReminderTime(updatedTime);
        ClientResponse clientResponseToUpdateReminder = resource.path("users").path(userId).path("reminders").path(reminderReturned.getNodeId().toString()).queryParam("createdBy", createdBy).type("application/json").entity(reminderReturned).put(ClientResponse.class);
        assert clientResponseToUpdateReminder.getStatus() == 200;

        ClientResponse clientResponseToGetReminderUpdated = resource.path("users").path(userId).path("reminders").path(reminderCreated.getNodeId().toString()).type("application/json").get(ClientResponse.class);
        Reminder reminderUpdated = clientResponseToGetReminderUpdated.getEntity(Reminder.class);
        assert reminderUpdated.getReminderMessage().equalsIgnoreCase(reminderReturned.getReminderMessage()) && reminderUpdated.getReminderTime().equals(reminderReturned.getReminderTime());


        ClientResponse clientResponseToDeleteReminder = resource.path("users").path(userId).path("reminders").path(reminderCreated.getNodeId().toString()).type("application/json").delete(ClientResponse.class);
        assert clientResponseToDeleteReminder.getStatus() == 200;

        //Multiple Reminders of a User Fetch test

        clientResponseForReminderCreation = resource.path("users").path(userId).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", createdBy).type("application/json").entity(DataBrewer.getFakeReminder("Reminder One")).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;

        clientResponseForReminderCreation = resource.path("users").path(userId).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", createdBy).type("application/json").entity(DataBrewer.getFakeReminder("Reminder two")).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;

        clientResponseForReminderCreation = resource.path("users").path(userId).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", createdBy).type("application/json").entity(DataBrewer.getFakeReminder("Reminder three")).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;

        clientResponseForReminderCreation = resource.path("users").path(userId).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", createdBy).type("application/json").entity(DataBrewer.getFakeReminder("Reminder four")).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;

        clientResponseForReminderCreation = resource.path("users").path(userId).path("reminders").queryParam("reminderAbout", ReminderAbout.COLLECT.toString()).queryParam("createdBy", createdBy).type("application/json").entity(DataBrewer.getFakeReminder("Reminder five")).post(ClientResponse.class);
        assert clientResponseForReminderCreation.getStatus() == 201;

        ClientResponse clientResponseForMultipleReminderFetch = resource.path("users").path(userId).path("reminders").type("application/json").get(ClientResponse.class);
        assert clientResponseForMultipleReminderFetch.getStatus() == 200;
        ReminderPage reminderPage = clientResponseForMultipleReminderFetch.getEntity(ReminderPage.class);
    }
}