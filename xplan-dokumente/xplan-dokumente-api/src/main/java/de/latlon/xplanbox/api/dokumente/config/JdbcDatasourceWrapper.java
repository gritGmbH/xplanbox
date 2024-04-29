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