/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.commons;

import static org.deegree.cs.CRSUtils.EPSG_4326;
import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;

import java.text.SimpleDateFormat;

import javax.xml.stream.XMLStreamReader;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.SimpleGeometryFactory;
import org.deegree.gml.GMLStreamReader;
import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;

public class XPlanFeatureCollectionTest {

	@Test
	public void testBP2070XPlan3() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan3/BP2070.zip");
		Envelope expectedBbox = createEnvelopeIn4326(8.677866116091622, 53.13336118980635, 8.71389373561357,
				53.149394465922974);

		assertEquals("4011000", fc.getPlanGkz());
		assertNull(fc.getPlanNummer());
		assertEquals(351, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	@Test
	public void testBP2135XPlan3() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan3/BP2135.zip");
		Envelope expectedBbox = createEnvelopeIn4326(8.868225190603235, 53.02503052421284, 8.898393606163037,
				53.03851016123366);

		assertEquals("4011000", fc.getPlanGkz());
		assertEquals("Bebauungsplan 2135", fc.getPlanName());
		assertEquals("2135", fc.getPlanNummer());
		assertEquals(241, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	@Test
	public void testFPlanXPlan3() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan3/FPlan.zip");

		assertEquals("FPlan Bad Liebenwerda", fc.getPlanName());
		assertNull(fc.getPlanNummer());
		assertEquals("12062024", fc.getPlanGkz());
		assertEquals(3828, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326(), nullValue());
	}

	@Test
	public void testPlessaXPlan3() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan3/Plessa.zip");
		Envelope expectedBbox = createEnvelopeIn4326(13.601305277766333, 51.466146727282, 13.603502206068638,
				51.46709229148047);

		assertEquals("BP Plessa Gewerbegebiet, erste Aenderung", fc.getPlanName());
		assertNull(fc.getPlanNummer());
		assertEquals("12062372", fc.getPlanGkz());
		assertEquals(17, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	@Test
	public void testWuerdenhainXPlan3() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan3/Wuerdenhain.zip");
		Envelope expectedBbox = createEnvelopeIn4326(13.452766605622251, 51.47355832342167, 13.462083366729448,
				51.476197930428064);

		assertEquals("Klarstellungs-u. Ergänzungssatzung der Gemeinde Haida mit dem OT Würdenhain", fc.getPlanName());
		assertEquals("KES - O4", fc.getPlanNummer());
		assertEquals("12062410", fc.getPlanGkz());
		assertEquals(28, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	@Test
	public void testV4_1_ID_66_40() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan40/V4_1_ID_66.zip");
		Envelope expectedBbox = createEnvelopeIn4326(13.81485304869009, 51.58459550871735, 13.819661964336056,
				51.587255713842126);

		assertEquals("12062425", fc.getPlanGkz());
		assertEquals("\"Weinbergstrasse\"", fc.getPlanName());
		assertEquals("Nr. 2", fc.getPlanNummer());
		assertEquals(545, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	@Test
	public void testBP2070XPlan41() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan41/BP2070.zip");
		Envelope expectedBbox = createEnvelopeIn4326(8.677866116091622, 53.13336118980635, 8.71389373561357,
				53.149394465922974);

		assertEquals("4011000", fc.getPlanGkz());
		assertEquals(314, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	@Test
	public void testBP2135XPlan41() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan41/BP2135.zip");
		Envelope expectedBbox = createEnvelopeIn4326(8.868225190603235, 53.02503052421284, 8.898393606163037,
				53.03851016123366);

		assertEquals("4011000", fc.getPlanGkz());
		assertEquals("Bebauungsplan 2135", fc.getPlanName());
		assertEquals("2135", fc.getPlanNummer());
		assertEquals(241, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	@Test
	public void testDemoXPlan41() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan41/Demo.zip");
		Envelope expectedBbox = createEnvelopeIn4326(15.272746104246893, 49.46495151773874, 15.275043422963378,
				49.466076083919255);

		assertEquals("1234567", fc.getPlanGkz());
		assertEquals("BPlan Demo-Gemeinde", fc.getPlanName());
		assertNull(fc.getPlanNummer());
		assertEquals(20, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	@Test
	public void testEidelstedt_4_V4XPlan41() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan41/Eidelstedt_4_V4.zip");
		Envelope expectedBbox = createEnvelopeIn4326(9.900051499510967, 53.5915672483709, 9.909280908661561,
				53.5981347454368);

		assertEquals("02000000", fc.getPlanGkz());
		assertEquals("Eidelstedt 4", fc.getPlanName());
		assertNull(fc.getPlanNummer());
		assertEquals(56, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	@Test
	public void testFPlanXPlan41() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan41/FPlan.zip");

		assertEquals("12062024", fc.getPlanGkz());
		assertEquals("FPlan Bad Liebenwerda", fc.getPlanName());
		assertNull(fc.getPlanNummer());
		assertEquals(3602, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326(), nullValue());
	}

	@Test
	public void testLA22XPlan41() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan41/LA22.zip");
		Envelope expectedBbox = createEnvelopeIn4326(10.000865275035721, 53.667522639460394, 10.016612488700352,
				53.677413993384334);

		assertEquals("02000000", fc.getPlanGkz());
		assertEquals("Bebauungsplan LA 22", fc.getPlanName());
		assertEquals("LA 22", fc.getPlanNummer());
		assertEquals(1349, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	@Test
	public void testLA67XPlan41() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan41/LA67.zip");
		Envelope expectedBbox = createEnvelopeIn4326(9.994207973546764, 53.67757637094817, 10.000593706220158,
				53.68050184599885);

		assertEquals("1234567", fc.getPlanGkz());
		assertEquals("Bebauungsplan LA 22", fc.getPlanName());
		assertEquals("LA 22", fc.getPlanNummer());
		assertEquals(146, fc.getFeatures().size());
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	@Test
	public void testV4_1_ID_103_41() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan41/V4_1_ID_103.zip");
		Envelope expectedBbox = createEnvelopeIn4326(13.81519026784244, 51.58617350816596, 13.823968763528068,
				51.58945636520396);

		assertEquals("12062425", fc.getPlanGkz());
		assertEquals("\"Heideweg\"", fc.getPlanName());
		assertEquals("Nr.1", fc.getPlanNummer());
		assertEquals(500, fc.getFeatures().size());
		assertThat(fc.getPlanReleaseDate(), is(new SimpleDateFormat("yyyy-MM-dd").parse("2002-02-01")));
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	@Test
	public void testFPlanWithWirksamkeitsDatumXPlan41() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan41/FPlan-with-wirksamkeitsDatum.zip");

		assertEquals("12062024", fc.getPlanGkz());
		assertEquals("FPlan Bad Liebenwerda", fc.getPlanName());
		assertNull(fc.getPlanNummer());
		assertEquals(3602, fc.getFeatures().size());
		assertThat(fc.getPlanReleaseDate(), is(new SimpleDateFormat("yyyy-MM-dd").parse("2015-02-03")));
		assertThat(fc.getBboxIn4326(), nullValue());
	}

	@Test
	public void testnsm_niedersachsen_lrop_small_valid_for_managerWithDatumDesInkrafttretensXPlan41() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection(
				"xplan41/nsm/nsm_niedersachsen_lrop_small_valid_for_manager-with-datumDesInkrafttretens.zip");
		Envelope expectedBbox = createEnvelopeIn4326(6.086234576558423, 51.267042189186874, 11.66863301837069,
				54.23453401178898);

		assertEquals("LROP_NI", fc.getPlanName());
		assertNull(fc.getPlanNummer());
		assertEquals(71, fc.getFeatures().size());
		assertThat(fc.getPlanReleaseDate(), is(new SimpleDateFormat("yyyy-MM-dd").parse("2015-02-02")));
		assertThat(fc.getBboxIn4326().getMin().get0(), is(expectedBbox.getMin().get0()));
		assertThat(fc.getBboxIn4326().getMin().get1(), is(expectedBbox.getMin().get1()));
		assertThat(fc.getBboxIn4326().getMax().get0(), is(expectedBbox.getMax().get0()));
		assertThat(fc.getBboxIn4326().getMax().get1(), is(expectedBbox.getMax().get1()));
	}

	private XPlanFeatureCollection getMainFileAsXplanFeatureCollection(String name) throws Exception {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip(name,
				ResourceAccessor.readResourceStream(name));
		XPlanVersion version = archive.getVersion();
		XPlanAde ade = archive.getAde();
		XMLStreamReader xmlReader = archive.getMainFileXmlReader();
		GMLStreamReader gmlReader = createGMLStreamReader(version.getGmlVersion(), xmlReader);
		gmlReader.setApplicationSchema(XPlanSchemas.getInstance().getAppSchema(version, ade));
		FeatureCollection fc = gmlReader.readFeatureCollection();
		gmlReader.getIdContext().resolveLocalRefs();
		gmlReader.close();
		xmlReader.close();
		return new XPlanFeatureCollectionBuilder(fc, archive.getType()).build();
	}

	private Envelope createEnvelopeIn4326(double minx, double miny, double maxx, double maxy) {
		return new SimpleGeometryFactory().createEnvelope(minx, miny, maxx, maxy, EPSG_4326);
	}

}
