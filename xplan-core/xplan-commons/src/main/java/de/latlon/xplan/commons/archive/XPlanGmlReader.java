package de.latlon.xplan.commons.archive;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import org.apache.axiom.om.util.XMLStreamWriterFilter;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.persistence.CRSManager;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.Location;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;
import java.io.ByteArrayOutputStream;

import static de.latlon.xplan.commons.XPlanType.valueOfDefaultNull;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static java.lang.String.format;
import static org.deegree.commons.xml.stax.XMLStreamUtils.skipStartDocument;

/**
 * Reads the XPlan GML, pareses required information and updated the planname.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlReader {

    private Location crsLocation;

    private Location typeLocation;

    private XPlanVersion version;

    private XPlanType type;

    private XPlanAde ade;

    private ICRS crs;

    private String district;

    /**
     * Reads the XPlan GML, pareses required information and updated the planname.
     *
     * @param entry
     *                 XplanGML to read
     * @return the XplanGml as {@link MainZipEntry}, never <code>null</code>
     * @throws XMLStreamException
     *                 if the Xplan GML could not be parsed
     */
    public MainZipEntry createZipEntry( ArtefactEntry entry )
                    throws XMLStreamException {
        XMLStreamReader reader = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            reader = createReader( entry );
            XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter( bos );

            copy( reader, writer );
        } finally {
            closeQuietly( reader );
        }
        return new MainZipEntry( bos.toByteArray(), entry.getName(), version, type, ade, crs, district );
    }

    private void copy( XMLStreamReader reader, XMLStreamWriter writer )
                    throws XMLStreamException {
        XMLStreamWriterFilter filter = new XPlanGmlWriterFilter();
        filter.setDelegate( writer );
        writeAll( reader, filter );

        this.district = ( (XPlanGmlWriterFilter) filter ).getDistrict();
    }

    private XMLStreamReader createReader( ZipEntryWithContent entry )
                    throws XMLStreamException, FactoryConfigurationError {
        XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(
                        entry.retrieveContentAsStream() );
        skipStartDocument( xmlReader );
        return xmlReader;
    }

    private void writeAll( XMLStreamReader reader, XMLStreamWriter writer )
                    throws XMLStreamException {
        while ( reader.hasNext() ) {
            write( reader, writer );
            reader.next();
        }
        write( reader, writer );
        writer.flush();
    }

    private void write( XMLStreamReader reader, XMLStreamWriter writer )
                    throws XMLStreamException {
        switch ( reader.getEventType() ) {
        case XMLEvent.START_ELEMENT:
            writeStartElementWithNamespaceBindings( reader, writer );
            break;
        case XMLEvent.END_ELEMENT:
            writer.writeEndElement();
            break;
        case XMLEvent.SPACE:
        case XMLEvent.CHARACTERS:
            writer.writeCharacters( reader.getTextCharacters(), reader.getTextStart(), reader.getTextLength() );
            break;
        case XMLEvent.PROCESSING_INSTRUCTION:
            writer.writeProcessingInstruction( reader.getPITarget(), reader.getPIData() );
            break;
        case XMLEvent.CDATA:
            writer.writeCData( reader.getText() );
            break;

        case XMLEvent.COMMENT:
            writer.writeComment( reader.getText() );
            break;
        case XMLEvent.ENTITY_REFERENCE:
            writer.writeEntityRef( reader.getLocalName() );
            break;
        case XMLEvent.START_DOCUMENT:
            String encoding = reader.getCharacterEncodingScheme();
            String version = reader.getVersion();

            if ( encoding != null && version != null )
                writer.writeStartDocument( encoding, version );
            else if ( version != null )
                writer.writeStartDocument( reader.getVersion() );
            break;
        case XMLEvent.END_DOCUMENT:
            writer.writeEndDocument();
            break;
        case XMLEvent.DTD:
            writer.writeDTD( reader.getText() );
            break;
        }
    }

    private void writeStartElementWithNamespaceBindings( XMLStreamReader reader, XMLStreamWriter writer )
                    throws XMLStreamException {
        writeStartElement( reader, writer );
        writeNamespaces( reader, writer );
        writeAttributes( reader, writer );
    }

    private void writeStartElement( XMLStreamReader reader, XMLStreamWriter writer )
                    throws XMLStreamException {
        String localName = reader.getLocalName();
        String namespaceURI = reader.getNamespaceURI();

        setVersion( namespaceURI );
        setType( localName, reader.getLocation() );
        setAde( namespaceURI );
        setCrs( reader );

        if ( namespaceURI != null && !namespaceURI.isEmpty() ) {
            String prefix = reader.getPrefix();
            if ( prefix != null ) {
                writer.writeStartElement( prefix, localName, namespaceURI );
            } else {
                writer.writeStartElement( namespaceURI, localName );
            }
        } else {
            writer.writeStartElement( localName );
        }
    }

    private void writeNamespaces( XMLStreamReader reader, XMLStreamWriter writer )
                    throws XMLStreamException {
        for ( int namsespaceIndex = 0, namespaceCount = reader.getNamespaceCount();
              namsespaceIndex < namespaceCount; namsespaceIndex++ ) {
            writer.writeNamespace( reader.getNamespacePrefix( namsespaceIndex ),
                                   reader.getNamespaceURI( namsespaceIndex ) );
        }
    }

    private void writeAttributes( XMLStreamReader reader, XMLStreamWriter writer )
                    throws XMLStreamException {
        for ( int namsespaceIndex = 0, attCount = reader.getAttributeCount();
              namsespaceIndex < attCount; namsespaceIndex++ ) {
            String attNamespace = reader.getAttributeNamespace( namsespaceIndex );
            if ( attNamespace != null && !attNamespace.isEmpty() ) {
                writer.writeAttribute( attNamespace, reader.getAttributeLocalName( namsespaceIndex ),
                                       reader.getAttributeValue( namsespaceIndex ) );
            } else {
                writer.writeAttribute( reader.getAttributeLocalName( namsespaceIndex ),
                                       reader.getAttributeValue( namsespaceIndex ) );
            }
        }
    }

    private void setVersion( String namespaceUri ) {
        if ( namespaceUri == null )
            return;
        if ( version == null ) {
            version = XPlanVersionUtils.determineBaseVersion( namespaceUri );
        }
    }

    private void setType( String localName, Location location ) {
        XPlanType currentType = valueOfDefaultNull( localName );
        if ( currentType != null ) {
            if ( type == null ) {
                type = currentType;
                typeLocation = location;
            } else {
                throwDuplicatedTypeException( location, currentType );
            }
        }
    }

    private void setAde( String namespaceUri ) {
        if ( namespaceUri == null )
            return;
        try {
            if ( ade == null && XPLAN_41.equals( version ) ) {
                ade = XPlanAde.valueOfNamespace( namespaceUri );
            }
        } catch ( IllegalArgumentException e ) {
        }
    }

    private void setCrs( XMLStreamReader reader ) {
        String srsName = reader.getAttributeValue( null, "srsName" );
        if ( srsName != null ) {
            if ( crs == null ) {
                crs = CRSManager.getCRSRef( srsName );
                crsLocation = reader.getLocation();
            } else if ( !crs.getName().equals( srsName ) ) {
                throwCrsInConflictException( reader.getLocation(), srsName );
            }
        }
    }

    private void throwCrsInConflictException( Location currentLocation, String currrentCrsName ) {
        String msg = format( "Fehler: Dokument enthält widersprüchliche CRS-Informationen "
                             + "(srsName-Attribute): '%s' (Zeile: %d, Spalte: %d) und '%s' (Zeile: %d, Spalte: %d)",
                             crs.getName(), crsLocation.getLineNumber(), crsLocation.getColumnNumber(), currrentCrsName,
                             currentLocation.getLineNumber(), currentLocation.getColumnNumber() );
        throw new IllegalArgumentException( msg );
    }

    private void throwDuplicatedTypeException( Location r, XPlanType currentType ) {
        String msg = format( "Fehler: Dokument enthält mehr als ein XP_Plan-Element: '%s' (Zeile: %d, Spalte: %d) "
                             + "und '%s' (Zeile: %d, Spalte: %d)", type, typeLocation.getLineNumber(),
                             typeLocation.getColumnNumber(), currentType, r.getLineNumber(), r.getColumnNumber() );
        throw new IllegalArgumentException( msg );
    }

    private void closeQuietly( XMLStreamReader xmlReader ) {
        if ( xmlReader != null ) {
            try {
                xmlReader.close();
            } catch ( XMLStreamException e ) {
                // nothing to do
            }
        }
    }

}