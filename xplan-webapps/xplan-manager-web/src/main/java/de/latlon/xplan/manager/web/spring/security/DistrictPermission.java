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

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Checks if the requesting user has the permission to the archive with the district.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class DistrictPermission implements Permission {

	@Override
	public boolean isAllowed(Authentication authentication, Object targetDomainObject) {
		if (canHandleXPlanArchive(authentication, targetDomainObject)) {
			List<String> districts = ((XPlanArchive) targetDomainObject).getDistricts();
			return isAllowedToAllDistricts(authentication, districts);
		}
		else if (canHandleXPlan(authentication, targetDomainObject)) {
			String district = ((XPlan) targetDomainObject).getDistrict();
			return isAllowed(authentication, district);
		}
		return false;
	}

	private boolean canHandleXPlanArchive(Authentication authentication, Object targetDomainObject) {
		return authentication != null && targetDomainObject instanceof XPlanArchive;
	}

	private boolean canHandleXPlan(Authentication authentication, Object targetDomainObject) {
		return authentication != null && targetDomainObject instanceof XPlan;
	}

	private boolean isAllowedToAllDistricts(Authentication authentication, List<String> districts) {
		for (String district : districts) {
			if (!isAllowed(authentication, district)) {
				return false;
			}
		}
		return true;
	}

	private boolean isAllowed(Authentication authentication, String planDistrict) {
		if (planDistrict != null) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				if (grantedAuthority instanceof DistrictGrantedAuthority) {
					List<String> userDistricts = ((DistrictGrantedAuthority) grantedAuthority).getDistricts();
					if (userDistricts != null && userDistricts.contains(planDistrict))
						return true;
				}
			}
		}
		return false;
	}

}
