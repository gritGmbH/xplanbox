package de.latlon.xplan.commons.reference;

import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;
import static org.junit.Assert.assertEquals;

import javax.xml.stream.XMLStreamReader;

import org.deegree.feature.FeatureCollection;
import org.deegree.gml.GMLStreamReader;
import org.junit.Before;
import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;

public class ExternalReferenceScannerTest {

    private ExternalReferenceScanner scanner;

    @Before
    public void setup() {
        scanner = new ExternalReferenceScanner();
    }

    @Test
    public void scanXplan2NoReferences()
                    throws Exception {
        FeatureCollection fc = getMainFileAsFeatureCollection( "xplan2/BP2070.zip" );
        ExternalReferenceInfo referenceInfo = scanner.scan( fc );
        assertEquals( 0, referenceInfo.getExternalRefs().size() );
    }

    @Test
    public void scanXplan3NoReferences()
                    throws Exception {
        FeatureCollection fc = getMainFileAsFeatureCollection( "xplan3/BP2070.zip" );
        ExternalReferenceInfo referenceInfo = scanner.scan( fc );
        assertEquals( 0, referenceInfo.getExternalRefs().size() );
    }

    @Test
    public void scanXplan3WithReferencesAndRasterplan()
                    throws Exception {
        FeatureCollection fc = getMainFileAsFeatureCollection( "xplan3/Wuerdenhain.zip" );
        ExternalReferenceInfo referenceInfo = scanner.scan( fc );
        assertEquals( 13, referenceInfo.getExternalRefs().size() );
        assertEquals( 1, referenceInfo.getRasterPlanBaseScans().size() );
    }

    @Test
    public void scanXplan40WithReferencesAndRasterplan()
                    throws Exception {
        FeatureCollection fc = getMainFileAsFeatureCollection( "xplan40/V4_1_ID_66.zip" );
        ExternalReferenceInfo referenceInfo = scanner.scan( fc );
        assertEquals( 4, referenceInfo.getExternalRefs().size() );
        assertEquals( 1, referenceInfo.getRasterPlanBaseScans().size() );
    }

    @Test
    public void scanXplan41NoReferences()
                    throws Exception {
        FeatureCollection fc = getMainFileAsFeatureCollection( "xplan41/BP2070.zip" );
        ExternalReferenceInfo referenceInfo = scanner.scan( fc );
        assertEquals( 0, referenceInfo.getExternalRefs().size() );
    }

    @Test
    public void scanXplan41WithReferences()
                    throws Exception {
        FeatureCollection fc = getMainFileAsFeatureCollection( "xplan41/Demo.zip" );
        ExternalReferenceInfo referenceInfo = scanner.scan( fc );
        assertEquals( 2, referenceInfo.getExternalRefs().size() );
    }

    @Test
    public void scanXplan41WithReferencesAndRasterplan()
                    throws Exception {
        FeatureCollection fc = getMainFileAsFeatureCollection( "xplan41/V4_1_ID_103.zip" );
        ExternalReferenceInfo referenceInfo = scanner.scan( fc );
        assertEquals( 4, referenceInfo.getExternalRefs().size() );
    }

    private FeatureCollection getMainFileAsFeatureCollection( String name )
                    throws Exception {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        XPlanArchive archive = archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
        XPlanVersion version = archive.getVersion();
        XPlanAde ade = archive.getAde();
        XMLStreamReader xmlReader = archive.getMainFileXmlReader();
        GMLStreamReader gmlReader = createGMLStreamReader( version.getGmlVersion(), xmlReader );
        gmlReader.setApplicationSchema( XPlanSchemas.getInstance().getAppSchema( version, ade ) );
        FeatureCollection fc = gmlReader.readFeatureCollection();
        gmlReader.getIdContext().resolveLocalRefs();
        gmlReader.close();
        xmlReader.close();
        return fc;
    }

}