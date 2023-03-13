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

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(JUnitParamsRunner.class)
public class ParameterizedExternalReferenceScannerTest {

	@FileParameters("src/test/resources/de/latlon/xplan/commons/reference/externalReferenceScanner-test-input.csv")
	@Test
	public void testValidationOfSingleRule(String resourceUnderTest, int externalRefs, int rasterPlanBaseScans)
			throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection(resourceUnderTest);
		ExternalReferenceInfo referenceInfo = new ExternalReferenceScanner().scan(fc);
		assertEquals(externalRefs, referenceInfo.getAllReferences().size());
		assertEquals(rasterPlanBaseScans, referenceInfo.getRasterPlanBaseScans().size());

	}

	private FeatureCollection getMainFileAsFeatureCollection(String name) throws Exception {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip(name,
				ResourceAccessor.readResourceStream(name));
		return XPlanGmlParserBuilder.newBuilder().build().parseFeatureCollection(archive);
	}

}
