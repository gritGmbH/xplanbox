/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric.inspector.aenderungen;

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

import static org.deegree.commons.xml.CommonNamespaces.XLNNS;

/**
 * Parses all XP_VerbundenerPlan/verbundenerPlan/@xlink:href of
 *
 * <pre>
 * BP_Plan/aendert
 * BP_Plan/wurdeGeaendertVon
 * BP_Plan/aendertPlan
 * BP_Plan/wurdeGeaendertVonPlan
 * BP_Plan/aendertPlanBereich
 * BP_Plan/wurdeGeaendertVonPlanBereich
 * BP_Bereich/aendertPlan
 * BP_Bereich/wurdeGeaendertVonPlan
 * BP_Bereich/aendertPlanBereich
 * BP_Bereich/wurdeGeaendertVonPlanBereich
 * </pre>
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AenderungenInspector implements FeatureInspector {

	private final List<String> lokalAendertAndWurdeGeandertVonReferences = new ArrayList<>();

	@Override
	public Feature inspect(Feature feature) throws FeatureInspectionException {
		QName name = feature.getName();
		String namespaceURI = name.getNamespaceURI();
		if ("BP_Plan".equals(name.getLocalPart())) {
			addReferences(feature.getProperties(new QName(namespaceURI, "aendert")));
			addReferences(feature.getProperties(new QName(namespaceURI, "wurdeGeaendertVon")));
			addReferences(feature.getProperties(new QName(namespaceURI, "aendertPlan")));
			addReferences(feature.getProperties(new QName(namespaceURI, "wurdeGeaendertVonPlan")));
			addReferences(feature.getProperties(new QName(namespaceURI, "aendertPlanBereich")));
			addReferences(feature.getProperties(new QName(namespaceURI, "wurdeGeaendertVonPlanBereich")));

		}
		else if ("BP_Bereich".equals(name.getLocalPart())) {
			addReferences(feature.getProperties(new QName(namespaceURI, "aendertPlan")));
			addReferences(feature.getProperties(new QName(namespaceURI, "wurdeGeaendertVonPlan")));
			addReferences(feature.getProperties(new QName(namespaceURI, "aendertPlanBereich")));
			addReferences(feature.getProperties(new QName(namespaceURI, "wurdeGeaendertVonPlanBereich")));
		}
		return feature;
	}

	/**
	 * Checks if the passed gmlId is a reference of
	 *
	 * <pre>
	 * BP_Plan/aendert (< 6.0)
	 * BP_Plan/wurdeGeaendertVon (< 6.0)
	 * BP_Plan/aendertPlan (>= 6.0)
	 * BP_Plan/wurdeGeaendertVonPlan (>= 6.0)
	 * BP_Plan/aendertPlanBereich (>= 6.0)
	 * BP_Plan/wurdeGeaendertVonPlanBereich (>= 6.0)
	 * BP_Bereich/aendertPlan (>= 6.0)
	 * BP_Bereich/wurdeGeaendertVonPlan (>= 6.0)
	 * BP_Bereich/aendertPlanBereich (>= 6.0)
	 * BP_Bereich/wurdeGeaendertVonPlanBereich (>= 6.0)
	 * </pre>
	 * @param gmlId the gmlId of the reference to check, never <code>null</code>
	 * @return <code>true</code> if the gmlId is a reference of an aenderung,
	 * <code>false</code> otherwise
	 */
	public boolean isAenderungReference(String gmlId) {
		return lokalAendertAndWurdeGeandertVonReferences.contains("#" + gmlId);
	}

	private void addReferences(List<Property> aendertOrWurdeGeandertVonProps) {
		if (aendertOrWurdeGeandertVonProps.isEmpty())
			return;
		for (Property aendertOrWurdeGeandertVon : aendertOrWurdeGeandertVonProps) {
			List<TypedObjectNode> children = aendertOrWurdeGeandertVon.getChildren();
			for (TypedObjectNode child : children) {
				GenericXMLElement xmlChild = (GenericXMLElement) child;
				if (isVerbundenerPlanFeatureType(xmlChild)) {
					for (TypedObjectNode xpVerbundenerPlanChild : xmlChild.getChildren()) {
						if (xpVerbundenerPlanChild instanceof GenericProperty) {
							if (isVerbundenerPlanProperty((GenericProperty) xpVerbundenerPlanChild)) {
								PrimitiveValue href = ((GenericProperty) xpVerbundenerPlanChild).getAttributes()
									.get(new QName(XLNNS, "href"));
								if (href != null && href.getValue() != null) {
									lokalAendertAndWurdeGeandertVonReferences.add(href.getAsText());
								}
							}
						}

					}
				}
			}
		}
	}

	private static boolean isVerbundenerPlanFeatureType(GenericXMLElement xmlChild) {
		String childName = xmlChild.getName().getLocalPart();
		return "XP_VerbundenerPlan".equals(childName) || "XP_VerbundenerPlanBereich".equals(childName);
	}

	private static boolean isVerbundenerPlanProperty(GenericProperty xpVerbundenerPlanChild) {
		String childName = xpVerbundenerPlanChild.getName().getLocalPart();
		return "verbundenerPlan".equals(childName) || "verbundenerPlanBereich".equals(childName);
	}

}
