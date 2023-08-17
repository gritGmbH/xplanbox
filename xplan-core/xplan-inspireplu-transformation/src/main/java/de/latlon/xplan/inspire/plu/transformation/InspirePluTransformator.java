/*-
 * #%L
 * xplan-inspireplu-transformation - Transformation XPlanGML nach INSPIRE PLU
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
package de.latlon.xplan.inspire.plu.transformation;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.hale.TransformationException;

import java.nio.file.Path;

/**
 * Transformation from XPlan GML to INSPIRE PLU
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface InspirePluTransformator {

	/**
	 * Transforms the passed XPlan GML to INSPIRE PLU
	 * @param xPlanGml the XPlan GML document to transform. never <code>null</code>
	 * @param xPlanVersion the Version of the XPlan GML to transform. never
	 * <code>null</code>
	 * @return the transformed document, never <code>null</code>
	 * @throws TransformationException if the transformation failed
	 */
	Path transformToPlu(Path xPlanGml, XPlanVersion xPlanVersion) throws TransformationException;

}
