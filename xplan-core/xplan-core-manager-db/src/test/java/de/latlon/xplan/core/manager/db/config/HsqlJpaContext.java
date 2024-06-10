/*-
 * #%L
 * xplan-core-manager-db - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplan.core.manager.db.config;

import de.latlon.xplan.core.manager.db.DatasourceWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Configuration
@PropertySource("classpath:/jpa-hsql.properties")
@Profile("test-hsql")
public class HsqlJpaContext {

	@Bean
	public DatasourceWrapper datasourceWrapper(DataSource dataSource) throws SQLException {
		DatasourceWrapper datasourceWrapper = mock(DatasourceWrapper.class);
		when(datasourceWrapper.retrieveDataSource()).thenReturn(dataSource);
		return datasourceWrapper;
	}

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.addScript("/create-schema.sql")
			.setType(EmbeddedDatabaseType.HSQL)
			.ignoreFailedDrops(true)
			.build();
	}

	@Bean
	public HibernateJpaVendorAdapter jpaVendorAdapter(@Value("${hibernate.dialect}") String hibernateDialect) {
		System.out.println("---");
		System.out.println(getClass().getClassLoader()
			.getResource("org/springframework/orm/jpa/vendor/HibernateJpaVendorAdapter.class"));
		System.out.println(getClass().getClassLoader()
			.getResource("org/springframework/orm/jpa/vendor/HibernateJpaDialect.class"));

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		vendorAdapter.setDatabasePlatform(hibernateDialect);
		return vendorAdapter;
	}

}
