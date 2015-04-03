package com.campusconnect.neo4j.tests;

import com.campusconnect.neo4j.tests.functional.base.ResourceProvider;
import com.github.javafaker.Faker;
import com.sun.jersey.api.client.WebResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Created by sn1 on 1/18/15.
 */
@ContextConfiguration(locations = "classpath*:beans-ws-tests.xml")
public class TestBase extends AbstractTestNGSpringContextTests {
    public static final Faker faker = new Faker();
    
    public static WebResource resource;
    static{
        ResourceProvider provider = new ResourceProvider();
        resource = provider.getResource();
    }
    
}

