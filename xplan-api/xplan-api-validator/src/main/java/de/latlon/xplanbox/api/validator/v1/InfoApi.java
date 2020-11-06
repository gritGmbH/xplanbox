package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplanbox.api.commons.v1.model.SystemConfig;
import de.latlon.xplanbox.api.validator.handler.ConfigHandler;
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
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
public class InfoApi {

    @Autowired
    private ConfigHandler configHandler;

    @GET
    @Produces({ "application/json" })
    @Operation(summary = "Show system and application configuration", description = "Returns the system and application configuration", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = SystemConfig.class))) })
    public Response showConfig()
                            throws IOException {
        return Response.ok().entity( configHandler.describeSystem() ).build();
    }
}
