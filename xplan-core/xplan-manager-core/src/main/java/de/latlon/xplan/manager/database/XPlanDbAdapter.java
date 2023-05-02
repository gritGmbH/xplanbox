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
package de.latlon.xplan.manager.database;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.ZipEntryWithContent;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.util.FeatureCollectionUtils;
import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.core.manager.db.model.ArtefactId;
import de.latlon.xplan.core.manager.db.model.ArtefactType;
import de.latlon.xplan.core.manager.db.model.Feature;
import de.latlon.xplan.core.manager.db.model.Plan;
import de.latlon.xplan.core.manager.db.model.PlanwerkWmsMetadata;
import de.latlon.xplan.core.manager.db.repository.ArtefactRepository;
import de.latlon.xplan.core.manager.db.repository.PlanRepository;
import de.latlon.xplan.core.manager.db.repository.PlanwerkWmsMetadataRepository;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.AbstractReference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplan.validator.web.shared.XPlanEnvelope;
import org.apache.commons.io.IOUtils;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.io.WKTWriter;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.io.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.MimetypesFileTypeMap;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveAdditionalTypeWert;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDistrict;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveRechtsstandWert;
import static de.latlon.xplan.core.manager.db.model.ArtefactType.RASTERBASIS;
import static de.latlon.xplan.core.manager.db.model.ArtefactType.XPLANGML;
import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static org.apache.commons.io.IOUtils.copyLarge;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class XPlanDbAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanDbAdapter.class);

	private final CategoryMapper categoryMapper;

	private final PlanRepository planRepository;

	private final PlanwerkWmsMetadataRepository planwerkWmsMetadataRepository;

	private final ArtefactRepository artefactRepository;

	public XPlanDbAdapter(CategoryMapper categoryMapper, PlanRepository planRepository,
			PlanwerkWmsMetadataRepository planwerkWmsMetadataRepository, ArtefactRepository artefactRepository) {
		this.categoryMapper = categoryMapper;
		this.planRepository = planRepository;
		this.planwerkWmsMetadataRepository = planwerkWmsMetadataRepository;
		this.artefactRepository = artefactRepository;
	}

	public int insert(XPlanArchive archive, XPlanFeatureCollection fc, FeatureCollection synFc, PlanStatus planStatus,
			Date beginValidity, Date endValidity, Date sortDate, String internalId, List<String> wfsFeatureIds)
			throws Exception {
		LOG.info("Insert XPlan in XPlanDB");
		Plan plan = createPlan(archive, fc, synFc, planStatus, beginValidity, endValidity, sortDate, internalId,
				wfsFeatureIds);
		Plan savedPlan = planRepository.save(plan);
		return savedPlan.getId();
	}

	public void insertArtefacts(XPlanFeatureCollection xPlanFeatureCollection, XPlanArchive archive, int planId)
			throws Exception {
		LOG.info("Insert XPlan in XPlanDB");
		Plan plan = getRequiredPlanById(planId);
		List<ZipEntryWithContent> archiveEntries = xPlanFeatureCollection.getArchiveEntries(archive);
		AtomicInteger i = new AtomicInteger();
		Set<Artefact> artefacts = archiveEntries.stream()
				.map(archiveEntry -> createArtefact(plan, xPlanFeatureCollection, i, archiveEntry))
				.collect(Collectors.toSet());
		plan.setArtefacts(artefacts);
		planRepository.save(plan);
	}

	public void insertOrReplacePlanWerkWmsMetadata(int planId, String title, String resourceIdentifier,
			String datasetMetadataUrl, String serviceMetadataUrl) {
		LOG.info("Insert PlanWerkWmsMetadata");
		PlanwerkWmsMetadata planwerkWmsMetadata = new PlanwerkWmsMetadata().plan(planId).title(title)
				.resourceidentifier(resourceIdentifier).servicemetadataurl(serviceMetadataUrl)
				.datametadataurl(datasetMetadataUrl);
		planwerkWmsMetadataRepository.save(planwerkWmsMetadata);
	}

	public void deletePlan(int planId) throws Exception {
		planRepository.deleteById(planId);
	}

	/**
	 * @param oldXplan the {@link XPlan} describing the plan before update, never
	 * <code>null</code>
	 * @param newAdditionalPlanData of the {@link XPlan} with the updated values, never
	 * <code>null</code>
	 * @param fc the edited feature collection, never <code>null</code>
	 * @param synFc the edited feature collection with synthesized features, never
	 * <code>null</code>
	 * @param planArtefact the edited xplan gml, never <code>null</code>
	 * @param sortDate the date added to syn feature collection, may be <code>null</code>
	 * @param removedRefs
	 * @throws Exception
	 */
	public void update(XPlan oldXplan, AdditionalPlanData newAdditionalPlanData, XPlanFeatureCollection fc,
			FeatureCollection synFc, byte[] planArtefact, XPlanToEdit xPlanToEdit, Date sortDate,
			List<File> uploadedArtefacts, Set<String> removedRefs) throws Exception {
		int planId = Integer.parseInt(oldXplan.getId());
		LOG.info("- Aktualisierung der XPlan-Artefakte von Plan mit ID '{}'", planId);
		Plan plan = getRequiredPlanById(planId);
		updatePlan(oldXplan, newAdditionalPlanData, fc, synFc, planArtefact, xPlanToEdit, sortDate, uploadedArtefacts,
				removedRefs, planId, plan);
		planRepository.save(plan);

	}

	public void updateFids(int planId, List<String> fids) throws Exception {
		LOG.info("- Aktualisierung der XPlan-Features von Plan mit ID '{}'", planId);
		Plan plan = getRequiredPlanById(planId);
		Set<Feature> newFeatures = createFeatures(fids);
		plan.features(newFeatures);
		planRepository.save(plan);
	}

	/**
	 * Updates the district column of the table xplanmgr.plans.
	 * @param planId id of the plan to update, never <code>null</code>
	 * @param district the new district, may be <code>null</code>
	 * @throws Exception
	 */
	@Transactional(rollbackOn = Exception.class)
	public void updateDistrict(int planId, String district) throws Exception {
		Plan plan = getRequiredPlanById(planId);
		plan.setDistrict(district);
		planRepository.save(plan);
	}

	/**
	 * Updates the district column of the table xplanmgr.plans.
	 * @param planId id of the plan to update, never <code>null</code>
	 * @param bereiche the bereiche, never <code>null</code>
	 * @throws Exception
	 */
	public void updateBereiche(int planId, List<Bereich> bereiche) throws Exception {
		Plan plan = getRequiredPlanById(planId);
		plan.setBereiche(createBereiche(bereiche));
		planRepository.save(plan);
	}

	/**
	 * Updates the column artefacttype of the table xplanmgr.artefacts.
	 * @param planId of the plan to update, never <code>null</code>
	 * @param fileNames the fileNames to update, never <code>null</code>
	 * @param artefactType the artefactType to set, never <code>null</code>
	 * @throws SQLException
	 */
	@Transactional
	public void updateArtefacttype(int planId, List<String> fileNames, ArtefactType artefactType) {
		Stream<Artefact> artefacts = artefactRepository.findAllByPlanId(planId);
		for (String rasterReference : fileNames) {
			Optional<Artefact> artefact = artefacts.filter(candidate -> candidate.getData().equals(rasterReference))
					.findFirst();
			if (artefact.isPresent()) {
				Artefact artefactToUpdate = artefact.get();
				artefactToUpdate.artefacttype(artefactType);
				artefactRepository.save(artefactToUpdate);
			}
		}
	}

	/**
	 * @param planId of the plan to update, never <code>null</code>
	 * @throws Exception if the sql could not be executed
	 */
	public void updatePlanWasInspirePublished(int planId) throws Exception {
		Plan plan = getRequiredPlanById(planId);
		plan.setInspirepublished(true);
		planRepository.save(plan);
	}

	public XPlanVersionAndPlanStatus selectXPlanMetadata(int planId) throws Exception {
		Plan plan = getRequiredPlanById(planId);
		XPlanVersion version = plan.getVersion();
		PlanStatus planStatus = retrievePlanStatus(plan.getPlanstatus());
		return new XPlanVersionAndPlanStatus(version, planStatus);
	}

	public Set<String> selectFids(int planId) throws Exception {
		Plan plan = getRequiredPlanById(planId);
		return plan.getFeatures().stream().map(feature -> feature.getFid()).collect(Collectors.toSet());
	}

	/**
	 * Retrieve a list of all XPlans.
	 * @return list of XPlans
	 * @throws Exception
	 */
	public List<XPlan> selectAllXPlans() {
		Iterable<Plan> plans = planRepository.findAll();
		return StreamSupport.stream(plans.spliterator(), false).map(plan -> convertToXPlan(plan))
				.collect(Collectors.toList());
	}

	/**
	 * Retrieve a single {@link XPlan} by id.
	 * @param planId id of a plan, must not be <code>null</code>
	 * @return a single plan
	 * @throws Exception
	 */
	public XPlan selectXPlanById(int planId) {
		Optional<Plan> optionalPlan = planRepository.findById(planId);
		if (!optionalPlan.isPresent())
			return null;
		return convertToXPlan(optionalPlan.get());
	}

	public boolean selectPlanWithSameNameAndStatusExists(String planName, String status) {
		return planRepository.existsPlanByNameAndPlanstatus(planName, status);
	}

	public boolean existsPlan(int id) {
		return planRepository.existsPlanById(id);
	}

	public List<XPlan> getXPlanByName(String planName) {
		List<Plan> plans = planRepository.findByName(planName);
		return plans.stream().map(plan -> convertToXPlan(plan)).collect(Collectors.toList());
	}

	public List<XPlan> getXPlansLikeName(String planName) {
		List<Plan> plans = planRepository.findByNameLike(planName);
		return plans.stream().map(plan -> convertToXPlan(plan)).collect(Collectors.toList());
	}

	/**
	 * retrieves the id of the plan closest in future to the date passed
	 * @param releaseDate minimal release date
	 * @return id of plan with minimal release date
	 * @throws SQLException
	 */
	public String selectXPlanIdOfMoreRecentRasterPlan(Date releaseDate) {
		List<Plan> plan = planRepository.findByPlanWithMoreRecentRasterPlan(releaseDate);
		if (plan.isEmpty())
			return null;
		return Integer.toString(plan.get(0).getId());
	}

	/**
	 * exports a plan
	 * @param planId of plan to export
	 * @return
	 * @throws Exception
	 */
	public Stream<Artefact> selectAllXPlanArtefacts(int planId) {
		return artefactRepository.findAllByPlanId(planId);
	}

	/**
	 * @param planId the id of the requested plan, <code>null</code>
	 * @return the original plan artefact, never <code>null</code>
	 * @throws Exception
	 */
	public InputStream selectXPlanGmlArtefact(int planId) throws IOException {
		Optional<Artefact> xPlanGmlByPlan = artefactRepository.findXPlanGmlByPlan(planId);
		if (xPlanGmlByPlan.isPresent()) {
			Artefact artefact = xPlanGmlByPlan.get();
			return unzipArtefact(artefact.getData());
		}
		return null;

	}

	/**
	 * Retrieve internalId by the manager id from xplanmgr.plans.
	 * @param planId the planId of the plan, never <code>null</code>
	 * @return the internal id of a plan (if available), <code>null</code> if an error
	 * occurred
	 */
	public String selectInternalId(int planId) {
		Optional<Plan> plan = planRepository.findById(planId);
		if (plan.isPresent()) {
			return plan.get().getInternalid();
		}
		return null;
	}

	public void updateFeatureMetadata(int planId, List<String> fids) throws Exception {
		LOG.info("- Aktualisiere Features von XPlan " + planId + " in der Manager-DB.");
		updateFids(planId, fids);
	}

	private void collectArtefactsToUpdateAndInsert(List<File> uploadedArtefacts, List<String> artefactFileNames,
			Map<String, File> artefactsToUpdate, Map<String, File> artefactsToInsert, String refFileName)
			throws Exception {
		LOG.debug("Handle reference '{}'.", refFileName);
		if (refFileName.startsWith("http")) {
			LOG.debug("Found http reference, update of artefacts is not required.");
			return;
		}
		File uploadedFile = retrieveUploadedArtefact(refFileName, uploadedArtefacts);
		boolean isStoredInArtefactsTable = artefactFileNames.contains(refFileName);
		if (uploadedFile != null) {
			LOG.debug("Reference was uploaded, update in DB required.");
			if (isStoredInArtefactsTable) {
				artefactsToUpdate.put(refFileName, uploadedFile);
			}
			else {
				artefactsToInsert.put(refFileName, uploadedFile);
			}
		}
		else if (isStoredInArtefactsTable) {
			LOG.debug("Reference was not changed");
		}
		else {
			throw new Exception("Could not find referenced artefact with name " + refFileName);
		}
	}

	private File retrieveUploadedArtefact(String refFileName, List<File> uploadedArtefacts) {
		if (uploadedArtefacts != null) {
			for (File uploadedArtefact : uploadedArtefacts) {
				if (refFileName.equals(uploadedArtefact.getName()))
					return uploadedArtefact;
			}
		}
		return null;
	}

	private List<String> retrieveReferenceFileNames(XPlanToEdit xPlanToEdit) {
		List<String> referenceFileNames = new ArrayList<>();
		addReferences(referenceFileNames, xPlanToEdit.getTexts());
		addReferences(referenceFileNames, xPlanToEdit.getReferences());
		xPlanToEdit.getRasterBasis().forEach(rasterBasis -> {
			if (rasterBasis.getRasterReferences() != null)
				addReferences(referenceFileNames, rasterBasis.getRasterReferences());
		});
		return referenceFileNames;
	}

	private void addReferences(List<String> referenceFileNames, List<? extends AbstractReference> references) {
		for (AbstractReference ref : references) {
			String reference = ref.getReference();
			if (reference != null && !"".equals(reference))
				referenceFileNames.add(reference);
			String georeference = ref.getGeoReference();
			if (georeference != null && !"".equals(georeference))
				referenceFileNames.add(georeference);
		}
	}

	private void checkBereichNummern(List<Bereich> bereiche) throws AmbiguousBereichNummernException {
		List<String> bereichNummern = new ArrayList<>();
		for (Bereich bereich : bereiche) {
			String nummer = bereich.getNummer();
			if (bereichNummern.contains(nummer))
				throw new AmbiguousBereichNummernException(nummer);
			bereichNummern.add(nummer);
		}
	}

	private String getArtefactMimeType(String fileName) {
		MimetypesFileTypeMap mimeMap = new MimetypesFileTypeMap();
		mimeMap.addMimeTypes("text/xml gml xml");
		mimeMap.addMimeTypes("application/pdf pdf");
		mimeMap.addMimeTypes("application/zip zip");
		mimeMap.addMimeTypes("image/jpeg jpg jpeg");
		mimeMap.addMimeTypes("image/png png");
		mimeMap.addMimeTypes("image/tiff tif tiff");
		mimeMap.addMimeTypes("image/ecw ecw");
		mimeMap.addMimeTypes("text/html html");
		mimeMap.addMimeTypes("text/plain txt text");
		return mimeMap.getContentType(fileName);
	}

	private byte[] createZipArtefact(InputStream is) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gos = new GZIPOutputStream(bos);
		copyLarge(is, gos);
		gos.close();
		return bos.toByteArray();
	}

	private String createWktFromBboxIn4326(XPlanFeatureCollection fc) {
		Envelope bboxIn4326 = fc.getBboxIn4326();
		if (bboxIn4326 != null)
			return WKTWriter.write(bboxIn4326);
		return null;
	}

	private AdditionalPlanData createXPlanMetadata(String planStatus, Date startDateTime, Date endDateTime) {
		PlanStatus planStatusAsEnum = null;
		if (planStatus != null)
			planStatusAsEnum = PlanStatus.findByMessage(planStatus);
		return new AdditionalPlanData(planStatusAsEnum, startDateTime, endDateTime);
	}

	private ArtefactType detectArtefactType(XPlanFeatureCollection xPlanFeatureCollection,
			ZipEntryWithContent archiveEntry) {
		if (archiveEntry.isXPlanGml()) {
			return XPLANGML;
		}
		return detectNonXPlanGmlArtefactType(xPlanFeatureCollection, archiveEntry.getName());
	}

	private ArtefactType detectNonXPlanGmlArtefactType(XPlanFeatureCollection xPlanFeatureCollection, String name) {
		List<ExternalReference> rasterPlanBaseAndUpdateScans = xPlanFeatureCollection.getExternalReferenceInfo()
				.getRasterPlanBaseAndUpdateScans();
		boolean isRasterBasis = rasterPlanBaseAndUpdateScans.stream()
				.anyMatch(rasterPlanBaseAndUpdateScan -> name.equals(rasterPlanBaseAndUpdateScan.getReferenzUrl()));
		if (isRasterBasis)
			return RASTERBASIS;
		return null;
	}

	private PlanStatus retrievePlanStatus(String planStatusMessage) {
		if (planStatusMessage != null && planStatusMessage.length() > 0)
			return PlanStatus.findByMessage(planStatusMessage);
		return FESTGESTELLT;
	}

	private String retrievePlanStatusMessage(PlanStatus planStatus) {
		if (planStatus != null)
			return planStatus.getMessage();
		return FESTGESTELLT.getMessage();
	}

	private InputStream unzipArtefact(byte[] zippedData) throws IOException {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(zippedData);
				GZIPInputStream is = new GZIPInputStream(bis);
				ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			IOUtils.copy(is, bos);
			return new ByteArrayInputStream(bos.toByteArray());
		}
	}

	private Plan getRequiredPlanById(int planId) throws PlanNotFoundException {
		Optional<Plan> optionalPlan = planRepository.findById(planId);
		if (!optionalPlan.isPresent())
			throw new PlanNotFoundException(planId);
		return optionalPlan.get();
	}

	private Plan createPlan(XPlanArchive archive, XPlanFeatureCollection fc, FeatureCollection synFc,
			PlanStatus planStatus, Date beginValidity, Date endValidity, Date sortDate, String internalId,
			List<String> wfsFeatureIds) throws ParseException, AmbiguousBereichNummernException {
		String wktFromBboxIn4326 = createWktFromBboxIn4326(fc);
		org.locationtech.jts.geom.Geometry bbox = new org.locationtech.jts.io.WKTReader().read(wktFromBboxIn4326);
		Plan plan = new Plan().importDate(new Date(System.currentTimeMillis())).version(archive.getVersion())
				.type(archive.getType()).name(fc.getPlanName()).nummer(fc.getPlanNummer()).gkz(fc.getPlanGkz())
				.hasRaster(fc.getHasRaster()).rechtsstand(retrieveRechtsstandWert(synFc, archive.getType()))
				.releaseDate(fc.getPlanReleaseDate()).sonstPlanArt(retrieveAdditionalTypeWert(synFc, archive.getType()))
				.planstatus(retrievePlanStatusMessage(planStatus))
				.district(retrieveDistrict(fc.getFeatures(), archive.getType())).wmssortdate(sortDate)
				.gueltigkeitbeginn(beginValidity).gueltigkeitende(endValidity).internalid(internalId).bbox(bbox)
				.bereiche(createBereiche(synFc)).features(createFeatures(wfsFeatureIds));
		return plan;
	}

	private Set<de.latlon.xplan.core.manager.db.model.Bereich> createBereiche(FeatureCollection synFc)
			throws AmbiguousBereichNummernException {
		List<Bereich> bereiche = FeatureCollectionUtils.retrieveBereiche(synFc);
		checkBereichNummern(bereiche);
		return createBereiche(bereiche);
	}

	private Set<de.latlon.xplan.core.manager.db.model.Bereich> createBereiche(List<Bereich> bereiche) {
		return bereiche.stream().map(bereich -> new de.latlon.xplan.core.manager.db.model.Bereich()
				.name(bereich.getName()).nummer(bereich.getNummer())).collect(Collectors.toSet());
	}

	private Set<Feature> createFeatures(List<String> featureIds) {
		AtomicInteger index = new AtomicInteger();
		return featureIds.stream().map(featureId -> new Feature().fid(featureId).num(index.getAndIncrement()))
				.collect(Collectors.toSet());
	}

	private Artefact createArtefact(Plan plan, XPlanFeatureCollection xPlanFeatureCollection, AtomicInteger i,
			ZipEntryWithContent archiveEntry) {
		try {
			String name = archiveEntry.getName();
			InputStream is = archiveEntry.retrieveContentAsStream();
			byte[] data = createZipArtefact(is);
			String mimetype = getArtefactMimeType(name);
			ArtefactType artefactType = detectArtefactType(xPlanFeatureCollection, archiveEntry);
			ArtefactId id = new ArtefactId().plan(plan).filename(name);
			Artefact artefact = new Artefact().id(id).data(data).mimetype(mimetype).artefacttype(artefactType)
					.num(i.getAndIncrement());
			return artefact;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void updatePlan(XPlan oldXplan, AdditionalPlanData newAdditionalPlanData, XPlanFeatureCollection fc,
			FeatureCollection synFc, byte[] planArtefact, XPlanToEdit xPlanToEdit, Date sortDate,
			List<File> uploadedArtefacts, Set<String> removedRefs, int planId, Plan plan) throws Exception {
		XPlanType type = XPlanType.valueOf(oldXplan.getType());
		plan.name(fc.getPlanName()).rechtsstand(retrieveRechtsstandWert(synFc, type))
				.sonstPlanArt(retrieveAdditionalTypeWert(synFc, type)).wmssortdate(sortDate)
				.gueltigkeitbeginn(newAdditionalPlanData.getStartDateTime())
				.gueltigkeitende(newAdditionalPlanData.getEndDateTime())
				.planstatus(retrievePlanStatusMessage(newAdditionalPlanData.getPlanStatus()));

		Set<Artefact> planArtefacts = plan.getArtefacts();
		Optional<Integer> optionalNum = planArtefacts.stream().map(artefact -> artefact.getNum())
				.max(Integer::compareTo);
		int num = optionalNum.isPresent() ? optionalNum.get() : 0;
		Optional<Artefact> optionalArtefact = planArtefacts.stream()
				.filter(artefact -> XPLANGML.equals(artefact.getArtefacttype())).findFirst();
		if (!optionalArtefact.isPresent())
			throw new Exception("Plan mit ID " + planId
					+ " hat kein Artefakt vom Typ XPLANGML. Plan kann nicht aktualisiert werden.");
		Artefact xPlanGmlArtefact = optionalArtefact.get();
		xPlanGmlArtefact.data(createZipArtefact(new ByteArrayInputStream(planArtefact)));

		List<Artefact> artefactsToDelete = planArtefacts.stream()
				.filter(artefact -> removedRefs.contains(artefact.getId().getFilename())).collect(Collectors.toList());
		planArtefacts.removeAll(artefactsToDelete);

		List<String> referenceFileNames = retrieveReferenceFileNames(xPlanToEdit);
		List<String> artefactFileNames = planArtefacts.stream().map(artefact -> artefact.getId().getFilename())
				.collect(Collectors.toList());
		Map<String, File> artefactsToUpdate = new HashMap<>();
		Map<String, File> artefactsToInsert = new HashMap<>();
		for (String refFileName : referenceFileNames) {
			collectArtefactsToUpdateAndInsert(uploadedArtefacts, artefactFileNames, artefactsToUpdate,
					artefactsToInsert, refFileName);
		}
		for (Map.Entry<String, File> entry : artefactsToUpdate.entrySet()) {
			String fileName = entry.getKey();
			File file = entry.getValue();
			Optional<Artefact> artefactToUpdate = planArtefacts.stream()
					.filter(artefact -> fileName.equals(artefact.getId().getFilename())).findFirst();
			if (artefactToUpdate.isPresent()) {
				try (FileInputStream fileInputStream = new FileInputStream(file)) {
					artefactToUpdate.get().data(createZipArtefact(fileInputStream));
				}
			}
		}
		for (Map.Entry<String, File> entry : artefactsToInsert.entrySet()) {
			String fileName = entry.getKey();
			File file = entry.getValue();
			try (FileInputStream fileInputStream = new FileInputStream(file)) {
				byte[] data = createZipArtefact(fileInputStream);
				String mimetype = getArtefactMimeType(fileName);
				ArtefactType artefactType = detectNonXPlanGmlArtefactType(fc, fileName);

				ArtefactId id = new ArtefactId().plan(plan).filename(fileName);
				Artefact artefact = new Artefact().id(id).data(data).mimetype(mimetype).artefacttype(artefactType)
						.num(num++);
				plan.getArtefacts().add(artefact);
			}
		}
	}

	private XPlan convertToXPlan(Plan plan) {
		String name = plan.getName();
		XPlan xPlan = new XPlan((name != null ? name : "-"), Integer.toString(plan.getId()), plan.getType().name());
		xPlan.setVersion(plan.getVersion().name());
		xPlan.setNumber(plan.getNummer() != null ? plan.getNummer() : "-");
		xPlan.setGkz(plan.getGkz());
		xPlan.setRaster(plan.getHasRaster());
		xPlan.setAdditionalType(plan.getSonstPlanArt());
		xPlan.setLegislationStatus(plan.getRechtsstand());
		xPlan.setReleaseDate(plan.getReleaseDate());
		xPlan.setImportDate(plan.getImportDate());
		XPlanEnvelope bbox = convertToXPlanEnvelope(plan);

		xPlan.setBbox(bbox);
		xPlan.setXplanMetadata(
				createXPlanMetadata(plan.getPlanstatus(), plan.getGueltigkeitbeginn(), plan.getGueltigkeitende()));
		xPlan.setDistrict(categoryMapper.mapToCategory(plan.getDistrict()));
		xPlan.setInspirePublished(plan.getInspirepublished());
		xPlan.setInternalId(plan.getInternalid());
		List<Bereich> bereiche = plan.getBereiche().stream().map(bereich -> {
			Bereich newBereich = new Bereich();
			newBereich.setNummer(bereich.getNummer());
			newBereich.setName(bereich.getName());
			return newBereich;
		}).collect(Collectors.toList());
		xPlan.setBereiche(bereiche);
		return xPlan;
	}

	private XPlanEnvelope convertToXPlanEnvelope(Plan plan) {
		org.locationtech.jts.geom.Geometry bbox = plan.getBbox();
		Coordinate[] coordinates = bbox.getCoordinates();
		double minx = 0, miny = 0, maxx = 0, maxy = 0;
		boolean first = true;
		for (Coordinate coordinate : coordinates) {
			if (first) {
				minx = coordinate.x;
				miny = coordinate.y;
				maxx = coordinate.x;
				maxy = coordinate.y;
			}
			else {
				minx = Math.min(coordinate.x, minx);
				miny = Math.min(coordinate.y, miny);
				maxx = Math.max(coordinate.x, maxx);
				maxy = Math.max(coordinate.y, maxy);
			}
			first = false;
		}
		return new XPlanEnvelope(minx, miny, maxx, maxy, "EPSG:4326");
	}

}