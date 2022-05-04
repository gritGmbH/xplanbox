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
public class LpTypBioVerbundKomplexFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "LP_TypBioVerbundKomplex");
	}

	@Override
	public String flatten(TypedObjectNode node) {
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(((ElementNode) node).getName());
		List<Pair<String, String>> properties = new ArrayList<>();
		appendTranslatedCode("Flächentyp", node, "flaechenTypBV", version, "LP_FlaechenTypBV", properties);
		appendTranslatedCode("Flächentyp (speziell)", node, "flaechentypBVSpeziell", version,
				"LP_FlaechenTypBVSpeziell", properties);
		append("Flächentyp (Ergänzung zu speziell)", node, "flaechentypSpeziellText", properties);
		return encode(properties);
	}

}
