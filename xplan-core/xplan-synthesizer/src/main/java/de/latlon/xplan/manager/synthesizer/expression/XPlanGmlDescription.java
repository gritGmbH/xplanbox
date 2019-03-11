package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;

import javax.xml.namespace.QName;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.gml.GMLVersion;

import de.latlon.xplan.commons.synthesizer.Features;

/**
 * Returns a textual representation of the all <code>gml:description</code> properties of the features.
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public class XPlanGmlDescription implements Expression {

    /**
     * Returns a string representation of the 'gml:description' property of the given feature.
     * 
     * @return value of the 'gml:description' property of the given feature or <code>null</code> if it doesn't have this
     *         property
     */
    @Override
    public PrimitiveValue evaluate( Feature feature ) {
        String s = null;
        QName propName = getGmlDescriptionQName( feature );
        TypedObjectNode propValue = Features.getPropertyValue( feature, propName );
        if ( propValue != null ) {
            // due to the restrictions of gml3nas.xsd, the description can only be a string (and not a reference as
            // allowed by GML 3.0+)
            s = propValue.toString();
        }
        if ( s == null ) {
            return null;
        }
        return new PrimitiveValue( s );
    }

    private QName getGmlDescriptionQName( Feature feature ) {
        GMLVersion version = determineBaseVersion( feature.getName() ).getGmlVersion();
        return new QName( version.getNamespace(), "description" );
    }
}