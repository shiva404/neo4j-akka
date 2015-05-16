package com.campusconnect.neo4j.akka.goodreads.types;

import org.codehaus.jackson.annotate.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "authentication",
        "key",
        "method"
})
public class Request {

    @JsonProperty("authentication")
    private String authentication;
    @JsonProperty("key")
    private String key;
    @JsonProperty("method")
    private String method;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The authentication
     */
    @JsonProperty("authentication")
    public String getAuthentication() {
        return authentication;
    }

    /**
     * @param authentication The authentication
     */
    @JsonProperty("authentication")
    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    /**
     * @return The key
     */
    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    /**
     * @param key The key
     */
    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return The method
     */
    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    /**
     * @param method The method
     */
    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
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
