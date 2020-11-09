package de.latlon.xplan.validator.geometric;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.primitive.LineString;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.primitive.patches.PolygonPatch;
import org.deegree.gml.GMLInputFactory;
import org.hamcrest.CoreMatchers;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.URL;
import java.util.List;

import static org.deegree.gml.GMLVersion.GML_32;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for <link>XPlanGeometryInspector</link>.
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class XPlanGeometryInspectorTest {

    @Test
    public void testInspect_Ring_ShouldTestSelfIntersection()
                            throws Exception {
        XPlanGeometryInspector inspector = createInspectorWithMockedStream();
        inspector.inspect( readGeometry( "curve.gml" ) );
        verify( inspector, times( 1 ) ).checkSelfIntersection( any( Ring.class ) );
    }

    @Test
    public void testInspect_PolygonPatch_ShouldTestSelfIntersectionAndOrientation()
                            throws Exception {
        XPlanGeometryInspector inspector = createInspectorWithMockedStream();
        inspector.inspect( readGeometry( "surface.gml" ) );
        verify( inspector, times( 1 ) ).checkSelfIntersection( any( PolygonPatch.class ) );
        verify( inspector, times( 1 ) ).checkRingOrientations( any( PolygonPatch.class ) );
    }

    @Test
    public void testInspect_RingWithSelfIntersection()
                            throws Exception {
        Geometry geometryToInspect = readGeometry( "selfIntersectingRing.gml" );

        XPlanGeometryInspector inspector = createInspectorWithMockedStream();
        inspector.inspect( geometryToInspect );

        List<BadGeometry> badGeometries = inspector.getBadGeometries();
        assertThat( badGeometries.size(), is( 2 ) );
        assertThat( badGeometries.get( 0 ).getGeometry().getId(), is( "GML_ID_67697_intersection_1" ) );
        assertThat( badGeometries.get( 1 ).getGeometry().getId(), is( "GML_ID_67697" ) );
    }

    @Test
    public void testInspect_RingWithTwoSelfIntersetions()
                            throws Exception {
        Geometry geometryToInspect = readGeometry( "selfIntersectingRIng-2intersections.gml" );
        XPlanGeometryInspector inspector = createInspectorWithMockedStream();
        inspector.inspect( geometryToInspect );

        List<BadGeometry> badGeometries = inspector.getBadGeometries();
        assertThat( badGeometries.size(), is( 3 ) );

        Geometry intersection1 = badGeometries.get( 0 ).getGeometry();
        assertThat( intersection1.getId(), is( "Gml_8AB9C0E6-69DB-4855-A32C-CD9BBC95ABED_intersection_1" ) );
        assertThat( ( (Point) intersection1 ).get0(), is( 583028.4653110565 ) );
        assertThat( ( (Point) intersection1 ).get1(), is( 3581555.9624473285 ) );

        Geometry intersection2 = badGeometries.get( 1 ).getGeometry();
        assertThat( intersection2.getId(), is( "Gml_8AB9C0E6-69DB-4855-A32C-CD9BBC95ABED_intersection_2" ) );
        assertThat( ( (Point) intersection2 ).get0(), is( 583192.1906790873 ) );
        assertThat( ( (Point) intersection2 ).get1(), is( 5920635.179921611 ) );

        Geometry geometry = badGeometries.get( 2 ).getGeometry();
        assertThat( geometry.getId(), is( "Gml_8AB9C0E6-69DB-4855-A32C-CD9BBC95ABED" ) );
    }

    @Test
    public void testInspect_MultiSurface()
                            throws Exception {
        Geometry geometryToInspect = readGeometry( "multiSurface.gml" );
        XPlanGeometryInspector inspector = createInspectorWithMockedStream();
        inspector.inspect( geometryToInspect );

        List<BadGeometry> badGeometries = inspector.getBadGeometries();
        assertThat( badGeometries.size(), is( 0 ) );
    }

    @Ignore("Requires invalid MultiSurface")
    @Test
    public void testInspect_InvalidMultiSurface()
                            throws Exception {
        Geometry geometryToInspect = readGeometry( "multiSurface-invalid.gml" );
        XPlanGeometryInspector inspector = createInspectorWithMockedStream();
        inspector.inspect( geometryToInspect );

        List<BadGeometry> badGeometries = inspector.getBadGeometries();
        assertThat( badGeometries.size(), is( 1 ) );
        String id = badGeometries.get( 0 ).getGeometry().getId();
        assertThat( id, is( "GML_c73710ad-5e75-42ba-9b9c-932427ad5de3" ) );
    }

    private Geometry readGeometry( String geometryFile )
                            throws Exception {
        URL url = XPlanGeometryInspectorTest.class.getResource( geometryFile );
        return GMLInputFactory.createGMLStreamReader( GML_32, url ).readGeometry();
    }

    private XPlanGeometryInspector createInspectorWithMockedStream() {
        XMLStreamReaderWrapper mockXmlStream = mock( XMLStreamReaderWrapper.class );
        XPlanGeometryInspector inspector = new XPlanGeometryInspector( mockXmlStream );
        XPlanGeometryInspector spiedInspector = Mockito.spy( inspector );
        doAnswer( returnsFirstArg() ).when( spiedInspector ).createMessage( Mockito.anyString() );
        return spiedInspector;
    }

}