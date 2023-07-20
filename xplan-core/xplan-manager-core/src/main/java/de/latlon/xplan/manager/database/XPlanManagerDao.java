package de.latlon.xplan.manager.database;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.FeatureCollectionManipulator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.transaction.AttachmentUrlHandler;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static de.latlon.xplan.manager.synthesizer.FeatureTypeNameSynthesizer.SYN_FEATURETYPE_PREFIX;
import static de.latlon.xplan.manager.transaction.TransactionUtils.reassignFids;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class XPlanManagerDao extends XPlanDao {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanManagerDao.class);

	private final FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();

	private final XPlanSynthesizer xPlanSynthesizer;

	private final AttachmentUrlHandler attachmentUrlHandler;

	private final XPlanExporter xPlanExporter;

	/**
	 * @param managerWorkspaceWrapper never <code>null</code>
	 * @param xPlanDbAdapter never <code>null</code>
	 * @param xPlanSynthesizer never <code>null</code>
	 * @param attachmentUrlHandler may be <code>null</code>
	 * @param xPlanExporter may be <code>null</code> if attachmentHandler ist
	 * <code>null</code>
	 * @param applicationEventPublisher never <code>null</code>
	 */
	public XPlanManagerDao(ManagerWorkspaceWrapper managerWorkspaceWrapper, XPlanDbAdapter xPlanDbAdapter,
			XPlanSynthesizer xPlanSynthesizer, AttachmentUrlHandler attachmentUrlHandler, XPlanExporter xPlanExporter,
			ApplicationEventPublisher applicationEventPublisher) {
		super(managerWorkspaceWrapper, xPlanDbAdapter, applicationEventPublisher);
		this.xPlanSynthesizer = xPlanSynthesizer;
		this.attachmentUrlHandler = attachmentUrlHandler;
		this.xPlanExporter = xPlanExporter;
	}

	/**
	 * Stores the given XPlan in the database (and feature stores).
	 * @param archive plan archive, must not be <code>null</code>
	 * @param fc features of the main GML document from the archive, must not be
	 * <code>null</code>
	 * @param planStatus the status of the plan, may be <code>null</code>
	 * @param beginValidity the start of the validity, may be <code>null</code>
	 * @param beginValidity the end of the validity, may be <code>null</code>
	 * <code>null</code>
	 * @param internalId
	 * @return database id of the plan
	 */
	@Transactional(propagation = Propagation.MANDATORY)
	public int insert(XPlanArchive archive, XPlanFeatureCollection fc, PlanStatus planStatus, Date beginValidity,
			Date endValidity, Date sortDate, String internalId) throws Exception {
		try {
			LOG.info("Insert XPlan");
			long begin = System.currentTimeMillis();
			int planId = xPlanDbAdapter.insert(archive, fc, planStatus, beginValidity, endValidity, sortDate,
					internalId);
			manipulateXPlanGml(planId, fc);
			byte[] xPlanGml = createXPlanGml(fc);
			reassignFids(fc);
			FeatureCollection synFc = createSynFeatures(fc, archive.getVersion());
			manipulateXPlanSynGml(synFc, beginValidity, endValidity, planId, sortDate, internalId);
			List<String> fidsXPlanWfs = xPlanWfsAdapter.insert(fc, planStatus);
			xPlanDbAdapter.update(planId, archive.getType(), synFc);
			xPlanDbAdapter.updateFids(planId, fidsXPlanWfs);
			xPlanSynWfsAdapter.insert(synFc, planStatus);
			xPlanDbAdapter.insertArtefacts(planId, fc, archive, xPlanGml);

			long elapsed = System.currentTimeMillis() - begin;
			LOG.info("OK [" + elapsed + " ms].");
			return planId;
		}
		catch (AmbiguousBereichNummernException e) {
			throw e;
		}
		catch (Exception e) {
			throw new Exception("Fehler beim Einfügen: " + e.getMessage(), e);
		}
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
	 * @param internalId
	 * @throws Exception
	 */
	public void update(XPlan oldXplan, AdditionalPlanData newAdditionalPlanData, XPlanFeatureCollection fc,
			FeatureCollection synFc, byte[] planArtefact, XPlanToEdit xPlanToEdit, Date sortDate,
			List<File> uploadedArtefacts, Set<String> removedRefs, String internalId) throws Exception {
		try {
			LOG.info("Delete XPlan {}", oldXplan.getId());
			long begin = System.currentTimeMillis();

			int planId = getXPlanIdAsInt(oldXplan.getId());
			Set<String> oldFids = xPlanDbAdapter.selectFids(planId);

			xPlanDbAdapter.update(oldXplan, newAdditionalPlanData, fc, synFc, planArtefact, xPlanToEdit, sortDate,
					uploadedArtefacts, removedRefs);
			manipulateXPlanSynGml(synFc, newAdditionalPlanData.getStartDateTime(),
					newAdditionalPlanData.getEndDateTime(), planId, sortDate, internalId);

			List<String> newFids = xPlanSynWfsAdapter.update(planId, oldXplan, newAdditionalPlanData, synFc, oldFids);
			xPlanWfsAdapter.update(planId, oldXplan, newAdditionalPlanData, fc, oldFids);
			xPlanDbAdapter.updateFids(planId, newFids);

			long elapsed = System.currentTimeMillis() - begin;
			LOG.info("OK [" + elapsed + " ms].");
		}
		catch (Exception e) {
			throw new Exception("Fehler beim Einfügen: " + e.getMessage(), e);
		}
	}

	/**
	 * @param xplan to update, never <code>null</code>
	 * @param synFc to update, never <code>null</code>
	 * @param sortDate may be <code>null</code>
	 * @throws Exception
	 */
	public void updateXPlanSynFeatureCollection(XPlan xplan, FeatureCollection synFc, XPlanFeatureCollection originalFc,
			Date sortDate, String internalId, boolean updateFeaturesAndBlob) throws Exception {
		int planId = getXPlanIdAsInt(xplan.getId());
		AdditionalPlanData xplanMetadata = xplan.getXplanMetadata();
		PlanStatus planStatus = xplanMetadata.getPlanStatus();

		Set<String> ids = xPlanDbAdapter.selectFids(planId);

		manipulateXPlanSynGml(synFc, xplanMetadata.getStartDateTime(), xplanMetadata.getEndDateTime(), planId, sortDate,
				internalId);

		if (updateFeaturesAndBlob) {
			List<String> newFids = xPlanWfsAdapter.update(planId, planStatus, originalFc, ids);

			AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_SYN);
			List<QName> featureTypeNames = Arrays.stream(schema.getFeatureTypes())
					.map(featureType -> featureType.getName()).collect(Collectors.toList());

			Set<String> validIds = ids.stream().filter(oldFeatureId -> {
				Optional<QName> featureType = featureTypeNames.stream()
						.filter(featureTypeName -> oldFeatureId
								.startsWith(SYN_FEATURETYPE_PREFIX + featureTypeName.getLocalPart().toUpperCase()))
						.findFirst();
				if (featureType.isPresent()) {
					return true;
				}
				LOG.info("Es konnte kein feature type zu dem feature mit der ID " + oldFeatureId
						+ " gefunden werden. Es wird angenommen, dass dieser FeatureType nicht mehr existiert und die dazugehoerige Tabelle bereits geloescht wurde.");
				return false;
			}).collect(Collectors.toSet());

			xPlanSynWfsAdapter.update(planId, planStatus, synFc, validIds);
			xPlanDbAdapter.updateFids(planId, newFids);
		}
		else {
			xPlanSynWfsAdapter.update(planId, planStatus, synFc, ids);
		}

	}

	private FeatureCollection createSynFeatures(XPlanFeatureCollection fc, XPlanVersion version) {
		long begin = System.currentTimeMillis();
		LOG.info("- Erzeugen der XPlan-Syn Features...");
		FeatureCollection synFc = xPlanSynthesizer.synthesize(version, fc);
		long elapsed = System.currentTimeMillis() - begin;
		LOG.info("OK [" + elapsed + " ms]");
		return synFc;
	}

	private void manipulateXPlanSynGml(FeatureCollection synFc, Date beginValidity, Date endValidity, int planId,
			Date sortDate, String internalId) {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_SYN);
		featureCollectionManipulator.addAdditionalPropertiesToFeatures(synFc, schema, planId, sortDate, beginValidity,
				endValidity);
		featureCollectionManipulator.addInternalId(synFc, schema, internalId);
	}

	private void manipulateXPlanGml(int planId, XPlanFeatureCollection xPlanFeatureCollection) throws Exception {
		if (attachmentUrlHandler != null) {
			attachmentUrlHandler.replaceRelativeUrls(planId, xPlanFeatureCollection);
		}
	}

	private byte[] createXPlanGml(XPlanFeatureCollection xPlanFeatureCollection) throws Exception {
		if (xPlanExporter == null)
			return null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		FeatureCollection featureCollection = xPlanFeatureCollection.getFeatures();
		xPlanExporter.export(outputStream, xPlanFeatureCollection.getVersion(), featureCollection, null);
		return outputStream.toByteArray();
	}

}
