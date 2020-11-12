/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package org.deegree.gml;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
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
        return new XPlanFeatureCollectionBuilder( fc, archive.getType() ).build();
    }

    private NamespaceContext nsContext() {
        SimpleNamespaceContext simpleNamespaceContext = new SimpleNamespaceContext();
        simpleNamespaceContext.bind( "xplan", XPLAN_41.getNamespace() );
        return simpleNamespaceContext;
    }

}
