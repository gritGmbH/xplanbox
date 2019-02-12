package de.latlon.xplan.manager.jdbcconfig;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Writes jdbc connection configuration files for deegree.
 * 
 * @author Florian Bingel
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public interface JdbcConfigWriter {

    /**
     * Writes a deegree jdbc configuration file in the passed {@link OutputStream}.
     * 
     * @param outputStream
     *            to write in, never <code>null</code>
     * @param jdbcConnection
     *            to write in the configuration, should not be <code>null</code>
     * @param dbName
     *            to write in the configuration, should not be <code>null</code>
     * @param user
     *            to write in the configuration, should not be <code>null</code>
     * @param pw
     *            to write in the configuration, should not be <code>null</code>
     * @throws IOException
     */
    public void write( OutputStream outputStream, String jdbcConnection, String dbName, String user, String pw )
                            throws IOException;

}