/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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
package de.latlon.xplan.manager.web.client.comparator;

import de.latlon.xplan.manager.web.shared.XPlan;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.IMPORTDATE;
import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.NAME;
import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.TYPE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for {@link ColumnComparator}.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class ColumnComparatorTest {

	private XPlan plan1;

	private XPlan plan1Equal;

	private XPlan plan1Null;

	private XPlan plan2;

	@Before
	public void setup() {
		plan1 = new XPlan("name1", "1", "type1");
		plan1Equal = new XPlan("name1", "1", "type1");
		plan1Null = new XPlan("name2", "2", null);
		plan2 = new XPlan("name2", "2", "type2");
	}

	@Test
	public void testCompareShouldBeSmaller() throws Exception {
		ColumnComparator comparator = new ColumnComparator(NAME);
		int result = comparator.compare(plan1, plan2);

		assertThat(result, is(-1));
	}

	@Test
	public void testCompareShouldBeGreater() throws Exception {
		ColumnComparator comparator = new ColumnComparator(NAME);
		int result = comparator.compare(plan2, plan1);

		assertThat(result, is(1));
	}

	@Test
	public void testCompareShouldBeEqual() throws Exception {
		ColumnComparator comparator = new ColumnComparator(NAME);
		int result = comparator.compare(plan1, plan1Equal);

		assertThat(result, is(0));
	}

	@Test
	public void testCompareWithNullShouldBeSmaller() throws Exception {
		ColumnComparator comparator = new ColumnComparator(TYPE);
		int result = comparator.compare(plan1Null, plan1);

		assertThat(result, is(-1));
	}

	@Test
	public void testCompareWithNullShouldBeGreater() throws Exception {
		ColumnComparator comparator = new ColumnComparator(TYPE);
		int result = comparator.compare(plan1, plan1Null);

		assertThat(result, is(1));
	}

	@Test
	public void testCompareWithNullShouldBeEqual() throws Exception {
		ColumnComparator comparator = new ColumnComparator(NAME);
		int result = comparator.compare(plan1Null, plan1Null);

		assertThat(result, is(0));
	}

	@Test
	public void testCompareDate() throws Exception {
		plan1.setImportDate(new Date(112014));
		plan2.setImportDate(new Date(212014));
		ColumnComparator comparator = new ColumnComparator(IMPORTDATE);
		int result = comparator.compare(plan1, plan2);

		assertThat(result, is(-1));
	}

}
