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

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.xmlunit.matchers.EvaluateXPathMatcher.hasXPath;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(JUnitParamsRunner.class)
public class XplanSynthesizerXplan50Test extends AbstractXplanSynthesizerTest {

	// Contains broken geometrieS: "xplan50/FPlan.zip",
	@Parameters({ "xplan50/BP2070.zip", "xplan50/BP2135.zip", "xplan50/LA22.zip", "xplan50/LA67.zip" })
	@Test
	public void testCreateSynFeatures(String archiveName) throws Exception {
		XPlanArchive archive = getTestArchive(archiveName);
		XPlanFeatureCollection originalFeatureCollection = XPlanGmlParserBuilder.newBuilder().build()
				.parseXPlanFeatureCollection(archive);
		FeatureCollection synFeatureCollection = createSynFeatures(archive.getVersion(), originalFeatureCollection);

		int numberOfOriginalFeatures = originalFeatureCollection.getFeatures().size();
		int numberOfSynFeatures = synFeatureCollection.size();

		assertThat(numberOfSynFeatures, is(numberOfOriginalFeatures));
		String synGml = writeSynFeatureCollection(synFeatureCollection);

		assertThat(synGml,
				hasXPath("count(//xplansyn:rechtscharakter[text() = ''])", is("0")).withNamespaceContext(nsContext()));
	}

}
