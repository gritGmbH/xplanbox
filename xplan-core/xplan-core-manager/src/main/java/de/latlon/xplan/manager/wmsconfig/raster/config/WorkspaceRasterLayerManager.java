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

import de.latlon.xplan.manager.wmsconfig.raster.RasterConfigurationSource;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.deegree.commons.metadata.description.jaxb.LanguageStringType;
import org.deegree.layer.persistence.base.jaxb.ScaleDenominatorsType;
import org.deegree.layer.persistence.tile.jaxb.TileLayerType;
import org.deegree.layer.persistence.tile.jaxb.TileLayers;
import org.deegree.tile.persistence.geotiff.jaxb.GeoTIFFTileStoreJAXB;
import org.deegree.tile.tilematrixset.geotiff.jaxb.GeoTIFFTileMatrixSetConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static java.lang.Boolean.TRUE;

/**
 * Creates/deletes configuration and data files for raster layers in the WMS workspace.
 *
 * @author <a href="mailto:schmitz@occamlabs.de">Andreas Schmitz</a>
 * @since 1.0
 */
public class WorkspaceRasterLayerManager {

	private static final Logger LOG = LoggerFactory.getLogger(WorkspaceRasterLayerManager.class);

	private final Path wmsWorkspace;

	private final RasterConfigurationSource tileStoreType;

	private final String rasterConfigurationCrs;

	/**
	 * @param wmsWorkspace the location of the workspace the configuration should be
	 * stored, never <code>null</code>
	 * @param tileStoreType the type of the tile store to create, never <code>null</code>
	 * @param rasterConfigurationCrs the crs of the configuration, never <code>null</code>
	 */
	public WorkspaceRasterLayerManager(File wmsWorkspace, RasterConfigurationSource tileStoreType,
			String rasterConfigurationCrs) {
		this.wmsWorkspace = wmsWorkspace.toPath();
		this.tileStoreType = tileStoreType;
		this.rasterConfigurationCrs = rasterConfigurationCrs;
	}

	public void createRasterConfigurations(String rasterId, String rasterFileName, double minScaleDenominator,
			double maxScaleDenominator) throws JAXBException, IOException {
		switch (tileStoreType) {
			case geotiff:
				createGeotiffConfiguration(rasterId, rasterFileName, minScaleDenominator, maxScaleDenominator);
				break;
		}
	}

	/**
	 * Removes all configuration files for the specified plan:
	 * <ul>
	 * <li>datasources/tile/&lt;planId&gt;_&lt;XXX&gt;</li>
	 * <li>datasources/tile/tilematrixset/&lt;planId&gt;_&lt;XXX&gt;</li>
	 * <li>layers/&lt;planId&gt;_&lt;XXX&gt;</li>
	 * </ul>
	 * @param planId the id of the plan to remove configuration for, never
	 * <code>null</code>
	 */
	public void deleteDataFilesAndRasterConfigurations(int planId) throws IOException {
		final String prefix = planId + "_";
		deleteDataFilesAndRasterConfigurations(
				(path, basicFileAttributes) -> path.getFileName().toString().startsWith(prefix));
	}

	/**
	 * Removes the configuration files of the specified rasterId for the specified plan:
	 * <ul>
	 * <li>datasources/tile/&lt;planId&gt;_&lt;rasterId&gt;.xml</li>
	 * <li>datasources/tile/tilematrixset/&lt;planId&gt;_&lt;rasterId&gt;.xml</li>
	 * <li>layers/&lt;planId&gt;_&lt;rasterId&gt;.xml</li>
	 * </ul>
	 * @param planId the id of the plan to remove configuration for, never
	 * <code>null</code>
	 * @param rasterId id of the raster to remove, never <code>null</code>
	 */
	public void deleteDataFilesAndRasterConfigurations(int planId, String rasterId) throws IOException {
		final String rasterLayerFileName = planId + "_" + rasterId;
		deleteDataFilesAndRasterConfigurations(
				(path, basicFileAttributes) -> path.getFileName().toString().startsWith(rasterLayerFileName));
	}

	private void deleteDataFilesAndRasterConfigurations(BiPredicate<Path, BasicFileAttributes> xmlFilenameFilter)
			throws IOException {
		deleteFilesWithPrefix(wmsWorkspace.resolve("datasources/tile"), xmlFilenameFilter);
		deleteFilesWithPrefix(wmsWorkspace.resolve("datasources/tile/tilematrixset"), xmlFilenameFilter);
		deleteFilesWithPrefix(wmsWorkspace.resolve("layers"), xmlFilenameFilter);
	}

