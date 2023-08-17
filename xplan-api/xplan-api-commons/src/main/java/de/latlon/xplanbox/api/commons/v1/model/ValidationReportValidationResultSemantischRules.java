/*-
 * #%L
 * xplan-api-commons - xplan-api-commons
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
package de.latlon.xplanbox.api.commons.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
public class ValidationReportValidationResultSemantischRules {

	private @Valid String name;

	private @Valid Boolean isValid;

	private @Valid String message;

	private @Valid List<SemanticInvalidRuleResult> warnedFeatures = new ArrayList<SemanticInvalidRuleResult>();

	private @Valid List<SemanticInvalidRuleResult> erroredFeatures = new ArrayList<SemanticInvalidRuleResult>();

	/**
	 *
	 **/
	public ValidationReportValidationResultSemantischRules name(String name) {
		this.name = name;
		return this;
	}

	@Schema
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 **/
	public ValidationReportValidationResultSemantischRules isValid(Boolean isValid) {
		this.isValid = isValid;
		return this;
	}

	@Schema(description = "")
	@JsonProperty("isValid")
	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 *
	 **/
	public ValidationReportValidationResultSemantischRules message(String message) {
		this.message = message;
		return this;
	}

	@Schema
	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 *
	 **/
	public ValidationReportValidationResultSemantischRules warnedFeatures(
			List<SemanticInvalidRuleResult> warnedFeatures) {
		this.warnedFeatures = warnedFeatures;
		return this;
	}

	@JsonProperty("warnedFeatures")
	public List<SemanticInvalidRuleResult> getWarnedFeatures() {
		return warnedFeatures;
	}

	public void setWarnedFeatures(List<SemanticInvalidRuleResult> warnedFeatures) {
		this.warnedFeatures = warnedFeatures;
	}

	/**
	 *
	 **/
	public ValidationReportValidationResultSemantischRules erroredFeatures(
			List<SemanticInvalidRuleResult> erroredFeatures) {
		this.erroredFeatures = erroredFeatures;
		return this;
	}

	@JsonProperty("erroredFeatures")
	public List<SemanticInvalidRuleResult> getErroredFeatures() {
		return erroredFeatures;
	}

	public void setErroredFeatures(List<SemanticInvalidRuleResult> erroredFeatures) {
		this.erroredFeatures = erroredFeatures;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ValidationReportValidationResultSemantischRules validationReportValidationResultSemantischRules = (ValidationReportValidationResultSemantischRules) o;
		return Objects.equals(this.name, validationReportValidationResultSemantischRules.name)
				&& Objects.equals(this.isValid, validationReportValidationResultSemantischRules.isValid)
				&& Objects.equals(this.message, validationReportValidationResultSemantischRules.message)
				&& Objects.equals(this.warnedFeatures, validationReportValidationResultSemantischRules.warnedFeatures)
				&& Objects.equals(this.erroredFeatures,
						validationReportValidationResultSemantischRules.erroredFeatures);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, isValid, message, warnedFeatures, erroredFeatures);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ValidationReportValidationResultSemantischRules {\n");

		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    isValid: ").append(toIndentedString(isValid)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("    warnedFeatures: ").append(toIndentedString(warnedFeatures)).append("\n");
		sb.append("    erroredFeatures: ").append(toIndentedString(erroredFeatures)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the
	 * first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
