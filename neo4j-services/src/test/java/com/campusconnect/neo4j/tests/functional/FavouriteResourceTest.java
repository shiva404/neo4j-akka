package com.campusconnect.neo4j.tests.functional;

import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.tests.functional.base.DataBrewer;
import com.campusconnect.neo4j.types.neo4j.Favourite;
import com.campusconnect.neo4j.types.web.FavouritePage;
import com.sun.jersey.api.client.ClientResponse;
import org.testng.annotations.Test;

public class FavouriteResourceTest extends TestBase {

    private Favourite favourite;

    public static Favourite createFavourite(String genre) {
        ClientResponse clientResponse = resource.path("favourites").type("application/json").entity(DataBrewer.getFakeFavourites(genre)).post(ClientResponse.class);
        assert clientResponse.getStatus() == 201;
        return clientResponse.getEntity(Favourite.class);
    }

    @Test
    public void TestGroupCreationOfFavourites() {

        String genre = "fiction";
        favourite = createFavourite(genre);
        assert favourite.getGenre().toLowerCase().equals(genre);

        genre = "suspense";

        favourite = createFavourite(genre);
        assert favourite.getGenre().toLowerCase().equals(genre);

        ClientResponse clientResponse = resource.path("favourites").type("application/json").get(ClientResponse.class);
        assert clientResponse.getStatus() == 200;
        FavouritePage favouritePage = clientResponse.getEntity(FavouritePage.class);
        assert favouritePage.getSize() == 2;

    }

}
