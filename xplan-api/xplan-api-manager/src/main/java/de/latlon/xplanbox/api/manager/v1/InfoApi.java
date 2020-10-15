package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.handler.ConfigHandler;
import de.latlon.xplanbox.api.manager.v1.model.ManagerSystemConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/info")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class InfoApi {

    @Autowired
    private ConfigHandler configHandler;

    @GET
    @Produces({ "application/json" })
    @Operation(summary = "Show system and application configuration", description = "Returns the system and application configuration", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ManagerSystemConfig.class))) })
    public Response showConfig()
                            throws IOException {
        ManagerSystemConfig managerSystemConfig = configHandler.describeManagerSystem();
        return Response.ok().entity( managerSystemConfig ).build();
    }
}