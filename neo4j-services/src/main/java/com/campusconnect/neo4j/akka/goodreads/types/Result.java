
package com.campusconnect.neo4j.akka.goodreads.types;

import java.util.HashMap;
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
    "books_count",
    "id",
    "original_publication_day",
    "original_publication_month",
    "original_publication_year",
    "ratings_count",
    "text_reviews_count",
    "average_rating",
    "best_book"
})
public class Result {

    @JsonProperty("books_count")
    private Integer booksCount;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("original_publication_day")
    private Integer originalPublicationDay;
    @JsonProperty("original_publication_month")
    private Integer originalPublicationMonth;
    @JsonProperty("original_publication_year")
    private Integer originalPublicationYear;
    @JsonProperty("ratings_count")
    private Integer ratingsCount;
    @JsonProperty("text_reviews_count")
    private Integer textReviewsCount;
    @JsonProperty("average_rating")
    private String averageRating;
    @JsonProperty("best_book")
    private BestBook bestBook;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The booksCount
     */
    @JsonProperty("books_count")
    public Integer getBooksCount() {
        return booksCount;
    }

    /**
     * 
     * @param booksCount
     *     The books_count
     */
    @JsonProperty("books_count")
    public void setBooksCount(Integer booksCount) {
        this.booksCount = booksCount;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The originalPublicationDay
     */
    @JsonProperty("original_publication_day")
    public Integer getOriginalPublicationDay() {
        return originalPublicationDay;
    }

    /**
     *
     * @param originalPublicationDay
     *     The original_publication_day
     */
    @JsonProperty("original_publication_day")
    public void setOriginalPublicationDay(Integer originalPublicationDay) {
        this.originalPublicationDay = originalPublicationDay;
    }

    /**
     *
     * @return
     *     The originalPublicationMonth
     */
    @JsonProperty("original_publication_month")
    public Integer getOriginalPublicationMonth() {
        return originalPublicationMonth;
    }

    /**
     *
     * @param originalPublicationMonth
     *     The original_publication_month
     */
    @JsonProperty("original_publication_month")
    public void setOriginalPublicationMonth(Integer originalPublicationMonth) {
        this.originalPublicationMonth = originalPublicationMonth;
    }

    /**
     *
     * @return
     *     The originalPublicationYear
     */
    @JsonProperty("original_publication_year")
    public Integer getOriginalPublicationYear() {
        return originalPublicationYear;
    }

    /**
     *
     * @param originalPublicationYear
     *     The original_publication_year
     */
    @JsonProperty("original_publication_year")
    public void setOriginalPublicationYear(Integer originalPublicationYear) {
        this.originalPublicationYear = originalPublicationYear;
    }

    /**
     *
     * @return
     *     The ratingsCount
     */
    @JsonProperty("ratings_count")
    public Integer getRatingsCount() {
        return ratingsCount;
    }

    /**
     *
     * @param ratingsCount
     *     The ratings_count
     */
    @JsonProperty("ratings_count")
    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    /**
     *
     * @return
     *     The textReviewsCount
     */
    @JsonProperty("text_reviews_count")
    public Integer getTextReviewsCount() {
        return textReviewsCount;
    }

    /**
     *
     * @param textReviewsCount
     *     The text_reviews_count
     */
    @JsonProperty("text_reviews_count")
    public void setTextReviewsCount(Integer textReviewsCount) {
        this.textReviewsCount = textReviewsCount;
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
     *     The bestBook
     */
    @JsonProperty("best_book")
    public BestBook getBestBook() {
        return bestBook;
    }

    /**
     * 
     * @param bestBook
     *     The best_book
     */
    @JsonProperty("best_book")
    public void setBestBook(BestBook bestBook) {
        this.bestBook = bestBook;
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
