
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
     * 
     * @return
     *     The Name
     */
    @JsonProperty("@name")
    public String getName() {
        return Name;
    }

    /**
     * 
     * @param Name
     *     The @name
     */
    @JsonProperty("@name")
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * 
     * @return
     *     The Count
     */
    @JsonProperty("@count")
    public String getCount() {
        return Count;
    }

    /**
     * 
     * @param Count
     *     The @count
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
