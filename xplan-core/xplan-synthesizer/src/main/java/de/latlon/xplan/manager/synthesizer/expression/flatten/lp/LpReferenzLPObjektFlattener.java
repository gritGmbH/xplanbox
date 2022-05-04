package de.latlon.xplan.manager.synthesizer.expression.flatten.lp;

import de.latlon.xplan.manager.synthesizer.expression.flatten.AbstractFlattener;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class LpReferenzLPObjektFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "LP_ReferenzLPObjekt");
	}

	@Override
	public String flatten(TypedObjectNode node) {
		List<Pair<String, String>> properties = new ArrayList<>();
		append("Beschreibung", node, "beschreibung", properties);
		return encode(properties);
	}

}
