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
import de.latlon.xplan.validator.web.shared.ValidationOption;
import org.deegree.feature.types.AppSchema;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_FLAECHENSCHLUSS_OPTION;
import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_OPTIONS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;

/**
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 */
public class GeometricValidatorImplTest {

	@Ignore("TODO: test plan is required (with only few features)")
	@Test
	public void testValidateGeometryWithNullVoOptions() throws Exception {
		XPlanArchive archive = getTestArchive("xplan51/BP2070.zip");
		ValidatorResult report = validateGeometryAndReturnReport(archive, null);
		assertNotEquals(null, report);
	}

	@Test
	public void testValidateGeometryWithBrokenGeometry() throws Exception {
		XPlanArchive archive = getTestArchive("xplan41/Eidelstedt_4_V4-broken-geometry.zip");
		ValidatorResult report = validateGeometryAndReturnReport(archive, SKIP_OPTIONS);
		GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
		int numberOfErrors = geometricReport.getErrors().size();
		int numberOfWarnings = geometricReport.getWarnings().size();
		int numberOfBadGeometries = geometricReport.getBadGeometries().size();

		assertThat(report.isValid(), is(false));
		assertThat(numberOfErrors, is(1));
		assertThat(numberOfWarnings, is(0));
		assertThat(numberOfBadGeometries, is(0));
	}

	@Test
	public void testValidateGeometryWithInvalidFlaechenschluss_skipped() throws Exception {
		XPlanArchive archive = getTestArchive("xplan51/BP2070.zip");
		ValidatorResult report = validateGeometryAndReturnReport(archive, SKIP_OPTIONS);
		GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
		int numberOfErrors = geometricReport.getErrors().size();

		assertThat(report.isValid(), is(false));
		assertThat(numberOfErrors, is(6));
	}

	@Ignore("TODO: test plan is required (with only few features)")
	@Test
	public void testValidateGeometryWithInvalidFlaechenschluss_notskipped() throws Exception {
		XPlanArchive archive = getTestArchive("xplan51/BP2070.zip");
		List<ValidationOption> voOptions = Collections
			.singletonList(new ValidationOption(SKIP_FLAECHENSCHLUSS_OPTION, Boolean.toString(false)));
		ValidatorResult report = validateGeometryAndReturnReport(archive, voOptions);
		GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
		int numberOfErrors = geometricReport.getErrors().size();

		assertThat(report.isValid(), is(false));
		assertThat(numberOfErrors, is(12));
	}

	@Test
	public void testValidateGeometryWithInteriorRing_ValidOrientation() throws Exception {
		XPlanArchive archive = getTestArchive(getClass().getResourceAsStream("geometryOrientationValid.gml"));
		GeometricValidatorResult report = (GeometricValidatorResult) validateGeometryAndReturnReport(archive,
				SKIP_OPTIONS);

		assertThat(report.isValid(), is(true));
		assertThat(report.getErrors().size(), is(0));
		assertThat(report.getWarnings().size(), is(0));
		assertThat(report.getBadGeometries().size(), is(0));
	}

	@Test
	public void testValidateGeometryWithInteriorRing_InvalidOrientation() throws Exception {
		XPlanArchive archive = getTestArchive(getClass().getResourceAsStream("geometryOrientationInvalid.gml"));
		GeometricValidatorResult report = (GeometricValidatorResult) validateGeometryAndReturnReport(archive,
				SKIP_OPTIONS);

		assertThat(report.isValid(), is(false));
		assertThat(report.getErrors().size(), is(2));
		assertThat(report.getWarnings().size(), is(0));
		assertThat(report.getBadGeometries().size(), is(2));
	}

	@Test
	public void testValidateGeometry_InvalidSelfintersectionDetected() throws Exception {
		XPlanArchive archive = getTestArchive(getClass().getResourceAsStream("invalidSelfintersectionDeteced.gml"));
		GeometricValidatorResult report = (GeometricValidatorResult) validateGeometryAndReturnReport(archive,
				SKIP_OPTIONS);

		assertThat(report.isValid(), is(true));
		assertThat(report.getErrors().size(), is(0));
		assertThat(report.getWarnings().size(), is(0));
		assertThat(report.getBadGeometries().size(), is(0));
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, getClass().getResourceAsStream("/testdata/" + name));
	}

	private XPlanArchive getTestArchive(InputStream inputStream) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromGml("test", inputStream);
	}

	private ValidatorResult validateGeometryAndReturnReport(XPlanArchive archive, List<ValidationOption> voOptions)
			throws ValidatorException {
		XPlanVersion version = archive.getVersion();
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version);
		return new GeometricValidatorImpl().validateGeometry(archive, archive.getCrs(), schema, true, voOptions);
	}

}
