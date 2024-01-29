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
package de.latlon.xplan.manager.wmsconfig.raster.config;

import de.latlon.xplan.manager.configuration.ConfigurationException;
import de.latlon.xplan.manager.wmsconfig.WmsThemesConfig;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.RasterConfigurationSorter;
import org.deegree.theme.persistence.standard.jaxb.ThemeType;
import org.deegree.theme.persistence.standard.jaxb.ThemeType.Layer;
import org.deegree.theme.persistence.standard.jaxb.Themes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper.supportedTypes;
import static java.lang.Boolean.TRUE;
import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

/**
 * Modifies raster theme configuration files in the WMS workspace.
 *
 * @author <a href="mailto:schmitz@occamlabs.de">Andreas Schmitz</a>
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class WorkspaceRasterThemeManager {

	private static final Logger LOG = LoggerFactory.getLogger(WorkspaceRasterThemeManager.class);

	private final RasterConfigurationSorter rasterConfigurationSorter = new RasterConfigurationSorter();

	private final WmsWorkspaceWrapper wmsWorkspaceWrapper;

	/**
	 * @param wmsWorkspaceWrapper the wrapper of the workspace containing the wms
	 * configuration to manipulate, never <code>null</code>
	 * @throws IllegalArgumentException - if the wmsWorkspace is <code>null</code>
	 */
	public WorkspaceRasterThemeManager(WmsWorkspaceWrapper wmsWorkspaceWrapper) {
		if (wmsWorkspaceWrapper == null)
			throw new IllegalArgumentException();
		this.wmsWorkspaceWrapper = wmsWorkspaceWrapper;
	}

	/**
	 * Inserts the specified raster layers at the correct position in the "sorted theme"
	 * (which is sorted by plan date).
	 * @param rasterLayerIds ids of the new raster layers, must not be <code>null</code>
	 * @param succeedingPlanId id of the raster plan that succeeds the new layers, can be
	 * <code>null</code> (new layers are most recent, inserted at the end)
	 * @throws JAXBException if the changes could not be persisted
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	public synchronized void insertLayersRightBefore(String type, String crs, List<String> rasterLayerIds,
			int succeedingPlanId) throws JAXBException, IOException, ConfigurationException {
		checkIfTypeIsSupported(type);
		WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType(type, crs);

		Themes themes = themesConfig.getThemes();
		LOG.info("Succeeding plan id: {}", succeedingPlanId);
		for (String rasterLayerId : rasterLayerIds) {
			themes.getLayerStoreId().add(rasterLayerId);
		}
		ThemeType sortedTheme = themes.getTheme().getTheme().get(0);
		List<Layer> layers = sortedTheme.getLayer();
		List<Layer> newRasterLayers = createNewRasterLayers(rasterLayerIds);

		int index = findIndex(layers, succeedingPlanId);
		layers.addAll(index, newRasterLayers);

		for (Layer layer : layers) {
			LOG.debug(layer.getLayerStore());
		}
		persist(themes, themesConfig.getConfig());
	}

	/**
	 * Inserts the specified raster layers at the beginning.
	 * @param rasterLayerIds ids of the new raster layers, must not be <code>null</code>
	 * @throws JAXBException if the changes could not be persisted
	 * @throws IOException
	 */
	public synchronized void insertLayersAtBeginning(String type, String crs, List<String> rasterLayerIds)
			throws JAXBException, IOException, ConfigurationException {
		checkIfTypeIsSupported(type);
		WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType(type, crs);

		File config = themesConfig.getConfig();
		Themes themes = themesConfig.getThemes();

		for (String rasterLayerId : rasterLayerIds) {
			themes.getLayerStoreId().add(rasterLayerId);
		}
		ThemeType sortedTheme = themes.getTheme().getTheme().get(0);
		List<Layer> layers = sortedTheme.getLayer();
		List<Layer> newRasterLayers = createNewRasterLayers(rasterLayerIds);
		layers.addAll(0, newRasterLayers);
		persist(themes, config);
	}

	/**
	 * Removes all references to raster layers that belong to the specified plan.
	 * @param planId id of the plan, must not be <code>null</code>
	 * @throws JAXBException
	 * @throws IOException
	 */
	public synchronized void removeLayersForPlan(String type, int planId)
			throws JAXBException, IOException, ConfigurationException {
		final String prefix = planId + "_";
		removeLayers(type, toMatch -> toMatch.startsWith(prefix));
	}

	/**
	 * Removes all references to raster layers that belong to the specified plan with the
	 * specified rasterLayerId.
	 * @param type of the plan (bplan, rplan, fplan, lplan), never <code>null</code>
	 * @param planId id of the plan, must not be <code>null</code>
	 * @param rasterId the id of the raster (layer id without id suffix) to remove, never
	 * <code>null</code>
	 * @throws JAXBException
	 * @throws IOException
	 */
	public synchronized void removeLayersForPlan(String type, int planId, String rasterId)
			throws JAXBException, IOException, ConfigurationException {
		final String layerId = planId + "_" + rasterId;
		removeLayers(type, toMatch -> toMatch.equals(layerId));
	}

	/**
	 * Moves all raster configurations from one config to another. If sourceType and
	 * targetType are equal, nothing is moved.
	 * @param sourceType the type of the plan to move from, never <code>null</code>
	 * @param targetType the type of the plan to move to, never <code>null</code>
	 * @param planId the id of the plan to move, never <code>null</code>
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	public synchronized void moveLayers(String sourceType, String targetType, int planId)
			throws JAXBException, IOException, ConfigurationException {
		if (sourceType.equals(targetType))
			return;
		checkIfTypeIsSupported(sourceType);
		checkIfTypeIsSupported(targetType);

		WmsThemesConfig sourceThemesConfig = wmsWorkspaceWrapper.retrieveThemesForType(sourceType);
		Themes sourceThemes = sourceThemesConfig.getThemes();

		List<Layer> removedLayers = removeUserLayers(planId, sourceThemes.getTheme());

		WmsThemesConfig targetThemesConfig = wmsWorkspaceWrapper.retrieveThemesForType(targetType);
		Themes targetThemes = targetThemesConfig.getThemes();

		ThemeType sortedTheme = targetThemes.getTheme().getTheme().get(0);
		List<Layer> layers = sortedTheme.getLayer();
		layers.addAll(removedLayers);
		for (Layer removedLayer : removedLayers) {
			String layerStore = removedLayer.getLayerStore();
			sourceThemes.getLayerStoreId().remove(layerStore);
			targetThemes.getLayerStoreId().add(layerStore);
		}
		persist(sourceThemes, sourceThemesConfig.getConfig());
		persist(targetThemes, targetThemesConfig.getConfig());
	}

	/**
	 * Reorders the wms layer configuraton by the passed sort dates.
	 * @param planId2sortDate plan ids mapped to the new sort date, never
	 * <code>null</code>
	 * @throws ConfigurationException
	 * @throws IOException
	 * @throws JAXBException
	 */
	public synchronized void reorderWmsLayers(Map<String, Date> planId2sortDate, String crs)
			throws JAXBException, IOException, ConfigurationException {
		List<String> sortedByDate = rasterConfigurationSorter.sortByDateInDeegreeOrder(planId2sortDate);
		for (String supportedType : supportedTypes) {
			reorderWmsLayers(sortedByDate, crs, supportedType);
		}
	}

	public void reorderWmsLayers(int planId, int moreRecentPlan, String statusType)
			throws ConfigurationException, JAXBException, IOException {
		WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType(statusType);
		Themes themes = themesConfig.getThemes();

		ThemeType sortedTheme = themes.getTheme().getTheme().get(0);
		List<Layer> layers = sortedTheme.getLayer();
		List<Layer> layersToChangeIndex = layers.stream()
			.filter(layer -> layer.getValue().startsWith(planId + "_"))
			.collect(Collectors.toList());
		layers.removeAll(layersToChangeIndex);
		int index = findIndex(layers, moreRecentPlan);
		layers.addAll(index, layersToChangeIndex);
		persist(themes, themesConfig.getConfig());
	}

	private void reorderWmsLayers(List<String> sortedByDate, String crs, String supportedType)
			throws JAXBException, IOException, ConfigurationException {
		WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType(supportedType, crs);

		File config = themesConfig.getConfig();
		Themes themes = themesConfig.getThemes();

		ThemeType sortedTheme = themes.getTheme().getTheme().get(0);
		List<Layer> layers = sortedTheme.getLayer();
		rasterConfigurationSorter.sortLayers(layers, sortedByDate);
		persist(themes, config);
	}

	private void persist(Themes themes, File config) throws JAXBException {
		JAXBContext ctx = JAXBContext.newInstance("org.deegree.theme.persistence.standard.jaxb");
		Marshaller marshaller = ctx.createMarshaller();
		marshaller.setProperty(JAXB_FORMATTED_OUTPUT, TRUE);
		marshaller.marshal(themes, config);
	}

	private int findIndex(List<Layer> layers, int succedingPlanId) {
		if (succedingPlanId > -1) {
			String prefix = succedingPlanId + "_";
			for (int i = 0; i < layers.size(); i++) {
				if (layers.get(i).getLayerStore().startsWith(prefix)) {
					return i;
				}
			}
		}
		return layers.size();
	}

	private void checkIfTypeIsSupported(String type) {
		if (!supportedTypes.contains(type)) {
			throw new IllegalArgumentException("First parameter must be bplan, fplan, rplan, lplan or soplan.");
		}
	}

	private void removeLayers(String type, IdMatcher idMatcher)
			throws JAXBException, IOException, ConfigurationException {
		checkIfTypeIsSupported(type);
		WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType(type);
		Themes themes = themesConfig.getThemes();

		removeLayerStoresWithPrefix(themes, idMatcher);
		removeLayersWithPrefixFromAutoTheme(themes, idMatcher);
		removeUserLayersFromRootTheme(themes, idMatcher);
		removeUserLayersFromUserCategories(themes, idMatcher);
		persist(themes, themesConfig.getConfig());
	}

	private void removeLayerStoresWithPrefix(Themes themes, IdMatcher matcher) {
		List<String> removeIds = new ArrayList<String>();
		for (String layerStoreId : themes.getLayerStoreId()) {
			if (matcher.matches(layerStoreId)) {
				removeIds.add(layerStoreId);
			}
		}
		themes.getLayerStoreId().removeAll(removeIds);
	}

	private void removeLayersWithPrefixFromAutoTheme(Themes themes, IdMatcher matcher) {
		ThemeType theme = themes.getTheme().getTheme().get(0);
		List<Layer> removeLayers = new ArrayList<Layer>();
		for (Layer layer : theme.getLayer()) {
			if (matcher.matches(layer.getLayerStore())) {
				removeLayers.add(layer);
			}
		}
		theme.getLayer().removeAll(removeLayers);
	}

	private void removeUserLayersFromRootTheme(Themes themes, IdMatcher matcher) {
		List<ThemeType> removeThemes = new ArrayList<ThemeType>();
		for (int i = 1; i < themes.getTheme().getTheme().size(); i++) {
			ThemeType theme = themes.getTheme().getTheme().get(i);
			if (theme.getLayer().size() == 1) {
				if (matcher.matches(theme.getLayer().get(0).getValue())) {
					removeThemes.add(theme);
				}
			}
		}
		themes.getTheme().getTheme().removeAll(removeThemes);
	}

	private void removeUserLayersFromUserCategories(Themes themes, IdMatcher matcher) {
		for (int i = 1; i < themes.getTheme().getTheme().size(); i++) {
			ThemeType theme = themes.getTheme().getTheme().get(i);
			if (!theme.getTheme().isEmpty()) {
				List<ThemeType> userLayers = new ArrayList<ThemeType>();
				for (ThemeType userLayer : theme.getTheme()) {
					if (userLayer.getLayer().size() == 1) {
						if (matcher.matches(userLayer.getLayer().get(0).getValue())) {
							userLayers.add(userLayer);
						}
					}
				}
				theme.getTheme().removeAll(userLayers);
			}
		}
	}

	private List<Layer> createNewRasterLayers(List<String> rasterLayerIds) {
		List<Layer> newRasterLayers = new ArrayList<Layer>();
		for (String rasterLayerId : rasterLayerIds) {
			Layer layer = new Layer();
			layer.setLayerStore(rasterLayerId);
			layer.setValue(rasterLayerId);
			newRasterLayers.add(layer);
		}
		return newRasterLayers;
	}

	private List<Layer> removeUserLayers(int planId, ThemeType theme) {
		String prefix = planId + "_";
		List<Layer> removedLayers = new ArrayList<>();
		for (ThemeType subTheme : theme.getTheme()) {
			List<Layer> removedLayersFromSubtheme = new ArrayList<>();
			List<Layer> layers = subTheme.getLayer();
			for (Layer layer : layers) {
				String value = layer.getValue();
				if (value.startsWith(prefix)) {
					removedLayersFromSubtheme.add(layer);
				}
			}
			subTheme.getLayer().removeAll(removedLayersFromSubtheme);
			removedLayers.addAll(removedLayersFromSubtheme);
			removedLayers.addAll(removeUserLayers(planId, subTheme));
		}
		return removedLayers;
	}

	private interface IdMatcher {

		boolean matches(String toMatch);

	}

}
