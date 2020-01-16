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
public class BpDachgestaltungFlattener extends AbstractFlattener {

    @Override
    public boolean accepts( TypedObjectNode node ) {
        String elName = null;
        if ( node instanceof ElementNode ) {
            elName = ( (ElementNode) node ).getName().getLocalPart();
        }
        return "BP_Dachgestaltung".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode bpDachgestaltung ) {
        XPlanVersion version = XPlanVersionUtils.determineBaseVersion( ( (ElementNode) bpDachgestaltung ).getName() );
        List<Pair<String, String>> properties = new ArrayList<>();
        append( "Dachneigung", bpDachgestaltung, "DN", properties );
        append( "Dachneigung Min", bpDachgestaltung, "DNmin", properties );
        append( "Dachneigung Max", bpDachgestaltung, "DNmax", properties );
        append( "Dachneigung Zwingend", bpDachgestaltung, "DNzwingend", properties );
        appendTranslatedCode( "Dachform", bpDachgestaltung, "dachform", version, "BP_Dachform", properties );
        append( "Detaillierte Dachform", bpDachgestaltung, "detaillierteDachform", properties );
        return encode( properties );
    }

}