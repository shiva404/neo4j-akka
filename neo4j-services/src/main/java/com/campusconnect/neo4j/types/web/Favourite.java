package com.campusconnect.neo4j.types.web;

public class Favourite {

    private String genre;

    private Long nodeId;

    public Favourite() {
        super();
    }

    public Favourite(String genre) {
        super();
        this.genre = genre;
    }

    public Favourite(String genre, Long nodeId) {
        this.genre = genre;
        this.nodeId = nodeId;
    }

    public String getGenre() {
        return genre;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }
}
