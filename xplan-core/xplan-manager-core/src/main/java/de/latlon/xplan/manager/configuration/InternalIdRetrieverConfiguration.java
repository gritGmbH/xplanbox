/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
package de.latlon.xplan.manager.configuration;

/**
 * Provides access to the configuration for the InternalIdRetriever.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class InternalIdRetrieverConfiguration {

	private String workspaceName = "xplan-manager-workspace";

	private String jdbcConnectionId = "vfdb";

	private String internalIdLabel = "verfahrensid";

	private String internalNameLabel = "verfahrensname";

	private String selectMatchingIdsSql = "SELECT verfahrensid, verfahrensname FROM planverfahren WHERE lower(verfahrensname) LIKE lower(?) ORDER BY verfahrensname ASC";

	private String selectAllSql = "SELECT verfahrensid, verfahrensname FROM planverfahren ORDER BY verfahrensname ASC";

	/**
	 * @return the name of the workspace where the database with internal ids is
	 * configured, never <code>null</code>
	 */
	public String getWorkspaceName() {
		return workspaceName;
	}

	/**
	 * @param workspaceName the name of the workspace where the database with internal ids
	 * is configured, never <code>null</code>
	 */
	public void setWorkspaceName(String workspaceName) {
		this.workspaceName = workspaceName;
	}

	/**
	 * @return the id of the jdbc connection configuration in the workspace, never
	 * <code>null</code>
	 */
	public String getJdbcConnectionId() {
		return jdbcConnectionId;
	}

	/**
	 * @param jdbcConnectionId the id of the jdbc connection configuration in the
	 * workspace, never <code>null</code>
	 */
	public void setJdbcConnectionId(String jdbcConnectionId) {
		this.jdbcConnectionId = jdbcConnectionId;
	}

	/**
	 * @return the SQL to select the internal ids matching to a plan id, the SQL must
	 * contain exactly one '?' in the WHERE-Clause to limit the results to this id, never
	 * <code>null</code>. The columns with internalIdLabel and internalNameLabel must be
	 * selected (at least this columns and with exactly the configured names).
	 */
	public String getSelectMatchingIdsSql() {
		return selectMatchingIdsSql;
	}

	/**
	 * @param selectMatchingIdsSql the SQL to select the internal ids matching to a plan
	 * id, the SQL must contain exactly one '?' in the WHERE-Clause to limit the results
	 * to this id, never <code>null</code>. The columns with internalIdLabel and
	 * internalNameLabel must be selected (at least this columns and with exactly the
	 * configured names).
	 */
	public void setSelectMatchingIdsSql(String selectMatchingIdsSql) {
		this.selectMatchingIdsSql = selectMatchingIdsSql;
	}

	/**
	 * @return the SQL to select all internal ids for a plan id, the SQL must be fully
	 * executable (no '?'), never <code>null</code>. The columns with internalIdLabel and
	 * internalNameLabel must be selected (at least this columns and with exactly the
	 * configured names).
	 */
	public String getSelectAllSql() {
		return selectAllSql;
	}

	/**
	 * @param selectAllSql the SQL to select all internal ids for a plan id, the SQL must
	 * be fully executable (no '?'), never <code>null</code>. The columns with
	 * internalIdLabel and internalNameLabel must be selected (at least this columns and
	 * with exactly the configured names).
	 */
	public void setSelectAllSql(String selectAllSql) {
		this.selectAllSql = selectAllSql;
	}

	/**
	 * @return the name of the column containing the internal id, never <code>null</code>
	 */
	public String getInternalIdLabel() {
		return internalIdLabel;
	}

	/**
	 * @param internalIdLabel the name of the column containing the internal id, never
	 * <code>null</code>
	 */
	public void setInternalIdLabel(String internalIdLabel) {
		this.internalIdLabel = internalIdLabel;
	}

	/**
	 * @return the name of the column containing the internal name, never
	 * <code>null</code>
	 */
	public String getInternalNameLabel() {
		return internalNameLabel;
	}

	/**
	 * @param internalNameLabel the name of the column containing the internal name, never
	 * <code>null</code>
	 */
	public void setInternalNameLabel(String internalNameLabel) {
		this.internalNameLabel = internalNameLabel;
	}

}
