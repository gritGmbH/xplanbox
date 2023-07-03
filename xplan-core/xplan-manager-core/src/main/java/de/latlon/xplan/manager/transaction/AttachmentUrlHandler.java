package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.edit.XPlanManipulator;
import de.latlon.xplan.manager.edit.XPlanToEditFactory;
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

	private final String documentUrl;

	public AttachmentUrlHandler(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	public void replaceRelativeUrls(int planId, XPlanFeatureCollection xPlanFeatureCollection) throws Exception {
		FeatureCollection featureCollection = xPlanFeatureCollection.getFeatures();
		XPlanVersion version = xPlanFeatureCollection.getVersion();
		XPlanType type = xPlanFeatureCollection.getType();

		XPlanToEdit xPlanToEdit = xPlanToEditFactory.createXPlanToEdit(version, type, featureCollection);
		replaceRelativeUrls(planId, xPlanToEdit);
		AppSchema appSchema = XPlanSchemas.getInstance().getAppSchema(version);
		xPlanManipulator.modifyXPlan(featureCollection, xPlanToEdit, version, type, appSchema);
	}

	public void replaceRelativeUrls(int planId, XPlanToEdit xPlanToEdit) {
		xPlanToEdit.getRasterBasis().stream().forEach(rasterBasis -> {
			List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();
			rasterReferences.forEach(rasterReference -> replaceRelativeUrl(planId, rasterReference));
		});
		xPlanToEdit.getReferences().forEach(reference -> replaceRelativeUrl(planId, reference));
	}

	private void replaceRelativeUrl(int planId, AbstractReference rasterReference) {
		if (rasterReference != null) {
			// reference
			String reference = rasterReference.getReference();
			if (reference != null) {
				String newReference = replaceReference(planId, reference);
				LOG.debug("Replace reference {} with {}.", reference, newReference);
				rasterReference.setReference(newReference);
			}
			// georeference
			String geoReference = rasterReference.getGeoReference();
			if (geoReference != null) {
				String newGeoReference = replaceReference(planId, geoReference);
				LOG.debug("Replace georeference {} with {}.", geoReference, newGeoReference);
				rasterReference.setGeoReference(newGeoReference);
			}
		}
	}

	private String replaceReference(int planId, String reference) {
		if (reference != null && !reference.startsWith("http")) {
			String newReference = documentUrl;
			newReference = newReference.replace("{planId}", Integer.toString(planId));
			newReference = newReference.replace("{fileName}", reference);
			return newReference;
		}
		return reference;
	}

}
