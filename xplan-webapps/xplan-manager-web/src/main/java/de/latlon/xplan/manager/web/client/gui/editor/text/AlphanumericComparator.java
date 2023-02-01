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
package de.latlon.xplan.manager.web.client.gui.editor.text;

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
		List<Integer> integersO1 = parseInts(o1);
		List<Integer> integersO2 = parseInts(o2);
		if (integersO1.isEmpty() && integersO2.isEmpty())
			return o1.compareTo(o2);
		if (integersO1.isEmpty())
			return 1;
		if (integersO2.isEmpty())
			return -1;
		return compare(integersO1, integersO2);
	}

	private int compare(List<Integer> integersO1, List<Integer> integersO2) {
		int maxSize = Math.max(integersO1.size(), integersO2.size());
		for (int index = 0; index < maxSize; index++) {
			if (index < integersO1.size() && index < integersO2.size()) {
				Integer i1 = integersO1.get(index);
				Integer i2 = integersO2.get(index);
				if (!i1.equals(i2))
					return i1.compareTo(i2);
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

	private List<Integer> parseInts(String stringToParse) {
		RegExp regExp = RegExp.compile("\\d+", "gi");
		MatchResult matcher = regExp.exec(stringToParse);
		List<Integer> integers = new ArrayList<Integer>();
		while (matcher != null) {
			integers.add(Integer.parseInt(matcher.getGroup(0)));
			matcher = regExp.exec(stringToParse);
		}
		return integers;
	}

}
