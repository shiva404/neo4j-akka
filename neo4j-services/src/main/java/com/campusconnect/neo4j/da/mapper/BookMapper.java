package com.campusconnect.neo4j.da.mapper;

import com.campusconnect.neo4j.types.neo4j.Book;
import org.neo4j.graphdb.NotFoundException;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/15/15
 * Time: 6:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookMapper {
    private static Logger logger = LoggerFactory.getLogger(BookMapper.class);

    public static Book getBookFromRestNode(RestNode restNode) {
        try {
            Book book = new Book();
            book.setAuthorName((String) restNode.getProperty("authorName", null));
            book.setDescription((String) restNode.getProperty("description", null));
            book.setGoodreadsAuthorId((String) restNode.getProperty("goodreadsAuthorId", null));
            book.setGoodreadsId((Integer) restNode.getProperty("goodreadsId", null));
            book.setId((String) restNode.getProperty("id", null));
            book.setImageUrl((String) restNode.getProperty("imageUrl", null));
            book.setIsbn((String) restNode.getProperty("isbn", null));
            book.setIsbn13((String) restNode.getProperty("isbn13", null));
            book.setName((String) restNode.getProperty("name", null));
            book.setNodeId(restNode.getId());
            book.setNumberOfPages((Integer) restNode.getProperty("numberOfPages", null));
            book.setPublishedYear((Integer) restNode.getProperty("publishedYear", null));
            book.setPublisher((String) restNode.getProperty("publisher", null));
            return book;
        } catch (NotFoundException e) {
            logger.debug("Ignoring field not found exception, message:" + e.getMessage());
        }
        return null;
    }
}
