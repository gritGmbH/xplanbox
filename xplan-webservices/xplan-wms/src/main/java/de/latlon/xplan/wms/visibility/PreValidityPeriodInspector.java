/*-
 * #%L
 * xplan-wms - deegree XPlan WebMapService
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
package de.latlon.xplan.wms.visibility;

/**
 * A {@link ValidityPeriodInspector} for schema 'xplansynpre'
 *
 * @deprecated The VisibilityInspector will be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Deprecated
public class PreValidityPeriodInspector extends ValidityPeriodInspector {

	/**
	 * Instantiates the super class wth schema 'xplansynpre'
	 */
	public PreValidityPeriodInspector() {
		super("xplansynpre");
	}

}
