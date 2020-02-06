package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class BP_EmissionskontingentLaermFlattener extends AbstractFlattener {

    @Override
    public boolean accepts( TypedObjectNode element ) {
        String elName = null;
        if ( element instanceof ElementNode ) {
            elName = ( (ElementNode) element ).getName().getLocalPart();
        }
        return "BP_EmissionskontingentLaerm".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode bpEmissionskontingentLaerm ) {
        List<Pair<String, String>> properties = new ArrayList<>();
        append( "Emissionskontingent Tag [db]", bpEmissionskontingentLaerm, "ekwertTag", properties );
        append( "Emissionskontingent Nacht [db]", bpEmissionskontingentLaerm, "ekwertNacht", properties );
        append( "Erläuterung", bpEmissionskontingentLaerm, "erlaeuterung", properties );
        return encode( properties );
    }
}
