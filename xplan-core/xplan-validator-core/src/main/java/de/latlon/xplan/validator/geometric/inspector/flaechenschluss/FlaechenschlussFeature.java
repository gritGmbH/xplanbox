package de.latlon.xplan.validator.geometric.inspector.flaechenschluss;

import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.geometry.Geometry;

import javax.xml.namespace.QName;
import java.util.List;

/**
 *
 * Encapsulates a feature part of the Flaechenschluss.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FlaechenschlussFeature {

	private final Feature feature;

	private Geometry geometry;

	public FlaechenschlussFeature(Feature feature) {
		this.feature = feature;
	}

	public String getFeatureId() {
		return feature.getId();
	}

	public String getFeatureType() {
		return feature.getName().getLocalPart();
	}

	public Geometry getGeometry() {
		if (geometry == null) {
			String namespaceURI = feature.getType().getName().getNamespaceURI();
			QName positionPropName = new QName(namespaceURI, "position");
			List<Property> positionProperties = feature.getProperties(positionPropName);
			Property property = positionProperties.get(0);
			geometry = (Geometry) property.getValue();
		}
		return geometry;
	}

}
