package com.campusconnect.neo4j.resources;

import com.campusconnect.neo4j.da.GroupDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.da.utils.FilterHelper;
import com.campusconnect.neo4j.mappers.Neo4jToWebMapper;
import com.campusconnect.neo4j.types.common.AccessRoles;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.Group;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.web.BooksPage;
import com.campusconnect.neo4j.types.web.GroupMember;
import com.campusconnect.neo4j.types.web.GroupMembersPage;
import com.campusconnect.neo4j.types.web.UserIdsPage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.campusconnect.neo4j.mappers.Neo4jToWebMapper.mapGroupNeo4jToWeb;
import static com.campusconnect.neo4j.mappers.WebToNeo4jMapper.mapGroupWebToNeo4j;

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

    public GroupResource() {
    }

    @POST
    public Response createGroup(com.campusconnect.neo4j.types.web.Group group, @QueryParam("userId") final String userId) {
        group.setId(UUID.randomUUID().toString());
        setGroupCreateProperties(group, userId);
        Group createdGroup = groupDao.createGroup(mapGroupWebToNeo4j(group));
        // Todo Add user as admin
        groupDao.addUser(createdGroup.getId(), userId, AccessRoles.ADMIN.toString(), userId);
        return Response.created(null).entity(mapGroupNeo4jToWeb(createdGroup)).build();
    }

    @DELETE
    @Path("{groupId}")
    public Response deleteGroup(@PathParam("groupId") String groupId,@QueryParam("userId") String userId) {
        groupDao.deleteGroupByAdmin(groupId,userId);
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{groupId}/users/userId")
    public Response exitFromGroup(@PathParam("groupId") String groupId ,@PathParam("userId") String userId)
    {
    	groupDao.exitFromGroup(groupId,userId);
    	return Response.ok().build();
    }

    @GET
    @Path("{groupId}")
    public Response getGroup(@PathParam("groupId") final String groupId) {
        Group group = groupDao.getGroup(groupId);
        return Response.ok().entity(mapGroupNeo4jToWeb(group)).build();
    }

    @PUT
    @Path("{groupId}")
    public Response updateGroup(@PathParam("groupId") final String groupId,
                                com.campusconnect.neo4j.types.web.Group group,@QueryParam("userId") String userId) {
        
        Group groupUpdated = groupDao.updateGroup(groupId, mapGroupWebToNeo4j(group),userId);
       
        return Response.created(null).entity(mapGroupNeo4jToWeb(groupUpdated)).build();
    }

  

    private com.campusconnect.neo4j.types.web.Group setGroupCreateProperties(com.campusconnect.neo4j.types.web.Group group, String userId) {
        Long createdDate = System.currentTimeMillis();
        group.setCreatedDate(createdDate);
        group.setLastModifiedTime(createdDate);
        group.setLastModifiedBy(userId);
        return group;
    }

    @POST
    @Path("{groupId}/users/{userId}")
    public Response addUser(@PathParam("groupId") final String groupId,
                            @PathParam("userId") final String userId, @QueryParam("role") final String role,
                            @QueryParam("createdBy") final String createdBy) {
        groupDao.addUser(groupId, userId, role, createdBy);
        //TODO: Check userId exists or not
        //TODO :Should event or notification be added 
        return Response.created(null).build();
    }

    @GET
    @Path("{groupId}/search/users")
    public Response searchMembers(@PathParam("groupId") String groupId, @QueryParam("q") String searchString) {
        List<User> searchUsers = userDao.search(searchString, null);
        List<GroupMember> groupMembers = groupDao.getMembers(groupId, null);
        List<GroupMember> searchedUsers = new ArrayList<>();
        for (User user : searchUsers) {
            searchedUsers.add(Neo4jToWebMapper.mapUserNeo4jToWebGroupMember(user, null, null, null, null));
        }
        List<GroupMember> resultGroupMembers = FilterHelper.groupMembersMergeWithMembers(searchedUsers, groupMembers);
        return Response.ok().entity(new GroupMembersPage(resultGroupMembers, resultGroupMembers.size(), 0)).build();
    }

    @POST
    @Path("{groupId}/users/bulk")
    public Response addUsers(@PathParam("groupId") final String groupId,
                             @QueryParam("createdBy") final String createdBy,
                             final UserIdsPage userIdsPage) {
        // Todo validate user exist
        // Todo CreatedBy is of Admin or Write USER_ACCESS
        for (String userId : userIdsPage.getUserIds()) {
//            User user = userDao.getUser(userId);
            groupDao.addUser(groupId, userId, AccessRoles.READ.toString(), createdBy);
        }
        return Response.ok().build();
    }

    @GET
    @Path("{groupId}/users")
    public Response getUsers(@PathParam("groupId") final String groupId, @QueryParam("loggedInUser") String loggedInUser) {
        List<GroupMember> users = groupDao.getMembers(groupId, loggedInUser);
        GroupMembersPage groupMembers = new GroupMembersPage(users, users.size(), 0);
        return Response.ok().entity(groupMembers).build();
    }

    @GET
    @Path("{groupId}/books")
    public Response getBooks(@PathParam("groupId") final String groupId, @QueryParam("filter") String filterParam) {
        if (null == filterParam || filterParam.equals("") || filterParam.toLowerCase().equals("available")) {
            List<Book> books = groupDao.getavailableBooks(groupId);
            List<com.campusconnect.neo4j.types.web.Book> returnBooks = new ArrayList<>();
            for (Book book : books)
                returnBooks.add(Neo4jToWebMapper.mapBookNeo4jToWeb(book));

            BooksPage booksPage = new BooksPage(0, returnBooks.size(), returnBooks);
            return Response.ok().entity(booksPage).build();
        } else if (filterParam.toLowerCase().equals("lookingfor")) {
            List<Book> books = groupDao.getWishListBooks(groupId);
            List<com.campusconnect.neo4j.types.web.Book> returnBooks = new ArrayList<>();
            for (Book book : books)
                returnBooks.add(Neo4jToWebMapper.mapBookNeo4jToWeb(book));
            BooksPage booksPage = new BooksPage(0, returnBooks.size(), returnBooks);
            return Response.ok().entity(booksPage).build();
        }
        return Response.ok().entity(new BooksPage()).build();
    }
}
