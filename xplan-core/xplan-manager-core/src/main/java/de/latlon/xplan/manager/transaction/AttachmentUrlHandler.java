package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.web.shared.edit.AbstractReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class AttachmentUrlHandler {

	private static final Logger LOG = LoggerFactory.getLogger(AttachmentUrlHandler.class);

	private final String documentUrl;

	public AttachmentUrlHandler(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	public void replaceRelativeUrls(int planId, XPlanFeatureCollection xPlanFeatureCollection) {
		FeatureCollection featureCollection = xPlanFeatureCollection.getFeatures();
		featureCollection.stream().iterator().forEachRemaining(feature -> {
			List<Property> properties = feature.getProperties();
			properties.forEach(elementNode -> replace(planId, elementNode));
		});
	}

	public void replaceRelativeUrls(int planId, XPlanToEdit xPlanToEdit) {
		xPlanToEdit.getRasterBasis().stream().forEach(rasterBasis -> {
			List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();
			rasterReferences.forEach(rasterReference -> replaceRelativeUrl(planId, rasterReference));
		});
		xPlanToEdit.getReferences().forEach(reference -> replaceRelativeUrl(planId, reference));
	}

	private void replace(int planId, TypedObjectNode elementNode) {
		if (elementNode instanceof Property) {
			Property property = (Property) elementNode;
			TypedObjectNode value = property.getValue();
			String name = property.getName().getLocalPart();
			if (("referenzURL".equals(name) || "georefURL".equals(name)) && value instanceof PrimitiveValue) {
				PrimitiveValue newValue = createNewValue(planId, (PrimitiveValue) value, name);
				property.setValue(newValue);
			}
			else {
				List<TypedObjectNode> children = property.getChildren();
				children.forEach(child -> replace(planId, child));
			}
		}
		else if (elementNode instanceof GenericXMLElement) {
			GenericXMLElement genericXMLElement = (GenericXMLElement) elementNode;
			String name = genericXMLElement.getName().getLocalPart();
			if (("referenzURL".equals(name) || "georefURL".equals(name)) && !genericXMLElement.getChildren().isEmpty()
					&& genericXMLElement.getChildren().get(0) instanceof PrimitiveValue) {
				PrimitiveValue newValue = createNewValue(planId,
						(PrimitiveValue) genericXMLElement.getChildren().get(0), name);
				genericXMLElement.setChildren(Collections.singletonList(newValue));
			}
			else {
				List<TypedObjectNode> children = genericXMLElement.getChildren();
				children.forEach(child -> replace(planId, child));
			}
		}
	}

	private PrimitiveValue createNewValue(int planId, PrimitiveValue value, String name) {
		String oldReference = value.getAsText();
		String newReference = replaceReference(planId, oldReference);
		LOG.debug("Replace {} {} with {}.", name, oldReference, newReference);
		return new PrimitiveValue(newReference);
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
