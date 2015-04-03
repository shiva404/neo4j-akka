
package com.campusconnect.neo4j.akka.goodreads.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "title",
    "isbn",
    "isbn13",
    "asin",
    "image_url",
    "small_image_url",
    "publication_year",
    "publication_month",
    "publication_day",
    "publisher",
    "language_code",
    "is_ebook",
    "description",
    "work",
    "average_rating",
    "num_pages",
    "format",
    "edition_information",
    "ratings_count",
    "text_reviews_count",
    "url",
    "link",
    "authors",
    "reviews_widget",
    "popular_shelves",
    "book_links",
    "series_works",
    "similar_books"
})
public class Book {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("isbn13")
    private String isbn13;
    @JsonProperty("asin")
    private String asin;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("small_image_url")
    private String smallImageUrl;
    @JsonProperty("publication_year")
    private String publicationYear;
    @JsonProperty("publication_month")
    private String publicationMonth;
    @JsonProperty("publication_day")
    private String publicationDay;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("language_code")
    private String languageCode;
    @JsonProperty("is_ebook")
    private String isEbook;
    @JsonProperty("description")
    private String description;
    @JsonProperty("work")
    private Work work;
    @JsonProperty("average_rating")
    private String averageRating;
    @JsonProperty("num_pages")
    private String numPages;
    @JsonProperty("format")
    private String format;
    @JsonProperty("edition_information")
    private String editionInformation;
    @JsonProperty("ratings_count")
    private String ratingsCount;
    @JsonProperty("text_reviews_count")
    private String textReviewsCount;
    @JsonProperty("url")
    private String url;
    @JsonProperty("link")
    private String link;
    @JsonProperty("authors")
    private List<Author> authors = new ArrayList<Author>();
    @JsonProperty("reviews_widget")
    private String reviewsWidget;
    @JsonProperty("popular_shelves")
    private List<PopularShelf> popularShelves = new ArrayList<PopularShelf>();
    @JsonProperty("book_links")
    private List<Object> bookLinks = new ArrayList<Object>();
    @JsonProperty("series_works")
    private List<Object> seriesWorks = new ArrayList<Object>();
    @JsonProperty("similar_books")
    private List<SimilarBook> similarBooks = new ArrayList<SimilarBook>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The isbn
     */
    @JsonProperty("isbn")
    public String getIsbn() {
        return isbn;
    }

    /**
     * 
     * @param isbn
     *     The isbn
     */
    @JsonProperty("isbn")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * 
     * @return
     *     The isbn13
     */
    @JsonProperty("isbn13")
    public String getIsbn13() {
        return isbn13;
    }

    /**
     * 
     * @param isbn13
     *     The isbn13
     */
    @JsonProperty("isbn13")
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    /**
     * 
     * @return
     *     The asin
     */
    @JsonProperty("asin")
    public String getAsin() {
        return asin;
    }

    /**
     * 
     * @param asin
     *     The asin
     */
    @JsonProperty("asin")
    public void setAsin(String asin) {
        this.asin = asin;
    }

    /**
     * 
     * @return
     *     The imageUrl
     */
    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 
     * @param imageUrl
     *     The image_url
     */
    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 
     * @return
     *     The smallImageUrl
     */
    @JsonProperty("small_image_url")
    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    /**
     * 
     * @param smallImageUrl
     *     The small_image_url
     */
    @JsonProperty("small_image_url")
    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    /**
     * 
     * @return
     *     The publicationYear
     */
    @JsonProperty("publication_year")
    public String getPublicationYear() {
        return publicationYear;
    }

    /**
     * 
     * @param publicationYear
     *     The publication_year
     */
    @JsonProperty("publication_year")
    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * 
     * @return
     *     The publicationMonth
     */
    @JsonProperty("publication_month")
    public String getPublicationMonth() {
        return publicationMonth;
    }

    /**
     * 
     * @param publicationMonth
     *     The publication_month
     */
    @JsonProperty("publication_month")
    public void setPublicationMonth(String publicationMonth) {
        this.publicationMonth = publicationMonth;
    }

    /**
     * 
     * @return
     *     The publicationDay
     */
    @JsonProperty("publication_day")
    public String getPublicationDay() {
        return publicationDay;
    }

    /**
     * 
     * @param publicationDay
     *     The publication_day
     */
    @JsonProperty("publication_day")
    public void setPublicationDay(String publicationDay) {
        this.publicationDay = publicationDay;
    }

    /**
     * 
     * @return
     *     The publisher
     */
    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    /**
     * 
     * @param publisher
     *     The publisher
     */
    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 
     * @return
     *     The languageCode
     */
    @JsonProperty("language_code")
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * 
     * @param languageCode
     *     The language_code
     */
    @JsonProperty("language_code")
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * 
     * @return
     *     The isEbook
     */
    @JsonProperty("is_ebook")
    public String getIsEbook() {
        return isEbook;
    }

