package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.neo4j.NotificationEntity;
import com.campusconnect.neo4j.types.web.Notification;

import java.util.List;

public interface NotificationDao {

    NotificationEntity savenotification(NotificationEntity notificationEntity);

    NotificationEntity addNotification(String userId, Notification notification);

    NotificationEntity getFreshNotification(String userId);

    List<Notification> getNotifications(String userId, String filter);

    void moveNotification(String userId);

    void deleteNotificationOfUser(String userId);
}
