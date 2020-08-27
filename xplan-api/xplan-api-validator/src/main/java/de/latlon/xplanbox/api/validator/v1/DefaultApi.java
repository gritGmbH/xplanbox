package de.latlon.xplanbox.api.validator.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
@Api(description = "the  API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
public class DefaultApi {

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "OpenAPI document", notes = "API documentation", response = Object.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = Object.class)
    })
    public Response openApi() {
        return Response.ok().entity("magic!").build();
    }
}
