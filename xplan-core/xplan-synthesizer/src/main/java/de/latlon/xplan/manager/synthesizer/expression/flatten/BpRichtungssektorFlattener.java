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
public class BpRichtungssektorFlattener extends AbstractFlattener {

    @Override
    public boolean accepts( TypedObjectNode element ) {
        String elName = null;
        if ( element instanceof ElementNode ) {
            elName = ( (ElementNode) element ).getName().getLocalPart();
        }
        return "BP_Richtungssektor".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode bpRichtungssektor ) {
        List<Pair<String, String>> properties = new ArrayList<>();
        append( "Startwinkel", bpRichtungssektor, "winkelAnfang", properties );
        append( "Endwinkel", bpRichtungssektor, "winkelEnde", properties );
        append( "Zusatzkontingent Tag", bpRichtungssektor, "zkWertTag", properties );
        append( "Zusatzkontingent Nacht", bpRichtungssektor, "zkWertNacht", properties );
        return encode( properties );
    }

}
