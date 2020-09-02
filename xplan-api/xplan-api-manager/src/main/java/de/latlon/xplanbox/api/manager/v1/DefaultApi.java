package de.latlon.xplanbox.api.manager.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.jaxrs2.integration.resources.BaseOpenApiResource;

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

@Path("/")
@Api(description = "the  API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class DefaultApi extends BaseOpenApiResource {

    @Context
    private ServletConfig config;

    @Context
    private Application app;

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "OpenAPI document", notes = "API documentation", response = Object.class, tags = {})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Object.class) })
    public Response openApi(
                            @Context
                                                    HttpHeaders headers,
                            @Context
                                                    UriInfo uriInfo )
                            throws Exception {
        return super.getOpenApi( headers, this.config, this.app, uriInfo, APPLICATION_JSON );
    }
}
