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
package de.latlon.xplan.manager.database;

import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreException;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.protocol.wfs.transaction.action.IDGenMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class XPlanInspirePluAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanInspirePluAdapter.class);

	private final ManagerWorkspaceWrapper managerWorkspaceWrapper;

	public XPlanInspirePluAdapter(ManagerWorkspaceWrapper managerWorkspaceWrapper) {
		this.managerWorkspaceWrapper = managerWorkspaceWrapper;
	}

	public void insertInspirePlu(FeatureCollection featureCollection) throws Exception {
		FeatureStoreTransaction transaction = null;
		try {
			LOG.info("Insert INSPIRE PLU dataset");
			FeatureStore inspirePluStore = managerWorkspaceWrapper.lookupInspirePluStore();
			transaction = inspirePluStore.acquireTransaction();

			transaction.performInsert(featureCollection, IDGenMode.GENERATE_NEW);
			transaction.commit();
		}
		catch (FeatureStoreException e) {
			rollbackTransaction(transaction, e);
			throw new Exception("Fehler beim Einfügen des INSPIRE PLU Datensatz: " + e.getMessage(), e);
		}
	}

	private void rollbackTransaction(FeatureStoreTransaction transaction, FeatureStoreException e) {
		if (transaction != null)
			try {
				transaction.rollback();
			}
			catch (FeatureStoreException fse) {
				LOG.warn("Rollback failed: " + e.getMessage());
				LOG.trace("Rollback failed.", e);

			}
	}

}
