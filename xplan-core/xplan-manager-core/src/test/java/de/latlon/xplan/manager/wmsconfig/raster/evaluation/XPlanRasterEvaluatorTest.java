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

import static de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter.isGdalSuccessfullInitialized;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanRasterEvaluatorTest {

	private static final String CONFIGURED_CRS = "epsg:4326";

	private static final String REF = "reference/XPlanRasterManagerTest";

	private static final String TIFF_EPSG4269_NAME = "XPlanRasterManagerTest_epsg4269.tiff";

	private static final String TIFF_EPSG4326_NAME = "XPlanRasterManagerTest_epsg4326.tiff";

	private static final String TIFF_NO_CRS_NAME = "XPlanRasterManagerTest_noCrs.tiff";

	private static final String PNG_NO_CRS_NAME = "XPlanRasterManagerTest_noCrs.png";

	private static final String PNG_EPSG25833_NAME = "XPlanRasterManagerTest_epsg25833.png";

	private static final String PNG_EPSG25833_AUX_NAME = "XPlanRasterManagerTest_epsg25833.png.aux.xml";

	private static final String TXT_NAME = "XPlanRasterManagerTest.txt";

	@Test
	public void testEvaluateRasterdataGdalWithTiffEpsg4269() throws Exception {
		assumeTrue(isGdalSuccessfullInitialized());

		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(new GdalRasterEvaluation(CONFIGURED_CRS));
		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(mockArchiveWithTiffEpsg4269(),
				mockFeatureCollection());
		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(TIFF_EPSG4269_NAME));
		assertThat(result.isCrsSet(), is(true));
		assertThat(result.isConfiguredCrs(), is(false));
		assertThat(result.isSupportedImageFormat(), is(true));
	}

	@Test
	public void testEvaluateRasterdataGdalWithTiffEpsg4326() throws Exception {
		assumeTrue(isGdalSuccessfullInitialized());

		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(new GdalRasterEvaluation(CONFIGURED_CRS));
		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(mockArchiveWithTiffEpsg4326(),
				mockFeatureCollection());
		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(TIFF_EPSG4326_NAME));
		assertThat(result.isCrsSet(), is(true));
		assertThat(result.isConfiguredCrs(), is(true));
		assertThat(result.isSupportedImageFormat(), is(true));
	}

	@Test
	public void testEvaluateRasterdataGdalWithTiffNoCrs() throws Exception {
		assumeTrue(isGdalSuccessfullInitialized());

		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(new GdalRasterEvaluation(CONFIGURED_CRS));
		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(mockArchiveWithTiffNoCrs(),
				mockFeatureCollection());
		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(TIFF_NO_CRS_NAME));
		assertThat(result.isCrsSet(), is(false));
		assertThat(result.isConfiguredCrs(), is(false));
		assertThat(result.isSupportedImageFormat(), is(true));
	}

	@Test
	public void testEvaluateRasterdataGdalWithTxt() throws Exception {
		assumeTrue(isGdalSuccessfullInitialized());

		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(new GdalRasterEvaluation("EPSG:25832"));
		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(mockArchiveWithTxt(),
				mockFeatureCollection());
		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(TXT_NAME));
		assertThat(result.isCrsSet(), is(false));
		assertThat(result.isConfiguredCrs(), is(false));
		assertThat(result.isSupportedImageFormat(), is(false));
	}

	@Ignore
	@Test
	public void testEvaluateRasterdataGdalWithPng25833() throws Exception {
		assumeTrue(isGdalSuccessfullInitialized());

		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(new GdalRasterEvaluation(CONFIGURED_CRS));
		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(mockArchiveWithPngEpsg25833(),
				mockFeatureCollection());
		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(PNG_EPSG25833_NAME));
		assertThat(result.isCrsSet(), is(true));
		assertThat(result.isConfiguredCrs(), is(false));
		assertThat(result.isSupportedImageFormat(), is(true));
	}

	@Test
	public void testEvaluateRasterdataGeotiffWithTiff() throws Exception {
		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(new GeotiffRasterEvaluation(CONFIGURED_CRS));

		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(mockArchiveWithTiffNoCrs(),
				mockFeatureCollection());

		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(TIFF_NO_CRS_NAME));
		assertThat(result.isCrsSet(), is(false));
		assertThat(result.isConfiguredCrs(), is(true));
		assertThat(result.isSupportedImageFormat(), is(true));
	}

	@Test
	public void testEvaluateRasterdataGeotiffWithPng() throws Exception {
		XPlanRasterEvaluator xPlanRasterManager = new XPlanRasterEvaluator(new GeotiffRasterEvaluation(CONFIGURED_CRS));

		List<RasterEvaluationResult> results = xPlanRasterManager.evaluateRasterdata(mockArchiveWithPngNoCrs(),
				mockFeatureCollection());

		RasterEvaluationResult result = results.get(0);

		assertThat(result.getRasterName(), is(PNG_NO_CRS_NAME));
		assertThat(result.isCrsSet(), is(false));
		assertThat(result.isConfiguredCrs(), is(true));
		assertThat(result.isSupportedImageFormat(), is(false));
	}

	private XPlanArchive mockArchiveWithTiffEpsg4269() {
		return mockArchive(TIFF_EPSG4269_NAME, "dem30_geotiff_tiled.tiff");
	}

	private XPlanArchive mockArchiveWithTiffNoCrs() {
		return mockArchive(TIFF_NO_CRS_NAME, "dem30.tiff");
	}

	private XPlanArchive mockArchiveWithPngNoCrs() {
		return mockArchive(PNG_NO_CRS_NAME, "png_nocrs.png");
	}

	private XPlanArchive mockArchiveWithTiffEpsg4326() {
		return mockArchive(TIFF_EPSG4326_NAME, "dem30_geotiff_tiled_epsg4326.tiff");
	}

	private XPlanArchive mockArchiveWithTxt() {
		return mockArchive(TXT_NAME, "test.txt");
	}

	private XPlanArchive mockArchive(String entryName, String resourceName) {
		XPlanArchive mockedArchive = mock(XPlanArchive.class);
		ZipEntryWithContent mockedEntry = mockZipEntry(mockedArchive, entryName, resourceName);
		when(mockedArchive.getEntry(REF)).thenReturn(mockedEntry);

		List<ArchiveEntry> zipFileEntries = Collections.singletonList(mockedEntry);
		doReturn(zipFileEntries).when(mockedArchive).getZipFileEntries();
		return mockedArchive;
	}

	private XPlanArchive mockArchiveWithPngEpsg25833() {
		XPlanArchive mockedArchive = mock(XPlanArchive.class);

		ZipEntryWithContent mockedPngEntry = mockZipEntry(mockedArchive, PNG_EPSG25833_NAME, "png_25833.png");
		ZipEntryWithContent mockedAuxEntry = mockZipEntry(mockedArchive, PNG_EPSG25833_AUX_NAME,
				"png_25833.png.aux.xml");

		when(mockedArchive.getEntry(REF)).thenReturn(mockedPngEntry);

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

	private XPlanFeatureCollection mockFeatureCollection() {
		XPlanFeatureCollection mockedFeatureCollection = mock(XPlanFeatureCollection.class);
		ExternalReferenceInfo mockedExternalReferenceInfo = mock(ExternalReferenceInfo.class);

		ExternalReference mockedExternalReference = mock(ExternalReference.class);
		when(mockedExternalReference.getReferenzUrl()).thenReturn(REF);

		List<ExternalReference> externalReferences = Collections.singletonList(mockedExternalReference);
		when(mockedExternalReferenceInfo.getRasterPlanBaseScans()).thenReturn(externalReferences);
		when(mockedExternalReferenceInfo.getRasterPlanBaseAndUpdateScans()).thenReturn(externalReferences);

		when(mockedFeatureCollection.getExternalReferenceInfo()).thenReturn(mockedExternalReferenceInfo);
		return mockedFeatureCollection;
	}

}
