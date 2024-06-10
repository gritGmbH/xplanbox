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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RulesMetadata {

	@JsonInclude(NON_NULL)
	private @Valid String id;

	@JsonInclude(NON_NULL)
	private @Valid String name;

	@JsonInclude(NON_NULL)
	private @Valid String description;

	@JsonInclude(NON_NULL)
	private @Valid String version;

	@JsonInclude(NON_NULL)
	private @Valid String source;

	/**
	 *
	 **/
	public RulesMetadata id(String id) {
		this.id = id;
		return this;
	}

	@Schema(example = "profil1")
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 **/
	public RulesMetadata name(String name) {
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
	public RulesMetadata description(String description) {
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
	public RulesMetadata version(String version) {
		this.version = version;
		return this;
	}

	@Schema(example = "0.9.14")
	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 *
	 **/
	public RulesMetadata source(String source) {
		this.source = source;
		return this;
	}

	@Schema(example = "https://bitbucket.org/geowerkstatt-hamburg/xplanung/get/v0.9.14.zip")
	@JsonProperty("source")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RulesMetadata rulesMetadata = (RulesMetadata) o;
		return Objects.equals(this.name, rulesMetadata.name)
				&& Objects.equals(this.description, rulesMetadata.description)
				&& Objects.equals(this.version, rulesMetadata.version)
				&& Objects.equals(this.source, rulesMetadata.source);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, version, source);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class RulesMetadata {\n");

		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("    source: ").append(toIndentedString(source)).append("\n");
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
