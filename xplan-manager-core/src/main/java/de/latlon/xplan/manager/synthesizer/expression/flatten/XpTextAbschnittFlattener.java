package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;

import de.latlon.xplan.manager.synthesizer.expression.XplanTextAbschnitte;

public class XpTextAbschnittFlattener extends AbstractFlattener {

    @Override
    public boolean accepts( TypedObjectNode node ) {
        String elName = null;
        if ( node instanceof Feature ) {
            elName = ( (Feature) node ).getName().getLocalPart();
        }
        return "XP_TextAbschnitt".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode xpTextAbschnitt ) {
        Feature feature = (Feature) xpTextAbschnitt;
        return XplanTextAbschnitte.toString( feature );
    }
}
