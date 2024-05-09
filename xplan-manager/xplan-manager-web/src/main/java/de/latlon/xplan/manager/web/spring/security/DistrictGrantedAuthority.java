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

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

/**
 * Contains the role and districts the user is authorized.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class DistrictGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 6443157754173821834L;

	private final String role;

	private final List<String> districts;

	/**
	 * @param role role, such as user or administrator
	 */
	public DistrictGrantedAuthority(String role) {
		this(role, null);
	}

	/**
	 * @param role role, such as user or administrator
	 * @param districts districts, if <code>null</code>, an empty list is used.
	 */
	public DistrictGrantedAuthority(String role, List<String> districts) {
		this.role = role;
		if (districts != null)
			this.districts = districts;
		else
			this.districts = new ArrayList<>();
	}

	@Override
	public String getAuthority() {
		return role;
	}

	/**
	 * @return districts
	 */
	public List<String> getDistricts() {
		return districts;
	}

	@Override
	public String toString() {
		return "DistrictGrantedAuthority {role=" + role + ", districts=" + districts + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		DistrictGrantedAuthority that = (DistrictGrantedAuthority) o;

		if (!districts.equals(that.districts)) {
			return false;
		}
		if (role != null ? !role.equals(that.role) : that.role != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = role != null ? role.hashCode() : 0;
		result = 31 * result + districts.hashCode();
		return result;
	}

}
