package com.campusconnect.neo4j.types;

import java.util.List;

/**
 * Created by sn1 on 2/25/15.
 */
public class OwnedBooksPage {
    private int offset;
    private List<OwnedBook> ownedBooks;

    private int size;

    public OwnedBooksPage() {
    }

    public OwnedBooksPage(int offset, int size, List<OwnedBook> ownedBooks) {
        this.offset = offset;
        this.size = size;
        this.ownedBooks = ownedBooks;
    }

    public int getOffset() {
        return offset;
    }

    public List<OwnedBook> getOwnedBooks() {
        return ownedBooks;
    }

    public int getSize() {
        return size;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setOwnedBooks(List<OwnedBook> ownedBooks) {
        this.ownedBooks = ownedBooks;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
