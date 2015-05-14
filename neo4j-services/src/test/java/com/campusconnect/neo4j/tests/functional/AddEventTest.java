package com.campusconnect.neo4j.tests.functional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.testng.annotations.Test;

import com.campusconnect.neo4j.da.UserDaoImpl;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.resources.UserResource;
import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.tests.functional.base.DataBrewer;
import com.campusconnect.neo4j.types.Address;
import com.campusconnect.neo4j.types.EventPage;
import com.campusconnect.neo4j.types.Field;
import com.campusconnect.neo4j.types.Fields;
import com.campusconnect.neo4j.types.Notification;
import com.campusconnect.neo4j.types.NotificationEntity;
import com.campusconnect.neo4j.types.NotificationPage;
import com.campusconnect.neo4j.types.User;
import com.sun.jersey.api.client.ClientResponse;

public class AddEventTest extends TestBase{
	
	private String userId = null;
	private Long addressId = null;
	private Address addedAddress = null;
	
	@Test
	public void TestCreateUserEvent()
	{
		userId = UserResourceTest.createUser();	
		System.out.println(userId);
	}

	
	 @Test(dependsOnMethods = "TestCreateUserEvent")
	public void TestFollowUserEvent()
	{
		System.out.println("first User id " + userId);
		String userId2 = UserResourceTest.createUser();
		System.out.println("followed User id " + userId2);
		ClientResponse clientResponseForFollwingAnotherUser = resource
						.path("users").path(userId).path("follow").path(userId2).type("application/json")
						.post(ClientResponse.class);
		assert clientResponseForFollwingAnotherUser.getStatus() == 200;
		
		
	}
	 
	 @Test(dependsOnMethods = "TestFollowUserEvent")
	 public void TestUserUpdateEvent()
	 {
		Fields fields = new Fields();
		List<Field> listOfFields = new ArrayList<Field>();
		listOfFields.add(new Field("name", "Shiva"));
		fields.setFields(listOfFields);
			 ClientResponse clientResponseForUpdatingUserProfile = resource
						.path("users").path(userId).path("fields").entity(fields).type("application/json")
						.put(ClientResponse.class);
			 assert clientResponseForUpdatingUserProfile.getStatus() == 200;
			 
		 }
	 
	 @Test(dependsOnMethods= "TestUserUpdateEvent")
	 public void TestUserAddedAddressEvent()
	 {
		
		 Address address = new Address("home", "abcd", "efgh", "ijkl", "mnop", "pqrs", "tuvw", "123456");
		 ClientResponse clientResponseForAddingAddress = resource.path("users").path(userId).path("addresses").entity(address).type("application/json").post(ClientResponse.class);
		 assert clientResponseForAddingAddress.getStatus() == 200;
		 addedAddress = clientResponseForAddingAddress.getEntity(Address.class);
		 addressId = addedAddress.getId();
		 
		 
	 }
	 
	 @Test(dependsOnMethods = "TestUserAddedAddressEvent")
	 public void TestUserAddressUpdateEvent()
	 {
			 addedAddress.setCity("Davangere");
			 addedAddress.setCountry("India");
			 ClientResponse clientResponseForUpdatingUserProfile = resource
						.path("users").path(userId).path("addresses").path(addressId.toString()).entity(addedAddress).type("application/json")
						.put(ClientResponse.class);
			 assert clientResponseForUpdatingUserProfile.getStatus() == 200;
			 
		 }
	 
	
	@Test(dependsOnMethods = "TestUserAddressUpdateEvent")
	public void TestGetUserEvents()
	{
		ClientResponse clientResponseForReturningUserEvent = resource
					.path("users").path(userId).path("events").type("application/json").get(ClientResponse.class);
	assert clientResponseForReturningUserEvent.getStatus() == 200;
	}
	
	@Test(dependsOnMethods = "TestGetUserEvents")
	public void TestGetUserNotifications()
	{
		System.out.println("TestGetUserNotifications");
		ClientResponse clientResponseForReturningUserNotifications = resource.path("users").path(userId).path("notifications").queryParam("filter", "all").type("application/json").get(ClientResponse.class);
		assert clientResponseForReturningUserNotifications.getStatus() == 200;
		NotificationPage notificationPage = clientResponseForReturningUserNotifications.getEntity(NotificationPage.class);
		
	}

	@Test(dependsOnMethods = "TestGetUserNotifications")
	public void TestGetUserFreshNotifications()
	{
		System.out.println("TestGetUserFreshNotifications");
		ClientResponse clientResponseForReturningFreshUserNotifications = resource.path("users").path(userId).path("notifications").type("application/json").get(ClientResponse.class);
		assert clientResponseForReturningFreshUserNotifications.getStatus() == 200;
		NotificationPage notificationPage = clientResponseForReturningFreshUserNotifications.getEntity(NotificationPage.class);
		List<Notification> notifications = notificationPage.getNotifications();
		
		for(Notification notification:notifications)
		{
			System.out.println(notification);
		}
	}
	
	
	
	@Test(dependsOnMethods = "TestGetUserFreshNotifications")
	public void TestMoveUserNotification()
	{
		System.out.println("TestMoveUserNotification");
		ClientResponse clientResponseForMovingNotification = resource.path("users").path(userId).path("notifications").queryParam("filter", "all").type("application/json").delete(ClientResponse.class);
		assert clientResponseForMovingNotification.getStatus() == 200;
	}
	
	@Test(dependsOnMethods = "TestMoveUserNotification")
	public void TestGetUserAllNotifications()
	{
		System.out.println("TestGetUserAllNotifications");
		ClientResponse clientResponseForReturningUserNotifications = resource.path("users").path(userId).path("notifications").queryParam("filter", "all").type("application/json").get(ClientResponse.class);
		assert clientResponseForReturningUserNotifications.getStatus() == 200;
		NotificationPage notificationPage = clientResponseForReturningUserNotifications.getEntity(NotificationPage.class);
		
	}

	@Test(dependsOnMethods = "TestGetUserAllNotifications")
	public void TestGetUserNoFreshNotifications()
	{
		System.out.println("TestGetUserNoFreshNotifications");
		ClientResponse clientResponseForReturningFreshUserNotifications = resource.path("users").path(userId).path("notifications").type("application/json").get(ClientResponse.class);
		assert clientResponseForReturningFreshUserNotifications.getStatus() == 200;
		NotificationPage notificationPage = clientResponseForReturningFreshUserNotifications.getEntity(NotificationPage.class);
		List<Notification> notifications = notificationPage.getNotifications();
		
		for(Notification notification:notifications)
		{
			System.out.println(notification);
		}
	}
	
	
	
	
}
