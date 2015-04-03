
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
    "best_book_id",
    "books_count",
    "desc_user_id",
    "id",
    "media_type",
    "original_publication_year",
    "original_title",
    "rating_dist",
    "ratings_count",
    "ratings_sum",
    "reviews_count",
    "text_reviews_count"
})
public class Work {

    @JsonProperty("best_book_id")
    private Integer bestBookId;
    @JsonProperty("books_count")
    private Integer booksCount;
    @JsonProperty("desc_user_id")
    private Integer descUserId;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("media_type")
    private String mediaType;
    @JsonProperty("original_publication_year")
    private Integer originalPublicationYear;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("rating_dist")
    private String ratingDist;
    @JsonProperty("ratings_count")
    private Integer ratingsCount;
    @JsonProperty("ratings_sum")
    private Integer ratingsSum;
    @JsonProperty("reviews_count")
    private Integer reviewsCount;
    @JsonProperty("text_reviews_count")
    private Integer textReviewsCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The bestBookId
     */
    @JsonProperty("best_book_id")
    public Integer getBestBookId() {
        return bestBookId;
    }

    /**
     * 
     * @param bestBookId
     *     The best_book_id
     */
    @JsonProperty("best_book_id")
    public void setBestBookId(Integer bestBookId) {
        this.bestBookId = bestBookId;
    }

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
     *     The descUserId
     */
    @JsonProperty("desc_user_id")
    public Integer getDescUserId() {
        return descUserId;
    }

    /**
     * 
     * @param descUserId
     *     The desc_user_id
     */
    @JsonProperty("desc_user_id")
    public void setDescUserId(Integer descUserId) {
        this.descUserId = descUserId;
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
     *     The mediaType
     */
    @JsonProperty("media_type")
    public String getMediaType() {
        return mediaType;
    }

    /**
     * 
     * @param mediaType
     *     The media_type
     */
    @JsonProperty("media_type")
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
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
     *     The originalTitle
     */
    @JsonProperty("original_title")
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * 
     * @param originalTitle
     *     The original_title
     */
    @JsonProperty("original_title")
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     * 
     * @return
     *     The ratingDist
     */
    @JsonProperty("rating_dist")
    public String getRatingDist() {
        return ratingDist;
    }

    /**
     * 
     * @param ratingDist
     *     The rating_dist
     */
    @JsonProperty("rating_dist")
    public void setRatingDist(String ratingDist) {
        this.ratingDist = ratingDist;
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
     *     The ratingsSum
     */
    @JsonProperty("ratings_sum")
    public Integer getRatingsSum() {
        return ratingsSum;
    }

    /**
     * 
     * @param ratingsSum
     *     The ratings_sum
     */
    @JsonProperty("ratings_sum")
    public void setRatingsSum(Integer ratingsSum) {
        this.ratingsSum = ratingsSum;
    }

    /**
     * 
     * @return
     *     The reviewsCount
     */
    @JsonProperty("reviews_count")
    public Integer getReviewsCount() {
        return reviewsCount;
    }

    /**
     * 
     * @param reviewsCount
     *     The reviews_count
     */
    @JsonProperty("reviews_count")
    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
