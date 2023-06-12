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

import de.latlon.xplan.validator.report.html.HtmlReportGenerator;
import de.latlon.xplan.validator.report.pdf.PdfReportGenerator;
import de.latlon.xplan.validator.report.shapefile.ShapefileGenerator;
import de.latlon.xplan.validator.report.xml.XmlReportGenerator;
import de.latlon.xplan.validator.web.shared.ArtifactType;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static de.latlon.xplan.validator.web.shared.ArtifactType.HTML;
import static org.apache.commons.io.IOUtils.copy;

/**
 * Generates an archive the {@link ValidatorReport} as XMl, HTML and PDF
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class ReportWriter {

	private static final Logger LOG = LoggerFactory.getLogger(ReportWriter.class);

	private static final String SHAPES = "shapes";

	public static final String ERROR_LOG_FILENAME = "error.log";

	private final XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();

	private final PdfReportGenerator pdfGenerator = new PdfReportGenerator();

	private final HtmlReportGenerator htmlGenerator = new HtmlReportGenerator();

	private final ShapefileGenerator shapefileGenerator = new ShapefileGenerator();

	/**
	 * Writes all artefacts (XML, HTML and PDF as well as shp) into the passed directory.
	 * @param report the report to write, never <code>null</code>
	 * @param targetDirectory the directory to put the archive in, never <code>null</code>
	 * @throws ReportGenerationException if an exception occurred during writing the
	 * reports or zip archive
	 */
	public void writeArtefacts(ValidatorReport report, Path targetDirectory) {
		List<String> failures = new ArrayList<>();
		addXmlEntry(report, targetDirectory, failures);
		addHtmlEntry(report, targetDirectory, failures);
		addPdfEntry(report, targetDirectory, failures);
		addShapeDirectoryEntry(report, targetDirectory, failures);

		addFailureLog(failures, targetDirectory);
	}

	public Path retrieveHtmlReport(String validationName, Path sourceDirectory) {
		return retrieveArtifactFile(sourceDirectory, validationName, HTML);
	}

	public void writeZipWithArtifacts(OutputStream outputStream, String validationName, List<ArtifactType> artifacts,
			Path sourceDirectory) throws IOException {
		try (ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
			for (ArtifactType artifactType : artifacts) {
				addZipEntry(artifactType, validationName, zipOutputStream, sourceDirectory);
			}
			Path errorlog = sourceDirectory.resolve(ERROR_LOG_FILENAME);
			if (Files.isRegularFile(errorlog)) {
				addArtifact(zipOutputStream, errorlog);
			}
		}
	}

	private void addPdfEntry(ValidatorReport report, Path directoryToCreateZip, List<String> failures) {
		String validationName = report.getValidationName();
		Path pdfFile = directoryToCreateZip.resolve(validationName + ".pdf");
		try (OutputStream outputStream = Files.newOutputStream(pdfFile)) {
			pdfGenerator.createPdfReport(report, outputStream);
		}
		catch (Exception e) {
			failures.add(e.getMessage());
		}
	}

	private void addXmlEntry(ValidatorReport report, Path directoryToCreateZip, List<String> failures) {
		String validationName = report.getValidationName();
		Path xmlFile = directoryToCreateZip.resolve(validationName + ".xml");
		try (OutputStream outputStream = Files.newOutputStream(xmlFile)) {
			xmlReportGenerator.generateXmlReport(report, outputStream);
		}
		catch (Exception e) {
			failures.add(e.getMessage());
			LOG.error("XML Entry of the validtion report could not be created.", e);
		}

	}

	private void addHtmlEntry(ValidatorReport report, Path directoryToCreateZip, List<String> failures) {
		String validationName = report.getValidationName();
		Path htmlFile = directoryToCreateZip.resolve(validationName + ".html");
		try (OutputStream outputStream = Files.newOutputStream(htmlFile)) {
			htmlGenerator.generateHtmlReport(report, outputStream);
		}
		catch (Exception e) {
			failures.add(e.getMessage());
		}
	}

	private void addShapeDirectoryEntry(ValidatorReport report, Path directoryToCreateZip, List<String> failures) {
		String validationName = report.getValidationName();
		try {
			if (shapefileGenerator.hasBadGeometry(report)) {
				Path directoryToCreateShapes = directoryToCreateZip.resolve("shapes");
				Files.createDirectory(directoryToCreateShapes);
				shapefileGenerator.generateReport(report, validationName, directoryToCreateShapes);
			}
		}
		catch (Exception e) {
			failures.add(e.getMessage());
		}
	}

	private void addFailureLog(List<String> failures, Path directoryToCreateZip) {
		if (!failures.isEmpty()) {
			Path errorlog = directoryToCreateZip.resolve(ERROR_LOG_FILENAME);
			try (OutputStream outputStream = Files.newOutputStream(errorlog)) {
				String errorlogContent = String.join("\n", failures);
				IOUtils.write(errorlogContent, outputStream, Charset.defaultCharset());
			}
			catch (IOException e) {
				LOG.error("Could not write error.log", e);
			}
		}
	}

	private void addZipEntry(ArtifactType artifactType, String validationName, ZipOutputStream zipOutputStream,
			Path sourceDirectory) throws IOException {
		switch (artifactType) {
			case SHP:
				addShpArtifact(zipOutputStream, sourceDirectory);
				break;
			case HTML:
			case XML:
			case PDF:
				addSimpleArtifact(artifactType, validationName, zipOutputStream, sourceDirectory);
				break;
			default:
				break;
		}
	}

	private void addSimpleArtifact(ArtifactType artifactType, String validationName, ZipOutputStream zipOutputStream,
			Path sourceDirectory) throws IOException {
		Path artifactFile = retrieveArtifactFile(sourceDirectory, validationName, artifactType);
		if (Files.exists(artifactFile)) {
			addArtifact(zipOutputStream, artifactFile);
		}
	}

	private void addShpArtifact(ZipOutputStream zipOutputStream, Path sourceDirectory) throws IOException {
		Path shapesDirectory = sourceDirectory.resolve(SHAPES);
		if (Files.exists(shapesDirectory)) {
			addDirToArchive(zipOutputStream, shapesDirectory);
		}
	}

	private void addDirToArchive(ZipOutputStream zipOutputStream, Path shapeDirectory) throws IOException {
		DirectoryStream<Path> paths = Files.newDirectoryStream(shapeDirectory);
		Iterator<Path> pathIterator = paths.iterator();
		while (pathIterator.hasNext()) {
			Path file = pathIterator.next();
			addArtifact(zipOutputStream, file);
		}
	}

	private void addArtifact(ZipOutputStream zipOutputStream, Path artifactFile) throws IOException {
		ZipEntry entry = new ZipEntry(artifactFile.getFileName().toString());
		zipOutputStream.putNextEntry(entry);
		try (InputStream input = Files.newInputStream(artifactFile)) {
			copy(input, zipOutputStream);
			zipOutputStream.closeEntry();
		}
	}

	public Path retrieveArtifactFile(Path sourceDirectory, String validationName, ArtifactType artifactType) {
		String suffix = artifactType.name().toLowerCase();
		return sourceDirectory.resolve(validationName + "." + suffix);
	}

}
