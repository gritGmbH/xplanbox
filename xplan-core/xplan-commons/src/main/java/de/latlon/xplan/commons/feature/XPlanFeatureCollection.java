/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;

import java.util.Date;

/**
 * Provides convenient access to the information contained in the main document of an {@link XPlanArchive}.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanFeatureCollection {

    private final FeatureCollection fc;

    private final XPlanType type;

    private final XPlanVersion version;

    private final XPlanAde ade;

    private String name;

    private final String nummer;

    private final String gkz;

    private final Date planReleaseDate;

    private final Envelope bboxIn4326;

    private final ExternalReferenceInfo externalRefInfo;

    XPlanFeatureCollection( FeatureCollection fc, XPlanType type, String name, String nummer, String gkz,
                            Date planReleaseDate, ExternalReferenceInfo externalRefInfo, Envelope bboxIn4326,
                            XPlanVersion version, XPlanAde ade ) {
        this.fc = fc;
        this.type = type;
        this.name = name;
        this.nummer = nummer;
        this.gkz = gkz;
        this.planReleaseDate = planReleaseDate;
        this.externalRefInfo = externalRefInfo;
        this.bboxIn4326 = bboxIn4326;
        this.version = version;
        this.ade = ade;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public XPlanType getType() {
        return type;
    }

    public XPlanVersion getVersion() {
        return version;
    }

    public XPlanAde getAde() {
        return ade;
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
        return fc;
    }

    public ExternalReferenceInfo getExternalReferenceInfo() {
        return externalRefInfo;
    }

    /**
     * Returns BBOX of feature collection in EPSG:4326.
     *
     * @return BBOX in EPSG:4326, may be <code>null</code> if the feature collection does not contain any geometry
     * properties/envelope information or the bounding box could not be transformed to EPSG:4326
     */
    public Envelope getBboxIn4326() {
        return bboxIn4326;
    }

}
