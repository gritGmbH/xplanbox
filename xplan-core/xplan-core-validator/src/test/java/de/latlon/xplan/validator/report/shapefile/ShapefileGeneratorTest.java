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
package de.latlon.xplan.validator.report.shapefile;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import org.deegree.feature.types.AppSchema;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.StreamSupport;

import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_OPTIONS;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Florian Bingel
 * @version $Revision: $, $Date: $
 */
public class ShapefileGeneratorTest {

	@Rule
	public TemporaryFolder tempDir = new TemporaryFolder();

	@Test
	public void testShapeGeneration() throws Exception {
		ValidatorReport validatorReport = createArchive();
		Path directoryToCreateShapes = Paths.get(tempDir.newFolder().toURI());

		ShapefileGenerator shapefileGenerator = new ShapefileGenerator();
		shapefileGenerator.generateReport(validatorReport, "testShapeGenerator", directoryToCreateShapes);

		assertThat(directoryToCreateShapes, containsFile(".shp", 4));
		assertThat(directoryToCreateShapes, containsFile(".shx", 4));
		assertThat(directoryToCreateShapes, containsFile(".dbf", 4));
		assertThat(directoryToCreateShapes, containsFile(".cpg", 4));
	}

	@Test
	public void testGeneratorHasBadGeometry() throws Exception {
		ValidatorReport validatorReport = createArchive();
		ShapefileGenerator shapefileGenerator = new ShapefileGenerator();
		assertTrue(shapefileGenerator.hasBadGeometry(validatorReport));
	}

	private BaseMatcher<Path> containsFile(final String fileEnding, final int numberOfOccurences) {
		return new BaseMatcher<>() {
			@Override
			public boolean matches(Object item) {
				Path directory = (Path) item;
				try {
					DirectoryStream<Path> paths = Files.newDirectoryStream(directory, "*" + fileEnding);
					return StreamSupport.stream(paths.spliterator(), false).count() == numberOfOccurences;
				}
				catch (IOException e) {
					throw new RuntimeException(e);
				}

			}

			@Override
			public void describeTo(Description description) {
				String text = format("Directory must contain exactly %s files with ending %s.", numberOfOccurences,
						fileEnding);
				description.appendText(text);
			}
		};
	}

	private ValidatorReport createArchive() throws Exception {
		XPlanArchive archive = getTestArchive("xplan41/FPlan.zip");
		GeometricValidatorResult result = (GeometricValidatorResult) validateGeometryAndReturnReport(archive,
				SKIP_OPTIONS);
		ValidatorReport validatorReport = new ValidatorReport();
		validatorReport.setGeometricValidatorResult(result);
		return validatorReport;
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, getClass().getResourceAsStream("/testdata/" + name));
	}

	private ValidatorResult validateGeometryAndReturnReport(XPlanArchive archive, List<ValidationOption> voOptions)
			throws ValidatorException {
		XPlanVersion version = archive.getVersion();
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version);
		return (new GeometricValidatorImpl()).validateGeometry(archive, archive.getCrs(), schema, true, voOptions);
	}

}
