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

import static de.latlon.xplan.manager.web.spring.security.XPlanAuthorizationRole.ROLE_SUPERUSER;
import static de.latlon.xplan.manager.web.spring.security.XPlanAuthorizationRole.ROLE_USER;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.security.core.Authentication;

import de.latlon.xplan.manager.web.server.service.rest.SecurityController;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.web.spring.security.DistrictGrantedAuthority;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class AuthorizationManagerTest {

    @Test
    public void testCreateAuthorizationInfoFromAuthentication_WithEnabledSecurityShouldReturnDistricts()
                    throws Exception {
        AuthorizationManager managerWithEnabledSecurity = createSpiedAuthorizationManager( true,
                                                                                           createAuthoritiesWithoutSuperUser() );
        AuthorizationInfo authorizationInfo = managerWithEnabledSecurity.createAuthorizationInfoFromAuthentication();
        List<String> districts = authorizationInfo.getAuthorizedDistricts();

        assertTrue( districts.contains( "district1" ) );
        assertTrue( districts.contains( "district2" ) );
        assertTrue( districts.contains( "district3" ) );
        assertThat( districts.size(), is( 3 ) );
        assertThat( authorizationInfo.isSuperUser(), is( false ) );
    }

    @Test
    public void testCreateAuthorizationInfoFromAuthentication_WithEnabledSecurityShouldReturnSuperUser()
                    throws Exception {
        AuthorizationManager managerWithEnabledSecurity = createSpiedAuthorizationManager( true,
                                                                                           createAuthoritiesWithSuperUser() );
        AuthorizationInfo authorizationInfo = managerWithEnabledSecurity.createAuthorizationInfoFromAuthentication();
        List<String> districts = authorizationInfo.getAuthorizedDistricts();

        assertTrue( districts.contains( "district1" ) );
        assertTrue( districts.contains( "district2" ) );
        assertTrue( districts.contains( "district3" ) );
        assertThat( districts.size(), is( 3 ) );
        assertThat( authorizationInfo.isSuperUser(), is( true ) );
    }

    @Test
    public void testCreateAuthorizationInfoFromAuthentication_WithDisabledSecurityShouldReturnSuperUserPermissions()
                    throws Exception {
        AuthorizationManager managerWithEnabledSecurity = createSpiedAuthorizationManager( false );
        AuthorizationInfo authorizationInfo = managerWithEnabledSecurity.createAuthorizationInfoFromAuthentication();

        assertThat( authorizationInfo.getAuthorizedDistricts().size(), is( 0 ) );
        assertThat( authorizationInfo.isSuperUser(), is( false ) );
    }

    @Test
    public void testIsSuperUser_WithEnabledSecurityFromNotSuperUser()
                    throws Exception {
        AuthorizationManager managerWithEnabledSecurity = createSpiedAuthorizationManager( true,
                                                                                           createAuthoritiesWithoutSuperUser() );
        assertThat( managerWithEnabledSecurity.isSuperUser(), is( false ) );
    }

    @Test
    public void testIsSuperUser_WithEnabledSecurityFromSuperUser()
                    throws Exception {
        AuthorizationManager managerWithEnabledSecurity = createSpiedAuthorizationManager( true,
                                                                                           createAuthoritiesWithSuperUser() );
        assertThat( managerWithEnabledSecurity.isSuperUser(), is( true ) );
    }

    @Test
    public void testIsSuperUser_WithDisabledSecurity()
                    throws Exception {
        AuthorizationManager managerWithEnabledSecurity = createSpiedAuthorizationManager( false );
        assertThat( managerWithEnabledSecurity.isSuperUser(), is( true ) );
    }

    @Test(expected = ConfigurationException.class)
    public void testRetrieveAuthorizationInfoWithEnabledSecurityAndWithoutAuthenticationInstanceShouldThrowException()
                    throws Exception {
        AuthorizationManager securityManager = new AuthorizationManager( true );
        SecurityController controllerWithEnabledSecurity = new SecurityController( securityManager );
        controllerWithEnabledSecurity.retrieveAuthorizationInfo( mock( HttpServletResponse.class ) );
    }

    private AuthorizationManager createSpiedAuthorizationManager( boolean isSecurityEnabled ) {
        AuthorizationManager securityManager = spy( new AuthorizationManager( isSecurityEnabled ) );
        Authentication authentication = mock( Authentication.class );
        when( securityManager.retrieveAuthentication() ).thenReturn( authentication );
        return securityManager;
    }

    private AuthorizationManager createSpiedAuthorizationManager( boolean isSecurityEnabled,
                                                                  List<DistrictGrantedAuthority> authorities ) {
        AuthorizationManager securityManager = spy( new AuthorizationManager( isSecurityEnabled ) );
        Authentication authentication = mock( Authentication.class );
        when( securityManager.retrieveAuthentication() ).thenReturn( authentication );
        doReturn( authorities ).when( authentication ).getAuthorities();
        return securityManager;
    }

    private List<DistrictGrantedAuthority> createAuthoritiesWithoutSuperUser() {
        List<String> districts = new ArrayList<String>();
        districts.add( "district1" );
        districts.add( "district2" );
        DistrictGrantedAuthority authority1 = new DistrictGrantedAuthority( ROLE_USER.toString(), districts );
        DistrictGrantedAuthority authority2 = new DistrictGrantedAuthority( ROLE_USER.toString(),
                        singletonList( "district3" ) );
        List<DistrictGrantedAuthority> authorities = new ArrayList<DistrictGrantedAuthority>();
        authorities.add( authority1 );
        authorities.add( authority2 );
        return authorities;
    }

    private List<DistrictGrantedAuthority> createAuthoritiesWithSuperUser() {
        List<DistrictGrantedAuthority> authorities = createAuthoritiesWithoutSuperUser();
        DistrictGrantedAuthority authority = new DistrictGrantedAuthority( ROLE_SUPERUSER.toString(),
                        Collections.<String>emptyList() );
        authorities.add( authority );
        return authorities;
    }

}
