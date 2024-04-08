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

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class PdfReportGeneratorTest {

	@Test(expected = IllegalArgumentException.class)
	public void testCreateReportAsPdfWithNullReport() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfReportGenerator pdfReportGenerator = new PdfReportGenerator();
		pdfReportGenerator.createPdfReport(null, bos);
	}

	@Test
	public void testCreateReportAsPdfWithNullValidationName() throws Exception {
		ValidatorReport report = createReport(null, "PlanName");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfReportGenerator pdfReportGenerator = new PdfReportGenerator();
		pdfReportGenerator.createPdfReport(report, bos);
	}

	@Test
	public void testCreateReportAsPdfWithEmptyValidationName() throws Exception {
		ValidatorReport report = createReport("", "PlanName");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfReportGenerator pdfReportGenerator = new PdfReportGenerator();
		pdfReportGenerator.createPdfReport(report, bos);
	}

	@Test
	public void testCreateReportAsPdfWithNullPlanName() throws Exception {
		ValidatorReport report = createReport("ValName", null);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfReportGenerator pdfReportGenerator = new PdfReportGenerator();
		pdfReportGenerator.createPdfReport(report, bos);
	}

	@Test
	public void testCreateReportAsPdfWithEmptyPlanName() throws Exception {
		ValidatorReport report = createReport("ValName", "");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfReportGenerator pdfReportGenerator = new PdfReportGenerator();
		pdfReportGenerator.createPdfReport(report, bos);
	}

	@Test
	public void testCreateReportAsPdfWithResults() throws Exception {
		ValidatorReport report = createReportWithResults("Validerungtest", "test.gml");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfReportGenerator pdfReportGenerator = new PdfReportGenerator();
		pdfReportGenerator.createPdfReport(report, bos);
	}

	private ValidatorReport createReport(String validationName, String planName) {
		ValidatorReport validatorReport = new ValidatorReport();
		validatorReport.setPlanNames(Collections.singletonList(planName));
		validatorReport.setValidationName(validationName);
		validatorReport.setDate(new Date());
		validatorReport.setArchiveName(planName);
		validatorReport.setXPlanVersion(XPlanVersion.XPLAN_60);
		validatorReport.setXPlanType(XPlanType.BP_Plan);
		return validatorReport;
	}

	private ValidatorReport createReportWithResults(String validationName, String planName) {
		ValidatorReport validatorReport = createReport(validationName, planName);
		validatorReport.setPlanNames(Collections.singletonList(validationName));
		validatorReport.setValidationName(validationName);

		validatorReport.setSemanticValidatorResult(createSemanticResult());
		validatorReport.setSyntacticValidatorResult(createSyntacticResult());
		validatorReport.setGeometricValidatorResult(createGeometricResult());

		return validatorReport;
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

}
