package com.campusconnect.neo4j.tests.functional;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.campusconnect.neo4j.repositories.GroupRepository;
import com.campusconnect.neo4j.resources.GroupResource;
import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.tests.functional.base.DataBrewer;
import com.campusconnect.neo4j.types.Group;
import com.campusconnect.neo4j.types.User;
import com.campusconnect.neo4j.types.UserIdsPage;
import com.campusconnect.neo4j.types.UsersPage;
import com.sun.jersey.api.client.ClientResponse;

public class UserGroupRelationTest extends TestBase {

	@Test
	public void TestGroupCreationAndUserAdditionToGroup() {
		String userIdAdmin = UserResourceTest.createUser();
		String groupId = GroupResourceTest.createGroup(userIdAdmin);
		String userId = UserResourceTest.createUser();

		ClientResponse clientResponseForCreationOfGroupAndAddingUsers = resource
				.path("groups").path(groupId).path("users").path(userId)
				.queryParam("createdBy", userId).type("application/json")
				.post(ClientResponse.class);
		assert clientResponseForCreationOfGroupAndAddingUsers.getStatus() == 201;

		ClientResponse clientResponseToReturnAddedUsers = resource
				.path("groups").path(groupId).path("users")
				.type("application/json").get(ClientResponse.class);
		UsersPage usersPage = clientResponseToReturnAddedUsers
				.getEntity(UsersPage.class);

		for (User user : usersPage.getUsers()) {
			assert user.getId().equals(userIdAdmin)
					|| user.getId().equals(userId);
		}
		
		List<String> bulkUserIds = new ArrayList<String>();
		for (int i=0;i<5 ;i++)
		{
			String userIdbulk =UserResourceTest.createUser();
			bulkUserIds.add(userIdbulk);
		}
		
		UserIdsPage userIdsPage = new UserIdsPage(0,bulkUserIds.size(),bulkUserIds);
		
		ClientResponse clientResponseForBulkAdditionOfUsers = resource.path("groups").path(groupId).path("bulk").queryParam("createdBy", userIdAdmin).type("application/json").entity(userIdsPage).post(ClientResponse.class);
		assert clientResponseForBulkAdditionOfUsers.getStatus() == 200;
		
		ClientResponse clientResponseForBulkAdditionOfUsersResp = resource.path("groups").path(groupId).path("users").type("application/json").get(ClientResponse.class);
		assert clientResponseForBulkAdditionOfUsersResp.getStatus() == 200;
		
		UsersPage page = clientResponseForBulkAdditionOfUsersResp.getEntity(UsersPage.class);
		assert page.getSize() > 5;
		


	}

}
