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
package de.latlon.xplan.commons.reference;

/**
 * Relevant information contained in a single
 * <code>XP_ExterneReferenz</code>/<code>XP_ExterneReferenzPlan</code> object.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class ExternalReference {

	private final String geoRefUrl;

	private final String geoRefMimeTypeCode;

	private final String referenzUrl;

	private final String referenzName;

	private final String referenzMimeTypeCode;

	private final boolean isPlan;

	public ExternalReference(String referenzUrl) {
		this(null, null, referenzUrl, null, null, true);
	}

	public ExternalReference(String referenzUrl, String geoRefUrl) {
		this(geoRefUrl, null, referenzUrl, null, null, true);
	}

	ExternalReference(String geoRefUrl, String geoRefMimeType, String referenzUrl, String referenzName,
			String referenzMimeTypeCode, boolean isPlan) {
		this.geoRefUrl = geoRefUrl;
		this.geoRefMimeTypeCode = geoRefMimeType;
		this.referenzUrl = referenzUrl;
		this.referenzName = referenzName;
		this.referenzMimeTypeCode = referenzMimeTypeCode;
		this.isPlan = isPlan;
	}

	public String getGeoRefUrl() {
		return geoRefUrl;
	}

	public String getGeoRefMimeTypeCode() {
		return geoRefMimeTypeCode;
	}

	public String getReferenzUrl() {
		return referenzUrl;
	}

	public String getReferenzName() {
		return referenzName;
	}

	public String getReferenzMimeTypeCode() {
		return referenzMimeTypeCode;
	}

	public boolean isPlan() {
		return isPlan;
	}

	@Override
	public String toString() {
		return "ExternalReference {geoRefUrl=" + geoRefUrl + ", geoRefMimeTypeCode=" + geoRefMimeTypeCode
				+ ", referenzUrl=" + referenzUrl + ", referenzName=" + referenzName + ", referenzMimeTypeCode="
				+ referenzMimeTypeCode + ", isPlan=" + isPlan + "}";
	}

}
