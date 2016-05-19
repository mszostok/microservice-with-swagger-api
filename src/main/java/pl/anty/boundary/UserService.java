package pl.anty.boundary;

import io.swagger.annotations.*;
import org.codehaus.jackson.map.ObjectMapper;
import pl.anty.data.SampleUserData;
import pl.anty.exception.ApiException;
import pl.anty.model.User;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;


/**
 * @author mszostok
 */

@Stateless
@Path("/user")
@Api(value="/user", description = "Operations about user")
@Produces({"application/json"})
public class UserService {

    static SampleUserData sampleUserData = new SampleUserData();

    @GET
    @Path("/hello")
    @ApiOperation(value = "Sample User Data", notes = "Just return sample user data")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server") })
    public Response sayHello() {

        JsonObject value = Json.createObjectBuilder().add("method", "sayHello")
                .add("message", "Hello World!").build();

        return Response.status(200).entity(value).build();
    }

    @POST
    @ApiOperation(value = "Create user",
            notes = "This can only be done by the logged in user.",
            position = 1)
    public Response createUser(
            @ApiParam(value = "Created user object", required = true) User user) {
        sampleUserData.addUser(user);
        return Response.ok().entity("").build();
    }

    @PUT
    @Path("/{username}")
    @ApiOperation(value = "Updated user",
            notes = "This can only be done by the logged in user.",
            position = 4)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid user supplied"),
            @ApiResponse(code = 404, message = "User not found") })
    public Response updateUser(
            @ApiParam(value = "name that need to be deleted", required = true) @PathParam("username") String username,
            @ApiParam(value = "Updated user object", required = true) User user) {
        sampleUserData.updateUser(user);
        return Response.ok().entity("").build();
    }

    @DELETE
    @Path("/{username}")
    @ApiOperation(value = "Delete user",
            notes = "This can only be done by the logged in user.",
            position = 5)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid username supplied"),
            @ApiResponse(code = 404, message = "User not found") })
    public Response deleteUser(
            @ApiParam(value = "The name that needs to be deleted", required = true) @PathParam("username") String username) {
        sampleUserData.removeUser(username);
        return Response.ok().entity("").build();
    }

    @GET
    @Path("/{username}")
    @ApiOperation(value = "Get user by user name",
            response = User.class,
            position = 0)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid username supplied"),
            @ApiResponse(code = 404, message = "User not found") })
    public Response getUserByName(
            @ApiParam(value = "The name that needs to be fetched. Use user1 for testing. ", required = true) @PathParam("username") String username)
            throws ApiException, IOException {
        User user = sampleUserData.findUserByName(username);

        ObjectMapper mapper = new ObjectMapper();

        if (user != null) {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
            return Response.ok().entity(json).build();
        } else {
            throw new pl.anty.exception.NotFoundException(404, "User not found");
        }
    }
}




