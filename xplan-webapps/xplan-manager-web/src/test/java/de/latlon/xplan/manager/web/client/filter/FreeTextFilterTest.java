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
package de.latlon.xplan.manager.web.client.filter;

import de.latlon.xplan.manager.web.shared.XPlan;
import org.junit.Ignore;
import org.junit.Test;

import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.ID;
import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.LEGISLATIONSTATUS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class FreeTextFilterTest {

	@Test
	public void testIsMatchingWithNullStringShouldReturnTrue() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter(null);

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithEmptyStringShouldReturnTrue() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter("");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithWhiteSpaceStringShouldReturnTrue() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter(" ");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithExactCategoryShouldReturnTrue() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter("name");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithLowerUpperCaseLegislationStatusShouldReturnTrue() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter("legislaTionsTAtus");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithParAdditionaltypeShouldReturnTrue() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter("itio");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithWildcardShouldReturnTrue() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter("legis*STATUS");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithWildcardInBeginningShouldReturnTrue() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter("*isla");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithWildcardOnlyShouldReturnTrue() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter("leg*STA*US");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithMultipleWildcardsShouldReturnTrue() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter("*");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	public void testIsMatchingWithWildcardShoudReturnFalse() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter("legislation*ion");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(false));
	}

	@Ignore("Test currently fails as a gwt client class (here DateTimeFormat) cannot be used.")
	@Test
	public void testIsMatchingWithUnavailableStringShouldReturnFalse() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter("SeArch");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(false));
	}

	@Ignore("Test currently fails as a gwt client class (here DateTimeFormat) cannot be used.")
	@Test
	public void testIsMatchingWithUnsearchableColumnShouldReturnFalse() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter("category");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(false));
	}

	@Test
	public void testIsMatchingInColumnlegislationShouldReturnTrue() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter(LEGISLATIONSTATUS, "leg*StaT");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingInColumnIdShouldReturnFalse() throws Exception {
		FreeTextFilter freeTextFilter = new FreeTextFilter(ID, "leg*StaT");

		XPlan plan = createPlan();
		boolean matching = freeTextFilter.isMatching(plan);

		assertThat(matching, is(false));
	}

	private XPlan createPlan() {
		XPlan xPlan = new XPlan("name", "id", "type");
		xPlan.setDistrict("category");
		xPlan.setLegislationStatus("legislationStatus");
		xPlan.setAdditionalType("additionalType");
		return xPlan;
	}

}
