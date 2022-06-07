package de.latlon.xplan.manager.synthesizer.expression.flatten;

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
public class KomplexeZweckbestimmungFlattenerTest {

	@Test
	public void testFlatten_zweckbestimmung() throws Exception {
		FeatureCollection features = TestFeaturesUtils.getTestFeatures("xplan60/BPlan001_6-0.zip");
		Feature feature = getTestFeature(features, "GML_fa0eea57-ebb1-4d50-b205-95865d6b9284");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:zweckbestimmung"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Allgemein: Naturerfahrungsraum|Aufschrift: Grüne Hölle]", value.toString());
	}

}
