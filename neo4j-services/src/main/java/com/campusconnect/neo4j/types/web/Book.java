package com.campusconnect.neo4j.types.web;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/4/15
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Book {
    private String id;
    private Integer goodreadsId;
    private String authorName;
    private String goodreadsAuthorId;
    private String name;
    private String isbn;
    private String isbn13;
    private int publishedYear;
    private String description;
    private String publisher;
    private Integer numberOfPages;

    public AvailableBookDetails getAvailableBookDetails() {
        return availableBookDetails;
    }

    public void setAvailableBookDetails(AvailableBookDetails availableBookDetails) {
        this.availableBookDetails = availableBookDetails;
    }

    public BorrowedBookDetails getBorrowedBookDetails() {
        return borrowedBookDetails;
    }

    public void setBorrowedBookDetails(BorrowedBookDetails borrowedBookDetails) {
        this.borrowedBookDetails = borrowedBookDetails;
    }

    public LentBookDetails getLentBookDetails() {
        return lentBookDetails;
    }

    public void setLentBookDetails(LentBookDetails lentBookDetails) {
        this.lentBookDetails = lentBookDetails;
    }

    public WishlistBookDetails getWishlistBookDetails() {
        return wishlistBookDetails;
    }

    public void setWishlistBookDetails(WishlistBookDetails wishlistBookDetails) {
        this.wishlistBookDetails = wishlistBookDetails;
    }

    private AvailableBookDetails availableBookDetails;
    private BorrowedBookDetails borrowedBookDetails;
    private LentBookDetails lentBookDetails;
    private WishlistBookDetails wishlistBookDetails;

    private String bookType;  //Owned, borrowed, wishlist
    private String imageUrl;


    public Book() {
    }

    public Book(String id, Integer goodreadsId, String authorName, String goodreadsAuthorId, String name, String isbn, String isbn13,
                int publishedYear, String description, String publisher, Integer numberOfPages, String imageUrl) {
        this.id = id;
        this.goodreadsId = goodreadsId;
        this.authorName = authorName;
        this.goodreadsAuthorId = goodreadsAuthorId;
        this.name = name;
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.publishedYear = publishedYear;
        this.description = description;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
        this.imageUrl = imageUrl;
    }

    public Book(String name, String isbn) {
        this.name = name;
        this.isbn = isbn;
    }

    public Book(String id, String name, String isbn) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
    }


    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getGoodreadsId() {
        return goodreadsId;
    }

    public void setGoodreadsId(Integer goodreadsId) {
        this.goodreadsId = goodreadsId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGoodreadsAuthorId() {
        return goodreadsAuthorId;
    }

    public void setGoodreadsAuthorId(String goodreadsAuthorId) {
        this.goodreadsAuthorId = goodreadsAuthorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    @Override
    public String toString() {
        return "Book{" +
                ", id='" + id + '\'' +
                ", goodreadsId=" + goodreadsId +
                ", authorName='" + authorName + '\'' +
                ", goodreadsAuthorId='" + goodreadsAuthorId + '\'' +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", publishedYear=" + publishedYear +
                ", description='" + description + '\'' +
                ", publisher='" + publisher + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
