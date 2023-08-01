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
package de.latlon.xplan.validator.report;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import org.deegree.commons.uom.Measure;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.geometry.GeometryFactory;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static de.latlon.xplan.validator.web.shared.ArtifactType.HTML;
import static org.deegree.cs.persistence.CRSManager.lookup;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ReportWriterTest {

	private static final String PLAN_NAME = "planName";

	private static final String VALIDATION_NAME = "validationName";

	private static final String FAILURE = "Failure";

	private ReportWriter reportWriter = new ReportWriter();

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private Path targetDirectory;

	@Before
	public void createTargetDirectory() throws Exception {
		targetDirectory = Paths.get(temporaryFolder.newFolder("ReportWriterTest").toURI());
	}

	@Test
	public void testWriteArtefacts_ShouldHaveSubdirectoryWithArtifacts() throws Exception {
		reportWriter.writeArtefacts(createReport(), targetDirectory);

		assertThat(targetDirectory, containsFile(VALIDATION_NAME + ".html"));
		assertThat(targetDirectory, containsFile(VALIDATION_NAME + ".pdf"));
		assertThat(targetDirectory, containsFile(VALIDATION_NAME + ".xml"));
		assertThat(targetDirectory, containsDirectory("shapes"));
	}

	@Test
	public void testWriteArtefacts_WithFailure() {
		reportWriter.writeArtefacts(createReportThrowingFailure(), targetDirectory);

		assertThat(targetDirectory, containsFile("error.log"));
	}

	@Test
	public void testRetrieveHtmlReport_ShouldExistWithCorrectName() throws Exception {
		reportWriter.writeArtefacts(createReport(), targetDirectory);

		Path htmlReport = reportWriter.retrieveHtmlReport(VALIDATION_NAME, targetDirectory);

		assertThat(Files.exists(htmlReport), is(true));
		assertThat(htmlReport.getFileName().toString(), is(VALIDATION_NAME + ".html"));
	}

	@Test
	public void testWriteZipWithArtifacts_ShouldContainHtml() throws Exception {
		reportWriter.writeArtefacts(createReport(), targetDirectory);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		reportWriter.writeZipWithArtifacts(outputStream, VALIDATION_NAME, Collections.singletonList(HTML),
				targetDirectory);
		ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(outputStream.toByteArray()));
		assertThat(zipInputStream, hasEntryWithNameAndSize(VALIDATION_NAME + ".html", 1));
	}

	@Test
	public void testWriteZipWithArtifacts_WithFailure() throws Exception {
		reportWriter.writeArtefacts(createReportThrowingFailure(), targetDirectory);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		reportWriter.writeZipWithArtifacts(outputStream, VALIDATION_NAME, Collections.singletonList(HTML),
				targetDirectory);
		ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(outputStream.toByteArray()));
		assertThat(zipInputStream, hasEntryWithNameAndSize("error.log", 2));
	}

	private static ValidatorReport createReport() throws UnknownCRSException {
		List<BadGeometry> badGeometries = new ArrayList<>();
		ICRS crs = lookup("epsg:4326");
		String uomURI = "m";
		Measure measure = new Measure(BigDecimal.TEN, uomURI);
		badGeometries
			.add(new BadGeometry(new GeometryFactory().createPoint("id", 20, 10, crs).getBuffer(measure), "Fehler"));
		GeometricValidatorResult result = new GeometricValidatorResult(Collections.<String>emptyList(),
				Collections.<String>emptyList(), badGeometries, crs, false);
		ValidatorReport report = new ValidatorReport();
		report.setGeometricValidatorResult(result);
		report.setPlanNames(Collections.singletonList(PLAN_NAME));
		report.setValidationName(VALIDATION_NAME);
		return report;
	}

	private ValidatorReport createReportThrowingFailure() {
		ValidatorReport report = mock(ValidatorReport.class);
		when(report.getPlanNames()).thenReturn(Collections.singletonList(PLAN_NAME));
		when(report.getValidationName()).thenReturn(VALIDATION_NAME);
		when(report.getGeometricValidatorResult()).thenThrow(new IllegalArgumentException(FAILURE));
		return report;
	}

	private Matcher<? super Path> containsFile(final String fileName) {
		return new TypeSafeMatcher<>() {

			@Override
			public boolean matchesSafely(Path directory) {
				return Files.isRegularFile(directory.resolve(fileName));
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Directory must contain a file " + fileName);
			}
		};
	}

	private Matcher<? super Path> containsDirectory(final String directoryName) {
		return new TypeSafeMatcher<>() {

			@Override
			public boolean matchesSafely(Path directory) {
				return Files.isDirectory(directory.resolve(directoryName));
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Directory must contain a directory " + directoryName);
			}
		};
	}

	private Matcher<ZipInputStream> hasEntryWithNameAndSize(final String expectedName, final int expectedSize) {
		return new TypeSafeMatcher<>() {
			@Override
			protected boolean matchesSafely(ZipInputStream zip) {
				try {
					boolean hasExpectedName = false;
					int numberOfEntries = 0;
					ZipEntry nextEntry = zip.getNextEntry();
					while (nextEntry != null) {
						if (expectedName.equals(nextEntry.getName())) {
							hasExpectedName = true;
						}
						numberOfEntries++;
						nextEntry = zip.getNextEntry();
					}
					return expectedSize == numberOfEntries && hasExpectedName;
				}
				catch (IOException e) {
					throw new IllegalArgumentException("zip cannot be read");
				}
			}

			@Override
			public void describeTo(Description description) {
				description
					.appendText("Zip must contain " + expectedSize + "entries and an entry with name " + expectedName);
			}
		};
	}

}
