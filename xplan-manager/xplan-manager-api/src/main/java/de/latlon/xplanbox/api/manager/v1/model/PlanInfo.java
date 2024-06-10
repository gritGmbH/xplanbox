/*-
 * #%L
 * xplan-manager-api - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.latlon.xplanbox.api.commons.v1.model.PlanInfoBbox;
import de.latlon.xplanbox.api.commons.v1.model.VersionEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.Valid;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Datatype for PlanInfo.
 *
 * @since 4.0
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PlanInfo {

	private @Valid Integer id;

	private @Valid String type;

	private @Valid VersionEnum version;

	private @Valid PlanStatusEnum planStatus;

	private @Valid Boolean raster;

	private @Valid Date importDate;

	private @Valid Boolean inspirePublished;

	private @Valid PlanInfoBbox bbox;

	private @Valid PlanInfoXplanModelData xplanModelData;

	private @Valid List<Link> links = new ArrayList<Link>();

	/**
	 * internal unique manager identifier, generated at import time
	 **/
	public PlanInfo id(Integer id) {
		this.id = id;
		return this;
	}

	@Schema(format = "int32", example = "123",
			description = "internal unique manager identifier, generated at import time")
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 *
	 **/
	public PlanInfo type(String type) {
		this.type = type;
		return this;
	}

	@Schema(example = "BP_Plan", description = "")
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 *
	 **/
	public PlanInfo version(VersionEnum version) {
		this.version = version;
		return this;
	}

	@Schema(example = "XPLAN_51", description = "")
	@JsonProperty("version")
	public VersionEnum getVersion() {
		return version;
	}

	public void setVersion(VersionEnum version) {
		this.version = version;
	}

	/**
	 *
	 **/
	public PlanInfo planStatus(PlanStatusEnum planStatus) {
		this.planStatus = planStatus;
		return this;
	}

	@Schema(example = "Festgestellt")
	@JsonProperty("planStatus")
	public PlanStatusEnum getplanStatus() {
		return planStatus;
	}

	public void setplanStatus(PlanStatusEnum planStatus) {
		this.planStatus = planStatus;
	}

	/**
	 *
	 **/
	public PlanInfo raster(Boolean raster) {
		this.raster = raster;
		return this;
	}

	@Schema(example = "true", description = "")
	@JsonProperty("raster")
	public Boolean getRaster() {
		return raster;
	}

	public void setRaster(Boolean raster) {
		this.raster = raster;
	}

	/**
	 *
	 **/
	public PlanInfo importDate(Date importDate) {
		this.importDate = importDate;
		return this;
	}

	@Schema(description = "")
	@JsonProperty("importDate")
	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	/**
	 *
	 **/
	public PlanInfo inspirePublished(Boolean inspirePublished) {
		this.inspirePublished = inspirePublished;
		return this;
	}

	@Schema(example = "false", description = "")
	@JsonProperty("inspirePublished")
	public Boolean getInspirePublished() {
		return inspirePublished;
	}

	public void setInspirePublished(Boolean inspirePublished) {
		this.inspirePublished = inspirePublished;
	}

	/**
	 *
	 **/
	public PlanInfo bbox(PlanInfoBbox bbox) {
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

	/**
	 *
	 **/
	public PlanInfo xplanModelData(PlanInfoXplanModelData xplanModelData) {
		this.xplanModelData = xplanModelData;
		return this;
	}

	@Schema
	@JsonProperty("xplanModelData")
	public PlanInfoXplanModelData getXplanModelData() {
		return xplanModelData;
	}

	public void setXplanModelData(PlanInfoXplanModelData xplanModelData) {
		this.xplanModelData = xplanModelData;
	}

	/**
	 * Links related to the resource such as XPlanWerkWMS or self link
	 **/
	public PlanInfo links(List<Link> links) {
		this.links = links;
		return this;
	}

	@Schema(example = "{\"href\":\"https://xplanbox.lat-lon.de/xplan-wms/services/planwerkwms/planname/<PLANNAME>?\",\"rel\":\"planwerkwms\",\"type\":\"image/png\",\"hreflang\":\"de\",\"title\":\"Othmarschen 3\"}",
			description = "Links related to the resource such as XPlanWerkWMS or self link")
	@JsonProperty("links")
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PlanInfo planInfo = (PlanInfo) o;
		return Objects.equals(this.id, planInfo.id) && Objects.equals(this.type, planInfo.type)
				&& Objects.equals(this.version, planInfo.version) && Objects.equals(this.raster, planInfo.raster)
				&& Objects.equals(this.importDate, planInfo.importDate)
				&& Objects.equals(this.inspirePublished, planInfo.inspirePublished)
				&& Objects.equals(this.bbox, planInfo.bbox)
				&& Objects.equals(this.xplanModelData, planInfo.xplanModelData)
				&& Objects.equals(this.links, planInfo.links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type, version, raster, importDate, inspirePublished, bbox, xplanModelData, links);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PlanInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("    raster: ").append(toIndentedString(raster)).append("\n");
		sb.append("    importDate: ").append(toIndentedString(importDate)).append("\n");
		sb.append("    inspirePublished: ").append(toIndentedString(inspirePublished)).append("\n");
		sb.append("    bbox: ").append(toIndentedString(bbox)).append("\n");
		sb.append("    xplanModelData: ").append(toIndentedString(xplanModelData)).append("\n");
		sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
