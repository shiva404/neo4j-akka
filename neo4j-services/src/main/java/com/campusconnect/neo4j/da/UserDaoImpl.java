package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.akka.goodreads.GoodreadsAsynchHandler;
import com.campusconnect.neo4j.da.iface.AuditEventDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.repositories.UserRepository;
import com.campusconnect.neo4j.types.*;
import com.googlecode.ehcache.annotations.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.neo4j.rest.graphdb.entity.RestRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import java.util.*;

/**
 * Created by sn1 on 1/19/15.
 */
public class UserDaoImpl implements UserDao {
	
	
	@Autowired
    UserRepository userRepository;
    @Autowired
    GoodreadsAsynchHandler goodreadsAsynchHandler;
    @Autowired 
    AuditEventDao auditEventDao;
    
    @Autowired
    private FBDao fbDao;
    
    private Neo4jTemplate neo4jTemplate;
    ObjectMapper objectMapper;

    public UserDaoImpl(Neo4jTemplate neo4jTemplate) {
    	
        this.neo4jTemplate = neo4jTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public User createUser(User user, String accessToken) {
        user.setId(UUID.randomUUID().toString());
        if (accessToken != null && user.getFbId() != null) {
            String profileImageUrl = fbDao.getUserProfileImage(user.getFbId(), accessToken);
            if (profileImageUrl != null) {
                user.setProfileImageUrl(profileImageUrl);
            }
        }
      
        User createdUser = neo4jTemplate.save(user);
        try
        {
        Long currentTime = System.currentTimeMillis();
        Event userCreatedEvent = new Event(AuditEventType.CREATED.toString(), null,currentTime);
        String serializedEvent = objectMapper.writeValueAsString(userCreatedEvent);
    	AuditEvent auditEvent = new AuditEvent();
    	Set<String> events = auditEvent.getEvents();
    	events.add(serializedEvent);
    	auditEvent = auditEventDao.saveEvent(auditEvent);
    		UserEventRelationship userEventRelationship = new UserEventRelationship(auditEvent , createdUser);
    		neo4jTemplate.save(userEventRelationship);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return createdUser;
    }

	@Override
    @Cacheable(cacheName = "userIdCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public User getUser(String userId) {
        return userRepository.findBySchemaPropertyValue("id", userId);
    }

    @Override
    public User getUserByFbId(String fbId) {
        return userRepository.findBySchemaPropertyValue("fbId", fbId);
    }

    @Override
    @TriggersRemove(cacheName = "userFollowing", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public void createFollowingRelation(@PartialCacheKey User user1, User user2) {
   
    	neo4jTemplate.createRelationshipBetween(user1, user2, FollowingRelation.class, UserRelationType.FOLLOWING.toString(), false);
    	
    	try
    	{
    	Long currentTime = System.currentTimeMillis();
    	String targetUserId = user2.getId();
    	String targetUserName = user2.getName();
    	String targetUrl = "users/" + targetUserId;
    	Target target = new Target(IdType.USER_ID.toString(), targetUserName, targetUrl);	
    	Event followedUSerEvent = new Event(AuditEventType.FOLLOWING.toString(), target,currentTime);
    	auditEventDao.addEvent(user1.getId(), followedUSerEvent);
    	
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    @Override
    @TriggersRemove(cacheName = "userIdCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public User updateUser(@PartialCacheKey String userId, User user) {
    	return neo4jTemplate.save(user);
    }

    @Override
    @Cacheable(cacheName = "userFollowers", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public List<User> getFollowers(String userId) {
        return userRepository.getFollowers(userId);
    }

    @Override
    @Cacheable(cacheName = "userFollowing", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public List<User> getFollowing(String userId) {
        return userRepository.getFollowing(userId);
    }

    @Override
    @Cacheable(cacheName = "userOwnedBooks", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public List<OwnedBook> getOwnedBooks(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[relation:OWNS]->(books:Book) return books, relation", params);
        return getOwnedBooksFromResultMap(mapResult);
    }

    @Override
    public List<OwnedBook> getAvailableBooks(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[relation:OWNS {status: \"available\"}]->(books:Book) return books, relation", params);
        return getOwnedBooksFromResultMap(mapResult);
    }

    @Override
    public List<OwnedBook> getLentBooks(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[relation:OWNS {status: \"lent\"}]->(books:Book) return books, relation", params);
        return getOwnedBooksFromResultMap(mapResult);
    }

    private List<OwnedBook> getOwnedBooksFromResultMap(Result<Map<String, Object>> mapResult) {
        List<OwnedBook> ownedBooks = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode bookNode = (RestNode) objectMap.get("books");
            RestRelationship rawOwnsRelationship = (RestRelationship) objectMap.get("relation");

            Book book = neo4jTemplate.convert(bookNode, Book.class);
            OwnsRelationship ownsRelationship = neo4jTemplate.convert(rawOwnsRelationship, OwnsRelationship.class);
            ownedBooks.add(new OwnedBook(book, ownsRelationship));
        }
        return ownedBooks;
    }

    @Override
    @Cacheable(cacheName = "userBorrowedBooks", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public List<BorrowedBook> getBorrowedBooks(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[relation:BORROWED]->(books:Book) return books, relation", params);
        return getBorrowedBooksFromResultMap(mapResult);
    }
    
    @Override
    public void addAddressToUser(Address address, User user){
        if(address.getId() == null)
            address = neo4jTemplate.save(address);
        AddressRelation addressRelation = new AddressRelation(user, address);
        neo4jTemplate.save(addressRelation);
        
        try
        {
        	Long currentTime = System.currentTimeMillis();
        	
        	String targetEvent = objectMapper.writeValueAsString(address);
        	
        	Target target = new Target(IdType.USER_ID.toString(), targetEvent, null);	
        	Event addAddressToUserEvent = new Event(AuditEventType.ADDED_ADDRESS.toString(), target,currentTime);
        	auditEventDao.addEvent(user.getId(), addAddressToUserEvent);

        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    } 
    
    
    @Override
    @Cacheable(cacheName = "userWishBooks", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public List<WishListBook> getWishListBooks(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[relation:WISH]->(books:Book) return books, relation", params);
        return getWishListBooksFromResultMap(mapResult);
    }

    @Override
    public void synchWishListRec(String userId) {
        User user = getUser(userId);
        if (user != null)
            goodreadsAsynchHandler.getFriendRecForUser(userId, user.getGoodreadsId(), user.getGoodreadsAccessToken(), user.getGoodreadsAccessTokenSecret());
    }

    @Override
    public List<UserRecommendation> getUserRecommendations(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        Result<Map<String, Object>> mapResult = neo4jTemplate.query("match (users:User {id: {userId}})-[relation:GR_REC]->(books:Book) return books, relation", params);
        return getWishUserRecFromResultMap(mapResult);
    }

    private List<WishListBook> getWishListBooksFromResultMap(Result<Map<String, Object>> mapResult) {
        List<WishListBook> wishListBooks = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode bookNode = (RestNode) objectMap.get("books");
            RestRelationship rawWishRelationship = (RestRelationship) objectMap.get("relation");

            Book book = neo4jTemplate.convert(bookNode, Book.class);
            WishListRelationship whishListRelationship = neo4jTemplate.convert(rawWishRelationship, WishListRelationship.class);
            wishListBooks.add(new WishListBook(book, whishListRelationship));
        }
        return wishListBooks;
    }

    private List<UserRecommendation> getWishUserRecFromResultMap(Result<Map<String, Object>> mapResult) {
        List<UserRecommendation> userRecommendations = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode bookNode = (RestNode) objectMap.get("books");
            RestRelationship rawWishRelationship = (RestRelationship) objectMap.get("relation");

            Book book = neo4jTemplate.convert(bookNode, Book.class);
            GoodreadsFriendBookRecRelation goodreadsFriendBookRecRelation = neo4jTemplate.convert(rawWishRelationship, GoodreadsFriendBookRecRelation.class);
            userRecommendations.add(new UserRecommendation(book, goodreadsFriendBookRecRelation));
        }
        return userRecommendations;
    }

    private List<BorrowedBook> getBorrowedBooksFromResultMap(Result<Map<String, Object>> mapResult) {
        List<BorrowedBook> borrowedBooks = new ArrayList<>();
        for (Map<String, Object> objectMap : mapResult) {
            RestNode bookNode = (RestNode) objectMap.get("books");
            RestRelationship rawOwnsRelationship = (RestRelationship) objectMap.get("relation");

            Book book = neo4jTemplate.convert(bookNode, Book.class);
            BorrowRelation borrowRelationship = neo4jTemplate.convert(rawOwnsRelationship, BorrowRelation.class);
            borrowedBooks.add(new BorrowedBook(book, borrowRelationship));
        }
        return borrowedBooks;
    }
    
    @Override
	public void setReminder(ReminderRelationShip reminderRelationShip) {
		
		neo4jTemplate.save(reminderRelationShip);
		
	}

	@Override
	public void createFriendRelation(User user, User user2) {
		k
		
	}
}
