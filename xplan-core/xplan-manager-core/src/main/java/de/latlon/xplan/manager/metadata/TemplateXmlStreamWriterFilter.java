package de.latlon.xplan.manager.metadata;

import org.apache.axiom.om.util.XMLStreamWriterFilterBase;

import java.util.Properties;

/**
 * Replaces all occurrences of properties (key pattern: ${PROPERTY_NAME}). If a key is detected but no property value available the key is used as value.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TemplateXmlStreamWriterFilter extends XMLStreamWriterFilterBase {

    private final Properties properties;

    public TemplateXmlStreamWriterFilter( Properties properties ) {
        this.properties = properties;
    }

    @Override
    protected String xmlData( String s ) {
        if ( isProperty( s ) ) {
            String key = s.substring( 2, s.length() - 1 );
            return properties.getProperty( key, key );
        }
        return s;
    }

    private boolean isProperty( String s ) {
        return s.matches( "^\\$\\{.*\\}" );
    }

}