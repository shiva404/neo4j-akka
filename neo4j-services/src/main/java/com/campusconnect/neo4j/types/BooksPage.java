package com.campusconnect.neo4j.types;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sn1 on 4/24/15.
 */
public class BooksPage {
    int size;
    int offset;
    List<Book> books;

    public BooksPage() {

    }

    public BooksPage(int size, int offset, List<Book> books) {
        this.size = size;
        this.offset = offset;
        this.books = books;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<Book> getBooks() {
        if (books == null)
            books = new ArrayList<>();
        return books;
    }
}
