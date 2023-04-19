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
package de.latlon.xplan.core.manager.db.config;

import de.latlon.xplan.core.manager.db.repository.PlanRepository;
import de.latlon.xplan.core.manager.db.repository.PlanwerkWmsMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
@Configuration
@EnableJpaRepositories(basePackages = "de.latlon.xplan.core.manager.db.repository",
		includeFilters = @ComponentScan.Filter(value = { PlanRepository.class, PlanwerkWmsMetadataRepository.class },
				type = FilterType.ASSIGNABLE_TYPE))
@PropertySource("classpath:/jpa.properties")
@EnableTransactionManagement
public class JpaContext {

	@Autowired
	private Optional<DatasourceWrapper> datasourceWrapper;

	@Bean
	public DataSource dataSource() throws SQLException {
		if (datasourceWrapper.isPresent())
			return datasourceWrapper.get().retrieveDataSource();
		return null;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory(HibernateJpaVendorAdapter vendorAdapter, DataSource dataSource)
			throws SQLException {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("de.latlon.xplan.core.manager.db.model");
		factory.setDataSource(dataSource);
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	public HibernateJpaVendorAdapter getJpaVendorAdapter(@Value("${hibernate.dialect}") String hibernateDialect) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(true);
		vendorAdapter.setDatabasePlatform(hibernateDialect);
		return vendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
			throws SQLException {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}

}
