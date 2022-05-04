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
public class BpVeraenderungssperreDatenFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "BP_VeraenderungssperreDaten");
	}

	@Override
	public String flatten(TypedObjectNode node) {
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(((ElementNode) node).getName());
		List<Pair<String, String>> properties = new ArrayList<>();
		append("Startdatum", node, "startDatum", properties);
		append("Enddatum", node, "endDatum", properties);
		appendTranslatedCode("Verlängerung", node, "verlaengerung", version, "BP_VerlaengerungVeraenderungssperre",
				properties);
		append("Beschluss vom", node, "beschlussDatum", properties);
		return encode(properties);
	}

}
