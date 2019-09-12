package de.latlon.xplan.validator.geometric;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FlaechenschlussInspectorTest {

    @Test
    public void testRetrieveGeometricallyValidXPlanFeatures()
                            throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/V4_1_ID_103.zip" );
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
        FlaechenschlussInspector flaechenschlussInspector = new FlaechenschlussInspector();
        gmlStream.addInspector( flaechenschlussInspector );
        gmlStream.readFeature();

        boolean flaechenschluss = flaechenschlussInspector.checkFlaechenschluss();
        assertThat( flaechenschluss, is( true ) );
    }

    private XPlanArchive getTestArchive( String name )
                            throws IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }
}