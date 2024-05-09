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

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationReportValidationResultSemantischProfil {

	private @Valid String name;

	private @Valid String description;

	private @Valid ValidationReportValidationResultSemantisch result;

	/**
	 *
	 **/
	public ValidationReportValidationResultSemantischProfil name(String name) {
		this.name = name;
		return this;
	}

	@Schema(example = "GemeindeMusterdorf")
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
	public ValidationReportValidationResultSemantischProfil description(String description) {
		this.description = description;
		return this;
	}

	@Schema(example = "Beschreibung des Profils der Gemeinde Musterdorf")
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 *
	 **/
	public ValidationReportValidationResultSemantischProfil result(ValidationReportValidationResultSemantisch result) {
		this.result = result;
		return this;
	}

	@Schema
	@JsonProperty("ergebnis")
	public ValidationReportValidationResultSemantisch getResult() {
		return result;
	}

	public void setResult(ValidationReportValidationResultSemantisch result) {
		this.result = result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ValidationReportValidationResultSemantischProfil validationReportValidationResultSemantisch = (ValidationReportValidationResultSemantischProfil) o;
		return Objects.equals(this.name, validationReportValidationResultSemantisch.name)
				&& Objects.equals(this.description, validationReportValidationResultSemantisch.description)
				&& Objects.equals(this.result, validationReportValidationResultSemantisch.result);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, result);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ValidationReportValidationResultSemantisch {\n");

		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    result: ").append(toIndentedString(result)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the
	 * first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
