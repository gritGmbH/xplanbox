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
package de.latlon.xplan.validator.semantic.report;

import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.validator.semantic.report.ValidationResultType.ERROR;

/**
 * contains the validator result of the rules of the semantic validation
 *
 * @author Florian Bingel
 */
public class RuleResult implements Comparable {

	private final String name;

	private final String message;

	private final List<InvalidFeaturesResult> invalidFeaturesResults;

	protected RuleResult(String name, String message, List<InvalidFeaturesResult> invalidFeaturesResults) {
		this.name = name;
		this.message = message;
		this.invalidFeaturesResults = invalidFeaturesResults;
	}

	public String getName() {
		return name;
	}

	public boolean isValid() {
		return getInvalidFeaturesResultsByType(ERROR).isEmpty();
	}

	public String getMessage() {
		return message;
	}

	public List<InvalidFeaturesResult> getInvalidFeaturesResults() {
		return invalidFeaturesResults;
	}

	public List<InvalidFeaturesResult> getInvalidFeaturesResultsByType(ValidationResultType validationResultType) {
		return invalidFeaturesResults.stream()
			.filter(invalidRuleResult -> invalidRuleResult.getResultType().equals(validationResultType))
			.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "RuleResult{" + "name='" + name + '\'' + ", isValid=" + isValid() + ", message='" + message + '\'' + '}';
	}

	@Override
	public int compareTo(Object o) {
		try {
			String[] array1 = this.getName().split("\\.");
			String[] array2 = ((RuleResult) o).getName().split("\\.");
			for (int i = 0; i < array1.length && i < array2.length; i++) {
				int result = Integer.valueOf(array1[i]).compareTo(Integer.valueOf(array2[i]));
				if (result != 0)
					return result;
			}
			return compareStrings((RuleResult) o);
		}
		catch (NumberFormatException ex) {
			return compareStrings((RuleResult) o);
		}
	}

	private int compareStrings(RuleResult o) {
		return this.getName().compareTo(o.getName());
	}

}
