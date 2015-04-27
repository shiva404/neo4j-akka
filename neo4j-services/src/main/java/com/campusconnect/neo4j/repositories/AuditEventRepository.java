package com.campusconnect.neo4j.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.campusconnect.neo4j.types.AuditEvent;

public interface AuditEventRepository extends GraphRepository<AuditEvent>{

	@Query(value = "MATCH (user:User {id:{0}})-[:ACTIVITY]-(auditEvent:AuditEvent) return auditEvent")
	AuditEvent getAuditEventForUser(String userId);

}
