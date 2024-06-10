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
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.core.manager.db.listener.CleanupSqlFeatureStoreEvent;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.query.Query;
import org.deegree.feature.persistence.sql.SQLFeatureStore;
import org.deegree.feature.persistence.sql.SQLFeatureStoreTransaction;
import org.deegree.filter.IdFilter;
import org.deegree.protocol.wfs.getfeature.TypeName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Set;

import static org.deegree.protocol.wfs.transaction.action.IDGenMode.USE_EXISTING;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class XPlanWfsAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanWfsAdapter.class);

	private final ManagerWorkspaceWrapper managerWorkspaceWrapper;

	private final ApplicationEventPublisher applicationEventPublisher;

	public XPlanWfsAdapter(ManagerWorkspaceWrapper managerWorkspaceWrapper,
			ApplicationEventPublisher applicationEventPublisher) {
		this.managerWorkspaceWrapper = managerWorkspaceWrapper;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public List<String> insert(XPlanFeatureCollection fc, PlanStatus planStatus) throws Exception {
		LOG.info("Insert XPlan in XplanWFS/XPlanWMS");
		SQLFeatureStore xplanFs = lookupStore(fc.getVersion(), planStatus);
		LOG.info("- Einfügen von " + fc.getFeatures().size() + " Feature(s) in den FeatureStore (" + fc.getVersion()
				+ ")...");
		SQLFeatureStoreTransaction ta = (SQLFeatureStoreTransaction) xplanFs.acquireTransaction();
		return ta.performInsert(fc.getFeatures(), USE_EXISTING);
	}

	public void deletePlan(int planId, XPlanVersion version, PlanStatus planStatus, Set<String> ids) throws Exception {
		SQLFeatureStore xplanFs = lookupStore(version, planStatus);
		SQLFeatureStoreTransaction ta = (SQLFeatureStoreTransaction) xplanFs.acquireTransaction();

		IdFilter idFilter = new IdFilter(ids);
		LOG.info("- Entferne XPlan " + planId + " aus dem FeatureStore (" + version + ")...");
		ta.performDelete(idFilter, null);
		LOG.info("OK");
	}

	public FeatureCollection restoreFeatureCollection(XPlanVersion version, PlanStatus planStatus, Set<String> ids)
			throws Exception {
		SQLFeatureStore xplanFs = lookupStore(version, planStatus);
		IdFilter filter = new IdFilter(ids);
		Query query = new Query(new TypeName[0], filter, null, null, null);
		return xplanFs.query(query).toCollection();
	}

	public List<String> update(int planId, XPlan oldXPlan, PlanStatus targetPlanStatus, XPlanFeatureCollection fc,
			Set<String> oldFids) throws Exception {
		PlanStatus oldPlanStatus = oldXPlan.getPlanStatus();
		boolean sameSourceAndTarget = oldPlanStatus == targetPlanStatus;
		if (sameSourceAndTarget) {
			return update(planId, oldPlanStatus, fc, oldFids);
		}
		else {
			return update(planId, fc, oldFids, oldPlanStatus, targetPlanStatus);
		}
	}

	public List<String> update(int planId, PlanStatus planStatus, XPlanFeatureCollection fc, Set<String> oldFids)
			throws Exception {
		XPlanVersion version = fc.getVersion();
		SQLFeatureStore xplanFs = lookupStore(version, planStatus);
		SQLFeatureStoreTransaction ta = (SQLFeatureStoreTransaction) xplanFs.acquireTransaction();

		IdFilter idFilter = new IdFilter(oldFids);
		LOG.info("- Aktualisiere XPlan " + planId + " im FeatureStore (" + version + ")...");
		ta.performDelete(idFilter, null);
		List<String> newFids = ta.performInsert(fc.getFeatures(), USE_EXISTING);

		LOG.info("OK");
		return newFids;
	}

	private List<String> update(int planId, XPlanFeatureCollection fc, Set<String> oldFids, PlanStatus oldPlanStatus,
			PlanStatus newPlanStatus) throws Exception {
		XPlanVersion version = fc.getVersion();

		SQLFeatureStore fsSource = lookupStore(version, oldPlanStatus);
		SQLFeatureStoreTransaction taSource = (SQLFeatureStoreTransaction) fsSource.acquireTransaction();
		SQLFeatureStore fsTarget = lookupStore(version, newPlanStatus);
		SQLFeatureStoreTransaction taTarget = (SQLFeatureStoreTransaction) fsTarget.acquireTransaction();

		IdFilter idFilter = new IdFilter(oldFids);
		LOG.info("- Aktualisiere XPlan " + planId + " im FeatureStore (" + version + ")...");
		taSource.performDelete(idFilter, null);
		List<String> newFids = taTarget.performInsert(fc.getFeatures(), USE_EXISTING);

		LOG.info("OK");
		return newFids;
	}

	private SQLFeatureStore lookupStore(XPlanVersion version, PlanStatus planStatus) {
		SQLFeatureStore xplanFs = (SQLFeatureStore) managerWorkspaceWrapper.lookupStore(version, planStatus);
		applicationEventPublisher.publishEvent(new CleanupSqlFeatureStoreEvent(xplanFs));
		return xplanFs;
	}

}
