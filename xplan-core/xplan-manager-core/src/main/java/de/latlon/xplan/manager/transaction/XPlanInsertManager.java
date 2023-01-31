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

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollections;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.commons.util.FeatureCollectionUtils;
import de.latlon.xplan.manager.CrsUtils;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static de.latlon.xplan.manager.web.shared.PlanStatus.findByLegislationStatusCode;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanInsertManager extends XPlanTransactionManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanInsertManager.class);

	public XPlanInsertManager(XPlanSynthesizer xPlanSynthesizer, XPlanDao xplanDao, XPlanExporter xPlanExporter,
			XPlanRasterManager xPlanRasterManager, WorkspaceReloader workspaceReloader,
			ManagerConfiguration managerConfiguration, ManagerWorkspaceWrapper managerWorkspaceWrapper,
			SortPropertyReader sortPropertyReader) throws DataServiceCouplingException {
		super(xPlanSynthesizer, xplanDao, xPlanExporter, xPlanRasterManager, workspaceReloader, managerConfiguration,
				managerWorkspaceWrapper, sortPropertyReader);
	}

	/**
	 * @param archive to import, never <code>null</code>
	 * @param defaultCRS the default crs, may be <code>null</code> if no default crs
	 * should be set
	 * @param force should import be forced?
	 * @param makeRasterConfig <code>true</code> if the configuration of raster files
	 * should be created, <code>false</code> otherwise
	 * @param internalId is added to the feature collection of the plan, if
	 * <code>null</code>, internalId property is not added to the feature collection
	 * @param xPlanMetadata containing some metadata about the xplan, never
	 * <code>null</code>
	 * @throws Exception
	 * @return the id of the plan, never <code>null</code>
	 */
	public List<Integer> importPlan(XPlanArchive archive, ICRS defaultCRS, boolean force, boolean makeRasterConfig,
			String internalId, AdditionalPlanData xPlanMetadata) throws Exception {
		checkArchive(archive);
		LOG.info("- Importiere Plan " + archive);
		ICRS crs = CrsUtils.determineActiveCrs(defaultCRS, archive, LOG);
		XPlanFeatureCollections xPlanInstances = readAndValidateMainDocument(archive, crs, force);
		boolean gmlWithMultipleInstances = xPlanInstances.getxPlanGmlInstances().size() > 1;
		if (gmlWithMultipleInstances) {
			return importGmlWithMultipleInstances(archive, makeRasterConfig, internalId, xPlanMetadata, crs,
					xPlanInstances);
		}
		XPlanFeatureCollection xPlanInstance = xPlanInstances.getxPlanGmlInstances().get(0);
		return importGmlWithSingleInstance(archive, makeRasterConfig, internalId, xPlanMetadata, crs, xPlanInstance);
	}

	private List<Integer> importGmlWithSingleInstance(XPlanArchive archive, boolean makeRasterConfig, String internalId,
			AdditionalPlanData xPlanMetadata, ICRS crs, XPlanFeatureCollection xPlanInstance) throws Exception {
		PlanStatus selectedPlanStatus = xPlanMetadata.getPlanStatus();
		FeatureCollection synFc = createSynFeatures(xPlanInstance, archive.getVersion());
		if (internalId != null) {
			AppSchema synSchema = managerWorkspaceWrapper.lookupStore(XPLAN_SYN, selectedPlanStatus).getSchema();
			featureCollectionManipulator.addInternalId(synFc, synSchema, internalId);
		}
		int planId = importPlan(archive, makeRasterConfig, xPlanMetadata, crs, selectedPlanStatus, xPlanInstance,
				synFc);
		return Collections.singletonList(planId);
	}

	private List<Integer> importGmlWithMultipleInstances(XPlanArchive archive, boolean makeRasterConfig,
			String internalId, AdditionalPlanData xPlanMetadata, ICRS crs, XPlanFeatureCollections xPlanInstances)
			throws Exception {
		if (internalId != null || xPlanMetadata.getPlanStatus() != null) {
			LOG.warn(
					"XPlanGML contains multiple plan instances, internalId ({}) and selected planStatus ({}) are ignored.",
					internalId, xPlanMetadata.getPlanStatus());
		}
		List<Integer> planIds = new ArrayList<>();
		for (XPlanFeatureCollection xPlanInstance : xPlanInstances.getxPlanGmlInstances()) {
			PlanStatus planStatusFromPlan = detectPlanStatus(archive.getType(), xPlanInstance);
			FeatureCollection synFc = createSynFeatures(xPlanInstance, archive.getVersion());
			int planId = importPlan(archive, makeRasterConfig, xPlanMetadata, crs, planStatusFromPlan, xPlanInstance,
					synFc);
			planIds.add(planId);
		}
		LOG.info("Alle {} XPlan GML Instanzen aus dem XPlanArchiv wurden erfolgreich importiert.",
				xPlanInstances.getxPlanGmlInstances().size());
		return planIds;
	}

	private int importPlan(XPlanArchive archive, boolean makeRasterConfig, AdditionalPlanData xPlanMetadata, ICRS crs,
			PlanStatus selectedPlanStatus, XPlanFeatureCollection xPlanInstance, FeatureCollection synFc)
			throws Exception {
		Date sortDate = sortPropertyReader.readSortDate(archive.getType(), archive.getVersion(),
				xPlanInstance.getFeatures());
		int planId = xplanDao.insert(archive, xPlanInstance, synFc, selectedPlanStatus,
				xPlanMetadata.getStartDateTime(), xPlanMetadata.getEndDateTime(), sortDate, null);
		createRasterConfigurations(archive, makeRasterConfig, xPlanInstance, planId, selectedPlanStatus, sortDate);
		startCreationOfDataServicesCoupling(planId, xPlanInstance, crs);
		reloadWorkspace(planId);
		LOG.info("XPlanArchiv wurde erfolgreich importiert. Zugewiesene Id: " + planId);
		LOG.info("OK.");
		return planId;
	}

	private XPlanFeatureCollections readAndValidateMainDocument(XPlanArchive archive, ICRS crs, boolean force)
			throws Exception {
		performSchemaValidation(archive);
		try {
			XPlanFeatureCollections xPlanInstances = XPlanGmlParserBuilder.newBuilder().withDefaultCrs(crs)
					.withFixOrientation(true).build().parseXPlanFeatureCollectionAllowMultipleInstances(archive);
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

	private void createRasterConfigurations(XPlanArchive archive, boolean makeRasterConfig, XPlanFeatureCollection fc,
			int planId, PlanStatus planStatus, Date sortDate) throws Exception {
		if (makeRasterConfig) {
			createRasterConfiguration(archive, fc, planId, archive.getType(), planStatus, null, sortDate);
		}
	}

	private void performSchemaValidation(XPlanArchive archive) throws UnsupportPlanException {
		long begin = System.currentTimeMillis();
		LOG.info("- Schema-Validierung (Hauptdokument)...");
		SyntacticValidatorResult result;
		try {
			result = (SyntacticValidatorResult) new SyntacticValidatorImpl().validateSyntax(archive);
		}
		catch (Exception e) {
			throw new UnsupportPlanException(e.getMessage());
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
			throw new UnsupportPlanException("Das Hauptdokument ist nicht schema-valide.");
		}
	}

	private PlanStatus detectPlanStatus(XPlanType type, XPlanFeatureCollection xPlanInstance) {
		String rechtsstand = FeatureCollectionUtils.retrieveRechtsstand(xPlanInstance.getFeatures(),
				xPlanInstance.getType());
		if (rechtsstand != null) {
			try {
				int rechtsstandCode = Integer.parseInt(rechtsstand);
				return findByLegislationStatusCode(type.name(), rechtsstandCode);
			}
			catch (NumberFormatException e) {
			}
			LOG.warn("Could not parse legislation status code {} as integer", rechtsstand);
		}
		return PlanStatus.FESTGESTELLT;
	}

	private void checkArchive(XPlanArchive archive) throws UnsupportPlanException {
		if (archive.hasVerbundenerPlanBereich())
			throw new UnsupportPlanException(
					"Das XPlan GML Dokument beinhaltet Referenzen auf andere Plaene ueber die Relation verbundenerPlan. Der Import wird derzeit nicht unterstuetzt.");
	}

}
