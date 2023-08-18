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
package de.latlon.xplan.manager.wmsconfig.raster;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deegree.theme.persistence.standard.jaxb.ThemeType.Layer;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class RasterConfigurationSorterTest {

	private final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	private final RasterConfigurationSorter rasterConfigurationSorter = new RasterConfigurationSorter();

	@Test
	public void testSortByDateInDeegreeOrder() throws Exception {
		Map<String, Date> unsortedMap = new HashMap<String, Date>();
		unsortedMap.put("4", date("2015-03-01"));
		unsortedMap.put("1", null);
		unsortedMap.put("6", date("2015-04-01"));
		unsortedMap.put("2", null);
		unsortedMap.put("5", date("2015-03-01"));
		unsortedMap.put("3", date("1999-01-01"));
		List<String> sortedStrings = rasterConfigurationSorter.sortByDateInDeegreeOrder(unsortedMap);

		assertThat(sortedStrings, hasIdsInOrder());
	}

	@Test
	public void testSortLayers() {
		List<Layer> layers = new ArrayList<Layer>();
		layers.add(mockLayer("5"));
		layers.add(mockLayer("7"));
		layers.add(mockLayer("2"));
		layers.add(mockLayer("3"));
		List<String> sortedPlanIds = asList(new String[] { "1", "2", "3", "4", "5", "6", "7" });
		rasterConfigurationSorter.sortLayers(layers, sortedPlanIds);

		assertThat(layers, hasLayersInOrder());
	}

	@Test
	public void testSortLayers_MissingEntryInSortedList() {
		List<Layer> layers = new ArrayList<Layer>();
		layers.add(mockLayer("7"));
		layers.add(mockLayer("6"));
		layers.add(mockLayer("1"));
		layers.add(mockLayer("3"));
		List<String> sortedPlanIds = asList(new String[] { "2", "3", "4", "5", "6", "7" });
		rasterConfigurationSorter.sortLayers(layers, sortedPlanIds);

		assertThat(layers, hasLayersInOrder());
	}

	private Layer mockLayer(String id) {
		Layer layer = mock(Layer.class);
		when(layer.getValue()).thenReturn(id + "_testLayer");
		when(layer.toString()).thenReturn(id);
		return layer;
	}

	private Matcher<? super List<String>> hasIdsInOrder() {
		return new TypeSafeMatcher<List<String>>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("assert items in order");
			}

			@Override
			protected boolean matchesSafely(List<String> sortedList) {
				ArrayList<String> copy = new ArrayList<String>(sortedList);
				sort(copy);
				return sortedList.equals(copy);
			}
		};
	}

	private Matcher<? super List<Layer>> hasLayersInOrder() {
		return new TypeSafeMatcher<List<Layer>>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("assert items in order");
			}

			@Override
			protected boolean matchesSafely(List<Layer> sortedList) {
				ArrayList<String> values = extractValues(sortedList);
				ArrayList<String> copy = new ArrayList<String>(values);
				sort(copy);
				return values.equals(copy);
			}

			private ArrayList<String> extractValues(List<Layer> sortedList) {
				ArrayList<String> copy = new ArrayList<String>();
				for (Layer layer : sortedList) {
					copy.add(layer.getValue());
				}
				return copy;
			}
		};
	}

	private Date date(String date) throws ParseException {
		return FORMATTER.parse(date);
	}

}
