/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.validator.geometric;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParser;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorResult;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.deegree.feature.types.AppSchema;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_OPTIONS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(JUnitParamsRunner.class)
public class ParameterizedGeometricValidatorImplTest {

	private static final String NULL = "null";

	@FileParameters("src/test/resources/de/latlon/xplan/validator/geometric/geometricValidatorImplTest-validateGeometry-input.csv")
	@Test
	public void testValidateGeometry(String testResource, boolean expectedValidationResult, int expectedNumberOfErrors,
			int expectedNumberOfWarnings, int expectedNumberOBadGeometries) throws Exception {
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

	@FileParameters("src/test/resources/de/latlon/xplan/validator/geometric/geometricValidatorImplTest-retrieveFeatures-input.csv")
	@Test
	public void testRetrieveGeometricallyValidXPlanFeatures(String testResource, String expectedPlanName,
			String expectedPlanGz, String expectedPlanNumber, int expectedNumberOfFeatures) throws Exception {
		XPlanArchive archive = getTestArchive(testResource);
		XPlanFeatureCollection fc = new XPlanGmlParser().parseXPlanFeatureCollection(archive);
		if (!NULL.equals(expectedPlanName))
			assertThat(fc.getPlanName(), is(expectedPlanName));
		if (NULL.equals(expectedPlanGz))
			assertThat(fc.getPlanGkz(), is(nullValue()));
		else
			assertThat(fc.getPlanGkz(), is(expectedPlanGz));
		if (NULL.equals(expectedPlanNumber))
			assertThat(fc.getPlanNummer(), is(nullValue()));
		else
			assertThat(fc.getPlanNummer(), is(expectedPlanNumber));
		assertThat(fc.getFeatures().size(), is(expectedNumberOfFeatures));
	}

	private ValidatorResult validateGeometryAndReturnReport(XPlanArchive archive) throws ValidatorException {
		XPlanVersion version = archive.getVersion();
		XPlanAde ade = archive.getAde();
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, ade);
		return new GeometricValidatorImpl().validateGeometry(archive, archive.getCrs(), schema, true, SKIP_OPTIONS);
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

}
