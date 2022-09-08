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

import de.latlon.xplan.planwerkwms.jaxb.Planwerk;
import org.deegree.services.OWS;
import org.deegree.services.OWSProvider;
import org.deegree.services.controller.OGCFrontController;
import org.deegree.workspace.ResourceIdentifier;
import org.deegree.workspace.Workspace;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
			int planId = Integer.parseInt(pathInfo.substring(1));
			Workspace workspace = OGCFrontController.getServiceWorkspace().getNewWorkspace();
			List<ResourceIdentifier<OWS>> resourcesOfType = workspace.getResourcesOfType(OWSProvider.class);
			for (ResourceIdentifier<OWS> resourceId : resourcesOfType) {
				deletePlanWerkWMSConfiguration(planId, workspace, resourceId);
			}
		}
	}

	private static void deletePlanWerkWMSConfiguration(int planId, Workspace workspace,
			ResourceIdentifier<OWS> resourceId) {
		OWS resource = workspace.getResource(OWSProvider.class, resourceId.getId());
		if (resource != null && resource instanceof PlanwerkController) {
			Planwerk planwerk = ((PlanwerkController) resource).getPlanwerk();
			List<Integer> managerIds = planwerk.getManagerId();
			if (managerIds.contains(planId))
				workspace.destroy(resource.getMetadata().getIdentifier());
		}
	}

}
