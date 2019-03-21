package de.latlon.xplan.commons.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SynchronizeExecutor {

    private static final Logger LOG = LoggerFactory.getLogger( SynchronizeExecutor.class );

    private String logTableName;

    private Synchronizer synchronizer;

    /**
     * @param logTableName
     *                 the name (including the schema) of the log table, never <code>null</code>
     * @param synchronizer
     *                 the {@link Synchronizer} used for the synchronization, never <code>null</code>
     */
    public SynchronizeExecutor( String logTableName, Synchronizer synchronizer ) {
        this.logTableName = logTableName;
        this.synchronizer = synchronizer;
    }

    /**
     * Starts the synchronization.
     *
     * @param conn
     *                 to the dataase with th log table,  never <code>null</code>
     */
    public void synchronize( Connection conn ) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append( "SELECT xplanmgrid, " );
            sb.append( "(SELECT xp_version FROM " ).append( logTableName ).append(
                            " log0 WHERE log0.xplanmgrid = log.xplanmgrid LIMIT 1), " );
            sb.append( "(SELECT oldplanstatus FROM " ).append( logTableName ).append(
                            " log1 WHERE log1.xplanmgrid = log.xplanmgrid ORDER BY datum ASC LIMIT 1), " );
            sb.append( "(SELECT operation oldoperation FROM " ).append( logTableName ).append(
                            " log2 WHERE log2.xplanmgrid = log.xplanmgrid ORDER BY datum ASC LIMIT 1), " );
            sb.append( "(SELECT newplanstatus FROM " ).append( logTableName ).append(
                            " log3 WHERE log3.xplanmgrid = log.xplanmgrid ORDER BY datum DESC LIMIT 1), " );
            sb.append( "(SELECT operation newoperation FROM " ).append( logTableName ).append(
                            " log4 WHERE log4.xplanmgrid = log.xplanmgrid ORDER BY datum DESC LIMIT 1) " );
            sb.append( "FROM " ).append( logTableName ).append( " log GROUP BY xplanmgrid;" );
            ps = conn.prepareStatement( sb.toString() );
            LOG.debug( "Execute select plans to update: {}", ps );

            rs = ps.executeQuery();
            while ( rs.next() ) {
                synchronizePlan( conn, rs );
            }
        } catch ( SQLException e ) {
            LOG.error( "Could not select plans to synchronize from " + logTableName, e );
        } finally {
            DatabaseUtils.closeQuietly( ps, rs );
        }
    }

    private void synchronizePlan( Connection conn, ResultSet rs ) {
        int xplanmgrid = -1;
        try {
            xplanmgrid = rs.getInt( 1 );
            String version = rs.getString( 2 );
            String oldplanstatus = rs.getString( 3 );
            String oldoperation = rs.getString( 4 );
            String newplanstatus = rs.getString( 5 );
            String newoperation = rs.getString( 6 );

            Operation operation = determineOperationForSynchronization( oldoperation, newoperation );
            if ( operation != null ) {
                LOG.info( "Synchronize tables in lgv syn schema for plan with id {}, operation is {}, oldplanstatus {}, newplanststus {}",
                          xplanmgrid, operation, oldplanstatus, newplanstatus );
                synchronizer.synchronize( conn, xplanmgrid, version, oldplanstatus, newplanstatus, operation );
            } else {
                LOG.info( "Plan with id {} is already removed from xPlanBox. Will be removed from {}.", xplanmgrid,
                          operation, logTableName );
            }
            removePlanFromLog( conn, xplanmgrid );

            conn.commit();
        } catch ( SQLException | SynchronizationException e ) {
            LOG.error( "Could not update plan with id {}. Plan is not synchronized", xplanmgrid, e );
            try {
                if ( conn != null ) {
                    conn.rollback();
                }
            } catch ( SQLException s ) {
                LOG.error( "Rollback failed", e );
            }
        }
    }

    private Operation determineOperationForSynchronization( String oldoperation, String newoperation ) {
        if ( Operation.INSERT.name().equals( oldoperation ) && Operation.INSERT.name().equals( newoperation ) )
            return Operation.INSERT;
        if ( Operation.UPDATE.name().equals( oldoperation ) && Operation.UPDATE.name().equals( newoperation ) )
            return Operation.UPDATE;
        if ( Operation.DELETE.name().equals( oldoperation ) && Operation.DELETE.name().equals( newoperation ) )
            return Operation.DELETE;

        if ( Operation.INSERT.name().equals( oldoperation ) && !Operation.DELETE.name().equals( newoperation ) )
            return Operation.INSERT;
        if ( Operation.UPDATE.name().equals( oldoperation ) && Operation.DELETE.name().equals( newoperation ) )
            return Operation.DELETE;
        if ( Operation.UPDATE.name().equals( oldoperation ) && Operation.UPDATE.name().equals( newoperation ) )
            return Operation.UPDATE;
        return null;
    }

    private void removePlanFromLog( Connection conn, int xplanmgrid ) {
        PreparedStatement ps = null;
        try {
            String selectSynTableNames = "DELETE FROM " + logTableName + " WHERE xplanmgrid = ?";
            ps = conn.prepareStatement( selectSynTableNames );
            ps.setInt( 1, xplanmgrid );
            LOG.debug( "Execute select plans to update: {}", ps );
            ps.execute();
        } catch ( SQLException e ) {
            LOG.warn( "Could not remove plan with id {} from {}: {}", xplanmgrid, logTableName, e );
        } finally {
            DatabaseUtils.closeQuietly( ps );
        }
    }

}