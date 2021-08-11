/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.jdbcconfig;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Writes jdbc connection configuration files for deegree.
 * 
 * @author Florian Bingel
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
