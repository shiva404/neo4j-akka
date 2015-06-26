package com.campusconnect.neo4j.tests.functional;

import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.tests.base.DataBrewer;
import com.campusconnect.neo4j.types.common.AddressType;
import com.campusconnect.neo4j.types.neo4j.Book;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.web.Field;
import com.campusconnect.neo4j.types.web.Fields;
import com.sun.jersey.api.client.ClientResponse;
import org.testng.annotations.Test;

/**
 * Created by sn1 on 2/24/15.
 */
public class UserResourceTest extends TestBase {

    private static User createdUser;
    private User createdUser2;

    private Book createdBook;
    private Book createdBook2;

    private Book availableBook;
    private Book lentBook;

    private User updatedUser;

    public static String createUser() {
        ClientResponse clientResponse = resource.path("users").type("application/json").entity(DataBrewer.getFakeUserWithAddress()).post(ClientResponse.class);
        assert clientResponse.getStatus() == 201;
        createdUser = clientResponse.getEntity(User.class);
        String userId = createdUser.getId();
        return userId;
    }

    public static String createBook() {
        ClientResponse createBookClientResponse = resource.path("books").type("application/json").entity(DataBrewer.getFakeBook()).post(ClientResponse.class);
        return createBookClientResponse.getEntity(Book.class).getId();
    }

    public static String createKnowUserWithGoogleId(String userName, String email, String googleId) {
        ClientResponse clientResponse = resource.path("users").type("application/json").entity(DataBrewer.getFakeUserWithKnownEmailAddressAndGoogleId(userName, email, googleId)).post(ClientResponse.class);
        assert clientResponse.getStatus() == 201;
        createdUser = clientResponse.getEntity(User.class);
        System.out.println(createdUser.getEmail() + createdUser.getName());
        String userId = createdUser.getId();
        return userId;
    }

    public static String createKnowUserWithFbId(String userName, String email, String fbId) {
        ClientResponse clientResponse = resource.path("users").type("application/json").entity(DataBrewer.getFakeUserWithKnownEmailAddressAndFbId(userName, email, fbId)).post(ClientResponse.class);
        assert clientResponse.getStatus() == 201;
        createdUser = clientResponse.getEntity(User.class);
        System.out.println(createdUser.getEmail() + createdUser.getName());
        String userId = createdUser.getId();
        return userId;
    }

    @Test
    public void testCompleteUserFlow() {
        ClientResponse clientResponse = resource.path("users").type("application/json").entity(DataBrewer.getFakeUserWithAddress()).post(ClientResponse.class);
        assert clientResponse.getStatus() == 201;
        createdUser = clientResponse.getEntity(User.class);

        ClientResponse clientResponse2 = resource.path("users").type("application/json").entity(DataBrewer.getFakeUserWithAddress()).post(ClientResponse.class);
        assert clientResponse.getStatus() == 201;
        createdUser2 = clientResponse2.getEntity(User.class);

        createdUser.setName("Updated name");
        createdUser.getAddresses().clear();
        createdUser.getAddresses().add(DataBrewer.getFakeAddress(AddressType.OTHER.toString()));
        //update
//        ClientResponse updateClientResponse = resource.path("users").path(createdUser.getId()).type("application/json").entity(createdUser).put(ClientResponse.class);
//        assert updateClientResponse.getStatus() == 200;
        //get
        ClientResponse getClientResponse = resource.path("users").path(createdUser.getId()).accept("application/json").get(ClientResponse.class);
        assert getClientResponse.getStatus() == 200;
        User updatedUser = getClientResponse.getEntity(User.class);
//        assert updatedUser.getName().equals("Updated name");

        Fields fields = new Fields();
        Field field = new Field("goodreadsId", "abc");
        Field field1 = new Field("goodreadsAccessToken", "xyz");
        fields.getFields().add(field);
        fields.getFields().add(field1);
        ClientResponse updateFieldsClientResponse = resource.path("users").path(createdUser.getId()).path("fields").type("application/json").entity(fields).put(ClientResponse.class);
        assert updateFieldsClientResponse.getStatus() == 200;
    }

    @Test(dependsOnMethods = "testCompleteUserFlow")
    public void bookE2EFlows() {
        ClientResponse createBookClientResponse = resource.path("books").type("application/json").entity(DataBrewer.getFakeBook()).post(ClientResponse.class);
        assert createBookClientResponse.getStatus() == 201;
        ClientResponse createBookClientResponse2 = resource.path("books").type("application/json").entity(DataBrewer.getFakeBook()).post(ClientResponse.class);
        assert createBookClientResponse2.getStatus() == 201;

        createdBook = createBookClientResponse.getEntity(Book.class);
        createdBook2 = createBookClientResponse2.getEntity(Book.class);

        ClientResponse getBookResponse = resource.path("books").path(createdBook.getId()).accept("application/json").get(ClientResponse.class);
        assert getBookResponse.getStatus() == 200;
        Book getBook = getBookResponse.getEntity(Book.class);

        assert getBook != null;
    }

}