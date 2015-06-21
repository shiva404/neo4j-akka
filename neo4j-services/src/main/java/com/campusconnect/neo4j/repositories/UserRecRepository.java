package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.neo4j.GoodreadsRecRelationship;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by sn1 on 4/24/15.
 */
public interface UserRecRepository extends GraphRepository<GoodreadsRecRelationship> {

    @Query(value = "MATCH (user: User {id: {0}})-[grec:GR_REC]-(book: Book{id:{1}})return grec")
    public List<GoodreadsRecRelationship> getGoodreadsFriendBookRecRelations(String userId, String bookId);

}
