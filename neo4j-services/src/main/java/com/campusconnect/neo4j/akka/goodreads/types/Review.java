package com.campusconnect.neo4j.akka.goodreads.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
"id",
"book",
"rating",
"votes",
"spoiler_flag",
"spoilers_state",
"shelves",
"date_added",
"date_updated",
"body",
"comments_count",
"url",
"link",
"owned",
"started_at",
"read_at"
})
public class Review {

@JsonProperty("id")
private String id;
@JsonProperty("book")
private Book book;
@JsonProperty("rating")
private String rating;
@JsonProperty("votes")
private String votes;
@JsonProperty("spoiler_flag")
private String spoilerFlag;
@JsonProperty("spoilers_state")
private String spoilersState;
@JsonProperty("shelves")
private List<Shelf> shelves = new ArrayList<Shelf>();
@JsonProperty("date_added")
private String dateAdded;
@JsonProperty("date_updated")
private String dateUpdated;
@JsonProperty("comments_count")
private String commentsCount;
@JsonProperty("url")
private String url;
@JsonProperty("link")
private String link;
@JsonProperty("owned")
private String owned;
@JsonProperty("started_at")
private String startedAt;
@JsonProperty("read_at")
private String readAt;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The id
*/
@JsonProperty("id")
public String getId() {
return id;
}

/**
* 
* @param id
* The id
*/
@JsonProperty("id")
public void setId(String id) {
this.id = id;
}

/**
* 
* @return
* The book
*/
@JsonProperty("book")
public Book getBook() {
return book;
}

/**
* 
* @param book
* The book
*/
@JsonProperty("book")
public void setBook(Book book) {
this.book = book;
}

/**
* 
* @return
* The rating
*/
@JsonProperty("rating")
public String getRating() {
return rating;
}

/**
* 
* @param rating
* The rating
*/
@JsonProperty("rating")
public void setRating(String rating) {
this.rating = rating;
}

/**
* 
* @return
* The votes
*/
@JsonProperty("votes")
public String getVotes() {
return votes;
}

/**
* 
* @param votes
* The votes
*/
@JsonProperty("votes")
public void setVotes(String votes) {
this.votes = votes;
}

/**
* 
* @return
* The spoilerFlag
*/
@JsonProperty("spoiler_flag")
public String getSpoilerFlag() {
return spoilerFlag;
}

/**
* 
* @param spoilerFlag
* The spoiler_flag
*/
@JsonProperty("spoiler_flag")
public void setSpoilerFlag(String spoilerFlag) {
this.spoilerFlag = spoilerFlag;
}

/**
* 
* @return
* The spoilersState
*/
@JsonProperty("spoilers_state")
public String getSpoilersState() {
return spoilersState;
}

/**
* 
* @param spoilersState
* The spoilers_state
*/
@JsonProperty("spoilers_state")
public void setSpoilersState(String spoilersState) {
this.spoilersState = spoilersState;
}

/**
* 
* @return
* The shelves
*/
@JsonProperty("shelves")
public List<Shelf> getShelves() {
return shelves;
}

/**
* 
* @param shelves
* The shelves
*/
@JsonProperty("shelves")
public void setShelves(List<Shelf> shelves) {
this.shelves = shelves;
}

/**
* 
* @return
* The dateAdded
*/
@JsonProperty("date_added")
public String getDateAdded() {
return dateAdded;
}

/**
* 
* @param dateAdded
* The date_added
*/
@JsonProperty("date_added")
public void setDateAdded(String dateAdded) {
this.dateAdded = dateAdded;
}

/**
* 
* @return
* The dateUpdated
*/
@JsonProperty("date_updated")
public String getDateUpdated() {
return dateUpdated;
}

/**
* 
* @param dateUpdated
* The date_updated
*/
@JsonProperty("date_updated")
public void setDateUpdated(String dateUpdated) {
this.dateUpdated = dateUpdated;
}

/**
* 
* @return
* The commentsCount
*/
@JsonProperty("comments_count")
public String getCommentsCount() {
return commentsCount;
}

/**
* 
* @param commentsCount
* The comments_count
*/
@JsonProperty("comments_count")
public void setCommentsCount(String commentsCount) {
this.commentsCount = commentsCount;
}

/**
* 
* @return
* The url
*/
@JsonProperty("url")
public String getUrl() {
return url;
}

/**
* 
* @param url
* The url
*/
@JsonProperty("url")
public void setUrl(String url) {
this.url = url;
}

/**
* 
* @return
* The link
*/
@JsonProperty("link")
public String getLink() {
return link;
}

/**
* 
* @param link
* The link
*/
@JsonProperty("link")
public void setLink(String link) {
this.link = link;
}

/**
* 
* @return
* The owned
*/
@JsonProperty("owned")
public String getOwned() {
return owned;
}

/**
* 
* @param owned
* The owned
*/
@JsonProperty("owned")
public void setOwned(String owned) {
this.owned = owned;
}

/**
* 
* @return
* The startedAt
*/
@JsonProperty("started_at")
public String getStartedAt() {
return startedAt;
}

/**
* 
* @param startedAt
* The started_at
*/
@JsonProperty("started_at")
public void setStartedAt(String startedAt) {
this.startedAt = startedAt;
}

/**
* 
* @return
* The readAt
*/
@JsonProperty("read_at")
public String getReadAt() {
return readAt;
}

/**
* 
* @param readAt
* The read_at
*/
@JsonProperty("read_at")
public void setReadAt(String readAt) {
this.readAt = readAt;
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