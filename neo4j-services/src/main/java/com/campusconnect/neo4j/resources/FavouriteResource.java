package com.campusconnect.neo4j.resources;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.campusconnect.neo4j.da.iface.BookDao;
import com.campusconnect.neo4j.da.iface.FavouriteDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.types.Book;
import com.campusconnect.neo4j.types.Favourite;
import com.campusconnect.neo4j.types.FavouritePage;


@Path("/favourites")
@Consumes("application/json")
@Produces("application/json")
public class FavouriteResource {
		
	   private FavouriteDao favouriteDao;


	    public FavouriteResource(FavouriteDao favouriteDao) {
	        this.favouriteDao = favouriteDao;
	    }
	    
	    
	    @POST
	    public Response createFavourite(Favourite favourite) {
	      
	        Favourite createdFavourite = favouriteDao.createFavourite(favourite);
	        return Response.created(null).entity(createdFavourite).build();
	    }

	    @GET
	    public Response getFavourites() {
	        List<Favourite> favourites = favouriteDao.getFavourites();
	        return Response.ok().entity(new FavouritePage(favourites.size(), 0, favourites)).build();
	    }
	
	
}
