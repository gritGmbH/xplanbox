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
import org.deegree.db.datasource.DataSourceConnectionProvider;
import org.deegree.sqldialect.SQLDialect;
import org.deegree.workspace.Resource;
import org.deegree.workspace.ResourceMetadata;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class JpaConnectionProvider implements ConnectionProvider {

	private final DataSourceConnectionProvider dataSourceConnectionProvider;

	private final JpaConnectionProviderMetadata metadata;

	private JpaTransactionManager jpaTransactionManager;

	public JpaConnectionProvider(DataSourceConnectionProvider dataSourceConnectionProvider,
			JpaConnectionProviderMetadata metadata) {
		this.dataSourceConnectionProvider = dataSourceConnectionProvider;
		this.metadata = metadata;
	}

	@Override
	public Connection getConnection() {
		DataSource dataSource = jpaTransactionManager.getDataSource();
		return DataSourceUtils.getConnection(dataSource);
	}

	@Override
	public SQLDialect getDialect() {
		return dataSourceConnectionProvider.getDialect();
	}

	@Override
	public void invalidate(Connection connection) {

	}

	@Override
	public ResourceMetadata<? extends Resource> getMetadata() {
		return metadata;
	}

	@Override
	public void init() {
		dataSourceConnectionProvider.init();
	}

	@Override
	public void destroy() {
		dataSourceConnectionProvider.destroy();
	}

	public void setJpaTransactionManager(JpaTransactionManager jpaTransactionManager) {
		this.jpaTransactionManager = jpaTransactionManager;
	}

}
