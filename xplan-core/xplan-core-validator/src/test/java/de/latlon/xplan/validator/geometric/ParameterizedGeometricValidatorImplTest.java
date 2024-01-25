/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorResult;
import org.deegree.feature.types.AppSchema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_OPTIONS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(Parameterized.class)
public class ParameterizedGeometricValidatorImplTest {

	private final String testResource;

	private final boolean expectedValidationResult;

	private final int expectedNumberOfErrors;

	private final int expectedNumberOfWarnings;

	private final int expectedNumberOBadGeometries;

	@Parameterized.Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] { { "xplan41/BP2070.zip", false, 6, 0, 13 },
				{ "xplan41/BP2135.zip", true, 0, 0, 0 }, { "xplan41/Demo.zip", false, 1, 0, 2 },
				{ "xplan41/Eidelstedt_4_V4.zip", true, 0, 0, 0 }, { "xplan41/FPlan.zip", false, 141, 0, 347 },
				{ "xplan41/LA22.zip", false, 24, 0, 55 }, { "xplan41/LA67.zip", false, 7, 0, 16 },
				{ "xplan41/BPlan001_4-1.zip", true, 0, 0, 0 }, { "xplan40/BPlan004_4-0.zip", true, 0, 0, 0 },
				{ "xplan41/PlanWithComplexCurve.zip", true, 0, 0, 0 }, { "xplan50/BP2070.zip", false, 6, 0, 13 },
				{ "xplan50/BP2135.zip", true, 0, 0, 0 }, { "xplan50/FPlan.zip", false, 141, 0, 347 },
				{ "xplan50/LA22.zip", false, 24, 0, 55 }, { "xplan50/LA67.zip", false, 7, 0, 16 },
				{ "xplan51/BP2070.zip", false, 6, 0, 13 }, { "xplan51/BP2135.zip", true, 0, 0, 0 },
				{ "xplan51/FPlan.zip", false, 141, 0, 347 }, { "xplan51/LA22.zip", false, 24, 0, 55 },
				{ "xplan51/LA67.zip", false, 7, 0, 16 } });
	}

	public ParameterizedGeometricValidatorImplTest(String testResource, boolean expectedValidationResult,
			int expectedNumberOfErrors, int expectedNumberOfWarnings, int expectedNumberOBadGeometries) {
		this.testResource = testResource;
		this.expectedValidationResult = expectedValidationResult;
		this.expectedNumberOfErrors = expectedNumberOfErrors;
		this.expectedNumberOfWarnings = expectedNumberOfWarnings;
		this.expectedNumberOBadGeometries = expectedNumberOBadGeometries;
	}

	@Test
	public void testValidateGeometry() throws Exception {
		XPlanArchive archive = getTestArchive(testResource);
		ValidatorResult report = validateGeometryAndReturnReport(archive);
		GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
		int numberOfErrors = geometricReport.getErrors().size();
		int numberOfWarnings = geometricReport.getWarnings().size();
		int numberOfBadGeometries = geometricReport.getBadGeometries().size();

		assertThat(report.isValid(), is(expectedValidationResult));
		assertThat(numberOfErrors, is(expectedNumberOfErrors));
		assertThat(numberOfWarnings, is(expectedNumberOfWarnings));
		assertThat(numberOfBadGeometries, is(expectedNumberOBadGeometries));
	}

	private ValidatorResult validateGeometryAndReturnReport(XPlanArchive archive) throws ValidatorException {
		XPlanVersion version = archive.getVersion();
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version);
		return new GeometricValidatorImpl().validateGeometry(archive, archive.getCrs(), schema, true, SKIP_OPTIONS);
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, getClass().getResourceAsStream("/testdata/" + name));
	}

}
