package com.campusconnect.neo4j.types.neo4j;

import com.campusconnect.neo4j.types.common.Constants;
import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;


@RelationshipEntity(type = Constants.ACTIVITY_RELATION)
public class UserEventRelationship {
    @EndNode
    private AuditEvent auditEvent;

    @GraphId
    private Long id;

    @StartNode
    private User user;

    public UserEventRelationship() {
        super();
    }

    public UserEventRelationship(AuditEvent auditEvent, User user) {
        super();
        this.auditEvent = auditEvent;
        this.user = user;
    }

    public AuditEvent getEvent() {
        return auditEvent;
    }

    public void setEvent(AuditEvent auditEvent) {
        this.auditEvent = auditEvent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
