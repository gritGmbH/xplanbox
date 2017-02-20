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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.deegree.commons.config.DeegreeWorkspace;
import org.junit.Before;
import org.junit.Test;

import de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager.RasterConfigurationType;
import de.latlon.xplan.manager.workspace.WorkspaceUtils;

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

    private File workspaceDirectory;

    private DeegreeWorkspace workspace;

    @Before
    public void createTestWorkspaceFrame()
                    throws Exception {
        workspaceDirectory = createTmpWorkspace().toFile();
        workspace = WorkspaceUtils.instantiateWorkspace( workspaceDirectory.getName(), workspaceDirectory );
    }

    @Test
    public void testCreateRasterConfigurationsWithGdalTilestore()
                    throws Exception {
        WorkspaceRasterLayerManager workspaceRasterLayerManager = new WorkspaceRasterLayerManager( workspaceDirectory,
                        RasterConfigurationType.gdal, "epsg:25833" );
        workspaceRasterLayerManager.createRasterConfigurations( "rasterId", "tiffFileName" );

        workspace.initManagers();
    }

    @Test
    public void testCreateRasterConfigurationsWithGeotiffTilestore()
                    throws Exception {
        WorkspaceRasterLayerManager workspaceRasterLayerManager = new WorkspaceRasterLayerManager( workspaceDirectory,
                        RasterConfigurationType.geotiff, "epsg:25833" );
        workspaceRasterLayerManager.createRasterConfigurations( "rasterId", "tiffFileName" );

        workspace.initManagers();
    }

    private Path createTmpWorkspace()
                    throws IOException {
        Path workspaceDirectory = Files.createTempDirectory( "WorkspaceRasterLayerManagerIT_" );
        Files.createDirectory( workspaceDirectory.resolve( THEMES ) );
        Files.createDirectory( workspaceDirectory.resolve( LAYERS ) );
        Files.createDirectory( workspaceDirectory.resolve( SERVICES ) );
        return workspaceDirectory;
    }

}