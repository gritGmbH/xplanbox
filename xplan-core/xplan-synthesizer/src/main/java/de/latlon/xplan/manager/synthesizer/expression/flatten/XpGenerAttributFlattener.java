package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.commons.XPlanVersion;
import org.apache.xerces.xs.XSElementDeclaration;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;
import org.deegree.feature.types.AppSchema;
import org.deegree.feature.types.FeatureType;

import javax.xml.namespace.QName;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;

public class XpGenerAttributFlattener extends AbstractFlattener {

    @Override
    public boolean accepts( TypedObjectNode node ) {
        if ( node instanceof Feature ) {
            return isGenerAttribut( (Feature) node );
        } else if ( node instanceof ElementNode ) {
            XSElementDeclaration elDecl = ( (ElementNode) node ).getXSType();
            return isGenerAttribut( elDecl );
        }
        return false;
    }

    private boolean isGenerAttribut( Feature feature ) {
        XPlanVersion version = determineBaseVersion( feature.getName() );
        if ( !XPLAN_3.equals( version ) ) {
            return false;
        }
        FeatureType ft = feature.getType();
        AppSchema schema = ft.getSchema();
        String ns = feature.getName().getNamespaceURI();
        FeatureType generAttributFt = schema.getFeatureType( new QName( ns, "XP_GenerAttribut" ) );
        return schema.isSubType( generAttributFt, ft );
    }

    private boolean isGenerAttribut( XSElementDeclaration elDecl ) {
        if ( "XP_GenerAttribut".equals( elDecl.getName() ) ) {
            return true;
        }
        return elDecl.getSubstitutionGroupAffiliation() != null
               && isGenerAttribut( elDecl.getSubstitutionGroupAffiliation() );
    }

    @Override
    public String flatten( TypedObjectNode xpGenerAttribut ) {
        TypedObjectNode name = getPropertyValue( xpGenerAttribut, "name" );
        TypedObjectNode wert = getPropertyValue( xpGenerAttribut, "wert" );
        return "[\"" + name + "\"=\"" + wert + "\"]";
    }

}
