/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared.edit;

import java.io.Serializable;

import javax.validation.Valid;

/**
 * Encapsulates a reference of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class Reference extends AbstractReference implements Serializable {

	@Valid
	private ReferenceType type;

	public Reference() {
	}

	/**
	 * @param reference reference, may be <code>null</code>
	 * @param geoReference geoReference, may be <code>null</code>
	 * @param type type, may be <code>null</code>
	 */
	public Reference(String reference, String geoReference, ReferenceType type) {
		super(reference, geoReference);
		this.type = type;
	}

	/**
	 * @return the type, may be <code>null</code>
	 */
	public ReferenceType getType() {
		return type;
	}

	/**
	 * @param type the type to set, may be <code>null</code>
	 */
	public void setType(ReferenceType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reference {type=" + type + ", " + super.toString() + "}";
	}

}
