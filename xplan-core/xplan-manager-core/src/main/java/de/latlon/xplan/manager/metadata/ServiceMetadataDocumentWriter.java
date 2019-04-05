package de.latlon.xplan.manager.metadata;

import org.deegree.commons.xml.stax.XMLStreamUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.Properties;

import static org.deegree.commons.xml.stax.XMLStreamUtils.copy;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ServiceMetadataDocumentWriter {

    private final byte[] template;

    public ServiceMetadataDocumentWriter( byte[] template ) {
        this.template = template;
    }

    public void writeServiceMetadataDocument( Properties properties, OutputStream out )
                    throws XMLStreamException {
        XMLStreamWriter xmlStreamWriter = null;
        XMLStreamReader xmlStreamReader = null;
        try {
            xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter( out );
            xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(
                            new ByteArrayInputStream( template ) );

            TemplateXmlStreamWriterFilter templateWriterFilter = new TemplateXmlStreamWriterFilter( properties );
            templateWriterFilter.setDelegate( xmlStreamWriter );
            copy( templateWriterFilter, xmlStreamReader );
        } finally {
            closeQuietly( xmlStreamReader, xmlStreamWriter );
        }

    }

    private void closeQuietly( XMLStreamReader xmlStreamReader, XMLStreamWriter xmlStreamWriter ) {
        XMLStreamUtils.closeQuietly( xmlStreamReader );
        if ( xmlStreamWriter != null ) {
            try {
                xmlStreamWriter.close();
            } catch ( XMLStreamException e ) {
                // quiet...
            }
        }
    }

}