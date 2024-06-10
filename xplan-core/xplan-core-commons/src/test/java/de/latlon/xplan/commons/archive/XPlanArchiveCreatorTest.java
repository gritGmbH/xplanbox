/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.archive;

import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanType.FP_Plan;
import static de.latlon.xplan.commons.XPlanType.SO_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
class XPlanArchiveCreatorTest {

	@Test
	void testMetadataBP2070XPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/BP2070.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	void testMetadataBP2135XPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/BP2135.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	void testMetadataDemoXPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/Demo.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	void testMetadataEidelstedt_4_V4XPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/Eidelstedt_4_V4.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("urn:adv:crs:ETRS89_UTM32", archive.getCrs().getName());
	}

	@Test
	void testMetadataFPlanXPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/FPlan.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(FP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	void testMetadataLA22XPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/LA22.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	void testMetadataLA67XPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/LA67.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:31467", archive.getCrs().getName());
	}

	@Test
	void testMetadataBPlan001_41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/BPlan001_4-1.zip");

		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:25832", archive.getCrs().getName());
	}

	@Test
	void testMetadataEidelstedt4V4EimsbuettelXPlan41() throws IOException {
		XPlanArchive archive = getTestArchive("xplan41/Eidelstedt_4_V4-Eimsbuettel.zip");

		assertEquals(XPLAN_41, archive.getVersion());
	}

	@Test
	void testMetadataEidelstedt4V4EimsbuettelXPlan41WithMapper() throws IOException {
		XPlanArchive archive = getTestArchiveWithMapper("xplan41/Eidelstedt_4_V4-Eimsbuettel.zip");

		assertEquals(XPLAN_41, archive.getVersion());
	}

	@Test
	void testMetadataBPlan004_40() throws IOException {
		XPlanArchive archive = getTestArchive("xplan40/BPlan004_4-0.zip");
		assertEquals(XPLAN_40, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertEquals("EPSG:25832", archive.getCrs().getName());
	}

	@Test
	void testCreateXPlanArchive_41_SOPlan() throws IOException, UnknownCRSException {
		XPlanArchive archive = getTestArchive("xplan41/Erhaltung.zip");
		assertEquals(XPLAN_41, archive.getVersion());
		assertEquals(SO_Plan, archive.getType());
		assertEquals(CRSManager.lookup("EPSG:25832"), archive.getCrs());
	}

	@Test
	void testPlanWithWrongGmlFileNameShouldThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class,
				() -> getTestArchive("xplan41/Eidelstedt_4_V4-wrongGmlFileName.zip"));
	}

	@Test
	void testCreateXPlanArchive_51_GmlFile() throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		InputStream gmlAsStream = getClass().getResourceAsStream("/testdata/xplan51/BPlan001_5-1.gml");
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromGml("BPlan001_5-1.gml", gmlAsStream);
		assertEquals(XPLAN_51, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
	}

	@Test
	void testCreateXPlanArchive_withVerbundenerPlan() throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		InputStream gmlAsStream = XPlanArchiveCreatorTest.class
			.getResourceAsStream("../feature/xplan-multipleInstances-withVerbundenerPlan.gml");
		XPlanArchive archive = archiveCreator
			.createXPlanArchiveFromGml("xplan-multipleInstances-withVerbundenerPlan.gml", gmlAsStream);
		assertEquals(XPLAN_52, archive.getVersion());
		assertEquals(BP_Plan, archive.getType());
		assertTrue(archive.hasVerbundenerPlanBereich());
	}

	@Test
	void testCreateXPlanArchive_WfsCollection() throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		InputStream gmlAsStream = XPlanArchiveCreatorTest.class
			.getResourceAsStream("V4_1_ID_103-asWfsFeatureCollection.gml");
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromGml("V4_1_ID_103-asWfsFeatureCollection.gml",
				gmlAsStream);
		assertEquals(XPLAN_51, archive.getVersion());
	}

	@Test
	void testCreateXPlanArchive_NoXPlanGml() {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		InputStream gmlAsStream = XPlanArchiveCreatorTest.class
			.getResourceAsStream("V4_1_ID_103-noXPlanGmlCollection.gml");
		assertThrows(IllegalArgumentException.class,
				() -> archiveCreator.createXPlanArchiveFromGml("V4_1_ID_103-noXPlanGmlCollection.gml", gmlAsStream));
	}

	@Test
	void testCreateXPlanArchive_withEntity() {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		InputStream zipAsStream = XPlanArchiveCreatorTest.class
			.getResourceAsStream("Blankenese29_Test_60_withEntity.zip");
		assertThrows(IllegalArgumentException.class,
				() -> archiveCreator.createXPlanArchiveFromZip("Blankenese29_Test_60_withEntity.zip", zipAsStream));
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, getClass().getResourceAsStream("/testdata/" + name));
	}

	private XPlanArchive getTestArchiveWithMapper(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, getClass().getResourceAsStream("/testdata/" + name));
	}

}
