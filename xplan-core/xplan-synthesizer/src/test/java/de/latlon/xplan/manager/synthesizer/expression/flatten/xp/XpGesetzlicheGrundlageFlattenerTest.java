package de.latlon.xplan.manager.synthesizer.expression.flatten.xp;

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
public class XpGesetzlicheGrundlageFlattenerTest {

	@Test
	public void testFlatten_gesetzlicheGrundlage() throws Exception {
		FeatureCollection features = TestFeaturesUtils.load("xplan60/FNP_Test_60.zip");
		Feature feature = getTestFeature(features, "GML_24922052-2F62-B415-9862-CA135DBEC0CBA");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:gesetzlicheGrundlage"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Name: Kurortegesetz, KurorteG]", value.toString());
	}

	@Test
	public void testFlatten_arteDerFestlegung() throws Exception {
		FeatureCollection features = TestFeaturesUtils.load("xplan60/BPlan002_6-0.zip");
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:versionBauGB"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Name: BaugGB|Datum: 2004-03-02|Detail: §10]", value.toString());
	}

}
