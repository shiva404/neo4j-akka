package com.campusconnect.neo4j.types;

import java.io.Serializable;

/**
 * Created by sn1 on 3/22/15.
 */
public class WishListBook extends Book implements Serializable {

    public WishListBook(Integer goodreadsId, String authorName, String goodreadsAuthorId, String name, String isbn, String isbn13, int publishedYear, String description, String publisher, Integer numberOfPages, String imageUrl) {
        super(goodreadsId, authorName, goodreadsAuthorId, name, isbn, isbn13, publishedYear, description, publisher, numberOfPages, imageUrl);
    }

    public WishListBook(Book book, WishListRelationship whishListRelationship) {
        super(book.getNodeId(), book.getId(), book.getGoodreadsId(), book.getAuthorName(), book.getGoodreadsAuthorId(), book.getName(), book.getIsbn(), book.getIsbn13(), book.getPublishedYear(), book.getDescription(), book.getPublisher(), book.getNumberOfPages(), book.getImageUrl());
    }

    public WishListBook(Long nodeId, String id, Integer goodreadsId, String authorName, String goodreadsAuthorId, String name, String isbn, String isbn13, int publishedYear, String description, String publisher, Integer numberOfPages, String imageUrl) {
        super(nodeId, id, goodreadsId, authorName, goodreadsAuthorId, name, isbn, isbn13, publishedYear, description, publisher, numberOfPages, imageUrl);
    }

    public WishListBook() {
    }
}
