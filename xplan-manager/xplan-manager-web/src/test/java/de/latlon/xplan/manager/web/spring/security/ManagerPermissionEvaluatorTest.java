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
package de.latlon.xplan.manager.web.spring.security;

import org.junit.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ManagerPermissionEvaluatorTest {

	@Test
	public void testHasPermission() throws Exception {
		Permission permission = mock(Permission.class);
		ManagerPermissionEvaluator evaluator = new ManagerPermissionEvaluator(createPermissions(permission));
		Object targetDomainObject = "targetDomainObject";
		Authentication authentication = createAuthentication();
		evaluator.hasPermission(authentication, targetDomainObject, "permissionA");

		verify(permission).isAllowed(authentication, targetDomainObject);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testHasPermissionWithUnkownPermission() throws Exception {
		ManagerPermissionEvaluator evaluator = new ManagerPermissionEvaluator(
				createPermissions(mock(Permission.class)));
		Object targetDomainObject = "targetDomainObject";
		Authentication authentication = createAuthentication();
		evaluator.hasPermission(authentication, targetDomainObject, "permissionB");
	}

	private Map<String, Permission> createPermissions(Permission permission) {
		Map<String, Permission> permissions = new HashMap<String, Permission>();
		permissions.put("permissionA", permission);
		return permissions;
	}

	private Authentication createAuthentication() {
		Authentication mock = mock(Authentication.class);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new DistrictGrantedAuthority("role", createDistricts()));
		doReturn(authorities).when(mock).getAuthorities();
		return mock;
	}

	private List<String> createDistricts() {
		List<String> districts = new ArrayList<>();
		districts.add("dist");
		return districts;
	}

}
