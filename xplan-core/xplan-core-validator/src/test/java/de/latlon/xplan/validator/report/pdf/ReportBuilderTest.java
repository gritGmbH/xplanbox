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
package de.latlon.xplan.validator.report.pdf;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ReportBuilderTest {

	@Rule
	public TemporaryFolder tmpFolder = new TemporaryFolder();

	// @Ignore("Make a visibility check if the report looks like expected!")
	@Test
	public void testCreateReportAsPdf() throws Exception {
		PdfReportGenerator reportBuilder = new PdfReportGenerator();

		File file = tmpFolder.newFile();
		OutputStream os = new FileOutputStream(file);
		reportBuilder.createPdfReport(createReport(), os);
		os.close();
		assertThat(Files.size(file.toPath()), is(not(0)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateReportAsPdfWithNullReport() throws Exception {
		PdfReportGenerator reportBuilder = new PdfReportGenerator();
		reportBuilder.createPdfReport(null, createSimpleOutputStream());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateReportAsPdfWithNullStream() throws Exception {
		PdfReportGenerator reportBuilder = new PdfReportGenerator();
		reportBuilder.createPdfReport(createReport(), null);
	}

	private ValidatorReport createReport() {
		ValidatorReport report = new ValidatorReport();

		report.setSemanticValidatorResult(createSemanticResult());
		report.setSyntacticValidatorResult(createSyntacticResult());
		report.setGeometricValidatorResult(createGeometricResult());
		return report;
	}

	private SemanticValidatorResult createSemanticResult() {
		SemanticValidatorResult semanticResult = new SemanticValidatorResult();
		semanticResult.setValid(true);
		for (int i = 0; i < 20; i++) {
			if (i == 8 || i == 2)
				semanticResult.addRule("Name" + i,
						"HinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweis"
								+ i,
						Collections.emptyList());
			else
				semanticResult.addRule("Name" + i, "Hinweis" + i, Collections.emptyList());
		}
		return semanticResult;
	}

	private SyntacticValidatorResult createSyntacticResult() {
		List<String> messages = new ArrayList<String>();
		addMessages("Syntactic ", messages);
		ValidatorDetail detail = new ValidatorDetail("detailsHint");
		return new SyntacticValidatorResult(messages, false, detail);
	}

	private GeometricValidatorResult createGeometricResult() {
		List<String> warnings = new ArrayList<String>();
		addMessages("Geom ", warnings);
		List<String> errors = new ArrayList<String>();
		addMessages("Geom ", warnings);
		addMessages("Geom ", errors);
		List<BadGeometry> badGeometries = new ArrayList<>();
		return new GeometricValidatorResult(warnings, errors, badGeometries, null, true);
	}

	private void addMessages(String prefix, List<String> allMessages) {
		for (int i = 0; i < 20; i++) {
			allMessages.add(prefix + i);
		}
	}

	private OutputStream createSimpleOutputStream() {
		return new ByteArrayOutputStream();
	}

}
