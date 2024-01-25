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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import de.latlon.xplan.manager.web.shared.XPlan;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class CategoryFilterTest {

	private static final boolean NEGATIVE = true;

	private static final boolean POSITIVE = false;

	private static final String CATEGORY = "category";

	private static final String CATEGORY_LOWER_UPPER_CASE = "caTegOry";

	private final CategoryFilter categoryFilter = new CategoryFilter(CATEGORY);

	private final CategoryFilter categoryListNegativeFilter = new CategoryFilter(createCategoryList(), NEGATIVE);

	private final CategoryFilter categoryListPositiveFilter = new CategoryFilter(createCategoryList(), POSITIVE);

	@Test
	public void testIsMatchingWithFilterCategoryShouldMatch() throws Exception {
		boolean matching = categoryFilter.isMatching(createPlanWithCategory());

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithFilterCategoryAndLowerUpperPlanShouldMatch() throws Exception {
		boolean matching = categoryFilter.isMatching(createPlanWithLowerUpperCategory());

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithoutFilterCategoryShouldNotMatch() throws Exception {
		boolean matching = categoryFilter.isMatching(createPlan("UNKNOWN"));

		assertThat(matching, is(false));
	}

	@Test
	public void testIsMatchingWithCategoryListPositiveShouldMatch() throws Exception {
		boolean matching = categoryListPositiveFilter.isMatching(createPlanWithCategory());

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithCategoryListNegativeShouldNotMatch() throws Exception {
		boolean matching = categoryListNegativeFilter.isMatching(createPlanWithCategory());

		assertThat(matching, is(false));
	}

	@Test
	public void testIsMatchingWithNullCategoryFilterShouldMatch() throws Exception {
		CategoryFilter nullFilter = new CategoryFilter(null);
		boolean matching = nullFilter.isMatching(createPlanWithCategory());

		assertThat(matching, CoreMatchers.is(true));
	}

	@Test
	public void testIsMatchingWithEmptyCategoryAndPositiveFilterShouldMatch() throws Exception {
		CategoryFilter emptyFilter = new CategoryFilter(Collections.<String>emptyList(), POSITIVE);
		boolean matching = emptyFilter.isMatching(createPlanWithCategory());

		assertThat(matching, CoreMatchers.is(true));
	}

	@Test
	public void testIsMatchingWithEmptyCategoryAndNegativFilterShouldMatch() throws Exception {
		CategoryFilter emptyFilter = new CategoryFilter(Collections.<String>emptyList(), NEGATIVE);
		boolean matching = emptyFilter.isMatching(createPlanWithCategory());

		assertThat(matching, CoreMatchers.is(true));
	}

	private List<String> createCategoryList() {
		ArrayList<String> categories = new ArrayList<String>();
		categories.add("cate1");
		categories.add(CATEGORY);
		return categories;
	}

	private XPlan createPlanWithCategory() {
		return createPlan(CATEGORY);
	}

	private XPlan createPlanWithLowerUpperCategory() {
		return createPlan(CATEGORY_LOWER_UPPER_CASE);
	}

	private XPlan createPlan(String category) {
		XPlan xPlan = new XPlan("name", "id", "type");
		xPlan.setDistrict(category);
		return xPlan;
	}

}
