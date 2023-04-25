/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.validator.geometric.inspector.flaechenschluss;

import org.deegree.commons.uom.Measure;
import org.deegree.geometry.primitive.Point;

import static de.latlon.xplan.validator.geometric.inspector.flaechenschluss.FlaechenschlussTolerance.calculateAllowedDistance;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ControlPoint {

	private final String featureGmlId;

	private final Point point;

	private boolean hasIdenticalControlPoint;

	/**
	 * @param featureGmlId the gml id of the feature this control point is part of, never
	 * <code>null</code>
	 * @param point the control point, never <code>null</code>
	 */
	public ControlPoint(String featureGmlId, Point point) {
		this(featureGmlId, point, false);
	}

	/**
	 * @param featureGmlId the gml id of the feature this control point is part of, never
	 * <code>null</code>
	 * @param point the control point, never <code>null</code>
	 * @param hasIdenticalControlPoint <code>true</code> if already an identical point was
	 * found, <code>false</code> otherwise
	 */
	public ControlPoint(String featureGmlId, Point point, boolean hasIdenticalControlPoint) {
		this.featureGmlId = featureGmlId;
		this.point = point;
		this.hasIdenticalControlPoint = hasIdenticalControlPoint;
	}

	/**
	 * Checks if the passed {@link ControlPoint} is identical with this point.
	 * @param controlPoint the control point to compare, never <code>null</code>
	 * @return <code>true</code> if this control point an identical point,
	 * <code>false</code> otherwise
	 */
	public boolean checkIfIdentical(ControlPoint controlPoint) {
		boolean isIdentical = checkIfIdentical(controlPoint.point);
		if (isIdentical) {
			this.hasIdenticalControlPoint = true;
			controlPoint.hasIdenticalControlPoint = true;
		}
		return isIdentical;
	}

	/**
	 * Checks if the passed {@link Point} is identical with this point.
	 * @param pointToCheck the point to compare, never <code>null</code>
	 * @return <code>true</code> if this control point an identical point,
	 * <code>false</code> otherwise
	 */
	public boolean checkIfIdentical(Point pointToCheck) {
		Measure allowedDistance = calculateAllowedDistance(pointToCheck.getCoordinateSystem());
		boolean isIdentical = this.point.isWithinDistance(pointToCheck, allowedDistance);
		if (isIdentical) {
			this.hasIdenticalControlPoint = true;
		}
		return isIdentical;
	}

	/**
	 * @return the gml id of the feature this control point is part of, never
	 * <code>null</code>
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
	 * @return <code>true</code> if this control point has an identical point,
	 * <code>false</code> otherwis
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
