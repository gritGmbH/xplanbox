/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.validator.geometric.inspector.geltungsbereich;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.geometry.standard.AbstractDefaultGeometry;
import org.deegree.gml.reference.FeatureReference;
import org.locationtech.jts.geom.Geometry;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_53;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GeltungsbereichFeatureAnalyser {

	private static final List<String> GEHOERT_ZU_BEREICH_PROPNAMES = new ArrayList<>();

	private static final List<QName> OBJECTS_ALLOWED_OUTSIDE = new ArrayList<>();

	static {
		addForAllXPlanVersions("XP_FPO");
		addForAllXPlanVersions("XP_LPO");
		addForAllXPlanVersions("XP_LTO");
		addForAllXPlanVersions("XP_PPO");
		addForAllXPlanVersions("XP_PTO");
		addForAllXPlanVersions("XP_TPO");
		OBJECTS_ALLOWED_OUTSIDE.add(new QName(XPLAN_53.getNamespace(), "BP_HoehenMass"));
		OBJECTS_ALLOWED_OUTSIDE.add(new QName(XPLAN_53.getNamespace(), "BP_AbstandsMass"));
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuBereich");
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuBereich");
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuBP_Bereich");
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuLP_Bereich");
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuFP_Bereich");
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuRP_Bereich");
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuSO_Bereich");
	}

	private static void addForAllXPlanVersions(String localName) {
		for (XPlanVersion version : XPlanVersion.values()) {
			if (!XPLAN_SYN.equals(version)) {
				OBJECTS_ALLOWED_OUTSIDE.add(new QName(version.getNamespace(), localName));
			}
		}
	}

	/**
	 * @param feature never <code>null</code>
	 * @return <code>true</code> if the feature is a X_Plan feature, <code>false</code>
	 * otherwise
	 */
	boolean isPlanFeature(Feature feature) {
		return feature.getName().getLocalPart().endsWith("_Plan");
	}

	/**
	 * @param feature never <code>null</code>
	 * @return <code>true</code> if the feature is a X_Bereich feature, <code>false</code>
	 * otherwise
	 */
	boolean isBereichFeature(Feature feature) {
		return feature.getName().getLocalPart().endsWith("_Bereich");
	}

	/**
	 * @param feature never <code>null</code>
	 * @return <code>true</code> if the feature is allowed to be outside of the
	 * geltungsbereich, <code>false</code> otherwise
	 */
	boolean isAllowedToBeOutside(Feature feature) {
		return OBJECTS_ALLOWED_OUTSIDE.contains(feature.getName());
	}

	boolean hasGeometry(Feature feature) {
		return !feature.getGeometryProperties().isEmpty();
	}

	/**
	 * @param bereichFeature never <code>null</code>
	 * @return the id of the plan this feature belongs to
	 */
	public String getGehortZuPlanId(Feature bereichFeature) {
		return getPropertyValue(bereichFeature, "gehoertZuPlan");
	}

	/**
	 * @param feature never <code>null</code>
	 * @return the id of the bereich this feature belongs to, <code>null</code> is not
	 * assigned to a bereich
	 */
	String getGehortZuBereichId(Feature feature) {
		for (String propName : GEHOERT_ZU_BEREICH_PROPNAMES) {
			String gehortZuBereichId = getPropertyValue(feature, propName);
			if (gehortZuBereichId != null)
				return gehortZuBereichId;
		}
		return null;
	}

	/**
	 * @param feature never <code>null</code>
	 * @return the geometry of the feature as JTS geometry, <code>null</code> if the
	 * feature has no geometry
	 */
	Geometry getJtsGeometry(Feature feature) {
		AbstractDefaultGeometry originalGeometry = getOriginalGeometry(feature);
		if (originalGeometry != null)
			return originalGeometry.getJTSGeometry();
		return null;
	}

	/**
	 * @param feature never <code>null</code>
	 * @return the geometry of the feature, <code>null</code> if the feature has no
	 * geometry
	 */
	AbstractDefaultGeometry getOriginalGeometry(Feature feature) {
		List<Property> geometryProperties = feature.getGeometryProperties();
		if (!geometryProperties.isEmpty())
			return (AbstractDefaultGeometry) geometryProperties.get(0).getValue();
		return null;
	}

	private String getPropertyValue(Feature feature, String propName) {
		QName qName = new QName(feature.getName().getNamespaceURI(), propName);
		List<Property> properties = feature.getProperties(qName);
		if (properties == null || properties.isEmpty())
			return null;
		Property property = properties.get(0);
		TypedObjectNode value = property.getValue();
		if (value instanceof FeatureReference)
			return ((FeatureReference) value).getURI().substring(1);
		return value.toString().substring(1);
	}

}