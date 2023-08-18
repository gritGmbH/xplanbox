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

import static de.latlon.xplan.manager.web.spring.security.XPlanAuthorizationRole.ROLE_EDITOR;
import static de.latlon.xplan.manager.web.spring.security.XPlanAuthorizationRole.ROLE_SUPERUSER;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.springframework.security.access.hierarchicalroles.NullRoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ActiveDirectoryGrantedAuthoritiesMapperTest {

	@Test
	public void testMapAuthorities() throws Exception {
		ActiveDirectoryGrantedAuthoritiesMapper mapper = new ActiveDirectoryGrantedAuthoritiesMapper(
				singletonList("empty"), singletonList("empty"), createGroupsAndDistricts(), new NullRoleHierarchy());
		Collection<? extends GrantedAuthority> mappedAuthorities = mapper.mapAuthorities(createAuthorities());

		assertThat(mappedAuthorities.size(), is(1));
		assertThat(mappedAuthorities, containsDistrictAuthority(singletonList("District")));
	}

	@Test
	public void testMapAuthoritiesWithEmptyMap() throws Exception {
		ActiveDirectoryGrantedAuthoritiesMapper mapper = new ActiveDirectoryGrantedAuthoritiesMapper(
				singletonList("empty"), singletonList("empty"), Collections.<String, List<String>>emptyMap(),
				new NullRoleHierarchy());
		Collection<? extends GrantedAuthority> mappedAuthorities = mapper.mapAuthorities(createAuthorities());

		assertThat(mappedAuthorities.size(), is(0));
	}

	@Test
	public void testMapAuthoritiesWithNullMap() throws Exception {
		ActiveDirectoryGrantedAuthoritiesMapper mapper = new ActiveDirectoryGrantedAuthoritiesMapper(
				singletonList("empty"), singletonList("empty"), null, new NullRoleHierarchy());
		Collection<? extends GrantedAuthority> mappedAuthorities = mapper.mapAuthorities(createAuthorities());

		assertThat(mappedAuthorities.size(), is(0));
	}

	@Test
	public void testMapAuthoritiesWithSuperUser() throws Exception {
		ActiveDirectoryGrantedAuthoritiesMapper mapper = new ActiveDirectoryGrantedAuthoritiesMapper(
				singletonList("super"), singletonList("empty"), createGroupsAndDistricts(), new NullRoleHierarchy());
		Collection<? extends GrantedAuthority> mappedAuthorities = mapper.mapAuthorities(createAuthorities());

		assertThat(mappedAuthorities.size(), is(2));
		assertThat(mappedAuthorities, containsDistrictAuthority(singletonList("District")));
		assertThat(mappedAuthorities, containsSuperUser());
	}

	@Test
	public void testMapAuthoritiesWithEditor() throws Exception {
		ActiveDirectoryGrantedAuthoritiesMapper mapper = new ActiveDirectoryGrantedAuthoritiesMapper(
				singletonList("empty"), singletonList("editor"), createGroupsAndDistricts(), new NullRoleHierarchy());
		Collection<? extends GrantedAuthority> mappedAuthorities = mapper.mapAuthorities(createAuthorities());

		assertThat(mappedAuthorities.size(), is(2));
		assertThat(mappedAuthorities, containsDistrictAuthority(singletonList("District")));
		assertThat(mappedAuthorities, containsEditor());
	}

	@Test
	public void testMapAuthoritiesWithNull() throws Exception {
		ActiveDirectoryGrantedAuthoritiesMapper mapper = new ActiveDirectoryGrantedAuthoritiesMapper(null, null, null,
				new NullRoleHierarchy());
		Collection<? extends GrantedAuthority> mappedAuthorities = mapper.mapAuthorities(createAuthorities());

		assertThat(mappedAuthorities.size(), is(0));
		assertThat(mappedAuthorities, notContainsSuperUser());
		assertThat(mappedAuthorities, notContainsEditor());
	}

	@Test
	public void testMapAuthoritiesWithHierarchy() throws Exception {
		createRoleHierarchy();
		ActiveDirectoryGrantedAuthoritiesMapper mapper = new ActiveDirectoryGrantedAuthoritiesMapper(
				singletonList("empty"), singletonList("empty"), createGroupsAndDistricts(), createRoleHierarchy());
		Collection<? extends GrantedAuthority> mappedAuthorities = mapper.mapAuthorities(createAuthorities());

		assertThat(mappedAuthorities.size(), is(2));
		assertThat(mappedAuthorities, containsDistrictAuthority(singletonList("District")));
		assertThat(mappedAuthorities, containsDistrictAuthority(singletonList("SecondLevelHierarchy")));
	}

	private Collection<? extends GrantedAuthority> createAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("dist"));
		authorities.add(new SimpleGrantedAuthority("test"));
		authorities.add(new SimpleGrantedAuthority("super"));
		authorities.add(new SimpleGrantedAuthority("editor"));
		authorities.add(new SimpleGrantedAuthority("firstLevelHierarchy"));
		return authorities;
	}

	private Map<String, List<String>> createGroupsAndDistricts() {
		Map<String, List<String>> districts = new HashMap<>();
		districts.put("dist", singletonList("District"));
		districts.put("area", singletonList("Area"));
		districts.put("secondLevelHierarchy", singletonList("SecondLevelHierarchy"));
		return districts;
	}

	private RoleHierarchy createRoleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("firstLevelHierarchy > secondLevelHierarchy");
		return roleHierarchy;
	}

	private Matcher<Collection<? extends GrantedAuthority>> containsDistrictAuthority(final List<String> district) {
		return new BaseMatcher<Collection<? extends GrantedAuthority>>() {

			@Override
			public boolean matches(Object item) {
				@SuppressWarnings("unchecked")
				Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) item;
				for (GrantedAuthority authority : authorities) {
					if (authority instanceof DistrictGrantedAuthority
							&& district.equals(((DistrictGrantedAuthority) authority).getDistricts()))
						return true;
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("List should contain one DistrictGrantedAuthority with district " + district);
			}
		};
	}

	private Matcher<Collection<? extends GrantedAuthority>> containsSuperUser() {
		return new BaseMatcher<Collection<? extends GrantedAuthority>>() {

			@Override
			public boolean matches(Object item) {
				@SuppressWarnings("unchecked")
				Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) item;
				for (GrantedAuthority authority : authorities) {
					if (authority instanceof DistrictGrantedAuthority
							&& ROLE_SUPERUSER.toString().equals(authority.getAuthority()))
						return true;
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("List should contain at least one super user!");
			}
		};
	}

	private Matcher<Collection<? extends GrantedAuthority>> notContainsSuperUser() {
		return new BaseMatcher<Collection<? extends GrantedAuthority>>() {

			@Override
			public boolean matches(Object item) {
				@SuppressWarnings("unchecked")
				Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) item;
				for (GrantedAuthority authority : authorities) {
					if (authority instanceof DistrictGrantedAuthority
							&& ROLE_SUPERUSER.toString().equals(authority.getAuthority()))
						return false;
				}
				return true;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("List should not contain a super user!");
			}
		};
	}

	private Matcher<Collection<? extends GrantedAuthority>> containsEditor() {
		return new BaseMatcher<Collection<? extends GrantedAuthority>>() {

			@Override
			public boolean matches(Object item) {
				@SuppressWarnings("unchecked")
				Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) item;
				for (GrantedAuthority authority : authorities) {
					if (authority instanceof DistrictGrantedAuthority
							&& ROLE_EDITOR.toString().equals(authority.getAuthority()))
						return true;
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("List should contain at least one editor!");
			}
		};
	}

	private Matcher<Collection<? extends GrantedAuthority>> notContainsEditor() {
		return new BaseMatcher<Collection<? extends GrantedAuthority>>() {

			@Override
			public boolean matches(Object item) {
				@SuppressWarnings("unchecked")
				Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) item;
				for (GrantedAuthority authority : authorities) {
					if (authority instanceof DistrictGrantedAuthority
							&& ROLE_EDITOR.toString().equals(authority.getAuthority()))
						return false;
				}
				return true;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("List should not contain an editor!");
			}
		};
	}

}
