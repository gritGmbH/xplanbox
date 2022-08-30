/*-
 * #%L
 * xplan-wms - deegree XPlan WebMapService
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.planwerkwms;

import org.deegree.services.OWS;
import org.deegree.services.OWSProvider;
import org.deegree.services.controller.OGCFrontController;
import org.deegree.workspace.Workspace;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Delete the passed XPlanWerkWMS configuration.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DeletePlanwerkServlet extends HttpServlet {

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
		String pathInfo = req.getPathInfo();
		if (pathInfo != null) {
			String serviceId = pathInfo.substring(1);
			Workspace workspace = OGCFrontController.getServiceWorkspace().getNewWorkspace();
			OWS resource = workspace.getResource(OWSProvider.class, serviceId);
			if (resource != null) {
				workspace.destroy(resource.getMetadata().getIdentifier());
			}
		}
	}

}
