package de.latlon.xplan.manager.synthesizer.expression.flatten.lp;

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
public class LpBiologischeVielfaltKomplexFlattenerTest {

	@Test
	public void testFlatten_biologischeVielfalt() throws Exception {
		FeatureCollection features = TestFeaturesUtils.load("xplan60/LP-Test_60.zip");
		Feature feature = getTestFeature(features, "Gml_8AC988AC-0E6C-44C6-9522-A32244FBBCE0");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:biologischeVielfalt"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals(
				"[Biologische Vielfalt: [Bestandteil: Lebensstätte, Arthabitat]|von gemeinschaftlichem Interesse kartiert: ja]",
				value.toString());
	}

}
