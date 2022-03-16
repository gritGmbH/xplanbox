/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.manager.synthesizer.XpPpoLookup;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.multi.MultiPoint;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.standard.multi.DefaultMultiPoint;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * {@link Expression} that aggregates symbol positions for XP_Objekt features
 * ("Fachobjekte").
 * <p>
 * Symbol positions stem from XP_PPO features that reference the XP_Objekt via property
 * "dientZurDarstellungVon". Additionally, in XPlan 2 and 3, symbol positions may be
 * encoded using property "symbolPosition" of the XP_Objekt feature.
 * </p>
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @since 1.0
 */
public class XplanSymbolPositions implements Expression {

	@Override
	public MultiPoint evaluate(Feature xpObjekt, FeatureCollection features) {
		Set<Point> positions = getPpoSymbolPositions(xpObjekt);
		positions.addAll(getXpObjektSymbolPositionsXplan2or3(xpObjekt));
		if (positions.isEmpty()) {
			return null;
		}
		ICRS crs = positions.iterator().next().getCoordinateSystem();
		return new DefaultMultiPoint(null, crs, null, new ArrayList<>(positions));
	}

	private Set<Point> getPpoSymbolPositions(Feature xpObjekt) {
		Set<Point> positions = XpPpoLookup.lookup(xpObjekt.getId());
		if (positions == null) {
			positions = new LinkedHashSet<>();
		}
		else if (!positions.isEmpty()) {
			positions = new LinkedHashSet<>(positions);
		}
		return positions;
	}

	private Set<Point> getXpObjektSymbolPositionsXplan2or3(Feature xpObjekt) {
		Set<Point> points = new LinkedHashSet<>();
		QName symbolPosName = new QName(xpObjekt.getName().getNamespaceURI(), "symbolPosition");
		List<Property> props = xpObjekt.getProperties(symbolPosName);
		for (Property prop : props) {
			Point p = (Point) prop.getValue();
			points.add(p);
		}
		return points;
	}

}
