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
public class XpSPEMassnahmenDatenFlattener extends AbstractFlattener {

    @Override
    public boolean accepts( TypedObjectNode node ) {
        String elName = null;
        if ( node instanceof ElementNode ) {
            elName = ( (ElementNode) node ).getName().getLocalPart();
        }
        return "XP_SPEMassnahmenDaten".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode xpSPEMassnahmenDaten ) {
        XPlanVersion version = XPlanVersionUtils.determineBaseVersion(
                                ( (ElementNode) xpSPEMassnahmenDaten ).getName() );
        List<Pair<String, String>> properties = new ArrayList<>();
        append( "Maßnahmentext", xpSPEMassnahmenDaten, "massnahmeText", properties );
        append( "Maßnahmenkürzel", xpSPEMassnahmenDaten, "massnahmeKuerzel", properties );
        appendTranslatedCode( "Maßnahme", xpSPEMassnahmenDaten, "klassifizMassnahme", version, "XP_SPEMassnahmenTypen",
                              properties );
        return encode( properties );
    }

}