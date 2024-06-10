/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.manager.web.server.service.security;

import de.latlon.xplan.manager.web.server.service.SecurityServiceImpl;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.manager.web.spring.security.XPlanAuthorizationRole.ROLE_SUPERUSER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class AuthorizationManagerTest {

	@Test
	public void testCreateAuthorizationInfoFromAuthentication_WithEnabledSecurityShouldReturnSuperUser()
			throws Exception {
		AuthorizationManager managerWithEnabledSecurity = createSecurityManager(true, createAuthoritiesWithSuperUser());
		AuthorizationInfo authorizationInfo = managerWithEnabledSecurity.createAuthorizationInfoFromAuthentication();

		assertThat(authorizationInfo.isSuperUser(), is(true));
	}

	@Test
	public void testCreateAuthorizationInfoFromAuthentication_WithDisabledSecurityShouldReturnSuperUserPermissions()
			throws Exception {
		AuthorizationManager managerWithEnabledSecurity = createSecurityManager(false);
		AuthorizationInfo authorizationInfo = managerWithEnabledSecurity.createAuthorizationInfoFromAuthentication();

		assertThat(authorizationInfo.isSuperUser(), is(false));
	}

	@Test
	public void testIsSuperUser_WithEnabledSecurityFromNotSuperUser() throws Exception {
		AuthorizationManager managerWithEnabledSecurity = createSecurityManager(true);
		assertThat(managerWithEnabledSecurity.isSuperUser(), is(false));
	}

	@Test
	public void testIsSuperUser_WithEnabledSecurityFromSuperUser() throws Exception {
		AuthorizationManager managerWithEnabledSecurity = createSecurityManager(true, createAuthoritiesWithSuperUser());
		assertThat(managerWithEnabledSecurity.isSuperUser(), is(true));
	}

	@Test
	public void testIsSuperUser_WithDisabledSecurity() throws Exception {
		AuthorizationManager managerWithEnabledSecurity = createSecurityManager(false);
		assertThat(managerWithEnabledSecurity.isSuperUser(), is(true));
	}

	@Test(expected = ConfigurationException.class)
	public void testRetrieveAuthorizationInfoWithEnabledSecurityAndWithoutAuthenticationInstanceShouldThrowException()
			throws Exception {
		AuthorizationManager securityManager = new AuthorizationManager(true);
		SecurityServiceImpl controllerWithEnabledSecurity = new SecurityServiceImpl(securityManager);
		controllerWithEnabledSecurity.retrieveAuthorizationInfo();
	}

	private AuthorizationManager createSecurityManager(boolean isSecurityEnabled) {
		return createSecurityManager(isSecurityEnabled, Collections.emptyList());
	}

	private AuthorizationManager createSecurityManager(boolean isSecurityEnabled,
			List<SimpleGrantedAuthority> authorities) {
		AuthorizationManager securityManager = spy(new AuthorizationManager(isSecurityEnabled));
		Authentication authentication = mock(Authentication.class);
		when(securityManager.retrieveAuthentication()).thenReturn(authentication);
		doReturn(authorities).when(authentication).getAuthorities();
		return securityManager;
	}

	private List<SimpleGrantedAuthority> createAuthoritiesWithSuperUser() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ROLE_SUPERUSER.toString());
		authorities.add(authority);
		return authorities;
	}

}
