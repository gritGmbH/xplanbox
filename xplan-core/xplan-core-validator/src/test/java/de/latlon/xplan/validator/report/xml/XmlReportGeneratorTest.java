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
package de.latlon.xplan.validator.report.xml;

import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.semantic.report.InvalidFeaturesResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.junit.Test;
import org.xmlunit.matchers.EvaluateXPathMatcher;
import org.xmlunit.matchers.HasXPathMatcher;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.validator.report.ReportUtils.SkipCode.SYNTAX_ERRORS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @deprecated will be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
@Deprecated
public class XmlReportGeneratorTest {

	private static final String PLAN_NAME = "PLAN_NAME";

	private static final String VALIDATION_NAME = "VALIDATION_NAME";

	@Test
	public void testGenerateXmlReport() throws Exception {
		XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		xmlReportGenerator.generateXmlReport(createValidatorReport(), os);
		String xml = os.toString();

		assertThat(xml, EvaluateXPathMatcher.hasXPath("/ValidationReport/name", equalTo(VALIDATION_NAME)));
		assertThat(xml, EvaluateXPathMatcher.hasXPath("/ValidationReport/Plan/name", equalTo(PLAN_NAME)));
		assertThat(xml, HasXPathMatcher.hasXPath("/ValidationReport/Validation"));
	}

	@Test
	public void testGenerateXmlReport_CheckSyntacticDetailsHint() throws Exception {
		XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		xmlReportGenerator.generateXmlReport(createValidatorReportWithSyntacticDetailHint(), os);
		String xml = os.toString();

		assertThat(xml,
				EvaluateXPathMatcher.hasXPath("/ValidationReport/Validation/Syn/details", equalTo("detailsHint")));
	}

	@Test
	public void testGenerateXmlReport_CheckSemanticsResults() throws Exception {
		XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		xmlReportGenerator.generateXmlReport(createValidatorReportWithSemanticFailures(), os);
		String xml = os.toString();

		assertThat(xml,
				EvaluateXPathMatcher.hasXPath("/ValidationReport/Validation/Sem/result", equalTo("nicht valide")));
		assertThat(xml,
				EvaluateXPathMatcher.hasXPath("count(/ValidationReport/Validation/Sem/Rules/Rule)", equalTo("2")));
		assertThat(xml,
				EvaluateXPathMatcher.hasXPath("/ValidationReport/Validation/Sem/Rules/Rule[1]/name", equalTo("1.1")));
		assertThat(xml,
				EvaluateXPathMatcher.hasXPath("/ValidationReport/Validation/Sem/Rules/Rule[2]/name", equalTo("1.2")));
	}

	@Test
	public void testGenerateXmlReport_OrderOfValidations() throws Exception {
		XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		xmlReportGenerator.generateXmlReport(createValidatorReportWithAllTypes(), os);
		String xml = os.toString();

		assertThat(xml, HasXPathMatcher.hasXPath("/ValidationReport/Validation/Sem"));
		assertThat(xml, HasXPathMatcher.hasXPath("/ValidationReport/Validation/*[local-name()= 'Sem']"));
		assertThat(xml, EvaluateXPathMatcher.hasXPath(
				"count(/ValidationReport/Validation/*[local-name()= 'Sem']/preceding-sibling::*)+1.", equalTo("1")));
		assertThat(xml, EvaluateXPathMatcher.hasXPath(
				"count(/ValidationReport/Validation/*[local-name()= 'Geom']/preceding-sibling::*)+1.", equalTo("2")));
		assertThat(xml, EvaluateXPathMatcher.hasXPath(
				"count(/ValidationReport/Validation/*[local-name()= 'Syn']/preceding-sibling::*)+1.", equalTo("3")));
	}

	@Test
	public void testGenerateXmlReport_Profil() throws Exception {
		XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		xmlReportGenerator.generateXmlReport(createValidatorReportWithAllTypesAndProfile(), os);
		String xml = os.toString();

		assertThat(xml, HasXPathMatcher.hasXPath("/ValidationReport/Validation/Profile"));
		assertThat(xml,
				EvaluateXPathMatcher.hasXPath("/ValidationReport/Validation/Profile/result", equalTo("nicht valide")));
		assertThat(xml, EvaluateXPathMatcher
			.hasXPath("/ValidationReport/Validation/Profile/Rules/Rule[name = 'a']/isValid", equalTo("true")));
		assertThat(xml, EvaluateXPathMatcher
			.hasXPath("/ValidationReport/Validation/Profile/Rules/Rule[name = 'b']/isValid", equalTo("false")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateXmlReportWithNullReport() throws Exception {
		XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
		xmlReportGenerator.generateXmlReport(null, new ByteArrayOutputStream());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGenerateXmlReportWithNullOutputStream() throws Exception {
		XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
		xmlReportGenerator.generateXmlReport(createValidatorReport(), null);
	}

	private ValidatorReport createValidatorReport() {
		ValidatorReport validatorReport = new ValidatorReport();
		validatorReport.setPlanNames(Collections.singletonList(PLAN_NAME));
		validatorReport.setValidationName(VALIDATION_NAME);
		return validatorReport;
	}

	private ValidatorReport createValidatorReportWithSyntacticDetailHint() {
		ValidatorReport validatorReport = createValidatorReport();
		List<String> messages = Collections.singletonList("Error in xml...");
		ValidatorDetail detail = new ValidatorDetail("detailsHint");
		SyntacticValidatorResult syntacticValidatorResult = new SyntacticValidatorResult(messages, false, detail);
		validatorReport.setSyntacticValidatorResult(syntacticValidatorResult);
		return validatorReport;
	}

	private ValidatorReport createValidatorReportWithSemanticFailures() {
		ValidatorReport validatorReport = createValidatorReport();
		SemanticValidatorResult semanticValidatorResult = new SemanticValidatorResult();
		semanticValidatorResult.addRule("1.1", "Test valid", Collections.emptyList());
		InvalidFeaturesResult id_12 = new InvalidFeaturesResult("id_12");
		semanticValidatorResult.addRule("1.2", "Test in valid", Collections.singletonList(id_12));
		validatorReport.setSemanticValidatorResult(semanticValidatorResult);
		return validatorReport;
	}

	private ValidatorReport createValidatorReportWithAllTypes() {
		ValidatorReport validatorReport = createValidatorReport();
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

}
