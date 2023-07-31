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
package de.latlon.xplan.job.validator.workspace;

import de.latlon.xplan.job.validator.exception.JobConfigException;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

import static org.deegree.commons.config.DeegreeWorkspace.getInstance;
import static org.deegree.commons.config.DeegreeWorkspace.isWorkspace;

/**
 * Encapsulates the xplan-validator-workspace.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidatorWorkspaceWrapper {

	private static final Logger LOG = LoggerFactory.getLogger(ValidatorWorkspaceWrapper.class);

	private static final String XPLAN_VALIDATOR_WORKSPACE = "xplan-validator-workspace";

	private static final String SQLFEATURESTORE_ID = "xplanvalidator";

	private static final String JDBC_POOL_ID = "xplan";

	private final DeegreeWorkspace validatorWorkspace;

	public ValidatorWorkspaceWrapper() throws JobConfigException {
		this.validatorWorkspace = initValidatorWorkspace();
	}

	private DeegreeWorkspace initValidatorWorkspace() throws JobConfigException {
		LOG.info("Get workspace instance '{}' from deegree workspace root '{}'", XPLAN_VALIDATOR_WORKSPACE,
				DeegreeWorkspace.getWorkspaceRoot());
		if (!isWorkspace(XPLAN_VALIDATOR_WORKSPACE)) {
			throw new JobConfigException("Workspace '" + XPLAN_VALIDATOR_WORKSPACE + "' does not exist.");
		}
		try {
			DeegreeWorkspace workspace = getInstance(XPLAN_VALIDATOR_WORKSPACE);
			workspace.initAll();
			return workspace;
		}
		catch (Exception e) {
			throw new JobConfigException(
					"Workspace '" + XPLAN_VALIDATOR_WORKSPACE + "' could not be initialised: " + e.getMessage(), e);
		}
	}

	/**
	 * Opens a connection to the 'xplan' jdbc resource in the workspace.
	 * @return opened connection
	 */
	public Connection openConnection() {
		ensureWorkspaceInitialized();
		ConnectionProvider resource = validatorWorkspace.getNewWorkspace()
			.getResource(ConnectionProviderProvider.class, JDBC_POOL_ID);
		return resource.getConnection();
	}

	/**
	 * Looks up the SQLFeatureStore in the xplan-validator-workspace.
	 * @return the SQLFeatureStore, never <code>null</code>
	 * @throws IllegalArgumentException if a FeatureStore can not be found
	 */
	public FeatureStore lookupFeatureStore() {
		ensureWorkspaceInitialized();
		FeatureStore sfs = validatorWorkspace.getNewWorkspace()
			.getResource(FeatureStoreProvider.class, SQLFEATURESTORE_ID);
		if (sfs == null) {
			throw new IllegalArgumentException("Feature Store '" + SQLFEATURESTORE_ID + "' is not available");
		}
		return sfs;
	}

	public DeegreeWorkspace getWorkspace() {
		return this.validatorWorkspace;
	}

	/**
	 * Ensures that the workspace is initialised.
	 */
	private void ensureWorkspaceInitialized() {
		try {
			validatorWorkspace.getNewWorkspace().getResource(ConnectionProviderProvider.class, JDBC_POOL_ID);
		}
		catch (Exception e) {
			long begin = System.currentTimeMillis();
			LOG.info("- Initialise workspace {} ...", XPLAN_VALIDATOR_WORKSPACE);
			validatorWorkspace.getNewWorkspace().initAll();
			long elapsed = System.currentTimeMillis() - begin;
			LOG.info("OK [" + elapsed + " ms]");
		}
	}

}
