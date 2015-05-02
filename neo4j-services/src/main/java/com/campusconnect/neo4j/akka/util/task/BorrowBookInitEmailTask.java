package com.campusconnect.neo4j.akka.util.task;


import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.User;

/**
 * Created by sn1 on 5/1/15.
 */
public class BorrowBookInitEmailTask {
    public User toUser;
    public Book book;
    public User fromUser;

    public User getFromUser() {
        return fromUser;
    }

    public BorrowBookInitEmailTask(User toUser, Book book, User fromUser) {

        this.toUser = toUser;
        this.book = book;
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public Book getBook() {
        return book;
    }
}
