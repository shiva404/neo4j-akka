package com.campusconnect.neo4j.tests.functional;

import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.types.neo4j.Address;
import com.campusconnect.neo4j.types.web.Field;
import com.campusconnect.neo4j.types.web.Fields;
import com.campusconnect.neo4j.types.web.Notification;
import com.campusconnect.neo4j.types.web.NotificationPage;
import com.sun.jersey.api.client.ClientResponse;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AddEventTest extends TestBase {

    private String userId = null;
    private Long addressId = null;
    private Address addedAddress = null;

    @Test
    public void TestCreateUserEvent() {
        userId = UserResourceFuncTest.createUser();
        System.out.println(userId);
    }


    @Test(dependsOnMethods = "TestCreateUserEvent")
    public void TestFollowUserEvent() {
        System.out.println("first User id " + userId);
        String userId2 = UserResourceFuncTest.createUser();
        System.out.println("followed User id " + userId2);
        ClientResponse clientResponseForFollwingAnotherUser = resource
                .path("users").path(userId).path("follow").path(userId2).type("application/json")
                .post(ClientResponse.class);
        assert clientResponseForFollwingAnotherUser.getStatus() == 200;


    }

    @Test(dependsOnMethods = "TestFollowUserEvent")
    public void TestUserUpdateEvent() {
        Fields fields = new Fields();
        List<Field> listOfFields = new ArrayList<Field>();
        listOfFields.add(new Field("name", "Shiva"));
        fields.setFields(listOfFields);
        ClientResponse clientResponseForUpdatingUserProfile = resource
                .path("users").path(userId).path("fields").entity(fields).type("application/json")
                .put(ClientResponse.class);
        assert clientResponseForUpdatingUserProfile.getStatus() == 200;

    }

    @Test(dependsOnMethods = "TestUserUpdateEvent")
    public void TestUserAddedAddressEvent() {

        Address address = new Address("home", "abcd", "efgh", "ijkl", "mnop", "pqrs", "tuvw", "123456");
        ClientResponse clientResponseForAddingAddress = resource.path("users").path(userId).path("addresses").entity(address).type("application/json").post(ClientResponse.class);
        assert clientResponseForAddingAddress.getStatus() == 200;
        addedAddress = clientResponseForAddingAddress.getEntity(Address.class);
        addressId = addedAddress.getId();


    }

    @Test(dependsOnMethods = "TestUserAddedAddressEvent")
    public void TestUserAddressUpdateEvent() {
        addedAddress.setCity("Davangere");
        addedAddress.setCountry("India");
        ClientResponse clientResponseForUpdatingUserProfile = resource
                .path("users").path(userId).path("addresses").path(addressId.toString()).entity(addedAddress).type("application/json")
                .put(ClientResponse.class);
        assert clientResponseForUpdatingUserProfile.getStatus() == 200;

    }


    @Test(dependsOnMethods = "TestUserAddressUpdateEvent")
    public void TestGetUserEvents() {
        ClientResponse clientResponseForReturningUserEvent = resource
                .path("users").path(userId).path("timeline").path("events").type("application/json").get(ClientResponse.class);
        assert clientResponseForReturningUserEvent.getStatus() == 200;
    }

    @Test(dependsOnMethods = "TestGetUserEvents")
    public void TestGetUserNotifications() {
        System.out.println("TestGetUserNotifications");
        ClientResponse clientResponseForReturningUserNotifications = resource.path("users").path(userId).path("notifications").queryParam("filter", "all").type("application/json").get(ClientResponse.class);
        assert clientResponseForReturningUserNotifications.getStatus() == 200;
        NotificationPage notificationPage = clientResponseForReturningUserNotifications.getEntity(NotificationPage.class);

    }

    @Test(dependsOnMethods = "TestGetUserNotifications")
    public void TestGetUserFreshNotifications() {
        System.out.println("TestGetUserFreshNotifications");
        ClientResponse clientResponseForReturningFreshUserNotifications = resource.path("users").path(userId).path("notifications").type("application/json").get(ClientResponse.class);
        assert clientResponseForReturningFreshUserNotifications.getStatus() == 200;
        NotificationPage notificationPage = clientResponseForReturningFreshUserNotifications.getEntity(NotificationPage.class);
        List<Notification> notifications = notificationPage.getNotifications();

        for (Notification notification : notifications) {
            System.out.println(notification);
        }
    }


    @Test(dependsOnMethods = "TestGetUserFreshNotifications")
    public void TestMoveUserNotification() {
        System.out.println("TestMoveUserNotification");
        ClientResponse clientResponseForMovingNotification = resource.path("users").path(userId).path("notifications").queryParam("filter", "all").type("application/json").delete(ClientResponse.class);
        assert clientResponseForMovingNotification.getStatus() == 200;
    }

    @Test(dependsOnMethods = "TestMoveUserNotification")
    public void TestGetUserAllNotifications() {
        System.out.println("TestGetUserAllNotifications");
        ClientResponse clientResponseForReturningUserNotifications = resource.path("users").path(userId).path("notifications").queryParam("filter", "all").type("application/json").get(ClientResponse.class);
        assert clientResponseForReturningUserNotifications.getStatus() == 200;
        NotificationPage notificationPage = clientResponseForReturningUserNotifications.getEntity(NotificationPage.class);

    }

    @Test(dependsOnMethods = "TestGetUserAllNotifications")
    public void TestGetUserNoFreshNotifications() {
        System.out.println("TestGetUserNoFreshNotifications");
        ClientResponse clientResponseForReturningFreshUserNotifications = resource.path("users").path(userId).path("notifications").type("application/json").get(ClientResponse.class);
        assert clientResponseForReturningFreshUserNotifications.getStatus() == 200;
        NotificationPage notificationPage = clientResponseForReturningFreshUserNotifications.getEntity(NotificationPage.class);
        List<Notification> notifications = notificationPage.getNotifications();

        for (Notification notification : notifications) {
            System.out.println(notification);
        }
    }


}
