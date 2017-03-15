package de.latlon.xplan.manager.synthesizer.expression.flatten;

import java.util.List;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.types.property.SimplePropertyType;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import de.latlon.xplan.manager.codelists.XPlanCodeLists;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;

public class XpHoehenangabeFlattener extends AbstractFlattener {

    private final XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

    @Override
    public boolean accepts( TypedObjectNode node ) {
        String elName = null;
        if ( node instanceof Feature ) {
            elName = ( (Feature) node ).getName().getLocalPart();
        }
        if ( node instanceof ElementNode ) {
            elName = ( (ElementNode) node ).getName().getLocalPart();
        }
        return "XP_Hoehenangabe".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode xpHoehenangabe ) {
        if ( xpHoehenangabe instanceof Feature ) {
            Feature feature = (Feature) xpHoehenangabe;
            String ftName = feature.getName().getLocalPart();
            String s = "[";
            List<Property> props = feature.getProperties();
            for ( Property prop : props ) {
                if ( prop.getType() instanceof SimplePropertyType ) {
                    String propLocal = prop.getName().getLocalPart();
                    TypedObjectNode value = xPlanSynthesizer.getRules().get( ftName + "/" + propLocal ).evaluate( feature );
                    s += concatenateValues( propLocal, asString( value ) ) + ";";
                }
            }
            s += "]";
            return s;
        }
        if ( xpHoehenangabe instanceof ElementNode ) {
            ElementNode elNode = (ElementNode) xpHoehenangabe;
            String s = "[";
            for ( TypedObjectNode child : elNode.getChildren() ) {
                if ( child instanceof ElementNode ) {
                    ElementNode childEl = (ElementNode) child;
                    String propName = ( (ElementNode) child ).getName().getLocalPart();
                    if ( childEl.getChildren().size() == 1 ) {
                        String value = translateIfNecessary( elNode, childEl );
                        s += concatenateValues( propName, value ) + ";";
                    }
                }
            }
            s += "]";
            return s;
        }
        return "";
    }

    private String translateIfNecessary( ElementNode elNode, ElementNode childEl ) {
        TypedObjectNode value = childEl.getChildren().get( 0 );
        String valueAsString = asString( value );
        String codeListName = null;
        String elementName = childEl.getName().getLocalPart();
        if ( "hoehenbezug".equals( elementName ) ) {
            codeListName = "XP_ArtHoehenbezug";
        } else if ( "bezugspunkt".equals( elementName ) ) {
            codeListName = "XP_ArtHoehenbezugspunkt";
        }
        if ( codeListName != null ) {
            XPlanVersion version = XPlanVersionUtils.determineBaseVersion( elNode.getName() );
            return XPlanCodeLists.get( version ).getDescription( codeListName, valueAsString );
        }
        return valueAsString;
    }

    private String concatenateValues( String propName, String value ) {
        return propName + "=" + value;
    }

    private String asString( TypedObjectNode value ) {
        if ( value instanceof Property ) {
            value = ( (Property) value ).getValue();
        }
        if ( value instanceof PrimitiveValue ) {
            return value.toString();
        }
        return "";
    }

}
