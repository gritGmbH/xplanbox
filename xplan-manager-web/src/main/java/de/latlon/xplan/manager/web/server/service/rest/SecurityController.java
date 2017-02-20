package de.latlon.xplan.manager.web.server.service.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.latlon.xplan.manager.web.server.service.security.AuthorizationManager;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.shared.ConfigurationException;

/**
 * REST-Interface for security functions.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
@Controller
@RequestMapping(value = "/security")
public class SecurityController {

    private static final Logger LOG = LoggerFactory.getLogger( SecurityController.class );

    private final AuthorizationManager securityManager;

    public SecurityController( AuthorizationManager securityManager ) {
        this.securityManager = securityManager;
    }

    @RequestMapping(value = "/authorizationInfo", method = GET)
    @ResponseBody
    public AuthorizationInfo retrieveAuthorizationInfo( @Context HttpServletResponse response )
                    throws ConfigurationException {
        response.addHeader( "Expires", "-1" );
        LOG.info( "Retrieve authorization information." );
        if ( !securityManager.isSecurityEnabled() ) {
            LOG.info( "Authentication is disabled." );
            return new AuthorizationInfo( true );
        }
        LOG.info( "Authentication is enabled." );
        return securityManager.createAuthorizationInfoFromAuthentication();
    }

}