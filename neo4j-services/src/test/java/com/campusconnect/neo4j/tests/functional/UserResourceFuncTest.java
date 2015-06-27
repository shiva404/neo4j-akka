package com.campusconnect.neo4j.tests.functional;

import static com.campusconnect.neo4j.mappers.Neo4jToWebMapper.mapAddressNeo4jToWeb;
import static com.campusconnect.neo4j.mappers.WebToNeo4jMapper.mapAddressWebToNeo4j;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.campusconnect.neo4j.mappers.Neo4jToWebMapper;
import com.campusconnect.neo4j.tests.base.DataBrewer;
import com.campusconnect.neo4j.types.web.*;
import com.sun.jersey.api.client.ClientResponse;
import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.util.Constants;

public class UserResourceFuncTest extends TestBase{
	
	private static User createdUser;
	
	private void assertAddress(Address address1, Address returnedAddress1) {
		assertEquals(address1.getCity(), returnedAddress1.getCity());
		 assertEquals(address1.getCountry(), returnedAddress1.getCountry());
		 assertEquals(address1.getCreatedDate(), returnedAddress1.getCreatedDate());
		 assertNotNull(returnedAddress1.getId());
		 assertEquals(address1.getLandmark(), returnedAddress1.getLandmark());
		 assertEquals(address1.getLastModifiedTime(), returnedAddress1.getLastModifiedTime());
		 assertEquals(address1.getLatitude(), returnedAddress1.getLatitude());
		 assertEquals(address1.getLine1(), returnedAddress1.getLine1());
		 assertEquals(address1.getLine2(), returnedAddress1.getLine2());
		 assertEquals(address1.getLongitude(), returnedAddress1.getLongitude());
		 assertEquals(address1.getState(), returnedAddress1.getState());
		 assertEquals(address1.getType(), returnedAddress1.getType());
		 assertEquals(address1.getZipCode(), returnedAddress1.getZipCode());
	}
	
	 public static String createUser() {
	        ClientResponse clientResponse = resource.path("users").type("application/json").entity(DataBrewer.getFakeUserWithAddress()).post(ClientResponse.class);
	        assert clientResponse.getStatus() == 201;
	        createdUser = clientResponse.getEntity(User.class);
	        String userId = createdUser.getId();
	        return userId;
	    }

	 
String userId1 = createUser();
String userId2 = createUser();

@Test
public void testAddAndGetAddressToUser() {
	
		Address address1 = DataBrewer
				.getFakeAddress(Constants.ADDRESS_TYPE_HOME);
		Address address2 = DataBrewer
				.getFakeAddress(Constants.ADDRESS_TYPE_WORK);
		ClientResponse clientResponse = resource.path("users").path(userId1)
				.path("addresses").type("application/json").entity(address1)
				.post(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		Address returnedAddress1 = clientResponse.getEntity(Address.class);

		assertAddress(address1, returnedAddress1);
		
		//TODO: Check for private event added

		clientResponse = resource.path("users").path(userId1).path("addresses")
				.type("application/json").entity(address2)
				.post(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		Address returnedAddress2 = clientResponse.getEntity(Address.class);

		clientResponse = resource.path("users").path(userId1).path("addresses")
				.type("application/json").get(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		AddressesPage addressesPage = clientResponse
				.getEntity(AddressesPage.class);
		List<Address> addresses = addressesPage.getAddresses();
		assertEquals(addresses.size(), 3);

}




@Test
public void testAddFriendGetPendingFriendAndSearchFriendsOfUsers() {

	 ClientResponse clientResponse = resource.path("users").path(userId1).path("friend").path(userId2).type("application/json").post(ClientResponse.class);
	 assertEquals(clientResponse.getStatus(), 200);

	 clientResponse = resource.path("users").path(userId2).path("friends").path("pending").type("application/json").get(ClientResponse.class);
	 assertEquals(clientResponse.getStatus(), 200);
	 UsersPage usersPage = clientResponse.getEntity(UsersPage.class);
	 
	 List<User> users = usersPage.getUsers();
	 assertEquals(users.size(), 1);
	 assertEquals(users.get(0).getId(), userId1);
	 
	 assertNotifications(userId1,userId2,Constants.FRIEND_REQUEST_SENT_NOTIFICATION);
	 //TODO: Search user's asserts 
}

private void assertNotifications(String targetUserId,String notificationofUserId,String type) {
	ClientResponse clientResponse;
	clientResponse = resource.path("users").path(notificationofUserId).path("notifications").queryParam("filter", "all").type("application/json").get(ClientResponse.class);
	 assertEquals(clientResponse.getStatus(), 200);
	 NotificationPage notificationPage = clientResponse.getEntity(NotificationPage.class);
	 List<Notification> notifications = notificationPage.getNotifications();
	 
	 boolean foundExpected = false;
	 
	 for(Notification notification:notifications)
	 {
		 if(notification.getTarget().getUrl().contains(targetUserId) && notification.getType().equals(type)){
			 	foundExpected = true;
			 	break;
		 }
	 }
	 
	 assertTrue(foundExpected);
}

	
@Test(dependsOnMethods = "testAddFriendGetPendingFriendAndSearchFriendsOfUsers")
public void testConfirmFriendAndGetFriendsToUsers()
{
	 ClientResponse clientResponse = resource.path("users").path(userId2).path("friend").path(userId1).queryParam("status", "agreed").type("application/json").put(ClientResponse.class);
	 assertEquals(clientResponse.getStatus(), 200);
	
	 clientResponse = resource.path("users").path(userId1).path("friends").queryParam("currentUserId",userId2).type("application/json").get(ClientResponse.class);
	 assertEquals(clientResponse.getStatus(), 200);
	 Friends friends = clientResponse.getEntity(Friends.class);
	 FriendsPage friendsPage = friends.getFriends();
	 
	 List<User> friend =  friendsPage.getFriends();
	 assertEquals(friend.size(), 1);
	 assertEquals(friend.get(0).getId(), userId2);
	 
	 clientResponse = resource.path("users").path(userId2).path("notifications").queryParam("filter", "all").type("application/json").get(ClientResponse.class);
	 assertEquals(clientResponse.getStatus(), 200);
	 NotificationPage notificationPage = clientResponse.getEntity(NotificationPage.class);
	 List<Notification> notifications = notificationPage.getNotifications();
	 
	 boolean foundExpected = false;
	 
	 for(Notification notification:notifications)
	 {
		 if(notification.getTarget().getUrl().contains(userId1) && notification.getType().equals(Constants.FRIEND_REQUEST_SENT_NOTIFICATION)){
			 	foundExpected = true;
			 	
		 }
	 }
	}
}