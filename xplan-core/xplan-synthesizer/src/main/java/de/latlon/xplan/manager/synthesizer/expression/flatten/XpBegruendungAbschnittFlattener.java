package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;

import de.latlon.xplan.manager.synthesizer.expression.XplanBegruendungAbschnitte;

public class XpBegruendungAbschnittFlattener extends AbstractFlattener {

    @Override
    public boolean accepts(TypedObjectNode node) {
        String elName = null;
        if (node instanceof Feature) {
            elName = ((Feature) node).getName().getLocalPart();
        }
        return "XP_BegruendungAbschnitt".equals(elName);
    }

    @Override
    public String flatten(TypedObjectNode xpBegruendungAbschnitt) {
        Feature feature = (Feature) xpBegruendungAbschnitt;
        return XplanBegruendungAbschnitte.toString(feature);
    }
}
