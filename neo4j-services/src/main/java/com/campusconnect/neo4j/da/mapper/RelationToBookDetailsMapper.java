package com.campusconnect.neo4j.da.mapper;

import com.campusconnect.neo4j.types.common.BookDetails;
import com.campusconnect.neo4j.types.neo4j.BorrowRelation;
import com.campusconnect.neo4j.types.neo4j.OwnsRelationship;
import com.campusconnect.neo4j.types.web.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/6/15
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class RelationToBookDetailsMapper {

    public static BookDetails getOwnsBookDetails(OwnsRelationship ownsRelationship) {

        //todo return based on the status
        return new AvailableBookDetails();
    }

    public static BorrowedBookDetails getBorrowBookDetails(BorrowRelation borrowRelation) {
        return new BorrowedBookDetails();
    }

    public static LentBookDetails getLentBookDetails(OwnsRelationship ownsRelationship) {
        return new LentBookDetails();
    }

    public static WishlistBookDetails getWishlistBookDetails(List<UserRecommendation> userRecommendations) {
        return new WishlistBookDetails();
    }

}
