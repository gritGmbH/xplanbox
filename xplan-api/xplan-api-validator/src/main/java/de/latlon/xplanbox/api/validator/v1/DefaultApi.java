package de.latlon.xplanbox.api.validator.v1;

import io.swagger.v3.jaxrs2.integration.resources.BaseOpenApiResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.stereotype.Component;

import javax.servlet.ServletConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("/")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
public class DefaultApi extends BaseOpenApiResource {

    @Context
    private ServletConfig config;

    @Context
    private Application app;

    @GET
    @Produces({ "application/json" })
    @Operation(summary = "OpenAPI document", description = "API documentation", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation") })
    public Response openApi(
                            @Context
                                                    HttpHeaders headers,
                            @Context
                                                    UriInfo uriInfo )
                            throws Exception {
        return super.getOpenApi( headers, this.config, this.app, uriInfo, APPLICATION_JSON );
    }
}