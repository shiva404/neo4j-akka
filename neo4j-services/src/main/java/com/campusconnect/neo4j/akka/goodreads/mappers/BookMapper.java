package com.campusconnect.neo4j.akka.goodreads.mappers;

import com.campusconnect.neo4j.akka.goodreads.types.Author;
import com.campusconnect.neo4j.akka.goodreads.types.Book;

/**
 * Created by sn1 on 3/12/15.
 */
public class BookMapper {
    public static com.campusconnect.neo4j.types.Book getBookFromGoodreadsBook(Book goodreadsBook) {
        if(goodreadsBook != null){
            com.campusconnect.neo4j.types.Book book = new com.campusconnect.neo4j.types.Book();
            final Author author = goodreadsBook.getAuthors().get(0);
            if(author != null){
                book.setAuthorName(author.getName());
                book.setGoodreadsAuthorId(author.getId());
            }
            book.setDescription(goodreadsBook.getDescription());
            book.setGoodreadsId(Integer.valueOf(goodreadsBook.getId()));
            book.setImageUrl(goodreadsBook.getImageUrl());
            book.setIsbn(goodreadsBook.getIsbn());
            book.setIsbn13(goodreadsBook.getIsbn13());
            book.setName(goodreadsBook.getTitle());
            book.setPublishedYear(Integer.valueOf(goodreadsBook.getPublicationYear() != null ? goodreadsBook.getPublicationYear() : "0"));
            book.setPublisher(goodreadsBook.getPublisher());
            book.setNumberOfPages(Integer.parseInt(goodreadsBook.getNumPages() != null ? goodreadsBook.getNumPages() : "0"));
            return book;
        }
        return null;
    }
}
