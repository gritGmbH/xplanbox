//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.web.server.service.security;

import static de.latlon.xplan.manager.web.spring.security.XPlanAuthorizationRole.ROLE_EDITOR;
import static de.latlon.xplan.manager.web.spring.security.XPlanAuthorizationRole.ROLE_SUPERUSER;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.web.spring.security.DistrictGrantedAuthority;

/**
 * Manages authorization aspects of the current user.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class AuthorizationManager {

    private static final Logger LOG = LoggerFactory.getLogger( AuthorizationManager.class );

    private final boolean isSecurityEnabled;

    /**
     * @param isSecurityEnabled
     *            <code>true</code> if security is enabled, <code>false</code> otherwise
     */
    public AuthorizationManager( boolean isSecurityEnabled ) {
        this.isSecurityEnabled = isSecurityEnabled;
    }

    /**
     * @return <code>true</code> if security is enabled, <code>false</code> otherwise
     */
    public boolean isSecurityEnabled() {
        return isSecurityEnabled;
    }

    /**
     * @return the {@link AuthorizationInfo} from current authentication, never <code>null</code>
     * @throws ConfigurationException
     *             if security is enabled but no authentication can be found
     */
    public AuthorizationInfo createAuthorizationInfoFromAuthentication()
                    throws ConfigurationException {
        List<String> districts = new ArrayList<>();
        boolean isSuperUser = false;
        boolean isEditor = false;

        Authentication authentication = retrieveAuthentication();
        if ( authentication != null ) {
            Collection<? extends GrantedAuthority> authorities = retrieveAuthorities( authentication );
            for ( GrantedAuthority grantedAuthority : authorities ) {
                isSuperUser = checkSuperUser( isSuperUser, grantedAuthority );
                isEditor = checkEditor( isEditor, grantedAuthority );
                if ( grantedAuthority instanceof DistrictGrantedAuthority ) {
                    addDistricts( districts, (DistrictGrantedAuthority) grantedAuthority );
                }
            }
        } else {
            String message = "Security is enabled in spring configuration although there is no Authentication instance!";
            LOG.warn( message );
            throw new ConfigurationException( message );
        }
        return new AuthorizationInfo( districts, isSuperUser, isEditor );
    }

    public boolean isSuperUser()
                    throws ConfigurationException {
        if ( !isSecurityEnabled )
            return true;
        Authentication authentication = retrieveAuthentication();
        boolean isSuperUser = false;
        if ( authentication != null ) {
            Collection<? extends GrantedAuthority> authorities = retrieveAuthorities( authentication );
            for ( GrantedAuthority grantedAuthority : authorities ) {
                isSuperUser = checkSuperUser( isSuperUser, grantedAuthority );
            }
        } else {
            String message = "Security is enabled in spring configuration although there is no Authentication instance!";
            LOG.warn( message );
            throw new ConfigurationException( message );
        }
        return isSuperUser;
    }

    /**
     * Retrieves an authentication instance.
     * 
     * @return authentication
     */
    public Authentication retrieveAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication();
    }

    private Collection<? extends GrantedAuthority> retrieveAuthorities( Authentication authentication ) {
        return authentication.getAuthorities();
    }

    private void addDistricts( List<String> districts, DistrictGrantedAuthority grantedAuthority ) {
        List<String> authorityDistricts = grantedAuthority.getDistricts();
        districts.addAll( authorityDistricts );
    }

    private boolean checkSuperUser( boolean isSuperUser, GrantedAuthority grantedAuthority ) {
        if ( ROLE_SUPERUSER.toString().equals( grantedAuthority.getAuthority() ) )
            isSuperUser = true;
        return isSuperUser;
    }

    private boolean checkEditor( boolean isEditor, GrantedAuthority grantedAuthority ) {
        if ( ROLE_EDITOR.toString().equals( grantedAuthority.getAuthority() ) )
            isEditor = true;
        return isEditor;
    }

}