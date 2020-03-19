package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.manager.synthesizer.expression.XplanTextAbschnitte;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;

import java.util.Arrays;
import java.util.List;

public class XpTextAbschnittFlattener extends AbstractFlattener {

    private static final List<String> ACCEPTEDELEMENTS = Arrays.asList(
                            new String[] { "XP_TextAbschnitt", "BP_TextAbschnitt", "FP_TextAbschnitt",
                                           "LP_TextAbschnitt", "RP_TextAbschnitt", "SO_TextAbschnitt" } );

    @Override
    public boolean accepts( TypedObjectNode node ) {
        String elName = null;
        if ( node instanceof Feature ) {
            elName = ( (Feature) node ).getName().getLocalPart();
        }
        return ACCEPTEDELEMENTS.contains( elName );
    }

    @Override
    public String flatten( TypedObjectNode xpTextAbschnitt ) {
        Feature feature = (Feature) xpTextAbschnitt;
        return XplanTextAbschnitte.toString( feature );
    }
}
