/*-
 * #%L
 * xplan-core-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.manager.synthesizer.PlanContext;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Geometries;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.GeometryFactory;
import org.deegree.geometry.SFSProfiler;
import org.deegree.geometry.linearization.MaxErrorCriterion;
import org.deegree.geometry.multi.MultiGeometry;

import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.castToArray;
import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.castToGeometry;
import static org.deegree.geometry.multi.MultiGeometry.MultiGeometryType.MULTI_GEOMETRY;

/**
 * {@link Expression} that returns an SFS-compliant version of a {@link Geometry} value.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanGeometry implements Expression {

	// i.e. meters, as the targeted CRS should always be in meters
	private final double MAX_ERROR = 0.05;

	// yep, not configurable (yet)
	private final int MAX_NUM_POINTS = 500;

	private final Xpath xpath;

	/**
	 * Create a XPlanGeometry rule instance by providing the xpath to the geometry
	 * @param xpath the {@link Xpath} to the geometry
	 */
	public XPlanGeometry(Xpath xpath) {
		this.xpath = xpath;
	}

	@Override
	public Geometry evaluate(Feature feature, FeatureCollection features, PlanContext planContext) {
		TypedObjectNodeArray<?> geometries = castToArray(xpath.evaluate(feature, features, planContext));
		if (geometries == null) {
			return null;
		}
		Geometry geometry = castToGeometry(geometries.getElements()[0]);
		if (geometry == null) {
			return null;
		}
		geometry = aggregateIndividualGeometries(geometries, geometry);
		geometry = makeSfsCompliant(feature, geometry);
		geometry = homogenizeMultiGeometry(geometry);
		return geometry;
	}

	private Geometry aggregateIndividualGeometries(TypedObjectNodeArray<?> geometries, Geometry geometry) {
		// if necessary, aggregate all geometries into a MultiGeometry first
		if (geometries.getElements().length > 1) {
			List<Geometry> members = new ArrayList<Geometry>();
			for (TypedObjectNode member : geometries.getElements()) {
				members.add(castToGeometry(member));
			}
			GeometryFactory fac = new GeometryFactory();
			geometry = fac.createMultiGeometry(null, geometry.getCoordinateSystem(), members);
		}
		return geometry;
	}

	private Geometry makeSfsCompliant(Feature feature, Geometry geometry) {
		SFSProfiler simplifier = new SFSProfiler(new MaxErrorCriterion(MAX_ERROR, MAX_NUM_POINTS));
		if (geometry.getCoordinateSystem() == null) {
			// should never happen
			throw new RuntimeException(
					"Interner Fehler. Feature '" + feature.getId() + " hat eine Geometrie ohne CRS.");
		}
		return simplifier.simplify(geometry);
	}

	private Geometry homogenizeMultiGeometry(Geometry geom) {
		if (geom instanceof MultiGeometry<?> && ((MultiGeometry<?>) geom).getMultiGeometryType() == MULTI_GEOMETRY) {
			try {
				return Geometries.homogenize((MultiGeometry<?>) geom);
			}
			catch (IllegalArgumentException e) {
				throw new RuntimeException("Heterogene MultiGeometry gefunden. Dies wird nicht unterstützt.");
			}
		}
		return geom;
	}

}
