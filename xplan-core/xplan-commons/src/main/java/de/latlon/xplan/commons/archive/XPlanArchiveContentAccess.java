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
package de.latlon.xplan.commons.archive;

import java.io.InputStream;
import java.util.List;

/**
 * Provides access to the content of the XPlanArchive.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public interface XPlanArchiveContentAccess {

    /**
     * @return a list of {@link ArchiveEntry}s encapsulated in this archive
     */
    List<? extends ArchiveEntry> getZipFileEntries();

    /**
     * Retrieve the content of the entry specified with its name as {@link InputStream}.
     * 
     * @param name
     *            the name of the entry, never <code>null</code>
     * @return an {@link InputStream} for the given zip entry
     * @throws IllegalArgumentException
     *             if name is <code>null</code> or no entry with this name exists
     */
    InputStream retrieveInputStreamFor( String name );

    /**
     * Retrieve the content of the entry as {@link ArchiveEntry}.
     * 
     * @param name
     *            the name of the entry, never <code>null</code>
     * @return the {@link ArchiveEntry} with the specified name, <code>null</code> if no entry exists.
     * @throws IllegalArgumentException
     *             if name is <code>null</code>
     */
    ArchiveEntry getEntry( String name );

}