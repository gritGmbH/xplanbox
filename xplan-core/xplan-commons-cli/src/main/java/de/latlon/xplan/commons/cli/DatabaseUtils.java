package de.latlon.xplan.commons.cli;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DatabaseUtils {

    /**
     * Creates a Postgres JDBC Url
     *
     * @param host
     *                 never <code>null</code>
     * @param port
     *                 may be<code>null</code>
     * @param database
     *                 never <code>null</code>
     * @return the jdbc url, never <code>null</code>
     */
    public static String createJdbcUrl( String host, String port, String database ) {
        StringBuffer jdbcUrl = new StringBuffer();
        jdbcUrl.append( "jdbc:postgresql://" );
        jdbcUrl.append( host );
        if ( port != null )
            jdbcUrl.append( ":" ).append( port );
        jdbcUrl.append( "/" );
        jdbcUrl.append( database );
        return jdbcUrl.toString();
    }

    /**
     * @param ps
     *                 to close, may be <code>null</code>
     * @param rs
     *                 to close, may be <code>null</code>
     */
    public static void closeQuietly( PreparedStatement ps, ResultSet rs ) {
        closeQuietly( ps );
        if ( rs != null ) {
            try {
                rs.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param ps
     *                 to close, may be <code>null</code>
     */
    public static void closeQuietly( PreparedStatement ps ) {
        if ( ps != null ) {
            try {
                ps.close();
            } catch ( SQLException e ) {
            }
        }
    }

}
