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
package de.latlon.xplan.commons.util;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.gml.GMLStreamReader;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.stream.XMLStreamReader;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.findPlanFeature;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveAdditionalType;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDistrict;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveRechtsstand;
import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for {@link FeatureCollectionUtils}.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class FeatureCollectionUtilsTest {

	@Test
	public void testFindPlanFeatureWithXPlan3() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan3/Plessa.zip");
		Feature planFeature = findPlanFeature(fc, BP_Plan);
		String id = planFeature.getId();
		String name = planFeature.getName().toString();
		String envelope = planFeature.getEnvelope().toString();

		assertThat(id, is("GML_59e52c0f-f079-41fd-b348-cf255a56f20b"));
		assertThat(name, is("{http://www.xplanung.de/xplangml/3/0}BP_Plan"));
		assertThat(envelope, is(
				"min: (402844.959,5702591.938), max: (402995.658,5702694.252), span0: 150.69900000002235, span1: 102.31400000024587 , crs: {uri=EPSG:25833, resolved=true}"));
	}

	@Test
	public void testFindPlanFeatureWithXPlan40() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan40/V4_1_ID_66.zip");
		Feature planFeature = findPlanFeature(fc, BP_Plan);
		String id = planFeature.getId();
		String name = planFeature.getName().toString();
		String envelope = planFeature.getEnvelope().toString();

		assertThat(id, is("FEATURE_8f28013a-8756-48cd-8374-9e16edcdcc34"));
		assertThat(name, is("{http://www.xplanung.de/xplangml/4/0}BP_Plan"));
		assertThat(envelope, is(
				"min: (417894.13,5715502.733), max: (418222.672,5715793.336), span0: 328.54200000001583, span1: 290.6030000001192 , crs: {uri=EPSG:25833, resolved=true}"));
	}

	@Test
	public void testFindPlanFeatureWithXPlan41() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan41/Eidelstedt_4_V4.zip");
		Feature planFeature = findPlanFeature(fc, BP_Plan);
		String id = planFeature.getId();
		String name = planFeature.getName().toString();
		String envelope = planFeature.getEnvelope().toString();

		assertThat(id, is("GML_671C685B-CE75-4B05-8236-622B0B8A7A5B"));
		assertThat(name, is("{http://www.xplanung.de/xplangml/4/1}BP_Plan"));
		assertThat(envelope, is(
				"min: (559573.142,5938465.032), max: (560174.871,5939188.129), span0: 601.7290000000503, span1: 723.097000000067 , crs: {uri=urn:adv:crs:ETRS89_UTM32, resolved=true}"));
	}

	@Test
	public void testRetrieveLegislationStatusWithExistingLegislationStatusShouldReturnString() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan41/Eidelstedt_4_V4.zip");
		String legislationStatus = retrieveRechtsstand(fc, BP_Plan);

		assertThat(legislationStatus, is("4000"));
	}

	@Test
	public void testRetrieveLegislationStatusWithMissingLegislationStatusShouldReturnNull() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan41/LA67.zip");
		String legislationStatus = retrieveRechtsstand(fc, BP_Plan);

		assertThat(legislationStatus, nullValue());
	}

	@Ignore("No test data available!")
	@Test
	public void testRetrieveAdditionalType() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("INSERT TEST FILE");
		String additionalType = retrieveAdditionalType(fc, BP_Plan);

		assertThat(additionalType, is("INSERT ADDITIONAL TYPE"));
	}

	@Test
	public void testRetrieveDistrictWithXPlan3ShouldReturnDistrict() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan3/hc7.zip");
		String district = retrieveDistrict(fc, BP_Plan, XPLAN_3);

		assertThat(district, is("Bezirk Hamburg-Mitte / Ortsteil 103"));
	}

	@Test
	public void testRetrieveDistrictWithXPlan3WithMissingDistrictShouldReturnNull() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan3/Plessa.zip");
		String district = retrieveDistrict(fc, BP_Plan, XPLAN_3);

		assertThat(district, nullValue());
	}

	@Test
	public void testRetrieveDistrictWithXPlan41ShouldReturnDistrict() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan41/Eidelstedt_4_V4.zip");
		String district = retrieveDistrict(fc, BP_Plan, XPLAN_41);

		assertThat(district, is("Bezirk Eimsbüttel Ortsteil 320"));
	}

	@Test
	public void testRetrieveDistrictWithXPlan41WithMissingDistrictNameShouldReturnNull() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan41/BP2070.zip");
		String district = retrieveDistrict(fc, BP_Plan, XPLAN_41);

		assertThat(district, nullValue());
	}

	@Test
	public void testRetrieveDistrictWithXPlan51WithMultipleDistricts() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan51/BP2070_mehrererOrtsteile.zip");
		String district = retrieveDistrict(fc, BP_Plan, XPLAN_51);

		assertThat(district, is("309"));
	}

	private FeatureCollection getMainFileAsFeatureCollection(String name) throws Exception {
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
		return fc;
	}

}
