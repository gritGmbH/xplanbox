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
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
public class SystemConfig {

	private @Valid String version;

	private @Valid RulesMetadata rulesMetadata;

	@JsonInclude(NON_EMPTY)
	private @Valid List<RulesMetadata> profiles = new ArrayList<>();

	private @Valid List<VersionEnum> supportedXPlanGmlVersions = new ArrayList<VersionEnum>();

	/**
	 * Version der xPlanBox
	 **/
	public SystemConfig version(String version) {
		this.version = version;
		return this;
	}

	@Schema(example = "v3.4.0", description = "Version der xPlanBox")
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
	public SystemConfig rulesMetadata(RulesMetadata rulesMetadata) {
		this.rulesMetadata = rulesMetadata;
		return this;
	}

	@Schema
	@JsonProperty("rulesMetadata")
	public RulesMetadata getRulesMetadata() {
		return rulesMetadata;
	}

	public void setRulesMetadata(RulesMetadata rulesMetadata) {
		this.rulesMetadata = rulesMetadata;
	}

	/**
	 *
	 **/
	public SystemConfig profiles(List<RulesMetadata> profiles) {
		this.profiles = profiles;
		return this;
	}

	@ArraySchema(schema = @Schema(description = "Suported validation profiles"))
	@JsonProperty("profiles")
	public List<RulesMetadata> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<RulesMetadata> profiles) {
		this.profiles = profiles;
	}

	/**
	 *
	 **/
	public SystemConfig supportedXPlanGmlVersions(List<VersionEnum> supportedXPlanGmlVersions) {
		this.supportedXPlanGmlVersions = supportedXPlanGmlVersions;
		return this;
	}

	@ArraySchema(schema = @Schema(example = "XPLAN_51"))
	@JsonProperty("supportedXPlanGmlVersions")
	public List<VersionEnum> getSupportedXPlanGmlVersions() {
		return supportedXPlanGmlVersions;
	}

	public void setSupportedXPlanGmlVersions(List<VersionEnum> supportedXPlanGmlVersions) {
		this.supportedXPlanGmlVersions = supportedXPlanGmlVersions;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SystemConfig systemConfig = (SystemConfig) o;
		return Objects.equals(this.version, systemConfig.version)
				&& Objects.equals(this.rulesMetadata, systemConfig.rulesMetadata)
				&& Objects.equals(this.profiles, systemConfig.profiles)
				&& Objects.equals(this.supportedXPlanGmlVersions, systemConfig.supportedXPlanGmlVersions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(version, rulesMetadata, profiles, supportedXPlanGmlVersions);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SystemConfig {\n");

		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("    rulesMetadata: ").append(toIndentedString(rulesMetadata)).append("\n");
		sb.append("    profiles: ").append(toIndentedString(profiles)).append("\n");
		sb.append("    supportedXPlanGmlVersions: ").append(toIndentedString(supportedXPlanGmlVersions)).append("\n");
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
