package com.campusconnect.neo4j.resources;

import com.campusconnect.neo4j.da.iface.ReminderDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


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
