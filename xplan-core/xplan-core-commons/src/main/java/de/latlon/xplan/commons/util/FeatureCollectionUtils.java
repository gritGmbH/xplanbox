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
package de.latlon.xplan.commons.util;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.manager.web.shared.Bereich;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.synthesizer.Features.getPropertyStringValue;
import static de.latlon.xplan.commons.synthesizer.Features.getPropertyValue;

/**
 * Contains utilities for deegree {@link org.deegree.feature.FeatureCollection}s.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class FeatureCollectionUtils {

	private FeatureCollectionUtils() {
	}

	/**
	 * Finds the XP_Plan feature of a XPlan featureCollection.
	 * @param fc XPlan featureCollection, never <code>null</code>
	 * @param type the type of the expected plan feature, never <code>null</code>
	 * @return XP_Plan , never <code>null</code>
	 * @throws IllegalArgumentException if the feature collection does not contain at
	 * least one XP_Plan feature
	 */
	public static Feature findPlanFeature(FeatureCollection fc, XPlanType type) {
		for (Feature feature : fc) {
			QName featureName = feature.getName();
			if (featureName.getLocalPart().equals(type.name())) {
				return feature;
			}
		}
		throw new IllegalArgumentException("Keine XPlan-FeatureCollection. Kein XP_Plan-Feature enthalten.");
	}

	/**
	 * Finds the XP_Plan features of a XPlan featureCollection.
	 * @param fc XPlan featureCollection, never <code>null</code>
	 * @param type the type of the expected plan feature, never <code>null</code>
	 * @return list of XPlan features, never <code>null</code> nor empty
	 * @throws IllegalArgumentException if the feature collection does not contain at
	 * least one XP_Plan feature
	 */
	public static List<Feature> findPlanFeatures(FeatureCollection fc, XPlanType type) {
		List<Feature> planFeatures = new ArrayList<>();
		for (Feature feature : fc) {
			QName featureName = feature.getName();
			if (featureName.getLocalPart().equals(type.name())) {
				planFeatures.add(feature);
			}
		}
		if (planFeatures.isEmpty())
			throw new IllegalArgumentException("Keine XPlan-FeatureCollection. Kein XP_Plan-Feature enthalten.");
		return planFeatures;
	}

	/**
	 * Retrieves the rechtsstand of a XPlan-FeatureCollection.
	 * @param fc XPlan-FeatureCollection, never <code>null</code>
	 * @param type XPlan-Type, never <code>null</code>
	 * @return rechtsstand of the plan or <code>null</code> if no value was found
	 */
	public static String retrieveRechtsstand(FeatureCollection fc, XPlanType type) {
		return retrievePlanProperty(fc, type, "rechtsstand");
	}

	/**
	 * Retrieves the translated rechtsstand ("rechtsstandWert") of a synthesized
	 * XPlan-FeatureCollection.
	 * @param fc synthesized XPlan-FeatureCollection, never <code>null</code>
	 * @param type XPlan-Type, never <code>null</code>
	 * @return translated rechtsstand ("rechtsstandWert") of the plan or <code>null</code>
	 * if no value was found
	 */
	public static String retrieveRechtsstandWert(FeatureCollection fc, XPlanType type) {
		return retrievePlanProperty(fc, type, "rechtsstandWert");
	}

	/**
	 * Retrieves the translated additional type ("sonstPlanArtWert") of a synthesized
	 * XPlan-FeatureCollection.
	 * @param fc synthesized XPlan-FeatureCollection, never <code>null</code>
	 * @param type XPlan-Type, never <code>null</code>
	 * @return translated sonstPlanArtWert value or <code>null</code> if no value was
	 * found
	 */
	public static String retrieveAdditionalTypeWert(FeatureCollection fc, XPlanType type) {
		return retrievePlanProperty(fc, type, "sonstPlanArtWert");
	}

	/**
	 * Retrieves the district ("ortsteilName") of a XPlan-FeatureCollection.
	 * @param fc XPlan-FeatureCollection, never <code>null</code>
	 * @param type XPlan-Type, never <code>null</code>
	 * @return district value or <code>null</code> if no value was found
	 */
	public static String retrieveDistrict(FeatureCollection fc, XPlanType type) {
		Feature planFeature = findPlanFeature(fc, type);
		String ns = planFeature.getName().getNamespaceURI();
		TypedObjectNode municipality = getPropertyValue(planFeature, new QName(ns, "gemeinde"));
		if (municipality != null && municipality instanceof ElementNode) {
			return scanMunicipalityChildren(ns, (ElementNode) municipality);
		}
		return null;
	}

	/**
	 * Retrieves the value of XX_Plan/beschreibung of the {@link FeatureCollection}.
	 * @param fc XPlan-FeatureCollection, never <code>null</code>
	 * @param type XPlan-Type, never <code>null</code>
	 * @return additional type value or <code>null</code> if no value was found
	 */
	public static String retrieveDescription(FeatureCollection fc, XPlanType type) {
		return retrievePlanProperty(fc, type, "beschreibung");
	}

	/**
	 * Retrieves the value of XX_Plan/name of all XX_Plan features in the
	 * {@link FeatureCollection}.
	 * @param fc XPlan-FeatureCollection, never <code>null</code>
	 * @param type XPlan-Type, never <code>null</code>
	 * @return list of names of the plans, never <code>null</code> (a new name is created
	 * for each unnamed plan)
	 */
	public static List<String> retrievePlanNames(FeatureCollection fc, XPlanType type) {
		return findPlanFeatures(fc, type).stream()
			.map(feature -> retrievePlanName(feature))
			.collect(Collectors.toList());
	}

	/**
	 * Retrieves the value of XX_Plan/name of the {@link FeatureCollection}.
	 * @param fc XPlan-FeatureCollection, never <code>null</code>
	 * @param type XPlan-Type, never <code>null</code>
	 * @return name of the plan, never <code>null</code> (a new name is created)
	 */
	public static String retrievePlanName(FeatureCollection fc, XPlanType type) {
		Feature planFeature = findPlanFeature(fc, type);
		return retrievePlanName(planFeature);
	}

	/**
	 * Retrieves the value of the attribute name of the passed {@link Feature}.
	 * @param planFeature never <code>null</code>
	 * @return name of the plan, never <code>null</code> (a new name is created)
	 */
	public static String retrievePlanName(Feature planFeature) {
		String ns = planFeature.getName().getNamespaceURI();
		String name = getPropertyStringValue(planFeature, new QName(ns, "name"));
		if (name == null || name.isEmpty()) {
			name = "Unbenannter XPlan (" + UUID.randomUUID().toString() + ")";
		}
		return name;
	}

	/**
	 * Retrieves the Bereiche of the passed {@link Feature}.
	 * @param fc XPlan-FeatureCollection, never <code>null</code>
	 * @return list of the bereiche of the plan, may be empty but never <code>null</code>
	 */
	public static List<Bereich> retrieveBereiche(FeatureCollection fc) {
		List<Bereich> bereiche = new ArrayList<>();
		for (Feature feature : fc) {
			QName featureName = feature.getName();
			if (featureName.getLocalPart().matches("(BP|FP|LP|RP|SO)_Bereich")) {
				String ns = feature.getName().getNamespaceURI();
				Bereich bereich = new Bereich();
				bereich.setNummer(getPropertyStringValue(feature, new QName(ns, "nummer")));
				bereich.setName(getPropertyStringValue(feature, new QName(ns, "name")));
				bereiche.add(bereich);
			}
		}
		return bereiche;
	}

	private static String scanMunicipalityChildren(String ns, ElementNode municipality) {
		String district = null;
		for (TypedObjectNode municipalityChild : municipality.getChildren()) {
			if (municipalityChild instanceof ElementNode) {
				ElementNode municipalityElement = (ElementNode) municipalityChild;
				if (new QName(ns, "ortsteilName").equals(municipalityElement.getName())) {
					district = retrieveDistrictName(municipalityElement);
				}
			}
		}
		return district;
	}

	private static String retrieveDistrictName(ElementNode municipalityElement) {
		TypedObjectNode districtName = municipalityElement.getChildren().get(0);
		if (districtName instanceof PrimitiveValue) {
			PrimitiveValue value = (PrimitiveValue) districtName;
			String district = value.getAsText();
			if (district.contains(",")) {
				String[] split = district.split(",");
				return split[0].trim();
			}
			return district;
		}
		return null;
	}

	private static String retrievePlanProperty(FeatureCollection fc, XPlanType type, String propertyName) {
		Feature planFeature = findPlanFeature(fc, type);
		String ns = planFeature.getName().getNamespaceURI();
		return getPropertyStringValue(planFeature, new QName(ns, propertyName));
	}

}
