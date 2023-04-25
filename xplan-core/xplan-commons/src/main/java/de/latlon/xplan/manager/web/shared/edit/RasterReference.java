/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared.edit;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

import static de.latlon.xplan.commons.util.TextPatternConstants.SIMPLE_NAME_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.S_LENGTH;

/**
 * Encapsulate a raster reference (refScan, refLegend, refText).
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class RasterReference extends AbstractReference {

	@Valid
	private RasterReferenceType type;

	@Valid
	private String featureId;

	@Size(max = S_LENGTH)
	@Pattern(regexp = SIMPLE_NAME_PATTERN)
	@Valid
	private String bereichNummer;

	public RasterReference() {
	}

	/**
	 * Instantiates a new {@link RasterReference} as copy of the passed.
	 * @param rasterReference to copy, never <code>null</code>
	 */
	public RasterReference(RasterReference rasterReference) {
		this(rasterReference.getBereichNummer(), rasterReference.getFeatureId(), rasterReference.getReference(),
				rasterReference.getGeoReference(), rasterReference.getType(), rasterReference.getGeorefMimeType(),
				rasterReference.getArt(), rasterReference.getInformationssystemURL(), rasterReference.getReferenzName(),
				rasterReference.getReferenzMimeType(), rasterReference.getBeschreibung(), rasterReference.getDatum());
	}

	/**
	 * @param reference reference, may be <code>null</code>
	 * @param geoReference geoReference, may be <code>null</code>
	 * @param type type, should not be <code>null</code>
	 */
	public RasterReference(String bereichNummer, String reference, String geoReference, RasterReferenceType type,
			MimeTypes georefMimeType, ExterneReferenzArt art, String informationssystemURL, String referenzName,
			MimeTypes referenzMimeType, String beschreibung, Date datum) {
		this(bereichNummer, null, reference, geoReference, type, georefMimeType, art, informationssystemURL,
				referenzName, referenzMimeType, beschreibung, datum);
	}

	/**
	 * @param featureId the id of the feature containing this reference, may be
	 * <code>null</code>
	 * @param reference reference, may be <code>null</code>
	 * @param geoReference geoReference, may be <code>null</code>
	 * @param type type, should not be <code>null</code>
	 */
	public RasterReference(String bereichNummer, String featureId, String reference, String geoReference,
			RasterReferenceType type, MimeTypes georefMimeType, ExterneReferenzArt art, String informationssystemURL,
			String referenzName, MimeTypes referenzMimeType, String beschreibung, Date datum) {
		super(reference, geoReference, georefMimeType, art, informationssystemURL, referenzName, referenzMimeType,
				beschreibung, datum);
		this.bereichNummer = bereichNummer;
		this.featureId = featureId;
		this.type = type;
	}

	/**
	 * @return the type, may be <code>null</code>
	 */
	public RasterReferenceType getType() {
		return type;
	}

	/**
	 * @param type the type to set, may be <code>null</code>
	 */
	public void setType(RasterReferenceType type) {
		this.type = type;
	}

	/**
	 * @return the id of the feature containing this reference, may be <code>null</code>
	 */
	public String getFeatureId() {
		return featureId;
	}

	/**
	 * @param featureId the id of the feature containing this reference, may be
	 * <code>null</code>
	 */
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	/**
	 * @return the nummer of the bereich this RasterReference is assigned to
	 */
	public String getBereichNummer() {
		return bereichNummer;
	}

	/**
	 * @return the nummer of the bereich this RasterReference is assigned to
	 */
	public void setBereichNummer(String bereichId) {
		this.bereichNummer = bereichId;
	}

	@Override
	public String toString() {
		return "RasterReference{" + "type=" + type + ", featureId=" + featureId + ", bereichId=" + bereichNummer + '}';
	}

}
