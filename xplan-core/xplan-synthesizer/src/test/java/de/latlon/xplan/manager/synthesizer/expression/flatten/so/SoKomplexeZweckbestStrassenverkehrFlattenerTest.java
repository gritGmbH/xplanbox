package de.latlon.xplan.manager.synthesizer.expression.flatten.so;

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
public class SoKomplexeZweckbestStrassenverkehrFlattenerTest {

	@Test
	public void testFlatten_arteDerFestlegung() throws Exception {
		FeatureCollection features = TestFeaturesUtils.load("xplan60/FNP_Test_60.zip");
		Feature feature = getTestFeature(features, "GML_2F2B7735-5081-24D2-6BFB-FF4545106711D");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:artDerFestlegung"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Allgemeine Zweckbestimmung: Ruhender Verkehr]", value.toString());
	}

}
