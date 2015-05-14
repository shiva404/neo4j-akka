package com.campusconnect.neo4j.akka.util.task;


import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.User;

/**
 * Created by sn1 on 5/1/15.
 */
public class BorrowBookInitEmailTask {
    private Book book;
    private User borrower;
    private User owner;

    public BorrowBookInitEmailTask(User owner, Book book, User borrower) {

        this.owner = owner;
        this.book = book;
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public User getBorrower() {
        return borrower;
    }

    public User getOwner() {
        return owner;
    }
}
