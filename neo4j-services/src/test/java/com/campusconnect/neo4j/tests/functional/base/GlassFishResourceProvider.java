package com.campusconnect.neo4j.tests.functional.base;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/26/15
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class GlassFishResourceProvider extends JerseyTest {
    private static final AppDescriptor APP_DESCRIPTOR =
            new WebAppDescriptor.Builder("com.campusconnect.neo4j.resources")
                    .contextParam("contextConfigLocation", "classpath*:beans-ws-tests.xml")
                    .servletClass(SpringServlet.class)
                    .contextListenerClass(ContextLoaderListener.class)
                    .requestListenerClass(RequestContextListener.class)
                    .initParam("com.sun.jersey.spi.container.ContainerRequestFilters", "com.sun.jersey.api.container.filter.LoggingFilter")
                    .initParam("com.sun.jersey.spi.container.ContainerResponseFilters", "com.sun.jersey.api.container.filter.LoggingFilter")
                    .build();

    public GlassFishResourceProvider() {
        super(APP_DESCRIPTOR);
    }

    public WebResource getResource() {
        return resource();
    }

    @BeforeSuite
    public void initTestContainer() throws Exception {
        setUp();
    }

    @AfterSuite
    public void tearDownTestContainer() throws Exception {
        tearDown();
    }

}
