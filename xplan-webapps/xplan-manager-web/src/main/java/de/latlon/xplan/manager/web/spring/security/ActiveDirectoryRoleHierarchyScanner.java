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

import org.springframework.ldap.core.support.DefaultDirObjectFactory;

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of Active Directory Group Hierarchy Scanner.
 *
 * @deprecated This class be removed in a future version.
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version 1.11
 * @since 1.11
 */
@Deprecated
public class ActiveDirectoryRoleHierarchyScanner {

	private final String roleHierarchy;

	/**
	 * @param providerUrl provider url, never <code>null</code>
	 * @param domain domain, never <code>null</code>
	 * @param username username, never <code>null</code>
	 * @param password passowrd, never <code>null</code>
	 * @param searchNode search node, never <code>null</code>
	 * @param superUserGroups super user groups, never <code>null</code>
	 * @param editorGroups editor groups, never <code>null</code>
	 * @param groupToPlanDistricts group to plan districts, never <code>null</code>
	 * @throws NamingException
	 */
	public ActiveDirectoryRoleHierarchyScanner(String providerUrl, String domain, String username, String password,
			String searchNode, List<String> superUserGroups, List<String> editorGroups,
			Map<String, List<String>> groupToPlanDistricts) throws NamingException {
		DirContext context = createContext(providerUrl, domain, username, password);
		Set<String> groups = collectGroups(superUserGroups, editorGroups, groupToPlanDistricts);
		roleHierarchy = doScan(context, searchNode, groups);
	}

	/**
	 * Retrieve role hierarchy.
	 * @return role hierarchy, never <code>null</code>
	 */
	public String retrieveRoleHierarchy() {
		return roleHierarchy;
	}

	private DirContext createContext(String providerUrl, String domain, String username, String password)
			throws NamingException {
		Hashtable<String, String> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, providerUrl);
		String securityPrincipal = username + "@" + domain;
		env.put(Context.SECURITY_PRINCIPAL, securityPrincipal);
		env.put(Context.SECURITY_CREDENTIALS, password);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.OBJECT_FACTORIES, DefaultDirObjectFactory.class.getName());
		return new InitialDirContext(env);
	}

	private Set<String> collectGroups(List<String> superUserGroups, List<String> editorGroups,
			Map<String, List<String>> groupToPlanDistricts) {
		Set<String> groups = new HashSet<>();
		groups.addAll(superUserGroups);
		groups.addAll(editorGroups);
		groups.addAll(groupToPlanDistricts.keySet());
		return groups;
	}

	private String doScan(DirContext context, String searchNode, Set<String> groups) throws NamingException {
		List<List<String>> roleHierarchies = new ArrayList<>();
		for (String group : groups) {
			String role = "CN=" + group;
			String searchBase = role + "," + searchNode;
			List<String> roleHierarchy = new ArrayList<>();
			search(context, searchBase, roleHierarchy);
			roleHierarchies.add(roleHierarchy);
		}
		return printRoleHierarchies(roleHierarchies);
	}

	private void search(DirContext context, String searchBase, List<String> roleHierarchy) throws NamingException {
		Enumeration<SearchResult> result = searchGroups(context, searchBase);
		while (result.hasMoreElements()) {
			SearchResult nextResult = result.nextElement();
			Attributes attributes = nextResult.getAttributes();
			Attribute cn = attributes.get("cn");
			String groupName = cn.toString().substring(4);
			roleHierarchy.add(groupName);
			Attribute memberAttribute = attributes.get("member");
			if (memberAttribute != null) {
				List<String> subSearchBases = parseMembers(memberAttribute);
				for (String subSearchBase : subSearchBases) {
					search(context, subSearchBase, roleHierarchy);
				}
			}
		}
	}

	private List<String> parseMembers(Attribute memberAttribute) throws NamingException {
		List<String> groupMembers = new ArrayList<>();
		Enumeration<?> members = memberAttribute.getAll();
		while (members.hasMoreElements()) {
			groupMembers.add(members.nextElement().toString());
		}
		return groupMembers;
	}

	private String printRoleHierarchies(List<List<String>> roleHierarchies) {
		StringBuilder hierarchies = new StringBuilder();
		for (List<String> roleHierarchy : roleHierarchies) {
			Collections.reverse(roleHierarchy);
			boolean isFirst = true;
			for (String role : roleHierarchy) {
				if (!isFirst)
					hierarchies.append(" > ");
				hierarchies.append(role);
				isFirst = false;
			}
			hierarchies.append("\n");
		}
		return hierarchies.toString();
	}

	private Enumeration<SearchResult> searchGroups(DirContext context, String searchBase) throws NamingException {
		try {
			return context.search(searchBase, "(objectclass=group)", searchControls());
		}
		catch (NameNotFoundException e) {
			return Collections.emptyEnumeration();
		}
	}

	private SearchControls searchControls() {
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		return searchControls;
	}

}
