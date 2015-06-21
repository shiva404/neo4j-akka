package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.neo4j.Reminder;

import java.util.List;

public class ReminderPage {

    List<Reminder> reminders;
    private int offset;
    private int size;

    public ReminderPage() {

    }

    public ReminderPage(int offset, int size, List<Reminder> reminders) {
        super();
        this.offset = offset;
        this.size = size;
        this.reminders = reminders;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
