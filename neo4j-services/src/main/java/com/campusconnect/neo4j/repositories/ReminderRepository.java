package com.campusconnect.neo4j.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.campusconnect.neo4j.types.Address;
import com.campusconnect.neo4j.types.Reminder;

public interface ReminderRepository extends GraphRepository<Reminder>{

	
	 @Query(value = "match (user:User {id:{0}})-[:REMINDER_ABOUT]->(reminders:Reminder) return reminders")
	    public List<Reminder> getAllReminders(String userId);
	
	

}
