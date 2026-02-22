package org.danilovolles;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;

@Path("/example")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class ExampleResource {

    private int i = 0;

    @GET
    public int getI() {
        return i;
    }

    @GET()
    @Path("/nano")
    public int getIdDiferentao() {
        return LocalDateTime.now().getNano();
    }

    @POST
    public void setI() {
        i++;
    }

    @DELETE
    public void remove() {
        i--;
    }

    @PUT
    public void setI(int i) {
        this.i = i;
    }
}
