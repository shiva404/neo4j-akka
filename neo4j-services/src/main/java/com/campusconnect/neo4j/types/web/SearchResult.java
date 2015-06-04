package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.neo4j.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sn1 on 3/9/15.
 */
public class SearchResult {
    private List<Book> books;

    public SearchResult() {
    }

    public SearchResult(List<Book> books) {

        this.books = books;
    }

    public List<Book> getBooks() {
        if (books == null) {
            books = new ArrayList<>();
        }
        return books;
    }

}
