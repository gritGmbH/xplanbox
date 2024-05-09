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
package de.latlon.xplan.validator.report.html;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.semantic.report.InvalidFeaturesResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.validator.report.ReportUtils.SkipCode.SYNTAX_ERRORS;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.xmlunit.matchers.EvaluateXPathMatcher.hasXPath;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class HtmlReportGeneratorTest {

	@Test
	public void testGenerateHtmlReport() throws Exception {
		ByteArrayOutputStream html = new ByteArrayOutputStream();
		HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

		htmlReportGenerator.generateHtmlReport(createValidationReport(), html);

		assertThat(html.toString(), hasXPath("/html/body/h1", containsString("Validierungsbericht")));
	}

	@Test
	public void testGenerateHtmlReport_WithSemanticFailures() throws Exception {
		ByteArrayOutputStream html = new ByteArrayOutputStream();
		HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

		htmlReportGenerator.generateHtmlReport(createValidatorReportWithSemanticFailures(), html);

		assertThat(html.toString(), hasXPath("/html/body/p[7]/p/ul/li[1]", containsString("2 Validierungsregeln")));
		assertThat(html.toString(),
				hasXPath("/html/body/p[7]/p/ul/li[2]", containsString("1 Validierungsregeln nicht")));
		assertThat(html.toString(), hasXPath("/html/body/p[7]/p/ul/li[3]", containsString("1 Validierungsregeln")));
	}

	@Test
	public void testGenerateHtmlReport_WithSyntacticDetailHint() throws Exception {
		ByteArrayOutputStream html = new ByteArrayOutputStream();
		HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

		htmlReportGenerator.generateHtmlReport(createValidatorReportWithSyntacticDetailHint(), html);

		assertThat(html.toString(), hasXPath("/html/body/p/div", containsString("detailsHint")));
	}

	@Test
	public void testGenerateHtmlReport_WithGeometricWarnings() throws Exception {
		ByteArrayOutputStream html = new ByteArrayOutputStream();
		HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

		htmlReportGenerator.generateHtmlReport(createValidatorReportWithGeometricWarnings(), html);

		assertThat(html.toString(), hasXPath("/html/body/p[7]/p[2]", containsString("1 Warnungen")));
	}

	@Test
	public void testGenerateHtmlReport_OrderOfValidations() throws Exception {
		ByteArrayOutputStream html = new ByteArrayOutputStream();
		HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

		htmlReportGenerator.generateHtmlReport(createValidatorReportWithAllTypes(), html);

		assertThat(html.toString(), hasXPath("/html/body/p[7]", containsString("semantischen")));
		assertThat(html.toString(), hasXPath("/html/body/p[8]", containsString("geometrischen")));
		assertThat(html.toString(), hasXPath("/html/body/p[9]", containsString("syntaktischen")));
	}

	@Test
	public void testGenerateHtmlReport_Profil() throws Exception {
		ByteArrayOutputStream html = new ByteArrayOutputStream();
		HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

		htmlReportGenerator.generateHtmlReport(createValidatorReportWithAllTypesAndProfile(), html);

		assertThat(html.toString(), hasXPath("/html/body/p[7]", containsString("semantischen")));
		assertThat(html.toString(), hasXPath("/html/body/p[8]", containsString("geometrischen")));
		assertThat(html.toString(), hasXPath("/html/body/p[9]", containsString("syntaktischen")));
		assertThat(html.toString(), hasXPath("/html/body/p[10]", containsString("gegen das Profil")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateXmlReportWithNullReport() throws Exception {
		HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();
		htmlReportGenerator.generateHtmlReport(null, new ByteArrayOutputStream());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateXmlReportWithNullOutputStream() throws Exception {
		HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();
		htmlReportGenerator.generateHtmlReport(createValidationReport(), null);
	}

	private ValidatorReport createValidatorReportWithSemanticFailures() {
		ValidatorReport validatorReport = createValidationReport();
		SemanticValidatorResult semanticValidatorResult = new SemanticValidatorResult();
		semanticValidatorResult.addRule("1.1", "Test valid", Collections.emptyList());
		InvalidFeaturesResult id_12 = new InvalidFeaturesResult("id_12");
		semanticValidatorResult.addRule("1.2", "Test in valid", Collections.singletonList(id_12));
		validatorReport.setSemanticValidatorResult(semanticValidatorResult);
		return validatorReport;
	}

	private ValidatorReport createValidatorReportWithSyntacticDetailHint() {
		ValidatorReport validatorReport = createValidationReport();
		List<String> messages = Collections.singletonList("Error in xml...");
		ValidatorDetail detail = new ValidatorDetail("detailsHint");
		SyntacticValidatorResult syntacticValidatorResult = new SyntacticValidatorResult(messages, false, detail);
		validatorReport.setSyntacticValidatorResult(syntacticValidatorResult);
		return validatorReport;
	}

	private ValidatorReport createValidatorReportWithGeometricWarnings() {
		ValidatorReport validatorReport = createValidationReport();
		List<String> warnings = Collections.singletonList("Warning...");
		List<String> errors = Collections.singletonList("Error...");
		List<BadGeometry> badGeometries = Collections.emptyList();
		GeometricValidatorResult geometricValidatorResult = new GeometricValidatorResult(warnings, errors,
				badGeometries, null, false);
		validatorReport.setGeometricValidatorResult(geometricValidatorResult);
		return validatorReport;
	}

	private ValidatorReport createValidatorReportWithAllTypes() {
		ValidatorReport validatorReport = createValidationReport();
		SyntacticValidatorResult syntacticValidatorResult = new SyntacticValidatorResult(Collections.emptyList(), true,
				null);
		validatorReport.setSyntacticValidatorResult(syntacticValidatorResult);

		SemanticValidatorResult semanticValidatorResult = new SemanticValidatorResult();
		semanticValidatorResult.addRule("1.1", "Test valid", Collections.emptyList());
		InvalidFeaturesResult id_12 = new InvalidFeaturesResult("id_12");
		semanticValidatorResult.addRule("1.2", "Test in valid", Collections.singletonList(id_12));
		validatorReport.setSemanticValidatorResult(semanticValidatorResult);

		GeometricValidatorResult geometricValidatorResult = new GeometricValidatorResult(SYNTAX_ERRORS);
		validatorReport.setGeometricValidatorResult(geometricValidatorResult);
		return validatorReport;
	}

	private ValidatorReport createValidatorReportWithAllTypesAndProfile() {
		ValidatorReport validatorReport = createValidatorReportWithAllTypes();

		SemanticValidatorResult profileValidatorResult = new SemanticValidatorResult();
		profileValidatorResult.addRule("a", "Test valid", Collections.emptyList());
		InvalidFeaturesResult invalidFeaturesResult = new InvalidFeaturesResult("id_profile10");
		profileValidatorResult.addRule("b", "Test invalid", Collections.singletonList(invalidFeaturesResult));

		validatorReport.addSemanticProfileValidatorResults(profileValidatorResult);
		return validatorReport;
	}

	private ValidatorReport createValidationReport() {
		ValidatorReport validatorReport = new ValidatorReport();
		validatorReport.setPlanNames(Collections.singletonList("PLAN_NAME"));
		validatorReport.setValidationName("VALIDATION_NAME");
		return validatorReport;
	}

}
