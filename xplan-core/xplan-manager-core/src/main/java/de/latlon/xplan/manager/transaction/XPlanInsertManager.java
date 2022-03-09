/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollections;
import de.latlon.xplan.manager.CrsUtils;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceManager;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.findWorkspaceDirectory;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanInsertManager extends XPlanTransactionManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanInsertManager.class);

	public XPlanInsertManager(XPlanSynthesizer xPlanSynthesizer, XPlanGmlTransformer xPlanGmlTransformer,
			XPlanDao xplanDao, XPlanExporter xPlanExporter, XPlanRasterManager xPlanRasterManager,
			WorkspaceReloader workspaceReloader, ManagerConfiguration managerConfiguration,
			ManagerWorkspaceWrapper managerWorkspaceWrapper, SortPropertyReader sortPropertyReader)
			throws DataServiceCouplingException {
		super(xPlanSynthesizer, xPlanGmlTransformer, xplanDao, xPlanExporter, xPlanRasterManager, workspaceReloader,
				managerConfiguration, managerWorkspaceWrapper, sortPropertyReader);
	}

	/**
	 * @param archive to import, never <code>null</code>
	 * @param defaultCRS the default crs, may be <code>null</code> if no default crs
	 * should be set
	 * @param force should import be forced?
	 * @param makeWMSConfig <code>true</code> if the WMS configuration for the plan to
	 * import should be created, <code>false</code> otherwise. To use this option it is
	 * required, that makeRasterConfig is <code>true</code>
	 * @param makeRasterConfig <code>true</code> if the configuration of raster files
	 * should be created, <code>false</code> otherwise
	 * @param workspaceFolder workspace folder, may be <code>null</code> if default path
	 * should be used.
	 * @param internalId is added to the feature collection of the plan, if
	 * <code>null</code>, internalId property is not added to the feature collection
	 * @param xPlanMetadata containing some metadata about the xplan, never
	 * <code>null</code>
	 * @throws Exception
	 * @return the id of the plan, never <code>null</code>
	 */
	public List<Integer> importPlan(XPlanArchive archive, ICRS defaultCRS, boolean force, boolean makeWMSConfig,
			boolean makeRasterConfig, File workspaceFolder, String internalId, AdditionalPlanData xPlanMetadata)
			throws Exception {
		LOG.info("- Importiere Plan " + archive);
		ICRS crs = CrsUtils.determineActiveCrs(defaultCRS, archive, LOG);
		PlanStatus selectedPlanStatus = xPlanMetadata.getPlanStatus();
		XPlanFeatureCollections xPlanInstances = readAndValidateMainDocument(archive, crs, force);
		List<Integer> planIds = new ArrayList<>();
		boolean gmlWithMultipleInstances = xPlanInstances.getxPlanGmlInstances().size() > 1;
		if (gmlWithMultipleInstances && (internalId != null || selectedPlanStatus != null)) {
			LOG.warn(
					"XPlanGML contains multiple plan instances, internalId ({}) and selected planStatus ({}) are ignored.",
					internalId, selectedPlanStatus);
			internalId = null;
			selectedPlanStatus = null;
		}
		for (XPlanFeatureCollection xPlanInstance : xPlanInstances.getxPlanGmlInstances()) {
			FeatureCollection synFc = createSynFeatures(xPlanInstance, archive.getVersion());
			if (internalId != null) {
				AppSchema synSchema = managerWorkspaceWrapper.lookupStore(XPLAN_SYN, null, selectedPlanStatus)
						.getSchema();
				featureCollectionManipulator.addInternalId(synFc, synSchema, internalId);
			}
			Date sortDate = sortPropertyReader.readSortDate(archive.getType(), archive.getVersion(),
					xPlanInstance.getFeatures());
			int planId = xplanDao.insert(archive, xPlanInstance, synFc, xPlanMetadata, sortDate, internalId);
			createRasterConfigurations(archive, makeWMSConfig, makeRasterConfig, workspaceFolder, xPlanInstance, planId,
					selectedPlanStatus, sortDate);
			startCreationOfDataServicesCoupling(planId, xPlanInstance, crs);
			reloadWorkspace();
			LOG.info("XPlanArchiv wurde erfolgreich importiert. Zugewiesene Id: " + planId);
			LOG.info("OK.");
			planIds.add(planId);
		}
		LOG.info("Alle {0} XPlan GML Instanzen aus dem XPlanArchiv wurden erfolgreich importiert.",
				xPlanInstances.getxPlanGmlInstances().size());
		return planIds;
	}

	private XPlanFeatureCollections readAndValidateMainDocument(XPlanArchive archive, ICRS crs, boolean force)
			throws Exception {
		performSchemaValidation(archive);
		try {
			XPlanFeatureCollections xPlanInstances = xPlanGmlParser
					.parseXPlanFeatureCollectionAllowMultipleInstances(archive, crs);
			reassignFids(xPlanInstances);
			for (XPlanFeatureCollection xPlanInstance : xPlanInstances.getxPlanGmlInstances()) {
				long begin = System.currentTimeMillis();
				new SyntacticValidatorImpl().validateReferences(archive, xPlanInstance.getExternalReferenceInfo(),
						force);
				LOG.info("- Überprüfung der externen Referenzen...");
				long elapsed = System.currentTimeMillis() - begin;
				LOG.info("OK [" + elapsed + " ms]");
			}
			return xPlanInstances;
		}
		catch (XMLStreamException | UnknownCRSException e) {
			LOG.error("Could not read and validate xplan gml", e);
			return null;
		}
	}

	private void createRasterConfigurations(XPlanArchive archive, boolean makeWMSConfig, boolean makeRasterConfig,
			File workspaceFolder, XPlanFeatureCollection fc, int planId, PlanStatus planStatus, Date sortDate)
			throws Exception {
		if (makeRasterConfig) {
			List<String> rasterIds = createRasterConfiguration(archive, fc, planId, archive.getType(), planStatus, null,
					sortDate);
			if (makeWMSConfig) {
				WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager(
						findWorkspaceDirectory(workspaceFolder));
				wmsWorkspaceManager.updateWmsWorkspace(archive, planId, rasterIds, planStatus, fc.getBboxIn4326(),
						managerConfiguration.getDefaultBboxIn4326());
			}
		}
	}

	private void performSchemaValidation(XPlanArchive archive) throws Exception {
		long begin = System.currentTimeMillis();
		LOG.info("- Schema-Validierung (Hauptdokument)...");
		SyntacticValidatorResult result;
		try {
			result = (SyntacticValidatorResult) new SyntacticValidatorImpl().validateSyntax(archive);
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		long elapsed = System.currentTimeMillis() - begin;
		if (result.isValid()) {
			LOG.info("OK [" + elapsed + " ms].");
		}
		else {
			List<String> messages = result.getMessages();
			LOG.info(messages.size() + " Problem(e) gefunden [" + elapsed + " ms]");
			for (String message : messages) {
				LOG.info(message);
			}
			throw new Exception("Das Hauptdokument ist nicht schema-valide.");
		}
	}

}
