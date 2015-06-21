package com.campusconnect.neo4j.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: sn1
 * Date: 6/13/15
 * Time: 9:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Path("health")
@Consumes("application/json")
@Produces("application/json")
public class HealthCheckResource {

    @Path("local")
    @GET
    public Response getLocalHealth() {
        return Response.ok().entity("Health ok").build();
    }
}
