/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.manager.web.server.service;

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

import org.junit.Test;
import org.springframework.security.core.Authentication;

import de.latlon.xplan.manager.web.server.service.security.AuthorizationManager;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.spring.security.DistrictGrantedAuthority;

/**
 * Tests for {@link de.latlon.xplan.manager.web.server.service.rest.SecurityController}.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class SecurityServiceImplTest {

	@Test
	public void testRetrieveAuthorizationInfo_WithEnabledSecurityShouldReturnDistricts() throws Exception {
		SecurityServiceImpl controllerWithEnabledSecurity = createSecurityController(true,
				createAuthoritiesWithoutSuperUser());
		AuthorizationInfo authorizationInfo = controllerWithEnabledSecurity.retrieveAuthorizationInfo();
		List<String> districts = authorizationInfo.getAuthorizedDistricts();
		boolean isSuperUser = authorizationInfo.isSuperUser();

		assertTrue(districts.contains("district1"));
		assertTrue(districts.contains("district2"));
		assertTrue(districts.contains("district3"));
		assertThat(districts.size(), is(3));
		assertThat(isSuperUser, is(false));
	}

	@Test
	public void testRetrieveAuthorizationInfo_WithEnabledSecurityShouldReturnSuperUser() throws Exception {
		SecurityServiceImpl controllerWithEnabledSecurity = createSecurityController(true,
				createAuthoritiesWithSuperUser());
		AuthorizationInfo authorizationInfo = controllerWithEnabledSecurity.retrieveAuthorizationInfo();
		List<String> districts = authorizationInfo.getAuthorizedDistricts();
		boolean isSuperUser = authorizationInfo.isSuperUser();

		assertTrue(districts.contains("district1"));
		assertTrue(districts.contains("district2"));
		assertTrue(districts.contains("district3"));
		assertThat(districts.size(), is(3));
		assertThat(isSuperUser, is(true));
	}

	@Test
	public void testRetrieveAuthorizationInfo_WithDisabledSecurityShouldReturnSuperUserPermissions() throws Exception {
		AuthorizationManager securityManager = new AuthorizationManager(false);
		SecurityServiceImpl controllerWithDisabledSecurity = new SecurityServiceImpl(securityManager);
		AuthorizationInfo authorizationInfo = controllerWithDisabledSecurity.retrieveAuthorizationInfo();

		assertThat(authorizationInfo.getAuthorizedDistricts(), is(Collections.<String>emptyList()));
		assertThat(authorizationInfo.isSuperUser(), is(true));
	}

	private SecurityServiceImpl createSecurityController(boolean isSecurityEnabled,
			List<DistrictGrantedAuthority> authorities) {
		AuthorizationManager securityManager = spy(new AuthorizationManager(isSecurityEnabled));
		Authentication authentication = mock(Authentication.class);
		when(securityManager.retrieveAuthentication()).thenReturn(authentication);
		doReturn(authorities).when(authentication).getAuthorities();
		return new SecurityServiceImpl(securityManager);
	}

	private List<DistrictGrantedAuthority> createAuthoritiesWithoutSuperUser() {
		List<String> districts = new ArrayList<String>();
		districts.add("district1");
		districts.add("district2");
		DistrictGrantedAuthority authority1 = new DistrictGrantedAuthority(ROLE_USER.toString(), districts);
		DistrictGrantedAuthority authority2 = new DistrictGrantedAuthority(ROLE_USER.toString(),
				singletonList("district3"));
		List<DistrictGrantedAuthority> authorities = new ArrayList<DistrictGrantedAuthority>();
		authorities.add(authority1);
		authorities.add(authority2);
		return authorities;
	}

	private List<DistrictGrantedAuthority> createAuthoritiesWithSuperUser() {
		List<DistrictGrantedAuthority> authorities = createAuthoritiesWithoutSuperUser();
		DistrictGrantedAuthority authority = new DistrictGrantedAuthority(ROLE_SUPERUSER.toString(),
				Collections.<String>emptyList());
		authorities.add(authority);
		return authorities;
	}

}
