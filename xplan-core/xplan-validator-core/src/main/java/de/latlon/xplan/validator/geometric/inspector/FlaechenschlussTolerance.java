package de.latlon.xplan.validator.geometric.inspector;

import org.deegree.commons.uom.Measure;
import org.deegree.cs.components.IUnit;
import org.deegree.cs.components.Unit;
import org.deegree.cs.coordinatesystems.ICRS;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FlaechenschlussTolerance {

    public static final double ALLOWEDDISTANCE_METRE = 0.002;

    /**
     * Calculate allowed distance.
     *
     * @param coordinateSystem
     *                         ma< be <code>null</code>
     * @return never <code>null</code>
     */
    public static Measure calculateAllowedDistance( ICRS coordinateSystem ) {
        double allowedDistanceValue = calculateAllowedDistanceValue( coordinateSystem );
        return new Measure( BigDecimal.valueOf( allowedDistanceValue ), "m" );
    }

    /**
     * Calculate allowed distance.
     *
     * @param coordinateSystem
     *                         may be <code>null</code>
     * @return the allowed distance
     */
    public static double calculateAllowedDistanceValue( ICRS coordinateSystem ) {
        if ( coordinateSystem != null ) {
            IUnit[] units = coordinateSystem.getUnits();
            if ( units != null && units.length > 0 ) {
                if ( units[0].canConvert( Unit.METRE ) ) {
                    return units[0].convert( ALLOWEDDISTANCE_METRE, Unit.METRE );
                }
            }
        }
        return ALLOWEDDISTANCE_METRE;
    }

}
