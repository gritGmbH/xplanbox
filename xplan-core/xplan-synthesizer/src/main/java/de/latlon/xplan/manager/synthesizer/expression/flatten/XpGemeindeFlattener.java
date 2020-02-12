package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class XpGemeindeFlattener extends AbstractFlattener {

    @Override
    public boolean accepts( TypedObjectNode node ) {
        String elName = null;
        if ( node instanceof ElementNode ) {
            elName = ( (ElementNode) node ).getName().getLocalPart();
        }
        return "XP_Gemeinde".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode xpGemeinde ) {
        List<Pair<String, String>> properties = new ArrayList<>();
        append( "Gemeindeschlüssel", xpGemeinde, "ags", properties );
        append( "Regionalschlüssel", xpGemeinde, "rs", properties );
        append( "Gemeinde", xpGemeinde, "gemeindeName", properties );
        append( "Ortsteil", xpGemeinde, "ortsteilName", properties );
        return encode( properties );
    }

}
