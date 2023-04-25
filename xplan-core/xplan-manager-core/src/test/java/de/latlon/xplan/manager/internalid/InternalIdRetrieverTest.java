/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.manager.internalid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.h2.tools.SimpleResultSet;
import org.junit.Before;
import org.junit.Test;

import de.latlon.xplan.manager.configuration.InternalIdRetrieverConfiguration;

/**
 * Tests for {@link de.latlon.xplan.manager.internalid.InternalIdRetriever}.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class InternalIdRetrieverTest {

	private InternalIdRetriever internalIdRetriever;

	@Before
	public void setup() throws Exception {
		InternalIdRetrieverConfiguration configuration = new InternalIdRetrieverConfiguration();
		internalIdRetriever = spy(new InternalIdRetriever(configuration));
		Connection conn = mock(Connection.class);
		PreparedStatement ps = mock(PreparedStatement.class);
		ResultSet rs = createResultSet();
		doReturn(conn).when(internalIdRetriever).createConnectionFromWorkspace();
		doReturn(ps).when(internalIdRetriever).retrievePreparedStatement(anyString(), anyString(), eq(conn));
		doReturn(rs).when(internalIdRetriever).retrieveResultSet(eq(ps));
	}

	@Test
	public void testGetMatchingInternalIds() throws Exception {
		Map<String, String> internalIds = internalIdRetriever.getMatchingInternalIds("test");

		assertEquals(3, internalIds.size());
		assertTrue(internalIds.containsKey("id1"));
		assertTrue(internalIds.containsKey("id2"));
		assertTrue(internalIds.containsKey("id3"));
		assertTrue(internalIds.containsValue("name1"));
		assertTrue(internalIds.containsValue("name2"));
		assertTrue(internalIds.containsValue("name3"));
	}

	private SimpleResultSet createResultSet() {
		SimpleResultSet rs = new SimpleResultSet();
		rs.addColumn("verfahrensid", 0, 0, 0);
		rs.addColumn("verfahrensname", 0, 0, 0);
		rs.addRow("id1", "name1");
		rs.addRow("id2", "name2");
		rs.addRow("id3", "name3");
		return rs;
	}

}
