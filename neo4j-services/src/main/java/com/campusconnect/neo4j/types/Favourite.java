package com.campusconnect.neo4j.types;

import java.io.Serializable;



import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Favourite implements Serializable{
	
		private String genre;

	    @GraphId
	    private Long nodeId;

		public Favourite() {
			super();
		}

		public Favourite(String genre) {
		super();
		this.genre = genre;
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
