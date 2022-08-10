package de.latlon.xplan.manager.synthesizer.praesentation;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.PraesentationsobjektLookup;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.property.SimpleProperty;

import java.io.IOException;

import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class AbstractPraesentationsobjektLookupTest {

	protected static PrimitiveValue getEvaluate(FeatureCollection features, String gmlId,
			PraesentationsobjektLookup lookup) {
		Feature feature = getTestFeature(features, gmlId);
		TypedObjectNode evaluate = lookup.evaluate(feature, features);
		if (evaluate instanceof GenericProperty) {
			GenericProperty genericProperty = (GenericProperty) evaluate;
			TypedObjectNode child = genericProperty.getChildren().get(0);
			return (PrimitiveValue) child;
		}
		if (evaluate instanceof SimpleProperty) {
			SimpleProperty simpleProperty = (SimpleProperty) evaluate;
			return simpleProperty.getValue();
		}
		return (PrimitiveValue) evaluate;
	}

	protected XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

}
