package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplanbox.api.validator.v1.model.SystemConfig;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/info")
@Api(description = "the info API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-26T09:59:16.298+02:00[Europe/Berlin]")
public class InfoApi {

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "Show system and application configuration", notes = "Returns the system and application configuration", response = SystemConfig.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = SystemConfig.class)
    })
    public Response showConfig() {
        return Response.ok().entity("magic!").build();
    }
}
