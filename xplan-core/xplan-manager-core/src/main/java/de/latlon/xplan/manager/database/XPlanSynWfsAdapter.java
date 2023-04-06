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

import de.latlon.xplan.manager.web.shared.PlanStatus;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.sql.SQLFeatureStoreTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

	// addAdditionalProperties(synFc, beginValidity, endValidity, synFs, planId,
	// sortDate);
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

}
