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
package de.latlon.xplan.manager.wmsconfig;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.commons.config.ResourceInitException;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.SimpleGeometryFactory;
import org.deegree.layer.persistence.LayerStore;
import org.deegree.layer.persistence.LayerStoreProvider;
import org.deegree.theme.Theme;
import org.deegree.theme.persistence.ThemeProvider;
import org.deegree.workspace.ResourceStates;
import org.deegree.workspace.ResourceStates.ResourceState;
import org.deegree.workspace.standard.DefaultResourceIdentifier;
import org.junit.Before;
import org.junit.Test;

import javax.xml.transform.Source;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.deegree.commons.config.DeegreeWorkspace.getInstance;
import static org.deegree.cs.CRSUtils.EPSG_4326;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.xmlunit.matchers.EvaluateXPathMatcher.hasXPath;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class WmsWorkspaceManagerIT {

    private static final String SINGLE_RASTER_ID = "raster999";

    private static final String SECOND_RASTER_ID = "raster111";

    private static final String SERVICES = "services";

    private static final String LAYERS = "layers";

    private static final String THEMES = "themes";

    private Path workspaceDirectory;

    @Before
    public void createTestWorkspaceFrame()
                    throws IOException {
        workspaceDirectory = createTmpWorkspace();
    }

    @Test
    public void testUpdateWmsWorkspaceForBPlanShouldContainConfigFilesForVector()
                    throws Exception {
        WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager( workspaceDirectory.toFile() );
        XPlanArchive archive = createArchive();
        wmsWorkspaceManager.updateWmsWorkspace( archive, 999, Collections.<String>emptyList(), null, null, null );

        assertThatWorkspaceIsInstantiable();

        assertThat( file( SERVICES, "wms_999.xml" ).exists(), is( true ) );

        assertThat( file( THEMES, "bplan_999_raster.xml" ).exists(), is( false ) );
        assertThat( file( THEMES, "bplan_999_vector.xml" ).exists(), is( true ) );

        assertThat( file( LAYERS, "bplan_999_raster.xml" ).exists(), is( false ) );
        assertThat( file( LAYERS, "bplan_999_vector.xml" ).exists(), is( true ) );
    }

    @Test
    public void testUpdateWmsWorkspaceForBPlanShouldHaveCorrectServiceConfig()
                    throws Exception {
        WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager( workspaceDirectory.toFile() );
        XPlanArchive archive = createArchive();
        wmsWorkspaceManager.updateWmsWorkspace( archive, 999, Collections.<String>emptyList(), null, null, null );

        assertThatWorkspaceIsInstantiable();

        assertThat( xml( SERVICES, "wms_999.xml" ),
                    hasXPath( "count(//wms:deegreeWMS/wms:ServiceConfiguration/wms:ThemeId)",
                              equalTo( "1" ) ).withNamespaceContext( nsContext() ) );

        assertThat( xml( SERVICES, "wms_999.xml" ), hasXPath( "//wms:deegreeWMS/wms:ServiceConfiguration/wms:ThemeId",
                                                              equalTo( "bplan_999_vector" ) ).withNamespaceContext(
                        nsContext() ) );
    }

    @Test
    public void testUpdateWmsWorkspaceForBPlanWithOneRasterShouldContainConfigFilesForVectorAndRaster()
                    throws Exception {
        WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager( workspaceDirectory.toFile() );
        XPlanArchive archive = createArchive();
        wmsWorkspaceManager.updateWmsWorkspace( archive, 999, rasterId(), null, null, null );

        assertThatWorkspaceIsInstantiable();

        assertThat( file( SERVICES, "wms_999.xml" ).exists(), is( true ) );

        assertThat( file( THEMES, "bplan_999_raster.xml" ).exists(), is( true ) );
        assertThat( file( THEMES, "bplan_999_vector.xml" ).exists(), is( true ) );

        assertThat( file( LAYERS, "bplan_999_vector.xml" ).exists(), is( true ) );
    }

    @Test
    public void testUpdateWmsWorkspaceForBPlanWithOneRasterShouldHaveCorrectServiceConfig()
                    throws Exception {
        WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager( workspaceDirectory.toFile() );
        XPlanArchive archive = createArchive();
        wmsWorkspaceManager.updateWmsWorkspace( archive, 999, rasterId(), null, null, null );

        assertThatWorkspaceIsInstantiable();

        assertThat( xml( SERVICES, "wms_999.xml" ),
                    hasXPath( "count(//wms:deegreeWMS/wms:ServiceConfiguration/wms:ThemeId)",
                              equalTo( "2" ) ).withNamespaceContext( nsContext() ) );

        assertThat( xml( SERVICES, "wms_999.xml" ),
                    hasXPath( "//wms:deegreeWMS/wms:ServiceConfiguration/wms:ThemeId[1]",
                              equalTo( "bplan_999_vector" ) ).withNamespaceContext( nsContext() ) );

        assertThat( xml( SERVICES, "wms_999.xml" ),
                    hasXPath( "//wms:deegreeWMS/wms:ServiceConfiguration/wms:ThemeId[2]",
                              equalTo( "bplan_999_raster" ) ).withNamespaceContext( nsContext() ) );
    }

    @Test
    public void testUpdateWmsWorkspaceForBPlanWithOneRasterShouldHaveCorrectThemesConfig()
                    throws Exception {
        WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager( workspaceDirectory.toFile() );
        XPlanArchive archive = createArchive();
        wmsWorkspaceManager.updateWmsWorkspace( archive, 999, rasterId(), null, null, null );

        assertThatWorkspaceIsInstantiable();

        assertThat( xml( THEMES, "bplan_999_raster.xml" ),
                    hasXPath( "count(//th:Themes/th:Theme/th:Theme/th:Layer)", equalTo( "1" ) ).withNamespaceContext(
                                    nsContext() ) );

        assertThat( xml( THEMES, "bplan_999_raster.xml" ),
                    hasXPath( "//th:Themes/th:Theme/th:Theme/th:Layer",
                              equalTo( SINGLE_RASTER_ID ) ).withNamespaceContext( nsContext() ) );
    }

    @Test
    public void testUpdateWmsWorkspaceForBPlanWithTwoRastersShouldHaveCorrectThemesConfig()
                    throws Exception {
        WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager( workspaceDirectory.toFile() );
        XPlanArchive archive = createArchive();
        wmsWorkspaceManager.updateWmsWorkspace( archive, 999, twoRasterId(), null, null, null );

        assertThatWorkspaceIsInstantiable();

        assertThat( xml( THEMES, "bplan_999_raster.xml" ),
                    hasXPath( "count(//th:Themes/th:Theme/th:Theme/th:Layer)", equalTo( "2" ) ).withNamespaceContext(
                                    nsContext() ) );

        assertThat( xml( THEMES, "bplan_999_raster.xml" ), hasXPath( "//th:Themes/th:Theme/th:Theme/th:Layer[1]",
                                                                     equalTo( SINGLE_RASTER_ID ) ).withNamespaceContext(
                        nsContext() ) );

        assertThat( xml( THEMES, "bplan_999_raster.xml" ), hasXPath( "//th:Themes/th:Theme/th:Theme/th:Layer[2]",
                                                                     equalTo( SECOND_RASTER_ID ) ).withNamespaceContext(
                        nsContext() ) );
    }

    @Test
    public void testUpdateWmsWorkspaceForBPlanShouldBeLoadable()
                    throws Exception {
        WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager( workspaceDirectory.toFile() );
        XPlanArchive archive = createArchive();
        wmsWorkspaceManager.updateWmsWorkspace( archive, 999, Collections.<String>emptyList(), null, null, null );

        DeegreeWorkspace instance = assertThatWorkspaceIsInstantiable();

        ResourceStates states = instance.getNewWorkspace().getStates();
        ResourceState themeState = states.getState( new DefaultResourceIdentifier<Theme>( ThemeProvider.class,
                        "bplan_999_vector" ) );
        assertThat( themeState, is( ResourceStates.ResourceState.Initialized ) );

        ResourceState layerStoreState = states.getState( new DefaultResourceIdentifier<LayerStore>(
                        LayerStoreProvider.class, "bplan_999_vector" ) );
        assertThat( layerStoreState, is( ResourceStates.ResourceState.Error ) );
    }

    @Test
    public void testDeleteWmsWorkspaceForBPlanShouldBeEmpty()
                    throws Exception {
        WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager( workspaceDirectory.toFile() );
        XPlanArchive archive = createArchive();
        int planId = 111;
        wmsWorkspaceManager.updateWmsWorkspace( archive, planId, Collections.<String>emptyList(), null, null, null );

        wmsWorkspaceManager.deleteWmsWorkspaceFilesForId( Integer.toString( planId ) );
        assertThat( filesInDirectory( THEMES ).isEmpty(), is( true ) );
        assertThat( filesInDirectory( LAYERS ).isEmpty(), is( true ) );
        assertThat( filesInDirectory( SERVICES ).isEmpty(), is( true ) );
    }

    @Test
    public void testUpdateWmsWorkspaceForBPlanShouldHaveCorrectConfiguredBboxInLayerConfig()
                    throws Exception {
        WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager( workspaceDirectory.toFile() );
        XPlanArchive archive = createArchive();
        Envelope bboxIn4326 = new SimpleGeometryFactory().createEnvelope( 1, 2, 3, 4, EPSG_4326 );
        wmsWorkspaceManager.updateWmsWorkspace( archive, 999, Collections.<String>emptyList(), null, bboxIn4326, null );

        assertThatWorkspaceIsInstantiable();

        assertThat( xml( LAYERS, "bplan_999_vector.xml" ),
                    hasXPath( "count(//f:FeatureLayer/s:Envelope/s:LowerCorner)",
                              equalTo( "113" ) ).withNamespaceContext( nsContext() ) );

        assertThat( xml( LAYERS, "bplan_999_vector.xml" ),
                    hasXPath( "count(//f:FeatureLayer/s:Envelope/s:UpperCorner)",
                              equalTo( "113" ) ).withNamespaceContext( nsContext() ) );

        assertThat( xml( LAYERS, "bplan_999_vector.xml" ),
                    hasXPath( "//f:FeatureLayer/s:Envelope/s:LowerCorner", equalTo( "1.0 2.0" ) ).withNamespaceContext(
                                    nsContext() ) );

        assertThat( xml( LAYERS, "bplan_999_vector.xml" ),
                    hasXPath( "//f:FeatureLayer/s:Envelope/s:UpperCorner", equalTo( "3.0 4.0" ) ).withNamespaceContext(
                                    nsContext() ) );
    }

    @Test
    public void testUpdateWmsWorkspaceForBPlanShouldHaveCorrectConfiguredDefaultBboxInLayerConfig()
                    throws Exception {
        WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager( workspaceDirectory.toFile() );
        XPlanArchive archive = createArchive();
        Envelope defaultBboxIn4326 = new SimpleGeometryFactory().createEnvelope( 1, 2, 3, 4, EPSG_4326 );
        wmsWorkspaceManager.updateWmsWorkspace( archive, 999, Collections.<String>emptyList(), null, null,
                                                defaultBboxIn4326 );

        assertThatWorkspaceIsInstantiable();

        assertThat( xml( LAYERS, "bplan_999_vector.xml" ),
                    hasXPath( "count(//f:FeatureLayer/s:Envelope/s:LowerCorner)",
                              equalTo( "113" ) ).withNamespaceContext( nsContext() ) );

        assertThat( xml( LAYERS, "bplan_999_vector.xml" ),
                    hasXPath( "count(//f:FeatureLayer/s:Envelope/s:UpperCorner)",
                              equalTo( "113" ) ).withNamespaceContext( nsContext() ) );

        assertThat( xml( LAYERS, "bplan_999_vector.xml" ),
                    hasXPath( "//f:FeatureLayer/s:Envelope/s:LowerCorner", equalTo( "1.0 2.0" ) ).withNamespaceContext(
                                    nsContext() ) );

        assertThat( xml( LAYERS, "bplan_999_vector.xml" ),
                    hasXPath( "//f:FeatureLayer/s:Envelope/s:UpperCorner", equalTo( "3.0 4.0" ) ).withNamespaceContext(
                                    nsContext() ) );
    }

    @Test
    public void testUpdateWmsWorkspaceForBPlanShouldHaveCorrectDefaultBboxInLayerConfig()
                    throws Exception {
        WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager( workspaceDirectory.toFile() );
        XPlanArchive archive = createArchive();
        wmsWorkspaceManager.updateWmsWorkspace( archive, 999, Collections.<String>emptyList(), null, null, null );

        assertThatWorkspaceIsInstantiable();

        assertThat( xml( LAYERS, "bplan_999_vector.xml" ),
                    hasXPath( "count(//f:FeatureLayer/s:Envelope/s:LowerCorner)",
                              equalTo( "113" ) ).withNamespaceContext( nsContext() ) );

        assertThat( xml( LAYERS, "bplan_999_vector.xml" ),
                    hasXPath( "count(//f:FeatureLayer/s:Envelope/s:UpperCorner)",
                              equalTo( "113" ) ).withNamespaceContext( nsContext() ) );

        assertThat( xml( LAYERS, "bplan_999_vector.xml" ),
                    hasXPath( "//f:FeatureLayer/s:Envelope/s:LowerCorner", equalTo( "-180 -90" ) ).withNamespaceContext(
                                    nsContext() ) );

        assertThat( xml( LAYERS, "bplan_999_vector.xml" ),
                    hasXPath( "//f:FeatureLayer/s:Envelope/s:UpperCorner", equalTo( "180 90" ) ).withNamespaceContext(
                                    nsContext() ) );
    }

    private XPlanArchive createArchive()
                    throws IOException {
        InputStream inputStream = ResourceAccessor.readResourceStream( "/de/latlon/xplan/xplan2/BP2070.zip" );
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchiveFromZip( "testArchive", inputStream );
    }

    private List<String> rasterId() {
        return new ArrayList<String>( Arrays.asList( SINGLE_RASTER_ID ) );
    }

    private List<String> twoRasterId() {
        return new ArrayList<String>( Arrays.asList( SINGLE_RASTER_ID, SECOND_RASTER_ID ) );
    }

    private Map<String, String> nsContext() {
        Map<String, String> nsContext = new HashMap<>();
        nsContext.put( "wms", "http://www.deegree.org/services/wms" );
        nsContext.put( "th", "http://www.deegree.org/themes/standard" );
        nsContext.put( "f", "http://www.deegree.org/layers/feature" );
        nsContext.put( "s", "http://www.deegree.org/metadata/spatial" );
        return nsContext;
    }


    private File file( String subDirectory, String name ) {
        return workspaceDirectory.resolve( subDirectory ).resolve( name ).toFile();
    }

    private String xml( String subDirectory, String name )
                    throws IOException {
        return readFileToString( file( subDirectory, name ) );
    }

    private List<String> filesInDirectory( String subDirectory ) {
        return asList( workspaceDirectory.resolve( subDirectory ).toFile().list() );
    }

    private Path createTmpWorkspace()
                    throws IOException {
        Path workspaceDirectory = Files.createTempDirectory( "deegreeWorkspace-WMS-WorkspaceManager-IT" );
        Files.createDirectory( workspaceDirectory.resolve( THEMES ) );
        Files.createDirectory( workspaceDirectory.resolve( LAYERS ) );
        Files.createDirectory( workspaceDirectory.resolve( SERVICES ) );
        return workspaceDirectory;
    }

    private DeegreeWorkspace assertThatWorkspaceIsInstantiable()
                    throws IOException, ResourceInitException {
        DeegreeWorkspace instance = getInstance( "UNKNOWN", workspaceDirectory.toFile() );
        instance.initAll();
        return instance;
    }

}
