package com.campusconnect.neo4j.repositories;

import java.util.List;

import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.BorrowRelationship;
import com.campusconnect.neo4j.types.neo4j.OwnsRelationship;
import com.campusconnect.neo4j.types.neo4j.User;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by I308260 on 2/17/2015.
 */
public interface BookRepository extends GraphRepository<Book> {
	
	 @Query(value = "match (user:User {id:{0}})-[r:OWNS]-(book:Book{id:{1}}) return r")
	    public OwnsRelationship getOwnsRelationship(String userId,String  bookId);
	 
	 @Query(value = "match (user:User {id:{0}})-[r:BORROWED {ownerUserId:{2}}]-(book:Book{id:{1}}) return r")
	    public BorrowRelationship getBorrowRelationship(String userIdborrower,String  bookId,String userIdOwner);
	 
	 
	 
}
