package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.neo4j.NotificationEntity;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface NotificationRepository extends GraphRepository<NotificationEntity> {

    @Query(value = "MATCH (user:User {id:{0}})-[:NOTIFICATION]-(notificationEntity:NotificationEntity) return notificationEntity")
    List<NotificationEntity> getAllNotificationForUser(String userId);


    @Query(value = "MATCH (user:User {id:{0}})-[:NOTIFICATION {type:{1}}]-(notificationEntity:NotificationEntity) return notificationEntity")
    NotificationEntity getFreshNotificationForUser(String userId, String type);

    @Query(value = "MATCH (user:User {id:{0}})-[:NOTIFICATION {type:{1}}]-(notificationEntity:NotificationEntity) return notificationEntity")
    NotificationEntity getPastNotificationForUser(String userId, String type);

}
