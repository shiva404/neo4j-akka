package com.campusconnect.neo4j.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.campusconnect.neo4j.da.iface.AuditEventDao;
import com.campusconnect.neo4j.repositories.AuditEventRepository;
import com.campusconnect.neo4j.types.AuditEvent;

public class AuditEventDaoImpl implements AuditEventDao {
	
	@Autowired
	AuditEventRepository auditEventRepository;
	

	@Override
	public void createEvent(AuditEvent auditEvent) {
		
		
		auditEventRepository.save(auditEvent);
		
	}

	@Override
	public AuditEvent getEvents(String userId) {
		
		//write query and uncomment
		//auditEventRepository.getAuditEventForUser(userId);
		return null;
	}

}
