package de.latlon.xplan.manager.synthesizer.expression.flatten.so;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import de.latlon.xplan.manager.synthesizer.expression.flatten.AbstractFlattener;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SoKomplexeZweckbestStrassenverkehrFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "SO_KomplexeZweckbestStrassenverkehr");
	}

	@Override
	public String flatten(TypedObjectNode node) {
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(((ElementNode) node).getName());
		List<Pair<String, String>> properties = new ArrayList<>();
		appendTranslatedCode("Allgemeine Zweckbestimmung", node, "allgemein", version,
				"SO_ZweckbestimmungStrassenverkehr", properties);
		append("Ergänzung", node, "textlicheErgaenzung", properties);
		append("Aufschrift", node, "aufschrift", properties);
		return encode(properties);
	}

}
