package de.latlon.xplan.manager.web.client.service;

import com.google.gwt.core.client.GWT;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * REST interface for security functions.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public interface SecurityService extends RestService {

    public static class Util {

        private static SecurityService instance;

        public static SecurityService getService() {
            if ( instance == null ) {
                instance = GWT.create( SecurityService.class );
            }
            Resource resource = new Resource( GWT.getModuleBaseURL() + "rest/security" );
            ( (RestServiceProxy) instance ).setResource( resource );
            return instance;
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("/authorizationInfo")
    void retrieveAuthorizationInfo( MethodCallback<AuthorizationInfo> callback );

}