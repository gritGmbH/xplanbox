/*-
 * #%L
 * xplan-core-manager-deegree
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
package de.latlon.xplan.core.manager.deegree.jpa;

import de.latlon.xplan.connectionprovider.jpa.jaxb.JpaConnectionProvider;
import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.workspace.ResourceBuilder;
import org.deegree.workspace.ResourceInitException;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.Workspace;
import org.deegree.workspace.standard.AbstractResourceMetadata;
import org.deegree.workspace.standard.DefaultResourceIdentifier;
import org.slf4j.Logger;

import static de.latlon.xplan.core.manager.deegree.jpa.JpaConnectionProviderProvider.SCHEMA_URL;
import static org.deegree.commons.xml.jaxb.JAXBUtils.unmarshall;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
class JpaConnectionProviderMetadata extends AbstractResourceMetadata<ConnectionProvider> {

	private static final Logger LOG = getLogger(JpaConnectionProviderMetadata.class);

	private static final String JAXB_PACKAGE = "de.latlon.xplan.connectionprovider.jpa.jaxb";

	JpaConnectionProviderMetadata(final Workspace workspace, final ResourceLocation<ConnectionProvider> location,
			final ConnectionProviderProvider provider) {
		super(workspace, location, provider);
	}

	@Override
	public ResourceBuilder<ConnectionProvider> prepare() {
		try {
			JpaConnectionProvider cfg = (JpaConnectionProvider) unmarshall(JAXB_PACKAGE, SCHEMA_URL,
					location.getAsStream(), workspace);
			String connid = cfg.getDataSourceConnection();
			dependencies
				.add(new DefaultResourceIdentifier<ConnectionProvider>(ConnectionProviderProvider.class, connid));
			return new JpaConnectionProviderBuilder(cfg, this, workspace);
		}
		catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
			throw new ResourceInitException(e.getLocalizedMessage(), e);
		}
	}

}
