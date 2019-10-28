package de.latlon.xplan.validator.geometric;

import org.deegree.commons.uom.Measure;
import org.deegree.geometry.primitive.Point;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ControlPoint {

    private static final Measure ALLOWEDDISTANCE = new Measure( "2", "mm" );

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
        boolean isIdentical = this.point.isWithinDistance( pointToCheck, ALLOWEDDISTANCE );
        if ( isIdentical ) {
            this.hasIdenticalControlPoint = true;
        }
        return isIdentical;
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