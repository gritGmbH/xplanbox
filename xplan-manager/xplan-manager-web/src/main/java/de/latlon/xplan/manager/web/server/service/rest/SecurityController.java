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
package de.latlon.xplan.manager.web.server.service.rest;

import de.latlon.xplan.manager.web.server.service.security.AuthorizationManager;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * REST-Interface for security functions.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
@Controller
@RequestMapping(value = "/security")
public class SecurityController {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityController.class);

	private final AuthorizationManager securityManager;

	/**
	 * Used to create a SecurityController.
	 * @param securityManager the authorization manager to use.
	 */
	@Autowired
	public SecurityController(AuthorizationManager securityManager) {
		this.securityManager = securityManager;
	}

	@RequestMapping(value = "/authorizationInfo", method = GET)
	@ResponseBody
	public AuthorizationInfo retrieveAuthorizationInfo(@Context HttpServletResponse response)
			throws ConfigurationException {
		response.addHeader("Expires", "-1");
		LOG.info("Retrieve authorization information.");
		if (!securityManager.isSecurityEnabled()) {
			LOG.info("Authentication is disabled.");
			return new AuthorizationInfo(true);
		}
		LOG.info("Authentication is enabled.");
		return securityManager.createAuthorizationInfoFromAuthentication();
	}

}
