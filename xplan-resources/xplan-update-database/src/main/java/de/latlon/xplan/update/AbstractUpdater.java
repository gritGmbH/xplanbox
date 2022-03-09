/*-
 * #%L
 * xplan-update-database - update of database
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.update;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.manager.database.XPlanDao;

/**
 * Abstract super class for all update tools from one version to another.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public abstract class AbstractUpdater {

	private final Logger LOG = LoggerFactory.getLogger(AbstractUpdater.class);

	protected final XPlanDao xplanDao;

	/**
	 * @param xplanDao allows access to the database, never <code>null</code>
	 */
	public AbstractUpdater(XPlanDao xplanDao) {
		this.xplanDao = xplanDao;
	}

	/**
	 * @param conn open database connection, is not closed in this methode, never
	 * <code>null</code>
	 * @throws Exception if an exception occurred during update
	 */
	public abstract void update(Connection conn) throws Exception;

	/**
	 * @param conn open database connection, to rollback, never <code>null</code>
	 */
	protected void rollback(Connection conn) {
		try {
			conn.rollback();
		}
		catch (SQLException e1) {
			LOG.warn("Rollback failed!", e1);
		}
	}

}
