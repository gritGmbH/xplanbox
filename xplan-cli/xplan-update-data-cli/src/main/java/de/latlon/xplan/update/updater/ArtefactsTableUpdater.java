/*-
 * #%L
 * xplan-update-data-cli - update of database
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.update.updater;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.XPlanGmlParser;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.archive.XPlanArchiveCreator.MAIN_FILE;
import static de.latlon.xplan.manager.database.ArtefactType.RASTERBASIS;
import static de.latlon.xplan.manager.database.ArtefactType.XPLANGML;
import static java.util.Collections.singletonList;

/**
 * Updates the data from version 6.0 to 6.1: Inserts data to
 * xplanmgr.artefacts.artefacttype
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ArtefactsTableUpdater {

	private final Logger LOG = LoggerFactory.getLogger(BereichUpdate.class);

	private final XPlanDao xplanDao;

	private final ExternalReferenceScanner externalReferenceScanner = new ExternalReferenceScanner();

	public ArtefactsTableUpdater(XPlanDao xplanDao) {
		this.xplanDao = xplanDao;
	}

	public void update() throws Exception {
		List<XPlan> plans = xplanDao.getXPlanList(false);
		for (XPlan plan : plans) {
			update(plan);
		}
	}

	private void update(XPlan plan) throws Exception {
		LOG.info("Update plan with id {}, version {}, type {}", plan.getId(), plan.getVersion(), plan.getType());
		xplanDao.updateArtefacttype(plan.getId(), singletonList(MAIN_FILE), XPLANGML);

		FeatureCollection featureCollection = retrieveFeatureCollection(plan);
		if (featureCollection.isEmpty()) {
			LOG.warn("FeatureCollection is not available! Plan with id {} is skipped.", plan.getId());
			return;
		}
		List<String> rasterFileNames = scanRasterReferenceFileNames(featureCollection);
		if (rasterFileNames.isEmpty()) {
			LOG.info("Plan with id {} has no rasterfile.", plan.getId());
			return;
		}
		xplanDao.updateArtefacttype(plan.getId(), rasterFileNames, RASTERBASIS);
	}

	private FeatureCollection retrieveFeatureCollection(XPlan plan) throws Exception {
		XPlanVersion version = XPlanVersion.valueOf(plan.getVersion());
		InputStream originalPlan = xplanDao.retrieveXPlanArtefact(plan.getId());
		return XPlanGmlParser.newParser().parseFeatureCollection(originalPlan, version);
	}

	private List<String> scanRasterReferenceFileNames(FeatureCollection featureCollection) {
		ExternalReferenceInfo scan = externalReferenceScanner.scan(featureCollection);
		List<ExternalReference> rasterPlanBaseAndUpdateScans = scan.getRasterPlanBaseAndUpdateScans();
		return rasterPlanBaseAndUpdateScans.stream().map(externalReference -> externalReference.getReferenzUrl())
				.collect(Collectors.toList());
	}

}
