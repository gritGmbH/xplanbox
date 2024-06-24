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
package de.latlon.xplan.manager.wmsconfig.raster.evaluation;

import de.latlon.xplan.commons.archive.ArchiveEntry;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.ZipEntryWithContent;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanRasterEvaluatorTest {

	private static final String CONFIGURED_CRS = "epsg:4326";

	private static final String TIFF_EPSG4269_NAME = "XPlanRasterManagerTest_epsg4269.tiff";

	private static final String TIFF_EPSG4326_NAME = "XPlanRasterManagerTest_epsg4326.tiff";

	private static final String TIFF_NO_CRS_NAME = "XPlanRasterManagerTest_noCrs.tiff";

	private static final String PNG_NO_CRS_NAME = "XPlanRasterManagerTest_noCrs.png";

	private static final String PNG_EPSG25833_NAME = "XPlanRasterManagerTest_epsg25833.png";

	private static final String PNG_EPSG25833_AUX_NAME = "XPlanRasterManagerTest_epsg25833.png.aux.xml";

	private static final String TXT_NAME = "XPlanRasterManagerTest.txt";

	@Ignore("media type detected for dem30_geotiff_tiled.tiff by Apache Tika is application/octet-stream, which is not supported in XPlanArchives")
	@Test
	public void testEvaluateRasterdataMapserverWithTiffEpsg4269() throws Exception {
		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(
				new MapserverRasterEvaluation(CONFIGURED_CRS));
		XPlanArchive xPlanArchive = mockArchive(TIFF_EPSG4269_NAME, "dem30_geotiff_tiled.tiff");
		XPlanFeatureCollection planFeatureCollection = mockFeatureCollection(TIFF_EPSG4269_NAME);
		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(xPlanArchive,
				planFeatureCollection);
		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(TIFF_EPSG4269_NAME));
		assertThat(result.isCrsSet(), is(true));
		assertThat(result.isConfiguredCrs(), is(false));
		assertThat(result.isSupportedImageFormat(), is(true));
	}

	@Test
	public void testEvaluateRasterdataMapserverWithTiffEpsg4326() throws Exception {
		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(
				new MapserverRasterEvaluation(CONFIGURED_CRS));
		XPlanArchive xPlanArchive = mockArchive(TIFF_EPSG4326_NAME, "dem30_geotiff_tiled_epsg4326.tiff");
		XPlanFeatureCollection planFeatureCollection = mockFeatureCollection(TIFF_EPSG4326_NAME);
		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(xPlanArchive,
				planFeatureCollection);
		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(TIFF_EPSG4326_NAME));
		assertThat(result.isCrsSet(), is(true));
		assertThat(result.isConfiguredCrs(), is(true));
		assertThat(result.isSupportedImageFormat(), is(true));
	}

	@Test
	public void testEvaluateRasterdataMapserverWithTiffNoCrs() throws Exception {
		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(
				new MapserverRasterEvaluation(CONFIGURED_CRS));
		XPlanArchive xPlanArchive = mockArchive(TIFF_NO_CRS_NAME, "dem30.tiff");
		XPlanFeatureCollection planFeatureCollection = mockFeatureCollection(TIFF_NO_CRS_NAME);
		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(xPlanArchive,
				planFeatureCollection);
		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(TIFF_NO_CRS_NAME));
		assertThat(result.isCrsSet(), is(false));
		assertThat(result.isConfiguredCrs(), is(false));
		assertThat(result.isSupportedImageFormat(), is(true));
	}

	@Test
	public void testEvaluateRasterdataMapserverWithTxt() throws Exception {
		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(new MapserverRasterEvaluation("EPSG:25832"));
		XPlanArchive xPlanArchive = mockArchive(TXT_NAME, "test.txt");
		XPlanFeatureCollection planFeatureCollection = mockFeatureCollection(TXT_NAME);
		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(xPlanArchive,
				planFeatureCollection);
		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(TXT_NAME));
		assertThat(result.isCrsSet(), is(false));
		assertThat(result.isConfiguredCrs(), is(false));
		assertThat(result.isSupportedImageFormat(), is(false));
	}

	@Test
	public void testEvaluateRasterdataMapserverWithPng25833() throws Exception {
		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(
				new MapserverRasterEvaluation(CONFIGURED_CRS));
		XPlanArchive archive = mockArchiveWithPngEpsg25833(PNG_EPSG25833_NAME, PNG_EPSG25833_AUX_NAME);
		XPlanFeatureCollection planFeatureCollection = mockFeatureCollection(PNG_EPSG25833_NAME,
				PNG_EPSG25833_AUX_NAME);
		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(archive, planFeatureCollection);
		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(PNG_EPSG25833_NAME));
		assertThat(result.isCrsSet(), is(true));
		assertThat(result.isConfiguredCrs(), is(false));
		assertThat(result.isSupportedImageFormat(), is(true));
	}

	@Test
	public void testEvaluateRasterdataGeotiffWithTiff() throws Exception {
		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(new GeotiffRasterEvaluation(CONFIGURED_CRS));
		XPlanArchive xPlanArchive = mockArchive(TIFF_NO_CRS_NAME, "dem30.tiff");
		XPlanFeatureCollection planFeatureCollection = mockFeatureCollection(TIFF_NO_CRS_NAME);
		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(xPlanArchive,
				planFeatureCollection);

		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(TIFF_NO_CRS_NAME));
		assertThat(result.isCrsSet(), is(false));
		assertThat(result.isConfiguredCrs(), is(true));
		assertThat(result.isSupportedImageFormat(), is(true));
	}

	@Test
	public void testEvaluateRasterdataGeotiffWithPng() throws Exception {
		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(new GeotiffRasterEvaluation(CONFIGURED_CRS));
		XPlanArchive xPlanArchive = mockArchive(PNG_NO_CRS_NAME, "png_nocrs.png");
		XPlanFeatureCollection planFeatureCollection = mockFeatureCollection(PNG_NO_CRS_NAME);
		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(xPlanArchive,
				planFeatureCollection);

		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(PNG_NO_CRS_NAME));
		assertThat(result.isCrsSet(), is(false));
		assertThat(result.isConfiguredCrs(), is(true));
		assertThat(result.isSupportedImageFormat(), is(false));
	}

	private XPlanArchive mockArchive(String entryName, String resourceName) {
		XPlanArchive mockedArchive = mock(XPlanArchive.class);
		ZipEntryWithContent mockedEntry = mockZipEntry(mockedArchive, entryName, resourceName);
		when(mockedArchive.getEntry(entryName)).thenReturn(mockedEntry);
		when(mockedArchive.retrieveInputStreamFor(entryName))
			.thenReturn(XPlanRasterEvaluatorTest.class.getResourceAsStream(resourceName))
			.thenReturn(XPlanRasterEvaluatorTest.class.getResourceAsStream(resourceName));

		List<ArchiveEntry> zipFileEntries = Collections.singletonList(mockedEntry);
		doReturn(zipFileEntries).when(mockedArchive).getZipFileEntries();
		return mockedArchive;
	}

	private XPlanArchive mockArchiveWithPngEpsg25833(String entryNamePng, String entryNameAux) {
		XPlanArchive mockedArchive = mock(XPlanArchive.class);

		String resourceNamePng = "png_25833.png";
		String resourceNameAux = "png_25833.png.aux.xml";
		ZipEntryWithContent mockedPngEntry = mockZipEntry(mockedArchive, entryNamePng, resourceNamePng);
		ZipEntryWithContent mockedAuxEntry = mockZipEntry(mockedArchive, entryNameAux, resourceNameAux);

		when(mockedArchive.getEntry(entryNamePng)).thenReturn(mockedPngEntry);
		when(mockedArchive.retrieveInputStreamFor(entryNamePng))
			.thenReturn(XPlanRasterEvaluatorTest.class.getResourceAsStream(resourceNamePng))
			.thenReturn(XPlanRasterEvaluatorTest.class.getResourceAsStream(resourceNamePng));

		when(mockedArchive.getEntry(entryNameAux)).thenReturn(mockedPngEntry);
		when(mockedArchive.retrieveInputStreamFor(entryNameAux))
			.thenReturn(XPlanRasterEvaluatorTest.class.getResourceAsStream(resourceNameAux))
			.thenReturn(XPlanRasterEvaluatorTest.class.getResourceAsStream(resourceNameAux));

		List<ArchiveEntry> zipFileEntries = Arrays.asList(mockedPngEntry, mockedAuxEntry);
		doReturn(zipFileEntries).when(mockedArchive).getZipFileEntries();
		return mockedArchive;
	}

	private ZipEntryWithContent mockZipEntry(XPlanArchive mockedArchive, String name, String resource) {
		ZipEntryWithContent mockedEntry = mock(ZipEntryWithContent.class);
		when(mockedEntry.getName()).thenReturn(name);
		InputStream resourceStream = XPlanRasterEvaluatorTest.class.getResourceAsStream(resource);
		when(mockedArchive.retrieveInputStreamFor(name)).thenReturn(resourceStream);
		return mockedEntry;
	}

	private XPlanFeatureCollection mockFeatureCollection(String referenzUrl) {
		return mockFeatureCollection(referenzUrl, null);
	}

	private XPlanFeatureCollection mockFeatureCollection(String referenzUrl, String georeferenzUrl) {
		XPlanFeatureCollection mockedFeatureCollection = mock(XPlanFeatureCollection.class);
		ExternalReferenceInfo mockedExternalReferenceInfo = mock(ExternalReferenceInfo.class);

		ExternalReference mockedExternalReference = mock(ExternalReference.class);
		when(mockedExternalReference.getReferenzUrl()).thenReturn(referenzUrl);
		when(mockedExternalReference.getGeoRefUrl()).thenReturn(georeferenzUrl);

		List<ExternalReference> externalReferences = Collections.singletonList(mockedExternalReference);
		when(mockedExternalReferenceInfo.getRasterPlanBaseScans()).thenReturn(externalReferences);
		when(mockedExternalReferenceInfo.getRasterPlanBaseAndUpdateScans()).thenReturn(externalReferences);

		when(mockedFeatureCollection.getExternalReferenceInfo()).thenReturn(mockedExternalReferenceInfo);
		return mockedFeatureCollection;
	}

}
