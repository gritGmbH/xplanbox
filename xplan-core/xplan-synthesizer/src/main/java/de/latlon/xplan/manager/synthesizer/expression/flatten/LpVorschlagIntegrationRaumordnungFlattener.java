package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class LpVorschlagIntegrationRaumordnungFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "LP_VorschlagIntegrationRaumordnung");
	}

	@Override
	public String flatten(TypedObjectNode node) {
		List<Pair<String, String>> properties = new ArrayList<>();
		append("Begruendung", node, "begruendung", properties);
		return encode(properties);
	}

}
