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

import org.deegree.commons.tom.ows.Version;
import org.deegree.protocol.wms.WMSConstants;
import org.deegree.services.OWS;
import org.deegree.services.OWSProvider;
import org.deegree.services.controller.ImplementationMetadata;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.ResourceMetadata;
import org.deegree.workspace.Workspace;

import java.net.URL;

import static org.deegree.protocol.wms.WMSConstants.VERSION_111;
import static org.deegree.protocol.wms.WMSConstants.VERSION_130;

/**
 * The {@link OWSProvider} of the PlanwerkWMS
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkProvider extends OWSProvider {

	public static final String NAMESPACE = "http://www.lat-lon.de/services/planwerk";

	protected static final ImplementationMetadata<WMSConstants.WMSRequestType> IMPLEMENTATION_METADATA = new ImplementationMetadata<WMSConstants.WMSRequestType>() {
		{
			supportedVersions = new Version[] { VERSION_111, VERSION_130 };
			handledNamespaces = new String[] { "" }; // WMS uses null namespace for SLD
														// GetMap Post requests
			handledRequests = WMSConstants.WMSRequestType.class;
			serviceName = new String[] { "WMS" };
		}
	};

	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

	@Override
	public URL getSchema() {
		return PlanwerkProvider.class
			.getResource("/META-INF/schemas/services/planwerkwms/1.0/planwerk_configuration.xsd");
	}

	@Override
	public ImplementationMetadata<WMSConstants.WMSRequestType> getImplementationMetadata() {
		return IMPLEMENTATION_METADATA;
	}

	@Override
	public ResourceMetadata<OWS> createFromLocation(Workspace workspace, ResourceLocation<OWS> location) {
		return new PlanwerkMetadata(workspace, location, this);
	}

}