    /**
     * 
     * @param isEbook
     *     The is_ebook
     */
    @JsonProperty("is_ebook")
    public void setIsEbook(String isEbook) {
        this.isEbook = isEbook;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The work
     */
    @JsonProperty("work")
    public Work getWork() {
        return work;
    }

    /**
     * 
     * @param work
     *     The work
     */
    @JsonProperty("work")
    public void setWork(Work work) {
        this.work = work;
    }

    /**
     * 
     * @return
     *     The averageRating
     */
    @JsonProperty("average_rating")
    public String getAverageRating() {
        return averageRating;
    }

    /**
     * 
     * @param averageRating
     *     The average_rating
     */
    @JsonProperty("average_rating")
    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * 
     * @return
     *     The numPages
     */
    @JsonProperty("num_pages")
    public String getNumPages() {
        return numPages;
    }

    /**
     * 
     * @param numPages
     *     The num_pages
     */
    @JsonProperty("num_pages")
    public void setNumPages(String numPages) {
        this.numPages = numPages;
    }

    /**
     * 
     * @return
     *     The format
     */
    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    /**
     * 
     * @param format
     *     The format
     */
    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * 
     * @return
     *     The editionInformation
     */
    @JsonProperty("edition_information")
    public String getEditionInformation() {
        return editionInformation;
    }

    /**
     * 
     * @param editionInformation
     *     The edition_information
     */
    @JsonProperty("edition_information")
    public void setEditionInformation(String editionInformation) {
        this.editionInformation = editionInformation;
    }

    /**
     * 
     * @return
     *     The ratingsCount
     */
    @JsonProperty("ratings_count")
    public String getRatingsCount() {
        return ratingsCount;
    }

    /**
     * 
     * @param ratingsCount
     *     The ratings_count
     */
    @JsonProperty("ratings_count")
    public void setRatingsCount(String ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    /**
     * 
     * @return
     *     The textReviewsCount
     */
    @JsonProperty("text_reviews_count")
    public String getTextReviewsCount() {
        return textReviewsCount;
    }

    /**
     * 
     * @param textReviewsCount
     *     The text_reviews_count
     */
    @JsonProperty("text_reviews_count")
    public void setTextReviewsCount(String textReviewsCount) {
        this.textReviewsCount = textReviewsCount;
    }

    /**
     * 
     * @return
     *     The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The link
     */
    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The authors
     */
    @JsonProperty("authors")
    public List<Author> getAuthors() {
        if(authors == null)
            authors = new ArrayList<>();
        return authors;
    }

    /**
     * 
     * @param authors
     *     The authors
     */
    @JsonProperty("authors")
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    /**
     * 
     * @return
     *     The reviewsWidget
     */
    @JsonProperty("reviews_widget")
    public String getReviewsWidget() {
        return reviewsWidget;
    }

    /**
     * 
     * @param reviewsWidget
     *     The reviews_widget
     */
    @JsonProperty("reviews_widget")
    public void setReviewsWidget(String reviewsWidget) {
        this.reviewsWidget = reviewsWidget;
    }

    /**
     * 
     * @return
     *     The popularShelves
     */
    @JsonProperty("popular_shelves")
    public List<PopularShelf> getPopularShelves() {
        return popularShelves;
    }

    /**
     * 
     * @param popularShelves
     *     The popular_shelves
     */
    @JsonProperty("popular_shelves")
    public void setPopularShelves(List<PopularShelf> popularShelves) {
        this.popularShelves = popularShelves;
    }

    /**
     * 
     * @return
     *     The bookLinks
     */
    @JsonProperty("book_links")
    public List<Object> getBookLinks() {
        return bookLinks;
    }

    /**
     * 
     * @param bookLinks
     *     The book_links
     */
    @JsonProperty("book_links")
    public void setBookLinks(List<Object> bookLinks) {
        this.bookLinks = bookLinks;
    }

    /**
     * 
     * @return
     *     The seriesWorks
     */
    @JsonProperty("series_works")
    public List<Object> getSeriesWorks() {
        return seriesWorks;
    }

    /**
     * 
     * @param seriesWorks
     *     The series_works
     */
    @JsonProperty("series_works")
    public void setSeriesWorks(List<Object> seriesWorks) {
        this.seriesWorks = seriesWorks;
    }

    /**
     * 
     * @return
     *     The similarBooks
     */
    @JsonProperty("similar_books")
    public List<SimilarBook> getSimilarBooks() {
        return similarBooks;
    }

    /**
     * 
     * @param similarBooks
     *     The similar_books
     */
    @JsonProperty("similar_books")
    public void setSimilarBooks(List<SimilarBook> similarBooks) {
        this.similarBooks = similarBooks;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
