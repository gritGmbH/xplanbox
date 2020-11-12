/*-
 * #%L
 * xplan-update-database - update of database
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.update;

import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.update.from_1_0_to_1_3_1.UpdaterFrom1_0To1_3_1;
import de.latlon.xplan.update.from_pre1_0_to_1_0.UpdaterFromPre1_0To1_0;

import java.sql.Connection;
import java.util.List;

import static de.latlon.xplan.manager.database.DatabaseUtils.closeQuietly;
import static de.latlon.xplan.update.DatabaseDataUpdater.UPDATE_VERSION.FROM_1_0_to_1_3_1;
import static de.latlon.xplan.update.DatabaseDataUpdater.UPDATE_VERSION.FROM_PRE1_0_to_1_0;

/**
 * Updates the data in the tables xplanmgr.plans and xplan3.gml_object. Schema must be up to date already.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class DatabaseDataUpdater {

    public enum UPDATE_VERSION {
        FROM_PRE1_0_to_1_0, FROM_1_0_to_1_3_1
    }

    private final XPlanDao xplanDao;

    private ManagerWorkspaceWrapper managerWorkspaceWrapper;

    /**
     * @param xplanDao
     *                 allows access to the database, never <code>null</code>
     * @param managerWorkspaceWrapper
     *                 never <code>null</code>
     */
    public DatabaseDataUpdater( XPlanDao xplanDao, ManagerWorkspaceWrapper managerWorkspaceWrapper ) {
        this.xplanDao = xplanDao;
        this.managerWorkspaceWrapper = managerWorkspaceWrapper;
    }

    /**
     * Updates data. Schema must be up to date already.
     * 
     * @param versions
     *            a list of {@link UPDATE_VERSION}s to execute, never <code>null</code>
     * 
     * @throws Exception
     *             if an error occurred during update
     */
    public void updateData( List<UPDATE_VERSION> versions )
                    throws Exception {
        Connection conn = managerWorkspaceWrapper.openConnection();
        conn.setAutoCommit( false );
        try {
            if ( versions.contains( FROM_PRE1_0_to_1_0 ) ) {
                UpdaterFromPre1_0To1_0 updaterFromPre1_0To1_0 = new UpdaterFromPre1_0To1_0( xplanDao );
                updaterFromPre1_0To1_0.update( conn );
            }
            if ( versions.contains( FROM_1_0_to_1_3_1 ) ) {
                UpdaterFrom1_0To1_3_1 updaterFrom1_0To1_3_1 = new UpdaterFrom1_0To1_3_1( xplanDao );
                updaterFrom1_0To1_3_1.update( conn );
            }
        } finally {
            closeQuietly( conn );
        }
    }

}
