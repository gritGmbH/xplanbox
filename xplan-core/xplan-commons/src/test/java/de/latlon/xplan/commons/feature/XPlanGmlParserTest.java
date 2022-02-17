package de.latlon.xplan.commons.feature;

/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlParserTest {

	@Test
	public void testParseFeatureCollection() throws Exception {
		XPlanGmlParser xPlanGmlParser = new XPlanGmlParser();
		XPlanArchive testArchive = getTestArchive("xplan41/Eidelstedt_4_V4.zip");
		XPlanFeatureCollection xPlanFeatureCollection = xPlanGmlParser.parseXPlanFeatureCollection(testArchive);
		assertThat(xPlanFeatureCollection.getFeatures().size(), is(56));
	}

	@Test
	public void testParseFeatureCollectionMultipleInstance() throws Exception {
		XPlanGmlParser xPlanGmlParser = new XPlanGmlParser();
		XPlanArchive testArchive = getArchive("xplan-multipleInstances.gml");
		XPlanFeatureCollections xPlanFeatureCollections = xPlanGmlParser
				.parseXPlanFeatureCollectionAllowMultipleInstances(testArchive, null);
		assertThat(xPlanFeatureCollections.getxPlanGmlInstances().size(), is(3));
		assertThat(xPlanFeatureCollections, containsInstanceWithNoOFFeatures(5));
		assertThat(xPlanFeatureCollections, containsInstanceWithNoOFFeatures(20));
		assertThat(xPlanFeatureCollections, containsInstanceWithNoOFFeatures(488));
	}

	@Test(expected = FeatureCollectionParseException.class)
	public void testParseFeatureCollectionMultipleInstanceWithUnreferencedFeature() throws Exception {
		XPlanGmlParser xPlanGmlParser = new XPlanGmlParser();
		XPlanArchive testArchive = getArchive("xplan-multipleInstances-withUnreferenced.gml");
		xPlanGmlParser.parseXPlanFeatureCollectionAllowMultipleInstances(testArchive, null);
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

	private XPlanArchive getArchive(String name) throws IOException {
		InputStream resourceAsStream = XPlanGmlParserTest.class.getResourceAsStream(name);
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromGml("multipleInstances", resourceAsStream);
	}

	private BaseMatcher<XPlanFeatureCollections> containsInstanceWithNoOFFeatures(int expectedNoOfFeatures) {
		return new BaseMatcher<>() {

			@Override
			public boolean matches(Object o) {
				XPlanFeatureCollections collections = (XPlanFeatureCollections) o;
				for (XPlanFeatureCollection collection : collections.getxPlanGmlInstances()) {
					if (collection.getFeatures().size() == expectedNoOfFeatures) {
						return true;
					}
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {

			}
		};
	}

}
