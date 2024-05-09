/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.manager.wmsconfig.raster.config;

import de.latlon.xplan.manager.wmsconfig.raster.RasterConfigurationType;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.commons.utils.DoublePair;
import org.deegree.layer.persistence.LayerStore;
import org.deegree.layer.persistence.LayerStoreProvider;
import org.deegree.layer.persistence.tile.TileLayer;
import org.deegree.workspace.Workspace;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;
import static java.nio.file.Files.copy;
import static java.nio.file.Files.createDirectory;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class WorkspaceRasterLayerManagerTestManual {

	private static final String SERVICES = "services";

	private static final String LAYERS = "layers";

	private static final String THEMES = "themes";

	private static final String DATA = "data";

	public static final String TIFF_FILE = "dem30_geotiff_tiled_epsg4326.tiff";

	public static final String RASTER_ID = "rasterId";

	@ClassRule
	public final static TemporaryFolder tempFolder = new TemporaryFolder();

	private static File workspaceDirectory;

	@BeforeClass
	public static void setupFakedWorkspace() throws IOException {
		workspaceDirectory = tempFolder.newFolder("xplan-wms-workspace");
		System.setProperty("DEEGREE_WORKSPACE_ROOT", workspaceDirectory.getParentFile().toString());
	}

	@Test
	public void testCreateRasterConfigurationsWithGeotiffTilestore() throws Exception {
		WorkspaceRasterLayerManager workspaceRasterLayerManager = new WorkspaceRasterLayerManager(workspaceDirectory,
				RasterConfigurationType.geotiff, "EPSG:4326");
		double minScaleDenominator = 10;
		double maxScaleDenominator = 1500;
		workspaceRasterLayerManager.createRasterConfigurations(RASTER_ID, TIFF_FILE, minScaleDenominator,
				maxScaleDenominator);

		DeegreeWorkspace workspace = instantiateWorkspace(workspaceDirectory.getName());
		Workspace newWorkspace = workspace.getNewWorkspace();
		newWorkspace.initAll();
		LayerStore layerStoreMap = newWorkspace.getResource(LayerStoreProvider.class, RASTER_ID);

		assertThat(layerStoreMap, is(notNullValue()));
		TileLayer tileLayer = (TileLayer) layerStoreMap.get(RASTER_ID);
		DoublePair scaleDenominators = tileLayer.getMetadata().getScaleDenominators();
		assertThat(scaleDenominators.first, is(minScaleDenominator));
		assertThat(scaleDenominators.second, is(maxScaleDenominator));
	}

	@Test
	public void testCreateRasterConfigurations_LayerWithDefaultScaleDenominators() throws Exception {
		WorkspaceRasterLayerManager workspaceRasterLayerManager = new WorkspaceRasterLayerManager(workspaceDirectory,
				RasterConfigurationType.geotiff, "EPSG:4326");
		workspaceRasterLayerManager.createRasterConfigurations(RASTER_ID, TIFF_FILE, Double.NaN, Double.NaN);

		DeegreeWorkspace workspace = instantiateWorkspace(workspaceDirectory.getName());
		Workspace newWorkspace = workspace.getNewWorkspace();
		newWorkspace.initAll();
		LayerStore layerStoreMap = newWorkspace.getResource(LayerStoreProvider.class, RASTER_ID);

		assertThat(layerStoreMap, is(notNullValue()));
		TileLayer tileLayer = (TileLayer) layerStoreMap.get(RASTER_ID);
		DoublePair scaleDenominators = tileLayer.getMetadata().getScaleDenominators();
		assertThat(scaleDenominators.first, is(Double.NEGATIVE_INFINITY));
		assertThat(scaleDenominators.second, is(Double.POSITIVE_INFINITY));
	}

	private Path createTmpWorkspace() throws IOException {
		Path workspaceDirectory = Files.createTempDirectory("WorkspaceRasterLayerManagerIT_");
		createDirectory(workspaceDirectory.resolve(THEMES));
		createDirectory(workspaceDirectory.resolve(LAYERS));
		createDirectory(workspaceDirectory.resolve(SERVICES));
		Path dataPath = createDirectory(workspaceDirectory.resolve(DATA));
		copyFile(TIFF_FILE, dataPath);
		return workspaceDirectory;
	}

	private void copyFile(String resourceName, Path dataPath) throws IOException {
		InputStream resourceAsStream = getClass().getResourceAsStream("../evaluation/" + resourceName);
		Path targetFile = dataPath.resolve(resourceName);
		copy(resourceAsStream, targetFile);
	}

}
