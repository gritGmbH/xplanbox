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
    public String flatten( TypedObjectNode xpVerbundenerPlan ) {
        XPlanVersion version = XPlanVersionUtils.determineBaseVersion( ( (ElementNode) xpVerbundenerPlan ).getName() );
        List<Pair<String, String>> properties = new ArrayList<>();
        append( "Verbundener Plan", xpVerbundenerPlan, "planName", properties );
        appendTranslatedCode( "Rechtscharakter Planänderung", xpVerbundenerPlan, "rechtscharakter", version,
                              "XP_RechtscharakterPlanaenderung", properties );
        append( "Nummer verbundener Plan", xpVerbundenerPlan, "nummer", properties );
        return encode( properties );
    }

}