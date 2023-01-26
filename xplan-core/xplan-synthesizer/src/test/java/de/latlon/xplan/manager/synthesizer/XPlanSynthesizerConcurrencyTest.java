/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import org.deegree.feature.Feature;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(ConcurrentTestRunner.class)
public class XPlanSynthesizerConcurrencyTest extends AbstractXplanSynthesizerTest {

	@Test
	@ThreadCount(2)
	public void testSynthesize_Concurrency() throws Exception {
		XPlanArchive archive = getTestArchive("xplan52/BPlan001_5-2_Bereiche.zip");
		XPlanFeatureCollection xplanFc = parseFeatureCollection(archive);

		xPlanSynthesizer.synthesize(xplanFc);
		xPlanSynthesizer.synthesize(xplanFc);
	}

	private XPlanFeatureCollection parseFeatureCollection(XPlanArchive archive) throws Exception {
		XPlanFeatureCollection xplanFc = readFeatures(archive);
		int id = 1;
		for (Feature feature : xplanFc.getFeatures()) {
			feature.setId("FEATURE_" + id++);
		}
		return xplanFc;
	}

}