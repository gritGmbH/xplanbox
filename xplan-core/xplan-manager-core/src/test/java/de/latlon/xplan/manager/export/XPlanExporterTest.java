//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.export;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.manager.export.XPlanExporter.MAIN_FILE_REEXPORTED_PREFIX;
import static de.latlon.xplan.manager.export.XPlanExporter.MAIN_FILE_REEXPORTED_SUFFIX;
import static org.apache.commons.io.IOUtils.copyLarge;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.xmlmatchers.XmlMatchers.conformsTo;
import static org.xmlmatchers.transform.XmlConverters.the;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.validation.Schema;

import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.junit.Test;
import org.xmlmatchers.validation.SchemaFactory;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class XPlanExporterTest {

    private static final String MAIN_FILE_REEXPORTED = MAIN_FILE_REEXPORTED_PREFIX + MAIN_FILE_REEXPORTED_SUFFIX;

    private static final String MAIN_FILE_REEXPORTED_1 = MAIN_FILE_REEXPORTED_PREFIX + "-1"
                                                         + MAIN_FILE_REEXPORTED_SUFFIX;

    private static final String MAIN_FILE_REEXPORTED_2 = MAIN_FILE_REEXPORTED_PREFIX + "-2"
                                                         + MAIN_FILE_REEXPORTED_SUFFIX;

    private static final String MAIN_FILE_REEXPORTED_3 = MAIN_FILE_REEXPORTED_PREFIX + "-3"
                                                         + MAIN_FILE_REEXPORTED_SUFFIX;

    private static final String MAIN_FILE_REEXPORTED_WITH_TEXT = MAIN_FILE_REEXPORTED_PREFIX + "-zz"
                                                                 + MAIN_FILE_REEXPORTED_SUFFIX;

    private static final String MAIN_FILE_REEXPORTED_38 = MAIN_FILE_REEXPORTED_PREFIX + "-38"
                                                          + MAIN_FILE_REEXPORTED_SUFFIX;

    private static final String MAIN_FILE_REEXPORTED_39 = MAIN_FILE_REEXPORTED_PREFIX + "-39"
                                                          + MAIN_FILE_REEXPORTED_SUFFIX;

    private XPlanExporter exporter = new XPlanExporter( mockManagerConfiguration( true ) );

    @Test
    public void testExport()
                    throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XPlanArtefactIterator artefacts = mockArtefactIterator();
        XPlanArchiveContent contents = createContents( artefacts );
        exporter.export( outputStream, contents );

        List<String> exportedFiles = readExportedContent( outputStream );

        assertThat( exportedFiles.size(), is( 3 ) );
        assertThat( exportedFiles, hasItems( "1.xml", "2.xml", MAIN_FILE_REEXPORTED ) );
    }

    @Test
    public void testExportWithDeactivatedReexported()
                    throws Exception {
        XPlanExporter xplanExporter = new XPlanExporter( mockManagerConfiguration( false ) );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XPlanArtefactIterator artefacts = mockArtefactIterator();
        XPlanArchiveContent contents = createContents( artefacts );
        xplanExporter.export( outputStream, contents );

        List<String> exportedFiles = readExportedContent( outputStream );

        assertThat( exportedFiles.size(), is( 2 ) );
        assertThat( exportedFiles, hasItems( "1.xml", "2.xml" ) );
    }

    @Test
    public void testExportWithNullManagerConfiguration()
                    throws Exception {
        XPlanExporter xplanExporter = new XPlanExporter( null );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XPlanArtefactIterator artefacts = mockArtefactIterator();
        XPlanArchiveContent contents = createContents( artefacts );
        xplanExporter.export( outputStream, contents );

        List<String> exportedFiles = readExportedContent( outputStream );

        assertThat( exportedFiles.size(), is( 2 ) );
        assertThat( exportedFiles, hasItems( "1.xml", "2.xml" ) );
    }

    @Test
    public void testExportWithReexported()
                    throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XPlanArtefactIterator artefacts = mockArtefactIteratorWithReexported();
        XPlanArchiveContent contents = createContents( artefacts );
        exporter.export( outputStream, contents );

        List<String> exportedFiles = readExportedContent( outputStream );

        assertThat( exportedFiles.size(), is( 4 ) );
        assertThat( exportedFiles, hasItems( "1.xml", "2.xml", MAIN_FILE_REEXPORTED, MAIN_FILE_REEXPORTED_1 ) );
    }

    @Test
    public void testExportWithMultipleReexported()
                    throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XPlanArtefactIterator artefacts = mockArtefactIteratorWithMultipleReexported();
        XPlanArchiveContent contents = createContents( artefacts );
        exporter.export( outputStream, contents );

        List<String> exportedFiles = readExportedContent( outputStream );

        assertThat( exportedFiles.size(), is( 6 ) );
        assertThat( exportedFiles, hasItems( "1.xml", "2.xml", MAIN_FILE_REEXPORTED, MAIN_FILE_REEXPORTED_1,
                                             MAIN_FILE_REEXPORTED_2, MAIN_FILE_REEXPORTED_3 ) );
    }

    @Test
    public void testExportWithMultipleReexportedNotInOrder()
                    throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XPlanArtefactIterator artefacts = mockArtefactIteratorWithMultipleReexportedNotInOrder();
        XPlanArchiveContent contents = createContents( artefacts );
        exporter.export( outputStream, contents );

        List<String> exportedFiles = readExportedContent( outputStream );

        assertThat( exportedFiles.size(), is( 8 ) );
        assertThat( exportedFiles,
                    hasItems( "1.xml", "2.xml", MAIN_FILE_REEXPORTED, MAIN_FILE_REEXPORTED_1, MAIN_FILE_REEXPORTED_2,
                              MAIN_FILE_REEXPORTED_3, MAIN_FILE_REEXPORTED_38, MAIN_FILE_REEXPORTED_39 ) );
    }

    @Test
    public void testExportWithMultipleReexportedWithText()
                    throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XPlanArtefactIterator artefacts = mockArtefactIteratorWithMultipleReexportedWithText();
        XPlanArchiveContent contents = createContents( artefacts );
        exporter.export( outputStream, contents );

        List<String> exportedFiles = readExportedContent( outputStream );

        assertThat( exportedFiles.size(), is( 5 ) );
        assertThat( exportedFiles, hasItems( "1.xml", "2.xml", MAIN_FILE_REEXPORTED, MAIN_FILE_REEXPORTED_WITH_TEXT,
                                             MAIN_FILE_REEXPORTED_1 ) );
    }

    @Test
    public void testExport_SchemaConform()
                    throws Exception {
        FeatureCollection featureCollection = readFeatureCollection( "xplan41/V4_1_ID_103.zip" );

        XPlanExporter planExporter = new XPlanExporter( mockManagerConfiguration( true ) );
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        planExporter.export( outputStream, XPLAN_41, featureCollection, null );

        Schema schema = SchemaFactory.w3cXmlSchemaFrom( XPLAN_41.getSchemaUrl() );
        String exportedPlan = new String( outputStream.toByteArray() );

        assertThat( the( exportedPlan ), conformsTo( schema ) );
    }

    private XPlanArchiveContent createContents( XPlanArtefactIterator artefacts )
                    throws Exception {
        FeatureCollection restoredFeatureCollection = readFeatureCollection( "xplan41/V4_1_ID_103.zip" );
        return new XPlanArchiveContent( restoredFeatureCollection, artefacts, XPlanVersion.XPLAN_41 );
    }

    private ManagerConfiguration mockManagerConfiguration( boolean isExportOfReexportedActive ) {
        ManagerConfiguration mockedManagerConfiguraion = mock( ManagerConfiguration.class );
        when( mockedManagerConfiguraion.isExportOfReexportedActive() ).thenReturn( isExportOfReexportedActive );
        return mockedManagerConfiguraion;
    }

    private XPlanArtefactIterator mockArtefactIterator()
                    throws Exception {
        XPlanArtefactIterator mockedIterator = mock( XPlanArtefactIterator.class );
        when( mockedIterator.hasNext() ).thenReturn( true, true, false );
        XPlanArtefact stream1 = stream( "1.xml" );
        XPlanArtefact stream2 = stream( "2.xml" );
        when( mockedIterator.next() ).thenReturn( stream1, stream2, null );
        return mockedIterator;
    }

    private XPlanArtefactIterator mockArtefactIteratorWithReexported()
                    throws Exception {
        XPlanArtefactIterator mockedIterator = mock( XPlanArtefactIterator.class );
        when( mockedIterator.hasNext() ).thenReturn( true, true, true, false );
        XPlanArtefact stream1 = stream( "1.xml" );
        XPlanArtefact stream2 = stream( "2.xml" );
        XPlanArtefact streamReexported = stream( MAIN_FILE_REEXPORTED );
        when( mockedIterator.next() ).thenReturn( stream1, stream2, streamReexported, null );
        return mockedIterator;
    }

    private XPlanArtefactIterator mockArtefactIteratorWithMultipleReexported()
                    throws Exception {
        XPlanArtefactIterator mockedIterator = mock( XPlanArtefactIterator.class );
        when( mockedIterator.hasNext() ).thenReturn( true, true, true, true, true, false );
        XPlanArtefact stream1 = stream( "1.xml" );
        XPlanArtefact stream2 = stream( "2.xml" );
        XPlanArtefact streamReexported = stream( MAIN_FILE_REEXPORTED );
        XPlanArtefact streamReexported1 = stream( MAIN_FILE_REEXPORTED_1 );
        XPlanArtefact streamReexported2 = stream( MAIN_FILE_REEXPORTED_2 );
        when( mockedIterator.next() ).thenReturn( stream1, stream2, streamReexported, streamReexported1,
                                                  streamReexported2, null );
        return mockedIterator;
    }

    private XPlanArtefactIterator mockArtefactIteratorWithMultipleReexportedNotInOrder()
                    throws Exception {
        XPlanArtefactIterator mockedIterator = mock( XPlanArtefactIterator.class );
        when( mockedIterator.hasNext() ).thenReturn( true, true, true, true, true, true, true, false );
        XPlanArtefact stream1 = stream( "1.xml" );
        XPlanArtefact stream2 = stream( "2.xml" );
        XPlanArtefact streamReexported = stream( MAIN_FILE_REEXPORTED );
        XPlanArtefact streamReexported1 = stream( MAIN_FILE_REEXPORTED_1 );
        XPlanArtefact streamReexported2 = stream( MAIN_FILE_REEXPORTED_2 );
        XPlanArtefact streamReexported3 = stream( MAIN_FILE_REEXPORTED_3 );
        XPlanArtefact streamReexported38 = stream( MAIN_FILE_REEXPORTED_38 );
        when( mockedIterator.next() ).thenReturn( stream1, stream2, streamReexported, streamReexported1,
                                                  streamReexported2, streamReexported3, streamReexported38, null );
        return mockedIterator;
    }

    private XPlanArtefactIterator mockArtefactIteratorWithMultipleReexportedWithText()
                    throws Exception {
        XPlanArtefactIterator mockedIterator = mock( XPlanArtefactIterator.class );
        when( mockedIterator.hasNext() ).thenReturn( true, true, true, true, false );
        XPlanArtefact stream1 = stream( "1.xml" );
        XPlanArtefact stream2 = stream( "2.xml" );
        XPlanArtefact streamReexported = stream( MAIN_FILE_REEXPORTED );
        XPlanArtefact streamReexportedWithText = stream( MAIN_FILE_REEXPORTED_WITH_TEXT );
        when( mockedIterator.next() ).thenReturn( stream1, stream2, streamReexported, streamReexportedWithText, null );
        return mockedIterator;
    }

    private XPlanArtefact stream( String name )
                    throws IOException {
        XPlanArtefact xPlanArtefact = mock( XPlanArtefact.class );
        when( xPlanArtefact.getFileName() ).thenReturn( name );
        ByteArrayInputStream content = createZippedContent( name );
        when( xPlanArtefact.getContent() ).thenReturn( content );
        return xPlanArtefact;
    }

    private List<String> readExportedContent( ByteArrayOutputStream outputStream )
                    throws Exception {
        List<String> exportedFiles = new ArrayList<String>();
        ZipInputStream zipInputStream = new ZipInputStream( new ByteArrayInputStream( outputStream.toByteArray() ) );
        ZipEntry entry;
        while ( ( entry = zipInputStream.getNextEntry() ) != null ) {
            exportedFiles.add( entry.getName() );
        }
        return exportedFiles;
    }

    private FeatureCollection readFeatureCollection( String archiveName )
                    throws Exception {
        XPlanArchive archive = createArchive( archiveName );
        XPlanFeatureCollection xplanFc = readFeatures( archive );
        return xplanFc.getFeatures();
    }

    private XPlanArchive createArchive( String testArchiveName )
                    throws IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        InputStream archiveResource = ResourceAccessor.readResourceStream( testArchiveName );
        return archiveCreator.createXPlanArchive( testArchiveName, archiveResource );
    }

    private XPlanFeatureCollection readFeatures( XPlanArchive archive )
                    throws Exception {
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( archive.getVersion(), archive.getAde() );
        ICRS crs = CRSManager.lookup( "EPSG:31467" );
        if ( archive.getCrs() != null )
            crs = archive.getCrs();
        return ( new GeometricValidatorImpl() ).retrieveGeometricallyValidXPlanFeatures( archive, crs, schema, true,
                                                                                         null );
    }

    private ByteArrayInputStream createZippedContent( String name )
                    throws IOException {
        InputStream is = new ByteArrayInputStream( name.getBytes() );
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gos = new GZIPOutputStream( bos );
        copyLarge( is, gos );
        gos.close();
        return new ByteArrayInputStream( bos.toByteArray() );
    }

}