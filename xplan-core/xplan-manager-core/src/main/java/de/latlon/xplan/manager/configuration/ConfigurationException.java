/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.configuration;

/**
 * Indicates an invalid configuration.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ConfigurationException extends Exception {

	private static final long serialVersionUID = -641681336482309680L;

	public ConfigurationException(String msg) {
		super(msg);
	}

	public ConfigurationException(Throwable cause) {
		super(cause);
	}

	public ConfigurationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
