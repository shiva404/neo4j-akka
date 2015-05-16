package com.campusconnect.neo4j.akka.goodreads.types;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "title",
        "isbn",
        "isbn13",
        "small_image_url",
        "image_url",
        "average_rating",
        "ratings_count",
        "authors"
})
public class SimilarBook {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("isbn13")
    private String isbn13;
    @JsonProperty("small_image_url")
    private String smallImageUrl;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("average_rating")
    private String averageRating;
    @JsonProperty("ratings_count")
    private String ratingsCount;
    @JsonProperty("authors")
    private List<Author_> authors = new ArrayList<Author_>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The isbn
     */
    @JsonProperty("isbn")
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn The isbn
     */
    @JsonProperty("isbn")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return The isbn13
     */
    @JsonProperty("isbn13")
    public String getIsbn13() {
        return isbn13;
    }

    /**
     * @param isbn13 The isbn13
     */
    @JsonProperty("isbn13")
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    /**
     * @return The smallImageUrl
     */
    @JsonProperty("small_image_url")
    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    /**
     * @param smallImageUrl The small_image_url
     */
    @JsonProperty("small_image_url")
    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    /**
     * @return The imageUrl
     */
    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl The image_url
     */
    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return The averageRating
     */
    @JsonProperty("average_rating")
    public String getAverageRating() {
        return averageRating;
    }

    /**
     * @param averageRating The average_rating
     */
    @JsonProperty("average_rating")
    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * @return The ratingsCount
     */
    @JsonProperty("ratings_count")
    public String getRatingsCount() {
        return ratingsCount;
    }

    /**
     * @param ratingsCount The ratings_count
     */
    @JsonProperty("ratings_count")
    public void setRatingsCount(String ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    /**
     * @return The authors
     */
    @JsonProperty("authors")
    public List<Author_> getAuthors() {
        return authors;
    }

    /**
     * @param authors The authors
     */
    @JsonProperty("authors")
    public void setAuthors(List<Author_> authors) {
        this.authors = authors;
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
