package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.neo4j.Reminder;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface ReminderRepository extends GraphRepository<Reminder> {


    @Query(value = "match (user:User {id:{0}})-[:REMINDER_ABOUT]->(reminders:Reminder) return reminders")
    public List<Reminder> getAllReminders(String userId);


}
