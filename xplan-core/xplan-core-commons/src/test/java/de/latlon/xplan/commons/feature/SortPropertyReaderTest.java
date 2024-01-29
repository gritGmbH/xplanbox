/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import org.deegree.feature.FeatureCollection;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanType.FP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
class SortPropertyReaderTest {

	@Test
	void testReadSortDate() throws Exception {
		SortConfiguration sortConfiguration = createSortConfiguration("BP_Plan", "technHerstellDatum");
		SortPropertyReader sortPropertyReader = new SortPropertyReader(sortConfiguration);
		Date readSortDate = sortPropertyReader.readSortDate(BP_Plan, XPLAN_40, readFeatureCollection());

		assertThat(readSortDate).isEqualTo(asDate("2001-08-06"));
	}

	@Test
	void testReadSortDate_UnmatchingType() throws Exception {
		SortConfiguration sortConfiguration = createSortConfiguration("BP_Plan", "technHerstellDatum");
		SortPropertyReader sortPropertyReader = new SortPropertyReader(sortConfiguration);
		Date readSortDate = sortPropertyReader.readSortDate(FP_Plan, XPLAN_40, readFeatureCollection());

		assertThat(readSortDate).isNull();
	}

	@Test
	void testReadSortDate_UnmatchingVersion() throws Exception {
		SortConfiguration sortConfiguration = createSortConfiguration("BP_Plan", "technHerstellDatum");
		SortPropertyReader sortPropertyReader = new SortPropertyReader(sortConfiguration);
		Date readSortDate = sortPropertyReader.readSortDate(BP_Plan, XPLAN_50, readFeatureCollection());

		assertThat(readSortDate).isNull();
	}

	@Test
	void testReadSortDate_UnavailableFeatureType() throws Exception {
		SortConfiguration sortConfiguration = createSortConfiguration("BP_PlanNotThere", "technHerstellDatum");
		SortPropertyReader sortPropertyReader = new SortPropertyReader(sortConfiguration);
		Date readSortDate = sortPropertyReader.readSortDate(BP_Plan, XPLAN_50, readFeatureCollection());

		assertThat(readSortDate).isNull();
	}

	@Test
	void testReadSortDate_UnavailableProperty() throws Exception {
		SortConfiguration sortConfiguration = createSortConfiguration("BP_Plan", "notThereDatum");
		SortPropertyReader sortPropertyReader = new SortPropertyReader(sortConfiguration);
		Date readSortDate = sortPropertyReader.readSortDate(BP_Plan, XPLAN_50, readFeatureCollection());

		assertThat(readSortDate).isNull();
	}

	@Test
	void testReadSortDate_NullSortConfiguration() {
		assertThrows(NullPointerException.class, () -> new SortPropertyReader(null));
	}

	private SortConfiguration createSortConfiguration(String featureType, String propertyName) {
		SortConfiguration mockedSortConfig = mock(SortConfiguration.class);
		when(mockedSortConfig.retrieveFeatureType(BP_Plan, XPLAN_40)).thenReturn(featureType);
		when(mockedSortConfig.retrievePropertyName(BP_Plan, XPLAN_40)).thenReturn(propertyName);
		return mockedSortConfig;
	}

	private FeatureCollection readFeatureCollection() throws Exception {
		String name = "xplan51/BP2070.zip";
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip(name,
				getClass().getResourceAsStream("/testdata/" + name));
		return XPlanGmlParserBuilder.newBuilder().build().parseFeatureCollection(archive);
	}

	private Date asDate(String string) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.parse(string);
	}

}
