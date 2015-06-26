package com.campusconnect.neo4j.da.mapper;

import com.campusconnect.neo4j.mappers.Neo4jToWebMapper;
import com.campusconnect.neo4j.types.common.BookDetails;
import com.campusconnect.neo4j.types.common.UserRelationType;
import com.campusconnect.neo4j.types.neo4j.*;
import com.campusconnect.neo4j.types.web.AvailableBookDetails;
import com.campusconnect.neo4j.types.web.BorrowedBookDetails;
import com.campusconnect.neo4j.types.web.GroupMember;
import com.campusconnect.neo4j.types.web.LentBookDetails;
import com.campusconnect.neo4j.util.Constants;
import org.neo4j.graphdb.NotFoundException;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.neo4j.rest.graphdb.entity.RestRelationship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.campusconnect.neo4j.mappers.Neo4jToWebMapper.mapUserNeo4jToWebGroupMember;
import static com.campusconnect.neo4j.util.Constants.*;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/15/15
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBMapper {
    private static Logger logger = LoggerFactory.getLogger(DBMapper.class);

    public static GoodreadsRecRelationship getGoodreadsRecRelationship(RestRelationship restRelationship) {
        if (restRelationship.getType().name().equals(GOODREADS_REC_RELATION)) {
            GoodreadsRecRelationship goodreadsRecRelationship = new GoodreadsRecRelationship();
            goodreadsRecRelationship.setFriendGoodreadsId((String) restRelationship.getProperty("friendGoodreadsId", null));
            goodreadsRecRelationship.setFriendImageUrl((String) restRelationship.getProperty("friendImageUrl", null));
            goodreadsRecRelationship.setFriendName((String) restRelationship.getProperty("friendName", null));
            goodreadsRecRelationship.setFriendId((String) restRelationship.getProperty("friendId", null));
            goodreadsRecRelationship.setCreatedDate((Long) restRelationship.getProperty("createdDate", null));

            return goodreadsRecRelationship;

        }
        return null;
    }

    public static UserRelation getUserRelation(RestRelationship userRawRelation) {
        if(userRawRelation.getType().name().equals(CONNECTED_RELATION)){
            UserRelation userRelation = new UserRelation();
            userRelation.setCreatedDate((Long) userRawRelation.getProperty("createdDate", null));
            userRelation.setId((Long) userRawRelation.getProperty("id", null));
            userRelation.setType((String) userRawRelation.getProperty("type", null));
            userRelation.setUser1((User) userRawRelation.getStartNode());
            userRelation.setUser2((User) userRawRelation.getEndNode());
            return userRelation;
        }
        return null;
    }

    public static void setUserRelationFieldsToUser(RestRelationship rawUserRelation, User user) {
        if(user != null && rawUserRelation != null && rawUserRelation.getType().name().equals(CONNECTED_RELATION)) {
            String relationType = (String) rawUserRelation.getProperty("type", null);
            if(relationType.equals(UserRelationType.FRIEND_REQUEST_PENDING.toString()))
                user.setUserRelation(Constants.FRIEND_REQ_SENT);
            else
                user.setUserRelation(relationType);
        }
    }

    public static Group getGroupFromRestNode(RestNode groupNode){
        try{
            Group group = new Group();
            group.setCreatedDate((Long) groupNode.getProperty("createdDate", null));
            group.setId((String) groupNode.getProperty("id", null));
            group.setLastModifiedBy((String) groupNode.getProperty("lastModifiedBy", null));
            group.setLastModifiedTime((Long) groupNode.getProperty("lastModifiedTime", null));
            group.setName((String) groupNode.getProperty("lastModifiedTime", null));
            group.setNodeId((Long) groupNode.getProperty("nodeId", null));
            return group;
        } catch (Exception e) {
            logger.error("Error while constructing user object:" + groupNode.getProperty("id"));
        }
        return null;
    }

    public static GroupMember getGroupMember(User user, RestRelationship rawRelationship, String groupId) {
        if(user != null && rawRelationship != null) {
            String role = (String) rawRelationship.getProperty("role", null);
            Long createdDate = (Long) rawRelationship.getProperty("createdDate", null);
            //fixme groupName returned null
            return mapUserNeo4jToWebGroupMember(user, groupId, null, createdDate, role);
        }
        else if(user != null)
            //fixme groupName returned null
            return mapUserNeo4jToWebGroupMember(user, groupId, null, 0L, null);
        else
            return null;
    }

    public static User getUserFromRestNode(RestNode userNode) {
        try{
            User user = new User();
            user.setCreatedDate((Long) userNode.getProperty("createdDate", null));
            user.setEmail((String) userNode.getProperty("email", null));
            //TODO: getting fav of user
            //user.setFavorites();
            user.setFbId((String) userNode.getProperty("fbId", null));
            user.setGender((String) userNode.getProperty("gender", null));
            user.setGoodreadsAccessToken((String) userNode.getProperty("goodreadsAccessToken", null));
            user.setGoodreadsAccessTokenSecret((String) userNode.getProperty("goodreadsAccessTokenSecret", null));
            user.setGoodreadsAuthStatus((String) userNode.getProperty("goodreadsAuthStatus", null));
            user.setGoodreadsId((String) userNode.getProperty("goodreadsId", null));
            user.setGoodReadsSynchStatus((String) userNode.getProperty("goodReadsSynchStatus", null));
            user.setGoogleId((String) userNode.getProperty("googleId", null));
            user.setId((String) userNode.getProperty("id", null));
            //TODO: fix this
//            user.setLastGoodreadsSychDate((Long) userNode.getProperty("lastGoodreadsSychDate", null));
            user.setLastModifiedDate((Long) userNode.getProperty("lastModifiedDate", null));
            user.setName((String) userNode.getProperty("name", null));
            user.setNodeId((Long) userNode.getProperty("nodeId", null));
            user.setPhone((String) userNode.getProperty("phone", null));
            user.setProfileImageUrl((String) userNode.getProperty("profileImageUrl", null));
            user.setWorkDesignation((String) userNode.getProperty("workDesignation", null));
            user.setWorkLocation((String) userNode.getProperty("workLocation", null));
            return user;
        }catch (Exception e) {
            logger.error("Error while constructing user object:" + userNode.getProperty("id", null));
        }
        return null;
    }


    public static Book getBookFromRestNode(RestNode restNode) {
        try {
            Book book = new Book();
            book.setAuthorName((String) restNode.getProperty("authorName", null));
            book.setDescription((String) restNode.getProperty("description", null));
            book.setGoodreadsAuthorId((String) restNode.getProperty("goodreadsAuthorId", null));
            book.setGoodreadsId((Integer) restNode.getProperty("goodreadsId", null));
            book.setId((String) restNode.getProperty("id", null));
            book.setImageUrl((String) restNode.getProperty("imageUrl", null));
            book.setIsbn((String) restNode.getProperty("isbn", null));
            book.setIsbn13((String) restNode.getProperty("isbn13", null));
            book.setName((String) restNode.getProperty("name", null));
            book.setNodeId(restNode.getId());
            book.setNumberOfPages((Integer) restNode.getProperty("numberOfPages", null));
            book.setPublishedYear((Integer) restNode.getProperty("publishedYear", null));
            book.setPublisher((String) restNode.getProperty("publisher", null));
            return book;
        } catch (NotFoundException e) {
            logger.debug("Ignoring field not found exception, message:" + e.getMessage());
        }
        return null;
    }

    public static Book setRelationDetailsToBook(RestRelationship rawRelationship, Book book) {
        switch (rawRelationship.getType().name()) {
            case OWNS_RELATION:
                book.setBookType(OWNS_RELATION);
                book.setBookDetails(getOwnsBookDetails(rawRelationship));
                break;
            case BORROWED_RELATION:
                book.setBookType(BORROWED_RELATION);
                book.setBookDetails(getBorrowBookDetails(rawRelationship));
                break;
            case READ_RELATION:
                book.setBookType(READ_RELATION);
                break;
            case WISHLIST_RELATION:
                book.setBookType(WISHLIST_RELATION);
                break;
        }
        return book;
    }

    private static BookDetails getOwnsBookDetails(RestRelationship ownsRelationship) {
        //todo return based on the status
        return new AvailableBookDetails();
        /*
        wnedBook.setCreatedDate(ownsRelationship.getCreatedDate());
            ownedBook.setStatus(ownsRelationship.getStatus());
            ownedBook.setLastModifiedDate(ownsRelationship.getLastModifiedDate());
            ownedBook.setBorrowerId(ownsRelationship.getBorrowerId());
            ownedBook.setDueDate(ownsRelationship.getDueDate());
         */
    }

    private static BorrowedBookDetails getBorrowBookDetails(RestRelationship borrowRelation) {
        return new BorrowedBookDetails();
        /*
                    borrowedBook.setStatus(borrowRelationship.getStatus());
            borrowedBook.setDueDate(borrowRelationship.getDueDate());
            borrowedBook.setCreatedDate(borrowRelationship.getCreatedDate());
            borrowedBook.setOwnerUserId(borrowRelationship.getOwnerUserId());
            borrowedBook.setAdditionalComments(borrowRelationship.getAdditionalComments());
            borrowedBook.setBorrowDate(borrowRelationship.getBorrowDate());
            borrowedBook.setContractPeriodInDays(borrowRelationship.getContractPeriodInDays());
            borrowedBook.setLastModifiedDate(borrowRelationship.getLastModifiedDate());
         */
    }

    private static LentBookDetails getLentBookDetails(RestRelationship ownsRelationship) {
        return new LentBookDetails();
    }

}
