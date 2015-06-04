package com.campusconnect.neo4j.repositories;

import com.campusconnect.neo4j.types.neo4j.Favourite;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface FavouriteRepository extends GraphRepository<Favourite> {

    @Query(value = "match (favourites:Favourite) return favourites")
    public List<Favourite> getFavourites();

}
