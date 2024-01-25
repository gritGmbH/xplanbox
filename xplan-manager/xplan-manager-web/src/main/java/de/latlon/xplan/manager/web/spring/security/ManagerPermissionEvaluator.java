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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

/**
 * Evaluates if an authentication with {@link DistrictGrantedAuthority} has permission to
 * a XPlanArchive.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ManagerPermissionEvaluator implements PermissionEvaluator {

	private static final Logger LOG = LoggerFactory.getLogger(ManagerPermissionEvaluator.class);

	private final Map<String, Permission> permissionNameToPermissions;

	public ManagerPermissionEvaluator(Map<String, Permission> permissionNameToPermissionMap) {
		if (permissionNameToPermissionMap != null)
			this.permissionNameToPermissions = permissionNameToPermissionMap;
		else
			this.permissionNameToPermissions = new HashMap<String, Permission>();
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if (canHandle(authentication, targetDomainObject, permission)) {
			LOG.info("Check permission for authentication {} and target {}", authentication, targetDomainObject);
			return checkPermission(authentication, targetDomainObject, (String) permission);
		}
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		throw new UnsupportedOperationException("Call method without targetId!");
	}

	private boolean canHandle(Authentication authentication, Object targetDomainObject, Object permission) {
		return targetDomainObject != null && authentication != null && permission instanceof String;
	}

	private boolean checkPermission(Authentication authentication, Object targetDomainObject, String permissionKey) {
		verifyPermissionIsDefined(permissionKey);
		Permission permission = permissionNameToPermissions.get(permissionKey);
		return permission.isAllowed(authentication, targetDomainObject);
	}

	private void verifyPermissionIsDefined(String permissionKey) {
		if (!permissionNameToPermissions.containsKey(permissionKey)) {
			throw new UnsupportedOperationException("The permission key is not defined! ");
		}

	}

}
