/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared.edit;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

import static de.latlon.xplan.commons.util.TextPatternConstants.L_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.M_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.S_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.TEXT_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.URL_PATTERN;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public abstract class AbstractReference implements Serializable {

	@Size(max = M_LENGTH)
	@Pattern(regexp = URL_PATTERN)
	@Valid
	private String geoReference;

	@Valid
	private MimeTypes georefMimeType;

	@Valid
	private ExterneReferenzArt art;

	@Size(max = M_LENGTH)
	@Pattern(regexp = URL_PATTERN)
	@Valid
	private String informationssystemURL;

	@Size(max = M_LENGTH)
	@Pattern(regexp = URL_PATTERN)
	@Valid
	private String reference;

	@Valid
	private MimeTypes referenzMimeType;

	@Size(max = S_LENGTH)
	@Pattern(regexp = TEXT_PATTERN)
	@Valid
	private String referenzName;

	@Size(max = L_LENGTH)
	@Pattern(regexp = TEXT_PATTERN)
	@Valid
	private String beschreibung;

	@Valid
	private Date datum;

	public AbstractReference() {
	}

	/**
	 * @param reference may be <code>null</code>
	 * @param geoReference may be <code>null</code>
	 */
	public AbstractReference(String reference, String geoReference) {
		this.reference = reference;
		this.geoReference = geoReference;
	}

	/**
	 * @param reference may be <code>null</code>
	 * @param geoReference may be <code>null</code>
	 * @param georefMimeType may be <code>null</code>
	 * @param art may be <code>null</code>
	 * @param informationssystemURL may be <code>null</code>
	 * @param referenzName may be <code>null</code>
	 * @param referenzMimeType may be <code>null</code>
	 * @param beschreibung may be <code>null</code>
	 * @param datum may be <code>null</code>
	 */
	public AbstractReference(String reference, String geoReference, MimeTypes georefMimeType, ExterneReferenzArt art,
			String informationssystemURL, String referenzName, MimeTypes referenzMimeType, String beschreibung,
			Date datum) {
		this.reference = reference;
		this.geoReference = geoReference;
		this.georefMimeType = georefMimeType;
		this.art = art;
		this.informationssystemURL = informationssystemURL;
		this.referenzName = referenzName;
		this.referenzMimeType = referenzMimeType;
		this.beschreibung = beschreibung;
		this.datum = datum;
	}

	/**
	 * @return the reference, may be <code>null</code>
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set, may be <code>null</code>
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the geoReference, may be <code>null</code>
	 */
	public String getGeoReference() {
		return geoReference;
	}

	/**
	 * @param geoReference the geoReference to set, may be <code>null</code>
	 */
	public void setGeoReference(String geoReference) {
		this.geoReference = geoReference;
	}

	/**
	 * @return the mime type of the georeference, may be <code>null</code>
	 */
	public MimeTypes getGeorefMimeType() {
		return georefMimeType;
	}

	/**
	 * @param georefMimeType the mime type of the georeference, may be <code>null</code>
	 */
	public void setGeorefMimeType(MimeTypes georefMimeType) {
		this.georefMimeType = georefMimeType;
	}

	/**
	 * @return the type of this reference, may be <code>null</code>
	 */
	public ExterneReferenzArt getArt() {
		return art;
	}

	/**
	 * @param art the type of this reference, may be <code>null</code>
	 */
	public void setArt(ExterneReferenzArt art) {
		this.art = art;
	}

	/**
	 * @return the url, may be <code>null</code>
	 */
	public String getInformationssystemURL() {
		return informationssystemURL;
	}

	/**
	 * @param informationssystemURL the url, may be <code>null</code>
	 */
	public void setInformationssystemURL(String informationssystemURL) {
		this.informationssystemURL = informationssystemURL;
	}

	/**
	 * @return the name of the reference, may be <code>null</code>
	 */
	public String getReferenzName() {
		return referenzName;
	}

	/**
	 * @param referenzName the name of the reference, may be <code>null</code>
	 */
	public void setReferenzName(String referenzName) {
		this.referenzName = referenzName;
	}

	/**
	 * @return the mime type of the reference, may be <code>null</code>
	 */
	public MimeTypes getReferenzMimeType() {
		return referenzMimeType;
	}

	/**
	 * @param referenzMimeType the mime type of the reference, may be <code>null</code>
	 */
	public void setReferenzMimeType(MimeTypes referenzMimeType) {
		this.referenzMimeType = referenzMimeType;
	}

	/**
	 * @return the description, may be <code>null</code>
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * @param beschreibung the description, may be <code>null</code>
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @return the date, may be <code>null</code>
	 */
	public Date getDatum() {
		return datum;
	}

	/**
	 * @param datum the date, may be <code>null</code>
	 */
	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public String toString() {
		return "AbstractReference{" + "geoReference='" + geoReference + '\'' + ", georefMimeType=" + georefMimeType
				+ ", art=" + art + ", informationssystemURL='" + informationssystemURL + '\'' + ", reference='"
				+ reference + '\'' + ", referenzMimeType=" + referenzMimeType + ", referenzName='" + referenzName + '\''
				+ ", beschreibung='" + beschreibung + '\'' + ", datum=" + datum + '}';
	}

}
