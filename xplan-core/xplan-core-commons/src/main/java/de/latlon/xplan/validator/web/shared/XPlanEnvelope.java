/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.validator.web.shared;

import java.io.Serializable;

/**
 * Encapsulates a bounding box
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanEnvelope implements Serializable {

	private static final long serialVersionUID = -5291781248135795441L;

	private double minX;

	private double minY;

	private double maxX;

	private double maxY;

	private String crs;

	public XPlanEnvelope() {
	}

	public XPlanEnvelope(double minX, double minY, double maxX, double maxY, String crs) {
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
		this.crs = crs;
	}

	public double getMinX() {
		return minX;
	}

	public void setMinX(double minX) {
		this.minX = minX;
	}

	public double getMinY() {
		return minY;
	}

	public void setMinY(double minY) {
		this.minY = minY;
	}

	public double getMaxX() {
		return maxX;
	}

	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}

	public double getMaxY() {
		return maxY;
	}

	public void setMaxY(double maxY) {
		this.maxY = maxY;
	}

	public String getCrs() {
		return crs;
	}

	public void setCrs(String crs) {
		this.crs = crs;
	}

	public double getCenterX() {
		return minX + (maxX - minX);
	}

	public double getCenterY() {
		return minY + (maxY - minY);
	}

	@Override
	public String toString() {
		return "XPlanEnvelope {crs=" + crs + ", minX=" + minX + ", minY=" + minY + ", maxX=" + maxX + ", maxY=" + maxY
				+ "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crs == null) ? 0 : crs.hashCode());
		long temp;
		temp = Double.doubleToLongBits(maxX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(maxY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minX);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minY);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XPlanEnvelope other = (XPlanEnvelope) obj;
		if (crs == null) {
			if (other.crs != null)
				return false;
		}
		else if (!crs.equals(other.crs))
			return false;
		if (Double.doubleToLongBits(maxX) != Double.doubleToLongBits(other.maxX))
			return false;
		if (Double.doubleToLongBits(maxY) != Double.doubleToLongBits(other.maxY))
			return false;
		if (Double.doubleToLongBits(minX) != Double.doubleToLongBits(other.minX))
			return false;
		if (Double.doubleToLongBits(minY) != Double.doubleToLongBits(other.minY))
			return false;
		return true;
	}

}
