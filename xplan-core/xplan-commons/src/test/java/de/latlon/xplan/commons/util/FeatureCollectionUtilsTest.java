/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.util;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.manager.web.shared.Bereich;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import java.util.List;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.findPlanFeature;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveBereiche;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDistrict;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveRechtsstand;
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
	public void testFindPlanFeatureWithXPlan40() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan40/BPlan004_4-0.zip");
		Feature planFeature = findPlanFeature(fc, BP_Plan);
		String id = planFeature.getId();
		String name = planFeature.getName().toString();
		String envelope = planFeature.getEnvelope().toString();

		assertThat(id, is("GML_88bfe952-199f-4bba-bea2-c2b441737144"));
		assertThat(name, is("{http://www.xplanung.de/xplangml/4/0}BP_Plan"));
		assertThat(envelope, is(
				"min: (567386.293,5937595.479), max: (567474.996,5937698.959), span0: 88.70300000009593, span1: 103.47999999951571 , crs: {uri=EPSG:25832, resolved=true}"));
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

	@Test
	public void testRetrieveDistrictWithXPlan41ShouldReturnDistrict() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan41/Eidelstedt_4_V4.zip");
		String district = retrieveDistrict(fc, BP_Plan);

		assertThat(district, is("Bezirk Eimsbüttel Ortsteil 320"));
	}

	@Test
	public void testRetrieveDistrictWithXPlan41WithMissingDistrictNameShouldReturnNull() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan41/BP2070.zip");
		String district = retrieveDistrict(fc, BP_Plan);

		assertThat(district, nullValue());
	}

	@Test
	public void testRetrieveDistrictWithXPlan51WithMultipleDistricts() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan51/BP2070_mehrererOrtsteile.zip");
		String district = retrieveDistrict(fc, BP_Plan);

		assertThat(district, is("309"));
	}

	@Test
	public void testRetrieveBereicheWithXPlan41() throws Exception {
		FeatureCollection fc = getMainFileAsFeatureCollection("xplan52/BPlan001_5-2_Bereiche.zip");
		List<Bereich> bereiche = retrieveBereiche(fc);
		assertThat(bereiche.size(), is(2));

		assertThat(bereiche.get(0).getNummer(), is("0"));
		assertThat(bereiche.get(0).getName(), is(nullValue()));

		assertThat(bereiche.get(1).getNummer(), is("1"));
		assertThat(bereiche.get(1).getName(), is(nullValue()));
	}

	private FeatureCollection getMainFileAsFeatureCollection(String name) throws Exception {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip(name,
				ResourceAccessor.readResourceStream(name));
		return XPlanGmlParserBuilder.newBuilder().build().parseFeatureCollection(archive);
	}

}
