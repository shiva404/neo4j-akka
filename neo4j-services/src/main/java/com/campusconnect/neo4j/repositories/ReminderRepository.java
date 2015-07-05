package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.neo4j.Reminder;
import com.campusconnect.neo4j.types.neo4j.ReminderRelationShip;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface ReminderRepository extends GraphRepository<Reminder> {


    @Query(value = "match (user:User {id:{0}})-[r:REMINDER_ABOUT{type:{1}}]->(reminders:Reminder) return reminders")
    public List<Reminder> getReminders(String userId, String filter);

    @Query(value = "match (user:User {id:{0}})-[reminderRelationship:REMINDER_ABOUT]-(reminders:Reminder{id:{1}}) delete reminderRelationship")
    public ReminderRelationShip deleteReminderRelationShip(String userId, String reminderId);

    @Query(value = "match (user:User {id:{0}})-[r:REMINDER_ABOUT}]->(reminders:Reminder) return reminders")
	public List<Reminder> getAllReminders(String userId);

}
