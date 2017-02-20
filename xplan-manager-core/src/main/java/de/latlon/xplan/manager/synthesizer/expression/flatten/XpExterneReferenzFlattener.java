package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;

public class XpExterneReferenzFlattener extends AbstractFlattener {

    private final String fid;

    public XpExterneReferenzFlattener( Feature contextFeature ) {
        fid = contextFeature.getId();
    }

    @Override
    public boolean accepts( TypedObjectNode node ) {
        String elName = null;
        if ( node instanceof Feature ) {
            elName = ( (Feature) node ).getName().getLocalPart();
        } else if ( node instanceof ElementNode ) {
            elName = ( (ElementNode) node ).getName().getLocalPart();
        }
        return "XP_ExterneReferenz".equals( elName ) || "XP_ExterneReferenzPlan".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode xpExterneReferenz ) {
        String s = "";
        TypedObjectNode referenzUrl = getPropertyValue( xpExterneReferenz, "referenzURL" );
        if ( referenzUrl != null ) {
            String refUrlString = referenzUrl.toString();
            if ( refUrlString.contains( ":/" ) ) {
                s += "[" + escape( refUrlString ) + "]";
            } else {
                s += "[/getAttachment?featureID=" + fid + "&filename=" + escape( refUrlString ) + "]";
            }
        }
        TypedObjectNode georefURL = getPropertyValue( xpExterneReferenz, "georefURL" );
        if ( georefURL != null ) {
            String georefUrlString = georefURL.toString();
            if ( georefUrlString.contains( "/:" ) ) {
                s += "[" + escape( georefUrlString ) + "]";
            } else {
                s += "[/getAttachment?featureID=" + fid + "&filename=" + escape( georefUrlString ) + "]";
            }
        }
        return s;
    }
}
