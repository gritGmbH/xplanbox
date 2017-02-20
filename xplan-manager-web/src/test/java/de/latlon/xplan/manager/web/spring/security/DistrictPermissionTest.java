package de.latlon.xplan.manager.web.spring.security;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class DistrictPermissionTest {

    @Test
    public void testIsAllowedArchiveAuthorised()
                    throws Exception {
        DistrictPermission evaluator = new DistrictPermission();
        XPlanArchive archive = createArchive( "planDistrict" );
        boolean hasPermission = evaluator.isAllowed( createAuthentication(), archive );
        assertThat( hasPermission, is( true ) );
    }

    @Test
    public void testIsAllowedArchiveAccessDenied()
                    throws Exception {
        DistrictPermission evaluator = new DistrictPermission();
        XPlanArchive archive = createArchive( "unauthorized" );
        boolean hasPermission = evaluator.isAllowed( createAuthentication(), archive );
        assertThat( hasPermission, is( false ) );
    }

    @Test
    public void testIsAllowedPlanAuthorised()
                    throws Exception {
        DistrictPermission evaluator = new DistrictPermission();
        XPlan plan = createPlan( "planDistrict" );
        boolean hasPermission = evaluator.isAllowed( createAuthentication(), plan );
        assertThat( hasPermission, is( true ) );
    }

    @Test
    public void testIsAllowedPlanAccessDenied()
                    throws Exception {
        DistrictPermission evaluator = new DistrictPermission();
        XPlan plan = createPlan( "unauthorized" );
        boolean hasPermission = evaluator.isAllowed( createAuthentication(), plan );
        assertThat( hasPermission, is( false ) );
    }

    private Authentication createAuthentication() {
        Authentication mock = mock( Authentication.class );
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add( new DistrictGrantedAuthority( "role", createDistricts() ) );
        doReturn( authorities ).when( mock ).getAuthorities();
        return mock;
    }

    private XPlanArchive createArchive( String planDistrict ) {
        XPlanArchive mock = mock( XPlanArchive.class );
        doReturn( planDistrict ).when( mock ).getDistrict();
        return mock;
    }

    private XPlan createPlan( String planDistrict ) {
        XPlan mock = mock( XPlan.class );
        doReturn( planDistrict ).when( mock ).getDistrict();
        return mock;
    }

    private List<String> createDistricts() {
        List<String> districts = new ArrayList<>();
        districts.add( "planDistrict" );
        districts.add( "planArea" );
        return districts;
    }

}
