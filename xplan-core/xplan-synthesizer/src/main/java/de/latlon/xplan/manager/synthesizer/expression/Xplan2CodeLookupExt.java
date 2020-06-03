package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.manager.synthesizer.expression.Expressions.toPrimitiveValue;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.manager.codelists.XPlanCodeLists;
import de.latlon.xplan.manager.codelists.XPlanCodeListsFactory;

/**
 * Expression for retrieving the textual description for an external XPlan 2.0 / 3.0 code.
 * 
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @author last edited by: $Author: schneider $
 * 
 * @version $Revision: 1074 $, $Date: 2010-02-19 17:07:26 +0100 (Fr, 19 Feb 2010) $
 */
public class Xplan2CodeLookupExt implements Expression {

    private static final Logger LOG = LoggerFactory.getLogger( Xplan2CodeLookupExt.class );

    private final Expression exp;

    private final String xplan3CodeList;

    public Xplan2CodeLookupExt( Expression exp, String xplan2CodeList, String xplan3CodeList ) {
        this.exp = exp;
        this.xplan3CodeList = xplan3CodeList;
    }

    @Override
    public PrimitiveValue evaluate( Feature feature, FeatureCollection features ) {
        String descriptions = null;
        TypedObjectNodeArray<TypedObjectNode> codes = Expressions.castToArray( exp.evaluate( feature, features ) );
        if ( codes != null ) {
            if ( codes.getElements().length > 1 ) {
                LOG.warn( "More than one value for " + this );
            }
            for ( TypedObjectNode code : codes.getElements() ) {
                XPlanCodeLists synExt = XPlanCodeListsFactory.getXPlanSynExt();
                try {
                    descriptions = synExt.getDescription( xplan3CodeList, code.toString() );
                } catch ( Exception e ) {
                    descriptions = "" + code;
                    String msg = "Keine Beschreibung für externen Code '" + code.toString() + "' (CodeList "
                                 + xplan3CodeList + ") gefunden. Verwende Code als Beschreibung.";
                    LOG.debug( msg );
                }
            }
        }
        return toPrimitiveValue( descriptions );
    }
}
