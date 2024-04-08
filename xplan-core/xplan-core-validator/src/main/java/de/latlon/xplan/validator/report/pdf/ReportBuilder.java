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

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.latlon.xplan.validator.i18n.ValidationMessages.format;
import static de.latlon.xplan.validator.i18n.ValidationMessages.getMessage;
import static de.latlon.xplan.validator.report.ReportUtils.asLabel;
import static de.latlon.xplan.validator.report.ReportUtils.createValidLabel;
import static de.latlon.xplan.validator.semantic.report.ValidationResultType.WARNING;

/**
 * Use OPenPDF to build a validation report
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
class ReportBuilder {

	private static final String LABEL_HINT = getMessage("report_pdf_hint");

	private static final String LABEL_WARNING = getMessage("report_pdf_warning");

	private static final String LABEL_ERROR = getMessage("report_pdf_error");

	private static final String LABEL_OK = getMessage("report_pdf_ok");

	private static Font FONT_TEXT = new Font(Font.HELVETICA, 10);

	private static Font FONT_CHAPTER = new Font(Font.HELVETICA, 14, Font.BOLD);

	/**
	 * Write pdf report to the passed {@link Document}.
	 * @param report the validation report to serialize, never <code>null</code>
	 * @param document to write into, never <code>nulll</code>
	 * @throws IllegalArgumentException if the passed report is <code>null</code>
	 */
	void writeReport(ValidatorReport report, Document document) {
		checkReportParam(report);

		appendMetadataSection(report, document);
		appendPlanNames(report.getPlanNames(), document);
		appendExternalReferenceReport(report.getExternalReferenceReport(), document);

		appendSemanticValidatorResult(report.getSemanticValidatorResult(), document);
		appendGeometricRules(report.getGeometricValidatorResult(), document);
		appendSyntacticRules(report.getSyntacticValidatorResult(), document);

		List<SemanticValidatorResult> semanticProfileValidatorResults = report.getSemanticProfileValidatorResults();
		if (!semanticProfileValidatorResults.isEmpty()) {
			semanticProfileValidatorResults.sort(Comparator.comparing(o -> o.getRulesMetadata().getName()));
			for (SemanticValidatorResult profileSemanticValidatorResult : semanticProfileValidatorResults) {
				appendHeaderAndResultOfProfile(profileSemanticValidatorResult, document);
				appendSemanticValidatorResult(profileSemanticValidatorResult, document);
			}
		}

	}

	private void checkReportParam(ValidatorReport report) {
		if (report == null)
			throw new IllegalArgumentException("Report must not be null!");
	}

	private void appendMetadataSection(ValidatorReport report, Document document) {
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[] { 25, 75 });
		table.setTotalWidth(tableWidth(document));
		table.setLockedWidth(true);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setPaddingBottom(10);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		table.addCell(createTableCell("Validierungsname:", FONT_TEXT));
		table.addCell(createTableCell(report.getValidationName(), FONT_TEXT));
		table.addCell(createTableCell("Dateiname:", FONT_TEXT));
		table.addCell(createTableCell(createValidLabel(report.isReportValid()), FONT_TEXT));
		table.addCell(createTableCell("Validierungsergebnis:", FONT_TEXT));
		table.addCell(createTableCell(report.getValidationName(), FONT_TEXT));
		table.addCell(createTableCell("Version XPlanGML:", FONT_TEXT));
		table.addCell(createTableCell(asLabel(report.getXPlanVersion()), FONT_TEXT));
		table.addCell(createTableCell("Planart:", FONT_TEXT));
		table.addCell(createTableCell(report.getXPlanType() != null ? report.getXPlanType().name() : "-", FONT_TEXT));

		document.add(table);
	}

	private void appendPlanNames(List<String> planNames, Document document) {
		if (planNames != null) {
			appendHeader(getMessage("report_pdf_plannamen"), document);

			PdfPTable table = new PdfPTable(1);
			table.setWidths(new int[] { 100 });
			table.setTotalWidth(tableWidth(document));
			table.setLockedWidth(true);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			table.getDefaultCell().setPaddingBottom(5);
			table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			planNames.stream().sorted().forEach(planName -> {
				table.addCell(new Paragraph(planName, FONT_TEXT));
			});
			document.add(table);
		}
	}

	private void appendExternalReferenceReport(ExternalReferenceReport externalReferenceReport, Document document) {
		if (externalReferenceReport != null) {
			appendHeader(getMessage("report_pdf_externalReferences"), document);

			PdfPTable table = new PdfPTable(1);
			table.setWidths(new int[] { 100 });
			table.setTotalWidth(tableWidth(document));
			table.setLockedWidth(true);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			table.getDefaultCell().setPaddingBottom(5);
			table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

			ReportUtils.SkipCode skipCode = externalReferenceReport.getSkipCode();
			if (skipCode != null) {
				document.add(new Paragraph(skipCode.getMessage()));
			}
			Map<String, ExternalReferenceStatus> references = externalReferenceReport.getReferencesAndStatus();
			if (references != null && !references.isEmpty()) {
				references.forEach((name, status) -> {
					String nameAndStatus = String.format("%s (%s)", name, status.getLabel());
					table.addCell(new Paragraph(nameAndStatus, FONT_TEXT));
				});
			}
			document.add(table);
		}
	}

	private void appendSemanticValidatorResult(SemanticValidatorResult result, Document document) {
		if (result != null) {
			appendHeaderAndResult(result, document);
			appendDetailsHint(result, document);
			appendRulesMetadata(result, document);
			appendNumberOfRules(result, document);
			appendNumberOfFailedRules(result, document);
			appendNumberOfValidRules(result, document);
			appendSemanticValidatorRules(result.getRules(), document);
		}
	}

	private void appendRulesMetadata(SemanticValidatorResult semanticValidatorResult, Document document) {
		RulesMetadata rulesMetadata = semanticValidatorResult.getRulesMetadata();
		if (rulesMetadata != null) {
			if (rulesMetadata.getName() != null) {
				String name = String.format(" Name: %s", rulesMetadata.getName());
				appendText(name, document);
			}
			if (rulesMetadata.getDescription() != null) {
				String description = String.format(" Beschreibung: %s", rulesMetadata.getDescription());
				appendText(description, document);
			}
			String version = format("report_pdf_versionRules", rulesMetadata.getVersion());
			appendText(version, document);
			String source = format("report_pdf_sourceRules", rulesMetadata.getSource());
			appendText(source, document);
		}
	}

	private void appendNumberOfRules(SemanticValidatorResult semanticValidatorResult, Document document) {
		int noOfRules = semanticValidatorResult.getRules().size();
		String text = format("report_pdf_noOfCheckedRules", noOfRules);
		appendText(text, document);
	}

	private void appendNumberOfFailedRules(SemanticValidatorResult semanticValidatorResult, Document document) {
		long noOfRules = semanticValidatorResult.getRules().stream().filter(r -> !r.isValid()).count();
		String text = format("report_pdf_noOfInvalidRules", noOfRules);
		appendText(text, document);
	}

	private void appendNumberOfValidRules(SemanticValidatorResult semanticValidatorResult, Document document) {
		long noOfRules = semanticValidatorResult.getRules().stream().filter(r -> r.isValid()).count();
		String text = format("report_pdf_noOfValidRules", noOfRules);
		appendText(text, document);
	}

	private void appendSyntacticRules(SyntacticValidatorResult result, Document document) {
		if (result != null) {
			appendHeaderAndResult(result, document);
			appendDetailsHint(result, document);

			PdfPTable table = createValidationResultTable(document, 15, 75);
			for (String message : result.getMessages()) {
				table.addCell(new Paragraph(LABEL_HINT, FONT_TEXT));
				table.addCell(new Paragraph(message, FONT_TEXT));
			}
			document.add(table);
		}
	}

	private void appendGeometricRules(GeometricValidatorResult result, Document document) {
		if (result != null) {
			appendHeaderAndResult(result, document);
			appendDetailsHint(result, document);

			PdfPTable table = createValidationResultTable(document, 15, 75);
			for (String message : result.getWarnings()) {
				table.addCell(new Paragraph(LABEL_WARNING, FONT_TEXT));
				table.addCell(new Paragraph(message, FONT_TEXT));
			}
			for (String message : result.getErrors()) {
				table.addCell(new Paragraph(LABEL_ERROR, FONT_TEXT));
				table.addCell(new Paragraph(message, FONT_TEXT));
			}
			document.add(table);
		}
	}

	private void appendHeaderAndResult(ValidatorResult result, Document document) {
		PdfPTable table = new PdfPTable(2);
		table.setWidths(new int[] { 75, 25 });
		table.setTotalWidth(tableWidth(document));
		table.setLockedWidth(true);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setPaddingTop(7);
		table.getDefaultCell().setPaddingBottom(5);
		table.getDefaultCell().setBorder(Rectangle.BOTTOM);

		table.addCell(new Paragraph(result.getType(), FONT_CHAPTER));
		table.addCell(new Paragraph(getResultMessage(result), FONT_CHAPTER));
		document.add(table);
	}

	private void appendHeader(String header, Document document) {
		PdfPTable table = new PdfPTable(1);
		table.setWidths(new int[] { 100 });
		table.setTotalWidth(tableWidth(document));
		table.setLockedWidth(true);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setPaddingTop(7);
		table.getDefaultCell().setPaddingBottom(5);
		table.getDefaultCell().setBorder(Rectangle.BOTTOM);

		table.addCell(new Paragraph(header, FONT_CHAPTER));
		document.add(table);
	}

	private void appendHeaderAndResultOfProfile(SemanticValidatorResult result, Document document) {
		String text = "Profil " + result.getRulesMetadata().getName();
		document.add(new Paragraph(text, FONT_CHAPTER));
		document.add(new Paragraph(getResultMessage(result), FONT_CHAPTER));
	}

	private void appendDetailsHint(ValidatorResult validatorResult, Document document) {
		if (validatorResult != null && validatorResult.getValidatorDetail() != null) {
			ValidatorDetail detailsHint = validatorResult.getValidatorDetail();
			appendText(detailsHint.toString(), document);
		}
	}

	private void appendSemanticValidatorRules(List<RuleResult> ruleResults, Document document) {
		appendText(getMessage("report_pdf_details"), document);

		PdfPTable table = createValidationResultTable(document, 15, 15, 70);
		for (RuleResult ruleResult : ruleResults) {
			List<InvalidFeaturesResult> invalidFeaturesResults = ruleResult.getInvalidFeaturesResults();
			if (invalidFeaturesResults.isEmpty()) {
				appendSemanticValidatorRuleResult(LABEL_OK, ruleResult.getName(), ruleResult.getMessage(),
						Collections.emptyList(), table);
			}
			else {
				for (InvalidFeaturesResult invalidRuleResult : invalidFeaturesResults) {
					String label = WARNING.equals(invalidRuleResult.getResultType()) ? LABEL_WARNING : LABEL_ERROR;
					appendSemanticValidatorRuleResult(label, ruleResult.getName(), invalidRuleResult.getMessage(),
							invalidRuleResult.getGmlIds(), table);
				}
			}
		}
		document.add(table);
	}

	private void appendSemanticValidatorRuleResult(String label, String name, String message, List<String> gmlIds,
			PdfPTable table) {
		StringBuilder messageBuilder = new StringBuilder(message);
		if (!gmlIds.isEmpty()) {
			messageBuilder.append(getMessage("report_pdf_gmlIds"));
			messageBuilder.append(gmlIds.stream().collect(Collectors.joining(", ")));
		}
		table.addCell(new Paragraph(label, FONT_TEXT));
		table.addCell(new Paragraph(name, FONT_TEXT));
		table.addCell(new Paragraph(messageBuilder.toString(), FONT_TEXT));
	}

	private void appendText(String text, Document document) {
		if (text != null) {
			Paragraph paragraph = new Paragraph(text, FONT_TEXT);
			paragraph.setIndentationLeft(indentation(document, 10));
			document.add(paragraph);
		}
	}

	private PdfPTable createValidationResultTable(Document document, int... widths) {
		PdfPTable table = new PdfPTable(widths.length);
		table.setWidths(widths);
		table.setTotalWidth(tableWidth(document) - 30);
		table.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.setSpacingBefore(5);
		table.setLockedWidth(true);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setPaddingBottom(5);
		table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
		return table;
	}

	private PdfPCell createTableCell(String text, Font font) {
		String textToAdd = text != null ? text : "-";
		PdfPCell cell = new PdfPCell(new Paragraph(textToAdd, font));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}

	private String getResultMessage(ValidatorResult result) {
		if (result.isSkipped()) {
			return result.getSkipCode().getMessage();
		}
		return createValidLabel(result.isValid());
	}

	private float tableWidth(Document document) {
		float width = document.getPageSize().getWidth();
		return width - 72;
	}

	private float indentation(Document document, int indentation) {
		float left = document.getPageSize().getLeft();
		return left + indentation;
	}

}
