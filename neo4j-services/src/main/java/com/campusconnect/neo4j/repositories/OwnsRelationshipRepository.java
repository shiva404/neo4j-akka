package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.OwnsRelationship;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by sn1 on 2/25/15.
 */
public interface OwnsRelationshipRepository extends GraphRepository<OwnsRelationship> {
}
