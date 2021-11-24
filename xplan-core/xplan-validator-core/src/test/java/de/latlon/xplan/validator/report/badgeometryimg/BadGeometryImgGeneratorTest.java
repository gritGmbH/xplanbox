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
package de.latlon.xplan.validator.report.badgeometryimg;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import org.deegree.feature.types.AppSchema;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_OPTIONS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @author Florian Bingel
 * @version $Revision: $, $Date: $
 */
public class BadGeometryImgGeneratorTest {

	@Test
	public void testImageGeneration() throws Exception {
		ValidatorReport validatorReport = createReportFromSampleArchive();
		BadGeometryImgGenerator badGeometryImgGenerator = new BadGeometryImgGenerator();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		badGeometryImgGenerator.generateReport(validatorReport, byteArrayOutputStream);

		assertTrue(byteArrayOutputStream.size() > 0);

		InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		BufferedImage image = javax.imageio.ImageIO.read(byteArrayInputStream);
		assertNotNull(image);

		byteArrayOutputStream.close();
		byteArrayInputStream.close();
	}

	@Test
	public void testHasBadGeometryFromSampleArchiveSingleResult() throws Exception {
		ValidatorReport report = createReportFromSampleArchive();
		BadGeometryImgGenerator badGeometryImgGenerator = new BadGeometryImgGenerator();
		boolean hasBadGeometry = badGeometryImgGenerator.hasBadGeometry(report);

		assertThat(hasBadGeometry, is(true));
	}

	@Test
	public void testHasBadGeometryWithBadGeometry() throws Exception {
		ValidatorReport report = createReportWithBadGeometries();
		BadGeometryImgGenerator badGeometryImgGenerator = new BadGeometryImgGenerator();
		boolean hasBadGeometry = badGeometryImgGenerator.hasBadGeometry(report);

		assertThat(hasBadGeometry, is(true));
	}

	@Test
	public void testHasBadGeometryWithoutBadGeometry() throws Exception {
		ValidatorReport report = createReportWithoutBadGeometries();
		BadGeometryImgGenerator badGeometryImgGenerator = new BadGeometryImgGenerator();
		boolean hasBadGeometry = badGeometryImgGenerator.hasBadGeometry(report);

		assertThat(hasBadGeometry, is(false));
	}

	private ValidatorReport createReportFromSampleArchive() throws Exception {
		XPlanArchive archive = getTestArchive("xplan41/FPlan.zip");
		GeometricValidatorResult result = (GeometricValidatorResult) validateGeometryAndReturnReport(archive,
				SKIP_OPTIONS);
		ValidatorReport validatorReport = new ValidatorReport();
		validatorReport.setGeometricValidatorResult(result);
		return validatorReport;
	}

	private ValidatorReport createReportWithoutBadGeometries() {
		return createReport(mockResultWithoutBadGeometry(), mockResultWithoutBadGeometry());
	}

	private ValidatorReport createReportWithBadGeometries() {
		return createReport(mockResultWithBadGeometry(), mockResultWithoutBadGeometry());
	}

	private ValidatorReport createReport(GeometricValidatorResult... result) {
		ValidatorReport validatorReport = new ValidatorReport();
		validatorReport.setGeometricValidatorResult(result[0]);
		return validatorReport;
	}

	private GeometricValidatorResult mockResultWithBadGeometry() {
		GeometricValidatorResult resultMock = mock(GeometricValidatorResult.class);
		BadGeometry badGeometry = new BadGeometry();
		Mockito.when(resultMock.getBadGeometries()).thenReturn(Collections.singletonList(badGeometry));
		return resultMock;
	}

	private GeometricValidatorResult mockResultWithoutBadGeometry() {
		GeometricValidatorResult resultMock = mock(GeometricValidatorResult.class);
		Mockito.when(resultMock.getBadGeometries()).thenReturn(Collections.<BadGeometry>emptyList());
		return resultMock;
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

	private ValidatorResult validateGeometryAndReturnReport(XPlanArchive archive, List<ValidationOption> voOptions)
			throws ValidatorException {
		XPlanVersion version = archive.getVersion();
		XPlanAde ade = archive.getAde();
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, ade);
		return (new GeometricValidatorImpl()).validateGeometry(archive, archive.getCrs(), schema, true, voOptions)
				.getValidatorResult();
	}

}
