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
package de.latlon.xplan.manager.wmsconfig.raster;

import static de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager.RasterConfigurationType.gdal;
import static de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager.RasterConfigurationType.geotiff;
import static de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager.isGdalSuccessfullInitialized;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.latlon.xplan.commons.archive.ZipEntryWithContent;
import org.junit.Test;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.archive.ArchiveEntry;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager.RasterConfigurationType;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class XPlanRasterManagerTest {

    private static final String CONFIGURED_CRS = "epsg:4326";

    private static final String REF = "reference/XPlanRasterManagerTest";

    private static final String TIFF_EPSG4269_NAME = "XPlanRasterManagerTest_epsg4269.tiff";

    private static final String TIFF_EPSG4326_NAME = "XPlanRasterManagerTest_epsg4326.tiff";

    private static final String TIFF_NO_CRS_NAME = "XPlanRasterManagerTest_noCrs.tiff";

    private static final String PNG_NO_CRS_NAME = "XPlanRasterManagerTest_noCrs.png";

    private static final String PNG_EPSG25833_NAME = "XPlanRasterManagerTest_epsg25833.png";

    private static final String PNG_EPSG25833_AUX_NAME = "XPlanRasterManagerTest_epsg25833.png.aux.xml";

    private static final String TXT_NAME = "XPlanRasterManagerTest.txt";

    @Test
    public void testEvaluateRasterdataGdalWithTiffEpsg4269()
                    throws Exception {
        assumeTrue( isGdalSuccessfullInitialized() );

        XPlanRasterManager xPlanRasterManager = new XPlanRasterManager( mockWmsWorkspaceWrapper(),
                        mockGdalManagerConfig() );
        List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata( mockArchiveWithTiffEpsg4269(),
                                                                                      mockFeatureCollection() );
        RasterEvaluationResult result = results.get( 0 );

        assertThat( result.getRasterName(), is( TIFF_EPSG4269_NAME ) );
        assertThat( result.isCrsSet(), is( true ) );
        assertThat( result.isConfiguredCrs(), is( false ) );
        assertThat( result.isSupportedImageFormat(), is( true ) );
    }

    @Test
    public void testEvaluateRasterdataGdalWithTiffEpsg4326()
                    throws Exception {
        assumeTrue( isGdalSuccessfullInitialized() );

        XPlanRasterManager xPlanRasterManager = new XPlanRasterManager( mockWmsWorkspaceWrapper(),
                        mockGdalManagerConfig() );
        List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata( mockArchiveWithTiffEpsg4326(),
                                                                                      mockFeatureCollection() );
        RasterEvaluationResult result = results.get( 0 );

        assertThat( result.getRasterName(), is( TIFF_EPSG4326_NAME ) );
        assertThat( result.isCrsSet(), is( true ) );
        assertThat( result.isConfiguredCrs(), is( true ) );
        assertThat( result.isSupportedImageFormat(), is( true ) );
    }

    @Test
    public void testEvaluateRasterdataGdalWithTiffNoCrs()
                    throws Exception {
        assumeTrue( isGdalSuccessfullInitialized() );

        XPlanRasterManager xPlanRasterManager = new XPlanRasterManager( mockWmsWorkspaceWrapper(),
                        mockGdalManagerConfig() );
        List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata( mockArchiveWithTiffNoCrs(),
                                                                                      mockFeatureCollection() );
        RasterEvaluationResult result = results.get( 0 );

        assertThat( result.getRasterName(), is( TIFF_NO_CRS_NAME ) );
        assertThat( result.isCrsSet(), is( false ) );
        assertThat( result.isConfiguredCrs(), is( false ) );
        assertThat( result.isSupportedImageFormat(), is( true ) );
    }

    @Test
    public void testEvaluateRasterdataGdalWithTxt()
                    throws Exception {
        assumeTrue( isGdalSuccessfullInitialized() );

        XPlanRasterManager xPlanRasterManager = new XPlanRasterManager( mockWmsWorkspaceWrapper(),
                        mockGdalManagerConfig() );
        List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata( mockArchiveWithTxt(),
                                                                                      mockFeatureCollection() );
        RasterEvaluationResult result = results.get( 0 );

        assertThat( result.getRasterName(), is( TXT_NAME ) );
        assertThat( result.isCrsSet(), is( false ) );
        assertThat( result.isConfiguredCrs(), is( false ) );
        assertThat( result.isSupportedImageFormat(), is( false ) );
    }

    @Test
    public void testEvaluateRasterdataGeotiffWithTiff()
                    throws Exception {
        assumeTrue( isGdalSuccessfullInitialized() );

        XPlanRasterManager xPlanRasterManager = new XPlanRasterManager( mockWmsWorkspaceWrapper(),
                        mockGeotiffManagerConfig() );

        List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata( mockArchiveWithTiffNoCrs(),
                                                                                      mockFeatureCollection() );

        RasterEvaluationResult result = results.get( 0 );

        assertThat( result.getRasterName(), is( TIFF_NO_CRS_NAME ) );
        assertThat( result.isCrsSet(), is( false ) );
        assertThat( result.isConfiguredCrs(), is( true ) );
        assertThat( result.isSupportedImageFormat(), is( true ) );
    }

    @Test
    public void testEvaluateRasterdataGeotiffWithPng()
                    throws Exception {
        assumeTrue( isGdalSuccessfullInitialized() );

        XPlanRasterManager xPlanRasterManager = new XPlanRasterManager( mockWmsWorkspaceWrapper(),
                        mockGeotiffManagerConfig() );

        List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata( mockArchiveWithPngNoCrs(),
                                                                                      mockFeatureCollection() );

        RasterEvaluationResult result = results.get( 0 );

        assertThat( result.getRasterName(), is( PNG_NO_CRS_NAME ) );
        assertThat( result.isCrsSet(), is( false ) );
        assertThat( result.isConfiguredCrs(), is( true ) );
        assertThat( result.isSupportedImageFormat(), is( false ) );
    }

    @Test
    public void testEvaluateRasterdataGdalWithPng25833()
                    throws Exception {
        assumeTrue( isGdalSuccessfullInitialized() );

        XPlanRasterManager xPlanRasterManager = new XPlanRasterManager( mockWmsWorkspaceWrapper(),
                        mockGdalManagerConfig() );
        List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata( mockArchiveWithPngEpsg25833(),
                                                                                      mockFeatureCollection() );
        RasterEvaluationResult result = results.get( 0 );

        assertThat( result.getRasterName(), is( PNG_EPSG25833_NAME ) );
        assertThat( result.isCrsSet(), is( true ) );
        assertThat( result.isConfiguredCrs(), is( false ) );
        assertThat( result.isSupportedImageFormat(), is( true ) );
    }

    private WmsWorkspaceWrapper mockWmsWorkspaceWrapper() {
        return mock( WmsWorkspaceWrapper.class );
    }

    private XPlanArchive mockArchiveWithTiffEpsg4269() {
        return mockArchive( TIFF_EPSG4269_NAME, "dem30_geotiff_tiled.tiff" );
    }

    private XPlanArchive mockArchiveWithTiffNoCrs() {
        return mockArchive( TIFF_NO_CRS_NAME, "dem30.tiff" );
    }

    private XPlanArchive mockArchiveWithPngNoCrs() {
        return mockArchive( PNG_NO_CRS_NAME, "png_nocrs.png" );
    }

    private XPlanArchive mockArchiveWithTiffEpsg4326() {
        return mockArchive( TIFF_EPSG4326_NAME, "dem30_geotiff_tiled_epsg4326.tiff" );
    }

    private XPlanArchive mockArchiveWithTxt() {
        return mockArchive( TXT_NAME, "test.txt" );
    }

    private XPlanArchive mockArchive( String entryName, String resourceName ) {
        XPlanArchive mockedArchive = mock( XPlanArchive.class );
        ZipEntryWithContent mockedEntry = mockZipEntry( mockedArchive, entryName, resourceName );
        when( mockedArchive.getEntry( REF ) ).thenReturn( mockedEntry );

        List<ArchiveEntry> zipFileEntries = Collections.singletonList( mockedEntry );
        doReturn( zipFileEntries ).when( mockedArchive ).getZipFileEntries();
        return mockedArchive;
    }

    private XPlanArchive mockArchiveWithPngEpsg25833() {
        XPlanArchive mockedArchive = mock( XPlanArchive.class );

        ZipEntryWithContent mockedPngEntry = mockZipEntry( mockedArchive, PNG_EPSG25833_NAME, "png_25833.png" );
        ZipEntryWithContent mockedAuxEntry = mockZipEntry( mockedArchive, PNG_EPSG25833_AUX_NAME, "png_25833.png.aux.xml" );

        when( mockedArchive.getEntry( REF ) ).thenReturn( mockedPngEntry );

        List<ArchiveEntry> zipFileEntries = Arrays.asList( mockedPngEntry, mockedAuxEntry );
        doReturn( zipFileEntries ).when( mockedArchive ).getZipFileEntries();
        return mockedArchive;
    }

    private ZipEntryWithContent mockZipEntry( XPlanArchive mockedArchive, String name, String resource ) {
        ZipEntryWithContent mockedEntry = mock( ZipEntryWithContent.class );
        when( mockedEntry.getName() ).thenReturn( name );
        InputStream resourceStream = XPlanRasterManagerTest.class.getResourceAsStream( resource );
        when( mockedArchive.retrieveInputStreamFor( name ) ).thenReturn( resourceStream );
        return mockedEntry;
    }

    private XPlanFeatureCollection mockFeatureCollection() {
        XPlanFeatureCollection mockedFeatureCollection = mock( XPlanFeatureCollection.class );
        ExternalReferenceInfo mockedExternalReferenceInfo = mock( ExternalReferenceInfo.class );

        ExternalReference mockedExternalReference = mock( ExternalReference.class );
        when( mockedExternalReference.getReferenzUrl() ).thenReturn( REF );

        List<ExternalReference> externalReferences = Collections.singletonList( mockedExternalReference );
        when( mockedExternalReferenceInfo.getRasterPlanBaseScans() ).thenReturn( externalReferences );
        when( mockedExternalReferenceInfo.getRasterPlanBaseAndUpdateScans() ).thenReturn( externalReferences );

        when( mockedFeatureCollection.getExternalReferenceInfo() ).thenReturn( mockedExternalReferenceInfo );
        return mockedFeatureCollection;
    }

    private ManagerConfiguration mockGdalManagerConfig() {
        return mockManagerConfig( gdal );
    }

    private ManagerConfiguration mockGeotiffManagerConfig() {
        return mockManagerConfig( geotiff );
    }

    private ManagerConfiguration mockManagerConfig( RasterConfigurationType configurationType ) {
        ManagerConfiguration mockedConfiguration = mock( ManagerConfiguration.class );
        when( mockedConfiguration.getRasterConfigurationType() ).thenReturn( configurationType );
        when( mockedConfiguration.getRasterConfigurationCrs() ).thenReturn( CONFIGURED_CRS );
        return mockedConfiguration;
    }

}