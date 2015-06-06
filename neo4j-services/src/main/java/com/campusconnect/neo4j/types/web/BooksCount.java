package com.campusconnect.neo4j.types.web;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/5/15
 * Time: 11:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class BooksCount {
    private int availableBooks;
    private int borrowedBooks;
    private int lentBooks;
    private int currentlyReading;

    public int getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(int availableBooks) {
        this.availableBooks = availableBooks;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(int borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public int getLentBooks() {
        return lentBooks;
    }

    public void setLentBooks(int lentBooks) {
        this.lentBooks = lentBooks;
    }

    public int getCurrentlyReading() {
        return currentlyReading;
    }

    public void setCurrentlyReading(int currentlyReading) {
        this.currentlyReading = currentlyReading;
    }

    public BooksCount() {

    }

    public BooksCount(int availableBooks, int borrowedBooks, int lentBooks, int currentlyReading) {

        this.availableBooks = availableBooks;
        this.borrowedBooks = borrowedBooks;
        this.lentBooks = lentBooks;
        this.currentlyReading = currentlyReading;
    }
}
