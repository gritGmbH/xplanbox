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
import de.latlon.xplan.manager.web.shared.edit.ValidityPeriod;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;

/**
 * Datatype for Zeitraum.
 *
 * @deprecated will be removed in a future version.
 * @since 4.4
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2021-11-03T09:34:00.218+01:00[Europe/Berlin]")
@Deprecated
public class Zeitraum {

	private @Valid Date start;

	private @Valid Date ende;

	public static Zeitraum fromValidityPeriod(ValidityPeriod validityPeriod) {
		return new Zeitraum().start(validityPeriod.getStart()).ende(validityPeriod.getEnd());
	}

	public ValidityPeriod toValidityPeriod() {
		return new ValidityPeriod(start, ende);
	}

	/**
	 *
	 **/
	public Zeitraum start(Date start) {
		this.start = start;
		return this;
	}

	@JsonProperty("start")
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 *
	 **/
	public Zeitraum ende(Date ende) {
		this.ende = ende;
		return this;
	}

	@JsonProperty("ende")
	public Date getEnde() {
		return ende;
	}

	public void setEnde(Date ende) {
		this.ende = ende;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Zeitraum zeitraum = (Zeitraum) o;
		return Objects.equals(this.start, zeitraum.start) && Objects.equals(this.ende, zeitraum.ende);
	}

	@Override
	public int hashCode() {
		return Objects.hash(start, ende);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Zeitraum {\n");

		sb.append("    start: ").append(toIndentedString(start)).append("\n");
		sb.append("    ende: ").append(toIndentedString(ende)).append("\n");
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
