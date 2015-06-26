package com.campusconnect.neo4j.akka.util.task;

import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.User;

public class SuccessfulTransactionEmailTask {
	
	  private Book book;
	    private User owner;
	    private User borrower;

	    public SuccessfulTransactionEmailTask(User borrower, Book book, User owner) {

	        this.borrower = borrower;
	        this.book = book;
	        this.owner = owner;
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
