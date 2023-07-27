package de.latlon.xplanbox.api.dokumente.config;

import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Configuration
public class HsqlJpaContext {

	@Bean
	@Primary
	public ManagerWorkspaceWrapper managerWorkspaceWrapper() throws WorkspaceException {
		return mock(ManagerWorkspaceWrapper.class);
	}

	@Bean
	@Primary
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.addScript("/create-schema.sql").setType(EmbeddedDatabaseType.HSQL).ignoreFailedDrops(true)
				.build();
	}

	@Bean
	public HibernateJpaVendorAdapter jpaVendorAdapter(@Value("${hibernate.dialect}") String hibernateDialect) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		vendorAdapter.setDatabasePlatform(hibernateDialect);
		return vendorAdapter;
	}

}
