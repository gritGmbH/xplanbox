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
package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class XPlanGmlParserTest {

	@Test
	void testParseFeatureCollection() throws Exception {
		XPlanArchive testArchive = getTestArchive("xplan41/Eidelstedt_4_V4.zip");
		XPlanFeatureCollection xPlanFeatureCollection = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollection(testArchive);
		assertThat(xPlanFeatureCollection.getFeatures()).hasSize(56);
	}

	@Test
	void testParseWfsFeatureCollection() throws Exception {
		XPlanArchive testArchive = getArchive("wfs20FeatureCollection.gml");
		XPlanFeatureCollection xPlanFeatureCollection = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollection(testArchive);
		assertThat(xPlanFeatureCollection.getFeatures()).hasSize(3);
		assertThat(XPlanVersionUtils.determineBaseVersion(xPlanFeatureCollection.getFeatures().getName()))
			.isEqualTo(XPLAN_51);
	}

	@Test
	void testParseWfsFeatureCollectionWithAdditionalObjects() throws Exception {
		XPlanArchive testArchive = getArchive("wfs20FeatureCollection-additionalObjects.gml");
		XPlanFeatureCollection xPlanFeatureCollection = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollection(testArchive);
		assertThat(xPlanFeatureCollection.getFeatures()).hasSize(3);
		assertThat(XPlanVersionUtils.determineBaseVersion(xPlanFeatureCollection.getFeatures().getName()))
			.isEqualTo(XPLAN_51);
	}

	@Test
	void testParseFeatureCollectionMultipleInstance() throws Exception {
		XPlanArchive testArchive = getArchive("xplan-multipleInstances.gml");
		XPlanFeatureCollections xPlanFeatureCollections = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollectionAllowMultipleInstances(testArchive);
		assertThat(xPlanFeatureCollections.getxPlanGmlInstances()).hasSize(3);

		List<Integer> featureSizes = xPlanFeatureCollections.getxPlanGmlInstances()
			.stream() //
			.map(it -> it.getFeatures().size()) //
			.collect(Collectors.toList());

		assertThat(featureSizes).contains(5, 20, 488);
	}

	@Test
	void testParseFeatureCollectionMultipleInstanceWithUnreferencedFeature() throws Exception {
		XPlanArchive testArchive = getArchive("xplan-multipleInstances-withUnreferenced.gml");
		assertThrows(FeatureCollectionParseException.class,
				() -> XPlanGmlParserBuilder.newBuilder()
					.build()
					.parseXPlanFeatureCollectionAllowMultipleInstances(testArchive));
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, getClass().getResourceAsStream("/testdata/" + name));
	}

	private XPlanArchive getArchive(String name) throws IOException {
		InputStream resourceAsStream = XPlanGmlParserTest.class.getResourceAsStream(name);
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromGml("multipleInstances", resourceAsStream);
	}

}
