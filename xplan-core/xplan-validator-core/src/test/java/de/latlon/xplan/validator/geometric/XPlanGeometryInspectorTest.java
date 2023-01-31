/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.io.WKTWriter;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.primitive.patches.PolygonPatch;
import org.deegree.gml.GMLInputFactory;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.URL;
import java.util.List;

import static org.deegree.gml.GMLVersion.GML_32;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for <link>XPlanGeometryInspector</link>.
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanGeometryInspectorTest {

	@Test
	public void testInspect_PolygonWithInteriorRing() throws Exception {
		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(readGeometry("polygonWithInteriorRing.gml"));

		List<BadGeometry> badGeometries = inspector.getBadGeometries();
		assertThat(badGeometries.size(), is(0));
	}

	@Test
	public void testInspect_PolygonWithInteriorRing_touching() throws Exception {
		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(readGeometry("polygonWithInteriorRing-touching.gml"));

		List<BadGeometry> badGeometries = inspector.getBadGeometries();
		assertThat(badGeometries.size(), is(0));
	}

	@Test
	public void testInspect_PolygonWithInteriorRings_touching() throws Exception {
		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(readGeometry("polygonWithInteriorRings-touching.gml"));

		List<BadGeometry> badGeometries = inspector.getBadGeometries();
		assertThat(badGeometries.size(), is(0));
	}

	@Test
	public void testInspect_Ring_ShouldTestSelfIntersection() throws Exception {
		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(readGeometry("curve.gml"));
		verify(inspector, times(1)).checkSelfIntersection(any(Ring.class));
	}

	@Test
	public void testInspect_PolygonPatch_ShouldTestSelfIntersectionAndOrientation() throws Exception {
		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(readGeometry("surface.gml"));
		verify(inspector, times(1)).checkSelfIntersection(any(PolygonPatch.class));
		verify(inspector, times(1)).checkRingOrientations(any(PolygonPatch.class));
	}

	@Test
	public void testInspect_RingWithSelfIntersection() throws Exception {
		Geometry geometryToInspect = readGeometry("selfIntersectingRing.gml");

		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(geometryToInspect);

		List<BadGeometry> badGeometries = inspector.getBadGeometries();
		assertThat(badGeometries.size(), is(3));
		assertThat(badGeometries.get(0).getOriginalGeometry().getId(), is("GML_ID_67697_intersection_1"));
		assertThat(badGeometries.get(1).getOriginalGeometry().getId(), is("GML_ID_67697_intersection_2"));
		assertThat(badGeometries.get(2).getOriginalGeometry().getId(), is("GML_ID_67697"));
	}

	@Test
	public void testInspect_RingWithSelfIntersectionAtTheSamePoint() throws Exception {
		Geometry geometryToInspect = readGeometry("selfIntersectingRing-samePoint.gml");

		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(geometryToInspect);

		List<BadGeometry> badGeometries = inspector.getBadGeometries();
		assertThat(badGeometries.size(), is(2));
		assertThat(badGeometries.get(0).getOriginalGeometry().getId(), is("GML_ID_67697_doppelterStuetzpunkt_1"));
		assertThat(badGeometries.get(1).getOriginalGeometry().getId(), is("GML_ID_67697"));
		assertThat(badGeometries.get(1).getErrors().size(), is(1));
	}

	@Test
	public void testInspect_RingWithDuplicateControlPoint() throws Exception {
		Geometry geometryToInspect = readGeometry("duplicateControlPointRing.gml");

		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(geometryToInspect);

		List<BadGeometry> badGeometries = inspector.getBadGeometries();
		assertThat(badGeometries.size(), is(2));

		Geometry duplicateControlPoint1 = badGeometries.get(0).getOriginalGeometry();
		assertThat(duplicateControlPoint1.getId(), is("GML_doppelterStuetzpunkt_doppelterStuetzpunkt_1"));

		Geometry geometry = badGeometries.get(1).getOriginalGeometry();
		assertThat(geometry.getId(), is("GML_doppelterStuetzpunkt"));
	}

	@Test
	public void testInspect_RingWithTwoSelfIntersetions() throws Exception {
		Geometry geometryToInspect = readGeometry("selfIntersectingRIng-2intersections.gml");
		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(geometryToInspect);

		List<BadGeometry> badGeometries = inspector.getBadGeometries();
		assertThat(badGeometries.size(), is(3));

		Geometry intersection1 = badGeometries.get(0).getOriginalGeometry();
		assertThat(intersection1.getId(), is("Gml_8AB9C0E6-69DB-4855-A32C-CD9BBC95ABED_intersection_1"));
		assertThat(((Point) intersection1).get0(), is(583192.1906790873));
		assertThat(((Point) intersection1).get1(), is(5920635.179921611));

		Geometry intersection2 = badGeometries.get(1).getOriginalGeometry();
		assertThat(intersection2.getId(), is("Gml_8AB9C0E6-69DB-4855-A32C-CD9BBC95ABED_intersection_2"));
		assertThat(((Point) intersection2).get0(), is(583028.4653110565));
		assertThat(((Point) intersection2).get1(), is(3581555.9624473285));

		Geometry geometry = badGeometries.get(2).getOriginalGeometry();
		assertThat(geometry.getId(), is("Gml_8AB9C0E6-69DB-4855-A32C-CD9BBC95ABED"));
	}

	@Test
	public void testInspect_MultiSurface_shouldBeValid() throws Exception {
		Geometry geometryToInspect = readGeometry("multiSurface.gml");
		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(geometryToInspect);

		List<BadGeometry> badGeometries = inspector.getBadGeometries();
		assertThat(badGeometries.size(), is(0));
	}

	@Test
	public void testInspect_MultiSurfaceTouches_shouldBeValid() throws Exception {
		Geometry geometryToInspect = readGeometry("multiSurface-touches.gml");
		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(geometryToInspect);

		assertThat(inspector.getBadGeometries().size(), is(0));
		assertThat(inspector.getErrors().size(), is(0));
	}

	@Test
	public void testInspect_MultiSurfaceIntersection_shouldBeInvalid() throws Exception {
		Geometry geometryToInspect = readGeometry("multiSurface-intersection.gml");
		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(geometryToInspect);

		List<BadGeometry> badGeometries = inspector.getBadGeometries();
		assertThat(badGeometries.size(), is(1));
		Geometry intersection = badGeometries.get(0).getOriginalGeometry();
		assertThat(intersection.getId(), is("GML_48d90d78-aa4a-44cc-939b-3562757993c6_intersection_1"));
		assertThat(inspector.getErrors().size(), is(1));
	}

	@Test
	public void testInspect_MultiSurfaceIntersectionInSelfIntersection() throws Exception {
		Geometry geometryToInspect = readGeometry("multiSurface-intersectionInIntersection.gml");
		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(geometryToInspect);

		assertThat(inspector.getErrors().size(), is(2));
		assertThat(inspector.getBadGeometries().size(), is(1));
	}

	@Test
	public void testInspect_MultiSurfaceCoveringGeometries() throws Exception {
		Geometry geometryToInspect = readGeometry("multiSurface-covering.gml");
		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(geometryToInspect);

		assertThat(inspector.getErrors().size(), is(1));
		assertThat(inspector.getBadGeometries().size(), is(1));
	}

	@Test
	public void testInspect_InvalidOrientation() throws Exception {
		Geometry geometryToInspect = readGeometry("polygon-orientation-invalid.gml");
		XPlanGeometryInspector inspector = createInspectorWithMockedStream();
		inspector.inspect(geometryToInspect);

		List<BadGeometry> badGeometries = inspector.getBadGeometries();
		assertThat(badGeometries.size(), is(2));
	}

	@Test
	public void testInspect_InvalidOrientation_skipOrientation() throws Exception {
		Geometry geometryToInspect = readGeometry("polygon-orientation-invalid.gml");
		XPlanGeometryInspector inspector = createInspectorWithMockedStream(true);
		inspector.inspect(geometryToInspect);

		List<BadGeometry> badGeometries = inspector.getBadGeometries();
		assertThat(badGeometries.size(), is(0));
	}

	private Geometry readGeometry(String geometryFile) throws Exception {
		URL url = XPlanGeometryInspectorTest.class.getResource(geometryFile);
		return GMLInputFactory.createGMLStreamReader(GML_32, url).readGeometry();
	}

	private XPlanGeometryInspector createInspectorWithMockedStream() {
		return createInspectorWithMockedStream(false);
	}

	private XPlanGeometryInspector createInspectorWithMockedStream(boolean skipOrientation) {
		XMLStreamReaderWrapper mockXmlStream = mock(XMLStreamReaderWrapper.class);
		XPlanGeometryInspector inspector = new XPlanGeometryInspector(mockXmlStream, skipOrientation);
		XPlanGeometryInspector spiedInspector = Mockito.spy(inspector);
		doAnswer(returnsFirstArg()).when(spiedInspector).createMessage(Mockito.anyString());
		return spiedInspector;
	}

}
