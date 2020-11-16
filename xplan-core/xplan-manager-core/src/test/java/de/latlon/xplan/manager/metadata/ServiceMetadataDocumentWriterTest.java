/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
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

    private static final String TITLE = "Alsterdorf20";

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

        assertThat( the( serviceMetadataInstance.toString() ),
                    hasXPath( "//gmd:identificationInfo/srv:SV_ServiceIdentification/gmd:citation/gmd:CI_Citation/gmd:title/gco:CharacterString",
                              nsContext(), is( "WMS Bebauungsplan " + TITLE ) ) );
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.setProperty( "CURRENT_DATE", DATE );
        properties.setProperty( "MD_SCOPE_CODE", TYPE );
        properties.setProperty( "TITLE", TITLE );
        return properties;
    }

    private NamespaceContext nsContext() {
        SimpleNamespaceContext nsContext = new SimpleNamespaceContext();
        nsContext.bind( "gmd", "http://www.isotc211.org/2005/gmd" );
        nsContext.bind( "gco", "http://www.isotc211.org/2005/gco" );
        nsContext.bind( "srv", "http://www.isotc211.org/2005/srv" );
        return nsContext;
    }

}
