package de.latlon.xplan.validator.geometric.inspector;

import org.deegree.commons.uom.Measure;
import org.deegree.cs.components.IUnit;
import org.deegree.cs.components.Unit;
import org.deegree.geometry.primitive.Point;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ControlPoint {

    private static final double ALLOWEDDISTANCE_METRE = 0.002;

    private final String featureGmlId;

    private final Point point;

    private boolean hasIdenticalControlPoint;

    /**
     * @param featureGmlId
     *                         the gml id of the feature this control point is part of, never <code>null</code>
     * @param point
     *                         the control point, never <code>null</code>
     * @param hasIdenticalControlPoint
     *                         <code>true</code> if already an identical point was found, <code>false</code> otherwise
     */
    public ControlPoint( String featureGmlId, Point point, boolean hasIdenticalControlPoint ) {
        this.featureGmlId = featureGmlId;
        this.point = point;
        this.hasIdenticalControlPoint = hasIdenticalControlPoint;
    }

    /**
     * Checks if the passed {@link ControlPoint} is identical with this point.
     *
     * @param controlPoint
     *                         the control point to compare, never <code>null</code>
     * @return <code>true</code> if this control point an identical point, <code>false</code> otherwise
     */
    public boolean checkIfIdentical( ControlPoint controlPoint ) {
        boolean isIdentical = checkIfIdentical( controlPoint.point );
        if ( isIdentical ) {
            this.hasIdenticalControlPoint = true;
            controlPoint.hasIdenticalControlPoint = true;
        }
        return isIdentical;
    }

    /**
     * Checks if the passed {@link Point} is identical with this point.
     *
     * @param pointToCheck
     *                         the point to compare, never <code>null</code>
     * @return <code>true</code> if this control point an identical point, <code>false</code> otherwise
     */
    public boolean checkIfIdentical( Point pointToCheck ) {
        Measure allowedDistance = calculateAllowedDistance( pointToCheck );
        boolean isIdentical = this.point.isWithinDistance( pointToCheck, allowedDistance );
        if ( isIdentical ) {
            this.hasIdenticalControlPoint = true;
        }
        return isIdentical;
    }

    private Measure calculateAllowedDistance( Point pointToCheck ) {
        double allowedDistanceValue = calculateAllowedDistanceValue( pointToCheck );
        return new Measure( BigDecimal.valueOf( allowedDistanceValue ), "m" );
    }

    private double calculateAllowedDistanceValue( Point pointToCheck ) {
        if ( pointToCheck.getCoordinateSystem() != null ) {
            IUnit[] units = pointToCheck.getCoordinateSystem().getUnits();
            if ( units != null && units.length > 0 ) {
                if ( units[0].canConvert( Unit.METRE ) ) {
                    return units[0].convert( ALLOWEDDISTANCE_METRE, Unit.METRE );
                }
            }

        }
        return ALLOWEDDISTANCE_METRE;
    }

    /**
     * @return the gml id of the feature this control point is part of, never <code>null</code>
     */
    public String getFeatureGmlId() {
        return featureGmlId;
    }

    /**
     * @return the control point, never <code>null</code>
     */
    public Point getPoint() {
        return point;
    }

    /**
     * @return <code>true</code> if this control point has an identical point, <code>false</code> otherwis
     */
    public boolean hasIdenticalControlPoint() {
        return hasIdenticalControlPoint;
    }

    @Override
    public String toString() {
        return "ControlPoint{" + "featureGmlId='" + featureGmlId + '\'' + ", point=" + point
               + ", hasIdenticalControlPoint=" + hasIdenticalControlPoint + '}';
    }
}