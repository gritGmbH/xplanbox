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
package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollections;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.util.FeatureCollectionUtils;
import de.latlon.xplan.manager.CrsUtils;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.metadata.MetadataCouplingHandler;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.transaction.service.XPlanInsertService;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.manager.web.shared.PlanStatus.findByLegislationStatusCode;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanInsertManager extends XPlanTransactionManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanInsertManager.class);

	private final XPlanInsertService xPlanInsertService;

	public XPlanInsertManager(XPlanSynthesizer xPlanSynthesizer, XPlanDao xplanDao,
			XPlanRasterManager xPlanRasterManager, WorkspaceReloader workspaceReloader,
			ManagerConfiguration managerConfiguration, SortPropertyReader sortPropertyReader,
			XPlanInsertService xPlanInsertService, MetadataCouplingHandler metadataCouplingHandler) {
		super(xPlanSynthesizer, xplanDao, xPlanRasterManager, workspaceReloader, managerConfiguration,
				sortPropertyReader, metadataCouplingHandler);
		this.xPlanInsertService = xPlanInsertService;
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
		List<PlanImportData> importedPlansData = importPlans(archive, internalId, xPlanMetadata, crs, xPlanInstances);
		startCreationOfDataServicesCoupling(importedPlansData, crs);
		createRasterConfigurations(importedPlansData, makeRasterConfig);
		reloadWorkspace(importedPlansData);
		return importedPlansData.stream()
			.map(importedPlanData -> importedPlanData.getPlanId())
			.collect(Collectors.toList());
	}

	private List<PlanImportData> importPlans(XPlanArchive archive, String internalId, AdditionalPlanData xPlanMetadata,
			ICRS crs, XPlanFeatureCollections xPlanInstances) throws Exception {
		boolean gmlWithMultipleInstances = xPlanInstances.getxPlanGmlInstances().size() > 1;
		if (gmlWithMultipleInstances) {
			List<PlanImportData> plansToImport = importGmlWithMultipleInstances(archive, internalId, xPlanMetadata, crs,
					xPlanInstances);
			return xPlanInsertService.importPlans(plansToImport);
		}
		else {
			XPlanFeatureCollection xPlanInstance = xPlanInstances.getxPlanGmlInstances().get(0);
			PlanImportData planToImport = importGmlWithSingleInstance(archive, internalId, xPlanMetadata, crs,
					xPlanInstance);
			return xPlanInsertService.importPlans(Collections.singletonList(planToImport));
		}
	}

	private PlanImportData importGmlWithSingleInstance(XPlanArchive archive, String internalId,
			AdditionalPlanData xPlanMetadata, ICRS crs, XPlanFeatureCollection xPlanInstance) {
		PlanStatus selectedPlanStatus = xPlanMetadata.getPlanStatus();
		Date sortDate = sortPropertyReader.readSortDate(archive.getType(), archive.getVersion(),
				xPlanInstance.getFeatures());
		return new PlanImportData(archive, selectedPlanStatus, xPlanMetadata, sortDate, crs, xPlanInstance, internalId);
	}

	private List<PlanImportData> importGmlWithMultipleInstances(XPlanArchive archive, String internalId,
			AdditionalPlanData xPlanMetadata, ICRS crs, XPlanFeatureCollections xPlanInstances) {
		if (internalId != null || xPlanMetadata.getPlanStatus() != null) {
			LOG.warn(
					"XPlanGML contains multiple plan instances, internalId ({}) and selected planStatus ({}) are ignored.",
					internalId, xPlanMetadata.getPlanStatus());
		}
		List<PlanImportData> plansImportData = new ArrayList<>();
		for (XPlanFeatureCollection xPlanInstance : xPlanInstances.getxPlanGmlInstances()) {
			PlanStatus planStatusFromPlan = detectPlanStatus(archive.getType(), xPlanInstance);
			Date sortDate = sortPropertyReader.readSortDate(archive.getType(), archive.getVersion(),
					xPlanInstance.getFeatures());
			PlanImportData planImportData = new PlanImportData(archive, planStatusFromPlan, xPlanMetadata, sortDate,
					crs, xPlanInstance);

			plansImportData.add(planImportData);
		}
		LOG.info("Alle {} XPlan GML Instanzen aus dem XPlanArchiv wurden erfolgreich importiert.",
				xPlanInstances.getxPlanGmlInstances().size());
		return plansImportData;
	}

	private XPlanFeatureCollections readAndValidateMainDocument(XPlanArchive archive, ICRS crs, boolean force)
			throws Exception {
		performSchemaValidation(archive);
		try {
			XPlanFeatureCollections xPlanInstances = XPlanGmlParserBuilder.newBuilder()
				.withDefaultCrs(crs)
				.withFixOrientation(true)
				.build()
				.parseXPlanFeatureCollectionAllowMultipleInstances(archive);
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

	private void createRasterConfigurations(List<PlanImportData> importedPlansData, boolean makeRasterConfig) {
		if (makeRasterConfig) {
			for (PlanImportData importedPlanData : importedPlansData) {
				XPlanArchive archive = importedPlanData.getxPlanArchive();
				List<String> rasterRefsFileNamesToAdd = collectRasterScanFiles(importedPlanData.getxPlanFC());
				createRasterConfiguration(archive, rasterRefsFileNamesToAdd, importedPlanData.getPlanId(),
						archive.getType(), importedPlanData.getPlanStatus(), null, importedPlanData.getSortDate());
			}
		}
	}

	private List<String> collectRasterScanFiles(XPlanFeatureCollection fc) {
		List<String> scanFiles = new ArrayList<>();
		for (ExternalReference externalRef : fc.getExternalReferenceInfo().getRasterPlanBaseScans()) {
			scanFiles.add(externalRef.getReferenzUrl());
		}
		return scanFiles;
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

	private void startCreationOfDataServicesCoupling(List<PlanImportData> importedPlansData, ICRS crs) {
		for (PlanImportData importedPlanData : importedPlansData) {
			startCreationOfDataServicesCoupling(importedPlanData.getPlanId(), importedPlanData.getxPlanFC(), crs);
		}
	}

	private void reloadWorkspace(List<PlanImportData> importedPlansData) {
		for (PlanImportData importedPlanData : importedPlansData) {
			reloadWorkspace(importedPlanData.getPlanId());
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
