package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplanbox.api.validator.v1.handler.ConfigHandler;
import de.latlon.xplanbox.api.validator.v1.model.SystemConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/info")
@Api(description = "the info API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
public class InfoApi {

    @Autowired
    private ConfigHandler configHandler;

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "Show system and application configuration", notes = "Returns the system and application configuration", response = SystemConfig.class, tags = {})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = SystemConfig.class) })
    public Response showConfig()
                            throws IOException {
        return Response.ok().entity( configHandler.describeSystem() ).build();
    }
}
