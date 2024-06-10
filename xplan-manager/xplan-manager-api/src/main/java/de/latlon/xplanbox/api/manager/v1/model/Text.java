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
import de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

import static de.latlon.xplan.commons.util.TextPatternConstants.S_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.TEXT_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.XL_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.XS_LENGTH;

/**
 * Datatype for Text.
 *
 * @since 4.4
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2021-11-03T09:34:00.218+01:00[Europe/Berlin]")
public class Text {

	private String id;

	@Size(max = XS_LENGTH)
	@Pattern(regexp = TEXT_PATTERN)
	private @Valid String schluessel;

	@Size(max = S_LENGTH)
	@Pattern(regexp = TEXT_PATTERN)
	private @Valid String gesetzlicheGrundlage;

	@Size(max = XL_LENGTH)
	@Pattern(regexp = TEXT_PATTERN)
	private @Valid String text;

	private @Valid Referenz refText;

	@DecimalMin("1000")
	@DecimalMax("99999")
	private @Valid Integer rechtscharakter;

	public static Text fromText(de.latlon.xplan.manager.web.shared.edit.Text oldText) {
		return fromText(oldText.getFeatureId(), oldText);
	}

	public static Text fromText(String textId, de.latlon.xplan.manager.web.shared.edit.Text oldText) {
		Referenz referenz = new Referenz().art(oldText.getArt() != null ? oldText.getArt().getCode() : null)
			.beschreibung(oldText.getBeschreibung())
			.datum(oldText.getDatum())
			.georefMimeType(oldText.getGeorefMimeType() != null ? oldText.getGeorefMimeType().getCode() : null)
			.georefURL(oldText.getGeoReference())
			.informationssystemURL(oldText.getInformationssystemURL())
			.referenzMimeType(oldText.getReferenzMimeType() != null ? oldText.getReferenzMimeType().getCode() : null)
			.referenzURL(oldText.getReference())
			.referenzName(oldText.getReferenzName());
		Text text = new Text().id(textId)
			.schluessel(oldText.getKey())
			.gesetzlicheGrundlage(oldText.getBasis())
			.text(oldText.getText())
			.rechtscharakter(oldText.getRechtscharakter() != null ? oldText.getRechtscharakter().getCode() : -1)
			.refText(referenz);
		return text;
	}

	public de.latlon.xplan.manager.web.shared.edit.Text toText(String version, String type) {
		de.latlon.xplan.manager.web.shared.edit.Text oldText = new de.latlon.xplan.manager.web.shared.edit.Text();
		oldText.setKey(schluessel);
		oldText.setBasis(gesetzlicheGrundlage);
		oldText.setText(text);
		oldText.setRechtscharakter(TextRechtscharacterType.fromCode(rechtscharakter, version, type));
		if (refText != null) {
			oldText.setReference(refText.getReferenzURL());
			oldText.setReferenzName(refText.getReferenzName());
			oldText.setReferenzMimeType(MimeTypes.getByCode(refText.getReferenzMimeType()));
			oldText.setGeoReference(refText.getGeorefURL());
			oldText.setGeorefMimeType(MimeTypes.getByCode(refText.getGeorefMimeType()));
			oldText.setArt(ExterneReferenzArt.getByCode(refText.getArt()));
			oldText.setBeschreibung(refText.getBeschreibung());
			oldText.setDatum(refText.getDatum());
			oldText.setInformationssystemURL(refText.getInformationssystemURL());
		}
		return oldText;
	}

	public Text id(String id) {
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
	public Text schluessel(String schluessel) {
		this.schluessel = schluessel;
		return this;
	}

	@JsonProperty("schluessel")
	public String getSchluessel() {
		return schluessel;
	}

	public void setSchluessel(String schluessel) {
		this.schluessel = schluessel;
	}

	/**
	 *
	 **/
	public Text gesetzlicheGrundlage(String gesetzlicheGrundlage) {
		this.gesetzlicheGrundlage = gesetzlicheGrundlage;
		return this;
	}

	@JsonProperty("gesetzlicheGrundlage")
	public String getGesetzlicheGrundlage() {
		return gesetzlicheGrundlage;
	}

	public void setGesetzlicheGrundlage(String gesetzlicheGrundlage) {
		this.gesetzlicheGrundlage = gesetzlicheGrundlage;
	}

	/**
	 *
	 **/
	public Text text(String text) {
		this.text = text;
		return this;
	}

	@JsonProperty("text")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 *
	 **/
	public Text refText(Referenz refText) {
		this.refText = refText;
		return this;
	}

	@JsonProperty("refText")
	public Referenz getRefText() {
		return refText;
	}

	public void setRefText(Referenz refText) {
		this.refText = refText;
	}

	/**
	 *
	 **/
	public Text rechtscharakter(int rechtscharakter) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Text text = (Text) o;
		return Objects.equals(this.schluessel, text.schluessel)
				&& Objects.equals(this.gesetzlicheGrundlage, text.gesetzlicheGrundlage)
				&& Objects.equals(this.text, text.text) && Objects.equals(this.refText, text.refText)
				&& Objects.equals(this.rechtscharakter, text.rechtscharakter);
	}

	@Override
	public int hashCode() {
		return Objects.hash(schluessel, gesetzlicheGrundlage, text, refText, rechtscharakter);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Text {\n");

		sb.append("    schluessel: ").append(toIndentedString(schluessel)).append("\n");
		sb.append("    gesetzlicheGrundlage: ").append(toIndentedString(gesetzlicheGrundlage)).append("\n");
		sb.append("    text: ").append(toIndentedString(text)).append("\n");
		sb.append("    refText: ").append(toIndentedString(refText)).append("\n");
		sb.append("    rechtscharakter: ").append(toIndentedString(rechtscharakter)).append("\n");
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
