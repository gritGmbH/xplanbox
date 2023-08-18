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

import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ReportUtils;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.report.reference.ExternalReferenceReport;
import de.latlon.xplan.validator.report.reference.ExternalReferenceStatus;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.report.InvalidFeaturesResult;
import de.latlon.xplan.validator.semantic.report.RuleResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.MultiPageListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.latlon.xplan.validator.i18n.ValidationMessages.format;
import static de.latlon.xplan.validator.i18n.ValidationMessages.getMessage;
import static de.latlon.xplan.validator.report.ReportUtils.asLabel;
import static de.latlon.xplan.validator.report.ReportUtils.createValidLabel;
import static de.latlon.xplan.validator.report.pdf.Templates.bold14LeftStyle;
import static de.latlon.xplan.validator.report.pdf.Templates.createFooter;
import static de.latlon.xplan.validator.report.pdf.Templates.createTemplate;
import static de.latlon.xplan.validator.report.pdf.Templates.root20LeftIndentStyle;
import static de.latlon.xplan.validator.report.pdf.Templates.simpleStyle;
import static de.latlon.xplan.validator.semantic.report.ValidationResultType.WARNING;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

/**
 * Encapsulates a {@link JasperReportBuilder} building a validation report
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
class ReportBuilder {

	private static final String LABEL_TITLE = "ValidationReport";

	private static final String LABEL_HINT = getMessage("report_pdf_hint");

	private static final String LABEL_WARNING = getMessage("report_pdf_warning");

	private static final String LABEL_ERROR = getMessage("report_pdf_error");

	private static final String LABEL_OK = getMessage("report_pdf_ok");

	private static final int VERTICAL_GAP = 10;

	/**
	 * Creates a {@link JasperReportBuilder} with the {@link ValidatorReport}
	 * @param report the validation report to serialize, never <code>null</code>
	 * @throws ReportGenerationException if an exception occurred during writing the
	 * report
	 * @throws IllegalArgumentException if the passed report is <code>null</code>
	 */
	JasperReportBuilder createReport(ValidatorReport report) throws ReportGenerationException {
		checkReportParam(report);
		try {
			return report().setTemplate(createTemplate())
				.title(Templates.createTitleComponent(LABEL_TITLE), createMetadataSection(report))
				.summary(createValidationResults(report))
				.pageFooter(createFooter());
		}
		catch (JRException e) {
			throw new ReportGenerationException(e);
		}
	}

	private void checkReportParam(ValidatorReport report) {
		if (report == null)
			throw new IllegalArgumentException("Report must not be null!");
	}

	private VerticalListBuilder createValidationResults(ValidatorReport report) {
		VerticalListBuilder verticalList = cmp.verticalList();

		List<String> planNames = report.getPlanNames();
		if (planNames != null) {
			verticalList = addPlanNames(verticalList, planNames);
		}

		ExternalReferenceReport externalReferenceReport = report.getExternalReferenceReport();
		if (externalReferenceReport != null) {
			verticalList = addExternalReferenceReport(verticalList, externalReferenceReport);
		}

		SemanticValidatorResult semanticValidatorResult = report.getSemanticValidatorResult();
		if (semanticValidatorResult != null) {
			verticalList = verticalList.add(appendHeaderAndResult(semanticValidatorResult));
			verticalList = appendSemanticValidatorResult(verticalList, semanticValidatorResult);
		}

		GeometricValidatorResult geometricValidatorResult = report.getGeometricValidatorResult();
		if (geometricValidatorResult != null) {
			verticalList = verticalList.add(appendHeaderAndResult(geometricValidatorResult));
			verticalList = appendDetailsHint(verticalList, geometricValidatorResult);
			verticalList = verticalList.add(createGeometricRules(geometricValidatorResult));
		}

		SyntacticValidatorResult syntacticValidatorResult = report.getSyntacticValidatorResult();
		if (syntacticValidatorResult != null) {
			verticalList = verticalList.add(appendHeaderAndResult(syntacticValidatorResult));
			verticalList = appendDetailsHint(verticalList, syntacticValidatorResult);
			verticalList = verticalList.add(createSyntacticRules(syntacticValidatorResult)).add(cmp.verticalGap(10));
		}
		List<SemanticValidatorResult> semanticProfileValidatorResults = report.getSemanticProfileValidatorResults();
		if (!semanticProfileValidatorResults.isEmpty()) {
			semanticProfileValidatorResults.sort(Comparator.comparing(o -> o.getRulesMetadata().getName()));
			for (SemanticValidatorResult profileSemanticValidatorResult : semanticProfileValidatorResults) {
				verticalList = verticalList.add(appendHeaderAndResultOfProfile(profileSemanticValidatorResult));
				verticalList = appendSemanticValidatorResult(verticalList, profileSemanticValidatorResult);
			}
		}

		return verticalList;
	}

	private VerticalListBuilder appendSemanticValidatorResult(VerticalListBuilder verticalList,
			SemanticValidatorResult profileSemanticValidatorResult) {
		verticalList = appendDetailsHint(verticalList, profileSemanticValidatorResult);
		verticalList = appendRulesMetadata(verticalList, profileSemanticValidatorResult);
		verticalList = verticalList.add(appendNumberOfRules(profileSemanticValidatorResult));
		verticalList = verticalList.add(appendNumberOfFailedRules(profileSemanticValidatorResult));
		verticalList = verticalList.add(appendNumberOfValidRules(profileSemanticValidatorResult));
		verticalList = verticalList.add(appendDetailsSection());
		verticalList = verticalList.add(createSemanticRules(profileSemanticValidatorResult)).add(cmp.verticalGap(10));
		return verticalList;
	}

	private VerticalListBuilder addPlanNames(VerticalListBuilder verticalList, List<String> planNames) {
		ComponentBuilder<?, ?> rulesHead = cmp.text(getMessage("report_pdf_plannamen")).setStyle(bold14LeftStyle);
		HorizontalListBuilder header = cmp.horizontalList().add(rulesHead);
		verticalList = verticalList.add(header);

		MultiPageListBuilder rules = cmp.multiPageList();

		planNames.stream().sorted().forEach(planName -> {
			StyleBuilder style = stl.style(simpleStyle).setLeftIndent(10);
			TextFieldBuilder<String> referenceField = cmp.text(planName).setStyle(style);
			rules.add(cmp.horizontalList().add(referenceField));
		});
		return verticalList.add(rules);
	}

	private VerticalListBuilder addExternalReferenceReport(VerticalListBuilder verticalList,
			ExternalReferenceReport externalReferenceReport) {
		ComponentBuilder<?, ?> rulesHead = cmp.text(getMessage("report_pdf_externalReferences"))
			.setStyle(bold14LeftStyle);
		HorizontalListBuilder header = cmp.horizontalList().add(rulesHead);
		verticalList = verticalList.add(header);

		ReportUtils.SkipCode skipCode = externalReferenceReport.getSkipCode();
		if (skipCode != null) {
			StyleBuilder style = stl.style(simpleStyle).setLeftIndent(10);
			TextFieldBuilder<String> skipCodeField = cmp.text(skipCode.getMessage()).setStyle(style);
			verticalList = verticalList.add(skipCodeField);
		}
		Map<String, ExternalReferenceStatus> references = externalReferenceReport.getReferencesAndStatus();
		if (references != null && !references.isEmpty()) {
			MultiPageListBuilder rules = cmp.multiPageList();
			references.forEach((name, status) -> {
				StyleBuilder style = stl.style(simpleStyle).setLeftIndent(10);
				String nameAndStatus = String.format("%s (%s)", name, status.getLabel());
				TextFieldBuilder<String> referenceField = cmp.text(nameAndStatus).setStyle(style);
				rules.add(cmp.horizontalList().add(referenceField));
			});
			verticalList = verticalList.add(rules);
		}
		return verticalList;
	}

	private VerticalListBuilder appendRulesMetadata(VerticalListBuilder verticalList,
			SemanticValidatorResult semanticValidatorResult) {
		RulesMetadata rulesMetadata = semanticValidatorResult.getRulesMetadata();
		if (rulesMetadata != null) {
			if (rulesMetadata.getName() != null) {
				String name = String.format(" Name: %s", rulesMetadata.getName());
				verticalList = verticalList.add(addTextString(name));
			}
			if (rulesMetadata.getDescription() != null) {
				String description = String.format(" Beschreibung: %s", rulesMetadata.getDescription());
				verticalList = verticalList.add(addTextString(description));
			}
			String version = format("report_pdf_versionRules", rulesMetadata.getVersion());
			verticalList = verticalList.add(addTextString(version));
			String source = format("report_pdf_sourceRules", rulesMetadata.getSource());
			verticalList = verticalList.add(addTextString(source));
		}
		return verticalList;
	}

	private ComponentBuilder<?, ?> appendNumberOfRules(SemanticValidatorResult semanticValidatorResult) {
		int noOfRules = semanticValidatorResult.getRules().size();
		String text = format("report_pdf_noOfCheckedRules", noOfRules);
		return addTextString(text);
	}

	private ComponentBuilder<?, ?> appendNumberOfFailedRules(SemanticValidatorResult semanticValidatorResult) {
		long noOfRules = semanticValidatorResult.getRules().stream().filter(r -> !r.isValid()).count();
		String text = format("report_pdf_noOfInvalidRules", noOfRules);
		return addTextString(text);
	}

	private ComponentBuilder<?, ?> appendNumberOfValidRules(SemanticValidatorResult semanticValidatorResult) {
		long noOfRules = semanticValidatorResult.getRules().stream().filter(r -> r.isValid()).count();
		String text = format("report_pdf_noOfValidRules", noOfRules);
		return addTextString(text);
	}

	private ComponentBuilder<?, ?> appendDetailsSection() {
		return addTextString(getMessage("report_pdf_details"));
	}

	private ComponentBuilder<?, ?> addTextString(String text) {
		StyleBuilder detailsHintStyle = stl.style(simpleStyle).setLeftIndent(10).setTopPadding(5).setBottomPadding(5);
		TextFieldBuilder<String> textString = cmp.text(text).setStyle(detailsHintStyle);
		return cmp.horizontalList().add(textString);
	}

	private MultiPageListBuilder createSemanticRules(SemanticValidatorResult result) {
		MultiPageListBuilder rules = cmp.multiPageList();
		appendSemanticValidatorRules(rules, result.getRules());
		return rules;
	}

	private ComponentBuilder<?, ?> createSyntacticRules(SyntacticValidatorResult result) {
		MultiPageListBuilder rules = cmp.multiPageList();
		appendMessageRules(rules, LABEL_HINT, (result).getMessages());
		return rules;
	}

	private ComponentBuilder<?, ?> createGeometricRules(GeometricValidatorResult result) {
		MultiPageListBuilder rules = cmp.multiPageList();
		appendMessageRules(rules, LABEL_WARNING, (result).getWarnings());
		appendMessageRules(rules, LABEL_ERROR, (result).getErrors());
		return rules;
	}

	private ComponentBuilder<?, ?> appendHeaderAndResult(ValidatorResult result) {
		ComponentBuilder<?, ?> rulesHead = cmp.text(result.getType()).setStyle(bold14LeftStyle);
		TextFieldBuilder<String> validString = cmp.text(getResultMessage(result))
			.setStyle(bold14LeftStyle.setBottomBorder(stl.pen1Point()));
		return cmp.horizontalList().add(rulesHead).add(validString);
	}

	private ComponentBuilder<?, ?> appendHeaderAndResultOfProfile(SemanticValidatorResult result) {
		String text = "Profil " + result.getRulesMetadata().getName();
		ComponentBuilder<?, ?> rulesHead = cmp.text(text).setStyle(bold14LeftStyle);
		TextFieldBuilder<String> validString = cmp.text(getResultMessage(result))
			.setStyle(bold14LeftStyle.setBottomBorder(stl.pen1Point()));
		return cmp.horizontalList().add(rulesHead).add(validString);
	}

	private VerticalListBuilder appendDetailsHint(VerticalListBuilder verticalList, ValidatorResult validatorResult) {
		if (validatorResult != null && validatorResult.getValidatorDetail() != null) {
			ValidatorDetail detailsHint = validatorResult.getValidatorDetail();
			StyleBuilder detailsHintStyle = stl.style(simpleStyle)
				.setLeftIndent(10)
				.setTopPadding(5)
				.setBottomPadding(5);
			TextFieldBuilder<String> detailsString = cmp.text(detailsHint.toString()).setStyle(detailsHintStyle);
			verticalList = verticalList.add(detailsString);
		}
		return verticalList;
	}

	private void appendMessageRules(MultiPageListBuilder rules, String label, List<String> messagesToWrite) {
		for (String message : messagesToWrite) {
			TextFieldBuilder<String> labelField = cmp.text(label).setFixedWidth(100).setStyle(root20LeftIndentStyle);
			TextFieldBuilder<String> messageField = cmp.text(message).setStyle(simpleStyle);
			rules.add(cmp.horizontalList().add(labelField).add(messageField));
		}
	}

	private void appendSemanticValidatorRules(MultiPageListBuilder rules, List<RuleResult> ruleResults) {
		for (RuleResult ruleResult : ruleResults) {
			List<InvalidFeaturesResult> invalidFeaturesResults = ruleResult.getInvalidFeaturesResults();
			if (invalidFeaturesResults.isEmpty()) {
				appendSemanticValidatorRule(rules, LABEL_OK, ruleResult.getName(), ruleResult.getMessage(),
						Collections.emptyList());
			}
			else {
				for (InvalidFeaturesResult invalidRuleResult : invalidFeaturesResults) {
					String label = WARNING.equals(invalidRuleResult.getResultType()) ? LABEL_WARNING : LABEL_ERROR;
					appendSemanticValidatorRule(rules, label, ruleResult.getName(), invalidRuleResult.getMessage(),
							invalidRuleResult.getGmlIds());
				}
			}
		}
	}

	private void appendSemanticValidatorRule(MultiPageListBuilder rules, String label, String name, String message,
			List<String> gmlIds) {
		TextFieldBuilder<String> labelField = cmp.text(label).setFixedWidth(100).setStyle(root20LeftIndentStyle);
		TextFieldBuilder<String> nameField = cmp.text(name).setFixedWidth(60).setStyle(simpleStyle);
		StringBuilder messageBuilder = new StringBuilder(message);
		if (!gmlIds.isEmpty()) {
			messageBuilder.append(getMessage("report_pdf_gmlIds"));
			messageBuilder.append(gmlIds.stream().collect(Collectors.joining(", ")));
		}
		TextFieldBuilder<String> messageField = cmp.text(messageBuilder.toString()).setStyle(simpleStyle);
		rules.add(cmp.horizontalList().add(labelField).add(nameField).add(messageField));
	}

	private ComponentBuilder<?, ?> createMetadataSection(ValidatorReport report) throws JRException {
		InputStream is = PdfReportGenerator.class.getResourceAsStream("/jrxml/metadata.jrxml");
		JasperReport jasperTitleSubreport = JasperCompileManager.compileReport(is);
		Map<String, Object> parameters = createParams(report);
		return cmp.verticalList()
			.add(cmp.subreport(jasperTitleSubreport).setParameters(parameters))
			.add(cmp.verticalGap(VERTICAL_GAP));
	}

	private String getResultMessage(ValidatorResult result) {
		if (result.isSkipped()) {
			return result.getSkipCode().getMessage();
		}
		return createValidLabel(result.isValid());
	}

	private Map<String, Object> createParams(ValidatorReport report) {
		String isValid = createValidLabel(report.isReportValid());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("valName", report.getValidationName());
		params.put("valResult", isValid);
		params.put("date", report.getDate());
		params.put("fileName", report.getArchiveName());
		params.put("version", asLabel(report.getXPlanVersion()));
		return params;
	}

}
