package de.latlon.xplan.commons.cli;

import java.sql.Connection;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface Synchronizer {

    /**
     * Synchronizes the plan with the passed id.
     *
     * @param conn
     *                 the database connection, never <code>null</code>
     * @param xPlanManagerId
     *                 the id of the plan, never <code>null</code>
     * @param planVersion
     *                 the version of the plan, never <code>null</code>
     * @param oldPlanStatus
     *                 the old status of the plan, never <code>null</code>
     * @param lastnew
     *                 the new status of the plan, never <code>null</code>
     * @param operation
     *                 the operation, never <code>null</code>
     * @throws SynchronizationException
     */
    void synchronize( Connection conn, int xPlanManagerId, String planVersion, String oldPlanStatus, String lastnew,
                      Operation operation )
                    throws SynchronizationException;
}