package de.latlon.xplan.update.db;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class TableInfoMapper implements RowMapper<TableInfo> {

	@Override
	public TableInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		TableInfo tableInfo = new TableInfo();
		tableInfo.setSchemaName(rs.getString("table_schema"));
		tableInfo.setTableName(rs.getString("table_name"));
		return tableInfo;
	}

}
