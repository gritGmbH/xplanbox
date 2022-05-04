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
public class LpBiologischeVielfaltKomplexFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "LP_BiologischeVielfaltKomplex");
	}

	@Override
	public String flatten(TypedObjectNode node) {
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(((ElementNode) node).getName());
		List<Pair<String, String>> properties = new ArrayList<>();
		appendTranslatedCode("Bestandteil", node, "bioVielfaltTypus", version, "LP_FlaechenTypBV", properties);
		appendTranslatedCode("Planzenarten", node, "bioVfPflanzenArt", version, "LP_BioVfPflanzenArtKomplex",
				properties);
		appendTranslatedCode("Tierarten", node, "bioVfTierArt", version, "LP_BioVfTiereArtKomplex", properties);
		appendTranslatedCode("Biotoptyp", node, "bioVfBiotoptyp", version, "LP_BioVfBiotoptypKomplex", properties);
		append("von gemeinschaftlichem Interesse kartiert", node, "bioVfArtFFHAnhangII", properties);
		return encode(properties);
	}

}
