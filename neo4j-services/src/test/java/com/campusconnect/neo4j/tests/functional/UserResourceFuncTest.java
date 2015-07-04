package com.campusconnect.neo4j.tests.functional;

import static com.campusconnect.neo4j.mappers.Neo4jToWebMapper.mapAddressNeo4jToWeb;
import static com.campusconnect.neo4j.mappers.WebToNeo4jMapper.mapAddressWebToNeo4j;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static org.testng.Assert.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.campusconnect.neo4j.mappers.Neo4jToWebMapper;
import com.campusconnect.neo4j.tests.base.DataBrewer;
import com.campusconnect.neo4j.types.neo4j.Reminder;
import com.campusconnect.neo4j.types.neo4j.ReminderRelationShip;
import com.campusconnect.neo4j.types.web.*;
import com.sun.jersey.api.client.ClientResponse;
import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.util.Constants;

public class UserResourceFuncTest extends TestBase {

	private static User createdUser;

	public static Address addAddressToUser(String userId, Address address) {
		ClientResponse clientResponse = resource.path("users").path(userId)
				.path("addresses").type("application/json").entity(address)
				.post(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		return clientResponse.getEntity(Address.class);

	}

	public static String createUser() {
		ClientResponse clientResponse = resource.path("users")
				.type("application/json")
				.entity(DataBrewer.getFakeUserWithAddress())
				.post(ClientResponse.class);
		assert clientResponse.getStatus() == 201;
		createdUser = clientResponse.getEntity(User.class);
		String userId = createdUser.getId();
		return userId;
	}
	
	 public static String createBook() {
	        ClientResponse createBookClientResponse = resource.path("books").type("application/json").entity(DataBrewer.getFakeBook()).post(ClientResponse.class);
	        return createBookClientResponse.getEntity(Book.class).getId();
	    }
	 
	 public static void deleteUser(String userId) {
	        ClientResponse clientResponse = resource.path("users").path(userId).type("application/json").delete(ClientResponse.class);
	        assert clientResponse.getStatus() == 200;
	    }

	private void assertAddress(Address address1, Address returnedAddress1) {
		assertEquals(address1.getCity(), returnedAddress1.getCity());
		assertEquals(address1.getCountry(), returnedAddress1.getCountry());
		assertEquals(address1.getCreatedDate(),
				returnedAddress1.getCreatedDate());
		assertNotNull(returnedAddress1.getId());
		assertEquals(address1.getLandmark(), returnedAddress1.getLandmark());
		assertEquals(address1.getLastModifiedTime(),
				returnedAddress1.getLastModifiedTime());
		assertEquals(address1.getLatitude(), returnedAddress1.getLatitude());
		assertEquals(address1.getLine1(), returnedAddress1.getLine1());
		assertEquals(address1.getLine2(), returnedAddress1.getLine2());
		assertEquals(address1.getLongitude(), returnedAddress1.getLongitude());
		assertEquals(address1.getState(), returnedAddress1.getState());
		assertEquals(address1.getType(), returnedAddress1.getType());
		assertEquals(address1.getZipCode(), returnedAddress1.getZipCode());
	}

	private void assertNotifications(String targetUserId,
			String notificationofUserId, String type) {
		ClientResponse clientResponse;
		clientResponse = resource.path("users").path(notificationofUserId)
				.path("notifications").queryParam("filter", "all")
				.type("application/json").get(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		NotificationPage notificationPage = clientResponse
				.getEntity(NotificationPage.class);
		List<Notification> notifications = notificationPage.getNotifications();

		boolean foundExpected = false;

		for (Notification notification : notifications) {
			if (notification.getTarget().getUrl().contains(targetUserId)
					&& notification.getType().equals(type)) {
				foundExpected = true;
				break;
			}
		}

		assertTrue(foundExpected);
	}

	
	Long addressId1;
	Long addressId2;
	String userId1;
	String userId2;
	String userId3;
	String userId4;
	
	 @BeforeClass
	    public void setUp() {
		 	
			 userId1 = createUser();
			 userId2 = createUser();
			 userId3 = createUser();
			 userId4 = createUser();

	    }

	    @AfterClass
	    public void removeData() {
	        //deleteUsers
	        UserResourceFuncTest.deleteUser(userId1);
	        UserResourceFuncTest.deleteUser(userId2);
	        UserResourceFuncTest.deleteUser(userId3);
	        UserResourceFuncTest.deleteUser(userId4);
	    }
	
	
	@Test
	public void testAddAndGetAddressToUser() {

		Address address1 = DataBrewer
				.getFakeAddress(Constants.ADDRESS_TYPE_HOME);
		Address address2 = DataBrewer
				.getFakeAddress(Constants.ADDRESS_TYPE_WORK);

		Address returnedAddress1 = addAddressToUser(userId1, address1);

		assertAddress(address1, returnedAddress1);

		// TODO: Check for private event added

		Address returnedAddress2 = addAddressToUser(userId1, address2);
		addressId1 = returnedAddress1.getId();
		addressId2 = returnedAddress2.getId();
		ClientResponse clientResponse = resource.path("users").path(userId1)
				.path("addresses").type("application/json")
				.get(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		AddressesPage addressesPage = clientResponse
				.getEntity(AddressesPage.class);
		List<Address> addresses = addressesPage.getAddresses();
		assertEquals(addresses.size(), 3);

	}

	@Test
	public void testAddFriendGetPendingFriendAndSearchFriendsOfUsers() {

		ClientResponse clientResponse = resource.path("users").path(userId1)
				.path("friend").path(userId2).type("application/json")
				.post(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);

		clientResponse = resource.path("users").path(userId2).path("friends")
				.path("pending").type("application/json")
				.get(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		UsersPage usersPage = clientResponse.getEntity(UsersPage.class);

		List<User> users = usersPage.getUsers();
		assertEquals(users.size(), 1);
		assertEquals(users.get(0).getId(), userId1);

		assertNotifications(userId1, userId2,
				Constants.FRIEND_REQUEST_SENT_NOTIFICATION);
		// TODO: Search user's asserts
	}

	@Test(dependsOnMethods = "testAddFriendGetPendingFriendAndSearchFriendsOfUsers")
	public void testConfirmFriendAndGetFriendsToUsers() {
		ClientResponse clientResponse = resource.path("users").path(userId2)
				.path("friend").path(userId1).queryParam("status", "agreed")
				.type("application/json").put(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);

		clientResponse = resource.path("users").path(userId1).path("friends")
				.queryParam("currentUserId", userId2).type("application/json")
				.get(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		Friends friends = clientResponse.getEntity(Friends.class);
		FriendsPage friendsPage = friends.getFriends();
		
		List<User> friend = friendsPage.getFriends();
		assertEquals(friend.size(), 1);
		assertEquals(friend.get(0).getId(), userId2);
		
		clientResponse = resource.path("users").path(userId2).path("friends")
				.path("pending").type("application/json")
				.get(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		UsersPage usersPage = clientResponse.getEntity(UsersPage.class);

		List<User> users = usersPage.getUsers();
		assertEquals(users.size(), 0);

		

		assertNotifications(userId1, userId2,
				Constants.FRIEND_REQUEST_ACCEPTED_NOTIFICATION);
	}

	@Test
	public void testCancelFriendRequestUsers() {
		ClientResponse clientResponse = resource.path("users").path(userId3)
				.path("friend").path(userId4).type("application/json")
				.post(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);

		clientResponse = resource.path("users").path(userId4).path("friend")
				.path(userId3).queryParam("status", "cancel")
				.type("application/json").put(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);

		clientResponse = resource.path("users").path(userId4).path("friends")
				.path("pending").type("application/json")
				.get(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		UsersPage usersPage = clientResponse.getEntity(UsersPage.class);

		List<User> users = usersPage.getUsers();
		assertEquals(users.size(), 0);
	}
	
	@Test(dependsOnMethods = "testConfirmFriendAndGetFriendsToUsers")
	public void testUnFriendUsers() {
		
				ClientResponse clientResponse = resource.path("users").path(userId1).path("friend")
				.path(userId2).type("application/json")
				.delete(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		
		clientResponse = resource.path("users").path(userId1).path("friends")
				.queryParam("currentUserId", userId2).type("application/json")
				.get(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		Friends friends = clientResponse.getEntity(Friends.class);
		FriendsPage friendsPage = friends.getFriends();

		List<User> friend = friendsPage.getFriends();
		assertEquals(friend.size(), 0);
		
	}

	@Test(dependsOnMethods = "testAddAndGetAddressToUser")
	public void testDeleteAddress() {
		ClientResponse clientResponse = resource.path("users").path(userId1)
				.path("addresses").path(addressId1.toString())
				.type("application/json").delete(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		
		clientResponse = resource.path("users").path(userId1).path("addresses")
				.type("application/json").get(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		AddressesPage addressesPage = clientResponse
				.getEntity(AddressesPage.class);
		List<Address> addresses = addressesPage.getAddresses();
		assertEquals(addresses.size(), 2);
		
		//TODO: test added internal Event 

		clientResponse = resource.path("users").path(userId1).path("addresses")
				.path(addressId1.toString()).type("application/json")
				.get(ClientResponse.class);
		assertEquals(clientResponse.getStatus(), 200);
		Address address2 = clientResponse.getEntity(Address.class);
		assertNull(address2);
	
	}

		
}