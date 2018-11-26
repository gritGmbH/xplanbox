package de.latlon.xplan.manager.web.client.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gwt.core.client.GWT;

import de.latlon.xplan.manager.web.shared.LegislationStatus;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;

/**
 * REST interface of the manager to get, remove, import plans.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public interface ManagerService extends RestService {

    public static class Util {

        private static ManagerService instance;

        public static ManagerService getService() {
            if ( instance == null ) {
                instance = GWT.create( ManagerService.class );
            }
            Resource resource = new Resource( GWT.getModuleBaseURL() + "rest/manager" );
            ( (RestServiceProxy) instance ).setResource( resource );
            return instance;
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/plans")
    void getPlansFromManager( MethodCallback<List<XPlan>> callback );

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/local/plan")
    void getPlanFromLocal( MethodCallback<XPlan> callback );

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/edit/plan/{planId}")
    void getPlanToEdit( @PathParam("planId") String planId, MethodCallback<XPlanToEdit> callback  );

    @POST
    @Path("/edit/plan/{planId}")
    void editPlan( @PathParam("planId") String planId, 
                   @QueryParam("updateRasterConfig") boolean updateRasterConfig,
                   @RequestBody XPlanToEdit xPlanToEdit, 
                   MethodCallback<Void> callback  );

    @POST
    @Produces(APPLICATION_JSON)
    @Path("/edit/raster/{planId}")
    void evaluateRaster( @PathParam("planId") String planId, 
                         @RequestBody XPlanToEdit xPlanToEdit, 
                         MethodCallback<List<RasterEvaluationResult>> callback );
    
    @DELETE
    @Path("/plan/{planId}")
    void removePlanFromManager( @PathParam("planId")
    String planId, MethodCallback<Void> callback );

    @DELETE
    @Path("/local/plan/{planId}")
    void removePlanFromFileSystem( @PathParam("planId")
    String planId, MethodCallback<Void> callback );

    @PUT
    @Path("/plan/{planId}")
    void importPlan( @PathParam("planId")
    String planId, @QueryParam("internalId")
    String internalId, @QueryParam("defaultCrs")
    String defaultCrs, @QueryParam("makeRasterConfig")
    boolean makeRasterConfig, @QueryParam("planStatus")
    PlanStatus planStatus, @QueryParam(value = "startDateTime") 
    Date startDateTime, @QueryParam(value = "endDateTime") 
    Date endDateTime, MethodCallback<Boolean> callback );

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/internalid/{internalId}")
    void retrieveMatchingInternalIds( @PathParam("internalId")
    String internalId, MethodCallback<Map<String, String>> callback );

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/crs/{id}")
    void isCrsSet( @PathParam("id")
    String id, MethodCallback<Boolean> callback );

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/raster/{id}")
    void evaluateRaster( @PathParam("id")
    String id, MethodCallback<List<RasterEvaluationResult>> callback );

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/legislationstatus/{id}")
    void determineLegislationStatus( @PathParam("id")
    String id, MethodCallback<LegislationStatus> callback );

    @GET
    @Path("/plu/plan/{planId}")
    void publishPlan( @PathParam("planId") String planId, MethodCallback<Boolean> callback );

}