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
package de.latlon.xplan.manager.wmsconfig.config;

import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.storage.filesystem.DeegreeRasterCacheCleaner;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter;
import de.latlon.xplan.manager.wmsconfig.raster.config.DeegreeRasterConfigManager;
import de.latlon.xplan.manager.wmsconfig.raster.config.NoConfigRasterConfigManager;
import de.latlon.xplan.manager.wmsconfig.raster.config.RasterConfigManager;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.GdalRasterEvaluation;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.GeotiffRasterEvaluation;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.RasterEvaluation;
import de.latlon.xplan.manager.wmsconfig.raster.storage.FileSystemStorage;
import de.latlon.xplan.manager.wmsconfig.raster.storage.GdalRasterStorage;
import de.latlon.xplan.manager.wmsconfig.raster.storage.RasterStorage;
import de.latlon.xplan.manager.wmsconfig.raster.storage.s3.config.AmazonS3RasterStorageContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

/**
 * Spring configuration for the storage type to be used for raster data. The storage type
 * is selected by evaluating the ManagerConfiguration property rasterConfigurationType.
 * Supported are geotiff, gdal and mapserver using a local file system. To use a AWS S3
 * bucket as a storage backend with MapServer use the <code>AmazonS3Context</code> for
 * configuration.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 * @since 7.0
 * @see AmazonS3RasterStorageContext
 */
@Configuration
@ComponentScan("de.latlon.xplan.manager.storage.filesystem.listener")
public class RasterStorageContext {

	@Bean
	public RasterStorage rasterStorage(ManagerConfiguration managerConfiguration,
			WmsWorkspaceWrapper wmsWorkspaceWrapper, RasterEvaluation rasterEvaluation,
			DeegreeRasterCacheCleaner deegreeRasterCacheCleaner) {
		final Path dataDirectory = wmsWorkspaceWrapper.getDataDirectory();
		switch (managerConfiguration.getRasterConfigurationType()) {
			case gdal:
			case mapserver:
				return new GdalRasterStorage(dataDirectory, rasterEvaluation, new GdalRasterAdapter(),
						deegreeRasterCacheCleaner);
			default:
				return new FileSystemStorage(dataDirectory, rasterEvaluation, deegreeRasterCacheCleaner);
		}
	}

	@Bean
	public RasterEvaluation rasterEvaluation(ManagerConfiguration managerConfiguration) {
		switch (managerConfiguration.getRasterConfigurationType()) {
			case gdal:
			case mapserver:
				return new GdalRasterEvaluation(managerConfiguration.getRasterConfigurationCrs());
			default:
				return new GeotiffRasterEvaluation(managerConfiguration.getRasterConfigurationCrs());
		}
	}

	@Bean
	public RasterConfigManager rasterConfigManager(WmsWorkspaceWrapper wmsWorkspaceWrapper,
			ManagerConfiguration managerConfiguration) {
		switch (managerConfiguration.getRasterConfigurationType()) {
			case mapserver:
				return new NoConfigRasterConfigManager();
			case gdal:
			case geotiff:
			default:
				return new DeegreeRasterConfigManager(wmsWorkspaceWrapper, managerConfiguration);
		}
	}

}
