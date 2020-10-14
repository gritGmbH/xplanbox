package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplanbox.api.manager.PlanInfoBuilder;
import de.latlon.xplanbox.api.manager.config.ManagerApiConfiguration;
import de.latlon.xplanbox.api.manager.handler.PlanHandler;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/plans")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class PlansApi {

    @Autowired
    private PlanHandler planHandler;

    @Context
    private ManagerApiConfiguration managerApiConfiguration;

    @GET
    @Produces({ "application/json" })
    @Operation(summary = "Search for plan by name", description = "Returns a list of plans where the plan name contains the query string case insensitve", tags = {
                            "search" }, responses = {
                            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PlanInfo.class)))) })
    public Response findByName(
                            @QueryParam("planName")
                            @Parameter(description = "The name of the plan to search for", example = "bplan_123, fplan-123, rplan20200803")
                                                    String planName )
                            throws Exception {
        List<XPlan> plans = planHandler.findPlans( planName );
        List<PlanInfo> planInfos = plans.stream().map( xPlan -> {
            return new PlanInfoBuilder( xPlan, managerApiConfiguration ).selfMediaType( APPLICATION_JSON ).build();
        } ).collect( Collectors.toList() );
        return Response.ok().entity( planInfos ).build();
    }

}
