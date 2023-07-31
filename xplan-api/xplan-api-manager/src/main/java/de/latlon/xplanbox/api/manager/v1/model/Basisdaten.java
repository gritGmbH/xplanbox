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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.latlon.xplan.manager.web.shared.edit.BaseData;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;

import static de.latlon.xplan.commons.util.TextPatternConstants.DESCRIPTION_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.L_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.NAME_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.S_LENGTH;

/**
 * Datatype for Basisdaten.
 *
 * @since 4.4
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2021-11-03T09:34:00.218+01:00[Europe/Berlin]")
public class Basisdaten {

	@Size(max = S_LENGTH)
	@Pattern(regexp = NAME_PATTERN)
	private @Valid String name;

	@Size(max = L_LENGTH)
	@Pattern(regexp = DESCRIPTION_PATTERN)
	private @Valid String beschreibung;

	@DecimalMin("1000")
	@DecimalMax("99999")
	private @Valid Integer planArt;

	// https://www.jira.geoportal-hamburg.de/browse/XPLANBOX-1227
	@JsonIgnore
	@DecimalMin("1000")
	@DecimalMax("99999")
	private @Valid Integer sonstPlanArt;

	@DecimalMin("1000")
	@DecimalMax("99999")
	private @Valid Integer verfahren;

	@DecimalMin("1000")
	@DecimalMax("99999")
	private @Valid Integer rechtsstand;

	private @Valid Date rechtsverordnungsDatum;

	private @Valid Date technHerstellDatum;

	private @Valid Date untergangsDatum;

	public static Basisdaten fromBaseData(BaseData baseData) {
		return new Basisdaten().name(baseData.getPlanName())
			.beschreibung(baseData.getDescription())
			.planArt(baseData.getPlanTypeCode())
			.sonstPlanArt(baseData.getOtherPlanTypeCode())
			.verfahren(baseData.getMethodCode())
			.rechtsstand(baseData.getLegislationStatusCode())
			.rechtsverordnungsDatum(baseData.getRegulationDate())
			.technHerstellDatum(baseData.getCreationDate())
			.untergangsDatum(baseData.getLossDate());
	}

	public BaseData toBaseData() {
		return new BaseData(name, beschreibung, technHerstellDatum, untergangsDatum, asInt(planArt),
				asInt(sonstPlanArt), asInt(verfahren), asInt(rechtsstand), rechtsverordnungsDatum);
	}

	private int asInt(Integer code) {
		if (code == null)
			return -1;
		return code;
	}

	/**
	 *
	 **/
	public Basisdaten name(String name) {
		this.name = name;
		return this;
	}

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
	public Basisdaten beschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
		return this;
	}

	@JsonProperty("beschreibung")
	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 *
	 **/
	public Basisdaten planArt(int planArt) {
		if (planArt > 0)
			this.planArt = planArt;
		else
			this.planArt = null;
		return this;
	}

	@JsonProperty("planArt")
	public Integer getPlanArt() {
		return planArt;
	}

	public void setPlanArt(Integer planArt) {
		this.planArt = planArt;
	}

	/**
	 *
	 **/
	public Basisdaten sonstPlanArt(int sonstPlanArt) {
		if (sonstPlanArt > 0)
			this.sonstPlanArt = sonstPlanArt;
		else
			this.sonstPlanArt = null;
		return this;
	}

	// https://www.jira.geoportal-hamburg.de/browse/XPLANBOX-1227
	// @JsonProperty("sonstPlanArt")
	public Integer getSonstPlanArt() {
		return sonstPlanArt;
	}

	public void setSonstPlanArt(Integer sonstPlanArt) {
		this.sonstPlanArt = sonstPlanArt;
	}

	/**
	 *
	 **/
	public Basisdaten verfahren(int verfahren) {
		if (verfahren > 0)
			this.verfahren = verfahren;
		else
			this.verfahren = null;
		return this;
	}

	@JsonProperty("verfahren")
	public Integer getVerfahren() {
		return verfahren;
	}

	public void setVerfahren(Integer verfahren) {
		this.verfahren = verfahren;
	}

	/**
	 *
	 **/
	public Basisdaten rechtsstand(int rechtsstand) {
		if (rechtsstand > 0)
			this.rechtsstand = rechtsstand;
		else
			this.rechtsstand = null;
		return this;
	}

	@JsonProperty("rechtsstand")
	public Integer getRechtsstand() {
		return rechtsstand;
	}

	public void setRechtsstand(Integer rechtsstand) {
		this.rechtsstand = rechtsstand;
	}

	/**
	 *
	 **/
	public Basisdaten rechtsverordnungsDatum(Date rechtsverordnungsDatum) {
		this.rechtsverordnungsDatum = rechtsverordnungsDatum;
		return this;
	}

	@JsonProperty("rechtsverordnungsDatum")
	public Date getRechtsverordnungsDatum() {
		return rechtsverordnungsDatum;
	}

	public void setRechtsverordnungsDatum(Date rechtsverordnungsDatum) {
		this.rechtsverordnungsDatum = rechtsverordnungsDatum;
	}

	/**
	 *
	 **/
	public Basisdaten technHerstellDatum(Date technHerstellDatum) {
		this.technHerstellDatum = technHerstellDatum;
		return this;
	}

	@JsonProperty("technHerstellDatum")
	public Date getTechnHerstellDatum() {
		return technHerstellDatum;
	}

	public void setTechnHerstellDatum(Date technHerstellDatum) {
		this.technHerstellDatum = technHerstellDatum;
	}

	/**
	 *
	 **/
	public Basisdaten untergangsDatum(Date untergangsDatum) {
		this.untergangsDatum = untergangsDatum;
		return this;
	}

	@JsonProperty("untergangsDatum")
	public Date getUntergangsDatum() {
		return untergangsDatum;
	}

	public void setUntergangsDatum(Date untergangsDatum) {
		this.untergangsDatum = untergangsDatum;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Basisdaten basisdaten = (Basisdaten) o;
		return Objects.equals(this.name, basisdaten.name) && Objects.equals(this.beschreibung, basisdaten.beschreibung)
				&& Objects.equals(this.planArt, basisdaten.planArt)
				&& Objects.equals(this.sonstPlanArt, basisdaten.sonstPlanArt)
				&& Objects.equals(this.verfahren, basisdaten.verfahren)
				&& Objects.equals(this.rechtsstand, basisdaten.rechtsstand)
				&& Objects.equals(this.rechtsverordnungsDatum, basisdaten.rechtsverordnungsDatum)
				&& Objects.equals(this.technHerstellDatum, basisdaten.technHerstellDatum)
				&& Objects.equals(this.untergangsDatum, basisdaten.untergangsDatum);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, beschreibung, planArt, sonstPlanArt, verfahren, rechtsstand, rechtsverordnungsDatum,
				technHerstellDatum, untergangsDatum);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Basisdaten {\n");

		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    beschreibung: ").append(toIndentedString(beschreibung)).append("\n");
		sb.append("    planArt: ").append(toIndentedString(planArt)).append("\n");
		sb.append("    sonstPlanArt: ").append(toIndentedString(sonstPlanArt)).append("\n");
		sb.append("    verfahren: ").append(toIndentedString(verfahren)).append("\n");
		sb.append("    rechtsstand: ").append(toIndentedString(rechtsstand)).append("\n");
		sb.append("    rechtsverordnungsDatum: ").append(toIndentedString(rechtsverordnungsDatum)).append("\n");
		sb.append("    technHerstellDatum: ").append(toIndentedString(technHerstellDatum)).append("\n");
		sb.append("    untergangsDatum: ").append(toIndentedString(untergangsDatum)).append("\n");
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
