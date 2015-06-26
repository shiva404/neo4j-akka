package com.campusconnect.neo4j.akka.util.task;

import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.User;

public class RejectionToLendBookEmailTask {
    private Book book;
    private User owner;
    private User borrower;
    private String rejectionMessage;

    public RejectionToLendBookEmailTask(User borrower, Book book, User owner,String rejectionMessage) {

        this.borrower = borrower;
        this.book = book;
        this.owner = owner;
        this.rejectionMessage = rejectionMessage;
    }

    public String getRejectionMessage() {
		return rejectionMessage;
	}

	public Book getBook() {
        return book;
    }

    public User getOwner() {
        return owner;
    }

    public User getBorrower() {
        return borrower;
    }

}