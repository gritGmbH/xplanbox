/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
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
