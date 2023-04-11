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

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.sql.SQLFeatureStoreTransaction;
import org.deegree.filter.IdFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

import static org.deegree.protocol.wfs.transaction.action.IDGenMode.USE_EXISTING;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class XPlanWfsAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanWfsAdapter.class);

	private final ManagerWorkspaceWrapper managerWorkspaceWrapper;

	public XPlanWfsAdapter(ManagerWorkspaceWrapper managerWorkspaceWrapper) {
		this.managerWorkspaceWrapper = managerWorkspaceWrapper;
	}

	public List<String> insert(XPlanFeatureCollection fc, PlanStatus planStatus) throws Exception {
		try {
			LOG.info("Insert XPlan in XplanWFS/XPlanWMS");
			FeatureStore xplanFs = managerWorkspaceWrapper.lookupStore(fc.getVersion(), planStatus);
			LOG.info("- Einfügen von " + fc.getFeatures().size() + " Feature(s) in den FeatureStore (" + fc.getVersion()
					+ ")...");
			SQLFeatureStoreTransaction ta = (SQLFeatureStoreTransaction) xplanFs.acquireTransaction();
			List<String> fids = ta.performInsert(fc.getFeatures(), USE_EXISTING);
			ta.commit();
			return fids;
		}
		catch (Exception e) {
			throw new Exception("Fehler beim Einfügen: " + e.getMessage(), e);
		}
	}

	public void deletePlan(XPlanVersionAndPlanStatus xPlanMetadata, Set<String> ids, int planId) throws Exception {
		try {
			XPlanVersion version = xPlanMetadata.version;
			PlanStatus planStatus = xPlanMetadata.planStatus;

			FeatureStore fs = managerWorkspaceWrapper.lookupStore(version, planStatus);
			SQLFeatureStoreTransaction ta = (SQLFeatureStoreTransaction) fs.acquireTransaction();

			IdFilter idFilter = new IdFilter(ids);
			LOG.info("- Entferne XPlan " + planId + " aus dem FeatureStore (" + version + ")...");
			ta.performDelete(idFilter, null);
			ta.commit();
			LOG.info("OK");
		}
		catch (Exception e) {
			throw new Exception("Fehler beim Löschen des Plans: " + e.getMessage() + ".", e);
		}
	}

	public List<String> update(int planId, XPlan oldXPlan, AdditionalPlanData newXPlanMetadata,
			XPlanFeatureCollection fc, Set<String> oldFids) throws Exception {
		PlanStatus oldPlanStatus = oldXPlan.getXplanMetadata().getPlanStatus();
		PlanStatus newPlanStatus = newXPlanMetadata.getPlanStatus();
		boolean sameSourceAndTarget = oldPlanStatus == newPlanStatus;
		if (sameSourceAndTarget) {
			return update(planId, oldPlanStatus, fc, oldFids);
		}
		else {
			return update(planId, fc, oldFids, oldPlanStatus, newPlanStatus);
		}
	}

	public List<String> update(int planId, PlanStatus planStatus, XPlanFeatureCollection fc, Set<String> oldFids)
			throws Exception {
		SQLFeatureStoreTransaction ta = null;
		try {
			XPlanVersion version = fc.getVersion();
			FeatureStore fs = managerWorkspaceWrapper.lookupStore(version, planStatus);

			ta = (SQLFeatureStoreTransaction) fs.acquireTransaction();

			IdFilter idFilter = new IdFilter(oldFids);
			LOG.info("- Aktualisiere XPlan " + planId + " im FeatureStore (" + version + ")...");
			ta.performDelete(idFilter, null);
			List<String> newFids = ta.performInsert(fc.getFeatures(), USE_EXISTING);

			ta.commit();
			LOG.info("OK");
			return newFids;
		}
		catch (Exception e) {
			LOG.error("Fehler beim Aktualiseren der Features. Ein Rollback wird durchgeführt.", e);
			if (ta != null)
				ta.rollback();
			throw new Exception("Fehler beim Aktualiseren des Plans: " + e.getMessage() + ".", e);
		}
	}

	private List<String> update(int planId, XPlanFeatureCollection fc, Set<String> oldFids, PlanStatus oldPlanStatus,
			PlanStatus newPlanStatus) throws Exception {
		SQLFeatureStoreTransaction taSource = null;
		SQLFeatureStoreTransaction taTarget = null;
		try {
			XPlanVersion version = fc.getVersion();

			FeatureStore fsSource = managerWorkspaceWrapper.lookupStore(version, oldPlanStatus);
			taSource = (SQLFeatureStoreTransaction) fsSource.acquireTransaction();
			FeatureStore fsTarget = managerWorkspaceWrapper.lookupStore(version, newPlanStatus);
			taTarget = (SQLFeatureStoreTransaction) fsTarget.acquireTransaction();

			IdFilter idFilter = new IdFilter(oldFids);
			LOG.info("- Aktualisiere XPlan " + planId + " im FeatureStore (" + version + ")...");
			taSource.performDelete(idFilter, null);
			List<String> newFids = taTarget.performInsert(fc.getFeatures(), USE_EXISTING);

			taSource.commit();
			taTarget.commit();
			LOG.info("OK");
			return newFids;
		}
		catch (Exception e) {
			LOG.error("Fehler beim Aktualiseren der Features. Ein Rollback wird durchgeführt.", e);
			if (taSource != null)
				taSource.rollback();
			if (taTarget != null)
				taTarget.rollback();
			throw new Exception("Fehler beim Aktualiseren des Plans: " + e.getMessage() + ".", e);
		}
	}

}
