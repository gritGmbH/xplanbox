package de.latlon.xplan.manager.synthesizer.expression.flatten.lp;

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

import de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.XplanFlattenProperty;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class LpEingriffsregelungKomplexFlattenerTest {

	@Test
	public void testFlatten_eingriffsregelungFlaechenTyp() throws Exception {
		FeatureCollection features = TestFeaturesUtils.load("xplan60/LP-Test_60.zip");
		Feature feature = getTestFeature(features, "Gml_35828929-2C80-4454-A4E3-8EA08D4F5D13");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:eingriffsregelungFlaechenTyp"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Flächenart: Potenzielle Fläche Kompensation]", value.toString());
	}

}
