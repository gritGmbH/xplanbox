package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.edit.EditException;
import de.latlon.xplan.manager.edit.XPlanManipulator;
import de.latlon.xplan.manager.edit.XPlanToEditFactory;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.edit.AbstractReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class AttachmentUrlHandler {

	private static final Logger LOG = LoggerFactory.getLogger(AttachmentUrlHandler.class);

	private final XPlanToEditFactory xPlanToEditFactory = new XPlanToEditFactory();

	private final XPlanManipulator xPlanManipulator = new XPlanManipulator();

	private final String downloadUrl;

	public AttachmentUrlHandler(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public void replaceRelativeUrls(int planId, XPlanArchive archive, AdditionalPlanData additionalPlanData,
			XPlanFeatureCollection xPlanFeatureCollection) throws EditException {
		FeatureCollection featureCollection = xPlanFeatureCollection.getFeatures();
		XPlanToEdit xPlanToEdit = xPlanToEditFactory.createXPlanToEdit(archive.getVersion(), archive.getType(),
				additionalPlanData, featureCollection);
		xPlanToEdit.getRasterBasis().stream().forEach(rasterBasis -> {
			List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();
			rasterReferences.forEach(rasterReference -> replaceRelativeUrl(planId, rasterReference));
		});
		xPlanToEdit.getReferences().forEach(reference -> replaceRelativeUrl(planId, reference));
		AppSchema appSchema = XPlanSchemas.getInstance().getAppSchema(archive.getVersion());
		xPlanManipulator.modifyXPlan(featureCollection, xPlanToEdit, archive.getVersion(), archive.getType(),
				appSchema);
	}

	private void replaceRelativeUrl(int planId, AbstractReference rasterReference) {
		if (rasterReference != null) {
			// reference
			String reference = rasterReference.getReference();
			String newReference = replaceReference(planId, reference);
			rasterReference.setReference(newReference);
			// georeference
			String geoReference = rasterReference.getGeoReference();
			String newGeoReference = replaceReference(planId, geoReference);
			rasterReference.setGeoReference(newGeoReference);
		}
	}

	private String replaceReference(int planId, String reference) {
		if (reference != null && !reference.startsWith("http")) {
			String newReference = downloadUrl;
			newReference = newReference.replace("{planId}", Integer.toString(planId));
			newReference = newReference.replace("{fileName}", reference);
			return newReference;
		}
		return reference;
	}

}
