package com.campusconnect.neo4j.tests.functional;

import org.testng.annotations.Test;

import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.tests.functional.base.DataBrewer;
import com.campusconnect.neo4j.types.Group;
import com.sun.jersey.api.client.ClientResponse;

public class GroupResourceTest extends TestBase{
	
	private static Group createdGroup;
	
	
	public static String createGroup(String userId)
	{
		Group group = DataBrewer.getFakeGroup();		
		ClientResponse clientResponse = resource.path("groups").queryParam("userId", userId).type("application/json").entity(group).post(ClientResponse.class);
		assert clientResponse.getStatus() == 201;
		createdGroup = clientResponse.getEntity(Group.class);
		return createdGroup.getId();
	}
	
	@Test
	public void testGroupResourceFlow()
	{
		Group group = DataBrewer.getFakeGroup();
		String userId = UserResourceTest.createUser(); 
		
		ClientResponse clientResponse = resource.path("groups").queryParam("userId", userId).type("application/json").entity(group).post(ClientResponse.class);
		assert clientResponse.getStatus() == 201;
		createdGroup = clientResponse.getEntity(Group.class);
		
		assert createdGroup.getName().equals(group.getName());
	}

}