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

import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.ResourceMetadata;
import org.deegree.workspace.Workspace;

import java.net.URL;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class JpaConnectionProviderProvider extends ConnectionProviderProvider {

	static final String CONFIG_NAMESPACE = "http://www.latlon.de/xplan/connectionprovider/jpa";

	static final URL SCHEMA_URL = JpaConnectionProviderProvider.class
		.getResource("/META-INF/schemas/connectionprovider/jpa/datasource.xsd");

	@Override
	public String getNamespace() {
		return CONFIG_NAMESPACE;
	}

	@Override
	public ResourceMetadata<ConnectionProvider> createFromLocation(Workspace workspace,
			ResourceLocation<ConnectionProvider> location) {
		return new JpaConnectionProviderMetadata(workspace, location, this);
	}

	@Override
	public URL getSchema() {
		return SCHEMA_URL;
	}

}
