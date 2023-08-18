/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.wmsconfig;

import java.io.File;

import org.deegree.theme.persistence.standard.jaxb.Themes;

/**
 * Encapsulates the required configuration from the WMS workspace.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class WmsThemesConfig {

	private final File config;

	private final Themes themes;

	/**
	 * @param config the location of the themes configuration, never <code>null</code>
	 * @param themes the themes parsed from the configuration, never <code>null</code>
	 */
	public WmsThemesConfig(File config, Themes themes) {
		this.config = config;
		this.themes = themes;
	}

	/**
	 * @return the location of the themes configuration, never <code>null</code>
	 */
	public File getConfig() {
		return config;
	}

	/**
	 * @return the themes parsed from the configuration, never <code>null</code>
	 */
	public Themes getThemes() {
		return themes;
	}

}
