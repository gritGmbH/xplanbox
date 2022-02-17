/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.synthesizer.Features;
import de.latlon.xplan.manager.synthesizer.expression.XplanSymbolPositions;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.multi.MultiPoint;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.refs.PointReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.synthesizer.Features.getPropertyValue;

/**
 * The <code>XPlanAggregatePPOLookup</code> class is auxiliary to
 * {@link XplanSymbolPositions}. It first statically registers all the points from the
 * symbolPosition of fachobjekte in a map. Then it can statically return the set of points
 * by providing the feauture id of the fachobjekt.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @version $Revision: 1028 $, $Date: 2010-02-18 15:48:22 +0100 (Do, 18 Feb 2010) $
 */
public class XpPpoLookup {

	private static final Logger LOG = LoggerFactory.getLogger(XpPpoLookup.class);

	private static final Map<String, Set<Point>> fachobjektIdToPoints = new HashMap<String, Set<Point>>();

	/**
	 * Registers all the points from the symbolPosition property of fachobjekte in a map,
	 * that can later be accessed by {@link #lookup}
	 * @param fc the FeatureCollection that might contain XP_PPO objects (containing
	 * points) with references to fachobjekte
	 */
	public static void init(FeatureCollection fc) {
		fachobjektIdToPoints.clear();
		for (Feature feature : fc) {
			if ("XP_PPO".equals(feature.getName().getLocalPart())) {
				QName positionName = new QName(feature.getName().getNamespaceURI(), "position");
				List<Point> positionProps = parsePoints(feature, positionName);
				QName dientZurPropName = new QName(feature.getName().getNamespaceURI(), "dientZurDarstellungVon");
				List<Property> featureProps = feature.getProperties(dientZurPropName);
				if (featureProps != null) {
					for (Property prop : featureProps) {
						Feature refFeature = Features.getPropertyFeatureValue(prop);
						String refFeatureId = refFeature.getId();
						if (refFeatureId == null) {
							// if the XP_Objekt feature has no id, then it will not
							// need
							// be referenced, hence it doesn't
							// need any points from XP_PPO objects
							continue;
						}
						Set<Point> currentPoints = fachobjektIdToPoints.get(refFeatureId);
						if (currentPoints == null) {
							currentPoints = new LinkedHashSet<Point>();
						}
						currentPoints.addAll(positionProps);
						fachobjektIdToPoints.put(refFeatureId, currentPoints);
					}
				}
			}
		}
	}

	private static List<Point> parsePoints(Feature feature, QName positionName) {
		TypedObjectNode propertyValue = getPropertyValue(feature, positionName);
		if (propertyValue == null) {
			return Collections.emptyList();
		}
		if (propertyValue instanceof PointReference) {
			return Collections.singletonList(((PointReference) propertyValue).getReferencedObject());
		}
		else if (propertyValue instanceof Point) {
			return Collections.singletonList((Point) propertyValue);
		}
		else if (propertyValue instanceof MultiPoint) {
			return ((MultiPoint) propertyValue).stream().collect(Collectors.toList());
		}
		LOG.warn("Unexpected property value of class {}. Supported are Points and MultiPoints ",
				propertyValue.getClass());
		return Collections.emptyList();
	}

	/**
	 * Returns all Points from the XP_PPO features that reference the fachobjekt that has
	 * the given featureId.
	 * @param fachobjektId
	 * @return the points, or <code>null</code> if XP_PPO features reference the
	 * fachobjekt
	 */
	public static Set<Point> lookup(String fachobjektId) {
		return fachobjektIdToPoints.get(fachobjektId);
	}

}
