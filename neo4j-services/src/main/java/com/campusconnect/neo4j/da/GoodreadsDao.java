package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.akka.goodreads.GoodreadsAsynchHandler;
import com.campusconnect.neo4j.akka.goodreads.api.GetBook;
import com.campusconnect.neo4j.akka.goodreads.api.Search;
import com.campusconnect.neo4j.akka.goodreads.mappers.BookMapper;
import com.campusconnect.neo4j.akka.goodreads.types.*;
import com.campusconnect.neo4j.types.neo4j.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sn1 on 3/4/15.
 */
public class GoodreadsDao {
    private static Logger logger = LoggerFactory.getLogger(GoodreadsDao.class);
    private Search search;
    private GetBook getBook;
    private GoodreadsAsynchHandler goodreadsAsynchHandler;

    public GoodreadsDao() {
    }

    public void setGoodreadsAsynchHandler(GoodreadsAsynchHandler goodreadsAsynchHandler) {
        this.goodreadsAsynchHandler = goodreadsAsynchHandler;
    }

    public void getAndSaveBooksFromGoodreads(String userId, String goodreadsUserId, String accessToken, String accessTokenSecret) {
        goodreadsAsynchHandler.getAndSaveBooks(userId, goodreadsUserId, accessToken, accessTokenSecret);
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public void setGetBook(GetBook getBook) {
        this.getBook = getBook;
    }


    public List<Book> search(String queryString) {
        try {
            SearchResponse searchResponse = search.search(queryString);
            return formSearchResult(searchResponse);
        } catch (Exception e) {
            logger.error("Error while connecting to goodreads", e);
        }
        return new ArrayList<>();//return empty array list
    }

    private List<Book> formSearchResult(SearchResponse searchResponse) {
        List<Book> searchBooks = new ArrayList<>();
        if (searchResponse.getSearch() != null)
            for (Result result : searchResponse.getSearch().getResults()) {
                final BestBook bestBook = result.getBestBook();
                if (bestBook != null) {
                    Book book = new Book();
                    final Author author = bestBook.getAuthor();
                    if (author != null) {
                        book.setAuthorName(author.getName());
                        book.setGoodreadsAuthorId(author.getId());
                    }

                    book.setGoodreadsId(bestBook.getId());
                    book.setImageUrl(bestBook.getImageUrl());
                    book.setName(bestBook.getTitle());
//                    book.setPublishedYear(result.getOriginalPublicationYear());
                    searchBooks.add(book);
                }
            }
        return searchBooks;
    }

    public Book getBookById(String goodreadsId) throws IOException {
        GetBookResponse getBookResponse = getBook.getBookById(goodreadsId);
        return BookMapper.getBookFromGoodreadsBook(getBookResponse.getBook());
    }

    public Book getBookByISBN(String isbn) throws IOException {
        GetBookResponse getBookResponse = getBook.getBookByISBN(isbn);
        return BookMapper.getBookFromGoodreadsBook(getBookResponse.getBook());
    }

    public void replaceGoodreadsRecWithUserId(String id, Integer goodreadsId, String profileImageUrl) {
        goodreadsAsynchHandler.replaceGoodreadsIdWithUserId(id, goodreadsId, profileImageUrl);
    }
}
