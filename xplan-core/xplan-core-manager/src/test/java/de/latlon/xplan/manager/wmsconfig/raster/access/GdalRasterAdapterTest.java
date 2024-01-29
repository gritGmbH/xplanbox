/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.manager.wmsconfig.raster.access;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

import static de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter.isGdalSuccessfullInitialized;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assume.assumeTrue;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GdalRasterAdapterTest {

	@Test
	public void testGetReferencedFiles() throws Exception {
		assumeTrue(isGdalSuccessfullInitialized());

		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		InputStream testPlan = getClass().getResourceAsStream("/testdata/xplan60/Blankenese29_Test_60.zip");
		XPlanArchive xPlanArchive = archiveCreator.createXPlanArchiveFromZip("Blankenese29_Test_60.zip", testPlan);

		GdalRasterAdapter gdalRasterAdapter = new GdalRasterAdapter();
		Vector<?> referencedFiles = gdalRasterAdapter.getReferencedFiles(xPlanArchive, "Blankenese29.png");

		assertThat(referencedFiles.size(), is(2));
	}

	@Test
	public void testRasterCrs_geotiff() throws Exception {
		assumeTrue(isGdalSuccessfullInitialized());

		URL resource = GdalRasterAdapterTest.class.getResource("../evaluation/dem30_geotiff_tiled.tiff");
		File file = new File(resource.toURI());

		GdalRasterAdapter gdalRasterAdapter = new GdalRasterAdapter();
		String rasterCrs = gdalRasterAdapter.getRasterCrs(file);

		assertThat(rasterCrs, containsString("\"EPSG\",\"26912\""));
	}

	@Test
	public void testRasterCrs_pngWithPgw() throws Exception {
		assumeTrue(isGdalSuccessfullInitialized());

		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		InputStream testPlan = getClass().getResourceAsStream("/testdata/xplan60/Blankenese29_Test_60.zip");
		XPlanArchive xPlanArchive = archiveCreator.createXPlanArchiveFromZip("Blankenese29_Test_60.zip", testPlan);

		GdalRasterAdapter gdalRasterAdapter = new GdalRasterAdapter();
		File file = gdalRasterAdapter.unzipArchiveInTmpDirectory(xPlanArchive);
		String rasterCrs = gdalRasterAdapter.getRasterCrs(new File(file, "Blankenese29.png"));

		assertThat(rasterCrs, is(""));
	}

	@Test
	public void testRasterCrs_tiffWithWld() throws Exception {
		assumeTrue(isGdalSuccessfullInitialized());

		URL resource = GdalRasterAdapterTest.class.getResource("../evaluation/dem30.tiff");
		File file = new File(resource.toURI());
		GdalRasterAdapter gdalRasterAdapter = new GdalRasterAdapter();
		String rasterCrs = gdalRasterAdapter.getRasterCrs(file);

		assertThat(rasterCrs, is(""));
	}

	// relates to failing test
	// de.latlon.xplan.manager.wmsconfig.raster.evaluation.XPlanRasterEvaluatorTest.testEvaluateRasterdataGdalWithPng25833
	@Ignore
	@Test
	public void testRasterCrs_pngWithAux() throws Exception {
		assumeTrue(isGdalSuccessfullInitialized());

		URL resource = GdalRasterAdapterTest.class.getResource("../evaluation/png_25833.png");
		File file = new File(resource.toURI());
		GdalRasterAdapter gdalRasterAdapter = new GdalRasterAdapter();
		String rasterCrs = gdalRasterAdapter.getRasterCrs(file);

		assertThat(rasterCrs, containsString("\"EPSG\",\"25833\""));
	}

}
