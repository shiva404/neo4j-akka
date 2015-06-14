package com.campusconnect.neo4j.types.web;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/5/15
 * Time: 11:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class AllBooks {
    private List<Book> readBooks;
    private List<Book> borrowedBooks;
    private List<Book> ownedBooks;
    private List<Book> wishlistBooks;

    public List<Book> getReadBooks() {
        if (readBooks == null) {
            readBooks = new ArrayList<>();
        }
        return readBooks;
    }

    public List<Book> getBorrowedBooks() {
        if (borrowedBooks == null) {
            borrowedBooks = new ArrayList<>();
        }
        return borrowedBooks;
    }

    public List<Book> getOwnedBooks() {
        if (ownedBooks == null) {
            ownedBooks = new ArrayList<>();
        }
        return ownedBooks;
    }

    public List<Book> getWishlistBooks() {
        if (wishlistBooks == null) {
            wishlistBooks = new ArrayList<>();
        }
        return wishlistBooks;
    }

    public AllBooks() {
    }

}
