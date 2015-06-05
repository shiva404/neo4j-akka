package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.repositories.GoodreadsRecRepository;
import com.campusconnect.neo4j.types.neo4j.GoodreadsFriendBookRecRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;


/**
 * Created by sn1 on 5/5/15.
 */
public class UtilsDao {

    @Autowired
    GoodreadsRecRepository goodreadsRecRepository;

    Neo4jTemplate neo4jTemplate;

    public void getAndReplaceGRRecByGoodreadsId(Integer goodreadsId, String userId, String imageUrl) {
        for (GoodreadsFriendBookRecRelation goodreadsFriendBookRecRelation : goodreadsRecRepository.getGoodreadsRecById(goodreadsId)) {
            goodreadsFriendBookRecRelation.setFriendId(userId);
            goodreadsFriendBookRecRelation.setFriendImageUrl(imageUrl);
            goodreadsRecRepository.save(goodreadsFriendBookRecRelation);
        }
    }

}
