package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.handler.ConfigHandler;
import de.latlon.xplanbox.api.manager.v1.model.ManagerSystemConfig;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class InfoApi {

    @Autowired
    private ConfigHandler configHandler;

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "Show system and application configuration", notes = "Returns the system and application configuration", response = ManagerSystemConfig.class, tags = {})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = ManagerSystemConfig.class) })
    public Response showConfig()
                            throws IOException {
        ManagerSystemConfig managerSystemConfig = configHandler.describeManagerSystem();
        return Response.ok().entity( managerSystemConfig ).build();
    }

}