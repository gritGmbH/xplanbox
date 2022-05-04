package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpWirksamkeitBedingungFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "XP_WirksamkeitBedingung");
	}

	@Override
	public String flatten(TypedObjectNode xpPlangeber) {
		List<Pair<String, String>> properties = new ArrayList<>();
		append("Bedingung", xpPlangeber, "bedingung", properties);
		append("Datum", xpPlangeber, "datumAbsolut", properties);
		append("Zeitspanne", xpPlangeber, "datumRelativ", properties);
		return encode(properties);
	}

}
