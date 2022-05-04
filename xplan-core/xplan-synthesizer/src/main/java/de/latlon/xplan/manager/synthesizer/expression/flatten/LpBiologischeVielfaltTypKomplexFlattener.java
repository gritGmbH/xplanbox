package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class LpBiologischeVielfaltTypKomplexFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "LP_BiologischeVielfaltTypKomplex");
	}

	@Override
	public String flatten(TypedObjectNode node) {
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(((ElementNode) node).getName());
		List<Pair<String, String>> properties = new ArrayList<>();
		appendTranslatedCode("Bestandteil", node, "bioVielfaltTyp", version, "LP_BioVfBestandteil", properties);
		append("Bestandteil (Ergänzung)", node, "bioVielfaltTypText", properties);
		return encode(properties);
	}

}