	private void deleteFilesWithPrefix(Path dir, BiPredicate<Path, BasicFileAttributes> filenameFilter)
			throws IOException {
		if (!Files.exists(dir) || !Files.isDirectory(dir)) {
			return;
		}
		Stream<Path> filesToDelete = Files.find(dir, Integer.MAX_VALUE, filenameFilter);
		filesToDelete.forEach(file -> {
			LOG.info("- Entferne Workspace-Datei '" + file + "'...");
			try {
				Files.delete(file);
				LOG.info("OK");
			}
			catch (Exception e) {
				LOG.error("Fehler: " + e.getMessage());
				LOG.debug("Fehler: ", e);
			}
		});
	}

	private void createGeotiffConfiguration(String rasterId, String rasterFileName, double minScaleDenominator,
			double maxScaleDenominator) throws JAXBException, IOException {
		createGeotiffTileMatrixSetConfig(rasterId, rasterFileName);
		createGeotiffTileStoreConfig(rasterId, rasterFileName);
		createTileLayerConfig(rasterId, minScaleDenominator, maxScaleDenominator);
	}

	private void createTileLayerConfig(String rasterId, double minScaleDenominator, double maxScaleDenominator)
			throws JAXBException, IOException {
		TileLayers cfg = new TileLayers();
		TileLayerType lay = new TileLayerType();
		cfg.getTileLayer().add(lay);
		lay.setName(rasterId);
		lay.setCRS(rasterConfigurationCrs);
		setScaleDenominator(lay, minScaleDenominator, maxScaleDenominator);
		LanguageStringType title = new LanguageStringType();
		title.setValue(rasterId);
		lay.getTitle().add(title);
		org.deegree.layer.persistence.tile.jaxb.TileLayerType.TileDataSet tds;
		tds = new org.deegree.layer.persistence.tile.jaxb.TileLayerType.TileDataSet();
		lay.getTileDataSet().add(tds);
		tds.setTileStoreId(rasterId);
		tds.setValue(rasterId);
		Path layerDir = wmsWorkspace.resolve("layers");
		createDirectory(layerDir);
		Path layerFile = layerDir.resolve(rasterId + ".xml");
		marshallConfig(cfg, "org.deegree.layer.persistence.tile.jaxb", layerFile);
	}

	private void setScaleDenominator(TileLayerType lay, double minScaleDenominator, double maxScaleDenominator) {
		if (Double.isNaN(minScaleDenominator) && Double.isNaN(maxScaleDenominator))
			return;
		double minScaleDenominatorToSet = Double.isNaN(minScaleDenominator) ? 0 : minScaleDenominator;
		double maxScaleDenominatorToSet = Double.isNaN(maxScaleDenominator) ? Double.MAX_VALUE : maxScaleDenominator;
		ScaleDenominatorsType scaleDenominators = new ScaleDenominatorsType();
		scaleDenominators.setMin(minScaleDenominatorToSet);
		scaleDenominators.setMax(maxScaleDenominatorToSet);
		lay.setScaleDenominators(scaleDenominators);
	}

	private void createGeotiffTileMatrixSetConfig(String rasterId, String rasterFileName)
			throws JAXBException, IOException {
		GeoTIFFTileMatrixSetConfig cfg = new GeoTIFFTileMatrixSetConfig();
		cfg.setStorageCRS(rasterConfigurationCrs);
		cfg.setFile("../../../data/" + rasterFileName);
		Path tilematrixsetDir = wmsWorkspace.resolve("datasources/tile/tilematrixset/");
		createDirectory(tilematrixsetDir);
		Path tilematrixsetFile = tilematrixsetDir.resolve(rasterId + ".xml");
		marshallConfig(cfg, "org.deegree.tile.tilematrixset.geotiff.jaxb", tilematrixsetFile);
	}

	private void createGeotiffTileStoreConfig(String rasterId, String rasterFileName) throws JAXBException {
		GeoTIFFTileStoreJAXB cfg = new GeoTIFFTileStoreJAXB();
		GeoTIFFTileStoreJAXB.TileDataSet tds = new GeoTIFFTileStoreJAXB.TileDataSet();
		tds.setIdentifier(rasterId);
		tds.setFile("../../data/" + rasterFileName);
		tds.setTileMatrixSetId(rasterId);
		cfg.getTileDataSet().add(tds);
		Path file = wmsWorkspace.resolve("datasources/tile/" + rasterId + ".xml");
		marshallConfig(cfg, "org.deegree.tile.persistence.geotiff.jaxb", file);
	}

	private void createDirectory(Path directoryToCreate) throws IOException {
		if (!Files.exists(directoryToCreate))
			Files.createDirectories(directoryToCreate);
	}

	private void marshallConfig(Object cfg, String contextPath, Path toWriteIn) throws JAXBException {
		JAXBContext ctx = JAXBContext.newInstance(contextPath);
		Marshaller marshaller = ctx.createMarshaller();
		marshaller.setProperty(JAXB_FORMATTED_OUTPUT, TRUE);
		marshaller.marshal(cfg, toWriteIn.toFile());
	}

}
