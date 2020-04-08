package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;

import static de.latlon.xplan.manager.synthesizer.expression.Expressions.castToArray;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.toPrimitiveValue;

/**
 * Expression for normalizing internal XPlanGML 2.0 / 3.0 codes so they match their XPlan Syn counterparts.
 * <p>
 * For XPlan 3.0 features, the normalization always returns the original value (as the Syn codes follow the XPlan 3.0
 * codes). For XPlan 2.0, the code is determined by looking up the XPlan 2.0 description of the code, then finding the
 * XPlan Syn code for this description.
 * </p>
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author last edited by: $Author: schneider $
 * @version $Revision: 1028 $, $Date: 2010-02-18 15:48:22 +0100 (Do, 18 Feb 2010) $
 */
public class Xplan2CodeNormalize implements Expression {

    private final Expression exp;

    private String xplan2CodeList;

    private final String xplanSynCodeList;

    private boolean noXPlan2CodeList = false;

    public Xplan2CodeNormalize( Expression exp, String xplan2CodeList, String xplan3CodeList ) {
        this.exp = exp;
        this.xplanSynCodeList = xplan3CodeList;
        try {
            this.xplan2CodeList = xplan2CodeList;
        } catch ( IllegalArgumentException e ) {
            // no xplan2 code list available for this property
            noXPlan2CodeList = true;
        }
    }

    @Override
    public PrimitiveValue evaluate( Feature feature ) {
        String normalizedCodes = null;
        try {
            TypedObjectNodeArray<TypedObjectNode> codes = castToArray( exp.evaluate( feature ) );
            if ( codes != null ) {
                normalizedCodes = "";
                for ( TypedObjectNode o : codes.getElements() ) {
                    String code = o.toString();
                    normalizedCodes += "[" + escape( code ) + "]";
                }
                if ( codes.getElements().length == 1 ) {
                    // if there is only one code, then it does not need bracket delimiters
                    normalizedCodes = normalizedCodes.substring( 1, normalizedCodes.length() - 1 );
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            String msg = "Error performing code list lookup (" + xplan2CodeList + ") for feature '" + feature.getId()
                         + "': " + e.getMessage();
            throw new RuntimeException( msg, e );
        }
        return toPrimitiveValue( normalizedCodes );
    }

    private String escape( String desc ) {
        return desc.replace( "][", "][][" );
    }

}
