package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.da.iface.ReminderDao;
import com.campusconnect.neo4j.da.utils.TargetHelper;
import com.campusconnect.neo4j.repositories.ReminderRepository;
import com.campusconnect.neo4j.types.common.IdType;
import com.campusconnect.neo4j.types.common.Target;
import com.campusconnect.neo4j.types.neo4j.Reminder;
import com.campusconnect.neo4j.types.neo4j.ReminderRelationShip;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.web.Notification;
import com.campusconnect.neo4j.util.Constants;

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

//    public Reminder updateReminder(String reminderId, Reminder reminder,String userId) {
//
//        Reminder reminderToBeUpdated = getReminder(reminderId);
//        reminderToBeUpdated.setReminderMessage(reminder.getReminderMessage());
//        reminderToBeUpdated.setReminderTime(reminder.getReminderTime());
//        reminderToBeUpdated.setLastModifiedTime(System.currentTimeMillis());
//
//        return neo4jTemplate.save(reminderToBeUpdated);
//        
//        neo4jTemplate.getre
//        
//        Target reminderTarget = TargetHelper.createReminderTarget(IdType.REMINDER_ID, reminderToBeUpdated, )
//        
//        Notification notificationUpdateReminder =  new Notification(target, timeStamp, type)
//    }

    @Override
    public Reminder getReminder(String reminderId) {
        return reminderRepository.findOne(Long.parseLong(reminderId));
    }

    @Override
    public void deleteReminder(String reminderId,String userId) {
    	reminderRepository.deleteReminderRelationShip(userId,reminderId);
    }


    @Override
    public List<Reminder> getReminders(String userId,String filter) {
    	
    	if(filter.equalsIgnoreCase(Constants.SENT_REMINDER_TYPE))
    	{
    		return reminderRepository.getReminders(userId,filter);
    	}
    	else if(filter.equalsIgnoreCase(Constants.RECEIVED_REMINDER_TYPE))
    	{
    		return reminderRepository.getReminders(userId,filter);
    	}
    	else
    	{
    		return reminderRepository.getAllReminders(userId);
    	}
    }

    @Override
    public void deleteRemindersOfUser(String userId) {
        List<Reminder> allReminders = reminderRepository.getAllReminders(userId);
        for (Reminder reminder : allReminders)
            reminderRepository.delete(reminder);
    }

	
    
   
}
