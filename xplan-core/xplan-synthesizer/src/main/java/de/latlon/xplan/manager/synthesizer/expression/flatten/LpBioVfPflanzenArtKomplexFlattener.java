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
public class LpBioVfPflanzenArtKomplexFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "LP_BioVfPflanzenArtKomplex");
	}

	@Override
	public String flatten(TypedObjectNode node) {
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(((ElementNode) node).getName());
		List<Pair<String, String>> properties = new ArrayList<>();
		append("Pflanzenart", node, "bioVfPflanzenArtName", properties);
		appendTranslatedCode("Einordnung", node, "bioVfPflanzenSystematik", version, "LP_BioVfPflanzenArtSystematik",
				properties);
		append("Einordnung (Ergänzung)", node, "bioVfPflanzenSystematikText", properties);
		appendTranslatedCode("Rechtliche Grundlage", node, "bioVfPflanzenRechtlicherSchutz", version,
				"LP_BioVfPflanzenArtRechtlicherSchutz", properties);
		append("Rechtliche Grundlage (Ergänzung)", node, "bioVfPflanzenRechtlicherSchutzText", properties);
		return encode(properties);
	}

}
