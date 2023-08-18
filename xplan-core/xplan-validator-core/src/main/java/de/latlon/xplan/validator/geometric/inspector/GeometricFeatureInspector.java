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
package de.latlon.xplan.validator.geometric.inspector;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.gml.feature.FeatureInspector;

import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface GeometricFeatureInspector extends FeatureInspector {

	/**
	 * @return <code>true</code> if no errors was found, <code>false</code> otherwise
	 */
	boolean checkGeometricRule();

	/**
	 * @return all errors found, max be empty but never <code>null</code>
	 */
	List<String> getErrors();

	/**
	 * @return all warnings found, max be empty but never <code>null</code>
	 */
	List<String> getWarnings();

	/**
	 * @return the BadGeometries, max be empty but never <code>null</code>
	 */
	List<BadGeometry> getBadGeometries();

	/**
	 * @param version to be checked, never <code>null</code>
	 * @return <code>true</code> if the <link>FeatureInspector</link> should be applied
	 * for XPlan in the passed version, <code>false</code> otherwise
	 */
	boolean applicableForVersion(XPlanVersion version);

}
