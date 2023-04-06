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

import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.sql.SQLFeatureStoreTransaction;
import org.deegree.filter.IdFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static org.deegree.protocol.wfs.transaction.action.IDGenMode.USE_EXISTING;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class XPlanSynWfsAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanSynWfsAdapter.class);

	private final ManagerWorkspaceWrapper managerWorkspaceWrapper;

	public XPlanSynWfsAdapter(ManagerWorkspaceWrapper managerWorkspaceWrapper) {
		this.managerWorkspaceWrapper = managerWorkspaceWrapper;
	}

	public List<String> insert(FeatureCollection synFc, PlanStatus planStatus) throws Exception {
		try {
			LOG.info("Insert XPlan in XPlanSynWF");
			FeatureStore synFs = managerWorkspaceWrapper.lookupStore(XPLAN_SYN, planStatus);
			LOG.info("- Einfügen von " + synFc.size() + " Feature(s) in den FeatureStore (XPLAN_SYN)...");
			SQLFeatureStoreTransaction ta = (SQLFeatureStoreTransaction) synFs.acquireTransaction();
			List<String> fids = ta.performInsert(synFc, USE_EXISTING);

			ta.commit();
			return fids;
		}
		catch (Exception e) {
			throw new Exception("Fehler beim Einfügen: " + e.getMessage(), e);
		}
	}

	public void deletePlan(XPlanVersionAndPlanStatus xPlanMetadata, Set<String> ids, int planId) throws Exception {
		try {
			PlanStatus planStatus = xPlanMetadata.planStatus;

			FeatureStore fsSyn = managerWorkspaceWrapper.lookupStore(XPLAN_SYN, planStatus);
			SQLFeatureStoreTransaction taSyn = (SQLFeatureStoreTransaction) fsSyn.acquireTransaction();

			IdFilter idFilter = new IdFilter(ids);
			LOG.info("- Entferne XPlan " + planId + " aus dem FeatureStore (XPLAN_SYN)...");
			taSyn.performDelete(idFilter, null);
			LOG.info("OK");
			taSyn.commit();
		}
		catch (Exception e) {
			throw new Exception("Fehler beim Löschen des Plans: " + e.getMessage() + ".", e);
		}
	}

	public List<String> update(int planId, XPlan oldXPlan, AdditionalPlanData newXPlanMetadata, FeatureCollection synFc,
			Set<String> oldFids) throws Exception {
		SQLFeatureStoreTransaction taSynSource = null;
		SQLFeatureStoreTransaction taSynTarget = null;
		boolean sameSourceAndTarget = false;
		try {
			PlanStatus oldPlanStatus = oldXPlan.getXplanMetadata().getPlanStatus();
			PlanStatus newPlanStatus = newXPlanMetadata.getPlanStatus();

			FeatureStore synFsSource = managerWorkspaceWrapper.lookupStore(XPLAN_SYN, oldPlanStatus);
			sameSourceAndTarget = oldPlanStatus == newPlanStatus;

			taSynSource = (SQLFeatureStoreTransaction) synFsSource.acquireTransaction();
			if (sameSourceAndTarget) {
				taSynTarget = taSynSource;
			}
			else {
				FeatureStore synFsTarget = managerWorkspaceWrapper.lookupStore(XPLAN_SYN, newPlanStatus);
				taSynTarget = (SQLFeatureStoreTransaction) synFsTarget.acquireTransaction();
			}
			IdFilter idFilter = new IdFilter(oldFids);

			LOG.info("- Aktualisiere XPlan " + planId + " im FeatureStore (XPLAN_SYN)...");
			taSynSource.performDelete(idFilter, null);

			List<String> newFids = taSynTarget.performInsert(synFc, USE_EXISTING);
			taSynSource.commit();
			if (!sameSourceAndTarget) {
				taSynTarget.commit();
			}
			LOG.info("OK");
			return newFids;
		}
		catch (Exception e) {
			LOG.error("Fehler beim Aktualiseren der Features. Ein Rollback wird durchgeführt.", e);
			if (taSynSource != null)
				taSynSource.rollback();
			if (!sameSourceAndTarget) {
				if (taSynTarget != null)
					taSynTarget.rollback();
			}
			throw new Exception("Fehler beim Aktualiseren des Plans: " + e.getMessage() + ".", e);
		}
	}

}
