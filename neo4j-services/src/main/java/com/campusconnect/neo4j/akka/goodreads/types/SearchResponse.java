
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
    "Request",
    "search"
})
public class SearchResponse {

    @JsonProperty("Request")
    private Request Request;
    @JsonProperty("search")
    private Search search;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Request
     */
    @JsonProperty("Request")
    public Request getRequest() {
        return Request;
    }

    /**
     * 
     * @param Request
     *     The Request
     */
    @JsonProperty("Request")
    public void setRequest(Request Request) {
        this.Request = Request;
    }

    /**
     * 
     * @return
     *     The search
     */
    @JsonProperty("search")
    public Search getSearch() {
        return search;
    }

    /**
     * 
     * @param search
     *     The search
     */
    @JsonProperty("search")
    public void setSearch(Search search) {
        this.search = search;
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
