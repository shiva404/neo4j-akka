package com.campusconnect.neo4j.providers;

import com.campusconnect.neo4j.exceptions.InvalidInputDataException;
import com.campusconnect.neo4j.exceptions.Neo4jException;
import com.campusconnect.neo4j.types.Neo4jErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static com.campusconnect.neo4j.util.ErrorCodes.INTERNAL_SERVER_ERROR;

@Provider
public class Neo4jExceptionMapper implements ExceptionMapper<Throwable> {

    public static Neo4jErrorResponse getNeo4jErrorResponse(Neo4jException neo4jException) {
        return new Neo4jErrorResponse(neo4jException.getErrorCode(), "Client", neo4jException.getMessage());
    }

    @Override
    public Response toResponse(Throwable exceptionOccured) {
        // TODO Auto-generated method stub
        if (exceptionOccured instanceof InvalidInputDataException) {
            return Response.status(403).entity(getNeo4jErrorResponse((Neo4jException) exceptionOccured)).build();
        } else {
            Neo4jErrorResponse neo4jErrorResponse = new Neo4jErrorResponse(INTERNAL_SERVER_ERROR, "server", exceptionOccured.getMessage());
            return Response.status(500).entity(neo4jErrorResponse).build();
        }
    }
}