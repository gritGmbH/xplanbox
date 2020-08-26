package de.latlon.xplanbox.api.validator.v1;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/")
@Api(description = "the  API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-26T09:59:16.298+02:00[Europe/Berlin]")
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
