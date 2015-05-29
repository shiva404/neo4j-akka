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
        "query",
        "results-start",
        "results-end",
        "total-results",
        "source",
        "results"
})
public class Search {

    @JsonProperty("query")
    private String query;
    @JsonProperty("results-start")
    private String resultsStart;
    @JsonProperty("results-end")
    private String resultsEnd;
    @JsonProperty("total-results")
    private String totalResults;
    @JsonProperty("source")
    private String source;
    @JsonProperty("results")
    private List<Result> results = new ArrayList<Result>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The query
     */
    @JsonProperty("query")
    public String getQuery() {
        return query;
    }

    /**
     * @param query The query
     */
    @JsonProperty("query")
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return The resultsStart
     */
    @JsonProperty("results-start")
    public String getResultsStart() {
        return resultsStart;
    }

    /**
     * @param resultsStart The results-start
     */
    @JsonProperty("results-start")
    public void setResultsStart(String resultsStart) {
        this.resultsStart = resultsStart;
    }

    /**
     * @return The resultsEnd
     */
    @JsonProperty("results-end")
    public String getResultsEnd() {
        return resultsEnd;
    }

    /**
     * @param resultsEnd The results-end
     */
    @JsonProperty("results-end")
    public void setResultsEnd(String resultsEnd) {
        this.resultsEnd = resultsEnd;
    }

    /**
     * @return The totalResults
     */
    @JsonProperty("total-results")
    public String getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The total-results
     */
    @JsonProperty("total-results")
    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * @return The source
     */
    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    /**
     * @param source The source
     */
    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return The results
     */
    @JsonProperty("results")
    public List<Result> getResults() {
        if (results == null)
            results = new ArrayList<>();
        return results;
    }

    /**
     * @param results The results
     */
    @JsonProperty("results")
    public void setResults(List<Result> results) {
        this.results = results;
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
