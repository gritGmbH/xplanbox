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

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(Parameterized.class)
public class ParameterizedXPlanGmlParserTest {

	private final String resourceUnderTest;

	private final String expectedPlanName;

	private final String expectedPlanGz;

	private final String expectedPlanNumber;

	private final int expectedNumberOfFeatures;

	@Parameterized.Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] { { "xplan41/BP2070.zip", null, "4011000", null, 314 },
				{ "xplan41/BP2135.zip", "Bebauungsplan 2135", "4011000", "2135", 241 },
				{ "xplan41/Demo.zip", "BPlan Demo-Gemeinde", "1234567", null, 20 },
				{ "xplan41/Eidelstedt_4_V4.zip", "Eidelstedt 4", "02000000", null, 56 },
				{ "xplan41/FPlan.zip", "FPlan Bad Liebenwerda", "12062024", null, 3602 },
				{ "xplan41/LA22.zip", "Bebauungsplan LA 22", "02000000", "LA 22", 1349 },
				{ "xplan41/LA67.zip", "Bebauungsplan LA 22", "1234567", "LA 22", 146 },
				{ "xplan41/BPlan001_4-1.zip", "BPlan001_4-1", "02000000", null, 206 },
				{ "xplan40/BPlan004_4-0.zip", "BPlan004_4-0", "02000000", null, 64 },
				{ "xplan50/BP2070.zip", "BP2070", "4011000", null, 314 },
				{ "xplan50/BP2135.zip", "Bebauungsplan 2135", "4011000", "2135", 241 },
				{ "xplan50/FPlan.zip", "FPlan Bad Liebenwerda", "12062024", null, 3602 },
				{ "xplan50/LA22.zip", "Bebauungsplan LA 22", "02000000", "LA 22", 1349 },
				{ "xplan50/LA67.zip", "Bebauungsplan LA 22", "1234567", "LA 22", 146 },
				{ "xplan51/BP2070.zip", "BP2070", "4011000", null, 314 },
				{ "xplan51/BP2135.zip", "Bebauungsplan 2135", "4011000", "2135", 241 },
				{ "xplan51/FPlan.zip", "FPlan Bad Liebenwerda", "12062024", null, 3602 },
				{ "xplan51/LA22.zip", "Bebauungsplan LA 22", "02000000", "LA 22", 1349 },
				{ "xplan51/LA67.zip", "Bebauungsplan LA 22", "1234567", "LA 22", 146 } });
	}

	public ParameterizedXPlanGmlParserTest(String resourceUnderTest, String expectedPlanName, String expectedPlanGz,
			String expectedPlanNumber, int expectedNumberOfFeatures) {
		this.resourceUnderTest = resourceUnderTest;
		this.expectedPlanName = expectedPlanName;
		this.expectedPlanGz = expectedPlanGz;
		this.expectedPlanNumber = expectedPlanNumber;
		this.expectedNumberOfFeatures = expectedNumberOfFeatures;
	}

	@Test
	public void testRetrieveGeometricallyValidXPlanFeatures() throws Exception {
		XPlanArchive archive = getTestArchive(resourceUnderTest);
		XPlanFeatureCollection fc = XPlanGmlParserBuilder.newBuilder().build().parseXPlanFeatureCollection(archive);
		if (expectedPlanName == null)
			assertThat(fc.getPlanName(), containsString("Unbenannter XPlan"));
		else
			assertThat(fc.getPlanName(), is(expectedPlanName));
		if (expectedPlanGz == null)
			assertNull(fc.getPlanGkz());
		else
			assertThat(fc.getPlanGkz(), is(expectedPlanGz));
		if (expectedPlanNumber == null)
			assertNull(fc.getPlanNummer());
		else
			assertThat(fc.getPlanNummer(), is(expectedPlanNumber));

		assertThat(fc.getFeatures().size(), is(expectedNumberOfFeatures));
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

}
