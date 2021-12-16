package de.latlon.xplan.validator.geometric.inspector;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.property.GenericProperty;
import org.deegree.gml.feature.FeatureInspectionException;
import org.deegree.gml.feature.FeatureInspector;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * Parses all XP_VerbundenerPlan/verbundenerPlan/@xlink:href of BP_Plan/aendert and
 * BP_Plan/wurdeGeaendertVon
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AenderungenInspector implements FeatureInspector {

	private final List<String> lokalAendertAndWurdeGeandertVonReferences = new ArrayList<>();

	@Override
	public Feature inspect(Feature feature) throws FeatureInspectionException {
		QName name = feature.getName();
		if ("BP_Plan".equals(name.getLocalPart())) {
			String namespaceURI = name.getNamespaceURI();
			addReferences(feature.getProperties(new QName(namespaceURI, "aendert")));
			addReferences(feature.getProperties(new QName(namespaceURI, "wurdeGeaendertVon")));

		}
		return feature;
	}

	private void addReferences(List<Property> aendertOrWurdeGeandertVonProps) {
		if (aendertOrWurdeGeandertVonProps.isEmpty())
			return;
		for (Property aendertOrWurdeGeandertVon : aendertOrWurdeGeandertVonProps) {
			List<TypedObjectNode> children = aendertOrWurdeGeandertVon.getChildren();
			for (TypedObjectNode child : children) {
				GenericXMLElement xmlChild = (GenericXMLElement) child;
				if ("XP_VerbundenerPlan".equals(xmlChild.getName().getLocalPart())) {
					for (TypedObjectNode xpVerbundenerPlanChild : xmlChild.getChildren()) {
						if (xpVerbundenerPlanChild instanceof GenericProperty && "verbundenerPlan"
								.equals(((GenericProperty) xpVerbundenerPlanChild).getName().getLocalPart())) {
							PrimitiveValue href = ((GenericProperty) xpVerbundenerPlanChild).getAttributes()
									.get(new QName("http://www.w3.org/1999/xlink", "href"));
							if (href != null && href.getValue() != null) {
								lokalAendertAndWurdeGeandertVonReferences.add(href.getAsText());
							}
						}

					}
				}
			}
		}
	}

	public List<String> getLokalAendertAndWurdeGeandertVonReferences() {
		return lokalAendertAndWurdeGeandertVonReferences;
	}

}
