package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.v1.model.PlanInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/plans")
@Api(description = "the plans API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class PlansApi {

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "Search for plan by name", notes = "Returns a list of plans which match the search query", response = PlanInfo.class, responseContainer = "List", tags={ "search" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PlanInfo.class, responseContainer = "List")
    })
    public Response findByName(@QueryParam("planName")   @ApiParam("The name of the plan to search for")  String planName) {
        return Response.ok().entity("magic!").build();
    }
}
