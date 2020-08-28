package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.v1.model.ManagerSystemConfig;

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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class InfoApi {

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "Show system and application configuration", notes = "Returns the system and application configuration", response = ManagerSystemConfig.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ManagerSystemConfig.class)
    })
    public Response showConfig() {
        return Response.ok().entity("magic!").build();
    }
}
