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
import de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt;
import de.latlon.xplan.manager.web.shared.edit.MimeTypes;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.ReferenceType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Datatype for Dokument.
 *
 * @since 4.4
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2021-11-03T09:34:00.218+01:00[Europe/Berlin]")
public class Dokument extends Referenz {

	private String id;

	@DecimalMin("1000")
	@DecimalMax("99999")
	private @Valid Integer typ;

	public static Dokument fromReference(String dokumentId, Reference reference) {
		Dokument dokument = new Dokument().id(dokumentId);
		if (reference.getType() != null) {
			dokument.typ(reference.getType().getSpezExterneReferenceType());
		}
		dokument.art(reference.getArt() != null ? reference.getArt().getCode() : null)
			.beschreibung(reference.getBeschreibung())
			.datum(reference.getDatum())
			.georefMimeType(reference.getGeorefMimeType() != null ? reference.getGeorefMimeType().getCode() : null)
			.georefURL(reference.getGeoReference())
			.informationssystemURL(reference.getInformationssystemURL())
			.referenzMimeType(
					reference.getReferenzMimeType() != null ? reference.getReferenzMimeType().getCode() : null)
			.referenzURL(reference.getReference())
			.referenzName(reference.getReferenzName());
		return dokument;
	}

	public static Reference toReference(Dokument dokumentModel) {
		ReferenceType type = ReferenceType.getBySpezExterneReferenceType(dokumentModel.getTyp());
		Reference reference = new Reference(dokumentModel.getReferenzURL(), dokumentModel.getGeorefURL(), type);
		reference.setReferenzMimeType(MimeTypes.getByCode(dokumentModel.getReferenzMimeType()));
		reference.setReferenzName(dokumentModel.getReferenzName());
		reference.setGeorefMimeType(MimeTypes.getByCode(dokumentModel.getGeorefMimeType()));
		reference.setArt(ExterneReferenzArt.getByCode(dokumentModel.getArt()));
		reference.setBeschreibung(dokumentModel.getBeschreibung());
		reference.setDatum(dokumentModel.getDatum());
		reference.setInformationssystemURL(dokumentModel.getInformationssystemURL());
		return reference;
	}

	public Dokument id(String id) {
		this.id = id;
		return this;
	}

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
	public Dokument typ(int typ) {
		if (typ > 0)
			this.typ = typ;
		else
			this.typ = null;
		return this;
	}

	@JsonProperty("typ")
	public Integer getTyp() {
		return typ;
	}

	public void setTyp(Integer typ) {
		this.typ = typ;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Dokument dokument = (Dokument) o;
		return Objects.equals(typ, dokument.typ);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), typ);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Dokument {\n");
		sb.append("    georefURL: ").append(toIndentedString(getGeorefURL())).append("\n");
		sb.append("    georefMimeType: ").append(toIndentedString(getGeorefMimeType())).append("\n");
		sb.append("    art: ").append(toIndentedString(getArt())).append("\n");
		sb.append("    informationssystemURL: ").append(toIndentedString(getInformationssystemURL())).append("\n");
		sb.append("    referenzName: ").append(toIndentedString(getReferenzName())).append("\n");
		sb.append("    referenzURL: ").append(toIndentedString(getReferenzURL())).append("\n");
		sb.append("    referenzMimeType: ").append(toIndentedString(getReferenzMimeType())).append("\n");
		sb.append("    beschreibung: ").append(toIndentedString(getBeschreibung())).append("\n");
		sb.append("    datum: ").append(toIndentedString(getDatum())).append("\n");
		sb.append("    typ: ").append(toIndentedString(typ)).append("\n");
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
