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
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Datatype for PlanInfoXplanModelData with attributes derived from XPlanGML data model.
 *
 * @since 4.0
 */
@Schema(description = "attributes derived from XPlanGML data model")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PlanInfoXplanModelData {

	private @Valid String name;

	private @Valid String nummer;

	private @Valid String internalId;

	private @Valid String ags;

	private @Valid String gemeindeName;

	private @Valid String rechtsstand;

	private @Valid Date inkrafttretensDatum;

	private @Valid List<Bereich> bereiche = new ArrayList<Bereich>();

	/**
	 *
	 **/
	public PlanInfoXplanModelData name(String name) {
		this.name = name;
		return this;
	}

	@Schema(example = "Othmarschen3")
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
	public PlanInfoXplanModelData nummer(String nummer) {
		this.nummer = nummer;
		return this;
	}

	@Schema(example = "-")
	@JsonProperty("nummer")
	public String getNummer() {
		return nummer;
	}

	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

	/**
	 *
	 **/
	public PlanInfoXplanModelData internalId(String internalId) {
		this.internalId = internalId;
		return this;
	}

	@Schema(example = "12341")
	@JsonProperty("internalId")
	public String getInternalId() {
		return internalId;
	}

	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}

	/**
	 *
	 **/
	public PlanInfoXplanModelData ags(String ags) {
		this.ags = ags;
		return this;
	}

	@Schema(example = "02000000")
	@JsonProperty("ags")
	public String getAgs() {
		return ags;
	}

	public void setAgs(String ags) {
		this.ags = ags;
	}

	/**
	 *
	 **/
	public PlanInfoXplanModelData gemeindeName(String gemeindeName) {
		this.gemeindeName = gemeindeName;
		return this;
	}

	@Schema(example = "Gemeindename")
	@JsonProperty("gemeindeName")
	public String getGemeindeName() {
		return gemeindeName;
	}

	public void setGemeindeName(String gemeindeName) {
		this.gemeindeName = gemeindeName;
	}

	/**
	 * translation of code list value of xplan:rechtsstand
	 **/
	public PlanInfoXplanModelData rechtsstand(String rechtsstand) {
		this.rechtsstand = rechtsstand;
		return this;
	}

	@Schema(example = "Satzung", description = "translation of code list value of xplan:rechtsstand")
	@JsonProperty("rechtsstand")
	public String getRechtsstand() {
		return rechtsstand;
	}

	public void setRechtsstand(String rechtsstand) {
		this.rechtsstand = rechtsstand;
	}

	/**
	 *
	 **/
	public PlanInfoXplanModelData inkrafttretensDatum(Date inkrafttretensDatum) {
		this.inkrafttretensDatum = inkrafttretensDatum;
		return this;
	}

	@Schema
	@JsonProperty("inkrafttretensDatum")
	public Date getInkrafttretensDatum() {
		return inkrafttretensDatum;
	}

	public void setInkrafttretensDatum(Date inkrafttretensDatum) {
		this.inkrafttretensDatum = inkrafttretensDatum;
	}

	/**
	 * Bereiche related to the plan
	 **/
	public PlanInfoXplanModelData bereiche(List<Bereich> bereiche) {
		this.bereiche = bereiche;
		return this;
	}

	@Schema(description = "Bereiche related to the Plan")
	@JsonProperty("bereiche")
	public List<Bereich> getBereich() {
		return bereiche;
	}

	public void setBereich(List<Bereich> bereiche) {
		this.bereiche = bereiche;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PlanInfoXplanModelData planInfoXplanModelData = (PlanInfoXplanModelData) o;
		return Objects.equals(this.name, planInfoXplanModelData.name)
				&& Objects.equals(this.nummer, planInfoXplanModelData.nummer)
				&& Objects.equals(this.internalId, planInfoXplanModelData.internalId)
				&& Objects.equals(this.ags, planInfoXplanModelData.ags)
				&& Objects.equals(this.gemeindeName, planInfoXplanModelData.gemeindeName)
				&& Objects.equals(this.rechtsstand, planInfoXplanModelData.rechtsstand)
				&& Objects.equals(this.inkrafttretensDatum, planInfoXplanModelData.inkrafttretensDatum)
				&& Objects.equals(this.bereiche, planInfoXplanModelData.bereiche);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, nummer, internalId, ags, gemeindeName, rechtsstand, inkrafttretensDatum, bereiche);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PlanInfoXplanModelData {\n");

		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    nummer: ").append(toIndentedString(nummer)).append("\n");
		sb.append("    internalId: ").append(toIndentedString(internalId)).append("\n");
		sb.append("    ags: ").append(toIndentedString(ags)).append("\n");
		sb.append("    gemeindeName: ").append(toIndentedString(gemeindeName)).append("\n");
		sb.append("    rechtsstand: ").append(toIndentedString(rechtsstand)).append("\n");
		sb.append("    inkrafttretensDatum: ").append(toIndentedString(inkrafttretensDatum)).append("\n");
		sb.append("    bereiche: ").append(toIndentedString(bereiche)).append("\n");
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
