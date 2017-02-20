package de.latlon.xplan.manager.synthesizer;

import java.util.ArrayList;
import java.util.List;

import de.latlon.xplan.manager.synthesizer.expression.Expression;
import de.latlon.xplan.manager.synthesizer.expression.StringConstant;
import de.latlon.xplan.manager.synthesizer.expression.XPlanBesondZweckbest;
import de.latlon.xplan.manager.synthesizer.expression.XPlanBesondZweckbestLookup;
import de.latlon.xplan.manager.synthesizer.expression.XPlanCodeNormalizeExt;
import de.latlon.xplan.manager.synthesizer.expression.XPlanGeometry;
import de.latlon.xplan.manager.synthesizer.expression.XPlanGmlDescription;
import de.latlon.xplan.manager.synthesizer.expression.XPlanName;
import de.latlon.xplan.manager.synthesizer.expression.XPlanType;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.Xplan2CodeLookup;
import de.latlon.xplan.manager.synthesizer.expression.Xplan2CodeLookupExt;
import de.latlon.xplan.manager.synthesizer.expression.Xplan2CodeNormalize;
import de.latlon.xplan.manager.synthesizer.expression.XplanBaugebietFlaechenteile;
import de.latlon.xplan.manager.synthesizer.expression.XplanBegruendungAbschnitte;
import de.latlon.xplan.manager.synthesizer.expression.XplanCodeLookup;
import de.latlon.xplan.manager.synthesizer.expression.XplanCodeLookupExt;
import de.latlon.xplan.manager.synthesizer.expression.XplanFlattenProperty;
import de.latlon.xplan.manager.synthesizer.expression.XplanGmlName;
import de.latlon.xplan.manager.synthesizer.expression.XplanRefTextAbschnitte;
import de.latlon.xplan.manager.synthesizer.expression.XplanSymbolPositions;
import de.latlon.xplan.manager.synthesizer.expression.XplanTextAbschnitte;

/**
 * The <code>RuleParser</code> class parses the Syn rules into corresponding objects. These will be used for evalution
 * in the context of a feature.
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author last edited by: $Author: ionita $
 * @version $Revision: 1242 $, $Date: 2010-05-25 20:15:58 +0200 (Di, 25 Mai 2010) $
 */
class RuleParser {

    private String xplanType;

    private String xplanName;

    /**
     * @param xplanType
     *            the type of xplan document. See {@link XPlanType}
     * @param xplanName
     *            the name of xplan document, i.e. the name of the XP_Plan-descendant feature in the document
     */
    public RuleParser( String xplanType, String xplanName ) {
        this.xplanType = xplanType;
        this.xplanName = xplanName;
    }

    private String trimString( String s ) {
        String result = s.trim();
        if ( result.startsWith( "'" ) && result.endsWith( "'" ) ) {
            result = result.substring( 1, result.length() - 1 );
        }
        return result;
    }

    private Xpath parseXPath( List<String> args ) {
        return new Xpath( trimString( args.get( 0 ) ) );
    }

    private Expression parseXPlanFlattenFeature( List<String> args ) {
        return new XplanFlattenProperty( parse( args.get( 0 ) ) );
    }

    private Expression parseXPlanCodeNormalize( List<String> args ) {
        return new Xplan2CodeNormalize( parse( args.get( 0 ) ), trimString( args.get( 1 ) ), trimString( args.get( 2 ) ) );
    }

    private Expression parseXPlanCodeNormalizeExt( List<String> args ) {
        return new XPlanCodeNormalizeExt( parse( args.get( 0 ) ), trimString( args.get( 1 ) ),
                        trimString( args.get( 2 ) ) );
    }

    private Expression parseXPlanCodeLookup( List<String> args ) {
        return new XplanCodeLookup( parse( args.get( 0 ) ), trimString( args.get( 1 ) ) );
    }

    private Expression parseXPlanCodeLookupExt( List<String> args ) {
        return new XplanCodeLookupExt( parse( args.get( 0 ) ), trimString( args.get( 1 ) ) );
    }

    private Expression parseXPlan2CodeLookup( List<String> args ) {
        return new Xplan2CodeLookup( parse( args.get( 0 ) ), trimString( args.get( 1 ) ), trimString( args.get( 2 ) ) );
    }

    private Expression parseXPlan2CodeLookupExt( List<String> args ) {
        return new Xplan2CodeLookupExt( parse( args.get( 0 ) ), trimString( args.get( 1 ) ), trimString( args.get( 2 ) ) );
    }

    private Expression parseXPlanTextSchluessel( List<String> args ) {
        return new XplanTextAbschnitte();
    }

    private Expression parseXPlanTextSchluesselBegruendung( List<String> args ) {
        return new XplanBegruendungAbschnitte();
    }

