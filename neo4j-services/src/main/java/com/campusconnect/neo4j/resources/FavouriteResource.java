package com.campusconnect.neo4j.resources;

import com.campusconnect.neo4j.da.iface.FavouriteDao;
import com.campusconnect.neo4j.mappers.Neo4jToWebMapper;
import com.campusconnect.neo4j.types.neo4j.Favourite;
import com.campusconnect.neo4j.types.web.FavouritePage;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


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
        List<com.campusconnect.neo4j.types.web.Favourite> webFavourites = new ArrayList<>();
        for (Favourite favourite : favourites)
            webFavourites.add(Neo4jToWebMapper.mapFavouriteNeo4jToWeb(favourite));

        return Response.ok().entity(new FavouritePage(webFavourites.size(), 0, webFavourites)).build();
    }


}
