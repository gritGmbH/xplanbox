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

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

import static de.latlon.xplan.commons.util.TextPatternConstants.NAME_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.SIMPLE_NAME_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.S_LENGTH;

/**
 * Datatype for Aenderung.
 *
 * @since 4.4
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2021-11-03T09:34:00.218+01:00[Europe/Berlin]")
public class Aenderung {

	@Size(max = S_LENGTH)
	@Pattern(regexp = NAME_PATTERN)
	private @Valid String planName;

	@DecimalMin("1000")
	@DecimalMax("99999")
	private @Valid Integer rechtscharakter;

	@Size(max = S_LENGTH)
	@Pattern(regexp = SIMPLE_NAME_PATTERN)
	private @Valid String nummer;

	/**
	 *
	 **/
	public Aenderung planName(String planName) {
		this.planName = planName;
		return this;
	}

	@JsonProperty("planName")
	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	/**
	 *
	 **/
	public Aenderung rechtscharakter(int rechtscharakter) {
		if (rechtscharakter > 0)
			this.rechtscharakter = rechtscharakter;
		else
			this.rechtscharakter = null;
		return this;
	}

	@JsonProperty("rechtscharakter")
	public Integer getRechtscharakter() {
		return rechtscharakter;
	}

	public void setRechtscharakter(Integer rechtscharakter) {
		this.rechtscharakter = rechtscharakter;
	}

	/**
	 *
	 **/
	public Aenderung nummer(String nummer) {
		this.nummer = nummer;
		return this;
	}

	@JsonProperty("nummer")
	public String getNummer() {
		return nummer;
	}

	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Aenderung aenderung = (Aenderung) o;
		return Objects.equals(this.planName, aenderung.planName)
				&& Objects.equals(this.rechtscharakter, aenderung.rechtscharakter)
				&& Objects.equals(this.nummer, aenderung.nummer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(planName, rechtscharakter, nummer);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Aenderung {\n");

		sb.append("    planName: ").append(toIndentedString(planName)).append("\n");
		sb.append("    rechtscharakter: ").append(toIndentedString(rechtscharakter)).append("\n");
		sb.append("    nummer: ").append(toIndentedString(nummer)).append("\n");
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
