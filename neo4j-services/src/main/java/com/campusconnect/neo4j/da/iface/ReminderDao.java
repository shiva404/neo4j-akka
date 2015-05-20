package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.Reminder;

import java.util.List;

public interface ReminderDao {

    Reminder createReminder(Reminder reminder);

    Reminder getReminder(String reminderId);

    Reminder updateReminder(String reminderId, Reminder reminder);

    void deleteReminder(String reminderId);

    List<Reminder> getAllReminders(String userId);


}
