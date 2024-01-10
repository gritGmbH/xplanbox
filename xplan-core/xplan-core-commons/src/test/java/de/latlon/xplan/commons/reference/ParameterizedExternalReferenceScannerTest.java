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
package de.latlon.xplan.commons.reference;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.deegree.feature.FeatureCollection;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class ParameterizedExternalReferenceScannerTest {

	static List<Object[]> data() {
		return Arrays.asList(new Object[][] { { "xplan40/BPlan004_4-0.zip", 2, 1 }, { "xplan41/BP2070.zip", 0, 0 },
				{ "xplan41/Demo.zip", 2, 0 }, { "xplan41/BPlan001_4-1.zip", 1, 1 },
				{ "xplan50/BPlan004_5-0.zip", 2, 1 }, { "xplan51/BPlan002_5-1.zip", 2, 2 },
				{ "xplan51/BPlan002_5-1_rasterBasisAlsRefScan.zip", 1, 1 } });
	}

	@ParameterizedTest
	@MethodSource("data")
	void testValidationOfSingleRule(String resourceUnderTest, int noOfExternalRefs, int noOfRasterPlanBaseScans)
			throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection(resourceUnderTest);
		ExternalReferenceInfo referenceInfo = new ExternalReferenceScanner().scan(fc);
		assertEquals(noOfExternalRefs, referenceInfo.getAllReferences().size());
		assertEquals(noOfRasterPlanBaseScans, referenceInfo.getRasterPlanBaseScans().size());
	}

	private FeatureCollection getMainFileAsFeatureCollection(String name) throws Exception {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip(name,
				ResourceAccessor.readResourceStream(name));
		return XPlanGmlParserBuilder.newBuilder().build().parseFeatureCollection(archive);
	}

}
