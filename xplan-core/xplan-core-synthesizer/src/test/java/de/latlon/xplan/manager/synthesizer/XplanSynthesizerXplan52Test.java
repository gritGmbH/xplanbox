/*-
 * #%L
 * xplan-core-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.deegree.feature.FeatureCollection;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.xmlunit.assertj3.XmlAssert;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class XplanSynthesizerXplan52Test extends AbstractXplanSynthesizerTest {

	@ParameterizedTest
	@ValueSource(strings = { "xplan52/BP2070.zip", "xplan52/BP2135.zip", "xplan52/LA22.zip", "xplan52/LA67.zip" })
	void testCreateSynFeatures(String archiveName) throws Exception {
		XPlanArchive archive = getTestArchive(archiveName);
		XPlanFeatureCollection originalFeatureCollection = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollection(archive);
		FeatureCollection synFeatureCollection = createSynFeatures(archive.getVersion(), originalFeatureCollection);

		int numberOfOriginalFeatures = originalFeatureCollection.getFeatures().size();
		int numberOfSynFeatures = synFeatureCollection.size();

		assertEquals(numberOfOriginalFeatures, numberOfSynFeatures);
		String synGml = writeSynFeatureCollection(synFeatureCollection);

		XmlAssert.assertThat(synGml)
			.withNamespaceContext(nsContext())
			.valueByXPath("count(//xplansyn:rechtscharakter[text() = ''])")
			.isEqualTo(0);
	}

}
