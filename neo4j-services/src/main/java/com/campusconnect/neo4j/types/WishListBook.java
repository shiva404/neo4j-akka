package com.campusconnect.neo4j.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sn1 on 3/22/15.
 */
public class WishListBook extends Book implements Serializable {

    private List<UserRecommendation> userRecommendations;

    public WishListBook(Book book, WishListRelationship whishListRelationship) {
        setId(book.getId());
        setName(book.getName());
        setIsbn(book.getIsbn());
        setImageUrl(book.getImageUrl());
        setIsbn13(book.getIsbn13());
        setAuthorName(book.getAuthorName());
        setDescription(book.getDescription());
        setGoodreadsAuthorId(book.getGoodreadsAuthorId());
        setGoodreadsId(book.getGoodreadsId());
        setNodeId(book.getNodeId());
        setNumberOfPages(book.getNumberOfPages());
        setPublishedYear(book.getPublishedYear());
        setPublisher(book.getPublisher());
        setBookType(book.getBookType());
    }

    public WishListBook() {
    }

    public List<UserRecommendation> getUserRecommendations() {
        if (userRecommendations == null)
            userRecommendations = new ArrayList<>();
        return userRecommendations;
    }
}
