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
"reviews"
})
public class GetBooksResponse {

@JsonProperty("Request")
private com.campusconnect.neo4j.akka.goodreads.types.Request Request;
@JsonProperty("reviews")
private Reviews reviews;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The Request
*/
@JsonProperty("Request")
public com.campusconnect.neo4j.akka.goodreads.types.Request getRequest() {
return Request;
}

/**
* 
* @param Request
* The Request
*/
@JsonProperty("Request")
public void setRequest(com.campusconnect.neo4j.akka.goodreads.types.Request Request) {
this.Request = Request;
}

/**
* 
* @return
* The reviews
*/
@JsonProperty("reviews")
public Reviews getReviews() {
return reviews;
}

/**
* 
* @param reviews
* The reviews
*/
@JsonProperty("reviews")
public void setReviews(Reviews reviews) {
this.reviews = reviews;
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