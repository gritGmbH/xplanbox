/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.archive;

import de.latlon.xplan.ResourceAccessor;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;

import static de.latlon.xplan.commons.XPlanAde.NSM;
import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanType.FP_Plan;
import static de.latlon.xplan.commons.XPlanType.RP_Plan;
import static de.latlon.xplan.commons.XPlanType.SO_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanArchiveCreatorTest {

	@Test
	public void testMetadataBP2070XPlan3() throws IOException {
		XPlanArchive archive = getTestArchive("xplan3/BP2070.zip");
		assertEquals(XPLAN_3, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	public void testMetadataBP2135XPlan3() throws IOException {
		XPlanArchive archive = getTestArchive("xplan3/BP2135.zip");
		assertEquals(XPLAN_3, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	public void testMetadataFPlanXPlan3() throws IOException {
		XPlanArchive archive = getTestArchive("xplan3/FPlan.zip");
		assertEquals(XPLAN_3, archive.getVersion());
		assertEquals(FP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	public void testMetadataWuerdenhainXPlan3() throws IOException {
		XPlanArchive archive = getTestArchive("xplan3/BP2070-Finkenwerder.zip");
		assertEquals(XPLAN_3, archive.getVersion());
		assertEquals("Finkenwerder", archive.getDistricts().get(0));
	}

	@Test
	public void testMetadataWuerdenhainXPlan3WithMapper() throws IOException {
		XPlanArchive archive = getTestArchiveWithMapper("xplan3/BP2070-Finkenwerder.zip");
		assertEquals(XPLAN_3, archive.getVersion());
		assertEquals("Hamburg-Mitte", archive.getDistricts().get(0));
	}

	@Test
	public void testMetadataBP2070XPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/BP2070.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	public void testMetadataBP2135XPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/BP2135.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	public void testMetadataDemoXPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/Demo.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	public void testMetadataEidelstedt_4_V4XPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/Eidelstedt_4_V4.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("urn:adv:crs:ETRS89_UTM32", archive.getCrs().getName());
	}

	@Test
	public void testMetadataFPlanXPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/FPlan.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(FP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	public void testMetadataLA22XPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/LA22.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	public void testMetadataLA67XPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/LA67.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	public void testMetadataV4_1_ID_103_41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/V4_1_ID_103.zip");

		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:25833", archive.getCrs().getName());
	}

	@Test
	public void testMetadataEidelstedt4V4EimsbuettelXPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/Eidelstedt_4_V4-Eimsbuettel.zip");

		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals("Eimsbüttel", archive.getDistricts().get(0));
	}

	@Test
	public void testMetadataEidelstedt4V4EimsbuettelXPlan41WithMapper() throws IOException {
		XPlanArchive archive = getTestArchiveWithMapper("xplan41/Eidelstedt_4_V4-Eimsbuettel.zip");

		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals("Eimsbüttel", archive.getDistricts().get(0));
	}

	@Test
	public void testMetadataV4_1_ID_66_40() throws IOException {
		XPlanArchive archive = getTestArchive("xplan40/V4_1_ID_66.zip");
		assertEquals(XPLAN_40, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:25833", archive.getCrs().getName());
	}

	@Test
	public void testMetadataV4_1_NSM_Niedersachsen_lrop_Small() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/nsm/nsm_niedersachsen_lrop_small.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(NSM, archive.getAde());
		assertEquals(RP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	public void testMetadataEidelstedt_4_V4XPlan41_With_NSM_Namespace() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/Eidelstedt_4_V4_with_nsm_namespace.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(null, archive.getAde());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("urn:adv:crs:ETRS89_UTM32", archive.getCrs().getName());
	}

	@Test
	public void testCreateXPlanArchive_41_SOPlan() throws IOException, UnknownCRSException {
		XPlanArchive archive = getTestArchive("xplan41/Erhaltung.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(null, archive.getAde());
		assertEquals(0, archive.getDistricts().size());
		assertEquals(SO_Plan, archive.getType());
		assertEquals(CRSManager.lookup("EPSG:25832"), archive.getCrs());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPlanWithWrongGmlFileNameShouldThrowIllegalArgumentException() throws Exception {
		getTestArchive("xplan41/Eidelstedt_4_V4-wrongGmlFileName.zip");
	}

	@Test
	public void testCreateXPlanArchive_51_GmlFile() throws IOException, UnknownCRSException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator(mockMapper());
		InputStream gmlAsStream = ResourceAccessor.readResourceStream("xplan51/V4_1_ID_103.gml");
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromGml("V4_1_ID_103.gml", gmlAsStream);
		assertEquals(XPLAN_51, archive.getVersion());
		assertEquals(null, archive.getAde());
		assertEquals(null, archive.getDistricts().get(0));
		assertEquals(BP_Plan, archive.getType());
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

	private XPlanArchive getTestArchiveWithMapper(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator(mockMapper());
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

	private LocalCenterToDistrictMapper mockMapper() {
		LocalCenterToDistrictMapper mock = Mockito.mock(LocalCenterToDistrictMapper.class);
		when(mock.mapToDistrict("Eimsbüttel")).thenReturn("Eimsbüttel");
		when(mock.mapToDistrict("Finkenwerder")).thenReturn("Hamburg-Mitte");
		when(mock.mapToDistrict(null)).thenReturn(null);
		return mock;
	}

}
