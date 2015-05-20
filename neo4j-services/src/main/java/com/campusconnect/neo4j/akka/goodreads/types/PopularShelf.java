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
        "@count"
})
public class PopularShelf {

    @JsonProperty("@name")
    private String Name;
    @JsonProperty("@count")
    private String Count;
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
     * @return The Count
     */
    @JsonProperty("@count")
    public String getCount() {
        return Count;
    }

    /**
     * @param Count The @count
     */
    @JsonProperty("@count")
    public void setCount(String Count) {
        this.Count = Count;
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
