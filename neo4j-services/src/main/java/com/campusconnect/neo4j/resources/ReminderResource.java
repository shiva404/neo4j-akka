package com.campusconnect.neo4j.resources;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.campusconnect.neo4j.da.iface.ReminderDao;
import com.campusconnect.neo4j.types.Group;
import com.campusconnect.neo4j.types.Reminder;


@Path("/reminders")
@Consumes("application/json")
@Produces("application/json")
public class ReminderResource {

	private ReminderDao reminderDao;

	public ReminderResource(ReminderDao reminderDao) {
		super();
		this.reminderDao = reminderDao;
	}
}
