/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.feature.FeatureCollection;
import org.junit.Ignore;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Ignore
public class XplanSynthesizerXplan3Test extends AbstractXplanSynthesizerTest {

	@Override
	XPlanVersion getXPlanVersion() {
		return XPLAN_3;
	}

	@Test
	public void testBp2070() throws Exception {
		FeatureCollection expected = readSynFeatures("BP2070_XPLAN3.gml");
		FeatureCollection actual = createSynFeatures("xplan3/BP2070.zip");
		assertEqualContent(expected, actual);
	}

	@Test
	public void testBp2135() throws Exception {
		FeatureCollection expected = readSynFeatures("BP2135_XPLAN3.gml");
		FeatureCollection actual = createSynFeatures("xplan3/BP2135.zip");
		assertEqualContent(expected, actual);
	}

	@Test
	public void testDemo() throws Exception {
		FeatureCollection expected = readSynFeatures("Demo_XPLAN3.gml");
		FeatureCollection actual = createSynFeatures("xplan3/Demo.zip");
		assertEqualContent(expected, actual);
	}

	@Test
	public void testFplan() throws Exception {
		FeatureCollection expected = readSynFeatures("FPlan_XPLAN3.gml");
		FeatureCollection actual = createSynFeatures("xplan3/FPlan.zip");
		assertEqualContent(expected, actual);
	}

	@Test
	public void testHc7() throws Exception {
		FeatureCollection expected = readSynFeatures("hc7_XPLAN3.gml");
		FeatureCollection actual = createSynFeatures("xplan3/hc7.zip");
		assertEqualContent(expected, actual);
	}

	@Test
	public void testLa22() throws Exception {
		FeatureCollection expected = readSynFeatures("LA22_XPLAN3.gml");
		FeatureCollection actual = createSynFeatures("xplan3/LA22.zip");
		assertEqualContent(expected, actual);
	}

	@Test
	public void testLa67() throws Exception {
		FeatureCollection expected = readSynFeatures("LA67_XPLAN3.gml");
		FeatureCollection actual = createSynFeatures("xplan3/LA67.zip");
		assertEqualContent(expected, actual);
	}

}
