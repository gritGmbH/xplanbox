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
package de.latlon.xplan.manager.web.server.service;

import de.latlon.xplan.manager.web.server.service.security.AuthorizationManager;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
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
 * Tests for {@link de.latlon.xplan.manager.web.server.service.SecurityServiceImpl}.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class SecurityServiceImplTest {

	@Test
	public void testRetrieveAuthorizationInfo_WithEnabledSecurity() throws Exception {
		SecurityServiceImpl controllerWithEnabledSecurity = createSecurityController(true);
		AuthorizationInfo authorizationInfo = controllerWithEnabledSecurity.retrieveAuthorizationInfo();
		boolean isSuperUser = authorizationInfo.isSuperUser();

		assertThat(isSuperUser, is(false));
	}

	@Test
	public void testRetrieveAuthorizationInfo_WithEnabledSecurityShouldReturnSuperUser() throws Exception {
		SecurityServiceImpl controllerWithEnabledSecurity = createSecurityController(true,
				createAuthoritiesWithSuperUser());
		AuthorizationInfo authorizationInfo = controllerWithEnabledSecurity.retrieveAuthorizationInfo();
		boolean isSuperUser = authorizationInfo.isSuperUser();

		assertThat(isSuperUser, is(true));
	}

	@Test
	public void testRetrieveAuthorizationInfo_WithDisabledSecurityShouldReturnSuperUserPermissions() throws Exception {
		AuthorizationManager securityManager = new AuthorizationManager(false);
		SecurityServiceImpl controllerWithDisabledSecurity = new SecurityServiceImpl(securityManager);
		AuthorizationInfo authorizationInfo = controllerWithDisabledSecurity.retrieveAuthorizationInfo();

		assertThat(authorizationInfo.isSuperUser(), is(true));
	}

	private SecurityServiceImpl createSecurityController(boolean isSecurityEnabled) {
		return createSecurityController(isSecurityEnabled, Collections.emptyList());
	}

	private SecurityServiceImpl createSecurityController(boolean isSecurityEnabled,
			List<SimpleGrantedAuthority> authorities) {
		AuthorizationManager securityManager = spy(new AuthorizationManager(isSecurityEnabled));
		Authentication authentication = mock(Authentication.class);
		when(securityManager.retrieveAuthentication()).thenReturn(authentication);
		doReturn(authorities).when(authentication).getAuthorities();
		return new SecurityServiceImpl(securityManager);
	}

	private List<SimpleGrantedAuthority> createAuthoritiesWithSuperUser() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ROLE_SUPERUSER.toString());
		authorities.add(authority);
		return authorities;
	}

}
