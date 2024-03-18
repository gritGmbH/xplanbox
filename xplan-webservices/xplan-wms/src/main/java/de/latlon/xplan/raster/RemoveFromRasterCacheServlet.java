package de.latlon.xplan.raster;

import org.apache.commons.io.IOUtils;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.services.config.ApiKey;
import org.deegree.services.controller.OGCFrontController;
import org.deegree.tile.TileDataLevel;
import org.deegree.tile.TileDataSet;
import org.deegree.tile.persistence.GenericTileStore;
import org.deegree.tile.persistence.TileStore;
import org.deegree.tile.persistence.TileStoreProvider;
import org.deegree.tile.persistence.gdal.GdalTileDataLevel;
import org.deegree.tile.persistence.geotiff.GeoTIFFTileDataLevel;
import org.deegree.workspace.ResourceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clear raster cache to ensure raster files can be removed on windows.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.1.2
 */
public class RemoveFromRasterCacheServlet extends HttpServlet {

	private static final Logger LOG = LoggerFactory.getLogger(RemoveFromRasterCacheServlet.class);

	private static ApiKey apiKey = new ApiKey();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			apiKey.validate(req);
		}
		catch (SecurityException e) {
			resp.setStatus(403);
			IOUtils.write("There were security concerns: " + e.getLocalizedMessage() + "\n", resp.getOutputStream());
		}
		String pathInfo = req.getPathInfo();
		if (pathInfo != null) {
			String tileStorePrefix = pathInfo.substring(1);
			LOG.info("Cleanup pool of tile store prefix {}", tileStorePrefix);
			DeegreeWorkspace workspace = OGCFrontController.getServiceWorkspace();

			List<ResourceIdentifier<TileStore>> tileStoreResources = workspace.getNewWorkspace()
				.getResourcesOfType(TileStoreProvider.class);
			List<String> matchingTileStoreIds = tileStoreResources.stream()
				.filter(tileStoreResource -> tileStoreResource.getId().startsWith(tileStorePrefix))
				.map(tileStoreResource -> tileStoreResource.getId())
				.collect(Collectors.toList());

			if (matchingTileStoreIds.isEmpty()) {
				LOG.warn("Could not find tile store with prefix {}", tileStorePrefix);
				resp.setStatus(404);
				IOUtils.write("Could not find tile store with prefix " + tileStorePrefix, resp.getOutputStream());
				return;
			}

			for (String tileStoreId : matchingTileStoreIds) {
				cleanCache(tileStoreId, workspace);
			}
		}

	}

	private void cleanCache(String tileStoreId, DeegreeWorkspace workspace) {
		GenericTileStore tileStore = (GenericTileStore) workspace.getNewWorkspace()
			.getResource(TileStoreProvider.class, tileStoreId);
		if (tileStore == null) {
			LOG.warn("Could not find tile store with id {}", tileStoreId);
			return;
		}
		TileDataSet tileDataSet = tileStore.getTileDataSet(tileStoreId);
		List<TileDataLevel> tileDataLevels = tileDataSet.getTileDataLevels();
		tileDataLevels.forEach(tileDataLevel -> {
			try {
				if (tileDataLevel instanceof GeoTIFFTileDataLevel) {
					((GeoTIFFTileDataLevel) tileDataLevel).clearPool();
				}
				else if (tileDataLevel instanceof GdalTileDataLevel) {
					((GdalTileDataLevel) tileDataLevel).clearPool();
				}
			}
			catch (IOException e) {
				LOG.error("Pool could not be cleared!", e);
			}
		});
	}

}