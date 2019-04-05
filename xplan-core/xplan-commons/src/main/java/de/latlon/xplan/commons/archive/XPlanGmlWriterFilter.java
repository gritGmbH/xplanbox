package de.latlon.xplan.commons.archive;

import org.apache.axiom.om.util.XMLStreamWriterFilterBase;

import javax.xml.stream.XMLStreamException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlWriterFilter extends XMLStreamWriterFilterBase {

    private String currentPath = "";

    private String district;

    @Override
    public void writeStartElement( String localName )
                    throws XMLStreamException {
        this.currentPath += "/" + localName;
        super.writeStartElement( localName );
    }

    @Override
    public void writeStartElement( String namespaceURI, String localName )
                    throws XMLStreamException {
        this.currentPath += "/" + localName;
        super.writeStartElement( namespaceURI, localName );
    }

    @Override
    public void writeStartElement( String prefix, String localName, String namespaceURI )
                    throws XMLStreamException {
        this.currentPath += "/" + localName;
        super.writeStartElement( prefix, localName, namespaceURI );
    }

    @Override
    public void writeEndElement()
                    throws XMLStreamException {
        this.currentPath = this.currentPath.substring( 0, this.currentPath.lastIndexOf( "/" ) );
        super.writeEndElement();
    }

    @Override
    protected String xmlData( String s ) {
        if ( this.currentPath.endsWith( "/gemeinde/XP_Gemeinde/ortsteilName" ) ) {
            this.district = s;
        }
        if ( this.currentPath.endsWith( "ortsteil" ) ) {
            this.district = s;
        }
        return s;
    }

    /**
     * @return the district (if available and the filter was already applied), otherwise <code>null</code>
     */
    public String getDistrict() {
        return district;
    }
}
