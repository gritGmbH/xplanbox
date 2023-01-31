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
	 * @param coordinateSystem ma< be <code>null</code>
	 * @return never <code>null</code>
	 */
	public static Measure calculateAllowedDistance(ICRS coordinateSystem) {
		double allowedDistanceValue = calculateAllowedDistanceValue(coordinateSystem);
		return new Measure(BigDecimal.valueOf(allowedDistanceValue), "m");
	}

	/**
	 * Calculate allowed distance.
	 * @param coordinateSystem may be <code>null</code>
	 * @return the allowed distance
	 */
	public static double calculateAllowedDistanceValue(ICRS coordinateSystem) {
		if (coordinateSystem != null) {
			IUnit[] units = coordinateSystem.getUnits();
			if (units != null && units.length > 0) {
				if (units[0].canConvert(Unit.METRE)) {
					return units[0].convert(ALLOWEDDISTANCE_METRE, Unit.METRE);
				}
			}
		}
		return ALLOWEDDISTANCE_METRE;
	}

}
