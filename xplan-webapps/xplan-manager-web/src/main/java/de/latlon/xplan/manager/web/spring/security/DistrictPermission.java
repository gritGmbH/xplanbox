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
