package de.latlon.xplan.manager.synthesizer.expression.flatten.lp;

import de.latlon.xplan.manager.synthesizer.expression.flatten.AbstractFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.DefaultFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.Flattener;
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
		List<Pair<String, String>> properties = new ArrayList<>();
		appendFlattenedValue("Biologische Vielfalt", node, new LpBiologischeVielfaltTypKomplexFlattener(),
				"bioVielfaltTypus", properties);
		appendFlattenedValue("Planzenart", node, new LpBioVfPflanzenArtKomplexFlattener(), "bioVfPflanzenArt",
				properties);
		appendFlattenedValue("Tierart", node, new LpBioVfTiereArtKomplexFlattener(), "bioVfTierArt", properties);
		appendFlattenedValue("Biotoptyp", node, new LpBioVfBiotoptypKomplexFlattener(), "bioVfBiotoptyp", properties);
		appendBoolean("von gemeinschaftlichem Interesse kartiert", node, "bioVfArtFFHAnhangII", properties);
		return encode(properties);
	}

	private void appendFlattenedValue(String label, TypedObjectNode node, Flattener flattener, String propName,
			List<Pair<String, String>> properties) {
		String flattenedValue = toString(node, flattener, propName);
		if (flattenedValue != null) {
			properties.add(new Pair<>(label, flattenedValue));
		}
	}

	private String toString(TypedObjectNode node, Flattener flattener, String propName) {
		TypedObjectNode property = getPropertyValue(node, propName);
		if (property == null)
			return null;
		if (flattener.accepts(property)) {
			return flattener.flatten(property);
		}
		return new DefaultFlattener().flatten(node);
	}

}
