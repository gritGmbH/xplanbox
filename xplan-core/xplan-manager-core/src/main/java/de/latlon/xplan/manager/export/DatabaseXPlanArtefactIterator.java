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
package de.latlon.xplan.manager.export;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.deegree.commons.utils.JDBCUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Iterates over the artefacts entry in the database and creates {@link XPlanArtefact}s
 * from it (columnIndex 1 must be the filename, 2 the data).
 *
 * Call {@link XPlanArtefactIterator#close()) if working with the result set is finished!
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class DatabaseXPlanArtefactIterator implements XPlanArtefactIterator {

	private static final Logger LOG = LoggerFactory.getLogger(DatabaseXPlanArtefactIterator.class);

	private final Connection conn;

	private final PreparedStatement stmt;

	private final ResultSet rs;

	public DatabaseXPlanArtefactIterator(Connection conn, PreparedStatement stmt, ResultSet rs) {
		this.conn = conn;
		this.stmt = stmt;
		this.rs = rs;
	}

	@Override
	public boolean hasNext() {
		try {
			return rs.next();
		}
		catch (SQLException e) {
			throw new XPlanExportException(e);
		}
	}

	@Override
	public XPlanArtefact next() {
		try {
			String fileName = rs.getString(1);
			InputStream artefact = rs.getBinaryStream(2);
			return new XPlanArtefact(fileName, artefact);
		}
		catch (SQLException e) {
			throw new XPlanExportException(e);
		}
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Remove is not supported!");
	}

	@Override
	public void close() throws IOException {
		JDBCUtils.close(rs, stmt, conn, LOG);
	}

}
