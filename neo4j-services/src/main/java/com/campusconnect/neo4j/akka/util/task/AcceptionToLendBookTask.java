package com.campusconnect.neo4j.akka.util.task;

import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.User;

public class AcceptionToLendBookTask {
	 private Book book;
	    private User owner;
	    private User borrower;

	    public AcceptionToLendBookTask(User borrower, Book book, User owner) {

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
