package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplanbox.api.manager.PlanInfoBuilder;
import de.latlon.xplanbox.api.manager.handler.PlanHandler;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/plans")
@Api(description = "the plans API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class PlansApi {

    @Autowired
    private PlanHandler planHandler;

    @Context
    private ManagerConfiguration managerConfiguration;

    @GET
    @Produces({ "application/json" })
    @ApiOperation(value = "Search for plan by name", notes = "Returns a list of plans which match the search query", response = PlanInfo.class, responseContainer = "List", tags = {
                            "search" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PlanInfo.class, responseContainer = "List") })
    public Response findByName(
                            @Context
                                                    UriInfo uriInfo,
                            @QueryParam("planName")
                            @ApiParam("The name of the plan to search for")
                                                    String planName )
                            throws Exception {
        List<XPlan> plans = planHandler.findPlans( planName );
        List<PlanInfo> planInfos = plans.stream().map( xPlan -> {
            return new PlanInfoBuilder( xPlan, uriInfo ).wmsEndpoint(
                                    managerConfiguration.getWmsEndpoint() ).requestedMediaType(
                                    APPLICATION_JSON ).build();
        } ).collect( Collectors.toList() );
        return Response.ok().entity( planInfos ).build();
    }

}
