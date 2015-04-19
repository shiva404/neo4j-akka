package com.campusconnect.neo4j.types;

import java.util.List;

public class ReminderPage {
	
	private int offset;

	List<Reminder> reminders;

	private int size;
	
	public ReminderPage()
	{
		
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

	public List<Reminder> getReminders() {
		return reminders;
	}

	public int getSize() {
		return size;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	public void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
