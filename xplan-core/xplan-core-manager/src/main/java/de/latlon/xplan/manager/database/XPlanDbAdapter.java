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
import de.latlon.xplan.manager.edit.EditedArtefact;
import de.latlon.xplan.manager.edit.EditedArtefacts;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.XPlanEnvelope;
import org.apache.commons.io.IOUtils;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.io.WKTWriter;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.io.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import static de.latlon.xplan.commons.util.MimeTypeDetector.getArtefactMimeType;
import static de.latlon.xplan.core.manager.db.model.ArtefactType.RASTERBASIS;
import static de.latlon.xplan.core.manager.db.model.ArtefactType.XPLANGML;
import static de.latlon.xplan.manager.edit.ArtefactType.RASTER;
import static de.latlon.xplan.manager.edit.EditType.ADDED;
import static de.latlon.xplan.manager.edit.EditType.REMOVED;
import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static org.apache.commons.io.IOUtils.copyLarge;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class XPlanDbAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanDbAdapter.class);

	private final CategoryMapper categoryMapper;

	private final PlanRepository planRepository;

	private final PlanwerkWmsMetadataRepository planwerkWmsMetadataRepository;

	private final ArtefactRepository artefactRepository;

	/**
	 * @param categoryMapper may be <code>null</code>
	 * @param planRepository never <code>null</code>
	 * @param planwerkWmsMetadataRepository never <code>null</code>
	 * @param artefactRepository never <code>null</code>
	 */
	public XPlanDbAdapter(CategoryMapper categoryMapper, PlanRepository planRepository,
			PlanwerkWmsMetadataRepository planwerkWmsMetadataRepository, ArtefactRepository artefactRepository) {
		this.categoryMapper = categoryMapper;
		this.planRepository = planRepository;
		this.planwerkWmsMetadataRepository = planwerkWmsMetadataRepository;
		this.artefactRepository = artefactRepository;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public int insert(XPlanArchive archive, XPlanFeatureCollection fc, PlanStatus planStatus, Date beginValidity,
			Date endValidity, Date sortDate, String internalId) throws Exception {
		LOG.info("Insert XPlan in XPlanDB");
		Plan plan = createPlan(archive, fc, planStatus, beginValidity, endValidity, sortDate, internalId);
		Plan savedPlan = planRepository.save(plan);
		return savedPlan.getId();
	}

	/**
	 * @param planId id of the plan to insert artefacts
	 * @param xPlanFeatureCollection used to retrieve the artefacts to insert, never
	 * <code>null</code>
	 * @param archive containing the artefacts to insert, never <code>null</code>
	 * @param xplanGml overwrites the original XPlanGml from the archive, if
	 * <code>null</code> the original XPlanGML is inserted
	 * @throws PlanNotFoundException if a plan with the passed id does not exist
	 */
	@Transactional(propagation = Propagation.MANDATORY)
	public void insertArtefacts(int planId, XPlanFeatureCollection xPlanFeatureCollection, XPlanArchive archive,
			byte[] xplanGml) throws PlanNotFoundException {
		LOG.info("Insert XPlan in XPlanDB");
		Plan plan = getRequiredPlanById(planId);
		List<ZipEntryWithContent> archiveEntries = xPlanFeatureCollection.getArchiveEntries(archive);
		AtomicInteger i = new AtomicInteger();
		Set<Artefact> artefacts = archiveEntries.stream()
			.map(archiveEntry -> createArtefact(plan, xPlanFeatureCollection, i, archiveEntry, xplanGml))
			.collect(Collectors.toSet());
		plan.setArtefacts(artefacts);
		planRepository.save(plan);
	}

	public void insertOrReplacePlanWerkWmsMetadata(int planId, String title, String resourceIdentifier,
			String datasetMetadataUrl, String serviceMetadataUrl) {
		LOG.info("Insert PlanWerkWmsMetadata");
		PlanwerkWmsMetadata planwerkWmsMetadata = new PlanwerkWmsMetadata().plan(planId)
			.title(title)
			.resourceidentifier(resourceIdentifier)
			.servicemetadataurl(serviceMetadataUrl)
			.datametadataurl(datasetMetadataUrl);
		planwerkWmsMetadataRepository.save(planwerkWmsMetadata);
	}

	@Transactional(propagation = Propagation.MANDATORY)
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
	 * @param uploadedArtefacts list of uploaded files, may be empty but never
	 * <code>null</code>
	 * @param editedArtefacts describing the edited artefacts, never <code>null</code>
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.MANDATORY)
	public void update(XPlan oldXplan, AdditionalPlanData newAdditionalPlanData, XPlanFeatureCollection fc,
			FeatureCollection synFc, byte[] planArtefact, Date sortDate, List<File> uploadedArtefacts,
			EditedArtefacts editedArtefacts) throws Exception {
		int planId = Integer.parseInt(oldXplan.getId());
		LOG.info("- Aktualisierung der XPlan-Artefakte von Plan mit ID '{}'", planId);
		Plan plan = getRequiredPlanById(planId);
		updatePlan(oldXplan, newAdditionalPlanData, fc, synFc, planArtefact, sortDate, uploadedArtefacts,
				editedArtefacts, planId, plan);
		planRepository.save(plan);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void update(int planId, XPlanType type, FeatureCollection synFc) throws Exception {
		LOG.info("- Aktualisierung des Plans mit ID '{}'", planId);
		Plan plan = getRequiredPlanById(planId).rechtsstand(retrieveRechtsstandWert(synFc, type))
			.sonstPlanArt(retrieveAdditionalTypeWert(synFc, type))
			.bereiche(createBereiche(synFc));
		planRepository.save(plan);
	}

	@Transactional(propagation = Propagation.MANDATORY)
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
	@Transactional(rollbackFor = Exception.class)
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
	@Transactional
	public void updateBereiche(int planId, List<Bereich> bereiche) throws Exception {
		Plan plan = getRequiredPlanById(planId);
		plan.setBereiche(createBereiche(bereiche));
		planRepository.save(plan);
	}

	/**
	 * @param planId of the plan to update, never <code>null</code>
	 * @throws Exception if the sql could not be executed
	 */
	@Transactional
	public void updatePlanWasInspirePublished(int planId) throws Exception {
		Plan plan = getRequiredPlanById(planId);
		plan.setInspirepublished(true);
		planRepository.save(plan);
	}

	@Transactional(readOnly = true)
	public XPlanVersionAndPlanStatus selectXPlanMetadata(int planId) throws Exception {
		Plan plan = getRequiredPlanById(planId);
		XPlanVersion version = plan.getVersion();
		PlanStatus planStatus = retrievePlanStatus(plan.getPlanstatus());
		return new XPlanVersionAndPlanStatus(version, planStatus);
	}

	@Transactional(readOnly = true)
	public Set<String> selectFids(int planId) throws Exception {
		Plan plan = getRequiredPlanById(planId);
		return plan.getFeatures().stream().map(feature -> feature.getFid()).collect(Collectors.toSet());
	}

	@Transactional(readOnly = true)
	/**
	 * Retrieve a list of all XPlans.
	 * @return list of XPlans
	 * @throws Exception
	 */
	public List<XPlan> selectAllXPlans() {
		Iterable<Plan> plans = planRepository.findAll();
		return StreamSupport.stream(plans.spliterator(), false)
			.map(plan -> convertToXPlan(plan))
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
	public boolean selectPlanWithSameNameAndStatusExists(String planName, String status) {
		return planRepository.existsPlanByNameAndPlanstatus(planName, status);
	}

	@Transactional(readOnly = true)
	public boolean existsPlan(int id) {
		return planRepository.existsPlanById(id);
	}

	@Transactional(readOnly = true)
	public List<XPlan> getXPlanByName(String planName) {
		List<Plan> plans = planRepository.findByName(planName);
		return plans.stream().map(plan -> convertToXPlan(plan)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<XPlan> getXPlansLikeName(String planName) {
		List<Plan> plans = planRepository.findByNameLike(planName);
		return plans.stream().map(plan -> convertToXPlan(plan)).collect(Collectors.toList());
	}

	/**
	 * retrieves the id of the plan closest in future to the date passed
	 * @param releaseDate minimal release date
	 * @return id of plan with minimal release date, -1 if no such plan exists
	 * @throws SQLException
	 */
	@Transactional(readOnly = true)
	public int selectXPlanIdOfMoreRecentRasterPlan(Date releaseDate) {
		if (releaseDate == null)
			return -1;
		List<Plan> plan = planRepository.findByPlanWithMoreRecentRasterPlan(releaseDate);
		if (plan.isEmpty())
			return -1;
		return plan.get(0).getId();
	}

	/**
	 * exports a plan
	 * @param planId of plan to export
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public Stream<Artefact> selectAllXPlanArtefacts(int planId) {
		return artefactRepository.findAllByPlanId(planId);
	}

	/**
	 * exports a plan
	 * @param planId of plan to export
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<String> selectAllXPlanArtefactFileNames(int planId) {
		return artefactRepository.findAllFileNamesByPlanId(planId);
	}

	/**
	 * @param planId the id of the requested plan, <code>null</code>
	 * @return the original plan artefact, never <code>null</code>
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public InputStream selectXPlanGmlArtefact(int planId) throws IOException {
		Optional<Artefact> xPlanGmlByPlan = artefactRepository.findXPlanGmlByPlan(planId);
		if (xPlanGmlByPlan.isPresent()) {
			Artefact artefact = xPlanGmlByPlan.get();
			return unzipArtefactAsStream(artefact.getData());
		}
		return null;

	}

	/**
	 * Retrieve internalId by the manager id from xplanmgr.plans.
	 * @param planId the planId of the plan, never <code>null</code>
	 * @return the internal id of a plan (if available), <code>null</code> if an error
	 * occurred
	 */
	@Transactional(readOnly = true)
	public String selectInternalId(int planId) {
		Optional<Plan> plan = planRepository.findById(planId);
		if (plan.isPresent()) {
			return plan.get().getInternalid();
		}
		return null;
	}

	/**
	 * Retrieve sortDate by the manager id from xplanmgr.plans.
	 * @param planId the planId of the plan, never <code>null</code>
	 * @return the sortDate of a plan (if available), otherwise <code>null</code>
	 */
	@Transactional(readOnly = true)
	public Date selectSortDate(int planId) {
		Optional<Plan> plan = planRepository.findById(planId);
		if (plan.isPresent()) {
			return plan.get().getWmssortdate();
		}
		return null;
	}

	/**
	 * Retrieve sortDate by the manager id from xplanmgr.plans.
	 * @param planId the planId of the plan, never <code>null</code>
	 * @return the sortDate of a plan (if available), otherwise <code>null</code>
	 */
	@Transactional(readOnly = true)
	public List<Bereich> selectBereiche(int planId) {
		Optional<Plan> plan = planRepository.findById(planId);
		if (plan.isPresent()) {
			return plan.get().getBereiche().stream().map(b -> {
				Bereich bereich = new Bereich();
				bereich.setName(b.getName());
				bereich.setNummer(b.getNummer());
				return bereich;
			}).collect(Collectors.toList());
		}
		return null;
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

	private void checkBereichNummern(List<Bereich> bereiche) throws AmbiguousBereichNummernException {
		List<String> bereichNummern = new ArrayList<>();
		for (Bereich bereich : bereiche) {
			String nummer = bereich.getNummer();
			if (bereichNummern.contains(nummer))
				throw new AmbiguousBereichNummernException(nummer);
			bereichNummern.add(nummer);
		}
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

	private ArtefactType detectNonXPlanGmlArtefactType(EditedArtefact editedArtefact) {
		de.latlon.xplan.manager.edit.ArtefactType artefactType = editedArtefact.getArtefactType();
		if (artefactType == RASTER)
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

	private InputStream unzipArtefactAsStream(byte[] zippedData) throws IOException {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(zippedData);
				GZIPInputStream is = new GZIPInputStream(bis);
				ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			IOUtils.copy(is, bos);
			byte[] byteArray = bos.toByteArray();
			return new ByteArrayInputStream(byteArray);
		}
	}

	private Plan getRequiredPlanById(int planId) throws PlanNotFoundException {
		Optional<Plan> optionalPlan = planRepository.findById(planId);
		if (!optionalPlan.isPresent())
			throw new PlanNotFoundException(planId);
		return optionalPlan.get();
	}

	private Plan createPlan(XPlanArchive archive, XPlanFeatureCollection fc, PlanStatus planStatus, Date beginValidity,
			Date endValidity, Date sortDate, String internalId) throws ParseException {
		String wktFromBboxIn4326 = createWktFromBboxIn4326(fc);
		org.locationtech.jts.geom.Geometry bbox = new org.locationtech.jts.io.WKTReader().read(wktFromBboxIn4326);
		Plan plan = new Plan().importDate(new Date(System.currentTimeMillis()))
			.version(archive.getVersion())
			.type(archive.getType())
			.name(fc.getPlanName())
			.nummer(fc.getPlanNummer())
			.gkz(fc.getPlanGkz())
			.hasRaster(fc.getHasRaster())
			.releaseDate(fc.getPlanReleaseDate())
			.planstatus(retrievePlanStatusMessage(planStatus))
			.district(retrieveDistrict(fc.getFeatures(), archive.getType()))
			.wmssortdate(sortDate)
			.gueltigkeitbeginn(beginValidity)
			.gueltigkeitende(endValidity)
			.internalid(internalId)
			.bbox(bbox);
		return plan;
	}

	private Set<de.latlon.xplan.core.manager.db.model.Bereich> createBereiche(FeatureCollection synFc)
			throws AmbiguousBereichNummernException {
		List<Bereich> bereiche = FeatureCollectionUtils.retrieveBereiche(synFc);
		checkBereichNummern(bereiche);
		return createBereiche(bereiche);
	}

	private Set<de.latlon.xplan.core.manager.db.model.Bereich> createBereiche(List<Bereich> bereiche) {
		return bereiche.stream()
			.map(bereich -> new de.latlon.xplan.core.manager.db.model.Bereich().name(bereich.getName())
				.nummer(bereich.getNummer()))
			.collect(Collectors.toSet());
	}

	private Set<Feature> createFeatures(List<String> featureIds) {
		AtomicInteger index = new AtomicInteger();
		return featureIds.stream()
			.map(featureId -> new Feature().fid(featureId).num(index.getAndIncrement()))
			.collect(Collectors.toSet());
	}

	private Artefact createArtefact(Plan plan, XPlanFeatureCollection xPlanFeatureCollection, AtomicInteger i,
			ZipEntryWithContent archiveEntry, byte[] xplanGml) {
		try {
			String name = archiveEntry.getName();
			InputStream is = archiveEntry.retrieveContentAsStream();
			long contentLength = archiveEntry.getContentLength();
			String mimetype = archiveEntry.getContentType();
			ArtefactType artefactType = detectArtefactType(xPlanFeatureCollection, archiveEntry);
			byte[] data = retrieveData(xplanGml, is, artefactType);
			ArtefactId id = new ArtefactId().plan(plan).filename(name);
			Artefact artefact = new Artefact().id(id)
				.data(data)
				.mimetype(mimetype)
				.length(contentLength)
				.artefacttype(artefactType)
				.num(i.getAndIncrement());
			return artefact;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] retrieveData(byte[] xplanGml, InputStream is, ArtefactType artefactType) throws IOException {
		if (artefactType == XPLANGML)
			return createZipArtefact(new ByteArrayInputStream(xplanGml));
		return createZipArtefact(is);
	}

	private void updatePlan(XPlan oldXplan, AdditionalPlanData newAdditionalPlanData, XPlanFeatureCollection fc,
			FeatureCollection synFc, byte[] planArtefact, Date sortDate, List<File> uploadedArtefacts,
			EditedArtefacts editedArtefacts, int planId, Plan plan) throws Exception {
		XPlanType type = XPlanType.valueOf(oldXplan.getType());
		plan.name(fc.getPlanName())
			.rechtsstand(retrieveRechtsstandWert(synFc, type))
			.sonstPlanArt(retrieveAdditionalTypeWert(synFc, type))
			.wmssortdate(sortDate)
			.gueltigkeitbeginn(newAdditionalPlanData.getStartDateTime())
			.gueltigkeitende(newAdditionalPlanData.getEndDateTime())
			.planstatus(retrievePlanStatusMessage(newAdditionalPlanData.getPlanStatus()));

		Set<Artefact> planArtefacts = plan.getArtefacts();
		Optional<Integer> optionalNum = planArtefacts.stream()
			.map(artefact -> artefact.getNum())
			.max(Integer::compareTo);
		int num = optionalNum.isPresent() ? optionalNum.get() : 0;
		Optional<Artefact> optionalArtefact = planArtefacts.stream()
			.filter(artefact -> XPLANGML.equals(artefact.getArtefacttype()))
			.findFirst();
		if (!optionalArtefact.isPresent())
			throw new Exception("Plan mit ID " + planId
					+ " hat kein Artefakt vom Typ XPLANGML. Plan kann nicht aktualisiert werden.");
		Artefact xPlanGmlArtefact = optionalArtefact.get();
		xPlanGmlArtefact.data(createZipArtefact(new ByteArrayInputStream(planArtefact)));

		List<String> removedRefFileNames = editedArtefacts.getFileNames(REMOVED);
		List<Artefact> artefactsToDelete = planArtefacts.stream()
			.filter(artefact -> removedRefFileNames.contains(artefact.getId().getFilename()))
			.collect(Collectors.toList());
		planArtefacts.removeAll(artefactsToDelete);

		List<EditedArtefact> addedRefFileNames = editedArtefacts.getEditedArtefacts(ADDED);
		for (EditedArtefact editedArtefact : addedRefFileNames) {
			String fileName = editedArtefact.getFileName();
			File file = retrieveUploadedArtefact(fileName, uploadedArtefacts);
			long size = Files.size(file.toPath());
			try (FileInputStream fileInputStream = new FileInputStream(file)) {
				byte[] data = createZipArtefact(fileInputStream);
				String mimetype = getArtefactMimeType(fileName);
				ArtefactType artefactType = detectNonXPlanGmlArtefactType(editedArtefact);

				ArtefactId id = new ArtefactId().plan(plan).filename(fileName);
				Artefact artefact = new Artefact().id(id)
					.data(data)
					.mimetype(mimetype)
					.length(size)
					.artefacttype(artefactType)
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
		xPlan.setDistrict(mapToDistrict(plan.getDistrict()));
		xPlan.setInspirePublished(plan.getInspirepublished());
		xPlan.setInternalId(plan.getInternalid());
		return xPlan;
	}

	private String mapToDistrict(String districtFromPlan) {
		if (categoryMapper != null) {
			return categoryMapper.mapToCategory(districtFromPlan);
		}
		return null;
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
