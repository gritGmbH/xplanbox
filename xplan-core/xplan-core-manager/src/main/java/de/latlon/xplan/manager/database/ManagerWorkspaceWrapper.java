/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.database;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.core.manager.db.DatasourceWrapper;
import de.latlon.xplan.core.manager.deegree.jpa.JpaConnectionProvider;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.db.datasource.DataSourceConnectionProvider;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreProvider;
import org.deegree.feature.persistence.sql.SQLFeatureStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;

import static de.latlon.xplan.manager.web.shared.PlanStatus.ARCHIVIERT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ManagerWorkspaceWrapper implements DatasourceWrapper {

	private static final Logger LOG = LoggerFactory.getLogger(ManagerWorkspaceWrapper.class);

	private static final String JPA_JDBC_ID_XPLAN = "xplan";

	private static final String JDBC_ID_XPLAN = "xplancp";

	private static final String JPA_JDBC_ID_INSPIREPLU = "inspireplu";

	private static final String INSPIREPLU_FS_ID = "inspireplu";

	private static final String PRE_SUFFIX = "pre";

	private static final String ARCHIVE_SUFFIX = "archive";

	private DeegreeWorkspace managerWorkspace;

	public ManagerWorkspaceWrapper(DeegreeWorkspace managerWorkspace) {
		this.managerWorkspace = managerWorkspace;
	}

	@Override
	public DataSource retrieveDataSource() {
		ensureWorkspaceInitialized();
		ConnectionProvider resource = managerWorkspace.getNewWorkspace()
			.getResource(ConnectionProviderProvider.class, JDBC_ID_XPLAN);
		if (!(resource instanceof DataSourceConnectionProvider))
			throw new IllegalArgumentException(
					"Datasource configuration is not supported, must be an deegree DataSourceConnection");
		DataSourceConnectionProvider dataSourceConnectionProvider = (DataSourceConnectionProvider) resource;
		return dataSourceConnectionProvider.getDataSource();
	}

	@Override
	public void setJpaTransactionManager(JpaTransactionManager jpaTransactionManager) {
		ensureWorkspaceInitialized();
		setJpaTransactionManager(jpaTransactionManager, JPA_JDBC_ID_XPLAN);
		setJpaTransactionManager(jpaTransactionManager, JPA_JDBC_ID_INSPIREPLU);
	}

	/**
	 * Ensures that the workspace is initialised.
	 */
	public void ensureWorkspaceInitialized() {
		try {
			managerWorkspace.getNewWorkspace().getResource(ConnectionProviderProvider.class, JDBC_ID_XPLAN);
		}
		catch (Exception e) {
			long begin = System.currentTimeMillis();
			LOG.info("- Initialisiere Feature Stores...");
			managerWorkspace.getNewWorkspace().initAll();
			long elapsed = System.currentTimeMillis() - begin;
			LOG.info("OK [" + elapsed + " ms]");
		}
	}

	/**
	 * Opens a connection to the 'xplan' jdbc resource in the workspace.
	 * @return opened connection
	 */
	public Connection openConnection() {
		ensureWorkspaceInitialized();
		ConnectionProvider resource = managerWorkspace.getNewWorkspace()
			.getResource(ConnectionProviderProvider.class, JDBC_ID_XPLAN);
		return resource.getConnection();
	}

	/**
	 * Looks up a FeatureStore by the version and type.
	 * @param version XPlan version, never <code>null</code>
	 * @param planStatus xplan status, if <code>null</code>, default store is chosen (FIX)
	 * @return the FeatureStore fitting to version and ade, never <code>null</code>
	 * @throws IllegalArgumentException if a FeatureStore can not be found
	 */
	public FeatureStore lookupStore(XPlanVersion version, PlanStatus planStatus) {
		String store = version.name().replace("_", "").toLowerCase();
		if (IN_AUFSTELLUNG.equals(planStatus))
			return lookupStore(store + PRE_SUFFIX);
		else if (ARCHIVIERT.equals(planStatus))
			return lookupStore(store + ARCHIVE_SUFFIX);
		return lookupStore(store);
	}

	/**
	 * @return the INSPIRE PLU FeatureStore, never <code>null</code>
	 */
	public FeatureStore lookupInspirePluStore() {
		return lookupStore(INSPIREPLU_FS_ID);
	}

	private FeatureStore lookupStore(String id) {
		ensureWorkspaceInitialized();
		FeatureStore sfs = managerWorkspace.getNewWorkspace().getResource(FeatureStoreProvider.class, id);
		if (sfs == null) {
			LOG.debug("Feature Store '" + id + "' is not available");
			throw new IllegalArgumentException("Wrong FeatureStore Id " + id);
		}
		if (sfs instanceof SQLFeatureStore)
			((SQLFeatureStore) sfs).releaseConnectionFromExternal(true);
		return sfs;
	}

	private void setJpaTransactionManager(JpaTransactionManager jpaTransactionManager, String jpaJdbcId) {
		ConnectionProvider resource = managerWorkspace.getNewWorkspace()
			.getResource(ConnectionProviderProvider.class, jpaJdbcId);
		if (resource instanceof JpaConnectionProvider)
			((JpaConnectionProvider) resource).setJpaTransactionManager(jpaTransactionManager);
	}

	public DeegreeWorkspace getWorkspace() {
		return this.managerWorkspace;
	}

}
