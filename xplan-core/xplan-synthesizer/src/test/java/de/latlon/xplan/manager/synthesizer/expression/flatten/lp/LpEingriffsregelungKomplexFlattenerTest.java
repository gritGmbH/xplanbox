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
public class LpEingriffsregelungKomplexFlattenerTest {

	@Test
	public void testFlatten_eingriffsregelungFlaechenTyp() throws Exception {
		FeatureCollection features = TestFeaturesUtils.getTestFeatures("xplan60/LP-Test_60.zip");
		Feature feature = getTestFeature(features, "Gml_35828929-2C80-4454-A4E3-8EA08D4F5D13");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:eingriffsregelungFlaechenTyp"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Flächenart: Potenzielle Fläche Kompensation]", value.toString());
	}

}
