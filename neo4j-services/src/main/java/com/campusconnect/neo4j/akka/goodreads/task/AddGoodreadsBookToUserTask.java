package com.campusconnect.neo4j.akka.goodreads.task;

import com.campusconnect.neo4j.types.neo4j.Book;

/**
 * Created by sn1 on 3/13/15.
 */
public class AddGoodreadsBookToUserTask {
    private Book book;
    private String userId;
    private String shelfName;

    public AddGoodreadsBookToUserTask(Book book, String userId, String shelfName) {

        this.book = book;
        this.userId = userId;
        this.shelfName = shelfName;

    }

    public Book getBook() {
        return book;
    }

    public String getUserId() {
        return userId;
    }

    public String getShelfName() {
        return shelfName;
    }
}
