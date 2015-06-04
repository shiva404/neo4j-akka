package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.neo4j.Favourite;

import java.util.List;

public interface FavouriteDao {

    Favourite createFavourite(Favourite favourite);

    List<Favourite> getFavourites();

    Favourite createFavouriteWithGenreName(String genre);

}
