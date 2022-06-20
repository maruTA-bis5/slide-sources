package net.bis5.example.hello;

import java.time.LocalDateTime;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("hello")
public class HelloResource {

    @GET
    @Produces("application/json")
    public HelloResponse getMessage(@QueryParam("name") String name) {
        if (name == null || name.isEmpty()) {
            name = "World";
        }
        return new HelloResponse(String.format("Hello, %s!",name), LocalDateTime.now());
    }
    
}
