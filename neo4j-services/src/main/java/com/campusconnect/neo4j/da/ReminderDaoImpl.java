package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.da.iface.ReminderDao;
import com.campusconnect.neo4j.repositories.ReminderRepository;
import com.campusconnect.neo4j.types.neo4j.Reminder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import java.util.List;

public class ReminderDaoImpl implements ReminderDao {

    @Autowired
    private ReminderRepository reminderRepository;

    private Neo4jTemplate neo4jTemplate;

    public ReminderDaoImpl(Neo4jTemplate neo4jTemplate) {
        this.neo4jTemplate = neo4jTemplate;
        // TODO Auto-generated constructor stub
    }

    public ReminderDaoImpl() {
    }

    @Override
    public Reminder createReminder(Reminder reminder) {

        return neo4jTemplate.save(reminder);
    }

    public Reminder updateReminder(String reminderId, Reminder reminder) {

        Reminder reminderToBeUpdated = getReminder(reminderId);
        reminderToBeUpdated.setReminderMessage(reminder.getReminderMessage());
        reminderToBeUpdated.setReminderTime(reminder.getReminderTime());
        reminderToBeUpdated.setLastModifiedTime(System.currentTimeMillis());

        return neo4jTemplate.save(reminderToBeUpdated);
    }

    @Override
    public Reminder getReminder(String reminderId) {

        return reminderRepository.findOne(Long.parseLong(reminderId));

    }

    @Override
    public void deleteReminder(String reminderId) {
        reminderRepository.delete(Long.parseLong(reminderId));
    }


    @Override
    public List<Reminder> getAllReminders(String userId) {
        return reminderRepository.getAllReminders(userId);
    }

}
