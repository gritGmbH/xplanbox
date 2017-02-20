package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.manager.codelists.XPlanCodeConverter.xplan2ToSynCode;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.castToArray;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;

import de.latlon.xplan.manager.codelists.XPlan2CodeTranslation;

/**
 * Expression for setting the value of a "besondereZweckbestimmungCode" property.
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author last edited by: $Author: schneider $
 * 
 * @version $Revision: 1028 $, $Date: 2010-02-18 15:48:22 +0100 (Do, 18 Feb 2010) $
 */
public class XPlanBesondZweckbest implements Expression {

    private static final String XPLAN_2_NS = "http://www.xplanung.de/xplangml";

    private Expression zweckbest;

    private Expression besondZweckbest;

    private String zweckbestCodeList;

    public XPlanBesondZweckbest( Expression zweckbest, Expression besondZweckbest, String zweckbestCodeList ) {
        this.zweckbest = zweckbest;
        this.besondZweckbest = besondZweckbest;
        this.zweckbestCodeList = zweckbestCodeList;
    }

    @Override
    public TypedObjectNodeArray<PrimitiveValue> evaluate( Feature feature ) {
        PrimitiveValue[] normalizedCodes = null;
        boolean isXPlan2 = feature.getName().getNamespaceURI().equals( XPLAN_2_NS );
        if ( isXPlan2 ) {
            // XPlan 2: translate value of zweckbestimmung property and use extended code if generated
            TypedObjectNodeArray<TypedObjectNode> xp2Codes = castToArray( zweckbest.evaluate( feature ) );
            if ( xp2Codes != null && xp2Codes.getElements().length > 0 ) {
                // property has maxOccurs="1", so accessing the first value is fine
                XPlan2CodeTranslation translation = xplan2ToSynCode( xp2Codes.getElements()[0].toString(),
                                                                     zweckbestCodeList );
                if ( translation.getXplanSynExtCode() != null ) {
                    normalizedCodes = new PrimitiveValue[] { new PrimitiveValue( translation.getXplanSynExtCode() ) };
                }
            }
        } else {
            // XPlan 3: always use value from besondereZweckbestimmung property
            TypedObjectNodeArray<TypedObjectNode> codes = castToArray( besondZweckbest.evaluate( feature ) );
            if ( codes != null && codes.getElements().length > 0 ) {
                // property has maxOccurs="1", so accessing the first value is fine
                normalizedCodes = new PrimitiveValue[] { new PrimitiveValue( codes.getElements()[0].toString() ) };
            }
        }
        if (normalizedCodes == null) {
            return null;
        }
        return new TypedObjectNodeArray<PrimitiveValue>( normalizedCodes );
    }
}
