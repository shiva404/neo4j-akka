package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.neo4j.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sn1 on 4/24/15.
 */
public class BooksPage {
    List<Book> books;
    int offset;
    int size;

    public BooksPage() {

    }

    public BooksPage(int size, int offset, List<Book> books) {
        this.size = size;
        this.offset = offset;
        this.books = books;
    }

    public List<Book> getBooks() {
        if (books == null)
            books = new ArrayList<>();
        return books;
    }

    public int getOffset() {
        return offset;
    }

    public int getSize() {
        return size;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
