/*-
 * #%L
 * xplan-wms - deegree XPlan WebMapService
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
package de.latlon.xplan.planwerkwms;

import org.deegree.services.OWS;
import org.deegree.services.OWSProvider;
import de.latlon.xplan.planwerkwms.jaxb.Planwerk;
import org.deegree.services.wms.controller.WMSController;
import org.deegree.services.wms.controller.WmsMetadata;
import org.deegree.workspace.ResourceBuilder;
import org.deegree.workspace.Workspace;

/**
 * Instantiates a {@link WMSController} for the Planwerk
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkBuilder implements ResourceBuilder<OWS> {

	private final Workspace workspace;

	private final PlanwerkMetadata planwerkMetadata;

	private final Planwerk planwerk;

	public PlanwerkBuilder(Workspace workspace, PlanwerkMetadata planwerkMetadata, Planwerk planwerk) {
		this.workspace = workspace;
		this.planwerkMetadata = planwerkMetadata;
		this.planwerk = planwerk;
	}

	@Override
	public OWS build() {
		String planwerkId = planwerk.getPlanwerkWms();
		WmsMetadata wmsController = (WmsMetadata) workspace.getResourceMetadata(OWSProvider.class, planwerkId);
		return new PlanwerkController(planwerkMetadata, workspace, wmsController.getCfg(), planwerk);
	}

}
