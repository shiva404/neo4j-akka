package com.campusconnect.neo4j.tests.functional.base;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

/**
 * Created by sn1 on 2/24/15.
 */
public class ResourceProvider extends JerseyTest {
    public static final AppDescriptor APP_DESCRIPTOR =
            new WebAppDescriptor.Builder("com.intuit.platform.integration.sdx.ws.beans")
                    .contextParam("contextConfigLocation", "classpath*:beans-ws-tests.xml")
                    .servletClass(SpringServlet.class)
                    .contextListenerClass(ContextLoaderListener.class)
                    .requestListenerClass(RequestContextListener.class)
                    .initParam("com.sun.jersey.spi.container.ContainerRequestFilters", "com.sun.jersey.api.container.filter.LoggingFilter")
                    .initParam("com.sun.jersey.spi.container.ContainerResponseFilters", "com.sun.jersey.api.container.filter.LoggingFilter")
                    .build();

    public ResourceProvider() {
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
