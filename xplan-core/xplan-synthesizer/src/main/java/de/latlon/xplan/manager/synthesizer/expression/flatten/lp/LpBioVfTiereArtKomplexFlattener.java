package de.latlon.xplan.manager.synthesizer.expression.flatten.lp;

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
public class LpBioVfTiereArtKomplexFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "LP_BioVfTiereArtKomplex");
	}

	@Override
	public String flatten(TypedObjectNode node) {
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(((ElementNode) node).getName());
		List<Pair<String, String>> properties = new ArrayList<>();
		append("Tierart", node, "bioVfTierArtName", properties);
		appendTranslatedCode("Einordnung", node, "bioVfTiereSystematik", version, "LP_BioVfTiereArtSystematik",
				properties);
		append("Einordnung (Ergänzung)", node, "bioVfTiereSystematikText", properties);
		appendTranslatedCode("Rechtliche Grundlage", node, "bioVfTierArtRechtlicherSchutz", version,
				"LP_BioVfTierArtRechtlicherSchutz", properties);
		append("Rechtliche Grundlage (Ergänzung)", node, "bioVfTierArtRechtlicherSchutzText", properties);
		appendTranslatedCode("Habitatanforderungen", node, "bioVfTierArtHabitatanforderung", version,
				"LP_BioVfTierArtHabitatanforderung", properties);
		append("Habitatanforderungen (Ergänzung)", node, "bioVfTierArtHabitatanforderungText", properties);
		return encode(properties);
	}

}
