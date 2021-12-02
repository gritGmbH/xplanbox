package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.Date;
import java.util.Objects;

/**
 * Datatype for Referenz.
 *
 * @since 4.4
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2021-11-03T09:34:00.218+01:00[Europe/Berlin]")
public class Referenz {

	private @Valid String georefURL;

	private @Valid String georefMimeType;

	private @Valid String art;

	private @Valid String informationssystemURL;

	private @Valid String referenzName;

	private @Valid String referenzURL;

	private @Valid String referenzMimeType;

	private @Valid String beschreibung;

	private @Valid Date datum;

	/**
	 *
	 **/
	public Referenz georefURL(String georefURL) {
		this.georefURL = georefURL;
		return this;
	}

	@JsonProperty("georefURL")
	public String getGeorefURL() {
		return georefURL;
	}

	public void setGeorefURL(String georefURL) {
		this.georefURL = georefURL;
	}

	/**
	 *
	 **/
	public Referenz georefMimeType(String georefMimeType) {
		this.georefMimeType = georefMimeType;
		return this;
	}

	@JsonProperty("georefMimeType")
	public String getGeorefMimeType() {
		return georefMimeType;
	}

	public void setGeorefMimeType(String georefMimeType) {
		this.georefMimeType = georefMimeType;
	}

	/**
	 *
	 **/
	public Referenz art(String art) {
		this.art = art;
		return this;
	}

	@JsonProperty("art")
	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	/**
	 *
	 **/
	public Referenz informationssystemURL(String informationssystemURL) {
		this.informationssystemURL = informationssystemURL;
		return this;
	}

	@JsonProperty("informationssystemURL")
	public String getInformationssystemURL() {
		return informationssystemURL;
	}

	public void setInformationssystemURL(String informationssystemURL) {
		this.informationssystemURL = informationssystemURL;
	}

	/**
	 *
	 **/
	public Referenz referenzName(String referenzName) {
		this.referenzName = referenzName;
		return this;
	}

	@JsonProperty("referenzName")
	public String getReferenzName() {
		return referenzName;
	}

	public void setReferenzName(String referenzName) {
		this.referenzName = referenzName;
	}

	/**
	 *
	 **/
	public Referenz referenzURL(String referenzURL) {
		this.referenzURL = referenzURL;
		return this;
	}

	@JsonProperty("referenzURL")
	public String getReferenzURL() {
		return referenzURL;
	}

	public void setReferenzURL(String referenzURL) {
		this.referenzURL = referenzURL;
	}

	/**
	 *
	 **/
	public Referenz referenzMimeType(String referenzMimeType) {
		this.referenzMimeType = referenzMimeType;
		return this;
	}

	@JsonProperty("referenzMimeType")
	public String getReferenzMimeType() {
		return referenzMimeType;
	}

	public void setReferenzMimeType(String referenzMimeType) {
		this.referenzMimeType = referenzMimeType;
	}

	/**
	 *
	 **/
	public Referenz beschreibung(String beschreibung) {
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
	public Referenz datum(Date datum) {
		this.datum = datum;
		return this;
	}

	@JsonProperty("datum")
	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Referenz referenz = (Referenz) o;
		return Objects.equals(this.georefURL, referenz.georefURL)
				&& Objects.equals(this.georefMimeType, referenz.georefMimeType)
				&& Objects.equals(this.art, referenz.art)
				&& Objects.equals(this.informationssystemURL, referenz.informationssystemURL)
				&& Objects.equals(this.referenzName, referenz.referenzName)
				&& Objects.equals(this.referenzURL, referenz.referenzURL)
				&& Objects.equals(this.referenzMimeType, referenz.referenzMimeType)
				&& Objects.equals(this.beschreibung, referenz.beschreibung)
				&& Objects.equals(this.datum, referenz.datum);
	}

	@Override
	public int hashCode() {
		return Objects.hash(georefURL, georefMimeType, art, informationssystemURL, referenzName, referenzURL,
				referenzMimeType, beschreibung, datum);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Referenz {\n");

		sb.append("    georefURL: ").append(toIndentedString(georefURL)).append("\n");
		sb.append("    georefMimeType: ").append(toIndentedString(georefMimeType)).append("\n");
		sb.append("    art: ").append(toIndentedString(art)).append("\n");
		sb.append("    informationssystemURL: ").append(toIndentedString(informationssystemURL)).append("\n");
		sb.append("    referenzName: ").append(toIndentedString(referenzName)).append("\n");
		sb.append("    referenzURL: ").append(toIndentedString(referenzURL)).append("\n");
		sb.append("    referenzMimeType: ").append(toIndentedString(referenzMimeType)).append("\n");
		sb.append("    beschreibung: ").append(toIndentedString(beschreibung)).append("\n");
		sb.append("    datum: ").append(toIndentedString(datum)).append("\n");
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