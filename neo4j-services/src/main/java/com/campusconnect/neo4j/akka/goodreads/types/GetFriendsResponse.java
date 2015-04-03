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
"friends"
})
public class GetFriendsResponse {

@JsonProperty("Request")
private com.campusconnect.neo4j.akka.goodreads.types.Request Request;
@JsonProperty("friends")
private Friends friends;
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
* The friends
*/
@JsonProperty("friends")
public Friends getFriends() {
return friends;
}

/**
* 
* @param friends
* The friends
*/
@JsonProperty("friends")
public void setFriends(Friends friends) {
this.friends = friends;
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