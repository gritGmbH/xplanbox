/*-
 * #%L
 * xplan-core-api - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplanbox.api.commons.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationReportValidationResultSemantisch {

	private @Valid Boolean valid;

	private @Valid List<ValidationReportValidationResultSemantischRules> rules = new ArrayList<ValidationReportValidationResultSemantischRules>();

	/**
	 *
	 **/
	public ValidationReportValidationResultSemantisch valid(Boolean valid) {
		this.valid = valid;
		return this;
	}

	@Schema(example = "false")
	@JsonProperty("valid")
	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	/**
	 *
	 **/
	public ValidationReportValidationResultSemantisch rules(
			List<ValidationReportValidationResultSemantischRules> rules) {
		this.rules = rules;
		return this;
	}

	@Schema
	@JsonProperty("rules")
	public List<ValidationReportValidationResultSemantischRules> getRules() {
		return rules;
	}

	public void setRules(List<ValidationReportValidationResultSemantischRules> rules) {
		this.rules = rules;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ValidationReportValidationResultSemantisch validationReportValidationResultSemantisch = (ValidationReportValidationResultSemantisch) o;
		return Objects.equals(this.valid, validationReportValidationResultSemantisch.valid)
				&& Objects.equals(this.rules, validationReportValidationResultSemantisch.rules);
	}

	@Override
	public int hashCode() {
		return Objects.hash(valid, rules);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ValidationReportValidationResultSemantisch {\n");

		sb.append("    valid: ").append(toIndentedString(valid)).append("\n");
		sb.append("    rules: ").append(toIndentedString(rules)).append("\n");
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
