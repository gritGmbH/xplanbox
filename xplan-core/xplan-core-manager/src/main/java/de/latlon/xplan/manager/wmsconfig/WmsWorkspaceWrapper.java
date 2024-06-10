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
package de.latlon.xplan.manager.wmsconfig;

import de.latlon.xplan.manager.configuration.ConfigurationException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.io.FilenameUtils;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.commons.xml.jaxb.JAXBUtils;
import org.deegree.theme.persistence.standard.StandardThemeProvider;
import org.deegree.theme.persistence.standard.jaxb.ThemeType;
import org.deegree.theme.persistence.standard.jaxb.Themes;

import jakarta.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

/**
 * Wraps a wms workspace and provides some useful methods.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class WmsWorkspaceWrapper {

	private static final String DATA_DIRECTORY = "data";

	public static final List<String> supportedTypes = asList("bplan", "fplan", "rplan", "lplan", "soplan", "bplanpre",
			"fplanpre", "rplanpre", "lplanpre", "soplanpre", "bplanarchive", "fplanarchive", "rplanarchive",
			"lplanarchive", "soplanarchive");

	private final DeegreeWorkspace workspace;

	/**
	 * @param wmsWorkspace never <code>null</code>
	 */
	public WmsWorkspaceWrapper(DeegreeWorkspace wmsWorkspace) {
		this.workspace = wmsWorkspace;
	}

	/**
	 * @return the workspace location (must not exist!), never <code>null</code>
	 */
	public File getLocation() {
		return workspace.getLocation();
	}

	/**
	 * @return the workspace location (must not exist!), never <code>null</code>
	 */
	@SuppressFBWarnings(value = "PATH_TRAVERSAL_IN")
	public Path getDataDirectory() {
		return Paths.get(getLocation().toURI()).resolve(DATA_DIRECTORY);
	}

	/**
	 * Checks if all themes are configured as expected.
	 * @param configuredCrs the configured crs of the themes configuration, may be
	 * <code>null</code> (check of the themes crs is skipped)
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	public void checkThemes(String configuredCrs) throws JAXBException, IOException, ConfigurationException {
		for (String supportedType : supportedTypes) {
			retrieveThemesForType(supportedType, configuredCrs);
		}
	}

	/**
	 * @param type the type of the configuration to retrieve, never <code>null</code>
	 * @return the configuration of the passed type, never <code>null</code>
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	public WmsThemesConfig retrieveThemesForType(String type)
			throws JAXBException, IOException, ConfigurationException {
		return retrieveThemesForType(type, null);
	}

	/**
	 * @param type the type of the configuration to retrieve, never <code>null</code>
	 * @param configuredCrs the configured crs of the themes configuration, may be
	 * <code>null</code> (check of the themes crs is skipped)
	 * @return the configuration of the passed type, never <code>null</code>
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	public WmsThemesConfig retrieveThemesForType(String type, String configuredCrs)
			throws JAXBException, IOException, ConfigurationException {
		File config = createConfig(type);
		Themes themes = parseThemes(config);

		checkTheme(type, config, themes);
		checkThemeCrs(config, themes, configuredCrs);

		return new WmsThemesConfig(config, themes);
	}

	private File createConfig(String type) {
		File configFile = new File(workspace.getLocation(), format("themes/%sraster.xml", FilenameUtils.getName(type)));
		if (!configFile.isFile() || !configFile.canRead()) {
			throw new RuntimeException("Datei '" + configFile + "' ist nicht vorhanden.");
		}
		return configFile;
	}

	private void checkTheme(String type, File config, Themes themes) throws ConfigurationException {
		ThemeType theme = themes.getTheme();
		if (theme == null) {
			throw new ConfigurationException("Datei '" + config + "' enthält kein Theme-Element.");
		}
		if (theme.getTheme().isEmpty()) {
			throw new ConfigurationException("Datei '" + config + "' enthält kein verschachteltes Theme-Element.");
		}
		ThemeType firstChildTheme = theme.getTheme().get(0);
		String expected = checkAndRemovePreOrArchive(type) + "raster_sortiert";
		String firstChildIdentifier = firstChildTheme.getIdentifier().getValue();
		if (!expected.equals(firstChildIdentifier)) {
			throw new ConfigurationException("Datei '" + config + "' hat eine nicht unterstützte Struktur. "
					+ " Das erste Sub-Theme hat den Identifier: " + firstChildIdentifier + ". Erwartet: " + expected);
		}
	}

	private Themes parseThemes(File config) throws JAXBException, IOException {
		try (InputStream configFileStream = new FileInputStream(config)) {
			return (Themes) JAXBUtils.unmarshall("org.deegree.theme.persistence.standard.jaxb",
					new StandardThemeProvider().getSchema(), configFileStream, workspace.getNewWorkspace());
		}
	}

	private void checkThemeCrs(File config, Themes themes, String configuredCrs) throws ConfigurationException {
		if (configuredCrs == null)
			return;
		ThemeType theme = themes.getTheme();
		if (!configuredCrs.equalsIgnoreCase(theme.getCRS())) {
			throw new ConfigurationException(
					"In der Themes Konfiguration '" + config + "' ist ein anderes CRS konfiguriert (" + theme.getCRS()
							+ ") als in der Manager Konfiguration (" + configuredCrs + ").");
		}
	}

	private String checkAndRemovePreOrArchive(String type) {
		if (type.endsWith("pre"))
			return type.substring(0, type.length() - 3);
		if (type.endsWith("archive"))
			return type.substring(0, type.length() - 7);
		return type;
	}

}
