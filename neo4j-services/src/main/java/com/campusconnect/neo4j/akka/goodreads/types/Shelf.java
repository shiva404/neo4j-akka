package com.campusconnect.neo4j.akka.goodreads.types;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "@name",
        "@exclusive",
        "@review_shelf_id",
        "@sortable"
})
public class Shelf {

    @JsonProperty("@name")
    private String Name;
    @JsonProperty("@exclusive")
    private String Exclusive;
    @JsonProperty("@review_shelf_id")
    private String ReviewShelfId;
    @JsonProperty("@sortable")
    private String Sortable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The Name
     */
    @JsonProperty("@name")
    public String getName() {
        return Name;
    }

    /**
     * @param Name The @name
     */
    @JsonProperty("@name")
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return The Exclusive
     */
    @JsonProperty("@exclusive")
    public String getExclusive() {
        return Exclusive;
    }

    /**
     * @param Exclusive The @exclusive
     */
    @JsonProperty("@exclusive")
    public void setExclusive(String Exclusive) {
        this.Exclusive = Exclusive;
    }

    /**
     * @return The ReviewShelfId
     */
    @JsonProperty("@review_shelf_id")
    public String getReviewShelfId() {
        return ReviewShelfId;
    }

    /**
     * @param ReviewShelfId The @review_shelf_id
     */
    @JsonProperty("@review_shelf_id")
    public void setReviewShelfId(String ReviewShelfId) {
        this.ReviewShelfId = ReviewShelfId;
    }

    /**
     * @return The Sortable
     */
    @JsonProperty("@sortable")
    public String getSortable() {
        return Sortable;
    }

    /**
     * @param Sortable The @sortable
     */
    @JsonProperty("@sortable")
    public void setSortable(String Sortable) {
        this.Sortable = Sortable;
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