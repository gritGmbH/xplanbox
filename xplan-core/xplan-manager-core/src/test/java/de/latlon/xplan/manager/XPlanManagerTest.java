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
package de.latlon.xplan.manager;

import static de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager.RasterConfigurationType.gdal;
import static de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager.isGdalSuccessfullInitialized;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.web.shared.LegislationStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
@Ignore("Fix me: xplan-manager-workspace required")
public class XPlanManagerTest {

    private static File workspaceDirectory;

    @BeforeClass
    public static void createTestWorkspace()
                    throws IOException {
        workspaceDirectory = copyManagerWorkspace();
    }

    @AfterClass
    public static void deleteTestWorkspace()
                    throws IOException {
        workspaceDirectory.delete();
    }

    private static File copyManagerWorkspace()
                    throws IOException {
        // InputStream managerWorkspaceDatasources = XPlanManagerTest.class.getResourceAsStream(
        // "/xplan-manager-workspace" );
        // TODO
        return null;
    }

    @Test
    public void testEvaluateRasterdata()
                    throws Exception {
        assumeTrue( isGdalSuccessfullInitialized() );

        XPlanManager xPlanManager = createXPlanManager();
        String pathToArchive = copyPlan();

        List<RasterEvaluationResult> results = xPlanManager.evaluateRasterdata( pathToArchive );

        assertThat( results.size(), is( 1 ) );
        RasterEvaluationResult result = results.get( 0 );

        assertThat( result.isCrsSet(), is( false ) );
        assertThat( result.isConfiguredCrs(), is( false ) );
    }

    @Test
    public void testDetermineLegislationStatus()
                    throws Exception {
        XPlanManager xPlanManager = createXPlanManager();
        String pathToArchive = copyPlan();

        LegislationStatus legislationStatus = xPlanManager.determineLegislationStatus( pathToArchive );

        assertThat( legislationStatus.getCodeNumber(), is( 4000 ) );
    }

    private XPlanManager createXPlanManager()
                    throws Exception {
        CategoryMapper categoryMapper = mock( CategoryMapper.class );
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        ManagerConfiguration managerConfiguration = mockManagerConfig();
        return new XPlanManager( categoryMapper, archiveCreator, managerConfiguration,
                        workspaceDirectory.getAbsoluteFile(), null, null, null, null );
    }

    private ManagerConfiguration mockManagerConfig() {
        ManagerConfiguration mockedConfiguration = mock( ManagerConfiguration.class );
        when( mockedConfiguration.getRasterConfigurationType() ).thenReturn( gdal );
        when( mockedConfiguration.getRasterConfigurationCrs() ).thenReturn( "epsg:4326" );
        return mockedConfiguration;
    }

    private String copyPlan()
                    throws IOException {
        InputStream resource = ResourceAccessor.readResourceStream( "xplan41/V4_1_ID_103.zip" );
        FileOutputStream output = null;
        try {
            File resourceFile = File.createTempFile( "XPlanManagerTest_", ".zip" );
            output = new FileOutputStream( resourceFile );
            return resourceFile.getAbsolutePath();
        } finally {
            IOUtils.copy( resource, output );
            IOUtils.closeQuietly( resource );
        }
    }

}