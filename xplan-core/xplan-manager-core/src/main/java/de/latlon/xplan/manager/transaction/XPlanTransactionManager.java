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
package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.configuration.CoupledResourceConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import de.latlon.xplan.manager.metadata.MetadataCouplingHandler;
import de.latlon.xplan.manager.planwerkwms.PlanwerkServiceMetadata;
import de.latlon.xplan.manager.planwerkwms.PlanwerkServiceMetadataBuilder;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDescription;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class XPlanTransactionManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanTransactionManager.class);

	protected final XPlanSynthesizer xPlanSynthesizer;

	protected final XPlanDao xplanDao;

	protected final XPlanRasterManager xPlanRasterManager;

	protected final WorkspaceReloader workspaceReloader;

	protected final ManagerConfiguration managerConfiguration;

	protected final SortPropertyReader sortPropertyReader;

	private final MetadataCouplingHandler metadataCouplingHandler;

	public XPlanTransactionManager(XPlanSynthesizer xPlanSynthesizer, XPlanDao xplanDao,
			XPlanRasterManager xPlanRasterManager, WorkspaceReloader workspaceReloader,
			ManagerConfiguration managerConfiguration, SortPropertyReader sortPropertyReader,
			MetadataCouplingHandler metadataCouplingHandler) {
		this.xPlanSynthesizer = xPlanSynthesizer;
		this.xplanDao = xplanDao;
		this.xPlanRasterManager = xPlanRasterManager;
		this.workspaceReloader = workspaceReloader;
		this.managerConfiguration = managerConfiguration;
		this.sortPropertyReader = sortPropertyReader;
		this.metadataCouplingHandler = metadataCouplingHandler;
	}

	protected void reloadWorkspace(int planId) {
		if (workspaceReloader != null) {
			workspaceReloader.reloadWorkspace(planId);
		}
	}

	protected FeatureCollection createSynFeatures(XPlanFeatureCollection fc, XPlanVersion version) {
		FeatureCollection synFc;
		long begin = System.currentTimeMillis();
		LOG.info("- Erzeugen der XPlan-Syn Features...");
		synFc = xPlanSynthesizer.synthesize(version, fc);
		long elapsed = System.currentTimeMillis() - begin;
		LOG.info("OK [" + elapsed + " ms]");
		return synFc;
	}

	protected List<String> createRasterConfiguration(XPlanArchiveContentAccess archive,
			List<String> rasterRefsFileNamesToAdd, int planId, XPlanType type, PlanStatus planStatus,
			PlanStatus newPlanStatus, Date sortDate) {
		String moreRecentPlanId = null;
		if (sortDate != null) {
			moreRecentPlanId = xplanDao.getPlanIdOfMoreRecentRasterPlan(sortDate);
		}
		return xPlanRasterManager.updateWmsWorkspaceWithRasterLayers(archive, rasterRefsFileNamesToAdd, planId,
				moreRecentPlanId, type, planStatus, newPlanStatus, sortDate);
	}

	protected void startCreationOfDataServicesCoupling(int planId, XPlanFeatureCollection featureCollection, ICRS crs) {
		CoupledResourceConfiguration coupledResourceConfiguration = this.managerConfiguration
			.getCoupledResourceConfiguration();
		if (coupledResourceConfiguration != null) {
			LOG.info("Start creation of the data services coupling.");
			try {
				PlanwerkServiceMetadata planwerkServiceMetadata = createPlanwerkServiceMetadata(featureCollection, crs,
						coupledResourceConfiguration);
				String planName = featureCollection.getPlanName();
				DataServicesCouplingRunnable runnable = new DataServicesCouplingRunnable(planId, planName,
						planwerkServiceMetadata);
				Thread thread = new Thread(runnable);
				thread.start();
			}
			catch (UnsupportedEncodingException e) {
				LOG.warn("Creation of data services coupling failed. URL could not be created: {}", e.getMessage());
			}
			catch (Exception e) {
				LOG.warn("Creation of data services coupling failed: {}", e.getMessage());
				LOG.trace(e.getMessage(), e);
			}
		}
		else {
			LOG.info("Creation of data services coupling is disabled.");
		}
	}

	private PlanwerkServiceMetadata createPlanwerkServiceMetadata(XPlanFeatureCollection featureCollection, ICRS crs,
			CoupledResourceConfiguration coupledResourceConfiguration) throws UnsupportedEncodingException {
		String title = featureCollection.getPlanName();
		String description = retrieveDescription(featureCollection.getFeatures(), featureCollection.getType());
		Envelope envelope = featureCollection.getBboxIn4326();

		PlanwerkServiceMetadataBuilder builder = new PlanwerkServiceMetadataBuilder(XPlanType.BP_Plan, title,
				description, envelope, coupledResourceConfiguration);
		return builder.build(crs);
	}

	class DataServicesCouplingRunnable implements Runnable {

		private final Logger LOG = LoggerFactory.getLogger(DataServicesCouplingRunnable.class);

		private final int planId;

		private final String planName;

		private final PlanwerkServiceMetadata planwerkServiceMetadata;

		public DataServicesCouplingRunnable(int planId, String planName,
				PlanwerkServiceMetadata planwerkServiceMetadata) {
			this.planId = planId;
			this.planName = planName;
			this.planwerkServiceMetadata = planwerkServiceMetadata;
		}

		@Override
		public void run() {
			try {
				metadataCouplingHandler.processMetadataCoupling(planId, planName, planwerkServiceMetadata);
			}
			catch (DataServiceCouplingException e) {
				LOG.error("Could not create data services coupling", e);
			}
		}

	}

}
