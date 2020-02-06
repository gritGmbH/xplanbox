package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class BP_EmissionskontingentLaermGebietFlattener extends AbstractFlattener {

    @Override
    public boolean accepts( TypedObjectNode element ) {
        String elName = null;
        if ( element instanceof ElementNode ) {
            elName = ( (ElementNode) element ).getName().getLocalPart();
        }
        return "BP_EmissionskontingentLaermGebiet".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode bpEmissionskontingentLaermGebiet ) {
        List<Pair<String, String>> properties = new ArrayList<>();
        append( "Emissionskontingent Tag [db]", bpEmissionskontingentLaermGebiet, "ekwertTag", properties );
        append( "Emissionskontingent Nacht [db]", bpEmissionskontingentLaermGebiet, "ekwertNacht", properties );
        append( "Erläuterung", bpEmissionskontingentLaermGebiet, "erlaeuterung", properties );
        append( "Bezeichnung des Immissionsgebietes", bpEmissionskontingentLaermGebiet, "gebietsbezeichnung",
                properties );
        return encode( properties );
    }
}
