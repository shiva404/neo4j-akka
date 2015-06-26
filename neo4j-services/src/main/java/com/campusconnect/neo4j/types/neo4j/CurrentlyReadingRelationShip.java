package com.campusconnect.neo4j.types.neo4j;

import org.springframework.data.neo4j.annotation.RelationshipEntity;

import com.campusconnect.neo4j.util.Constants;


	@RelationshipEntity(type = Constants.CURRENTLY_READING_RELATION)
	public class CurrentlyReadingRelationShip extends BookRelationship {
	    

	    public CurrentlyReadingRelationShip() {
	        
	    }

	    public CurrentlyReadingRelationShip(User user, Book book, String status) {
	        super(user, book, status);
	        
	    }

	    public CurrentlyReadingRelationShip(User user, Book book, String status, Long createdDate, Long lastModifiedDate) {
	        super(user, book, status, createdDate, lastModifiedDate);
	        
	    }
	   
}
