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
package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.ZipEntryWithContent;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class XPlanFeatureCollection {

	protected FeatureCollection features;

	protected final XPlanType type;

	protected final XPlanVersion version;

	private String name;

	private final String nummer;

	private final String gkz;

	private final Date planReleaseDate;

	private final Envelope bboxIn4326;

	private final ExternalReferenceInfo externalRefInfo;

	XPlanFeatureCollection(FeatureCollection features, XPlanType type, String name, String nummer, String gkz,
			Date planReleaseDate, ExternalReferenceInfo externalRefInfo, Envelope bboxIn4326, XPlanVersion version) {
		this.features = features;
		this.type = type;
		this.name = name;
		this.nummer = nummer;
		this.gkz = gkz;
		this.planReleaseDate = planReleaseDate;
		this.externalRefInfo = externalRefInfo;
		this.bboxIn4326 = bboxIn4326;
		this.version = version;
	}

	public void setName(String name) {
		this.name = name;
	}

	public XPlanType getType() {
		return type;
	}

	public XPlanVersion getVersion() {
		return version;
	}

	public String getPlanName() {
		return name;
	}

	public String getPlanNummer() {
		return nummer;
	}

	public String getPlanGkz() {
		return gkz;
	}

	public boolean getHasRaster() {
		return !externalRefInfo.getRasterPlanBaseScans().isEmpty();
	}

	public Date getPlanReleaseDate() {
		return planReleaseDate;
	}

	public FeatureCollection getFeatures() {
		return features;
	}

	public void setFeatures(FeatureCollection features) {
		this.features = features;
	}

	public ExternalReferenceInfo getExternalReferenceInfo() {
		return externalRefInfo;
	}

	/**
	 * Returns BBOX of feature collection in EPSG:4326.
	 * @return BBOX in EPSG:4326, may be <code>null</code> if the feature collection does
	 * not contain any geometry properties/envelope information or the bounding box could
	 * not be transformed to EPSG:4326
	 */
	public Envelope getBboxIn4326() {
		return bboxIn4326;
	}

	/**
	 * Returns all ArchiveEntries assigned to the XPlanFeatureCollection.
	 * @param xPlanArchive the XPlanArchive the XPlanFeatureCollection belongs to, ḿay be
	 * <code>null</code>
	 * @return a list of ArchiveEntries assigned to the XPlanFeatureCollection, may be
	 * empty but never <code>null</code>. Empty if the xPlanArchive is null
	 */
	public abstract List<ZipEntryWithContent> getArchiveEntries(XPlanArchive xPlanArchive)
			throws FeatureCollectionParseException;

}
