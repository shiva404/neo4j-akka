package com.campusconnect.neo4j.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.campusconnect.neo4j.types.AuditEvent;

public interface AuditEventRepository extends GraphRepository<AuditEvent>{

	void getAuditEventForUser(String userId);

}
