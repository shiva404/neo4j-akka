package com.campusconnect.neo4j.da;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.campusconnect.neo4j.da.iface.FavouriteDao;
import com.campusconnect.neo4j.repositories.BookRepository;
import com.campusconnect.neo4j.repositories.FavouriteRepository;
import com.campusconnect.neo4j.types.Favourite;

public class FavouriteDaoImpl implements FavouriteDao{
	
	
	@Autowired
	FavouriteRepository favouriteRepository;

	private Neo4jTemplate neo4jTemplate;

	public FavouriteDaoImpl(Neo4jTemplate neo4jTemplate) {
		this.neo4jTemplate = neo4jTemplate;
	}

	@Override
	public Favourite createFavouriteWithGenreName(String genre) {
		return neo4jTemplate.save(new Favourite(genre));
	}

	@Override
	public List<Favourite> getFavourites() {
		return favouriteRepository.getFavourites();
	}

	@Override
	public Favourite createFavourite(Favourite favourite) {
		
		List<Favourite> favourites = getFavourites();
		
		for(Favourite fav:favourites)
		{
			if(favourite.getGenre().toLowerCase().equals(fav.getGenre().toLowerCase()))
				return fav;
		}
		
		return neo4jTemplate.save(favourite);
	}
	

}