    private Expression parseXPlanBesondZweckbestLookup( List<String> args ) {
        return new XPlanBesondZweckbestLookup( parse( args.get( 0 ) ), parse( args.get( 1 ) ),
                        trimString( args.get( 2 ) ), trimString( args.get( 3 ) ) );
    }

    private Expression parseXPlanBesondZweckbest( List<String> args ) {
        return new XPlanBesondZweckbest( parse( args.get( 0 ) ), parse( args.get( 1 ) ), trimString( args.get( 2 ) ) );
    }

    private Expression parseXPlanGeometry( List<String> args ) {
        return new XPlanGeometry( parseXPath( args ) );
    }

    private Expression parseFunction( String functionName, List<String> args ) {
        Expression result;
        switch ( functionName ) {
        case "xpath":
            result = parseXPath( args );
            break;
        case "xplanGmlName":
            result = new XplanGmlName();
            break;
        case "xplanGmlDescription":
            result = new XPlanGmlDescription();
            break;
        case "xplanFlattenFeature":
            result = parseXPlanFlattenFeature( args );
            break;
        case "xplanCodeLookup":
            result = parseXPlanCodeLookup( args );
            break;
        case "xplanCodeLookupExt":
            result = parseXPlanCodeLookupExt( args );
            break;
        case "xplan2CodeNormalize":
            result = parseXPlanCodeNormalize( args );
            break;
        case "xplanCodeNormalizeExt":
            result = parseXPlanCodeNormalizeExt( args );
            break;
        case "xplan2CodeLookup":
            result = parseXPlan2CodeLookup( args );
            break;
        case "xplan2CodeLookupExt":
            result = parseXPlan2CodeLookupExt( args );
            break;
        case "xplanRefTextAbschnitte":
            result = new XplanRefTextAbschnitte();
            break;
        case "xplanTextAbschnitte":
            result = parseXPlanTextSchluessel( args );
            break;
        case "xplanBegruendungAbschnitte":
            result = parseXPlanTextSchluesselBegruendung( args );
            break;
        case "xplanBesondZweckbest":
            result = parseXPlanBesondZweckbest( args );
            break;
        case "xplanBesondZweckbestLookup":
            result = parseXPlanBesondZweckbestLookup( args );
            break;
        case "xplanGeometry":
            result = parseXPlanGeometry( args );
            break;
        case "xplanAggregatePPOPosition":
            result = parseXPlanAggregatePPOPosition();
            break;
        case "xplanAggregateFlaechenteil":
            result = parseXPlanAggregateFlaechenteil();
            break;
        case "xplanType":
            result = new XPlanType( xplanType );
            break;
        case "xplanName":
            result = new XPlanName( xplanName );
            break;
        case "xplanFlatten":
            result = parseXPlanFlattenFeature( args );
            break;
        default:
            throw new RuntimeException( String.format( "Expression %s is not expected.", functionName ) );
        }
        return result;
    }

    private Expression parseXPlanAggregateFlaechenteil() {
        return new XplanBaugebietFlaechenteile();
    }

    private Expression parseXPlanAggregatePPOPosition() {
        return new XplanSymbolPositions();
    }

    /**
     * @param expr
     * @return
     */
    public Expression parse( String expr ) {
        int firstP = expr.indexOf( "(" );
        int firstQ = expr.indexOf( "\'" );
        if ( firstP == -1 || ( firstQ != -1 && firstQ < firstP ) ) {
            // if there are no parantheses or the paranthesis should be taken as text
            return new StringConstant( trimString( expr ) );
        }
        String functionName = expr.substring( 0, firstP );
        List<String> args = new ArrayList<String>();
        int cursor = firstP + 1;
        int parantBalance = 1;
        StringBuilder currentArg = new StringBuilder();
        boolean notValidSeparator = false; // commas that are strings parts of the concat function should not be
        // treated as separators
        while ( parantBalance > 0 ) {
            if ( cursor >= expr.length() ) {
                throw new RuntimeException( "cursor surpassed length of expr: " + expr );
            }
            if ( expr.charAt( cursor ) == '\'' ) {
                notValidSeparator = !notValidSeparator;
            }
            if ( !notValidSeparator && expr.charAt( cursor ) == ',' && parantBalance == 1 ) {
                args.add( currentArg.toString() );
                currentArg = new StringBuilder();
            } else if ( !notValidSeparator && expr.charAt( cursor ) == '(' ) {
                currentArg.append( expr.charAt( cursor ) );
                parantBalance++;
            } else if ( !notValidSeparator && expr.charAt( cursor ) == ')' ) {
                parantBalance--;
                if ( parantBalance != 0 ) {
                    currentArg.append( expr.charAt( cursor ) );
                }
            } else {
                currentArg.append( expr.charAt( cursor ) );
            }
            cursor++;
        }
        args.add( currentArg.toString() );
        return parseFunction( functionName.trim(), args );
    }

}