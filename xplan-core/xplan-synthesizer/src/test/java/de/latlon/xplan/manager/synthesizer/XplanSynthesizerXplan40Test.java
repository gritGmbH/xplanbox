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
import org.junit.Ignore;
import org.junit.Test;

public class XplanSynthesizerXplan40Test extends AbstractXplanSynthesizerTest {

	private final XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

	@Ignore("File size of referenced plan was too large for Github. Test plan was removed from repository.")
	@Test
	public void testId106() throws Exception {
		createSynFeatures("xplan40/V4_1_ID_106.zip");
	}

	@Override
	XPlanVersion getXPlanVersion() {
		return XPlanVersion.XPLAN_41;
	}

}
