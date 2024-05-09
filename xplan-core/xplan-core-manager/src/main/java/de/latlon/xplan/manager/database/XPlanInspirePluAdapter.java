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

import de.latlon.xplan.core.manager.db.listener.CleanupSqlFeatureStoreEvent;
import de.latlon.xplan.manager.inspireplu.InspirePluPublishException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.feature.persistence.sql.SQLFeatureStore;
import org.deegree.protocol.wfs.transaction.action.IDGenMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class XPlanInspirePluAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanInspirePluAdapter.class);

	private final ManagerWorkspaceWrapper managerWorkspaceWrapper;

	private final ApplicationEventPublisher applicationEventPublisher;

	public XPlanInspirePluAdapter(ManagerWorkspaceWrapper managerWorkspaceWrapper,
			ApplicationEventPublisher applicationEventPublisher) {
		this.managerWorkspaceWrapper = managerWorkspaceWrapper;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public void insertInspirePlu(FeatureCollection featureCollection) throws InspirePluPublishException {

		try {
			LOG.info("Insert INSPIRE PLU dataset");
			SQLFeatureStore inspirePluStore = (SQLFeatureStore) managerWorkspaceWrapper.lookupInspirePluStore();
			applicationEventPublisher.publishEvent(new CleanupSqlFeatureStoreEvent(inspirePluStore));
			FeatureStoreTransaction transaction = inspirePluStore.acquireTransaction();

			transaction.performInsert(featureCollection, IDGenMode.GENERATE_NEW);
		}
		catch (Exception e) {
			throw new InspirePluPublishException("Fehler beim Einfügen des INSPIRE PLU Datensatz: " + e.getMessage(),
					e);
		}
	}

}
