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

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.FeatureType;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import javax.xml.namespace.QName;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FeatureTypeNameSynthesizerTest {

	@Test
	public void testIdsMatchSynFeatureType_matchingIds() {
		XPlanFeatureCollection xPlanFeatureCollection = mockXPlanFeatureCollection("XPLAN_BP_PLAN_123", "BP_Plan",
				"XPLAN_BP_GRUENFLAECHE_456", "BP_Gruenflaeche");
		FeatureTypeNameSynthesizer featureTypeNameSynthesizer = new FeatureTypeNameSynthesizer();
		boolean idsMatchSynFeatureType = featureTypeNameSynthesizer.idsMatchSynFeatureType(xPlanFeatureCollection);
		assertThat(idsMatchSynFeatureType, is(true));
	}

	@Test
	public void testIdsMatchSynFeatureType_nonMatchingIds() {
		XPlanFeatureCollection xPlanFeatureCollection = mockXPlanFeatureCollection("BP_Plan_123", "BP_Plan",
				"BP_Gruenflaeche_456", "BP_Gruenflaeche");
		FeatureTypeNameSynthesizer featureTypeNameSynthesizer = new FeatureTypeNameSynthesizer();
		boolean idsMatchSynFeatureType = featureTypeNameSynthesizer.idsMatchSynFeatureType(xPlanFeatureCollection);
		assertThat(idsMatchSynFeatureType, is(false));
	}

	@Test
	public void testIdsMatchSynFeatureType_nonMatchingIdsGml() {
		XPlanFeatureCollection xPlanFeatureCollection = mockXPlanFeatureCollection("GML_123", "BP_Plan", "GML_456",
				"BP_Gruenflaeche");
		FeatureTypeNameSynthesizer featureTypeNameSynthesizer = new FeatureTypeNameSynthesizer();
		boolean idsMatchSynFeatureType = featureTypeNameSynthesizer.idsMatchSynFeatureType(xPlanFeatureCollection);
		assertThat(idsMatchSynFeatureType, is(false));
	}

	@Test
	public void testIdsMatchSynFeatureType_nonMatchingIdsSynWithTypo() {
		XPlanFeatureCollection xPlanFeatureCollection = mockXPlanFeatureCollection("XPLAN_BP_PLAN_123", "BP_Plan",
				"XPLAN_BP_ABWEICHUNGVONUEBERBAUBERERGRUNDSTUECKSFLAECHE_4546",
				"BP_AbweichungVonUeberbaubererGrundstuecksFlaeche");
		FeatureTypeNameSynthesizer featureTypeNameSynthesizer = new FeatureTypeNameSynthesizer();
		boolean idsMatchSynFeatureType = featureTypeNameSynthesizer.idsMatchSynFeatureType(xPlanFeatureCollection);
		assertThat(idsMatchSynFeatureType, is(false));
	}

	@Test
	public void testIdsMatchSynFeatureType_matchingIdsSynWithTypo() {
		XPlanFeatureCollection xPlanFeatureCollection = mockXPlanFeatureCollection("XPLAN_BP_PLAN_123", "BP_Plan",
				"XPLAN_BP_ABWEICHUNGVONUEBERBAUBARERGRUNDSTUECKSFLAECHE_4546",
				"BP_AbweichungVonUeberbaubererGrundstuecksFlaeche");
		FeatureTypeNameSynthesizer featureTypeNameSynthesizer = new FeatureTypeNameSynthesizer();
		boolean idsMatchSynFeatureType = featureTypeNameSynthesizer.idsMatchSynFeatureType(xPlanFeatureCollection);
		assertThat(idsMatchSynFeatureType, is(true));
	}

	@Test
	public void testIdsMatchSynFeatureType_matchingIdsSynWithoutTypo() {
		XPlanFeatureCollection xPlanFeatureCollection = mockXPlanFeatureCollection("XPLAN_BP_PLAN_123", "BP_Plan",
				"XPLAN_BP_ABWEICHUNGVONUEBERBAUBARERGRUNDSTUECKSFLAECHE_4546",
				"BP_AbweichungVonUeberbaubarerGrundstuecksFlaeche");
		FeatureTypeNameSynthesizer featureTypeNameSynthesizer = new FeatureTypeNameSynthesizer();
		boolean idsMatchSynFeatureType = featureTypeNameSynthesizer.idsMatchSynFeatureType(xPlanFeatureCollection);
		assertThat(idsMatchSynFeatureType, is(true));
	}

	private static XPlanFeatureCollection mockXPlanFeatureCollection(String feature1Id, String feature1TypeName,
			String feature2Id, String feature2TypeName) {
		XPlanFeatureCollection xPlanFeatureCollection = mock(XPlanFeatureCollection.class);
		FeatureCollection features = mock(FeatureCollection.class);
		Answer<Stream> answer = invocation -> {
			Feature feature1 = mockFeature(feature1Id, feature1TypeName);
			Feature feature2 = mockFeature(feature2Id, feature2TypeName);
			return Stream.of(feature1, feature2);
		};
		when(features.stream()).thenAnswer(answer);
		when(xPlanFeatureCollection.getFeatures()).thenReturn(features);
		return xPlanFeatureCollection;
	}

	private static Feature mockFeature(String featureId, String featureTypeName) {
		Feature feature = mock(Feature.class);
		when(feature.getId()).thenReturn(featureId);
		FeatureType featureType = mock(FeatureType.class);
		when(featureType.getName()).thenReturn(new QName(featureTypeName));
		when(feature.getType()).thenReturn(featureType);
		return feature;
	}

}
