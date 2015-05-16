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
        "link",
        "image_url",
        "small_image_url",
        "friends_count",
        "reviews_count",
        "created_at"
})
public class User {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("link")
    private String link;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("small_image_url")
    private String smallImageUrl;
    @JsonProperty("friends_count")
    private String friendsCount;
    @JsonProperty("reviews_count")
    private String reviewsCount;
    @JsonProperty("created_at")
    private String createdAt;
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
     * @return The friendsCount
     */
    @JsonProperty("friends_count")
    public String getFriendsCount() {
        return friendsCount;
    }

    /**
     * @param friendsCount The friends_count
     */
    @JsonProperty("friends_count")
    public void setFriendsCount(String friendsCount) {
        this.friendsCount = friendsCount;
    }

    /**
     * @return The reviewsCount
     */
    @JsonProperty("reviews_count")
    public String getReviewsCount() {
        return reviewsCount;
    }

    /**
     * @param reviewsCount The reviews_count
     */
    @JsonProperty("reviews_count")
    public void setReviewsCount(String reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    /**
     * @return The createdAt
     */
    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
