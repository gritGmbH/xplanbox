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

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.jts.JtsParser;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.xpath.TypedObjectNodeXPathEvaluator;
import org.deegree.filter.FilterEvaluationException;
import org.deegree.filter.IdFilter;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.standard.primitive.DefaultPolygon;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OrientationFixerTest {

	@Test
	public void testOrientationFixer_validLaufrichtung() throws Exception {
		XPlanArchive testArchive = getArchive("geometryOrientationValid.gml");
		XPlanFeatureCollection xPlanFeatureCollection = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollection(testArchive);
		Coordinate startPointOriginal = getSecondPoint(xPlanFeatureCollection);
		XPlanFeatureCollection xPlanFeatureCollectionFixOrientation = XPlanGmlParserBuilder.newBuilder()
			.withFixOrientation(true)
			.build()
			.parseXPlanFeatureCollection(testArchive);
		Coordinate startPointRepaired = getSecondPoint(xPlanFeatureCollectionFixOrientation);
		assertThat(startPointRepaired.getX(), is(startPointOriginal.getX()));
		assertThat(startPointRepaired.getY(), is(startPointOriginal.getY()));
	}

	@Test
	public void testOrientationFixer_invalidLaufrichtung() throws Exception {
		XPlanArchive testArchive = getArchive("geometryOrientationInvalid.gml");
		XPlanFeatureCollection xPlanFeatureCollection = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollection(testArchive);
		Coordinate startPointOriginal = getSecondPoint(xPlanFeatureCollection);
		XPlanFeatureCollection xPlanFeatureCollectionFixOrientation = XPlanGmlParserBuilder.newBuilder()
			.withFixOrientation(true)
			.build()
			.parseXPlanFeatureCollection(testArchive);
		Coordinate startPointRepaired = getSecondPoint(xPlanFeatureCollectionFixOrientation);
		assertThat(startPointRepaired.getX(), not(startPointOriginal.getX()));
		assertThat(startPointRepaired.getY(), not(startPointOriginal.getY()));
	}

	private Coordinate getSecondPoint(XPlanFeatureCollection xPlanFeatureCollection) throws FilterEvaluationException {
		Feature featureById = getFeatureById(xPlanFeatureCollection, "GML_88258139-e3ff-4388-9838-a30775e1f8bf");
		DefaultPolygon polygon = parseGeometry(featureById);
		Ring exteriorRing = polygon.getExteriorRing();
		return new JtsParser().getJTSRing(exteriorRing).getCoordinateN(1);
	}

	private Feature getFeatureById(XPlanFeatureCollection xPlanFeatureCollection, String id)
			throws FilterEvaluationException {
		FeatureCollection features = xPlanFeatureCollection.getFeatures();
		FeatureCollection members = features.getMembers(new IdFilter(id), new TypedObjectNodeXPathEvaluator());
		return members.stream().findFirst().get();
	}

	private DefaultPolygon parseGeometry(Feature featureById) {
		List<Property> properties = featureById.getProperties();
		for (Property property : properties) {
			if ("position".equals(property.getName().getLocalPart())) {
				return (DefaultPolygon) property.getValue();
			}
		}
		return null;
	}

	private XPlanArchive getArchive(String name) throws IOException {
		InputStream resourceAsStream = OrientationFixerTest.class.getResourceAsStream(name);
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromGml("orientation", resourceAsStream);
	}

}
