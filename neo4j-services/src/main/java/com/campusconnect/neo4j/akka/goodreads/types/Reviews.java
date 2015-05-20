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
        "@start",
        "@end",
        "@total",
        "review"
})
public class Reviews {

    @JsonProperty("@start")
    private String Start;
    @JsonProperty("@end")
    private String End;
    @JsonProperty("@total")
    private String Total;
    @JsonProperty("review")
    private List<Review> review = new ArrayList<Review>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The Start
     */
    @JsonProperty("@start")
    public String getStart() {
        return Start;
    }

    /**
     * @param Start The @start
     */
    @JsonProperty("@start")
    public void setStart(String Start) {
        this.Start = Start;
    }

    /**
     * @return The End
     */
    @JsonProperty("@end")
    public String getEnd() {
        return End;
    }

    /**
     * @param End The @end
     */
    @JsonProperty("@end")
    public void setEnd(String End) {
        this.End = End;
    }

    /**
     * @return The Total
     */
    @JsonProperty("@total")
    public String getTotal() {
        return Total;
    }

    /**
     * @param Total The @total
     */
    @JsonProperty("@total")
    public void setTotal(String Total) {
        this.Total = Total;
    }

    /**
     * @return The review
     */
    @JsonProperty("review")
    public List<Review> getReview() {
        return review;
    }

    /**
     * @param review The review
     */
    @JsonProperty("review")
    public void setReview(List<Review> review) {
        this.review = review;
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