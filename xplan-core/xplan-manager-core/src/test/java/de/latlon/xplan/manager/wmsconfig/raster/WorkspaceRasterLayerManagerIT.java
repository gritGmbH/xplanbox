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

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;
import static java.nio.file.Files.copy;
import static java.nio.file.Files.createDirectory;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.commons.utils.DoublePair;
import org.deegree.layer.persistence.LayerStore;
import org.deegree.layer.persistence.LayerStoreProvider;
import org.deegree.layer.persistence.tile.TileLayer;
import org.deegree.workspace.Workspace;
import org.junit.Before;
import org.junit.Test;

import de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager.RasterConfigurationType;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class WorkspaceRasterLayerManagerIT {

    private static final String SERVICES = "services";

    private static final String LAYERS = "layers";

    private static final String THEMES = "themes";

    private static final String DATA = "data";

    public static final String TIFF_FILE = "dem30_geotiff_tiled_epsg4326.tiff";

    public static final String RASTER_ID = "rasterId";

    private File workspaceDirectory;

    @Before
    public void createTestWorkspaceFrame()
                            throws Exception {
        workspaceDirectory = createTmpWorkspace().toFile();
    }

    @Test
    public void testCreateRasterConfigurationsWithGeotiffTilestore()
                            throws Exception {
        WorkspaceRasterLayerManager workspaceRasterLayerManager = new WorkspaceRasterLayerManager(
                                                                                                   workspaceDirectory,
                                                                                                   RasterConfigurationType.geotiff,
                                                                                                   "EPSG:4326" );
        double minScaleDenominator = 10;
        double maxScaleDenominator = 1500;
        workspaceRasterLayerManager.createRasterConfigurations( RASTER_ID, TIFF_FILE, minScaleDenominator,
                                                                maxScaleDenominator );

        DeegreeWorkspace workspace = instantiateWorkspace( workspaceDirectory.getName(), workspaceDirectory );
        Workspace newWorkspace = workspace.getNewWorkspace();
        newWorkspace.initAll();
        LayerStore layerStoreMap = newWorkspace.getResource( LayerStoreProvider.class, RASTER_ID );

        assertThat( layerStoreMap, is( notNullValue() ) );
        TileLayer tileLayer = (TileLayer) layerStoreMap.get( RASTER_ID );
        DoublePair scaleDenominators = tileLayer.getMetadata().getScaleDenominators();
        assertThat( scaleDenominators.first, is( minScaleDenominator ) );
        assertThat( scaleDenominators.second, is( maxScaleDenominator ) );
    }

    @Test
    public void testCreateRasterConfigurations_LayerWithDefaultScaleDenominators()
                            throws Exception {
        WorkspaceRasterLayerManager workspaceRasterLayerManager = new WorkspaceRasterLayerManager(
                                                                                                   workspaceDirectory,
                                                                                                   RasterConfigurationType.geotiff,
                                                                                                   "EPSG:4326" );
        workspaceRasterLayerManager.createRasterConfigurations( RASTER_ID, TIFF_FILE, Double.NaN, Double.NaN );

        DeegreeWorkspace workspace = instantiateWorkspace( workspaceDirectory.getName(), workspaceDirectory );
        Workspace newWorkspace = workspace.getNewWorkspace();
        newWorkspace.initAll();
        LayerStore layerStoreMap = newWorkspace.getResource( LayerStoreProvider.class, RASTER_ID );

        assertThat( layerStoreMap, is( notNullValue() ) );
        TileLayer tileLayer = (TileLayer) layerStoreMap.get( RASTER_ID );
        DoublePair scaleDenominators = tileLayer.getMetadata().getScaleDenominators();
        assertThat( scaleDenominators.first, is( Double.NEGATIVE_INFINITY ) );
        assertThat( scaleDenominators.second, is( Double.POSITIVE_INFINITY ) );
    }

    private Path createTmpWorkspace()
                            throws IOException {
        Path workspaceDirectory = Files.createTempDirectory( "WorkspaceRasterLayerManagerIT_" );
        createDirectory( workspaceDirectory.resolve( THEMES ) );
        createDirectory( workspaceDirectory.resolve( LAYERS ) );
        createDirectory( workspaceDirectory.resolve( SERVICES ) );
        Path dataPath = createDirectory( workspaceDirectory.resolve( DATA ) );
        copyFile( TIFF_FILE, dataPath );
        return workspaceDirectory;
    }

    private void copyFile( String resourceName, Path dataPath )
                            throws IOException {
        InputStream resourceAsStream = getClass().getResourceAsStream( resourceName );
        Path targetFile = dataPath.resolve( resourceName );
        copy( resourceAsStream, targetFile );
    }

}
