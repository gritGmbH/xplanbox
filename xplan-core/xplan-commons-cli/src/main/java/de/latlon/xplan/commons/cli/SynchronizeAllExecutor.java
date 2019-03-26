package de.latlon.xplan.commons.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static de.latlon.xplan.commons.cli.DatabaseUtils.closeQuietly;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SynchronizeAllExecutor {

    private static final Logger LOG = LoggerFactory.getLogger( SynchronizeAllExecutor.class );

    private final String logTableName;

    private final SynchronizeExecutor executor;

    /**
     * @param logTableName
     *                 the name (including the schema) of the log table, never <code>null</code>
     * @param synchronizer
     *                 the {@link Synchronizer} used for the synchronization, never <code>null</code>
     */
    public SynchronizeAllExecutor( String logTableName, Synchronizer synchronizer ) {
        this.logTableName = logTableName;
        this.executor = new SynchronizeExecutor( logTableName, synchronizer );
    }

    /**
     * Synchronizes all plan available from the log table.
     */
    public void synchronizeAll( Connection conn ) {
        insertInLogTable( conn );
        executor.synchronize( conn );
    }

    private void insertInLogTable( Connection conn ) {
        LOG.info( "Copy required metadata into {}", logTableName );
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(
                            "insert into " + logTableName + " (xplanmgrid, xp_version, newplanstatus, operation, datum)"
                            + " SELECT id, xp_version, planstatus, 'INSERT', now() from xplanmgr.plans;" );

            LOG.debug( "Execute insert in {}: {}", logTableName, ps );
            ps.execute();
            conn.commit();
        } catch ( SQLException e ) {
            LOG.error( "Could not update plan {}", logTableName, e );
            try {
                if ( conn != null ) {
                    conn.rollback();
                }
            } catch ( SQLException s ) {
                LOG.error( "Rollback failed", e );
            }
        } finally {
            closeQuietly( ps );
        }
    }

}