package com.campusconnect.neo4j.tests;

import com.campusconnect.neo4j.tests.functional.UserResourceTest;
import com.sun.jersey.api.client.ClientResponse;
import org.testng.annotations.Test;

/**
 * Created by sn1 on 5/7/15.
 */
public class DataSetUp extends TestBase {
    @Test
    public void dataSetUp() {
        //create User1

        String userId1 = UserResourceTest.createKnowUserWithGoogleId("Shiva Kumar", "shiva.n404@gmail.com", "105609898189858031660");

        //createUser2
        String userId2 = UserResourceTest.createUser();

        //createUser3
        String userId3 = UserResourceTest.createUser();

        //createUser4
        String userId4 = UserResourceTest.createUser();

        //create user5 
        String userId5 = UserResourceTest.createUser();

        //create user6
        String userId6 = UserResourceTest.createUser();

        //create user7 
        String userId7 = UserResourceTest.createUser();


        //user1 friend with user2
        ClientResponse clientResponse = resource
                .path("users").path(userId1).path("friend").path(userId2).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        //user1 friend with user3
        clientResponse = resource
                .path("users").path(userId1).path("friend").path(userId3).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        //user2 friend with user3
        clientResponse = resource
                .path("users").path(userId2).path("friend").path(userId3).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        //user3 friend with user4
        clientResponse = resource
                .path("users").path(userId3).path("friend").path(userId4).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        //user5 friend with user6
        clientResponse = resource
                .path("users").path(userId5).path("friend").path(userId6).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        //user3 friend with user7
        clientResponse = resource
                .path("users").path(userId3).path("friend").path(userId7).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        //user7 follow with user2
        clientResponse = resource
                .path("users").path(userId7).path("follow").path(userId2).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        //user3 follow with user5
        clientResponse = resource
                .path("users").path(userId3).path("follow").path(userId5).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

        //user5 follow with user6
        clientResponse = resource
                .path("users").path(userId5).path("follow").path(userId6).type("application/json")
                .post(ClientResponse.class);
        assert clientResponse.getStatus() == 200;

    }
}
