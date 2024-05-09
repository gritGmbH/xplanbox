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
package de.latlon.xplan.validator.web.shared;

import java.io.Serializable;

/**
 * Encapsulates a validation option
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ValidationOption implements Serializable {

	private static final long serialVersionUID = -685904060094230918L;

	private String name;

	private String argument;

	public ValidationOption() {
	}

	/**
	 * Instantiates a {@link ValidationOption} without argument.
	 * @param name of the option, never <code>null</code> or empty!
	 * @throws IllegalArgumentException if name is <code>null</code> or empty
	 */
	public ValidationOption(String name) {
		this(name, null);
	}

	/**
	 * @param name of the option, never <code>null</code> or empty!
	 * @param argument of the option, may be <code>null</code> if not required
	 * @throws IllegalArgumentException if name is <code>null</code> or empty
	 */
	public ValidationOption(String name, String argument) {
		checkName(name);
		this.name = name;
		this.argument = argument;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean hasArgument() {
		return argument != null;
	}

	public String getArgument() {
		return argument;
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}

	private void checkName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Parameter name must not be null!");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((argument == null) ? 0 : argument.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ValidationOption other = (ValidationOption) obj;
		if (argument == null) {
			if (other.argument != null)
				return false;
		}
		else if (!argument.equals(other.argument))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ValidationOption{" + "name='" + name + '\'' + ", argument='" + argument + '\'' + '}';
	}

}
