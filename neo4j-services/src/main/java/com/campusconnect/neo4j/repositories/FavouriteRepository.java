package com.campusconnect.neo4j.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.Favourite;

public interface FavouriteRepository extends GraphRepository<Favourite>{

	  @Query(value = "match (favourites:Favourite) return favourites")
	public List<Favourite> getFavourites();

}
