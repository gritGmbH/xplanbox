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
public class XpVerbundenerPlanBereichFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "XP_VerbundenerPlanBereich");
	}

	@Override
	public String flatten(TypedObjectNode node) {
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(((ElementNode) node).getName());
		List<Pair<String, String>> properties = new ArrayList<>();
		append("Verbundener Planbereich", node, "planName", properties);
		appendTranslatedCode("Rechtscharakter Planänderung", node, "rechtscharakter", version,
				"XP_RechtscharakterPlanaenderung", properties);
		appendTranslatedCode("Änderungsart", node, "aenderungsArt", version, "XP_Aenderungsarten", properties);
		append("Nummer verbundener Plan", node, "nummer", properties);
		return encode(properties);
	}

}
