package de.latlon.xplan.validator.geometric.inspector;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GeltungsbereichInspectorTest {

    @Test
    public void testCheck()
                            throws Exception {
        XPlanArchive archive = getTestArchive( "xplan51/V4_1_ID_103_geltungsbereich-erfuellt.zip" );
        GeltungsbereichInspector geltungsbereichInspector = readFeatures( archive );

        List<BadGeometry> errors = geltungsbereichInspector.checkGeometricRule();
        assertThat( errors.size(), is( 0 ) );
    }

    @Test
    public void testCheck_invalid()
                            throws Exception {
        XPlanArchive archive = getTestArchive( "xplan51/V4_1_ID_103.zip" );
        GeltungsbereichInspector geltungsbereichInspector = readFeatures( archive );

        List<BadGeometry> errors = geltungsbereichInspector.checkGeometricRule();
        assertThat( errors.size(), is( 1 ) );
    }

    @Test
    public void testCheck_invalid_41()
                            throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/V4_1_ID_103.zip" );
        GeltungsbereichInspector geltungsbereichInspector = readFeatures( archive );

        List<BadGeometry> errors = geltungsbereichInspector.checkGeometricRule();
        assertThat( errors.size(), is( 1 ) );
    }

    @Test
    public void testCheck_valid_tolerance()
                            throws Exception {
        XPlanArchive archive = getTestArchive( "xplan51/V4_1_ID_103_withtolerance.zip" );
        GeltungsbereichInspector geltungsbereichInspector = readFeatures( archive );

        List<BadGeometry> errors = geltungsbereichInspector.checkGeometricRule();
        assertThat( errors.size(), is( 0 ) );
    }

    private GeltungsbereichInspector readFeatures( XPlanArchive archive )
                            throws XMLStreamException, UnknownCRSException {
        XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper( archive.getMainFileXmlReader(), null );
        XPlanVersion version = archive.getVersion();
        GMLVersion gmlVersion = version.getGmlVersion();
        XPlanAde ade = archive.getAde();
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( version, ade );

        GeometryFactory geomFac = new GeometryFactory();
        GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader( gmlVersion, xmlStream );
        gmlStream.setGeometryFactory( geomFac );
        gmlStream.setApplicationSchema( schema );
        gmlStream.setSkipBrokenGeometries( true );
        GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector();
        gmlStream.addInspector( geltungsbereichInspector );
        gmlStream.readFeature();

        return geltungsbereichInspector;
    }

    private XPlanArchive getTestArchive( String name )
                            throws IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchiveFromZip( name, ResourceAccessor.readResourceStream( name ) );
    }

}
