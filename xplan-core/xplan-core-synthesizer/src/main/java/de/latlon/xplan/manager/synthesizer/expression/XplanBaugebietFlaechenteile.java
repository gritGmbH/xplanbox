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

import static de.latlon.xplan.commons.synthesizer.Features.getPropertyFeatureValue;
import static de.latlon.xplan.commons.synthesizer.Features.getPropertyValue;
import static org.deegree.geometry.multi.MultiGeometry.MultiGeometryType.MULTI_GEOMETRY;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import de.latlon.xplan.manager.synthesizer.PlanContext;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Geometries;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.SFSProfiler;
import org.deegree.geometry.linearization.MaxErrorCriterion;
import org.deegree.geometry.multi.MultiGeometry;
import org.deegree.geometry.multi.MultiSurface;
import org.deegree.geometry.primitive.Surface;
import org.deegree.geometry.standard.multi.DefaultMultiSurface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link Expression} that aggregates the geometries of referenced features.
 * <p>
 * This is only used for feature type BP_Baugebiet.
 * </p>
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @since 1.0
 */
public class XplanBaugebietFlaechenteile implements Expression {

	private static final Logger LOG = LoggerFactory.getLogger(XplanBaugebietFlaechenteile.class);

	// i.e. meters, as the targeted CRS should always be in meters
	private final double MAX_ERROR = 1.0;

	// yep, not configurable (yet)
	private final int MAX_NUM_POINTS = 500;

	@Override
	public Geometry evaluate(Feature feature, FeatureCollection features, PlanContext planContext) {
		List<Surface> members = new ArrayList<Surface>();
		String ns = feature.getName().getNamespaceURI();
		List<Property> props = feature.getProperties(new QName(ns, "flaechenteil"));
		for (Property prop : props) {
			Feature refFeature = getPropertyFeatureValue(prop);
			TypedObjectNode position = getPropertyValue(refFeature, new QName(ns, "position"));
			if (!(position instanceof Geometry)) {
				LOG.warn("The flaechenteil property does NOT contain a Geometry as it should, but "
						+ refFeature.getName());
				return null;
			}
			Geometry geom = (Geometry) position;
			if (geom instanceof Surface) {
				members.add((Surface) geom);
			}
			else if (geom instanceof MultiSurface) {
				@SuppressWarnings("unchecked")
				MultiSurface<Surface> multiSurface = (MultiSurface<Surface>) geom;
				for (Surface surfMember : multiSurface) {
					members.add(surfMember);
				}
			}
		}
		if (members.isEmpty()) {
			return null;
		}
		Surface sampleMember = members.get(0);
		DefaultMultiSurface geometry = new DefaultMultiSurface(null, sampleMember.getCoordinateSystem(),
				sampleMember.getPrecision(), members);
		Geometry sfs = makeSfsCompliant(geometry);
		sfs = homogenizeMultiGeometry(sfs);
		return sfs;
	}

	private Geometry makeSfsCompliant(Geometry geometry) {
		SFSProfiler simplifier = new SFSProfiler(new MaxErrorCriterion(MAX_ERROR, MAX_NUM_POINTS));
		if (geometry.getCoordinateSystem() == null) {
			// should never happen
			throw new RuntimeException("Interner Fehler. Geometrie ohne CRS.");
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
