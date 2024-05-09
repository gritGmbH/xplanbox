/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.manager.transformation;

import de.latlon.xplan.commons.XPlanVersion;

/**
 * Encapsulates the result of a transformation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TransformationResult {

	private final byte[] transformationResult;

	private final XPlanVersion versionOfTheResult;

	/**
	 * @param transformationResult the transformed XPlanGML, never <code>null</code>
	 * @param versionOfTheResult the version of the transformed XPlanGML, never
	 * <code>null</code>
	 */
	public TransformationResult(byte[] transformationResult, XPlanVersion versionOfTheResult) {
		this.transformationResult = transformationResult;
		this.versionOfTheResult = versionOfTheResult;
	}

	/**
	 * @return the transformed XPlanGML, never <code>null</code>
	 */
	public byte[] getTransformationResult() {
		return transformationResult;
	}

	/**
	 * @return the version of the transformed XPlanGML, never <code>null</code>
	 */
	public XPlanVersion getVersionOfTheResult() {
		return versionOfTheResult;
	}

}
