package com.campusconnect.neo4j.types;

import java.util.List;

/**
 * Created by sn1 on 2/26/15.
 */
public class BorrowedBooksPage {
    private int offset;
    private int size;
    private List<BorrowedBook> borrowedBooks;

    public BorrowedBooksPage() {
    }

    public BorrowedBooksPage(int offset, int size, List<BorrowedBook> borrowedBooks) {
        this.offset = offset;
        this.size = size;
        this.borrowedBooks = borrowedBooks;
    }

    public int getOffset() {

        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<BorrowedBook> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
//    private List
}
