/*-
 * #%L
 * xplan-cli-tools - Kommandozeilenwerkzeuge fuer die xPlanBox
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplanbox.cli.admin.db;

import de.latlon.xplan.manager.web.shared.XPlan;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Repository
public class SortPropertyDbUpdater {

	private static final Logger LOG = LoggerFactory.getLogger(SortPropertyDbUpdater.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Updates the wmsSortDate column of all tables in the syn schema and in
	 * xplanmgr.plans table.
	 * @param sortDate the new sort date, may be <code>null</code>
	 * @param plan the plan to update, never <code>null</code>
	 * @throws Exception
	 */
	public void updateSortProperty(Date sortDate, XPlan plan) throws Exception {
		updateSortPropertyInSynSchema(sortDate, plan);
		updateSortPropertyInMgrSchema(sortDate, plan);
	}

	@SuppressFBWarnings(value = "SQL_INJECTION_SPRING_JDBC",
			justification = "schemaname and tablename are selected from database")
	private void updateSortPropertyInSynSchema(Date sortDate, XPlan plan) throws Exception {
		String selectSchemaAndColumnsToModify = "SELECT table_name, table_schema "
				+ "FROM information_schema.columns WHERE table_schema like 'xplansyn%' "
				+ "AND table_name like 'xplan_%' AND column_name = 'xplan_wmssortdate';";
		List<TableInfo> tableInfos = jdbcTemplate.query(selectSchemaAndColumnsToModify, new TableInfoMapper());

		for (TableInfo tableInfo : tableInfos) {
			String schemaname = tableInfo.getSchemaName();
			String tablename = tableInfo.getTableName();

			StringBuilder updateSql = new StringBuilder();
			updateSql.append("UPDATE ").append(schemaname).append('.').append(tablename);
			updateSql.append(" SET xplan_wmssortdate = ? ");
			updateSql.append(" WHERE xplan_mgr_planid = ?");

			LOG.trace("SQL Update XPlan Syn sort date property: {}", updateSql);
			jdbcTemplate.update(updateSql.toString(), sortDate, getXPlanIdAsInt(plan.getId()));
		}
	}

	private void updateSortPropertyInMgrSchema(Date sortDate, XPlan plan) throws Exception {
		StringBuilder updateSql = new StringBuilder();
		updateSql.append("UPDATE xplanmgr.plans");
		updateSql.append(" SET wmssortdate = ? ");
		updateSql.append(" WHERE id = ?");

		LOG.trace("SQL Update XPlan Manager sort date property: {}", updateSql);
		jdbcTemplate.update(updateSql.toString(), sortDate, getXPlanIdAsInt(plan.getId()));
	}

	private int getXPlanIdAsInt(String planId) throws Exception {
		try {
			return Integer.parseInt(planId);
		}
		catch (NumberFormatException e) {
			throw new Exception("Spezifizierter Wert '" + planId + "' ist keine gültige XPlan-Id (Ganzzahl).", e);
		}
	}

}
