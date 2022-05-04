package de.latlon.xplan.manager.synthesizer.expression.flatten.xp;

import de.latlon.xplan.manager.synthesizer.expression.flatten.AbstractFlattener;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpGesetzlicheGrundlageFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "XP_GesetzlicheGrundlage");
	}

	@Override
	public String flatten(TypedObjectNode node) {
		List<Pair<String, String>> properties = new ArrayList<>();
		append("Name", node, "name", properties);
		append("Datum", node, "datum", properties);
		append("Detail", node, "detail", properties);
		return encode(properties);
	}

}
