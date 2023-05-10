/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.core.manager.db;

import org.deegree.db.ConnectionProvider;
import org.deegree.db.datasource.DataSourceConnectionProvider;
import org.deegree.sqldialect.SQLDialect;
import org.deegree.workspace.Resource;
import org.deegree.workspace.ResourceMetadata;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class SpringConnectionProvider implements ConnectionProvider {

	private final DataSourceConnectionProvider dataSourceConnectionProvider;

	private final JpaTransactionManager transactionManager;

	public SpringConnectionProvider(DataSourceConnectionProvider dataSourceConnectionProvider,
			JpaTransactionManager transactionManager) {
		this.dataSourceConnectionProvider = dataSourceConnectionProvider;
		this.transactionManager = transactionManager;
	}

	@Override
	public Connection getConnection() {
		DataSource dataSource = transactionManager.getDataSource();
		Object resource = TransactionSynchronizationManager.getResource(dataSource);
		return ((ConnectionHolder) resource).getConnection();
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
		return dataSourceConnectionProvider.getMetadata();
	}

	@Override
	public void init() {
		dataSourceConnectionProvider.init();
	}

	@Override
	public void destroy() {
		dataSourceConnectionProvider.destroy();
	}

}
