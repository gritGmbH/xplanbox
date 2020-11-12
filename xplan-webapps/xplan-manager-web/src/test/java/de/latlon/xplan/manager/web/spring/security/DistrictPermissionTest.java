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
