/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.primitive.Curve;
import org.deegree.geometry.primitive.Polygon;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.primitive.patches.PolygonPatch;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLVersion;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import static org.deegree.geometry.Geometry.GeometryType.PRIMITIVE_GEOMETRY;
import static org.deegree.geometry.primitive.Curve.CurveType.Ring;
import static org.deegree.geometry.primitive.GeometricPrimitive.PrimitiveType.Curve;
import static org.deegree.geometry.primitive.GeometricPrimitive.PrimitiveType.Surface;
import static org.deegree.geometry.primitive.Surface.SurfaceType.Polygon;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for <link>XPlanGeometryInspector</link>
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class XPlanGeometryInspectorTest {

    @Test
    public void testInspectRing_ShouldTestSelfIntersection() {
        XPlanGeometryInspector inspector = mockInspector();
        inspector.inspect( mockRing() );
        verify( inspector, times( 1 ) ).checkSelfIntersection( any( Ring.class ) );
    }

    @Test
    public void testInspectPolygonPatch_ShouldTestSelfIntersection() {
        XPlanGeometryInspector inspector = mockInspector();
        inspector.inspect( mockPolygon() );
        verify( inspector, times( 1 ) ).checkSelfIntersection( any( PolygonPatch.class ) );
        verify( inspector, times( 1 ) ).checkRingOrientations( any( PolygonPatch.class ) );
    }

    @Test
    public void testInspectPolygonPatch_ShouldTestOrientation() {
        XPlanGeometryInspector inspector = mockInspector();
        inspector.inspect( mockPolygon() );
        verify( inspector, times( 1 ) ).checkRingOrientations( any( PolygonPatch.class ) );
    }

    @Test
    public void testInspectRingWithInvalidGeometryWithoutIdShouldBeAddedAsBadGeometry()
                            throws Exception {
        Geometry geometryToInspect = createSelfIntersectingRing();
        XPlanGeometryInspector inspector = createInspectorWithMockedStream();
        inspector.inspect( geometryToInspect );

        List<BadGeometry> badGeometries = inspector.getBadGeometries();
        assertThat( badGeometries.size(), is( 1 ) );
        String id = badGeometries.get( 0 ).getGeometry().getId();
        assertThat( id, is( nullValue() ) );
    }

    private Polygon mockPolygon() {
        Polygon polygonPatch = mock( Polygon.class );
        Mockito.when( polygonPatch.getGeometryType() ).thenReturn( PRIMITIVE_GEOMETRY );
        when( polygonPatch.getPrimitiveType() ).thenReturn( Surface );
        when( polygonPatch.getSurfaceType() ).thenReturn( Polygon );
        when( polygonPatch.getPatches() ).thenReturn( Collections.singletonList( mockPolygonPatch() ) );
        return polygonPatch;

    }

    private PolygonPatch mockPolygonPatch() {
        return mock( PolygonPatch.class );
    }

    private Ring mockRing() {
        Ring mockRing = mock( Ring.class );
        when( mockRing.getGeometryType() ).thenReturn( PRIMITIVE_GEOMETRY );
        when( mockRing.getPrimitiveType() ).thenReturn( Curve );
        when( mockRing.getCurveType() ).thenReturn( Ring );
        return mockRing;
    }

    private Geometry createSelfIntersectingRing()
                            throws Exception {
        URL url = XPlanGeometryInspectorTest.class.getResource( "selfIntersectingRing.gml" );
        return ( (Polygon) GMLInputFactory.createGMLStreamReader( GMLVersion.GML_30,
                                                                  url ).readGeometry() ).getExteriorRing();
    }

    private XPlanGeometryInspector mockInspector() {
        XPlanGeometryInspector inspector = mock( XPlanGeometryInspector.class );
        doAnswer( returnsFirstArg() ).when( inspector ).checkClosed( any( Ring.class ) );
        doAnswer( returnsFirstArg() ).when( inspector ).checkRingOrientations( any( PolygonPatch.class ) );
        doAnswer( returnsFirstArg() ).when( inspector ).createMessage( anyString() );
        when( inspector.inspect( any( Curve.class ) ) ).thenCallRealMethod();
        return inspector;
    }

    private XPlanGeometryInspector createInspectorWithMockedStream() {
        XMLStreamReaderWrapper mockXmlStream = mock( XMLStreamReaderWrapper.class );
        XPlanGeometryInspector inspector = new XPlanGeometryInspector( mockXmlStream );
        XPlanGeometryInspector spiedInspector = Mockito.spy( inspector );
        doAnswer( returnsFirstArg() ).when( spiedInspector ).createMessage( Mockito.anyString() );
        return spiedInspector;
    }

}
