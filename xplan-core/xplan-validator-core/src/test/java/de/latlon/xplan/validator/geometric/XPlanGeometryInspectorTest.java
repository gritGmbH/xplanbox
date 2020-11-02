package de.latlon.xplan.validator.geometric;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.primitive.Polygon;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.primitive.patches.PolygonPatch;
import org.deegree.gml.GMLInputFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.URL;
import java.util.List;

import static org.deegree.gml.GMLVersion.GML_32;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for <link>XPlanGeometryInspector</link>
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
    public void testInspect_RingWithInvalidGeometryWithout_IdShouldBeAddedAsBadGeometry()
                            throws Exception {
        Geometry geometryToInspect =readGeometry( "selfIntersectingRing.gml" );

        XPlanGeometryInspector inspector = createInspectorWithMockedStream();
        inspector.inspect( geometryToInspect );

        List<BadGeometry> badGeometries = inspector.getBadGeometries();
        assertThat( badGeometries.size(), is( 1 ) );
        String id = badGeometries.get( 0 ).getGeometry().getId();
        assertThat( id, is( "GML_ID_67697" ) );
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