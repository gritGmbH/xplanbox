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
package de.latlon.xplan.manager.web.spring.security;

import org.junit.Test;

import javax.naming.NamingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @deprecated This class be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@Deprecated
public class ActiveDirectoryRoleHierarchyScannerTestManual {

	private static final String PROVIDERURL = "ldap://adserver:389";

	private static final String DOMAIN = "adserver.lat-lon";

	private static final String USERNAME = "ll-technical";

	private static final String PASSWORD = "ADServer!";

	private static final String SEARCHNODE = "OU=lgvxplanisk,DC=adserver,DC=lat-lon";

	@Test
	public void testRetrieveRoleHierarchy() throws Exception {
		ActiveDirectoryRoleHierarchyScanner scanner = initRoleHierarchyScanner();
		String hierarchy = scanner.retrieveRoleHierarchy();

		assertThat(hierarchy, containsString("B-Plan > ALTONA"));
		assertThat(hierarchy, containsString("G11 > SUPER"));
		assertThat(hierarchy, containsString("EDITOR"));
		assertThat(hierarchy, containsString("HARBURG"));
		assertThat(hierarchy, containsString("HAMBURGNORD"));
	}

	private static ActiveDirectoryRoleHierarchyScanner initRoleHierarchyScanner() throws NamingException {
		List<String> editorGroups = asList("ALTONA", "EDITOR");
		List<String> superGroups = asList("SUPER");
		Map<String, List<String>> districtMap = new HashMap<>();
		districtMap.put("ALTONA", asList("Altona"));
		districtMap.put("HARBURG", asList("Harburg"));
		districtMap.put("HAMBURGNORD", asList("Hamburg-Nord"));
		return new ActiveDirectoryRoleHierarchyScanner(PROVIDERURL, DOMAIN, USERNAME, PASSWORD, SEARCHNODE,
				editorGroups, superGroups, districtMap);
	}

}
