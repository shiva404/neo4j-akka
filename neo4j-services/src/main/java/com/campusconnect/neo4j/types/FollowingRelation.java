package com.campusconnect.neo4j.types;

import org.springframework.data.neo4j.annotation.RelationshipEntity;

/**
 * Created by sn1 on 1/19/15.
 */
@RelationshipEntity(type = "FOLLOWING")
public class FollowingRelation extends UserRelation {

}
