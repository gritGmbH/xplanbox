/*-
 * #%L
 * xplan-dokumente-api - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.api.dokumente.config;

import de.latlon.xplan.core.manager.db.DatasourceWrapper;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.2
 */
public class JdbcDatasourceWrapper implements DatasourceWrapper {

	private final DataSource dataSource;

	public JdbcDatasourceWrapper(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public DataSource retrieveDataSource() throws SQLException {
		return dataSource;
	}

	@Override
	public void setJpaTransactionManager(JpaTransactionManager jpaTransactionManager) {
		// nothing to do here
	}

}
