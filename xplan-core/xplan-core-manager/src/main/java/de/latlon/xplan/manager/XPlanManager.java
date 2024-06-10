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
package de.latlon.xplan.manager;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.archive.XPlanPartArchive;
import de.latlon.xplan.commons.feature.FeatureCollectionParseException;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollections;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfigurationAnalyser;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.dictionary.XPlanDictionaries;
import de.latlon.xplan.manager.dictionary.XPlanEnumerationFactory;
import de.latlon.xplan.manager.edit.XPlanToEditFactory;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.inspireplu.InspirePluPublisher;
import de.latlon.xplan.manager.transaction.UnsupportPlanException;
import de.latlon.xplan.manager.transaction.XPlanDeleteManager;
import de.latlon.xplan.manager.transaction.XPlanEditManager;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.PlanNameWithStatusResult;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.Rechtsstand;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.XPlanRasterEvaluator;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.deegree.commons.utils.Pair;
import org.deegree.commons.xml.XMLParsingException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveRechtsstand;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.createExternalRefAddedOrUpdated;
import static de.latlon.xplan.manager.web.shared.PlanStatus.findByLegislationStatusCode;
import static de.latlon.xplan.manager.web.shared.PlanStatus.valueOf;
import static de.latlon.xplan.manager.web.shared.Rechtsstand.UNKNOWN_RECHTSSTAND;
import static java.lang.Integer.parseInt;
import static org.apache.commons.io.IOUtils.closeQuietly;

