package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import static de.latlon.xplan.manager.synthesizer.expression.Expressions.castToArray;

/**
 * Expression for setting the value of a "besondereZweckbestimmungCode" property.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author last edited by: $Author: schneider $
 * @version $Revision: 1028 $, $Date: 2010-02-18 15:48:22 +0100 (Do, 18 Feb 2010) $
 */
public class XPlanBesondZweckbest implements Expression {

    private Expression besondZweckbest;

    public XPlanBesondZweckbest( Expression zweckbest, Expression besondZweckbest, String zweckbestCodeList ) {
        this.besondZweckbest = besondZweckbest;
    }

    @Override
    public TypedObjectNodeArray<PrimitiveValue> evaluate( Feature feature, FeatureCollection features ) {
        PrimitiveValue[] normalizedCodes = null;

        // XPlan 3: always use value from besondereZweckbestimmung property
        TypedObjectNodeArray<TypedObjectNode> codes = castToArray( besondZweckbest.evaluate( feature, features ) );
        if ( codes != null && codes.getElements().length > 0 ) {
            // property has maxOccurs="1", so accessing the first value is fine
            normalizedCodes = new PrimitiveValue[] { new PrimitiveValue( codes.getElements()[0].toString() ) };
        }

        if ( normalizedCodes == null ) {
            return null;
        }
        return new TypedObjectNodeArray<PrimitiveValue>( normalizedCodes );
    }
}
