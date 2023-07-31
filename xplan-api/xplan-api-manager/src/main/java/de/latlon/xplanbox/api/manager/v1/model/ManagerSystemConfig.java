/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
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
package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.latlon.xplanbox.api.commons.v1.model.SystemConfig;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Datatype for ManagerSystemConfig.
 *
 * @since 4.0
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ManagerSystemConfig extends SystemConfig {

	private @Valid String rasterCrs;

	private @Valid String rasterType;

	private @Valid Boolean skipSemantisch = false;

	private @Valid Boolean skipGeometrisch = false;

	private @Valid Boolean skipFlaechenschluss = false;

	private @Valid Boolean skipGeltungsbereich = false;

	private @Valid Boolean skipLaufrichtung = false;

	@JsonInclude(NON_NULL)
	private @Valid String documentUrl;

	/**
	 * Konfiguriertes CRS für die Rasterdatenhaltung
	 **/
	public ManagerSystemConfig rasterCrs(String rasterCrs) {
		this.rasterCrs = rasterCrs;
		return this;
	}

	@Schema(example = "epsg:28352", description = "Konfiguriertes CRS für die Rasterdatenhaltung")
	@JsonProperty("rasterCrs")
	public String getRasterCrs() {
		return rasterCrs;
	}

	public void setRasterCrs(String rasterCrs) {
		this.rasterCrs = rasterCrs;
	}

	/**
	 * Typ der Rasterdatenhaltung: gdal oder tiff
	 **/
	public ManagerSystemConfig rasterType(String rasterType) {
		this.rasterType = rasterType;
		return this;
	}

	@Schema(example = "gdal", description = "Typ der Rasterdatenhaltung: gdal oder tiff")
	@JsonProperty("rasterType")
	public String getRasterType() {
		return rasterType;
	}

	public void setRasterType(String rasterType) {
		this.rasterType = rasterType;
	}

	/**
	 * Semantische Validierung bei Import ueberspringen
	 **/
	public ManagerSystemConfig skipSemantisch(Boolean skipSemantisch) {
		this.skipSemantisch = skipSemantisch;
		return this;
	}

	@Schema(description = "Semantische Validierung bei Import ueberspringen")
	@JsonProperty("skipSemantisch")
	public Boolean getSkipSemantisch() {
		return skipSemantisch;
	}

	public void setSkipSemantisch(Boolean skipSemantisch) {
		this.skipSemantisch = skipSemantisch;
	}

	/**
	 * Geometrische Validierung bei Import ueberspringen
	 **/
	public ManagerSystemConfig skipGeometrisch(Boolean skipGeometrisch) {
		this.skipGeometrisch = skipGeometrisch;
		return this;
	}

	@Schema(description = "Geometrische Validierung bei Import ueberspringen")
	@JsonProperty("skipGeometrisch")
	public Boolean getSkipGeometrisch() {
		return skipGeometrisch;
	}

	public void setSkipGeometrisch(Boolean skipGeometrisch) {
		this.skipGeometrisch = skipGeometrisch;
	}

	/**
	 * Ueberpruefung des Flaechenschluss bei Import ueberspringen
	 **/
	public ManagerSystemConfig skipFlaechenschluss(Boolean skipFlaechenschluss) {
		this.skipFlaechenschluss = skipFlaechenschluss;
		return this;
	}

	@Schema(description = "Ueberpruefung des Flaechenschluss bei Import ueberspringen")
	@JsonProperty("skipFlaechenschluss")
	public Boolean getSkipFlaechenschluss() {
		return skipFlaechenschluss;
	}

	public void setSkipFlaechenschluss(Boolean skipFlaechenschluss) {
		this.skipFlaechenschluss = skipFlaechenschluss;
	}

	/**
	 * Ueberpruefung des Geltungsbereich bei Import ueberspringen
	 **/
	public ManagerSystemConfig skipGeltungsbereich(Boolean skipGeltungsbereich) {
		this.skipGeltungsbereich = skipGeltungsbereich;
		return this;
	}

	@Schema(description = "Ueberpruefung des Geltungsbereich bei Import ueberspringen")
	@JsonProperty("skipGeltungsbereich")
	public Boolean getSkipGeltungsbereich() {
		return skipGeltungsbereich;
	}

	public void setSkipGeltungsbereich(Boolean skipGeltungsbereich) {
		this.skipGeltungsbereich = skipGeltungsbereich;
	}

	/**
	 * Ueberpruefung der Laufrichtung bei Import ueberspringen
	 **/
	public ManagerSystemConfig skipLaufrichtung(Boolean skipLaufrichtung) {
		this.skipLaufrichtung = skipLaufrichtung;
		return this;
	}

	@Schema(description = "Ueberpruefung der Laufrichtung bei Import ueberspringen")
	@JsonProperty("skipLaufrichtung")
	public Boolean getSkipLaufrichtung() {
		return skipLaufrichtung;
	}

	public void setSkipLaufrichtung(Boolean skipLaufrichtung) {
		this.skipLaufrichtung = skipLaufrichtung;
	}

	/**
	 * Typ der Rasterdatenhaltung: gdal oder tiff
	 **/
	public ManagerSystemConfig documentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
		return this;
	}

	@Schema(example = "http://example.org/xdokumente/api/v1/dokument/{planId}/{fileName}",
			description = "URL ueber die alle Anlagen zu einem Plan heruntergeladen werden koennen")
	@JsonProperty("documentUrl")
	public String getDocumentUrl() {
		return documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ManagerSystemConfig managerSystemConfig = (ManagerSystemConfig) o;
		return super.equals(o) && Objects.equals(this.rasterCrs, managerSystemConfig.rasterCrs)
				&& Objects.equals(this.rasterType, managerSystemConfig.rasterType)
				&& Objects.equals(this.skipSemantisch, managerSystemConfig.skipSemantisch)
				&& Objects.equals(this.skipGeometrisch, managerSystemConfig.skipGeometrisch)
				&& Objects.equals(this.skipFlaechenschluss, managerSystemConfig.skipFlaechenschluss)
				&& Objects.equals(this.skipGeltungsbereich, managerSystemConfig.skipGeltungsbereich)
				&& Objects.equals(this.skipLaufrichtung, managerSystemConfig.skipLaufrichtung);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(new int[] { super.hashCode(), Objects.hash(rasterCrs, rasterType, skipSemantisch,
				skipGeometrisch, skipFlaechenschluss, skipGeltungsbereich, skipLaufrichtung) });
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ManagerSystemConfig {\n");

		sb.append("    version: ").append(toIndentedString(getVersion())).append("\n");
		sb.append("    rulesMetadata: ").append(toIndentedString(getRulesMetadata())).append("\n");
		sb.append("    supportedXPlanGmlVersions: ")
			.append(toIndentedString(getSupportedXPlanGmlVersions()))
			.append("\n");
		sb.append("    rasterCrs: ").append(toIndentedString(rasterCrs)).append("\n");
		sb.append("    rasterType: ").append(toIndentedString(rasterType)).append("\n");
		sb.append("    skipSemantisch: ").append(toIndentedString(skipSemantisch)).append("\n");
		sb.append("    skipGeometrisch: ").append(toIndentedString(skipGeometrisch)).append("\n");
		sb.append("    skipFlaechenschluss: ").append(toIndentedString(skipFlaechenschluss)).append("\n");
		sb.append("    skipGeltungsbereich: ").append(toIndentedString(skipGeltungsbereich)).append("\n");
		sb.append("    skipLaufrichtung: ").append(toIndentedString(skipLaufrichtung)).append("\n");
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
