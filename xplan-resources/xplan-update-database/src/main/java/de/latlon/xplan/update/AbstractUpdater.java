//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
----------------------------------------------------------------------------*/
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

    private final Logger LOG = LoggerFactory.getLogger( AbstractUpdater.class );

    protected final XPlanDao xplanDao;

    /**
     * @param xplanDao
     *            allows access to the database, never <code>null</code>
     */
    public AbstractUpdater( XPlanDao xplanDao ) {
        this.xplanDao = xplanDao;
    }

    /**
     * @param conn
     *            open database connection, is not closed in this methode, never <code>null</code>
     * @throws Exception
     *             if an exception occured during update
     */
    public abstract void update( Connection conn )
                    throws Exception;

    /**
     * @param conn
     *            open database connection, to rollback, never <code>null</code>
     */
    protected void rollback( Connection conn ) {
        try {
            conn.rollback();
        } catch ( SQLException e1 ) {
            LOG.warn( "Rollback failed!", e1 );
        }
    }

}