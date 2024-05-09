/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
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
package de.latlon.xplan.manager.web.client.filter;

import java.util.Collections;
import java.util.List;

import de.latlon.xplan.manager.web.shared.XPlan;

/**
 * Checks if the plan is assigned to the category or not.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class CategoryFilter implements PlanFilter {

	private final List<String> categories;

	private final boolean isNegative;

	/**
	 * Instantiates a positive filter: a plan matches if the category of the plan is the
	 * same (ignoring case) as the given category.
	 * @param category the expected category, <code>null</code> if the filter is disabled
	 */
	public CategoryFilter(String category) {
		if (category != null)
			this.categories = Collections.singletonList(category);
		else
			this.categories = null;
		this.isNegative = false;
	}

	/**
	 *
	 * Instantiates a positive or negative filter (depends on the isNegative parameter): a
	 * plan matches if the category of the plan is (positive)/is not (negative) in the
	 * list of categories (ignoring case).
	 * @param categories the category currently selected, <code>null</code> or empty if
	 * the filter is disabled
	 * @param isNegative
	 */
	public CategoryFilter(List<String> categories, boolean isNegative) {
		this.categories = categories;
		this.isNegative = isNegative;
	}

	@Override
	public boolean isMatching(XPlan plan) {
		if (categories == null || categories.isEmpty())
			return true;
		boolean inCategoriesList = isInCategoriesList(plan);
		return isNegative ? !inCategoriesList : inCategoriesList;
	}

	private boolean isInCategoriesList(XPlan plan) {
		for (String category : categories) {
			if (category.equalsIgnoreCase(plan.getDistrict())) {
				return true;
			}
		}
		return false;
	}

}
