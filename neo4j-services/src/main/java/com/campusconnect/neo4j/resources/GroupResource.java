package com.campusconnect.neo4j.resources;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.campusconnect.neo4j.da.GroupDao;
import com.campusconnect.neo4j.da.UserDaoImpl;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.exceptions.InvalidInputDataException;
import com.campusconnect.neo4j.types.AccessRoles;
import com.campusconnect.neo4j.types.Group;
import com.campusconnect.neo4j.types.User;
import com.campusconnect.neo4j.types.UserGroupRelationship;
import com.campusconnect.neo4j.types.UserIdsPage;
import com.campusconnect.neo4j.types.UsersPage;

@Path("/groups")
@Consumes("application/json")
@Produces("application/json")
public class GroupResource {

	GroupDao groupDao;
	UserDao userDao;

	public GroupResource(GroupDao groupDao, UserDao userDao) {
		this.groupDao = groupDao;
		this.userDao = userDao;
	}

	@POST
	public Response createGroup(Group group,
			@QueryParam("userId") final String userId) {
		group.setId(UUID.randomUUID().toString());
		setGroupCreateProperties(group, userId);
		Long currentTime = System.currentTimeMillis();
		Group createdGroup = groupDao.createGroup(group);
		User user = userDao.getUser(userId);
		UserGroupRelationship userGroupRelationship = new UserGroupRelationship(
				userId, currentTime, createdGroup, currentTime,
				AccessRoles.ADMIN.toString(), user);
		// Todo Add user as admin
		groupDao.addUser(userGroupRelationship);
		return Response.created(null).entity(createdGroup).build();
	}

	// @DELETE
	// public Response deleteGroup(Group group)
	// {
	// groupDao.deleteGroup(group);
	// return Response.ok().build();
	// }

	@GET
	@Path("{groupId}")
	public Response getGroup(@PathParam("groupId") final String groupId) {
		Group group = groupDao.getGroup(groupId);
		return Response.created(null).entity(group).build();

	}

	@PUT
	@Path("{groupID}")
	public Response updateGroup(@PathParam("groupId") final String groupId,
			Group group) {
		Group groupToBeUpdated = groupDao.updateGroup(groupId, group);
		return Response.created(null).entity(groupToBeUpdated).build();
	}

	private Group setGroupCreateProperties(Group group, String userId) {
		long createdDate = System.currentTimeMillis();
		group.setCreatedDate(createdDate);
		group.setLastModifiedTime(createdDate);
		group.setLastModifiedBy(userId);
		return group;
	}

	@POST
	@Path("{groupId}/users/{userId}")
	public Response addUser(@PathParam("groupId") final String groupId,
			@PathParam("userId") final String userId,
			@QueryParam("createdBy") final String createdBy) {
		Group group = groupDao.getGroup(groupId);
		User user = userDao.getUser(userId);
		Long currentTime = System.currentTimeMillis();
		UserGroupRelationship userGroupRelationship = new UserGroupRelationship(
				createdBy, currentTime, group, currentTime,
				AccessRoles.READ.toString(), user);
		groupDao.addUser(userGroupRelationship);
		return Response.created(null).build();
	}

	@POST
	@Path("{groupId}/users/bulk")
	public Response addUsers(@PathParam("groupId") final String groupId,
			@QueryParam("createdBy") final String createdBy,
			final UserIdsPage userIdsPage) {
		Group group = groupDao.getGroup(groupId);

		// Todo validate user exist
		// Todo CreatedBy is of Admin or Write USER_ACCESS

		Long currentTime = System.currentTimeMillis();

		for (String userId : userIdsPage.getUserIds()) {
			
			User user = userDao.getUser(userId);
			
			UserGroupRelationship userGroupRelationship = new UserGroupRelationship(
					createdBy, currentTime, group, currentTime,
					AccessRoles.READ.toString(), user);
			groupDao.addUser(userGroupRelationship);
		}
		return Response.ok().build();
	}

	@GET
	@Path("{groupId}/users")
	public Response getUsers(@PathParam("groupId") final String groupId) {
		List<User> users = groupDao.getUsers(groupId);
		UsersPage usersListPage = new UsersPage(0, users.size(), users);
		return Response.ok().entity(usersListPage).build();
	}
}
