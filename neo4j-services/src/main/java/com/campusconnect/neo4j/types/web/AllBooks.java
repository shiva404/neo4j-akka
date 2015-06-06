package com.campusconnect.neo4j.types.web;

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
    private List<Book> availableBooks;
    private List<Book> lentBooks;

    public AllBooks() {
    }

}
