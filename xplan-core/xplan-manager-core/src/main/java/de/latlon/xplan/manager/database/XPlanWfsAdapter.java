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

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.sql.SQLFeatureStoreTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

}
