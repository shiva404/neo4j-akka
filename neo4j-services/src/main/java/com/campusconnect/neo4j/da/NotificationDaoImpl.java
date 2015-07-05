package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.da.iface.NotificationDao;
import com.campusconnect.neo4j.da.utils.NotificationHelper;
import com.campusconnect.neo4j.repositories.NotificationRepository;
import com.campusconnect.neo4j.types.common.NotificationType;
import com.campusconnect.neo4j.types.neo4j.NotificationEntity;
import com.campusconnect.neo4j.types.web.Notification;
import com.campusconnect.neo4j.util.Constants;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class NotificationDaoImpl implements NotificationDao {
    @Autowired
    NotificationRepository notificationRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    public NotificationDaoImpl() {
    }

    @Override
    public NotificationEntity saveNotification(
            NotificationEntity notificationEntity) {
        return notificationRepository.save(notificationEntity);
    }

    @Override
    public List<Notification> getNotifications(String userId, String filter) {
        List<Notification> notifications = new ArrayList<Notification>();
        Set<String> notificationsSet = new HashSet<String>();

        if (filter.equalsIgnoreCase("all")) {
            List<NotificationEntity> notificationEntity = notificationRepository.getAllNotificationForUser(userId);

            for (NotificationEntity eachNotificationEntity : notificationEntity) {
                notificationsSet.addAll(eachNotificationEntity.getNotifications());
            }
            //TODO: sort
            for (String eachNotification : notificationsSet) {
                System.out.println("each notification" + eachNotification);
                Notification notification = NotificationHelper.deserializeNotificationString(eachNotification);
                addNotificationActionString(notification);
                notifications.add(notification);
            }
        } else if (filter.equalsIgnoreCase("fresh")) {
            NotificationEntity notificationEntity = notificationRepository.getFreshNotificationForUser(userId,
                    NotificationType.FRESH.toString());
            notificationsSet.addAll(notificationEntity.getNotifications());

            for (String eachNotification : notificationsSet) {
                System.out.println("each notification" + eachNotification);

                Notification notification = NotificationHelper.deserializeNotificationString(eachNotification);
                addNotificationActionString(notification);
                notifications.add(notification);

                //TODO : Add notification string according to notification type
            }
        }
        return notifications;
    }

    private void addNotificationActionString(Notification notification) {
        if (notification.getType().equals(Constants.FRIEND_REQUEST_ACCEPTED_NOTIFICATION_TYPE)) {
            notification.setActionString(" accepted your friend request");
        } else if (notification.getType().equals(Constants.FRIEND_REQUEST_SENT_NOTIFICATION_TYPE)) {
            notification.setActionString(" sent you friend request");
        } else if (notification.getType().equals(Constants.REMINDER_CREATED_NOTIFICATION_TYPE)) {
            notification.setActionString(" added a new reminder");
        }
    }

    @Override
    public NotificationEntity addNotification(String userId,
                                              Notification notification) {
        String notificationEventSerialized = NotificationHelper.serializeNotification(notification);

        NotificationEntity notificationEntity = getFreshNotification(userId);

        Set<String> notifications = notificationEntity.getNotifications();
        notifications.add(notificationEventSerialized);

        return saveNotification(notificationEntity);
    }

    @Override
    public NotificationEntity getFreshNotification(String userId) {
        return notificationRepository.getFreshNotificationForUser(userId,
                NotificationType.FRESH.toString());
    }

    @Override
    public void moveNotification(String userId) {
        NotificationEntity freshNotificationEntity = notificationRepository.getFreshNotificationForUser(userId,
                NotificationType.FRESH.toString());
        NotificationEntity pastNotificationEntity = notificationRepository.getFreshNotificationForUser(userId,
                NotificationType.PAST.toString());

        pastNotificationEntity.getNotifications()
                .addAll(freshNotificationEntity.getNotifications());

        saveNotification(pastNotificationEntity);

        freshNotificationEntity.getNotifications().clear();
        saveNotification(freshNotificationEntity);
    }

    @Override
    public void deleteNotificationOfUser(String userId) {
        List<NotificationEntity> allNotifications = notificationRepository.getAllNotificationForUser(userId);

        for (NotificationEntity notificationEntity : allNotifications) {
            notificationRepository.delete(notificationEntity);
        }
    }
}
