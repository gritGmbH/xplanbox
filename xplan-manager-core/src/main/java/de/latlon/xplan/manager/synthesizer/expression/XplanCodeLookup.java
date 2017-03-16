package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.castToArray;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.toPrimitiveValue;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.codelists.XPlanCodeListsFactory;

/**
 * {@link Expression} for translating codes from internal codelists (aka key enumerations) to their textual
 * representation.
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public class XplanCodeLookup implements Expression {

    private static final Logger LOG = LoggerFactory.getLogger( XplanFlattenProperty.class );

    private final Expression exp;

    private final String codeListName;

    public XplanCodeLookup( Expression exp, String codeListName ) {
        this.exp = exp;
        this.codeListName = codeListName;
    }

    @Override
    public PrimitiveValue evaluate( Feature feature ) {
        String translation = null;
        XPlanVersion version = determineBaseVersion( feature.getName() );
        try {
            TypedObjectNodeArray<TypedObjectNode> codes = castToArray( exp.evaluate( feature ) );
            if ( codes != null ) {
                translation = "";
                for ( TypedObjectNode o : codes.getElements() ) {
                    String code = o.toString();
                    String desc = XPlanCodeListsFactory.get( version ).getDescription( codeListName, code );
                    translation += "[" + escape( desc ) + "]";
                }
                if ( codes.getElements().length == 1 ) {
                    translation = translation.substring( 1, translation.length() - 1 );
                }
            }
        } catch ( Exception e ) {
            String msg = "Error performing code translation lookup for feature '" + feature.getId() + "': "
                         + e.getMessage();
            LOG.warn( msg );
            translation = "";
        }
        return toPrimitiveValue( translation );
    }

    private String escape( String desc ) {
        return desc.replace( "][", "][][" );
    }
}
