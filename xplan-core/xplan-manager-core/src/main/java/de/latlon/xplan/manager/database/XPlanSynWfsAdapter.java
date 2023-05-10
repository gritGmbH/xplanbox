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
		LOG.info("Insert XPlan in XPlanSynWF");
		FeatureStore synFs = managerWorkspaceWrapper.lookupStore(XPLAN_SYN, planStatus);
		LOG.info("- Einfügen von " + synFc.size() + " Feature(s) in den FeatureStore (XPLAN_SYN)...");
		SQLFeatureStoreTransaction ta = (SQLFeatureStoreTransaction) synFs.acquireTransaction();
		return ta.performInsert(synFc, USE_EXISTING);
	}

	public void deletePlan(XPlanVersionAndPlanStatus xPlanMetadata, Set<String> ids, int planId) throws Exception {
		PlanStatus planStatus = xPlanMetadata.planStatus;

		FeatureStore fsSyn = managerWorkspaceWrapper.lookupStore(XPLAN_SYN, planStatus);
		SQLFeatureStoreTransaction taSyn = (SQLFeatureStoreTransaction) fsSyn.acquireTransaction();

		IdFilter idFilter = new IdFilter(ids);
		LOG.info("- Entferne XPlan " + planId + " aus dem FeatureStore (XPLAN_SYN)...");
		taSyn.performDelete(idFilter, null);
		LOG.info("OK");
	}

	public List<String> update(int planId, XPlan oldXPlan, AdditionalPlanData newXPlanMetadata, FeatureCollection synFc,
			Set<String> oldFids) throws Exception {
		PlanStatus oldPlanStatus = oldXPlan.getXplanMetadata().getPlanStatus();
		PlanStatus newPlanStatus = newXPlanMetadata.getPlanStatus();
		boolean sameSourceAndTarget = oldPlanStatus == newPlanStatus;
		if (sameSourceAndTarget) {
			return update(planId, oldPlanStatus, synFc, oldFids);
		}
		else {
			return update(planId, synFc, oldFids, oldPlanStatus, newPlanStatus);
		}
	}

	public List<String> update(int planId, PlanStatus planStatus, FeatureCollection synFc, Set<String> oldFids)
			throws Exception {
		FeatureStore synFs = managerWorkspaceWrapper.lookupStore(XPLAN_SYN, planStatus);
		SQLFeatureStoreTransaction taSyn = (SQLFeatureStoreTransaction) synFs.acquireTransaction();
		IdFilter idFilter = new IdFilter(oldFids);

		LOG.info("- Aktualisiere XPlan " + planId + " im FeatureStore (XPLAN_SYN)...");
		taSyn.performDelete(idFilter, null);
		List<String> newFids = taSyn.performInsert(synFc, USE_EXISTING);
		LOG.info("OK");
		return newFids;
	}

	private List<String> update(int planId, FeatureCollection synFc, Set<String> oldFids, PlanStatus oldPlanStatus,
			PlanStatus newPlanStatus) throws Exception {
		SQLFeatureStoreTransaction taSynSource = null;
		SQLFeatureStoreTransaction taSynTarget = null;
		FeatureStore synFsSource = managerWorkspaceWrapper.lookupStore(XPLAN_SYN, oldPlanStatus);
		taSynSource = (SQLFeatureStoreTransaction) synFsSource.acquireTransaction();
		FeatureStore synFsTarget = managerWorkspaceWrapper.lookupStore(XPLAN_SYN, newPlanStatus);
		taSynTarget = (SQLFeatureStoreTransaction) synFsTarget.acquireTransaction();
		IdFilter idFilter = new IdFilter(oldFids);

		LOG.info("- Aktualisiere XPlan " + planId + " im FeatureStore (XPLAN_SYN)...");
		taSynSource.performDelete(idFilter, null);
		List<String> newFids = taSynTarget.performInsert(synFc, USE_EXISTING);
		LOG.info("OK");
		return newFids;
	}

}
