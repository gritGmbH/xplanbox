/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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

import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.report.html.HtmlReportGenerator;
import de.latlon.xplan.validator.report.pdf.PdfReportGenerator;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Generates an archive the {@link ValidatorReport} as XMl, HTML and PDF
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 */
public class ReportArchiveGenerator {

	private final PdfReportGenerator pdfGenerator = new PdfReportGenerator();

	private final HtmlReportGenerator htmlGenerator = new HtmlReportGenerator();

	private final ValidatorConfiguration validatorConfiguration;

	/**
	 * @param validatorConfiguration configuration of validator, never <code>null</code>
	 */
	public ReportArchiveGenerator(ValidatorConfiguration validatorConfiguration) {
		checkParameters(validatorConfiguration);
		this.validatorConfiguration = validatorConfiguration;
	}

	/**
	 * Creates a zip Archive containing the {@link ValidatorReport} as XML, HTML and PDF
	 * @param report the report to write, never <code>null</code>
	 * @param validationName the name of the validation run, never <code>null</code>
	 * @return the validation report directory, never <code>null</code>
	 * @throws ReportGenerationException if an exception occurred during writing the
	 * reports or zip archive
	 */
	public Path generateZipArchive(ValidatorReport report, String validationName) throws ReportGenerationException {
		Path validationReportDirectory = validatorConfiguration.getValidationReportDirectory();
		Path outputFile = validationReportDirectory.resolve(validationName + ".zip");
		try (OutputStream fileOutStream = Files.newOutputStream(outputFile);
				ZipOutputStream zipOutStream = new ZipOutputStream(fileOutStream)) {
			addHtmlEntry(report, validationName, zipOutStream);
			addPdfEntry(report, validationName, zipOutStream);
			return validationReportDirectory;
		}
		catch (IOException e) {
			throw new ReportGenerationException(e);
		}
	}

	private void addPdfEntry(ValidatorReport report, String validationName, ZipOutputStream zipOutStream)
			throws IOException, ReportGenerationException {
		ZipEntry pdfEntry = new ZipEntry(validationName + ".pdf");
		zipOutStream.putNextEntry(pdfEntry);
		pdfGenerator.createPdfReport(report, zipOutStream);
		zipOutStream.closeEntry();
	}

	private void addHtmlEntry(ValidatorReport report, String validationName, ZipOutputStream zipOutStream)
			throws IOException, ReportGenerationException {
		ZipEntry htmlEntry = new ZipEntry(validationName + ".html");
		zipOutStream.putNextEntry(htmlEntry);
		htmlGenerator.generateHtmlReport(report, zipOutStream);
		zipOutStream.closeEntry();
	}

	private void checkParameters(ValidatorConfiguration validatorConfiguration) {
		if (validatorConfiguration == null)
			throw new IllegalArgumentException("validatorConfiguration must not be null!");
	}

}
