package com.campusconnect.neo4j.tests;

import com.campusconnect.neo4j.tests.base.GlassFishResourceProvider;
import com.github.javafaker.Faker;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by sn1 on 1/18/15.
 */

public class TestBase {
    public static final Faker faker = new Faker();

    public static WebResource resource;

    static {
        GlassFishResourceProvider provider = new GlassFishResourceProvider();
        resource = provider.getResource();
    }

}
