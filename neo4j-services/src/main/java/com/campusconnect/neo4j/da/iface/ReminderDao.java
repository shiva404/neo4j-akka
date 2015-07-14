package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.neo4j.Reminder;
import com.campusconnect.neo4j.types.neo4j.User;

import java.util.List;

public interface ReminderDao {

    Reminder createReminder(Reminder reminder);

    Reminder getReminder(String reminderId);

   // Reminder updateReminder(String reminderId, Reminder reminder, String userId);

    void deleteReminder(String reminderId, String userId);

    List<Reminder> getReminders(String userId, String filter);

    void deleteRemindersOfUser(String userId);

}
