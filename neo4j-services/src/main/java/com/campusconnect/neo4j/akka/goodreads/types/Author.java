package com.campusconnect.neo4j.akka.goodreads.types;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "name",
        "role",
        "image_url",
        "small_image_url",
        "link",
        "average_rating",
        "ratings_count",
        "text_reviews_count"
})
public class Author {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("small_image_url")
    private String smallImageUrl;
    @JsonProperty("link")
    private String link;
    @JsonProperty("average_rating")
    private String averageRating;
    @JsonProperty("ratings_count")
    private String ratingsCount;
    @JsonProperty("text_reviews_count")
    private String textReviewsCount;
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
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
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
     * @return The link
     */
    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
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
     * @return The textReviewsCount
     */
    @JsonProperty("text_reviews_count")
    public String getTextReviewsCount() {
        return textReviewsCount;
    }

    /**
     * @param textReviewsCount The text_reviews_count
     */
    @JsonProperty("text_reviews_count")
    public void setTextReviewsCount(String textReviewsCount) {
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
