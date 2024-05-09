/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.wmsconfig.raster;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.deegree.theme.persistence.standard.jaxb.ThemeType.Layer;

/**
 * Sorts raster configurations.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class RasterConfigurationSorter {

	/**
	 * Sorts the strings of the passed map by the dates by the following order: First null
	 * dates, then the dates in ascending order. if dates are equal, the strings are
	 * sorted ascending.
	 * @param unsortedMap the map with the strings to sort by the date, never
	 * <code>null</code>
	 * @return the string sorted by the date, never <code>null</code>
	 */
	public List<String> sortByDateInDeegreeOrder(Map<String, Date> unsortedMap) {
		List<Map.Entry<String, Date>> list = new LinkedList<Map.Entry<String, Date>>(unsortedMap.entrySet());

		sort(list, new IdByDateComparator());

		List<String> sortedList = new ArrayList<>();
		for (Entry<String, Date> entry : list) {
			sortedList.add(entry.getKey());
		}
		return sortedList;
	}

	/**
	 * Sorts the passed layer in the same order as the order if the sortedPrefixes. If the
	 * sortedPrefixList does not contain a matching entry for a layer, this layer is
	 * sorted in the beginning.
	 * @param layers the layers list to sort, never <code>null</code>
	 * @param sortedPrefixList the list with reference order, never <code>null</code>
	 */
	public void sortLayers(List<Layer> layers, List<String> sortedPrefixList) {
		LayerBySortedPrefixListComparator comparator = new LayerBySortedPrefixListComparator(sortedPrefixList);
		sort(layers, comparator);
	}

	private class IdByDateComparator implements Comparator<Map.Entry<String, Date>> {

		@Override
		public int compare(Map.Entry<String, Date> o1, Map.Entry<String, Date> o2) {
			Date value1 = o1.getValue();
			Date value2 = o2.getValue();
			if (value1 == null) {
				if (value2 == null)
					return o1.getKey().compareTo(o2.getKey());
				else
					return -1;
			}
			if (value2 == null)
				return 1;
			int compareTo = value1.compareTo(value2);
			if (compareTo == 0)
				return o1.getKey().compareTo(o2.getKey());
			return compareTo;
		}

	}

	private class LayerBySortedPrefixListComparator implements Comparator<Layer> {

		private final List<String> sortedPrefixList;

		public LayerBySortedPrefixListComparator(List<String> sortedPrefixList) {
			this.sortedPrefixList = sortedPrefixList;
		}

		@Override
		public int compare(Layer o1, Layer o2) {
			String value1 = o1.getValue();
			String value2 = o2.getValue();
			int index1 = findIndex(value1);
			int index2 = findIndex(value2);
			return Integer.compare(index1, index2);
		}

		private int findIndex(String id) {
			for (String prefix : sortedPrefixList) {
				if (id.startsWith(prefix + "_"))
					return sortedPrefixList.indexOf(prefix);
			}
			return 1;
		}

	}

}
