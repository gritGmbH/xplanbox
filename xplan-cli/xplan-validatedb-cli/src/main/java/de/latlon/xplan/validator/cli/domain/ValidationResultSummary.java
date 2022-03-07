package de.latlon.xplan.validator.cli.domain;

import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.report.RuleResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidationResultSummary {

	private final String id;

	private final String version;

	private final String name;

	private final String district;

	private final ValidatorResult validatorReport;

	public ValidationResultSummary(String id, String version, String name, String district,
			ValidatorResult validatorReport) {
		this.id = id;
		this.version = version;
		this.name = name;
		this.district = district;
		this.validatorReport = validatorReport;
	}

	public String getId() {
		return id;
	}

	public String getVersion() {
		return version;
	}

	public String getName() {
		return name;
	}

	public String getDistrict() {
		return district;
	}

	public ValidatorResult getValidatorReport() {
		return validatorReport;
	}

	public boolean getResult() {
		return validatorReport.isValid();
	}

	public String getFailedRules() {
		if (validatorReport instanceof SemanticValidatorResult) {
			List<RuleResult> rules = ((SemanticValidatorResult) validatorReport).getRules();
			String collectedRules = rules.stream().filter(rule -> !rule.isValid()).map(rule -> rule.getName())
					.collect(Collectors.joining(","));
			if (!collectedRules.isEmpty())
				return "\"" + collectedRules + "\"";
		}
		return null;
	}

	@Override
	public String toString() {
		return "ValidationResultSummary{" + "id='" + id + '\'' + ", version='" + version + '\'' + ", name='" + name
				+ '\'' + ", district='" + district + '\'' + ", validatorReport=" + validatorReport + '}';
	}

}
