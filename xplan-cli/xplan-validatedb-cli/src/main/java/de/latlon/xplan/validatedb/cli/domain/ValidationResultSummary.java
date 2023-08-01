/*-
 * #%L
 * xplan-evaluation-schema-synchronize-cli - Datenbankschema für die Auswertung der XPlanGML-Daten
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
package de.latlon.xplan.validatedb.cli.domain;

import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.report.RuleResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 5.0
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
			return rules.stream()
				.filter(rule -> !rule.isValid())
				.map(rule -> rule.getName())
				.collect(Collectors.joining(","));
		}
		return null;
	}

	@Override
	public String toString() {
		return "ValidationResultSummary{" + "id='" + id + '\'' + ", version='" + version + '\'' + ", name='" + name
				+ '\'' + ", district='" + district + '\'' + ", validatorReport=" + validatorReport + '}';
	}

}
