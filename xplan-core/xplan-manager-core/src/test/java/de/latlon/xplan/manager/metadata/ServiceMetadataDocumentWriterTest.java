package de.latlon.xplan.manager.metadata;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.xmlmatchers.namespace.SimpleNamespaceContext;

import javax.xml.namespace.NamespaceContext;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.hasXPath;
import static org.xmlmatchers.transform.XmlConverters.the;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ServiceMetadataDocumentWriterTest {

    private static final String DATE = "2017-01-03";

    private static final String TYPE = "service";

    @Test
    public void testWriteServiceMetadataDocument()
                    throws Exception {
        byte[] template = IOUtils.toByteArray( ServiceMetadataDocumentWriterTest.class.getResourceAsStream(
                        "iso-service-metadata-example-template.xml" ) );

        ByteArrayOutputStream serviceMetadataInstance = new ByteArrayOutputStream();

        ServiceMetadataDocumentWriter serviceMetadataDocumentWriter = new ServiceMetadataDocumentWriter( template );
        serviceMetadataDocumentWriter.writeServiceMetadataDocument( properties(), serviceMetadataInstance );

        assertThat( the( serviceMetadataInstance.toString() ),
                    hasXPath( "//gmd:MD_Metadata/gmd:dateStamp/gco:Date", nsContext(), is( DATE ) ) );

        assertThat( the( serviceMetadataInstance.toString() ),
                    hasXPath( "//gmd:MD_Metadata/gmd:hierarchyLevel/gmd:MD_ScopeCode", nsContext(), is( TYPE ) ) );

        assertThat( the( serviceMetadataInstance.toString() ),
                    hasXPath( "//gmd:MD_Metadata/gmd:hierarchyLevel/gmd:MD_ScopeCode/@codeListValue", nsContext(),
                              is( TYPE ) ) );

        assertThat( the( serviceMetadataInstance.toString() ),
                    hasXPath( "//gmd:MD_Metadata/gmd:hierarchyLevelName/gco:CharacterString", nsContext(),
                              is( TYPE ) ) );

        assertThat( the( serviceMetadataInstance.toString() ),
                    hasXPath( "//gmd:MD_Metadata/gmd:metadataStandardName/gco:CharacterString", nsContext(),
                              is( "NOVALUE" ) ) );

    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.setProperty( "CURRENT_DATE", DATE );
        properties.setProperty( "MD_SCOPE_CODE", TYPE );
        return properties;
    }

    private NamespaceContext nsContext() {
        SimpleNamespaceContext nsContext = new SimpleNamespaceContext();
        nsContext.bind( "gmd", "http://www.isotc211.org/2005/gmd" );
        nsContext.bind( "gco", "http://www.isotc211.org/2005/gco" );
        return nsContext;
    }

}