package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_2;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;
import static de.latlon.xplan.manager.codelists.XPlanCodeConverter.xplan2ToSynCode;
import static de.latlon.xplan.manager.codelists.XPlanCodeListsFactory.getXPlanSyn;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.castToArray;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.toPrimitiveValue;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.commons.utils.Pair;
import org.deegree.feature.Feature;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.codelists.XPlanCodeConverter;

/**
 * {@link Expression} for translating codes from internal XPlan2 codelists to their XPlan3/XPlanSyn counterpart.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @since 1.0
 */
public class Xplan2CodeLookup implements Expression {

    private final Expression exp;

    private boolean noXPlan2CodeList = false;

    private String xplan2CodeList;

    private final String xplanSynCodeList;

    public Xplan2CodeLookup( Expression exp, String xplan2CodeList, String xplan3CodeList ) {
        this.exp = exp;
        this.xplanSynCodeList = xplan3CodeList;
        try {
            XPlanCodeConverter.xplan2ToSynCodeList( xplan2CodeList );
            this.xplan2CodeList = xplan2CodeList;
        } catch ( IllegalArgumentException e ) {
            // no xplan2 code list available for this property
            noXPlan2CodeList = true;
        }
    }

    @Override
    public PrimitiveValue evaluate( Feature feature ) {
        String descriptions = null;
        XPlanVersion version = determineBaseVersion( feature.getName() );
        try {
            TypedObjectNodeArray<TypedObjectNode> codes = castToArray( exp.evaluate( feature ) );
            if ( codes != null ) {
                descriptions = "";
                for ( TypedObjectNode o : codes.getElements() ) {
                    String code = o.toString();
                    String desc;
                    if ( version == XPLAN_2 ) {
                        Pair<String, String> codeAndDesc = getXPlanSynCodeAndDesc( code );
                        desc = codeAndDesc.second;
                    } else if ( version == XPLAN_3 ) {
                        desc = getXPlanSyn().getDescription( xplanSynCodeList, code );
                    } else {
                        // TODO lookup xplanSynCodeList
                        desc = code;
                    }
                    descriptions += "[" + escape( desc ) + "]";
                }
                if ( codes.getElements().length == 1 ) {
                    // if there is only one code, then it does not need bracket delimiters
                    descriptions = descriptions.substring( 1, descriptions.length() - 1 );
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            String msg = "Error performing code list lookup (" + xplan2CodeList + ") for feature '" + feature.getId()
                         + "': " + e.getMessage();
            throw new RuntimeException( msg, e );
        }
        return toPrimitiveValue( descriptions );
    }

    private String escape( String desc ) {
        return desc.replace( "][", "][][" );
    }

    /**
     * Retrieves the XPlan Syn code and description for the given XPlan 2 code.
     *
     * @param xplan2Code
     * @return
     */
    private Pair<String, String> getXPlanSynCodeAndDesc( String xplan2Code ) {

        String xplanSynCode;
        String xplanSynDesc;

        if ( noXPlan2CodeList ) {
            // property is string-valued in XPlan 2
            xplanSynCode = "9999";
            xplanSynDesc = "Sonstiges";
        } else {
            // property has an XPlan 2 code list
            xplanSynCode = xplan2ToSynCode( xplan2Code.toString(), xplan2CodeList ).getXplanSynCode();
            xplanSynDesc = getXPlanSyn().getDescription( xplanSynCodeList, xplanSynCode );
        }
        return new Pair<String, String>( xplanSynCode, xplanSynDesc );
    }
}
