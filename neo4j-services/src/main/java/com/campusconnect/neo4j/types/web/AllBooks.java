package com.campusconnect.neo4j.types.web;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/5/15
 * Time: 11:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class AllBooks {
    private BooksPage readBooks;
    private BooksPage borrowedBooks;
    private BooksPage availableBooks;
    private BooksPage lentBooks;

    public AllBooks() {
    }

    public BooksPage getReadBooks() {

        return readBooks;
    }

    public void setReadBooks(BooksPage readBooks) {
        this.readBooks = readBooks;
    }

    public BooksPage getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(BooksPage borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public BooksPage getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(BooksPage availableBooks) {
        this.availableBooks = availableBooks;
    }

    public BooksPage getLentBooks() {
        return lentBooks;
    }

    public void setLentBooks(BooksPage lentBooks) {
        this.lentBooks = lentBooks;
    }

    public AllBooks(BooksPage readBooks, BooksPage boorrowedBooks, BooksPage availableBooks, BooksPage lentBooks) {
        this.readBooks = readBooks;
        this.borrowedBooks = boorrowedBooks;
        this.availableBooks = availableBooks;
        this.lentBooks = lentBooks;
    }
}
