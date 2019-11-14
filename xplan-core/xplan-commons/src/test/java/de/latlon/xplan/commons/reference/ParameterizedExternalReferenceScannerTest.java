package de.latlon.xplan.commons.reference;

import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;
import static org.junit.Assert.assertEquals;

import javax.xml.stream.XMLStreamReader;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import org.deegree.feature.FeatureCollection;
import org.deegree.gml.GMLStreamReader;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;

@RunWith(JUnitParamsRunner.class)
public class ParameterizedExternalReferenceScannerTest {

    @FileParameters("src/test/resources/de/latlon/xplan/commons/reference/externalReferenceScanner-test-input.csv")
    @Test
    public void testValidationOfSingleRule( String resourceUnderTest, int externalRefs, int rasterPlanBaseScans )
                            throws Exception {
        FeatureCollection fc = getMainFileAsFeatureCollection( resourceUnderTest );
        ExternalReferenceInfo referenceInfo = new ExternalReferenceScanner().scan( fc );
        assertEquals( externalRefs, referenceInfo.getExternalRefs().size() );
        assertEquals( rasterPlanBaseScans, referenceInfo.getRasterPlanBaseScans().size() );

    }

    private FeatureCollection getMainFileAsFeatureCollection( String name )
                            throws Exception {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip( name, ResourceAccessor.readResourceStream( name ) );
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