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
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SemanticInvalidRuleResult {

	private @Valid String message;

	private @Valid List<String> invalidGmlIds = new ArrayList<String>();

	public SemanticInvalidRuleResult message(String message) {
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

	public SemanticInvalidRuleResult invalidGmlIds(List<String> invalidGmlIds) {
		this.invalidGmlIds = invalidGmlIds;
		return this;
	}

	@ArraySchema(schema = @Schema(description = "GML-Id", example = "GML_b4e47d29-d21c-42ab-85b7-b12ea57e89f2"))
	@JsonProperty("invalidGmlIds")
	public List<String> getInvalidGmlIds() {
		return invalidGmlIds;
	}

	public void setInvalidGmlIds(List<String> invalidFeatures) {
		this.invalidGmlIds = invalidFeatures;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SemanticInvalidRuleResult that = (SemanticInvalidRuleResult) o;
		return Objects.equals(message, that.message) && Objects.equals(invalidGmlIds, that.invalidGmlIds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, invalidGmlIds);
	}

	@Override
	public String toString() {
		return "SemanticInvalidRuleResult{" + "message='" + message + '\'' + ", invalidGmlIds=" + invalidGmlIds + '}';
	}

}
