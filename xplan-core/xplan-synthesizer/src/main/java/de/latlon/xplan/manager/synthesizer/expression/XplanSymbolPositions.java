/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.deegree.commons.tom.gml.property.Property;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.multi.MultiPoint;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.standard.multi.DefaultMultiPoint;

import de.latlon.xplan.manager.synthesizer.XpPpoLookup;

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
		return new DefaultMultiPoint(null, crs, null, new ArrayList<Point>(positions));
	}

	private Set<Point> getPpoSymbolPositions(Feature xpObjekt) {
		Set<Point> positions = XpPpoLookup.lookup(xpObjekt.getId());
		if (positions == null) {
			positions = new LinkedHashSet<Point>();
		}
		else if (!positions.isEmpty()) {
			positions = new LinkedHashSet<Point>(positions);
		}
		return positions;
	}

	private Set<Point> getXpObjektSymbolPositionsXplan2or3(Feature xpObjekt) {
		Set<Point> points = new LinkedHashSet<Point>();
		QName symbolPosName = new QName(xpObjekt.getName().getNamespaceURI(), "symbolPosition");
		List<Property> props = xpObjekt.getProperties(symbolPosName);
		for (Property prop : props) {
			Point p = (Point) prop.getValue();
			points.add(p);
		}
		return points;
	}

}
