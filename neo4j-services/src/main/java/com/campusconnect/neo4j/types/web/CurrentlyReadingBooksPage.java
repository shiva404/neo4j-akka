package com.campusconnect.neo4j.types.web;

import java.io.Serializable;
import java.util.List;

public class CurrentlyReadingBooksPage implements Serializable {

    private int offset;
    private List<CurrentlyReadingBook> currentlyReadingBooks;
    private int size;

    public CurrentlyReadingBooksPage() {

    }

    public CurrentlyReadingBooksPage(int offset, int size, List<CurrentlyReadingBook> currentlyReadingBooks) {

        this.offset = offset;
        this.currentlyReadingBooks = currentlyReadingBooks;
        this.size = size;
    }

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public List<CurrentlyReadingBook> getCurrentlyReadingBooks() {
		return currentlyReadingBooks;
	}

	public void setCurrentlyReadingBooks(
			List<CurrentlyReadingBook> currentlyReadingBooks) {
		this.currentlyReadingBooks = currentlyReadingBooks;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

   
}
