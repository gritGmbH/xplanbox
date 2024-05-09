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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Simple workaround for circular dependencies in Spring beans.
 */
@Configuration
public class ApplicationContextPart1 {

	@Bean
	public DatasourceWrapper datasourceWrapper(@Value("#{environment.XPLAN_JDBC_URL}") String jdbcUrl,
			@Value("#{environment.XPLAN_DB_USER}") String username,
			@Value("#{environment.XPLAN_DB_PASSWORD}") String password) {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.postgresql.Driver");
		dataSourceBuilder.url(jdbcUrl);
		dataSourceBuilder.username(username);
		dataSourceBuilder.password(password);
		DataSource dataSource = dataSourceBuilder.build();
		return new JdbcDatasourceWrapper(dataSource);
	}

}
