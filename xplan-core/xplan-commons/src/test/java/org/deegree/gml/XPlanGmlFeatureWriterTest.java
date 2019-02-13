package org.deegree.gml;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;
import org.xmlmatchers.namespace.SimpleNamespaceContext;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.hasXPath;
import static org.xmlmatchers.transform.XmlConverters.the;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlFeatureWriterTest {

    @Test
    public void testWrite()
                    throws Exception {
        XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection( "xplan41/Eidelstedt_4_V4.zip" );
        String xPlanFeatureCollection = writeXPlanFeatureCollection( fc );

        assertThat( the( xPlanFeatureCollection ), hasXPath( "/xplan:XPlanAuszug", nsContext() ) );
    }

    private String writeXPlanFeatureCollection( XPlanFeatureCollection xPlanFeatureCollection )
                    throws XMLStreamException, UnknownCRSException, org.deegree.cs.exceptions.TransformationException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        XMLStreamWriter xmlStream = XMLOutputFactory.newFactory().createXMLStreamWriter( os );
        GMLStreamWriter gmlStreamWriter = new XPlanGmlWriter( XPLAN_41, xmlStream );
        gmlStreamWriter.write( xPlanFeatureCollection.getFeatures() );
        gmlStreamWriter.close();
        xmlStream.close();
        return os.toString();
    }

    private XPlanFeatureCollection getMainFileAsXplanFeatureCollection( String name )
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
        return new XPlanFeatureCollection( fc, version, archive.getType(), ade );
    }

    private NamespaceContext nsContext() {
        SimpleNamespaceContext simpleNamespaceContext = new SimpleNamespaceContext();
        simpleNamespaceContext.bind( "xplan", XPLAN_41.getNamespace() );
        return simpleNamespaceContext;
    }

}
