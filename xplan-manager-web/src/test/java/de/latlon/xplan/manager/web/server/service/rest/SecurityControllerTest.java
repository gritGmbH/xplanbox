package de.latlon.xplan.manager.web.server.service.rest;

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

import de.latlon.xplan.manager.web.server.service.security.AuthorizationManager;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.spring.security.DistrictGrantedAuthority;

/**
 * Tests for {@link de.latlon.xplan.manager.web.server.service.rest.SecurityController}.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class SecurityControllerTest {

    @Test
    public void testRetrieveAuthorizationInfo_WithEnabledSecurityShouldReturnDistricts()
                    throws Exception {
        SecurityController controllerWithEnabledSecurity = createSecurityController( true,
                                                                                     createAuthoritiesWithoutSuperUser() );
        AuthorizationInfo authorizationInfo = controllerWithEnabledSecurity.retrieveAuthorizationInfo( mock( HttpServletResponse.class ) );
        List<String> districts = authorizationInfo.getAuthorizedDistricts();
        boolean isSuperUser = authorizationInfo.isSuperUser();

        assertTrue( districts.contains( "district1" ) );
        assertTrue( districts.contains( "district2" ) );
        assertTrue( districts.contains( "district3" ) );
        assertThat( districts.size(), is( 3 ) );
        assertThat( isSuperUser, is( false ) );
    }

    @Test
    public void testRetrieveAuthorizationInfo_WithEnabledSecurityShouldReturnSuperUser()
                    throws Exception {
        SecurityController controllerWithEnabledSecurity = createSecurityController( true,
                                                                                     createAuthoritiesWithSuperUser() );
        AuthorizationInfo authorizationInfo = controllerWithEnabledSecurity.retrieveAuthorizationInfo( mock( HttpServletResponse.class ) );
        List<String> districts = authorizationInfo.getAuthorizedDistricts();
        boolean isSuperUser = authorizationInfo.isSuperUser();

        assertTrue( districts.contains( "district1" ) );
        assertTrue( districts.contains( "district2" ) );
        assertTrue( districts.contains( "district3" ) );
        assertThat( districts.size(), is( 3 ) );
        assertThat( isSuperUser, is( true ) );
    }

    @Test
    public void testRetrieveAuthorizationInfo_WithDisabledSecurityShouldReturnSuperUserPermissions()
                    throws Exception {
        AuthorizationManager securityManager = new AuthorizationManager( false );
        SecurityController controllerWithDisabledSecurity = new SecurityController( securityManager );
        AuthorizationInfo authorizationInfo = controllerWithDisabledSecurity.retrieveAuthorizationInfo( mock( HttpServletResponse.class ) );

        assertThat( authorizationInfo.getAuthorizedDistricts(), is( Collections.<String>emptyList() ) );
        assertThat( authorizationInfo.isSuperUser(), is( true ) );
    }

    private SecurityController createSecurityController( boolean isSecurityEnabled,
                                                         List<DistrictGrantedAuthority> authorities ) {
        AuthorizationManager securityManager = spy( new AuthorizationManager( isSecurityEnabled ) );
        Authentication authentication = mock( Authentication.class );
        when( securityManager.retrieveAuthentication() ).thenReturn( authentication );
        doReturn( authorities ).when( authentication ).getAuthorities();
        return new SecurityController( securityManager );
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