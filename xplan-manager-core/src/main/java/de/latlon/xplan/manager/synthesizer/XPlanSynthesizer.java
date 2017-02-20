package de.latlon.xplan.manager.synthesizer;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.gml.property.PropertyType;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.GenericFeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.types.AppSchema;
import org.deegree.feature.types.FeatureType;

import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.synthesizer.expression.Expression;

/**
 * Transforms an {@link XPlanFeatureCollection} (with XPlan 2/3/4.0/4.1 features) into a {@link FeatureCollection} that
 * contains flat XPlanSyn features.
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanSynthesizer {

    private final static String SYN_NS = XPLAN_SYN.getNamespace();

    public static AppSchema synSchema;

    public static Map<String, Expression> rules = new HashMap<String, Expression>();

    static {
        try {
            synSchema = XPlanSchemas.getInstance().getAppSchema( XPLAN_SYN, null );
        } catch ( Exception e ) {
            e.printStackTrace();
            throw new RuntimeException( e.getMessage() );
        }
    }

    private static void processRuleFile( XPlanVersion version, String xplanType, String xplanName ) {
        rules.clear();
        String rulesResource = "/rules/";
        switch ( version ) {
        case XPLAN_2:
            rulesResource += "xplan2.syn";
            break;
        case XPLAN_3:
            rulesResource += "xplan3.syn";
            break;
        case XPLAN_40:
            rulesResource += "xplan40.syn";
            break;
        case XPLAN_41:
            rulesResource += "xplan41.syn";
            break;
        default:
            throw new IllegalArgumentException();
        }
        try ( InputStream is = XPlanSynthesizer.class.getResourceAsStream( rulesResource );
              InputStreamReader in = new InputStreamReader( is );
              BufferedReader reader = new BufferedReader( in ) ) {
            String line;
            RuleParser parser = new RuleParser( xplanType, xplanName );
            while ( ( line = reader.readLine() ) != null ) {
                int firstEquals = line.indexOf( "=" );
                rules.put( line.substring( 0, firstEquals ), parser.parse( line.substring( firstEquals + 1 ) ) );
            }
        } catch ( IOException e ) {
            String msg = "Error while reading the rules file " + rulesResource;
            throw new RuntimeException( msg, e );
        }
    }

    /**
     * @param version
     * @param xplanFc
     * @return
     */
    public static FeatureCollection synthesize( XPlanVersion version, XPlanFeatureCollection xplanFc ) {

        XPlanType xplanType = xplanFc.getType();
        String xplanName = xplanFc.getPlanName();
        FeatureCollection fc = xplanFc.getFeatures();

        processRuleFile( version, xplanType.name(), xplanName );

        // initialize lookup of XP_TextAbschnitt and XP_BegruendungAbschnitt features
        XplanAbschnittLookup.init( fc );
        // initialize lookup for all Fachobjekte that are referenced by XP_PPO features
        XpPpoLookup.init( fc );

        List<Feature> featureMembers = new ArrayList<Feature>();
        for ( Feature feature : fc ) {
            Feature synFeature = synthesize( feature );
            featureMembers.add( synFeature );
        }

        FeatureCollection newFc = new GenericFeatureCollection( fc.getId(), featureMembers );
        return newFc;
    }

    private static Feature synthesize( Feature feature ) {
        List<Property> newProps = new ArrayList<Property>();
        QName synFeatureName = new QName( SYN_NS, feature.getType().getName().getLocalPart() );

        if ( synSchema.getFeatureType( synFeatureName ) == null ) {
            String msg = "Interner Fehler. Das XPlanSyn Schema definiert keinen Feature Type mit Namen '"
                         + synFeatureName + "'.";
            throw new RuntimeException( msg );
        }
        List<PropertyType> propTypes = synSchema.getFeatureType( synFeatureName ).getPropertyDeclarations();
        for ( PropertyType propType : propTypes ) {
            // the rule keys are specified in "<featureName>/<propName>" format
            String key = feature.getName().getLocalPart() + "/" + propType.getName().getLocalPart();

            if ( rules.containsKey( key ) ) {
                TypedObjectNode newPropValue = rules.get( key ).evaluate( feature );

                if ( newPropValue == null ) {
                    continue;
                }

                if ( newPropValue instanceof Property ) {
                    newPropValue = ( (Property) newPropValue ).getValue();
                }

                if ( newPropValue instanceof TypedObjectNodeArray<?> ) {
                    newPropValue = toString( ( (TypedObjectNodeArray<?>) newPropValue ) );
                }

                if ( newPropValue instanceof GenericXMLElement ) {
                    newPropValue = new PrimitiveValue( newPropValue.getClass() + "" );
                }

                Property newProp = new GenericProperty( propType, newPropValue );
                // some (still-to-be-improved) corrections...
                // if ( propType instanceof SimplePropertyType ) {
                // SimplePropertyType simplePropType = (SimplePropertyType) propType;
                // if ( newPropValue instanceof BigInteger
                // && DECIMAL.equals( simplePropType.getPrimitiveType().getBaseType() ) ) {
                // BigInteger bigInt = (BigInteger) newPropValue;
                //
                // newProp = new GenericProperty( propType, new BigDecimal( bigInt ) );
                // } else {
                // newProp = new GenericProperty( propType, newPropValue );
                // }
                // } else {
                // newProp = new GenericProperty( propType, newPropValue );
                // }

                newProps.add( newProp );

            } else if ( propType.getMinOccurs() != 0 ) {
                throw new RuntimeException( "Interner Fehler. Die Regeldatei enthält keine Regel für " + key + "." );
            }
        }
        FeatureType synType = synSchema.getFeatureType( new QName( SYN_NS, feature.getName().getLocalPart() ) );
        return synType.newFeature( feature.getId(), newProps, null );
    }

    private static PrimitiveValue toString( TypedObjectNodeArray<?> array ) {
        StringBuilder sBuilder = new StringBuilder();
        for ( TypedObjectNode n : array.getElements() ) {
            sBuilder.append( n );
        }
        return new PrimitiveValue( sBuilder.toString() );
    }

}