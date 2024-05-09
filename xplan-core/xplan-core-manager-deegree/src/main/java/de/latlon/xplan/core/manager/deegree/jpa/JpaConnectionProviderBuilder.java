/*-
 * #%L
 * xplan-core-manager-deegree - Modul zur Gruppierung der Kernmodule
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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

import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.db.datasource.DataSourceConnectionProvider;
import org.deegree.workspace.ResourceBuilder;
import org.deegree.workspace.ResourceInitException;
import org.deegree.workspace.Workspace;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
class JpaConnectionProviderBuilder implements ResourceBuilder<ConnectionProvider> {

	private static final Logger LOG = getLogger(JpaConnectionProviderBuilder.class);

	private final de.latlon.xplan.connectionprovider.jpa.jaxb.JpaConnectionProvider config;

	private final JpaConnectionProviderMetadata metadata;

	private final Workspace workspace;

	JpaConnectionProviderBuilder(de.latlon.xplan.connectionprovider.jpa.jaxb.JpaConnectionProvider config,
			final JpaConnectionProviderMetadata metadata, final Workspace workspace) {
		this.config = config;
		this.metadata = metadata;
		this.workspace = workspace;
	}

	@Override
	public ConnectionProvider build() {
		ConnectionProvider conn = workspace.getResource(ConnectionProviderProvider.class,
				config.getDataSourceConnection());
		checkConnection(conn);

		return new JpaConnectionProvider((DataSourceConnectionProvider) conn, metadata);
	}

	private void checkConnection(ConnectionProvider conn) {
		if (conn == null) {
			String msg = "Unable to create JpaConnectionProvider: Connection with identifier "
					+ config.getDataSourceConnection() + " is not available.";
			LOG.error(msg);
			throw new ResourceInitException(msg);
		}
		if (!(conn instanceof DataSourceConnectionProvider)) {
			String msg = "Unable to create JpaConnectionProvider: Connection with identifier "
					+ config.getDataSourceConnection() + " is not a DataSourceConnection.";
			LOG.error(msg);
			throw new ResourceInitException(msg);
		}
	}

}
