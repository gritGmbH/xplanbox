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

import org.deegree.commons.xml.jaxb.JAXBUtils;
import org.deegree.services.OWS;
import de.latlon.xplan.planwerkwms.jaxb.Planwerk;
import org.deegree.workspace.ResourceBuilder;
import org.deegree.workspace.ResourceInitException;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.Workspace;
import org.deegree.workspace.standard.AbstractResourceMetadata;
import org.deegree.workspace.standard.AbstractResourceProvider;

/**
 * {@link org.deegree.workspace.ResourceMetadata} for a Planwerk
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkMetadata extends AbstractResourceMetadata<OWS> {

	protected static final String CONFIG_JAXB_PACKAGE = "de.latlon.xplan.planwerkwms.jaxb";

	public PlanwerkMetadata(Workspace workspace, ResourceLocation<OWS> location,
			AbstractResourceProvider<OWS> provider) {
		super(workspace, location, provider);
	}

	@Override
	public ResourceBuilder<OWS> prepare() {
		try {
			Planwerk planwerkConfig = (Planwerk) JAXBUtils.unmarshall(CONFIG_JAXB_PACKAGE, provider.getSchema(),
					location.getAsStream(), workspace);
			return new PlanwerkBuilder(workspace, this, planwerkConfig);
		}
		catch (Exception e) {
			throw new ResourceInitException(e.getLocalizedMessage(), e);
		}
	}

}
