package pl.anty.boundary;

/**
 * Created by indianer on 20.04.2016.
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Stateless
@Path("/hello")
@Api(value = "/hello", description = "Say Hello!")
@Produces({"application/json"})
public class CatalogService {

    @GET
    @ApiOperation(value = "Say Hello World", notes = "Just return hello world phrase")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server") })
    public Response sayHello() {

        JsonObject value = Json.createObjectBuilder().add("method", "sayHello")
                .add("message", "Hello World!").build();

        return Response.status(200).entity(value).build();
    }
}




