/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer;

import org.assertj.core.api.AbstractAssert;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class XplanSynthesizerXplan41Test extends AbstractXplanSynthesizerTest {

	@Test
	void testBp2070() throws Exception {
		createSynFeatures("xplan41/BP2070.zip");
	}

	@Test
	void testBp2135() throws Exception {
		createSynFeatures("xplan41/BP2135.zip");
	}

	@Test
	void testDemo() throws Exception {
		createSynFeatures("xplan41/Demo.zip");
	}

	@Test
	void testEidelstedt4() throws Exception {
		createSynFeatures("xplan41/Eidelstedt_4_V4.zip");
	}

	@Test
	void testEidelstedt4ContainsPropertyHoehenangabe() throws Exception {
		FeatureCollection features = createSynFeatures("xplan41/Eidelstedt_4_V4.zip");

		assertThatFeatureCollection(features).hasFeature("BP_BaugebietsTeilFlaeche");
		assertThatFeatureCollection(features).hasHoehenangabeProperty("BP_BaugebietsTeilFlaeche",
				"[Höhenbezug: absolutNHN|Bezugspunkt: HBA|Höhe: 23]");
	}

	@Test
	void testFplan() throws Exception {
		createSynFeatures("xplan41/FPlan.zip");
	}

	@Test
	void testHc7Bereich2() throws Exception {
		createSynFeatures("xplan41/hc7_bereich_2_V4.zip");
	}

	@Test
	void testLa22() throws Exception {
		createSynFeatures("xplan41/LA22.zip");
	}

	@Test
	void testLa67() throws Exception {
		createSynFeatures("xplan41/LA67.zip");
	}

	@Test
	void testBPlan001_41() throws Exception {
		createSynFeatures("xplan41/BPlan001_4-1.zip");
	}

	FeatureCollectionAssert assertThatFeatureCollection(FeatureCollection featureCollection) {
		return new FeatureCollectionAssert(featureCollection);
	}

	static class FeatureCollectionAssert extends AbstractAssert<FeatureCollectionAssert, FeatureCollection> {

		private QName propName = new QName("http://www.deegree.org/xplanung/1/0", "hoehenangabe");

		protected FeatureCollectionAssert(FeatureCollection actual) {
			super(actual, FeatureCollectionAssert.class);
		}

		FeatureCollectionAssert hasFeature(final String featureName) {
			for (Feature feature : actual) {
				if (featureName.equals(feature.getName().getLocalPart()))
					return this;
			}

			throw failure("Expect a feature with name " + featureName);
		}

		FeatureCollectionAssert hasHoehenangabeProperty(final String featureName, final String expectedPropertyValue) {

			List<Property> properties = findHoehenangabeProperties(actual, featureName);
			if (properties != null && !properties.isEmpty()) {
				String propertyValue = properties.get(0).getValue().toString();
				assertThat(propertyValue).isEqualTo(expectedPropertyValue);
				return this;
			}
			throw failure("Expect a feature with name " + featureName + " and property " + propName + " with value "
					+ expectedPropertyValue);
		}

		private List<Property> findHoehenangabeProperties(FeatureCollection features, String featureName) {
			for (Feature feature : features) {
				if (featureName.equals(feature.getName().getLocalPart())) {
					return feature.getProperties(propName);
				}
			}
			return null;
		}

	}

}
