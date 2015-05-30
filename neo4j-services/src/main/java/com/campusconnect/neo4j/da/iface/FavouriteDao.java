package com.campusconnect.neo4j.da.iface;

import java.util.List;

import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.Favourite;

public interface FavouriteDao {
	
	 Favourite createFavourite(Favourite favourite);

	    List<Favourite> getFavourites();

		Favourite createFavouriteWithGenreName(String genre);

}
