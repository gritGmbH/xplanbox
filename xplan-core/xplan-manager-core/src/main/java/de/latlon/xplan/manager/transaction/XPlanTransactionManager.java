/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.feature.FeatureCollectionManipulator;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParser;
import de.latlon.xplan.manager.configuration.CoupledResourceConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.edit.XPlanManipulator;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import de.latlon.xplan.manager.metadata.MetadataCouplingHandler;
import de.latlon.xplan.manager.planwerkwms.PlanwerkServiceMetadata;
import de.latlon.xplan.manager.planwerkwms.PlanwerkServiceMetadataBuilder;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplan.manager.workspace.WorkspaceReloaderConfiguration;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.parseFeatureCollection;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDescription;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class XPlanTransactionManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanTransactionManager.class);

	private static final DateFormat DATEFORMAT = createDateFormat();

	protected final XPlanSynthesizer xPlanSynthesizer;

	protected final XPlanGmlTransformer xPlanGmlTransformer;

	protected final XPlanDao xplanDao;

	protected final XPlanExporter xPlanExporter;

	protected final XPlanRasterManager xPlanRasterManager;

	protected final WorkspaceReloader workspaceReloader;

	protected final ManagerConfiguration managerConfiguration;

	protected final ManagerWorkspaceWrapper managerWorkspaceWrapper;

	protected final SortPropertyReader sortPropertyReader;

	protected final XPlanManipulator planModifier = new XPlanManipulator();

	protected final FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();

	protected final XPlanGmlParser xPlanGmlParser = new XPlanGmlParser();

	private final MetadataCouplingHandler metadataCouplingHandler;

	public XPlanTransactionManager(XPlanSynthesizer xPlanSynthesizer, XPlanGmlTransformer xPlanGmlTransformer,
			XPlanDao xplanDao, XPlanExporter xPlanExporter, XPlanRasterManager xPlanRasterManager,
			WorkspaceReloader workspaceReloader, ManagerConfiguration managerConfiguration,
			ManagerWorkspaceWrapper managerWorkspaceWrapper, SortPropertyReader sortPropertyReader)
			throws DataServiceCouplingException {
		this.xPlanSynthesizer = xPlanSynthesizer;
		this.xPlanGmlTransformer = xPlanGmlTransformer;
		this.xplanDao = xplanDao;
		this.xPlanExporter = xPlanExporter;
		this.xPlanRasterManager = xPlanRasterManager;
		this.workspaceReloader = workspaceReloader;
		this.managerConfiguration = managerConfiguration;
		this.managerWorkspaceWrapper = managerWorkspaceWrapper;
		this.sortPropertyReader = sortPropertyReader;
		if (managerConfiguration != null && managerConfiguration.getCoupledResourceConfiguration() != null)
			this.metadataCouplingHandler = new MetadataCouplingHandler(xplanDao,
					managerConfiguration.getCoupledResourceConfiguration());
		else
			this.metadataCouplingHandler = null;
	}

	protected void reloadWorkspace() {
		if (workspaceReloader != null) {
			WorkspaceReloaderConfiguration configuration = managerConfiguration.getWorkspaceReloaderConfiguration();
			workspaceReloader.reloadWorkspace(configuration);
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

	protected List<String> createRasterConfiguration(XPlanArchiveContentAccess archive, XPlanFeatureCollection fc,
			int planId, XPlanType type, PlanStatus planStatus, PlanStatus newPlanStatus, Date sortDate)
			throws SQLException {
		String moreRecentPlanId = null;
		if (sortDate != null) {
			moreRecentPlanId = xplanDao.getPlanIdOfMoreRecentRasterPlan(sortDate);
		}
		return xPlanRasterManager.updateWmsWorkspaceWithRasterLayers(archive, fc, planId, moreRecentPlanId, type,
				planStatus, newPlanStatus, sortDate);
	}

	protected byte[] createXPlanGml(XPlanVersion version, FeatureCollection plan) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		String comment = "Zuletzt aktualisiert am: " + DATEFORMAT.format(new Date());
		xPlanExporter.export(bos, version, plan, comment);
		return bos.toByteArray();
	}

	protected void reassignFids(XPlanFeatureCollection fc) {
		for (Feature f : fc.getFeatures()) {
			String prefix = "XPLAN_" + f.getName().getLocalPart().toUpperCase() + "_";
			String uuid = UUID.randomUUID().toString();
			f.setId(prefix + uuid);
		}
	}

	protected FeatureCollection renewFeatureCollection(XPlanVersion version, AppSchema appSchema,
			FeatureCollection modifiedFeatures) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		xPlanExporter.export(outputStream, version, modifiedFeatures, null);
		ByteArrayInputStream originalPlan = new ByteArrayInputStream(outputStream.toByteArray());
		XMLStreamReader originalPlanAsXmlReader = XMLInputFactory.newInstance().createXMLStreamReader(originalPlan);
		try {
			return parseFeatureCollection(originalPlanAsXmlReader, version, appSchema);
		}
		finally {
			originalPlanAsXmlReader.close();
		}
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
				LOG.warn("Creation of data services coupling failed. URL could not be created.");
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

	private static DateFormat createDateFormat() {
		DateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
		return simpleDateFormat;
	}

}
