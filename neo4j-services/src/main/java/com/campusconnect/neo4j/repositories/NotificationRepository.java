package com.campusconnect.neo4j.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.campusconnect.neo4j.types.AuditEvent;
import com.campusconnect.neo4j.types.Notification;
import com.campusconnect.neo4j.types.NotificationEntity;

public interface NotificationRepository extends GraphRepository<NotificationEntity>{

	@Query(value = "MATCH (user:User {id:{0}})-[:NOTIFICATION]-(notificationEntity:NotificationEntity) return notificationEntity")
	List<NotificationEntity> getAllNotificationForUser(String userId);
	

	@Query(value = "MATCH (user:User {id:{0}})-[:NOTIFICATION {type:{1}}]-(notificationEntity:NotificationEntity) return notificationEntity")
	NotificationEntity getFreshNotificationForUser(String userId,String type);
	
	@Query(value = "MATCH (user:User {id:{0}})-[:NOTIFICATION {type:{1}}]-(notificationEntity:NotificationEntity) return notificationEntity")
	NotificationEntity getPastNotificationForUser(String userId,String type);
	
}
