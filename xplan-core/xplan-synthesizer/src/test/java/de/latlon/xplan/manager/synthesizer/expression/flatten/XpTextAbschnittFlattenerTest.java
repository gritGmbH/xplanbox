package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.XplanFlattenProperty;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpTextAbschnittFlattenerTest {

	@Test
	public void testFlattenTexte() {
		FeatureCollection features = getTestFeatures(XPLAN_51, "flatten/XpTextAbschnittFlattener.xml");
		Feature feature = getTestFeature(features, "BP_PLAN");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:texte"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[§2 Nr.21 | text 3][§2 Nr.1 | text 1][§2 Nr.3 | text 2]", value.toString());
	}

	@Test
	public void testFlattenTexte_sorted() {
		FeatureCollection features = getTestFeatures(XPLAN_51, "flatten/XpTextAbschnittFlattener.xml");
		Feature feature = getTestFeature(features, "BP_PLAN");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:texte"), true);
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[§2 Nr.1 | text 1][§2 Nr.3 | text 2][§2 Nr.21 | text 3]", value.toString());
	}

	@Test
	public void testFlattenTexte_refText() {
		FeatureCollection features = getTestFeatures(XPLAN_51, "flatten/XpTextAbschnittWithRefText.xml");
		Feature feature = getTestFeature(features, "BP_PLAN");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:texte"), true);
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals(
				"[§2 Nr.9 | Externe Referenz: schluesseltest.pdf][§2 Nr.21 | text 3 | Externe Referenz: test.pdf][Externe Referenz: test.pdf]",
				value.toString());
	}

}
