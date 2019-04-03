package de.latlon.xplan.transform.cli;

import de.latlon.xplan.commons.cli.SynchronizeExecutor;
import de.latlon.xplan.commons.cli.Synchronizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static de.latlon.xplan.commons.cli.DatabaseUtils.closeQuietly;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TransformAllExecutor {

    private static final Logger LOG = LoggerFactory.getLogger( TransformAllExecutor.class );

    private final String logTableName;

    private final SynchronizeExecutor executor;

    /**
     * @param logTableName
     *                 the name (including the schema) of the log table, never <code>null</code>
     * @param synchronizer
     *                 the {@link Synchronizer} used for the synchronization, never <code>null</code>
     */
    public TransformAllExecutor( String logTableName, Synchronizer synchronizer ) {
        this.logTableName = logTableName;
        this.executor = new SynchronizeExecutor( logTableName, synchronizer );
    }

    /**
     * Synchronizes all plan available from the log table.
     */
    public void transformAll( Connection conn ) {
        insertInLogTable( conn );
        executor.synchronize( conn );
    }

    private void insertInLogTable( Connection conn ) {
        LOG.info( "Copy required metadata into {}", logTableName );
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement( "DELETE FROM " + logTableName );
            LOG.debug( "Execute delete from {}: {}", logTableName, ps );
            ps.execute();

            ps = conn.prepareStatement( "INSERT INTO " + logTableName
                                        + " (xplanmgrid, xp_version, newplanstatus, oldplanstatus, operation, datum, fids)"
                                        + " SELECT id, xp_version, planstatus, planstatus, (SELECT CASE WHEN EXISTS (SELECT fid FROM xplanmgr.features WHERE plan=id AND fid LIKE '%\\_PLAN\\_%' AND NOT EXISTS(SELECT gml_id from xplan51.gml_objects  WHERE fid = gml_id)) THEN 'INSERT' ELSE 'UPDATE' END), now(), (SELECT ARRAY(SELECT fid FROM xplanmgr.features WHERE plan= id)) from xplanmgr.plans" );
            LOG.debug( "Execute insert in {}: {}", logTableName, ps );
            ps.execute();
            conn.commit();
        } catch ( SQLException e ) {
            LOG.error( "Could not update log table {}", logTableName, e );
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