/**
 * An instance of XPlanManager provides the service methods to manage instances of XPlan.
 * Supports XPlan version 4.x, 5.x. and 6.x.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanManager.class);

	private final XPlanArchiveCreator archiveCreator;

	private final XPlanDao xplanDao;

	private final XPlanExporter xPlanExporter;

	private final XPlanToEditFactory planToEditFactory = new XPlanToEditFactory();

	private final XPlanRasterManager xPlanRasterManager;

	private final XPlanRasterEvaluator xPlanRasterEvaluator;

	private final SortPropertyReader sortPropertyReader;

	private final InspirePluPublisher inspirePluPublisher;

	private final XPlanInsertManager xPlanInsertManager;

	private final XPlanEditManager xPlanEditManager;

	private final XPlanDeleteManager xPlanDeleteManager;

	/**
	 * @param xPlanDao mandatory XPlan data access object
	 * @param archiveCreator mandatory archive creator
	 * @param managerConfiguration mandatory manager configuration
	 * @param wmsWorkspaceWrapper mandatory WMS workspace configuration
	 * @param xPlanExporter
	 * @param sortPropertyReader
	 * @param inspirePluPublisher
	 * @param xPlanInsertManager
	 * @param xPlanEditManager
	 * @param xPlanDeleteManager
	 * @throws Exception if mandatory arguments are missing or something went wrong
	 */
	public XPlanManager(XPlanDao xPlanDao, XPlanArchiveCreator archiveCreator,
			ManagerConfiguration managerConfiguration, WmsWorkspaceWrapper wmsWorkspaceWrapper,
			XPlanExporter xPlanExporter, XPlanRasterEvaluator xPlanRasterEvaluator,
			XPlanRasterManager xPlanRasterManager, SortPropertyReader sortPropertyReader,
			InspirePluPublisher inspirePluPublisher, XPlanInsertManager xPlanInsertManager,
			XPlanEditManager xPlanEditManager, XPlanDeleteManager xPlanDeleteManager) throws Exception {
		if (xPlanDao == null || archiveCreator == null || managerConfiguration == null || wmsWorkspaceWrapper == null) {
			throw new IllegalArgumentException("Mandatory argument must not be null");
		}
		this.xPlanExporter = xPlanExporter;
		this.sortPropertyReader = sortPropertyReader;
		this.inspirePluPublisher = inspirePluPublisher;
		this.xPlanInsertManager = xPlanInsertManager;
		this.xPlanEditManager = xPlanEditManager;
		this.xPlanDeleteManager = xPlanDeleteManager;
		this.xplanDao = xPlanDao;
		this.archiveCreator = archiveCreator;
		this.xPlanRasterEvaluator = xPlanRasterEvaluator;
		this.xPlanRasterManager = xPlanRasterManager;

		ManagerConfigurationAnalyser managerConfigurationAnalyser = new ManagerConfigurationAnalyser(
				managerConfiguration, wmsWorkspaceWrapper);
		managerConfigurationAnalyser.checkConfiguration();
	}

	@SuppressFBWarnings(value = "PATH_TRAVERSAL_IN")
	public XPlanArchive analyzeArchive(String fileName) throws IOException {
		LOG.info("- Analyse des XPlanArchivs ('{}')...", fileName);
		XPlanArchive archive = archiveCreator.createXPlanArchive(new File(fileName));
		LOG.info("OK. {}", archive);
		return archive;
	}

	/**
	 * Import a plan into the managed database
	 * @param archiveFileName the file name of the archive, never <code>null</code>
	 * @param force should import be forced?
	 * @param makeRasterConfig <code>true</code> if the configuration of raster files
	 * should be created, <code>false</code> otherwise
	 */
	public void importPlan(String archiveFileName, boolean force, boolean makeRasterConfig) throws Exception {
		XPlanArchive archive = analyzeArchive(archiveFileName);
		if (archive.hasMultipleXPlanElements())
			throw new UnsupportPlanException("Das XPlanGML enthält mehrere XP_Plan-Elemente.");
		xPlanInsertManager.importPlan(archive, force, makeRasterConfig, null, null);
	}

	/**
	 * Import a plan into the managed database
	 * @param archive to import, never <code>null</code>
	 * @param force should import be forced?
	 * @param makeRasterConfig <code>true</code> if the configuration of raster files
	 * should be created, <code>false</code> otherwise
	 * @param planStatus the PlanStatus, may be <code>null</code>
	 */
	@PreAuthorize("hasRole('ROLE_XPLAN_SUPERUSER')")
	public void importPlan(XPlanArchive archive, boolean force, boolean makeRasterConfig, PlanStatus planStatus)
			throws Exception {
		xPlanInsertManager.importPlan(archive, force, makeRasterConfig, null, planStatus);
	}

	/**
	 * Determines the legislation status of the plan referenced by the given path to the
	 * archive.
	 * @param pathToArchive path to the file, never <code>null</code>
	 * @return the legislation status of a plan, code number is -1 if the status is not
	 * set
	 * @throws IOException
	 * @throws UnknownCRSException
	 * @throws XMLStreamException
	 */
	public Pair<Rechtsstand, PlanStatus> determineRechtsstand(String pathToArchive)
			throws IOException, XMLStreamException, UnknownCRSException {
		XPlanArchive archive = analyzeArchive(pathToArchive);
		XPlanFeatureCollection xPlanFeatureCollection = XPlanGmlParserBuilder.newBuilder()
			.withSkipResolveReferences(true)
			.build()
			.parseXPlanFeatureCollection(archive);
		return determineRechtsstandAndPlanstatus(xPlanFeatureCollection, archive.getType());
	}

	/**
	 * @param pathToArchive the absolute path to the XPlanArchive to evaluate.
	 * @return a list of {@link RasterEvaluationResult}, one for each referenced raster
	 * plan, may be empty but never <code>null</code>
	 * @throws IOException
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 * @throws XMLParsingException
	 * @throws WorkspaceException
	 */
	public List<RasterEvaluationResult> evaluateRasterdata(String pathToArchive)
			throws IOException, XMLStreamException, XMLParsingException, UnknownCRSException {
		XPlanArchive archive = analyzeArchive(pathToArchive);
		XPlanFeatureCollection fc = XPlanGmlParserBuilder.newBuilder()
			.withSkipResolveReferences(true)
			.build()
			.parseXPlanFeatureCollection(archive);
		return xPlanRasterEvaluator.evaluateRasterdata(archive, fc);
	}

	/**
	 * @param pathToArchive the absolute path to the XPlanArchive to evaluate.
	 * @param status the selected status, may be <code>null</code>
	 * @return the result of the evaluation, never <code>null</code>
	 * @throws IOException
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 */
	public List<PlanNameWithStatusResult> evaluatePlanNameAndStatus(String pathToArchive, String status)
			throws IOException, XMLStreamException, UnknownCRSException, FeatureCollectionParseException {
		LOG.info("- Analyse des Vorkommens eines Plans mit gleichem Namen und Planstatus...");
		XPlanArchive archive = analyzeArchive(pathToArchive);
		XPlanFeatureCollections xPlanFeatureCollections = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollectionAllowMultipleInstances(archive);
		return xPlanFeatureCollections.getxPlanGmlInstances().stream().map(xPlanFeatureCollection -> {
			String planName = xPlanFeatureCollection.getPlanName();
			PlanStatus planStatus;
			if (status != null) {
				planStatus = valueOf(status);
			}
			else {
				Pair<Rechtsstand, PlanStatus> rechtsstandPAndlanStatus = determineRechtsstandAndPlanstatus(
						xPlanFeatureCollection, archive.getType());
				planStatus = rechtsstandPAndlanStatus.second;
			}
			boolean planWithSameNameAndStatusExists = xplanDao.checkIfPlanWithSameNameAndStatusExists(planName,
					planStatus.getMessage());
			LOG.info("OK, Plan mit Namen '{}' und Status '{}' existiert: {}", planName, status,
					planWithSameNameAndStatusExists);
			return new PlanNameWithStatusResult(planName, planStatus, planWithSameNameAndStatusExists);
		}).collect(Collectors.toList());
	}

	/**
	 * @param xPlanToEdit
	 * @param uploadedArtefacts the absolute path to the XPlanArchive to evaluate.
	 * @return a list of {@link RasterEvaluationResult}, one for each referenced raster
	 * plan, may be {@link EmptyStackException} but never <code>null</code>
	 * @throws IOException
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 * @throws XMLParsingException
	 * @throws WorkspaceException
	 */
	public List<RasterEvaluationResult> evaluateRasterdata(XPlanToEdit xPlanToEdit, List<File> uploadedArtefacts)
			throws IOException, XMLParsingException {
		XPlanArchiveContentAccess archive = new XPlanPartArchive(uploadedArtefacts);
		ExternalReferenceInfo externalReferenceInfoToUpdate = createExternalRefAddedOrUpdated(xPlanToEdit,
				uploadedArtefacts);
		return xPlanRasterEvaluator.evaluateRasterdata(archive, externalReferenceInfoToUpdate);
	}

	/**
	 * Export a plan to the given stream
	 * @param planId plan id to export, never <code>null</code>
	 * @param outputStream the stream to write the representation. must be closed
	 * afterwards
	 * @throws Exception
	 */
	public void export(String planId, OutputStream outputStream) throws Exception {
		List<Artefact> artefacts = xplanDao.retrieveAllXPlanArtefacts(planId);
		xPlanExporter.export(outputStream, artefacts);
		LOG.info("XPlanArchiv {} wurde erfolgreich exportiert", planId);
	}

	/**
	 * Retrieve a list of all XPlans.
	 * @return list of XPlans
	 * @throws Exception
	 */
	public List<XPlan> list() throws Exception {
		return xplanDao.getXPlanList();
	}

	/**
	 * Retrieve a single {@link XPlan} by id.
	 * @param planId id of a plan, must not be <code>null</code>
	 * @return a single plan
	 * @throws Exception
	 */
	public XPlan getXPlanById(int planId) {
		return xplanDao.getXPlanById(planId);
	}

	/**
	 * Retrieve the bereiche of a {@link XPlan} by id.
	 * @param planId id of a plan, must not be <code>null</code>
	 * @return the bereiche of the plan with the passed id, may be <code>empty</code> but
	 * never <code>null</code>
	 * @throws Exception
	 */
	public List<Bereich> getBereicheOfPlanWithId(String planId) throws Exception {
		return xplanDao.retrieveBereicheOfPlanWithId(planId);
	}

	/**
	 * Retrieve a {@link XPlanToEdit} by id.
	 * @param plan plan to be retrieved to edit, never <code>null</code>
	 * @return the {@link XPlanToEdit}, <code>null</code> if not found
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_XPLAN_EDITOR') or hasRole('ROLE_XPLAN_SUPERUSER')")
	public XPlanToEdit getXPlanToEdit(XPlan plan) throws Exception {
		InputStream originalPlan = null;
		try {
			XPlanVersion version = XPlanVersion.valueOf(plan.getVersion());
			originalPlan = xplanDao.retrieveXPlanArtefact(plan.getId());
			FeatureCollection originalPlanFC = XPlanGmlParserBuilder.newBuilder()
				.build()
				.parseFeatureCollection(originalPlan, version);
			return planToEditFactory.createXPlanToEdit(plan, originalPlanFC);
		}
		finally {
			closeQuietly(originalPlan, null);
		}
	}

	/**
	 * Modifies the plan with the passed ID.
	 * @param oldXplan the {@link XPlan} describing the plan before update, never
	 * <code>null</code>
	 * @param xPlanToEdit the plan containing the edited field values, never
	 * <code>null</code>
	 * @param makeRasterConfig <code>true</code> if the raster configuration should be
	 * created, <code>false</code> otherwise
	 * @param uploadedArtefacts a list of artifacts uploaded by the user, may be empty but
	 * never <code>null</code>
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_XPLAN_EDITOR') or hasRole('ROLE_XPLAN_SUPERUSER')")
	public void editPlan(XPlan oldXplan, XPlanToEdit xPlanToEdit, boolean makeRasterConfig,
			List<File> uploadedArtefacts) throws Exception {
		xPlanEditManager.editPlan(oldXplan, xPlanToEdit, makeRasterConfig, uploadedArtefacts);
	}

	/**
	 * @param plan plan to delete
	 * @throws Exception
	 */
	@PreAuthorize("hasRole('ROLE_XPLAN_SUPERUSER')")
	public void delete(XPlan plan) throws Exception {
		String planId = plan.getId();
		delete(planId);
	}

	/**
	 * @param planId the plan id to delete should be used.
	 * @throws Exception
	 */
	public void delete(String planId) throws Exception {
		xPlanDeleteManager.delete(planId);
	}

	/**
	 * Transforms the plans with the passed ID to INSPIRE PLU and imports them in the
	 * INSPIRE Download Service for PLU.
	 * @param plan plan to transform and publish
	 */
	@PreAuthorize("hasRole('ROLE_XPLAN_SUPERUSER')")
	public void publishPlu(XPlan plan) throws Exception {
		if (inspirePluPublisher == null) {
			LOG.warn("Transformation and publishing INSPIRE PLU datasets is not supported");
			throw new Exception("Transformation and publishing INSPIRE PLU datasets is not supported");
		}
		else {
			String planId = plan.getId();
			inspirePluPublisher.transformAndPublish(planId, XPlanVersion.valueOf(plan.getVersion()));
		}
	}

	private Pair<Rechtsstand, PlanStatus> determineRechtsstandAndPlanstatus(
			XPlanFeatureCollection xPlanFeatureCollection, XPlanType type) {
		Rechtsstand rechtsstand = determineRechtsstand(xPlanFeatureCollection);
		PlanStatus planStatus = findByLegislationStatusCode(type.name(), rechtsstand.getCodeNumber());
		return new Pair<>(rechtsstand, planStatus);
	}

	private Rechtsstand determineRechtsstand(XPlanFeatureCollection xPlanFeatureCollection) {
		String legislationStatus = retrieveRechtsstand(xPlanFeatureCollection.getFeatures(),
				xPlanFeatureCollection.getType());
		if (legislationStatus != null && !legislationStatus.isEmpty()) {
			try {
				int legislationStatusCode = parseInt(legislationStatus);
				String legislationStatusTranslation = translateRechtsstand(xPlanFeatureCollection.getVersion(),
						xPlanFeatureCollection.getType(), legislationStatusCode);
				return new Rechtsstand(legislationStatusCode, legislationStatusTranslation);
			}
			catch (NumberFormatException e) {
				LOG.info("Legislationstatus '{}' could not be parsed as integer. Returning -1.", legislationStatus);
			}
		}
		return UNKNOWN_RECHTSSTAND;
	}

	private String translateRechtsstand(XPlanVersion version, XPlanType type, int rechtsstandCode) {
		XPlanDictionaries dictionaries = XPlanEnumerationFactory.get(version);
		String dictionaryId = findDictionaryId(type);
		try {
			return dictionaries.getTranslation(dictionaryId, Integer.toString(rechtsstandCode));
		}
		catch (Exception e) {
			LOG.error("Could not translate rechtsstand code {}: {}", rechtsstandCode, e.getMessage());
			LOG.trace("Error translating rechtsstand code", e);
			return null;
		}
	}

	private String findDictionaryId(XPlanType xPlanType) {
		switch (xPlanType) {
			case BP_Plan:
				return "BP_Rechtsstand";
			case FP_Plan:
				return "FP_Rechtsstand";
			case LP_Plan:
				return "LP_Rechtsstand";
			case RP_Plan:
				return "RP_Rechtsstand";
			default:
				break;
		}
		return "XP_Rechtsstand";
	}

}
