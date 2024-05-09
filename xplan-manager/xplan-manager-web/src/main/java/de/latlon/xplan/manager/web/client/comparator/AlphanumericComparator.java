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
package de.latlon.xplan.manager.web.client.comparator;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AlphanumericComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		if (o1 == null && o2 == null)
			return 0;
		if (o1 == null && o2 != null)
			return 1;
		if (o1 != null && o2 == null)
			return -1;
		List<IntegerOrString> integersO1 = parseInts(o1);
		List<IntegerOrString> integersO2 = parseInts(o2);
		if (integersO1.isEmpty() && integersO2.isEmpty())
			return o1.compareTo(o2);
		if (integersO1.isEmpty())
			return 1;
		if (integersO2.isEmpty())
			return -1;
		return compare(integersO1, integersO2);
	}

	private int compare(List<IntegerOrString> integersO1, List<IntegerOrString> integersO2) {
		int maxSize = Math.max(integersO1.size(), integersO2.size());
		for (int index = 0; index < maxSize; index++) {
			if (index < integersO1.size() && index < integersO2.size()) {
				IntegerOrString i1 = integersO1.get(index);
				IntegerOrString i2 = integersO2.get(index);
				int comparison = i1.compareTo(i2);
				if (comparison != 0)
					return comparison;
			}
			else if (index >= integersO1.size()) {
				return -1;
			}
			else if (index >= integersO2.size()) {
				return 1;
			}
		}
		return 0;
	}

	private List<IntegerOrString> parseInts(String stringToParse) {
		stringToParse = prepareStringToParse(stringToParse);
		if (stringToParse.startsWith("§") || stringToParse.startsWith("[§")) {
			RegExp p = RegExp.compile("\\d+");
			return findMatchingSortCriterias(p, stringToParse);
		}
		stringToParse = getStringToFirstSpace(stringToParse);
		RegExp p = RegExp.compile("([A-Za-z\\s]+|\\d+)");
		return findMatchingSortCriterias(p, stringToParse);
	}

	private static String prepareStringToParse(String stringToParse) {
		if (stringToParse.contains("|")) {
			stringToParse = stringToParse.substring(0, stringToParse.indexOf("|"));
		}
		stringToParse = stringToParse.trim();
		return stringToParse;
	}

	private String getStringToFirstSpace(String stringToParse) {
		RegExp p = RegExp.compile("([^\\s]+)");
		MatchResult matcher = p.exec(stringToParse);
		if (matcher != null) {
			return matcher.getGroup(0);
		}
		return stringToParse;
	}

	private List<IntegerOrString> findMatchingSortCriterias(RegExp p, String stringToParse) {
		MatchResult m = p.exec(stringToParse);
		List<IntegerOrString> sortSriterias = new ArrayList<>();
		while (m != null) {
			String sortCriteria = m.getGroup(0);

			IntegerOrString integerOrString;
			try {
				integerOrString = new IntegerOrString(Integer.parseInt(sortCriteria));
			}
			catch (NumberFormatException e) {
				integerOrString = new IntegerOrString(sortCriteria);
			}
			sortSriterias.add(integerOrString);
			stringToParse = stringToParse.replaceFirst(RegExp.quote(sortCriteria), "");
			m = p.exec(stringToParse);
		}
		return sortSriterias;
	}

	private class IntegerOrString implements Comparable {

		private Integer intValue;

		private String stringValue;

		IntegerOrString(Integer intValue) {
			this.intValue = intValue;
		}

		IntegerOrString(String stringValue) {
			this.stringValue = stringValue;
		}

		private boolean isInt() {
			return intValue != null;
		}

		private boolean isString() {
			return stringValue != null;
		}

		@Override
		public int compareTo(Object o) {
			if (o == null)
				return 1;
			IntegerOrString other = (IntegerOrString) o;
			if (isInt() && other.isInt())
				return intValue.compareTo(other.intValue);
			if (isString() && other.isString()) {
				return stringValue.compareTo(other.stringValue);
			}
			if (isInt() && other.isString())
				return -1;
			if (isString() && other.isInt())
				return 1;
			return 0;
		}

		@Override
		public String toString() {
			return isInt() ? Integer.toString(intValue) : stringValue;
		}

	}

}
