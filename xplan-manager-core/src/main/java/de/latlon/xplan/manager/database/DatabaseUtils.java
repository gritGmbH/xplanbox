package de.latlon.xplan.manager.database;

import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.workspace.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility class to open and close database resources.
 *
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 */
public final class DatabaseUtils {

    private static final Logger LOG = LoggerFactory.getLogger( DatabaseUtils.class );

    private DatabaseUtils() {
    }

    /**
     * opens a connection to workspace's resource
     *
     * @param ws worspace providing resource
     * @param jdbcPoolId jdbc pool id
     * @return opened connection
     */
    public static Connection openConnection( Workspace ws, String jdbcPoolId ) {
        ConnectionProvider resource = ws.getResource( ConnectionProviderProvider.class, jdbcPoolId );
        return resource.getConnection();
    }

    /**
     * quietly closes a connection and logs if closing failed
     *
     * @param conn connection to close
     */
    public static void closeQuietly( Connection conn ) {
        if ( conn != null )
            try {
                conn.close();
            } catch ( SQLException e ) {
                LOG.trace( "DB connection could not be closed!", e );
                LOG.warn( "DB connection could not be closed: {}", e.getMessage() );
            }
    }

    /**
     * quietly closes a statement and logs if closing failed
     *
     * @param stmt statement to close
     */
    public static void closeQuietly( Statement stmt ) {
        try {
            if ( stmt != null )
                stmt.close();
        } catch ( SQLException e ) {
            LOG.trace( "DB connection could not be closed!", e );
            LOG.warn( "DB connection could not be closed: {}", e.getMessage() );
        }
    }

    /**
     * quietly closes a connection and logs if closing failed
     *
     * @param rs result set to close
     */
    public static void closeQuietly( ResultSet rs ) {
        try {
            if ( rs != null )
                rs.close();
        } catch ( SQLException e ) {
            LOG.trace( "DB connection could not be closed!", e );
            LOG.warn( "DB connection could not be closed: {}", e.getMessage() );
        }
    }

    /**
     * quietly closes a statement and result set and logs if closing failed
     * @param stmt
     * @param rs
     */
    public static void closeQuietly( Statement stmt, ResultSet rs ) {
        closeQuietly( rs );
        closeQuietly( stmt );
    }

    /**
     * quietly closes a connection, statement and result set and logs if closing failed
     *
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void closeQuietly( Connection conn, Statement stmt, ResultSet rs ) {
        closeQuietly( rs );
        closeQuietly( stmt );
        closeQuietly( conn );
    }

}
