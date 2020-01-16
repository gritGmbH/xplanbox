package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.manager.synthesizer.expression.flatten.*;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.Reference;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.manager.synthesizer.expression.Expressions.castToArray;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.toPrimitiveValue;

/**
 * {@link Expression} that returns a "flat" textual representation for properties that have a "complex" value (
 * {@link Feature} or {@link ElementNode}).
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @see Flattener
 * @since 1.0
 */
public class XplanFlattenProperty implements Expression {

    private static final Logger LOG = LoggerFactory.getLogger( XplanFlattenProperty.class );

    private final Expression exp;

    private final List<Flattener> customFlatteners = new ArrayList<Flattener>();

    /**
     * @param exp
     *            an expression that targets a property node
     */
    public XplanFlattenProperty( Expression exp ) {
        this.exp = exp;
        customFlatteners.add( new XpBegruendungAbschnittFlattener() );
        customFlatteners.add( new XpGemeindeFlattener() );
        customFlatteners.add( new XpGenerAttributFlattener() );
        customFlatteners.add( new XpHoehenangabeFlattener() );
        customFlatteners.add( new XpRasterplanFlattener() );
        customFlatteners.add( new XpTextAbschnittFlattener() );
        customFlatteners.add( new XpVerfahrensMerkmalFlattener() );
        customFlatteners.add( new XpVerbundenerPlanFlattener() );
    }

    @Override
    public PrimitiveValue evaluate( Feature feature ) {
        String s = null;
        XpExterneReferenzFlattener extRefFlattener = new XpExterneReferenzFlattener( feature );
        try {
            TypedObjectNodeArray<TypedObjectNode> props = castToArray( exp.evaluate( feature ) );
            if ( props != null && props.getElements().length > 0 ) {
                s = "";
                for ( TypedObjectNode o : props.getElements() ) {
                    if ( !( o instanceof Property ) ) {
                        String msg = "Trying to flatten  '" + o.getClass() + "', but it can only flatten properties.";
                        throw new IllegalArgumentException( msg );
                    }
                    s += flatten( (Property) o, extRefFlattener );
                }
            }
        } catch ( Exception e ) {
            String msg = "Error flattening property '" + exp + "' of feature '" + feature.getId() + " : "
                         + e.getMessage();
            LOG.error( msg, e );
            return null;
        }
        return toPrimitiveValue( s );
    }

    private String flatten( Property prop, XpExterneReferenzFlattener extRefFlattener ) {
        TypedObjectNode value = prop.getValue();
        if ( value instanceof ElementNode ) {
            try {
                value = getFirstChild( (ElementNode) value );
            } catch ( Exception e ) {
                return new DefaultFlattener().flatten( value );
            }
        } else if ( value instanceof Feature ) {
            Feature subFeature = (Feature) value;
            if ( subFeature instanceof Reference && ( (Reference<?>) subFeature ).isResolved() ) {
                value = ( (Reference<?>) subFeature ).getReferencedObject();
            } else {
                return flatten( ( (Reference<?>) value ) );
            }
        } else if ( value != null ) {
            LOG.error( "Only feature- or element-valued properties can be flattened. " );
            throw new IllegalArgumentException();
        }
        if ( value == null ) {
            return "";
        }
        for ( Flattener flattener : customFlatteners ) {
            if ( flattener.accepts( value ) ) {
                return flattener.flatten( value );
            }
        }
        if ( extRefFlattener.accepts( value ) ) {
            return extRefFlattener.flatten( value );
        }
        return new DefaultFlattener().flatten( value );
    }

    private TypedObjectNode getFirstChild( ElementNode elNode ) {
        if ( elNode.getChildren().size() > 0 && elNode.getChildren().get( 0 ) instanceof ElementNode ) {
            return elNode.getChildren().get( 0 );
        }
        throw new IllegalArgumentException();
    }

    private String flatten( Reference<?> externalRef ) {
        return "[" + escape( externalRef.getURI() ) + "]";
    }

    private String escape( String desc ) {
        String result = desc;
        if ( result.startsWith( "[" ) && result.endsWith( "]" ) ) {
            result = result.substring( 1, result.length() - 1 );
        }
        result = result.replace( "][", "][][" );
        return result;
    }
}
