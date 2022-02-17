/*-
 * #%L
 * xplan-api-commons - xplan-api-commons
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationReport {

	private @Valid VersionEnum version;

	private @Valid String filename;

	private @Valid String name;

	private @Valid PlanInfoBbox bbox;

	private @Valid Date date;

	private @Valid Boolean valid;

	private @Valid List<String> externalReferences = new ArrayList<String>();

	private @Valid @JsonInclude(Include.NON_NULL) URI wmsUrl;

	private @Valid RulesMetadata rulesMetadata;

	private @Valid ValidationReportValidationResult validationResult;

	/**
	 **/
	public ValidationReport filename(String filename) {
		this.filename = filename;
		return this;
	}

	@Schema(example = "xplan52-test.gml")
	@JsonProperty("filename")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	**/
	public ValidationReport name(String name) {
		this.name = name;
		return this;
	}

	@Schema(example = "xplan52-test")
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	**/
	public ValidationReport version(VersionEnum version) {
		this.version = version;
		return this;
	}

	@Schema(example = "XPLAN_51")
	@JsonProperty("version")
	public VersionEnum getVersion() {
		return version;
	}

	public void setVersion(VersionEnum version) {
		this.version = version;
	}

	/**
	**/

	public ValidationReport bbox(PlanInfoBbox bbox) {
		this.bbox = bbox;
		return this;
	}

	@Schema
	@JsonProperty("bbox")
	public PlanInfoBbox getBbox() {
		return bbox;
	}

	public void setBbox(PlanInfoBbox bbox) {
		this.bbox = bbox;
	}

	public ValidationReport date(Date date) {
		this.date = date;
		return this;
	}

	@Schema(example = "2020-08-24T15:06:36.662Z")
	@JsonProperty("date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/**
	**/
	public ValidationReport valid(Boolean valid) {
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
	**/
	public ValidationReport externalReferences(List<String> externalReferences) {
		this.externalReferences = externalReferences;
		return this;
	}

	@ArraySchema(schema = @Schema(example = "stelling.png"))
	@JsonProperty("externalReferences")
	public List<String> getExternalReferences() {
		return externalReferences;
	}

	public void setExternalReferences(List<String> externalReferences) {
		this.externalReferences = externalReferences;
	}

	/**
	**/
	public ValidationReport wmsUrl(URI wmsUrl) {
		this.wmsUrl = wmsUrl;
		return this;
	}

	@Schema(example = "https://xplanbox.lat-lon.de/xplan-validator-wms/services/wms?PLANWERK_MANAGERID=13")
	@JsonProperty("wmsUrl")
	public URI getWmsUrl() {
		return wmsUrl;
	}

	public void setWmsUrl(URI wmsUrl) {
		this.wmsUrl = wmsUrl;
	}

	/**
	**/
	public ValidationReport rulesMetadata(RulesMetadata rulesMetadata) {
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
	**/
	public ValidationReport validationResult(ValidationReportValidationResult validationResult) {
		this.validationResult = validationResult;
		return this;
	}

	@Schema
	@JsonProperty("validationResult")
	public ValidationReportValidationResult getValidationResult() {
		return validationResult;
	}

	public void setValidationResult(ValidationReportValidationResult validationResult) {
		this.validationResult = validationResult;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ValidationReport validationReport = (ValidationReport) o;
		return Objects.equals(this.filename, validationReport.filename)
				&& Objects.equals(this.name, validationReport.name)
				&& Objects.equals(this.version, validationReport.version)
				&& Objects.equals(this.bbox, validationReport.bbox) && Objects.equals(this.date, validationReport.date)
				&& Objects.equals(this.valid, validationReport.valid)
				&& Objects.equals(this.externalReferences, validationReport.externalReferences)
				&& Objects.equals(this.wmsUrl, validationReport.wmsUrl)
				&& Objects.equals(this.rulesMetadata, validationReport.rulesMetadata)
				&& Objects.equals(this.validationResult, validationReport.validationResult);
	}

	@Override
	public int hashCode() {
		return Objects.hash(filename, name, version, bbox, date, valid, externalReferences, wmsUrl, rulesMetadata,
				validationResult);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ValidationReport {\n");

		sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("    bbox: ").append(toIndentedString(bbox)).append("\n");
		sb.append("    date: ").append(toIndentedString(date)).append("\n");
		sb.append("    valid: ").append(toIndentedString(valid)).append("\n");
		sb.append("    externalReferences: ").append(toIndentedString(externalReferences)).append("\n");
		sb.append("    wmsUrl: ").append(toIndentedString(wmsUrl)).append("\n");
		sb.append("    rulesMetadata: ").append(toIndentedString(rulesMetadata)).append("\n");
		sb.append("    validationResult: ").append(toIndentedString(validationResult)).append("\n");
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
