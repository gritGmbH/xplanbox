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

import com.google.gwt.i18n.client.DateTimeFormat;
import de.latlon.xplan.manager.web.client.gui.PlanListColumnType;
import de.latlon.xplan.manager.web.shared.XPlan;

import java.util.Date;

import static de.latlon.xplan.manager.web.client.utils.DateTimeUtils.getImportDateFormat;
import static de.latlon.xplan.manager.web.client.utils.DateTimeUtils.getReleaseDateFormat;

/**
 * Checks if the plan contains a property matching the given search string.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class FreeTextFilter implements PlanFilter {

	private static final String WILDCARD = "*";

	private final String searchString;

	private final PlanListColumnType column;

	/**
	 * Instantiates a filter for a free text search in all columns.
	 * @param searchString used for search, if <code>null</code> all plans matches
	 */
	public FreeTextFilter(String searchString) {
		this(null, searchString);
	}

	/**
	 * Instantiates a filter to search in the specified column.
	 * @param column the column to search in, may be <code>null</code> if in all columns
	 * should be searched
	 * @param searchString used for search, if <code>null</code> all plans matches
	 */
	public FreeTextFilter(PlanListColumnType column, String searchString) {
		this.column = column;
		this.searchString = normalizeSearchString(searchString);
	}

	@Override
	public boolean isMatching(XPlan plan) {
		if (searchString == null)
			return true;
		if (column != null)
			return isMatchingColumn(column, plan);
		return isMatchingFreeText(plan);
	}

	private boolean isMatchingColumn(PlanListColumnType columnToSearchIn, XPlan plan) {
		switch (columnToSearchIn) {
			case ADDITIONALTYPE:
				return isMatching(plan.getAdditionalType());
			case ID:
				return isMatching(plan.getId());
			case IMPORTDATE:
				return isMatching(convertDateToString(plan.getImportDate(), getImportDateFormat()));
			case LEGISLATIONSTATUS:
				return isMatching(plan.getLegislationStatus());
			case NAME:
				return isMatching(plan.getName());
			case NUMBER:
				return isMatching(plan.getNumber());
			case RELEASEDATE:
				return isMatching(convertDateToString(plan.getReleaseDate(), getReleaseDateFormat()));
			case TYPE:
				return isMatching(plan.getType());
			default:
				return false;
		}
	}

	private String convertDateToString(Date date, DateTimeFormat dateFormat) {
		if (date != null) {
			return dateFormat.format(date);
		}
		return null;
	}

	private boolean isMatchingFreeText(XPlan plan) {
		for (PlanListColumnType searchColumn : PlanListColumnType.values()) {
			if (isMatchingColumn(searchColumn, plan))
				return true;
		}
		return false;
	}

	private boolean isMatching(String value) {
		if (value != null) {
			return containsIgnoreCase(value);
		}
		return false;
	}

	private boolean containsIgnoreCase(String value) {
		String[] searchStringParts = searchString.split("\\" + WILDCARD);
		if (searchStringParts.length == 0)
			return true;
		int indexSearchString = 0;
		for (int start = 0; start <= value.length(); start++) {
			String currentSearchString = searchStringParts[indexSearchString];
			if (isSearchStringMatching(value, start, currentSearchString)) {
				if (indexSearchString < searchStringParts.length - 1) {
					indexSearchString++;
					start += currentSearchString.length() - 1;
				}
				else {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isSearchStringMatching(String value, int start, String currentSearchString) {
		return value.regionMatches(true, start, currentSearchString, 0, currentSearchString.length());
	}

	private String normalizeSearchString(String searchString) {
		if (searchString == null)
			return null;
		String trimmedSearchString = searchString.trim();
		if (trimmedSearchString.length() == 0)
			return null;
		if (trimmedSearchString.startsWith(WILDCARD) && trimmedSearchString.length() > 1)
			return trimmedSearchString.substring(1);
		return trimmedSearchString;
	}

}
