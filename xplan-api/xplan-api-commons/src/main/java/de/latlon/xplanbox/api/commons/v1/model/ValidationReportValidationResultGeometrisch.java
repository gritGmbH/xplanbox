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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationReportValidationResultGeometrisch {

	private @Valid Boolean valid;

	private @Valid List<String> errors = new ArrayList<String>();

	private @Valid List<String> warnings = new ArrayList<String>();

	/**
	 *
	 **/
	public ValidationReportValidationResultGeometrisch valid(Boolean valid) {
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
	public ValidationReportValidationResultGeometrisch errors(List<String> errors) {
		this.errors = errors;
		return this;
	}

	@Schema
	@JsonProperty("errors")
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	/**
	 *
	 **/
	public ValidationReportValidationResultGeometrisch warnings(List<String> warnings) {
		this.warnings = warnings;
		return this;
	}

	@Schema
	@JsonProperty("warnings")
	public List<String> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ValidationReportValidationResultGeometrisch validationReportValidationResultGeometrisch = (ValidationReportValidationResultGeometrisch) o;
		return Objects.equals(this.valid, validationReportValidationResultGeometrisch.valid)
				&& Objects.equals(this.errors, validationReportValidationResultGeometrisch.errors)
				&& Objects.equals(this.warnings, validationReportValidationResultGeometrisch.warnings);
	}

	@Override
	public int hashCode() {
		return Objects.hash(valid, errors, warnings);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ValidationReportValidationResultGeometrisch {\n");

		sb.append("    valid: ").append(toIndentedString(valid)).append("\n");
		sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
		sb.append("    warnings: ").append(toIndentedString(warnings)).append("\n");
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
