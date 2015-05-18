package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.Group;
import com.campusconnect.neo4j.types.User;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by sn1 on 1/23/15.
 */
public interface GroupRepository extends GraphRepository<Group> {
    @Query("match (users:User)-[:USER_ACCESS]->(group:Group {id: {0}}) return users")
    public List<User> getUsers(String groupId);

    @Query("match (user:User {id:{0}})-[:USER_ACCESS]-(groups:Group) return groups")
    public List<Group> getGroups(String userId);
    
    @Query("match (books:Book)-[:OWNS]-(users:User)-[:USER_ACCESS]-(group:Group) return books")
	public List<Book> getAvailableBooks(String groupId);
    
    @Query("match (books:Book)-[:WISH]-(user:User)-[:USER_ACCESS]-(group:Group) return books")
    public List<Book> getWishListBooks(String groupID);
}