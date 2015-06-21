package com.campusconnect.neo4j.types.web;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sn1 on 4/21/15.
 */
public class WishListBooksPage implements Serializable {

    private int offset;
    private List<WishListBook> wishListBooks;
    private int size;

    public WishListBooksPage() {

    }

    public WishListBooksPage(int offset, int size, List<WishListBook> wishListBooks) {

        this.offset = offset;
        this.wishListBooks = wishListBooks;
        this.size = size;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<WishListBook> getWishListBooks() {
        return wishListBooks;
    }

    public void setWishListBooks(List<WishListBook> wishListBooks) {
        this.wishListBooks = wishListBooks;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
