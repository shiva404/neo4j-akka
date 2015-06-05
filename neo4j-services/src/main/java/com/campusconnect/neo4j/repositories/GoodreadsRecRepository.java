package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.neo4j.GoodreadsFriendBookRecRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * Created by sn1 on 5/5/15.
 */
public interface GoodreadsRecRepository extends GraphRepository<GoodreadsFriendBookRecRelation> {

    @Query("MATCH (user:User) - [grec:GR_REC {friendGoodreadsId:{0}}]-(book: Book) return grec")
    public List<GoodreadsFriendBookRecRelation> getGoodreadsRecById(Integer goodreadsId);

}
